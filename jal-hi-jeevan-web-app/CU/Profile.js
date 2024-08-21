var config = {
            	  apiKey: "AIzaSyCBiGZ2FM8q1a4-nRLf-RYpnWrj1o4iNfM",
                  authDomain: "trap-cecfc.firebaseapp.com",
                  databaseURL: "https://trap-cecfc.firebaseio.com",
                  projectId: "trap-cecfc",
                  storageBucket: "trap-cecfc.appspot.com",
                  messagingSenderId: "702600703488"
	         };
	 

	 firebase.initializeApp(config);

function printtoPDF()
{

var prtContent = document.getElementById("HTMLtoPDF");
var WinPrint = window.open('', '', 'left=0,top=0,width=800,height=900,toolbar=0,scrollbars=0,status=0');
WinPrint.document.write(prtContent.innerHTML);
WinPrint.document.close();
WinPrint.focus();
WinPrint.print();
WinPrint.close();

}
	 
function add()
{	
  		var queryString = decodeURIComponent(window.location.search);
    queryString = queryString.substring(1);
    var keyValues = queryString.split('&'); 
     
    var n = keyValues[0].toString();
	var q = keyValues[1].toString();
	var s = keyValues[2].toString();
    var d = keyValues[3].toString();
    
	var j = n+"&"+q+"&"+s+"&"+d;
	var k= j.toString();
	
    var a = "All.html?"+k;
	var e = "Pending.html?"+k;
	var z = "Rejected.html?"+k;
	var t = "AdminHome.html?"+k;
    
	var f = keyValues[4].toString();
    
	
    document.getElementById('all').href = a;
    document.getElementById('pend').href = e;
	document.getElementById('rej').href = z;
	document.getElementById('adho').href = t;
	
	
	
	var img = f+".jpg"; 
	var imURL = f+"/";
	var image = imURL+img;
	var l = "complaints/"+image;
 
    var ComplaintID;
	var Date;
    var Category;
	var Address;
	var City;
	var Status;
	var Solution;
	var Description;
	

    var storageRef = firebase.storage().ref();
     var spaceRef = storageRef.child(l);
     
 	 storageRef.child(l).getDownloadURL().then(function(url) {
             var test = url;
             document.querySelector('img').src = test;

         }).catch(function(error) {

         });

   
	
	
	var user;
    var table =  document.getElementById("table");
	     
    firebase.database().ref().child('/ComplaintDB/'+f).on('value',snap => {
		  
	var tap =  JSON.stringify(snap.val());
	var obj = JSON.parse(tap);
    
   	
	
   	
	document.getElementById('UserID').innerText = obj.UserID;
		
	document.getElementById('ComplaintID').innerText = obj.ComplaintID;

    ComplaintID = "ComplaintID : "+obj.ComplaintID+"       ";	

	document.getElementById('ComplaintDate').innerText = obj.Timestamp;

	ComplaintDate = "Complaint Registration Date : "+obj.Timestamp+"       ";
	
	document.getElementById('Category').innerText = obj.Category;
	
	Category = "Complaint Category : "+obj.Category+"       ";
	
	document.getElementById('WaterBodyType').innerText = obj.WaterBodyType;

	document.getElementById('Address').innerText = obj.Address;

	Address = "Address : "+obj.Address+"       ";
	
	document.getElementById('City').innerText = obj.City;
	
	document.getElementById('MapLink').href = "abc.html?"+obj.Coordinates;
	
	document.getElementById('MapLink').setAttribute("target","_blank");
	
	document.getElementById('Pincode').innerText = obj.PinCode;
		
	document.getElementById('Description').innerText = obj.Description;
 
    document.getElementById('Status').innerText = obj.Status;	 
		
    document.getElementById('Suggestion').innerText = obj.Solution;
		 
	
	var FireString = ComplaintID+ComplaintDate+Category+Address;
	
	var subject = "Concerned ComplaintID : "+obj.ComplaintID;
	
    var gmail_str = "https://mail.google.com/mail/?view=cm&fs=1&to=someone@example.com&su="+subject+"&body="+FireString+"&bcc=someone.else@example.com";
	document.getElementById('gmail').setAttribute('href',gmail_str);
       
	   
 
    var yahoo_str = "https://mail.google.com/mail/?view=cm&fs=1&to=someone@example.com&su=SUBJECT&body=BODY&bcc=someone.else@example.com";
	document.getElementById('yahoo').setAttribute('href',yahoo_str);
       
	var outlook_str = "https://outlook.live.com//mail/?view=cm&fs=1&to=someone@example.com&su=SUBJECT&body=BODY&bcc=someone.else@example.com";
	document.getElementById('outlook').setAttribute('href',outlook_str);
       
	   

	
	
	firebase.database().ref().child('/UserDB/'+obj.UserID.toString()).on('value',snap => 
	{
		 
		 
		 var tap =  JSON.stringify(snap.val());
	 	 var obj = JSON.parse(tap);
   
	     

    document.getElementById('Username').innerText = obj.Name;
	
	document.getElementById('Email').innerText= obj.EMail;

	document.getElementById('Phone').innerText = obj.Phone;

	});
	
	});
		
    

}

