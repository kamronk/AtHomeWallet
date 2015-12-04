<%@page import="main.PasswordManager"%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE HTML>
<!--
	Minimaxing by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<%@ page import="main.CoinInfo" %>
<%

CoinInfo coin = new CoinInfo();

%>
<html>
	<head>
		<title>Enter Password</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<link href='http://fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css'>
		<script src="js/jquery.min.js"></script>
		<script src="js/sha256.js"></script>
		<script src="js/jquery.cookie.js"></script>
		<script src="js/login.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-layers.min.js"></script>
		<script src="js/init.js"></script>
		<noscript>
			<link rel="stylesheet" href="css/skel.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-desktop.css" />
		</noscript>
		<!--[if lte IE 9]><link rel="stylesheet" href="css/ie9.css" /><![endif]-->
		<!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
	</head>
	<body>
		<div style="margin-top: 100px;" id="main">
			<div class="container">
				<div class="row main-row">
					<div class="12u">
						
						<section style="text-align: center;">
							<%
							PasswordManager pm = new PasswordManager();
							if (pm.isThereApassword()){
								%> 
									<h2>Enter your password</h2>
									<input style="margin-bottom: 20px; font-size: 20px;" type="password" id="pwCreate" placeholder="Please enter a password">
									</br>
									</br>
									</br>
								<%
							} else {
								%> 
									<span style="font-size: 50px; padding: 20px;">WELCOME!</span>
									</br>
									</br>
									</br>
									</br>
									<h2>Please enter a password to use with your HardWallet activity.</h2>
									<h1> There are no restrictions or requirements for your password, but it must be atleast one character.</h1>
									</br>
									</br>
									<span style="font-size: 20px; padding: 20px;"> Blockchain Technologies Inc (BTI) recommends 8 characters or more using uppercase letters, lower case letters, numbers and symbols.</span>
									</br>
									</br>
									</br>
									</br>
									</br>
									<input style="margin-bottom: 20px; font-size: 20px;" type="password" id="pwCreate" placeholder="Please enter a password">
									</br>
									<input style="margin-bottom: 20px; font-size: 20px;" type="password" id="pwConfirm" placeholder="Confirm your password">
									</br>
									</br>
									</br>
								<%
							}
							%>
							<a class="button"><span style="font-size: 20px; padding: 20px;">Continue</span></a>
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
								Please join us in our bitcointalk forum thread.
								<a href="https://bitcointalk.org/index.php?topic=1079404.0" class=""><img width="50px" src="images/bitcointalkicon.png"/></a>
							</p>
							<p>
								Find us on social media
								<a href="https://twitter.com/blockchaintechs" class=""><img width="50px" src="images/twitter_icon.png"/></a>
								<a href="https://www.facebook.com/blockchaintechnologies" class=""><img width="50px" src="images/facebook_icon.png"/></a>
							</p>
							<p>Or email us, at <a href="mail:darren@blockchaintechs.com">darren@blockchaintechs.com</a></p>
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
							&copy; Blockchain Technologies Inc. All rights reserved. | Design: <a href="http://html5up.net">HTML5 UP</a>
						</div>

					</div>
				</div>
			</div>
		</div>
	</body>
</html>