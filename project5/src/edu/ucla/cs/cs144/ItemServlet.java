package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import edu.ucla.cs.cs144.AuctionSearchClient;
import org.apache.axis2.AxisFault;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import java.io.StringReader;


import java.io.*;
import java.text.*;
import java.util.*;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ErrorHandler;


public class ItemServlet extends HttpServlet implements Servlet
{
    public ItemServlet() {}

 static final String[] typeName = {
    "none",
    "Element",
    "Attr",
    "Text",
    "CDATA",
    "EntityRef",
    "Entity",
    "ProcInstr",
    "Comment",
    "Document",
    "DocType",
    "DocFragment",
    "Notation",
   };
				//boolean flagitem=true;
                boolean flag=false;
                String Name = "";
                String Currently = ""; 
                String Buy_Price = ""; 
                String First_Bid = ""; 
                String Number_of_Bids = "";
                String Started = "";
                String Seller_UID = "";
                String Seller_Rating = ""; 
                String Description = "";
       
 /**************************************************************/   
    public static String timestamp(String XMLdate)
   {
       SimpleDateFormat correctFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       SimpleDateFormat wrongFormat = new SimpleDateFormat("MMM-dd-yy HH:mm:ss");
       try
       {
           Date parsedDate = wrongFormat.parse(XMLdate);
           return correctFormat.format(parsedDate);
       } catch(ParseException p) {
           System.err.println("Error! Could not parse the time!");
           return "blah";
       }
   }
   /**************************************************************/
    static Element[] getElementsByTagNameNR(Element e, String tagName) {
       Vector< Element > elements = new Vector< Element >();
       Node child = e.getFirstChild();
       while (child != null) {
           if (child instanceof Element && child.getNodeName().equals(tagName))
           {
               elements.add( (Element)child );
           }
           child = child.getNextSibling();
       }
       Element[] result = new Element[elements.size()];
       elements.copyInto(result);
       return result;
   }
/**************************************************************/
   static Element getElementByTagNameNR(Element e, String tagName) {
       Node child = e.getFirstChild();
       while (child != null) {
           if (child instanceof Element && child.getNodeName().equals(tagName))
               return (Element) child;
           child = child.getNextSibling();
       }
       return null;
   }
 /**************************************************************/  
   static String getElementText(Element e) {
       if (e.getChildNodes().getLength() == 1) {
           Text elementText = (Text) e.getFirstChild();
           return elementText.getNodeValue();
       }
       else
           return "";
   }
   
   /* Returns the text (#PCDATA) associated with the first subelement X
    * of e with the given tagName. If no such X exists or X contains no
    * text, "" is returned. NR means Non-Recursive.
    */
    /**************************************************************/
   static String getElementTextByTagNameNR(Element e, String tagName) {
       Element elem = getElementByTagNameNR(e, tagName);
       if (elem != null)
           return getElementText(elem);
       else
           return "";
   }
/**************************************************************/
    static String strip(String money) {
       if (money.equals(""))
           return money;
       else {
           double am = 0.0;
           NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
           try { am = nf.parse(money).doubleValue(); }
           catch (ParseException e) {
               System.out.println("This method should work for all " +
                                  "money values you find in our data.");
               System.exit(20);
           }
           nf.setGroupingUsed(false);
           return nf.format(am).substring(1);
       }
   }


/**************************************************************/
   public static void parseItem(Element itemslist) throws IOException
       {
           String itemID = itemslist.getAttribute("ItemID");
           
           Element exactLocation = getElementByTagNameNR(itemslist, "Location");
           String latitutde = exactLocation.getAttribute("Latitude");
           String longitude = exactLocation.getAttribute("Longitude");
           String no_of_bids=getElementTextByTagNameNR(itemslist, ("Number_of_Bids"));
           String country = getElementTextByTagNameNR(itemslist, "Country");
           if(country == null)
           {
               country = "";
           }
           Element seller = getElementByTagNameNR(itemslist, "Seller");
           String sellerID = seller.getAttribute("UserID");
           String name = getElementTextByTagNameNR(itemslist, "Name");
           String buy_price = strip(getElementTextByTagNameNR(itemslist, "Buy_Price"));
           if(buy_price == null)
           {
               buy_price = "";
           }
           String description = getElementTextByTagNameNR(itemslist, "Description");
           if(description.length() > 4000)
           {
               description = description.substring(0, 3999);
           }
           String first_bid = strip(getElementTextByTagNameNR(itemslist, "First_Bid"));
           String started = timestamp(getElementTextByTagNameNR(itemslist, "Started")).toString();
           String ends = timestamp(getElementTextByTagNameNR(itemslist, "Ends")).toString();
           String currently = strip(getElementTextByTagNameNR(itemslist, "Currently"));
           String location = getElementTextByTagNameNR(itemslist, "Location");
           if(location == null)
           {
               location = "";
           }
          //System.out.println(itemID+"*M* "+name+"*M* "+description+"*M* "+currently+"*M* "+buy_price+"*M* "+first_bid+"*M* "+no_of_bids+"*M* "+location+"*M* "+country+"*M* "+latitutde+"*M* "+longitude+"*M* "+started+"*M* "+ends+"*M* "+sellerID);
           //load(itemsDoc,itemID,name,description,currently,buy_price,first_bid,no_of_bids,location,country,latitutde,longitude,started,sellerID,ends);
         
       }
       
