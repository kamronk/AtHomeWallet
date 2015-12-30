package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CoinInfo {

	private String name = "Bitcoin";
	private String tickerSymbol = "BTC";
    private String walletFileName = "wallet.dat";
    private String linuxDataDirectoryName = "bitcoin";
    private String windowsDataDirectoryName = "Bitcoin";
    private String confFileName = "bitcoin.conf";
    private String port = "9368";
    private String website = "https://mazacoin.org/";
    private String blkExpAddressWebsite = "https://blockchain.info/address/";
    private String blkExpTransactionWebsite = "https://blockchain.info/tx/";

//	private String name = "Mazacoin";
//	private String tickerSymbol = "MZC";
//    private String walletFileName = "wallet.dat";
//    private String linuxDataDirectoryName = "mazacoin";
//    private String windowsDataDirectoryName = "Mazacoin";
//    private String confFileName = "mazacoin.conf";
//    private String port = "9370";
//    private String website = "https://mazacoin.org/";
//    private String blkExpAddressWebsite = "http://explorer.coinpayments.net/address.php?chain=11&addr=";
//    private String blkExpTransactionWebsite = "http://explorer.coinpayments.net/transaction.php?chain=11&hash=";

//	private String name = "BTI";
//	private String tickerSymbol = "BTI";
//    private String walletFileName = "wallet.dat";
//    private String linuxDataDirectoryName = "bti";
//    private String windowsDataDirectoryName = "BTICurrency";
//    private String confFileName = "bticurrency.conf";
//    private String port = "9366";
//    private String website = "http://blockchaintechs.com/";
//    private String blkExpAddressWebsite = "http://explorer.blockchaintechs.com/address/";
//    private String blkExpTransactionWebsite = "http://explorer.blockchaintechs.com/tx/";

//	private String name = "Mintcoin";
//	private String tickerSymbol = "MINT";
//    private String walletFileName = "wallet.dat";
//    private String linuxDataDirectoryName = "MintCoin";
//    private String windowsDataDirectoryName = "MintCoin";
//    private String confFileName = "mintcoin.conf";
//    private String port = "9365";
//    private String website = "http://www.mintcoinofficial.com/";
//    private String blkExpAddressWebsite = "http://www.multifaucet.tk/index.php?blockexplorer=MINT&address=";
//    private String blkExpTransactionWebsite = "http://www.multifaucet.tk/index.php?blockexplorer=MINT&txid=";
	
	public String getWebsite() {
		return website;
	}
	public String getBlkExpAddressWebsite() {
		return blkExpAddressWebsite;
	}
	public String getBlkExpTransactionWebsite() {
		return blkExpTransactionWebsite;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getPort() {
		return port;
	}
	public String getName() {
		return name;
	}
	public String getTickerSymbol() {
		return tickerSymbol;
	}
	public String getWalletFileName() {
		return walletFileName;
	}
	public String getLinuxDataDirectoryName() {
		return linuxDataDirectoryName;
	}
	public String getWindowsDataDirectoryName() {
		return windowsDataDirectoryName;
	}
	public String getConfFileName() {
		return confFileName;
	}
	
	public String getBlockCount(){

        try {
        	CommWithWallet that = new CommWithWallet();

        	JSONParser parser = new JSONParser();

			JSONObject result = (JSONObject) parser.parse(that.CryptoInvoke("getblockcount"));

			return result.get("result").toString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "Error";
	}
	
	public String getConnectionCount(){

        try {
        	CommWithWallet that = new CommWithWallet();

        	JSONParser parser = new JSONParser();

			JSONObject result = (JSONObject) parser.parse(that.CryptoInvoke("getconnectioncount"));

			return result.get("result").toString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "Error";
	}

	public String getBalance(){

        try {
        	CommWithWallet that = new CommWithWallet();

        	JSONParser parser = new JSONParser();

			JSONObject result = (JSONObject) parser.parse(that.CryptoInvoke("getbalance"));

			return result.get("result").toString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "Error";
	}


	public String getAvailableBalance(){

        try {
        	CommWithWallet that = new CommWithWallet();

        	JSONParser parser = new JSONParser();

			JSONObject result = (JSONObject) parser.parse(that.CryptoInvoke("getbalance"));
			System.out.println(result);
			return result.get("result").toString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "Error";
	}

	public Float getReceviedForAddress(String address){

        try {
        	CommWithWallet that = new CommWithWallet();

        	JSONParser parser = new JSONParser();

			JSONObject result = (JSONObject) parser.parse(that.CryptoInvoke("getreceivedbyaddress", address));

			return Float.parseFloat(result.get("result").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return 0f;
	}

    public ArrayList<CryptoTransaction> getRecentTransactions() {
    	
    	ArrayList<CryptoTransaction> txs = new ArrayList<CryptoTransaction>();
    	
    	int numberOfRecentTransactions = 10;

        try {
            CommWithWallet communicator = new CommWithWallet();

            JSONParser parser = new JSONParser();

            JSONObject result = (JSONObject) parser.parse(communicator.CryptoInvoke("listtransactions", "", numberOfRecentTransactions, 0));

            System.out.println(result.toJSONString());

            JSONArray txList = (JSONArray) result.get("result");

            System.out.println(txList.toJSONString());

            for (Object tx : txList) {

                JSONObject txObj = (JSONObject) parser.parse(tx.toString());
                
                String category = txObj.get("category").toString();
                float amount = Float.parseFloat(txObj.get("amount").toString());
                String txId = txObj.get("txid").toString();
                String time = txObj.get("time").toString();
                int confs = Integer.parseInt(txObj.get("confirmations").toString());
                double fee = 0;
                if (category.equals("send")){
                    fee = (Double) txObj.get("fee");
                }
                
                txs.add(new CryptoTransaction(category, amount, txId, time, confs, fee)); 
            }
            
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Collections.reverse(txs);
        return txs;
    }

    public ArrayList<CryptoTransaction> getTransactions() {
    	
    	ArrayList<CryptoTransaction> txs = new ArrayList<CryptoTransaction>();
    	
    	int numberOfTransactions = 10000;

        try {
            CommWithWallet communicator = new CommWithWallet();

            JSONParser parser = new JSONParser();

            JSONObject result = (JSONObject) parser.parse(communicator.CryptoInvoke("listtransactions", "", numberOfTransactions, 0));

            System.out.println(result.toJSONString());

            JSONArray txList = (JSONArray) result.get("result");

            System.out.println(txList.toJSONString());

            for (Object tx : txList) {

                JSONObject txObj = (JSONObject) parser.parse(tx.toString());
                
                String category = txObj.get("category").toString();
                float amount = Float.parseFloat(txObj.get("amount").toString());
                String txId = txObj.get("txid").toString();
                String time = txObj.get("time").toString();
                int confs = Integer.parseInt(txObj.get("confirmations").toString());
                double fee = 0;
                if (category.equals("send")){
                	fee = (Double) txObj.get("fee");
                }
                
                txs.add(new CryptoTransaction(category, amount, txId, time, confs, fee));
            }
            
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Collections.reverse(txs);
        return txs;
    }

    public ArrayList<CryptoAddress> getAddresses() {
    	
    	ArrayList<CryptoAddress> addys = new ArrayList<CryptoAddress>();
    	
        try {
            CommWithWallet communicator = new CommWithWallet();

            JSONParser parser = new JSONParser();

            JSONObject result = (JSONObject) parser.parse(communicator.CryptoInvoke("getaddressesbyaccount", ""));

            System.out.println(result.toJSONString());

            JSONArray txList = (JSONArray) result.get("result");

            for (Object tx : txList) {

                String address = tx.toString();
                
                addys.add(new CryptoAddress(address, getReceviedForAddress(address)));
            }
            
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Collections.reverse(addys);
        return addys;
    }

	
}
