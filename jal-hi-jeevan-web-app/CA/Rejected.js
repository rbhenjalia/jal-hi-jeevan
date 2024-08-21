
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

	
	
    document.getElementById('all').href = a;
    document.getElementById('pend').href = e;
	document.getElementById('rej').href = z;
	document.getElementById('adho').href = t;
	  

function adds()
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

	
	
    document.getElementById('all').href = a;
    document.getElementById('pend').href = e;
	document.getElementById('rej').href = z;
	document.getElementById('adho').href = t;
	
	var table =  document.getElementById("tables");
	
 
   
	firebase.database().ref('/ComplaintDB').on('value', function(snapshot) 
	{
      var abc = [];	 
 	  
	  var data = snapshot.val();
      var keys = Object.keys(data);
      
	  for (var i = 0; i < keys.length; i++)
	  {
         abc.push(keys[i]);
      }
	  
	
	x=1;
      for (var i = 0; i < abc.length; i++)
	  {
    
     
    	firebase.database().ref().child('/ComplaintDB/'+abc[i]).on('value',snap => {
		 
		 
	   var tap =  JSON.stringify(snap.val());
	 	
       var obj = JSON.parse(tap);
        
	   var w = obj.WaterBodyType;
       var state = obj.State;
	   var cx = obj.Category;
       var sts = obj.Status;
       var ser = "Rejected";
      
	
	      if(state==s)
		  {
			if(sts==ser)
	     	  {
				  
			  
    	var row = table.insertRow(x);        
       
	   
        var c1 = row.insertCell(0);  
		var c2 = row.insertCell(1);  
		var c3 = row.insertCell(2);  
		var c4 = row.insertCell(3);  
		var c5 = row.insertCell(4);  
	    var c6 = row.insertCell(5);  
	
	
        c1.innerHTML = obj.Timestamp;
	    c2.innerHTML = obj.ComplaintID;
        c3.innerHTML = obj.City;
	    c4.innerHTML = obj.Status;
		c5.innerHTML = obj.SupportCount;
	    
		var i = obj.ComplaintID;
		
		var t = j+"&"+i;
		 
		var newA = document.createElement('a');
        newA.setAttribute('href',"Profile.html?"+t);
        newA.innerHTML = "Details";
  
		c6.appendChild(newA);
         
		x=x+1;
			   
		  }
		}
		
		
	 });	
	
	}
	

	 	
	});
	
		var seen = {};
$('#tablew  tr').each(function() {
  var txt = $(this).text();
  if (seen[txt])
    $(this).remove();
  else
    seen[txt] = true;
   });
	
		window.setTimeout(function(){

               sortTableCount();

        }, 3000);
}


function sortTableCount() 
{
  var table, rows, switching, i, x, y, shouldSwitch;
  table = document.getElementById("tables");
  switching = true;
  /*Make a loop that will continue until
  no switching has been done:*/
  while (switching) {
    //start by saying: no switching is done:
    switching = false;
    rows = table.getElementsByTagName("tr");
    /*Loop through all table rows (except the
    first, which contains table headers):*/
    for (i = 1; i < (rows.length - 1); i++) {
      //start by saying there should be no switching:
      shouldSwitch = false;
      /*Get the two elements you want to compare,
      one from current row and one from the next:*/
      x = rows[i].getElementsByTagName("td")[4];
      y = rows[i + 1].getElementsByTagName("td")[4];
      //check if the two rows should switch place:
      if (parseInt(x.innerHTML.toString()) < parseInt(y.innerHTML.toString())) {
        //if so, mark as a switch and break the loop:
        shouldSwitch= true;
        break;
      }
    }
    if (shouldSwitch) {
      /*If a switch has been marked, make the switch
      and mark that a switch has been done:*/
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
    }
  }
}