function statusChangeToSolved()
{
   
	var queryString = decodeURIComponent(window.location.search);
    queryString = queryString.substring(1);
    var keyValues = queryString.split('&'); 
     
    var n = keyValues[0].toString();
	var q = keyValues[1].toString();
	var s = keyValues[2].toString();
    var d = keyValues[3].toString();
    
	var j = n+"&"+q+"&"+s+"&"+d;
	var k= j.toString();
	
    var a = "All.html?"+k;
	var e = "Pending.html?"+k;
	var z = "Rejected.html?"+k;
	var t = "AdminHome.html?"+k;
    
	var f = keyValues[4].toString();
    
	
	firebase.database().ref().child('/ComplaintDB/'+f).on('value',snap => 
	{
		 
		 
		 var tap =  JSON.stringify(snap.val());
	 	 var obj = JSON.parse(tap);
   
	     var ID = obj.ComplaintID;
		 
	firebase.database().ref().child('/UserDB/'+obj.UserID).on('value',snap => 
	{
		 
		 var tap =  JSON.stringify(snap.val());
	 	 var obj = JSON.parse(tap);
   
	     var phone = obj.Phone;	 
		 var message = "YOUR PROBLEM OF ID = "+ID+" has been Solved";
		 
		 alert(phone+" : "+message);
		 
		 var url = "http://ip.shreesms.net/smsserver/SMS10N.aspx?Userid=UVPGNU&UserPassword=uvp@gnu&PhoneNumber=91"+phone+"&Text="+message+"&GSM=UVPGNU";
		 
		 var win = window.open(url, '1366002941508','width=20,height=20,left=375,top=330');

         setTimeout(function () { win.close();}, 2000);

 

    });

	});
	
	
	var r = f+"/Status"
	var updates = {};
    updates["/ComplaintDB/"+r] = "Solved";
    firebase.database().ref().update(updates);
    window.alert("Status Changed : "+r);	
	
	
	 	window.setTimeout(function(){

               window.location.href = "All.html?"+k;

        }, 3000);
}
		
function statusChangeToAccept()
{var queryString = decodeURIComponent(window.location.search);
    queryString = queryString.substring(1);
    var keyValues = queryString.split('&'); 
     
    var n = keyValues[0].toString();
	var q = keyValues[1].toString();
	var s = keyValues[2].toString();
    var d = keyValues[3].toString();
    
	var j = n+"&"+q+"&"+s+"&"+d;
	var k= j.toString();
	
    var a = "All.html?"+k;
	var e = "Pending.html?"+k;
	var z = "Rejected.html?"+k;
	var t = "AdminHome.html?"+k;
    
	var f = keyValues[4].toString();
    
	
	firebase.database().ref().child('/ComplaintDB/'+f).on('value',snap => 
	{
		 
		 
		 var tap =  JSON.stringify(snap.val());
	 	 var obj = JSON.parse(tap);
   
	     var ID = obj.ComplaintID;
		 
	firebase.database().ref().child('/UserDB/'+obj.UserID).on('value',snap => 
	{
		 
		 var tap =  JSON.stringify(snap.val());
	 	 var obj = JSON.parse(tap);
   
	     var phone = obj.Phone;	 
		 var message = "YOUR PROBLEM OF ID = "+ID+" has been Accepted";
		 
		 alert(phone+" : "+message);
		 
		 var url = "http://ip.shreesms.net/smsserver/SMS10N.aspx?Userid=UVPGNU&UserPassword=uvp@gnu&PhoneNumber=91"+phone+"&Text="+message+"&GSM=UVPGNU";
		 
		 var win = window.open(url, '1366002941508','width=20,height=20,left=375,top=330');

         setTimeout(function () { win.close();}, 2000);

 

    });

	});
	
	
	var r = f+"/Status"
	var updates = {};
    updates["/ComplaintDB/"+r] = "Accepted";
    firebase.database().ref().update(updates);
    window.alert("Status Changed : "+r);	
	
	
	 	window.setTimeout(function(){

               window.location.href = "All.html?"+k;

        }, 3000);
}


