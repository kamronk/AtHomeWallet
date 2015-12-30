<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<!--
	Minimaxing by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<%@ page import="main.CoinInfo" %>
<%@ page import="main.CryptoTransaction"%>
<%@ page import="main.PasswordManager"%>
<%@ page import="main.NetworkInterfacer"%>
<%
NetworkInterfacer net = new NetworkInterfacer();
CoinInfo coin = new CoinInfo();
PasswordManager pM = new PasswordManager();

boolean loggedIn = false;
	        Cookie[] cookies = request.getCookies();
	        if (cookies != null){
	        for(int i = 0; i < cookies.length; i++) { 
	            Cookie c = cookies[i];
	            if (c.getName().equals("pwHash")) {
	            	if (pM.checkPassword(c.getValue())){
	            		loggedIn = true;
	            	}
	            }
	        }  
	        }

if (!loggedIn){
	String site = new String("./login.jsp");
	response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", site); 
}


%>
<html>
	<head>
		<title><%=coin.getName() %> HardWallet</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<link href="http://fonts.googleapis.com/css?family=Ubuntu+Condensed" rel="stylesheet">
		<script src="js/jquery.min.js"></script>
		<script src="js/sha256.js"></script>
		<script src="js/jquery.cookie.js"></script>
		<script src="js/jquery.tablesorter.pager.js"></script>
		<script src="js/staker.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-layers.min.js"></script>
		<script src="js/init.js"></script>
		<noscript>
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/skel.css" />
			<link rel="stylesheet" href="css/style-desktop.css" />
		</noscript>
		<!--[if lte IE 9]><link rel="stylesheet" href="css/ie9.css" /><![endif]-->
		<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
	</head>
	<body>
		<div id="header-wrapper">
			<div class="container">
				<div class="row">
					<div class="12u">
						
						<header id="header">
							<h1><a href="Home" id="logo"><%=coin.getAvailableBalance()%> <%=coin.getTickerSymbol() %></a></h1>
							<nav id="nav">
								<a href="Home">Overview</a>
								<a href="SendCoin">Send Coin</a>
								<a href="Addresses">Addresses</a>
								<a href="Transactions">Transactions</a>
								<!-- <a href="./walletconsole.jsp">Wallet Console</a> -->
								<a href="Settings" class="current-page-item">Settings</a>
							</nav>
						</header>
					
					</div>
				</div>
			</div>
		</div>
		<div id="main">
			<div class="container">
				<div class="row main-row">
					<div class="4u" style="width: 100%;">
						<section id="recentTransactions">
							<h2>Settings</h2>
							<div>
							<!--  
							<p class="settingsTitle">Change IP address</p>
							<input style="margin-bottom: 20px; font-size: 17px; width: 300px;" type="text" id="desiredIp" value="<%=net.getAddress()%>">
							</br>
							<input style="margin-bottom: 20px; font-size: 17px; width: 300px;" type="text" id="netMask" disabled value="<%=net.getNetmask()%>">
							</br>
							<input style="margin-bottom: 20px; font-size: 17px; width: 300px;" type="text" id="networkIp" disabled value="<%=net.getNetwork()%>">
							</br>
							<input style="margin-bottom: 20px; font-size: 17px; width: 300px;" type="text" id="broadcastIp" disabled value="<%=net.getBroadcast()%>">
							</br>
							<input style="margin-bottom: 20px; font-size: 17px; width: 300px;" type="text" id="defGateway" disabled value="<%=net.getGateway()%>">
							</br>
							<a id="changeIpAddress" class="button"><span style="font-size: 20px;">Change IP address</span></a>
							</br>
							</br>
							-->
							<p class="settingsTitle">Change Password</p>
							<input style="margin-bottom: 20px; font-size: 17px; width: 300px;" type="password" id="pwCurrent" placeholder="Enter your CURRENT password">
							</br>
							<input style="margin-bottom: 20px; font-size: 17px; width: 300px;" type="password" id="pwNew" placeholder="Enter a new password">
							</br>
							<input style="margin-bottom: 20px; font-size: 17px; width: 300px;" type="password" id="pwNewConfirm" placeholder="Confirm the new password">
							</br>
							<a id="changePassword" class="button"><span style="font-size: 20px;">Change Web UI Password</span></a>
							</br>
							</br>
							<p class="settingsTitle">Back Up Wallet.dat (download)</p>
							</br>
							<a id="downloadButton" class="button" title="You will download this from the device on your network."><span style="font-size: 20px;">Download Wallet.dat file</span></a>
							</br>
							</br>
							<p class="settingsTitle">Delete Wallet.dat</p>
							</br>
							<a id="deleteWalletButton" class="" title="This will delete the wallet.dat file on the device. This requires a 0 balance."><span style="font-size: 20px;">Delete Wallet.dat file</span></a>
							</div>
						</section>
					
					</div>
				</div>
			</div>
		</div>
		<div id="footer-wrapper">
			<div class="container">
				<div class="row">
					<div class="8u">
						
						<section>
							<h2>For help and support:</h2>
							<p>
								Please find my blog post regarding this app
								<a href="http://kamronk.com/bitcoin-wallet/" class=""><img width="50px" src="http://kamronk.com/wp-content/uploads/2015/12/38458381.png"/></a>
							</p>
							<p>
								Find me on social media
								<a href="https://twitter.com/kamronkennedy" class=""><img width="50px" src="images/twitter_icon.png"/></a>
								<a href="https://www.facebook.com/kamron.kennedy" class=""><img width="50px" src="images/facebook_icon.png"/></a>
							</p>
							<p>Or email me, at <a href="mail:kamronkennedy@gmail.com">kamronkennedy@gmail.com</a></p>
						</section>
					
					</div>
					<div class="4u">

						<section>
							<footer class="controls">
								<a href="<%=coin.getWebsite() %>" target="_blank" class="button"><img width="200px" src="images/coinLogo.png"/></a>
							</footer>
						</section>

					</div>
				</div>
				<div class="row">
					<div class="12u">

						<div id="copyright">
							&copy; Kamron K. All rights reserved. | Design: <a href="http://html5up.net">HTML5 UP</a>
						</div>

					</div>
				</div>
			</div>
		</div>
	</body>
</html>