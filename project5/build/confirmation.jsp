<% String URL = "http://" + request.getServerName()+":1448"+request.getContextPath()+"/search"; %>

<html>
	<title>Confirmation</title>
    <body>
    <h1>Payment and Confirmation<h1>
    <table style="width:50%" border="6">
    <tr>
    <td>Item Id: </td>
    <td><%= request.getAttribute("itemID") %></td>
    </tr>
    <tr>
    <td>Name :</td>
    <td><%= request.getAttribute("itemName") %></td>
    </tr>
     <tr>
    <td>Buy Price : </td>
    <td><%= request.getAttribute("buyPrice") %></td>
    </tr>
    <tr>
    <td>Credit Card No.: </td>
    <td><%= request.getAttribute("creditCardNum") %></td>
    </tr>
    <tr>
    <td>Date &amp; Time: </td>
    <td><%= request.getAttribute("dateTime") %></td>
    </tr>
    </table>

    


    </body>
</html>