<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 


<html>
<head> 
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" /> 
<style type="text/css"> 
  html { height: 100%} 
  body { height: 100%; margin: 0px; padding: 0px } 
  #map_canvas { height: 100%} 
  
 
table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    text-align: left;
    padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

  
  
  
</style> 
<script type="text/javascript" 
    src="http://maps.google.com/maps/api/js?sensor=false"> 
</script> 
<script type="text/javascript"> 
	var map;
	var geocoder;
  function initialize() { 
   // var latlng = new google.maps.LatLng(34.063509,-118.44541); 
    var myOptions = { 
      zoom: 8, // default is 8  
      //center: latlng, 
      mapTypeId: google.maps.MapTypeId.ROADMAP 
    }; 
    map = new google.maps.Map(document.getElementById("map_canvas"), 
        myOptions); 
        address();
  } 

  function address(){
  	var x,y;
  	//if(<%=request.getAttribute("ItemLocation")%>==null)return;
	var loc = '<%=request.getAttribute("ItemLocation")%>';
	
  	 geocoder = new google.maps.Geocoder();  
  	  geocoder.geocode({ 'address': loc }, function (results, status) { 
  	  	 if (status == google.maps.GeocoderStatus.OK) {
	                         var marker = new google.maps.Marker({
	                            position: results[0].geometry.location,
	                            map: map,
	                            title: loc
	                        }); 
	                        
	google.maps.event.addListener(marker, 'click', function() {
	                            new google.maps.InfoWindow({
	                                content: '<p>This is the location of postcode <b>' + loc + '.</b></p>'
	                            }).open(map, marker);
	                        });
	                        
	                         map.setCenter(results[0].geometry.location);
	                         //map.panTo(results[0].geometry.location);
	                    } else {
	                        map.setCenter(new google.maps.LatLng(34.063509,-118.44541) );
	                        map.setZoom(1);
	                       // alert('Postcode not found');
	                    }
	                    
	                });          
  }

</script> 
</head> 

<body onload="initialize()"> 
<body>
    	<h1>Item Search Page</h1>
    	
    	<form action="item" method="get">
    		Enter Id: <input autocomplete="off" type="text" name="id" /> <br />
    		<input type="submit" />
    	</form>
    	
  </body>
<c:if test ="${flagitem}">
  <div id="map_canvas" style="width:50%; height:50%"></div> 



<h1>Item Details<h1>
<table style="width:50%" border="6">
	<tr>
	<td>Item Id</td>
	<td><%= request.getAttribute("id") %></td>
	</tr>
	<tr>
	<td>Name of Item:</td>
	<td><%= request.getAttribute("Name") %></td>
	</tr>
	<tr>
	<td>Current Rate:</td>
	<td><%= request.getAttribute("Currently") %></td>
	</tr>
	<tr>
	<td>Number_of_bids:</td>
	<td><%= request.getAttribute("Number_of_Bids")%></td>
	</tr>
	<tr>
	<td>Seller_UID:</td>
	<td><%=request.getAttribute("Seller_UID")%></td>
	</tr>
	<tr>
	<td>Seller_Rating:</td>
	<td><%=request.getAttribute("Seller_Rating")%></td>
	</tr>
	<tr>
	<td>Buy_Price:</td>
	<td><%=request.getAttribute("Buy_Price")%></td>
	</tr>
	<tr>
	<td>Description:</td>
	<td><%=request.getAttribute("Description")%></td>
	</tr>
	<tr>
	<td>Started:</td>
	<td><%=request.getAttribute("Started")%></td>
	</tr>
	<tr>
	<td>First Bid:</td>
	<td><%=request.getAttribute("First_Bid")%></td>
	</tr>
	<tr>
	<td>Ends:</td>
	<td><%=request.getAttribute("Ends")%></td>
	</tr>
	<tr>
	<td>Categories</td>
	<td><%=request.getAttribute("categories")%></td>
	</tr>
	<tr>
	
	<td>Item Location:</td>
	<td><%=request.getAttribute("ItemLocation")%></td>
	
	</tr>

	<tr>
	
	<td>Item Latitude:</td>
	<td><%=request.getAttribute("ItemLatitude")%></td>
	
	</tr>
	<tr>
	
	<td>Item Country:</td>
	<td><%=request.getAttribute("Country")%></td>
	
	</tr>
	<tr>
	
	<td>Item Longitude:</td>
	<td><%=request.getAttribute("ItemLongitude")%></td>
	
	</tr>
	
	
</table>

<c:if test="${fn:length(ratings) > 0}">

<h4> Bid details</h4>

<table border = "1" style="width:60%">

<tr>
<th>Bidder ID</th>
<th>Bidder Rating</th>
<th>Location</th>
<th>Country</th>
<th>Time</th>
<th>Amount</th>
</tr>
<c:forEach begin="0" end="${fn:length(ratings)-1}" var="index">
<tr> 
<td><c:out value="${ids[index]}"/></td>
<td><c:out value="${ratings[index]}"/></td>
<td><c:out value="${locations[index]}"/></td>
<td><c:out value="${countries[index]}"/></td>
<td><c:out value="${times[index]}"/></td>
<td><c:out value="${amounts[index]}"/></td>
</tr>
</c:forEach>
</table>
</c:if>


<% String buyPrice = (String)request.getAttribute("Buy_Price");
          if (!buyPrice.equals("")) out.print("<a href=\"/eBay/transaction\">Wanna Buy This Item?</a>"); %>

  
</c:if> 
<c:if test = "${!flagitem}">
<p>No such item exists. Please enter valid Item Id</P>
</c:if>
</body>
</html>