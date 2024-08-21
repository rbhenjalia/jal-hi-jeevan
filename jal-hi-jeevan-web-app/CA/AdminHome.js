
	var config = {
	
	apiKey: "AIzaSyCBiGZ2FM8q1a4-nRLf-RYpnWrj1o4iNfM",
    authDomain: "trap-cecfc.firebaseapp.com",
    databaseURL: "https://trap-cecfc.firebaseio.com",
    projectId: "trap-cecfc",
    storageBucket: "trap-cecfc.appspot.com",
    messagingSenderId: "702600703488"
	
  };
	 

	 firebase.initializeApp(config);
  	

		var queryString = decodeURIComponent(window.location.search);
    queryString = queryString.substring(1);
    var keyValues = queryString.split('&'); 
     
    var n = keyValues[0].toString();
	var q = keyValues[1].toString();
	var s = keyValues[2].toString();
    
	var j = n+"&"+q+"&"+s;
	var k= j.toString();
	
    var a = "All.html?"+k;
	var e = "Pending.html?"+k;
	var z = "Rejected.html?"+k;
	var t = "AdminHome.html?"+k;
    var u = "Register.html?"+k;
	var v = "Edit.html?"+k;
	
    document.getElementById('all').href = a;
    document.getElementById('pend').href = e;
	document.getElementById('rej').href = z;
	document.getElementById('adho').href = t;
	document.getElementById('reg').href = u;
	document.getElementById('edt').href = v;
	
	
	
	
    var m=1;
	var p=1;
    	
	flagx=1;
    flagw=1; 	
    function changePassword()
    {
		
	   var x = document.createElement("INPUT");
     	
	   var bb = document.getElementById("bb");
	    
       x.setAttribute("type", "password");
	   x.setAttribute("id", "cp");
	   x.setAttribute("placeholder", "Enter New Password Here");
     
	   var y = document.createElement("INPUT");
       y.setAttribute("type", "button");
	   y.setAttribute("id", "btp");
       y.setAttribute("value", "Change Password");
       y.setAttribute("onclick", "changeP()");
     
	   bb.appendChild(x);
	   bb.appendChild(y);
	
	}


    function changeP()
    {
	    var cp = document.getElementById("cp").value;
		
		if(cp!="")
		{
        var r = confirm("Are you sure you want to update your password?");
        
		if (r == true) 
		{
		    var r = q+"/Password"
	        var updates = {};
			window.alert(r);
            updates["/AdminDB/"+r] = cp.toString();
            firebase.database().ref().update(updates);
            
			window.alert("Password Changed Successfully!!");
             
			 var dd = document.getElementById("bb");
       	     var inp = document.getElementById("cp");
             var bt =  document.getElementById("btp");
		     dd.removeChild(inp);
		     dd.removeChild(bt);
			 p=1;
		} 
		else 
		{
            window.alert("Password Change Aborted");
			 var dd = document.getElementById("bb");
       	     var inp = document.getElementById("cp");
             var bt =  document.getElementById("btp");
		     dd.removeChild(inp);
		     dd.removeChild(bt);
			 p=1;

        }
		}
		
		else
		{
		   window.alert("Text Box Empty..");
		}
	
	}	
	
	 function changeM()
    {
	    var cm = document.getElementById("cm").value;
		
		if(cm!="")
		{
        var r = confirm("Are you sure you want to update your contact number?");
        
		if (r == true) 
		{
		    var r = q+"/Phone"
	        var updates = {};
			window.alert(r);
            updates["/AdminDB/"+r] = cm.toString();
            firebase.database().ref().update(updates);
            
			window.alert("Phone Number Changed Successfully!!");
             
			 var dd = document.getElementById("dd");
       	     var inp = document.getElementById("cm");
             var bt =  document.getElementById("btm");
		     dd.removeChild(inp);
		     dd.removeChild(bt);
			 p=1;
		} 
		else 
		{
            window.alert("Change Aborted");
			 var dd = document.getElementById("dd");
       	     var inp = document.getElementById("cm");
             var bt =  document.getElementById("btm");
		     dd.removeChild(inp);
		     dd.removeChild(bt);
			 p=1;

        }
		}
		
		else
		{
		   window.alert("Text Box Empty..");
		}
	
	}
	
	
	function changeMobileNumber()
    {
	   var x = document.createElement("INPUT");
       var dd = document.getElementById("dd");
       x.setAttribute("type", "text");
	   x.setAttribute("id", "cm");
	   x.setAttribute("placeholder", "Enter New Mobile Here");
     
	   var y = document.createElement("INPUT");
       y.setAttribute("type", "button");
       y.setAttribute("id", "btm");
	   y.setAttribute("value", "Change Mobile Number");
       y.setAttribute("onclick", "changeM()");
     
	 dd.appendChild(x);
	   dd.appendChild(y);
	 if(m==1)
	 {
       dd.appendChild(x);
	   dd.appendChild(y);
	   m=2;
	 }
	 
	 else 
	 {
	    var inp = document.getElementById("cm");
        var bt =  document.getElementById("btm");
		dd.removeChild(inp);
		dd.removeChild(bt);
	    m=1;
	 }
	}	