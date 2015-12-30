# AtHomeWallet

Bitcoin wallet UI built to interface with a crypto-currencies daemon JSON-RPC API

This is a java 1.7 web application and uses the JSON-RPC interface of standard crypto daemon wallets. 

It is designed to be used on micro computers that have a cryptocurrency daemon installed and running on them. 

All of the basic abilities found in a standard QT wallet can be found in this app. Balances, addresses, sending and transaction history. You can also download the wallet.dat file using the UI and delete the wallet.dat file.

It is also designed to be password protected per browser session.

It has been tested with a number of different altcoins, and bitcoin. I hope to get some feedback on some things that could be done better, for example deleting the wallet.dat file. Giving extra permissions to the tomcat user to be allowed to delete such a file does not sound like the proper way to handle such processes, but this is the way it works for now.

In /src/main/ you will find CoinInfo.java, this file is to be edited for the crypto you plan to produce the web wallet for. It will need the same properties found in a .conf file (port, username, password) as well as will need the name of the cryptocurrency, and the abbreviation/ticker symbol. You should also include block exporer URIs in this file for links of addresses and transaction hashes to lead to the block explorer of your crypto. The website for your coin will also be needed so that users can find more info on the cryptocurrency. Last thing would need to be the coins logo. This can be found in /WebContent/images/coinLogo.png. This image will need to be replaced with the logo of a coin you would like it to work with.

In /WebContent/css/ you can find the style.css file where the colors can be changed to the branding of a certain coin. You can see examples of other coins styles as well in this directory. They are contained within the extra directories there.
