<%@page import="main.CryptoAddress"%>
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
<%

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
							<h1><a href="./" id="logo"><%=coin.getAvailableBalance()%> <%=coin.getTickerSymbol() %></a></h1>
							<nav id="nav">
								<a href="Home">Overview</a>
								<a href="SendCoin">Send Coin</a>
								<a href="Addresses" class="current-page-item">Addresses</a>
								<a href="Transactions">Transactions</a>
								<!-- <a href="./walletconsole.jsp">Wallet Console</a> -->
								<a href="Settings">Settings</a>
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
							<h2>Addresses</h2>
							<table class="addressReport table-responsive">
							<thead style="font-color: white;">
								<tr>
									<th>QR Code</th>
									<th>Address</th>
									<th>Received</th>
								</tr>
							</thead>
							<tbody>
								<%
								
								for (CryptoAddress addr : coin.getAddresses()){
									%>
									
									<tr>
										<td><img width="150px" src="http://chart.apis.google.com/chart?cht=qr&chs=300x300&chl=<%=coin.getName() %>%3A<%=addr.getAddress() %>"></td>
										<td><a href="<%=coin.getBlkExpAddressWebsite() %><%=addr.getAddress() %>"><%=addr.getAddress() %></a></td>
										<td><%=addr.getReceived() %></td>
									</tr>
									
									<%
								}
								
								%>
							</tbody> 
							</table>
							
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