
function logIn(){
	pwFirst = sha256_digest($("#pwCreate").val());
	pwConfirm = $("#pwConfirm").val();
	
	if (pwConfirm != null && pwConfirm != ""){
		pwConfirm = sha256_digest($("#pwConfirm").val());
	}
	
	if (pwFirst != ''){
		
		if (pwFirst == pwConfirm){
			$.post("LogInGetCookie",
	    		  {
				pwhash: pwFirst,
				pwhashconfirm: pwConfirm
	    		  },
	    		  function(data,status){
	    		    console.log("Data: " + data + "\nStatus: " + status);
	    		    if (data.split(':')[0] == '1') {
                        $.cookie("pwHash", pwFirst);
                        window.location.href = "./";
	    		    } else{
	    		    	alert('Your password was not stored successfully because of: ' + data.split(':')[1])
	    		    }
	    		  });
		} else {
			$.post("LogInGetCookie",
	    		  {
				pwhash: pwFirst
	    		  },
	    		  function(data,status){
	    		    console.log("Data: " + data + "\nStatus: " + status);
	    		    if (data.split(':')[0] == '0') {
                        $.cookie("pwHash", pwFirst);
                        window.location.href = "./";
	    		    } else{
	    		    	alert('Your passwords did not match: ' + data.split(':')[1])
	    		    }
	    		  });
		}
	    
	} else {
		alert('Your password is incorrect or empty.');
	}
}


$( document ).ready(function() {
	console.log('In.');

    $(document).keypress(function (e) {
        if (e.keyCode == 13) {
            logIn();
        }
    })
	
	$(".button").click(function() {
        logIn();
	});
});