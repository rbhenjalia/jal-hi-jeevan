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
	
function loadUsers()
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
	
 
	 var table = document.getElementById("table");
	 
	firebase.database().ref('AdminDB/').on('value',snap => 
	{
	 
	  var abc = [];	 
 	  
	  var data = snap.val();
      var keys = Object.keys(data);
      
	  
	  for (var i = 0; i < keys.length; i++)
	  {
         abc.push(keys[i]);
      }
	
	
	
	x=1;
      for (var i = 0; i < abc.length; i++)
	  {
    	
     
    	firebase.database().ref().child('/AdminDB/'+abc[i]).on('value',snap => {
		 
		 
	   var tap =  JSON.stringify(snap.val());
	 	
       var obj = JSON.parse(tap);
        
		var state = obj.State;
		
		if(state==s)
		{		
    	var row = table.insertRow(x);        
       
	   
        var c1 = row.insertCell(0);  
		var c2 = row.insertCell(1);  
		var c3 = row.insertCell(2);  
		var c4 = row.insertCell(3);  
		
	
        c1.innerHTML = obj.UserID;
	    c2.innerHTML = obj.Name;
        c3.innerHTML = obj.Department;
	    
		var n = obj.UserID.toString();
		
		var newA = document.createElement("INPUT");
        newA.setAttribute("type","button");
		newA.setAttribute("onclick","dele('"+n+"')");
        newA.setAttribute("value","Delete");
        
		c4.appendChild(newA);
         
		x=x+1;
		
		}
		
		
	 });	
	
	}
	 
	});
  
  var seen = {};
$('table tr').each(function() {
  var txt = $(this).text();
  if (seen[txt])
    $(this).remove();
  else
    seen[txt] = true;
});
}



function dele(id)
{
	 alert(id);
	 firebase.database().ref().child('/AdminDB/'+id).remove();
	 window.location.href = "Edit.html?"+k;
	 
	var seen = {};
$('table tr').each(function() {
  var txt = $(this).text();
  if (seen[txt])
    $(this).remove();
  else
    seen[txt] = true;
});
}