       /**************************************************************/
       public static void  parseCategory(Element itemslist) throws IOException
       {
           String itemID = itemslist.getAttribute("ItemID");
           
           Element[] categories = getElementsByTagNameNR(itemslist, "Category");
           for(int i = 0; i < categories.length; i++)
           {
               String category = getElementText(categories[i]);
               
              // load(categoryDoc,itemID,category);
           }
       }
       /**************************************************************/
       private static int bidID = 0;
       public static void  parsebids(Element itemslist) throws IOException
       {
           
            
                /*bidID already handled*/
                String itemID = itemslist.getAttribute("ItemID");
                Element[] bidss = getElementsByTagNameNR(getElementByTagNameNR(itemslist, "Bids"), "Bid");
                for(int k = 0; k < bidss.length; k++)
                {
                    bidID++;
                    Element bidder = getElementByTagNameNR(bidss[k], "Bidder");
                    String bidderid = bidder.getAttribute("UserID");
                    String notconvertedTime = getElementTextByTagNameNR(bidss[k], "Time");
                    String time = timestamp(notconvertedTime).toString();
                    String amount = strip(getElementTextByTagNameNR(bidss[k], "Amount"));
                    
                    //load(bidsDoc,bidderid,itemID,time,amount);
                }
            
       }
       /**************************************************************/
       public static void  parseBuyer(Element itemslist) throws IOException
       {
           Element[] bids = getElementsByTagNameNR(getElementByTagNameNR(itemslist, "Bids"), "Bid");
           for(int j = 0; j < bids.length; j++)
           {
               Element bidder = getElementByTagNameNR(bids[j], "Bidder");
               String bidderID = bidder.getAttribute("UserID");
               String bidderRating = bidder.getAttribute("Rating");
               String bidderLocation = getElementTextByTagNameNR(bidder, "Location");
              // String bidderLocation = bidder.getAttribute("Location");
               if(bidderLocation == null)
               {
                   bidderLocation = "";
               }
               String bidderCountry = getElementTextByTagNameNR(bidder, "Country");
               //String bidderCountry = bidder.getAttribute("Country");
               if(bidderCountry == null)
               {
                   bidderCountry = "";
               }
              // load(buyerDoc,bidderRating,bidderID,bidderLocation,bidderCountry );
           }
           
       }
   
       /**************************************************************/
       public static void  parseSeller(Element itemslist) throws IOException
       {
       Element sellingPerson = getElementByTagNameNR(itemslist, "Seller");
       String sellerid = sellingPerson.getAttribute("UserID");
       /*String location = getElementText(getElementByTagNameNR(itemslist, "Location"));
       if(location == null)
       {
           location = "";
       }
       String country = getElementText(getElementByTagNameNR(itemslist, "Country"));
       if(country == null)
       {
           country = "";
       }*/
       String rating = sellingPerson.getAttribute("Rating");
       //load(sellerDoc,sellerid,rating);

       
           
       }
   
        /**************************************************************/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
               HttpSession session = request.getSession(true);
               AuctionSearchClient client = new AuctionSearchClient();
       
        // your codes here
        boolean flagitem = true;
        String m=request.getParameter("id");
        request.setAttribute("id",m);
        session.setAttribute("itemID", m);
        String xmlString=AuctionSearchClient.getXMLDataForItemId(m);
        request.setAttribute("xmlString",xmlString);
		if(xmlString==null || xmlString.length()<10)
			flagitem=false;
			//request.setAttribute("flagitem",flagitem);
        //request.setAttribute("output",xmlString);
       //flag=false;

        /*String queryId = request.getParameter("id");
        String xmlString = AuctionSearchClient.getXMLDataForItemId(queryId);*/
        else {
			request.setAttribute("output",xmlString);
       flag=false;
        try{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xmlString));
        Document doc = builder.parse(is);
        Element item = doc.getDocumentElement();
        request.setAttribute("Name", getElementTextByTagNameNR(item, "Name"));
         // update session
               // session.setAttribute("itemID", itemID);
                session.setAttribute("itemName", getElementTextByTagNameNR(item, "Name"));
               // session.setAttribute("buyPrice", buyPrice);

