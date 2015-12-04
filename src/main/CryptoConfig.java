package main;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KamronK
 */
public class CryptoConfig {
    
    private String url;
    private String rpcPort;
    private String rpcUser;
    private String rpcPass;
    
    public CryptoConfig(String urlIN, String rpcPortIN, String rpcUserIN, String rpcPassIN){
        this.url = urlIN;
        this.rpcPort = rpcPortIN;
        this.rpcUser = rpcUserIN;
        this.rpcPass = rpcPassIN;
    }
    
    public CryptoConfig(){
        
    }

    public String getRpcPass() {
        return rpcPass;
    }

    public void setRpcPass(String rpcPass) {
        this.rpcPass = rpcPass;
    }

    public String getRpcPort() {
        return rpcPort;
    }

    public void setRpcPort(String rpcPort) {
        this.rpcPort = rpcPort;
    }

    public String getRpcUser() {
        return rpcUser;
    }

    public void setRpcUser(String rpcUser) {
        this.rpcUser = rpcUser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
    
}
