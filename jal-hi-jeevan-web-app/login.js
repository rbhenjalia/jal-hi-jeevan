
	var config = {
	
	apiKey: "AIzaSyCBiGZ2FM8q1a4-nRLf-RYpnWrj1o4iNfM",
    authDomain: "trap-cecfc.firebaseapp.com",
    databaseURL: "https://trap-cecfc.firebaseio.com",
    projectId: "trap-cecfc",
    storageBucket: "trap-cecfc.appspot.com",
    messagingSenderId: "702600703488"
	
  };
	 

	 firebase.initializeApp(config);

function verifyLogin()	
{ 
	 var check;
	var a=5;
	var id = document.getElementById('userid').value;
	var pass = document.getElementById('pass').value;
	 
	
	firebase.database().ref('/AdminDB/'+id).on('value',snap => 
	{
	  
	 var tap =  JSON.stringify(snap.val());
	var obj = JSON.parse(tap);
       
	if(obj==null)
	{		
	   alert("Wrong Credentials");	
	}
	else
	{
	   var usid = obj.UserID;
       var pwd = obj.Password;
       var name = obj.Name;
       var state = obj.State;
	   var dept = obj.Department;
	   var cen = "Central";
	 
	
	   if(usid==id)
	   {
            	
				if(pass==pwd)
				{	
		          
				  if(dept==cen)
				  {	  
                     var queryString = "?"+name+"&"+usid+"&"+state; 
   
                     window.location.href = "CA/All.html"+queryString ;
				  }
				  
				  else
				  {
					  var queryString = "?"+name+"&"+usid+"&"+state+"&"+dept; 
   
                     window.location.href = "CU/All.html"+queryString ;
					  
				  }
				
				}
	   
				else
				{
		           
	                alert("Wrong Password");
				}
		   
	   }
	   
	   else
	   {
		   
	         alert("Wrong UserID");
	   }
		
	}
	});
	
		
}	 
	