        String currently = (getElementTextByTagNameNR(item, "Currently"));
        if(currently == null)
           {
               currently = "";
           }
        request.setAttribute("Currently",currently);


            //String itemID = itemslist.getAttribute("ItemID");
           
           Element exactLocation = getElementByTagNameNR(item, "Location");
          
           String latitutde = exactLocation.getAttribute("Latitude");
           if(latitutde == null)
           {
               latitutde = "";
           }
           request.setAttribute("ItemLatitude",latitutde);
           String longitude = exactLocation.getAttribute("Longitude");
           if(longitude == null)
           {
               longitude = "";
           }
           request.setAttribute("ItemLongitude",longitude);
           String no_of_bids=getElementTextByTagNameNR(item, ("Number_of_Bids"));
           if(no_of_bids.equals("0"))
           {
              flag=true;
               
           }
           request.setAttribute("Number_of_Bids",no_of_bids);
           String country = getElementTextByTagNameNR(item, "Country");
           if(country == null)
           {
               country = "";
           }
           request.setAttribute("Country",country);
           Element sellingPerson = getElementByTagNameNR(item,"Seller");
            String sellerid = sellingPerson.getAttribute("UserID");
            if(sellerid == null)
           {
               sellerid = "";
           }
           request.setAttribute("Seller_UID",sellerid);
            String rating = sellingPerson.getAttribute("Rating");
            if(rating==null)
            rating="";
            request.setAttribute("Seller_Rating",rating);
           String name = getElementTextByTagNameNR(item, "Name");
           String buy_price = strip(getElementTextByTagNameNR(item,"Buy_Price"));
           if(buy_price == null)
           {
               buy_price = "";
           }
           request.setAttribute("Buy_Price",buy_price);
           session.setAttribute("buyPrice", buy_price);

           String description = getElementTextByTagNameNR(item, "Description");
           if(description.length() > 4000)
           {
               description = description.substring(0, 3999);
           }
           request.setAttribute("Description",description);
           String first_bid = strip(getElementTextByTagNameNR(item, "First_Bid"));
           if(first_bid==null)
           first_bid="";
           request.setAttribute("First_Bid",first_bid);
           String started = timestamp(getElementTextByTagNameNR(item, "Started")).toString();
           if(started==null)
           started="";
           request.setAttribute("Started",started);
           String ends = timestamp(getElementTextByTagNameNR(item, "Ends")).toString();
            if(ends==null)
            ends="";
            request.setAttribute("Ends",ends);
           String location = getElementTextByTagNameNR(item, "Location");
           if(location == null)
           {
               location = "";
           }
           request.setAttribute("ItemLocation",location);

           //parsing categories
           String categories = "";
                for (Element category : getElementsByTagNameNR(item, "Category")) {
                    categories = categories.concat(getElementText(category)+ ", ");}
                    String categories1=categories.substring(0,categories.length()-2);
                request.setAttribute("categories", categories1);

        //bids info
        if(flag==false) //no of bids not 0
        {Element[] bids = getElementsByTagNameNR( getElementByTagNameNR(item, "Bids"), "Bid");

                ArrayList times = new ArrayList();
                ArrayList amounts = new ArrayList();
                ArrayList ratings = new ArrayList();
                ArrayList ids = new ArrayList();
                ArrayList locations = new ArrayList();
                ArrayList countries = new ArrayList();
                for (Element bid : bids) {
                    times.add(getElementTextByTagNameNR(bid, "Time"));
                    amounts.add(getElementTextByTagNameNR(bid, "Amount"));

                    Element bidder = getElementByTagNameNR(bid, "Bidder");
                    locations.add(getElementTextByTagNameNR(bidder, 
                                                            "Location"));
                    countries.add(getElementTextByTagNameNR(bidder, 
                                                            "Country"));
                    ratings.add(bidder.getAttribute("Rating"));
                    ids.add(bidder.getAttribute("UserID"));

                }

                request.setAttribute("times", times);
                request.setAttribute("amounts", amounts);
                request.setAttribute("ratings", ratings);
                request.setAttribute("ids", ids);
                request.setAttribute("locations", locations);
                request.setAttribute("countries", countries);
                request.setAttribute("flag",flag);
         }
         else
                request.setAttribute("flag",flag);
          
        }
        catch (Exception e) {}
          //request.getRequestDispatcher("/getItem.jsp").forward(request, response);  
         
	
				 
		}
		request.setAttribute("flagitem",flagitem);
				request.getRequestDispatcher("/getItem.jsp").forward(request, response); 
	}
	}
	
           
              
    
