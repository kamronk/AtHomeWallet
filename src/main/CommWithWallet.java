package main;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author KamronK
 */
public class CommWithWallet {

    private CoinInfo coinInfo = new CoinInfo();
    private String PORT_NUMBER = coinInfo.getPort();
    private String WALLET_FILE_NAME = coinInfo.getWalletFileName();
    private String LINUX_DATA_DIR_NAME = coinInfo.getLinuxDataDirectoryName();
    private String WINDOWS_DATA_DIR_NAME = coinInfo.getWindowsDataDirectoryName();
    private String CONF_FILE_NAME = coinInfo.getConfFileName();

    public static void main(String[] args) {
		try {
        CommWithWallet that = new CommWithWallet();
    	JSONParser parser = new JSONParser();

		JSONObject result;
			result = (JSONObject) parser.parse(that.CryptoInvoke("getblockcount"));

		System.out.println(result.get("result").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public CommWithWallet() {
    }
    
    public String DeleteWallet(){
        try {
        	CommWithWallet that = new CommWithWallet();

        	JSONParser parser = new JSONParser();

			JSONObject result = (JSONObject) parser.parse(that.CryptoInvoke("getbalance"));

			float balance = Float.parseFloat(result.get("result").toString());
			
			if (balance > 0.001f){
				return "-1:Cannot delete wallet.dat, greater than 0.001 balance";
			} else {
		        String dir = "";
		        String fileLocation = "";
		        if (System.getProperty("user.home").contains("C:")) {
		            dir = System.getProperty("user.home") + "\\AppData\\Roaming\\" + WINDOWS_DATA_DIR_NAME;
		            fileLocation = dir + "\\" + WALLET_FILE_NAME;
		        } else {
		            dir = "/root/." + LINUX_DATA_DIR_NAME;
		            fileLocation = dir + "/" + WALLET_FILE_NAME;
		        }
		        File walletFile = new File(fileLocation);
//		        if (walletFile.exists()){
		        if (walletFile.delete()){
		        	return "1:Wallet file deleted.";
		        } else {
		        	return "-1:Wallet file removal failed.";
		        }
		        
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-2:Error:" + e.getLocalizedMessage();
		}
    }

    public String CryptoInvoke(String a_sMethod, Object... a_params) {

        String returnString = "";

        CryptoConfig configDetails = new CryptoConfig();
        configDetails.setUrl("http://127.0.0.1");
        configDetails.setRpcPort("" + PORT_NUMBER);
        configDetails.setRpcUser("anyusername");
        configDetails.setRpcPass("anypasswordyouwant");

//        System.out.println(configDetails.getUrl());
//        System.out.println(configDetails.getRpcPort());
//        System.out.println(configDetails.getRpcUser());
//        System.out.println(configDetails.getRpcPass());
//        System.out.println(a_sMethod);
//        System.out.println(currency);

        try {

            String urlString = configDetails.getUrl() + ":" + configDetails.getRpcPort();
            String signature = "";

            String userPassword = configDetails.getRpcUser() + ":" + configDetails.getRpcPass();
            String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());

            JSONObject paramsJson = new JSONObject();
            paramsJson.put("jsonrpc", "1.0");
            paramsJson.put("id", "1");
            paramsJson.put("method", a_sMethod);

            if (a_params != null) {
                if (a_params.length > 0) {
                    JSONArray paramArray = new JSONArray();
                    for (Object baz : a_params) {
                        paramArray.add(baz);
                    }
                    paramsJson.put("params", paramArray);
                }
            }

            CloseableHttpClient cliente = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(urlString);
            String comando = paramsJson.toJSONString();
            StringEntity entidad = new StringEntity(comando);
            httpPost.setEntity(entidad);
            httpPost.setHeader("Authorization", "Basic " + encoding);
            HttpResponse respuesta = cliente.execute(httpPost);
            BufferedReader rd = new BufferedReader(new InputStreamReader(respuesta.getEntity().getContent()));
            String inputLine = "";
            returnString = "";
            while ((inputLine = rd.readLine()) != null) {
                returnString += inputLine;
            }
            rd.close();
            httpPost.releaseConnection();
            cliente.close();
        } catch (NumberFormatException ne) {
            // btcAmountString was not a number
            ne.printStackTrace();
        } catch (IOException ne) {
            // btcAmountString was not a number
            ne.printStackTrace();
        }

        return returnString;
    }

    public CryptoConfig ReadConfig() {

        String portNumber = "";
        String dir = "";
        String fileLocation = "";

        CryptoConfig config = new CryptoConfig();

        if (System.getProperty("user.home").contains("C:")) {
            dir = System.getProperty("user.home") + "\\AppData\\Roaming\\" + WINDOWS_DATA_DIR_NAME;
            fileLocation = dir + "\\" + CONF_FILE_NAME;
        } else {
            dir = "/root/.bitcoin";
            fileLocation = dir + "/" + CONF_FILE_NAME;
        }
                portNumber = PORT_NUMBER;

            ArrayList<String> fileInfo = new ArrayList<String>();
            try {
                BufferedReader in = new BufferedReader(new FileReader(fileLocation));
                String inputLine = "";
                while ((inputLine = in.readLine()) != null) {
                    fileInfo.add(inputLine);
                }
                in.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CommWithWallet.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                System.out.println(fileLocation);
            } catch (IOException ex) {
                Logger.getLogger(CommWithWallet.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                System.out.println(fileLocation);
            }

            for (String configLine : fileInfo) {

                String configKey = "";
                String configValue = "";
                int index = configLine.indexOf('=');
                if (index > 0) {
                    configKey = configLine.substring(0, index);
                    configValue = configLine.substring(configLine.lastIndexOf('=') + 1);

                    if (configKey.equals("walleturl")) {
                        config.setUrl(configValue);
                    } else if (configKey.equals("rpcport")) {
                        config.setRpcPort(configValue);
                    } else if (configKey.equals("rpcuser")) {
                        config.setRpcUser(configValue);
                    } else if (configKey.equals("rpcpassword")) {
                        config.setRpcPass(configValue);
                    }


                }
            }


        return config;
    }
}
