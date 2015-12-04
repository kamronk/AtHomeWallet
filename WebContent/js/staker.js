
function isnn(inVar){
	if(inVar != null && inVar != ""){
		return true;
	} else {
		return false;
	}
}


$( document ).ready(function() {
	console.log('In.');
	
    $("#changeIpAddress").unbind("click");
    $("#changeIpAddress").click(function () {
    	console.log('Attempting to change the IP address...');
    	desiredIp = $("#desiredIp").val();
    	if (isnn(desiredIp)){
    		
    		ipElements = desiredIp.split(".");
    		console.log(ipElements.length);
    		if (ipElements.legnth != 4){
        		if (isNaN(ipElements[0]) || isNaN(ipElements[1]) || isNaN(ipElements[2]) || isNaN(ipElements[3])){
        			alert('Your IP address is invalid, some elements are not a number.');
        		} else {
        			$.post("ChangeIpAddress",
        		    		  {
        					pwhash: $.cookie("pwHash")
        		    		  },
        		    		  function(data,status){
        		    		    console.log("Data: " + data + "\nStatus: " + status);
        		    		    if (data.split(':')[0] == '1') {
        		    		    	alert('Your wallet was reset, ' + data.split(':')[1]);
        		    		    	$("#destinationAddress").val("");
        		    		    	$("#sendAmount").val("");
        		    		    } else{
        		    		    	alert('Your wallet was NOT reset: ' + data.split(':')[1])
        		    		    }
        		    		  });
        		}
    		} else {
    			alert('Your IP address is invalid.');
    		}
    		
    		
    	}
    });
	
    $("#downloadButton").unbind("click");
    $("#downloadButton").click(function () {
    	console.log('Attempting to download the file...');
        window.location.href = "./WalletDat?token=" + $.cookie("pwHash");
    });
	
    $("#deleteWalletButton").unbind("click");
    $("#deleteWalletButton").click(function () {
		$.post("RstWallDat",
	    		  {
			token: $.cookie("pwHash")
	    		  },
	    		  function(data,status){
	    		    console.log("Data: " + data + "\nStatus: " + status);
	    		    if (data.split(':')[0] == '1') {
	    		    	alert('Your wallet was reset, ' + data.split(':')[1]);
	    		    	$("#destinationAddress").val("");
	    		    	$("#sendAmount").val("");
	    		    } else{
	    		    	alert('Your wallet was NOT reset: ' + data.split(':')[1])
	    		    }
	    		  });
    });
	
    $("#changePassword").unbind("click");
    $("#changePassword").click(function () {
    	currPass = $("#pwCurrent").val();
    	newPass = $("#pwNew").val();
    	confPass = $("#pwNewConfirm").val();
    	console.log('currPass: ' + currPass + '\nnew:' + newPass + '\nconfPass:' + confPass);

    	if (isnn(currPass) && isnn(newPass) && isnn(confPass)){
        	currPass = sha256_digest($("#pwCurrent").val());
        	newPass = sha256_digest($("#pwNew").val());
        	confPass = sha256_digest($("#pwNewConfirm").val());
        	console.log('currPass: ' + currPass + '\nnew:' + newPass + '\nconfPass:' + confPass);
        	
    		if (newPass == confPass){
				$.post("LogInGetCookie",
			    		  {
					pwhash: newPass,
					pwhashconfirm: confPass,
					currentpwhash: currPass
			    		  },
			    		  function(data,status){
			    		    console.log("Data: " + data + "\nStatus: " + status);
			    		    if (data.split(':')[0] == '0') {
			    		    	alert('Your password was successfuly reset, ' + data.split(':')[1]);
			    		    	$("#pwCurrent").val("");
			    		    	$("#pwNew").val("");
			    		    	$("#pwNewConfirm").val("");
			    		    } else{
			    		    	alert('Your password was not changed: ' + data.split(':')[1])
			    		    }
			    		  });
    		} else {
    			alert('Your new and confirmation passwords do not match');
    		}
    	} else {
    		alert('All feilds between the "Change Password" title and this button are required to change your password.');
    	}
    });
    
    
    $("#sendCoinButton").unbind("click");
    $("#sendCoinButton").click(function () {
    	destAddy = $("#destinationAddress").val();
    	amount = $("#sendAmount").val();
    	
    	if (isNaN(amount)){
    		alert('Amount needs to be a number');
    	} else {
    		if (isnn(destAddy)){
				$.post("SendCoinAction",
		    		  {
					pwhash: $.cookie("pwHash"),
					address: destAddy,
					amount: amount
		    		  },
		    		  function(data,status){
		    		    console.log("Data: " + data + "\nStatus: " + status);
		    		    if (data.split(':')[0] == '1') {
		    		    	alert('Your coin was sent, ' + data.split(':')[1]);
		    		    	$("#destinationAddress").val("");
		    		    	$("#sendAmount").val("");
		    		    } else{
		    		    	alert('Your coin was NOT sent: ' + data.split(':')[1] + " " + data.split(':')[2]);
		    		    }
		    		  });
    		} else {
    			alert('Address cannot be null/empty');
    		}
    	}
    });
//	$("#ddd").click(function() {
//    	$("#banner-wrapper").fadeOut();
//		$("#recentTransactions").fadeOut();
//		$("#recentTransactions").fadeIn();
//	});
//	$("#eee").click(function() {
//		$("#recentTransactions").fadeOut();
//    	$("#banner-wrapper").fadeIn();
//		$("#recentTransactions").fadeIn();
//	});
});