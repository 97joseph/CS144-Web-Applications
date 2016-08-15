<html>
<head>
    <title>ResultSite</title>
    <script type="text/javascript" src="suggestiondata.js"></script>
    <script type="text/javascript" src="autosuggest.js"></script>
    <link rel="stylesheet" type="text/css" href="autosuggest.css" />
    <script type="text/javascript">
      window.onload=function(){
        var oTextbox = new AutoSuggestControl(document.getElementById("q"), new suggestionsProvider());
   };
    </script>
     <style type="text/css">
      body {
            text-align: center;
        }

    TABLE {
    border-collapse: collapse;
    width: 100%;
}

TH, TD {
    text-align: left;
    padding: 8px;
}

TR:nth-child(even){background-color: #f2f2f2}
    </style>
	

	
</head>
<body>
<%@ page import="java.util.*" %>

    <form name="input" action="search" method="get">

    Search keyword: <input type="text" autocomplete="off" name="q" id="q"/>
    <INPUT TYPE="hidden" NAME="numResultsToSkip" VALUE="0">
    <INPUT TYPE="hidden" NAME="numResultsToReturn" VALUE="15">
    
    <input type="submit" value="Submit" />
    
    </form> 

    Search results for keyword --> 
    <%= request.getAttribute("q") %> 
    
    <% if( (Integer)request.getAttribute("srLength")!=0 && !request.getAttribute("q").equals("")){%>
    
    ( <%= (Integer)request.getAttribute("numResultsToSkip") %> -  <%= (Integer)request.getAttribute("numResultsToSkip") +15 %> )
    
    
    <% 
    int test =40;
    int length = (Integer)request.getAttribute("srLength");
    %>

    <TABLE BORDER=2>
    <TR><TD>ItemID</TD><TD>ItemName<TD>
    <%
        for(int i=0; i< length; i++ ) {
         %>
            <TR>
            <TD><%= request.getAttribute("itemId"+i) %></TD>
            <TD><%= request.getAttribute("itemName"+i) %></TD>
            <TD>
                <form name="input" autocomplete="off" action="item" method="get">
    
                <input type="hidden" name="id" VALUE="<%= request.getAttribute("itemId"+i) %>"/></br>
                <input type="submit" value="View details of this item" />
                </form>
            </TD>
            </TR>
        <%
        }
        %>
     </TABLE>
     
     <TABLE > <TR><TD>
     <% if( ((Integer)request.getAttribute("numResultsToSkip") )>0 ) { %>
     <form name="input" action="search" method="get">

        <input type="hidden" name="q" VALUE="<%= request.getAttribute("q") %>"/>
        <INPUT TYPE="hidden" NAME="numResultsToSkip" VALUE="<%=    (Integer)request.getAttribute("numResultsToSkip") -15 %>">
        <INPUT TYPE="hidden" NAME="numResultsToReturn" VALUE="15">
        
        
        <button type="submit" name="Prev" value="Prev" >Prev</button>
        
        
    
    <% } else {%>
    <form name="input" action="search" method="get">
         <input type="hidden" name="q" VALUE="<%= request.getAttribute("q") %>"/>
        <INPUT TYPE="hidden" NAME="numResultsToSkip" VALUE="<%=    (Integer)request.getAttribute("numResultsToSkip")%>">
        <INPUT TYPE="hidden" NAME="numResultsToReturn" VALUE="15">
        
        <button name="Prev" value="">Prev</button>
    <% } %>
    </form>
    </TD>
    <TD>
     <% if( 15 == (Integer)request.getAttribute("srLength") && length>0 ) { %>
     <form name="input" autocomplete="off" action="search" method="get">

        <input type="hidden" name="q" VALUE="<%= request.getAttribute("q") %>"/>
        <INPUT TYPE="hidden" NAME="numResultsToSkip" VALUE="<%=    (Integer)request.getAttribute("numResultsToSkip") +15 %>">
        <INPUT TYPE="hidden" NAME="numResultsToReturn" VALUE="15">
        <button type="submit" name="Next" value="Next">Next</button>
        
        
    
    <% } else {%>
    <form name="input" action="search" method="get">
         <input type="hidden" name="q" VALUE="<%= request.getAttribute("q") %>"/>
        <INPUT TYPE="hidden" NAME="numResultsToSkip" VALUE="<%=    (Integer)request.getAttribute("numResultsToSkip")%>">
        <INPUT TYPE="hidden" NAME="numResultsToReturn" VALUE="15">
        
        <button name="Next" value="">Next</button>
    <% } %>
    </form>
    </TD>


    </TR></TABLE>
    
     <% } else{%>
     
     <br /><b>There is no such Item</b>
     
     <% } %>
</body>
</html>