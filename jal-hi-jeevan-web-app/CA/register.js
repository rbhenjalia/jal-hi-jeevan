// Initialize Firebase
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

	document.getElementById("state").innerText = s;
	
    var a = "All.html?"+k;
	var e = "Pending.html?"+k;
	var z = "Rejected.html?"+k;
	var t = "AdminHome.html?"+k;
	var u = "Register.html?"+k;
	
    document.getElementById('all').href = a;
    document.getElementById('pend').href = e;
	document.getElementById('rej').href = z;
	document.getElementById('adho').href = t;
	document.getElementById('register').href = u;
	
function add()
{
	
	
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
	
    document.getElementById('all').href = a;
    document.getElementById('pend').href = e;
	document.getElementById('rej').href = z;
	document.getElementById('adho').href = t;
	
	
  
  var name = document.getElementById('name').value;	
  var phone = document.getElementById('phone').value;	
  var state = s;

  var p = document.getElementById("Category").selectedIndex;
  var q = document.getElementById("Category").options;
 
  var category = q[p].text;

  var ad = "ad";
  var st = state.slice(0, 3).toLowerCase();
  var ct = category.slice(0, 3).toLowerCase();

  var id = ad+st+ct;
  var pass = "123"; 
  var flag=1;
    firebase.database().ref('/AdminDB/'+id).on('value',snap => 
	{
	  
	 var tap =  JSON.stringify(snap.val());
	 var obj = JSON.parse(tap);
      
  
     if(validatePhone(phone))
	  {     
            
	        var data = {Name:name,Password:pass,Phone:phone,State:state,Department:category,UserID:id}
            var updates = {};
            updates['/AdminDB/'+id] = data;
            firebase.database().ref().update(updates);
		
  		    window.setTimeout(function(){

               alert("User Created");	
			   window.location.href = t;

            }, 3000);
      }
	  
	  else
	  {
		  
         alert("Enter a valid phone number");	
    
	  }
  
  	});
	
	
  
  
}

function validatePhone(phone)
{
	
	 var IndNum = /^[0]?[789]\d{9}$/;

     if(IndNum.test(phone))
	 {
        return true;
     }

    else
	{
	    return false;
	}
}




