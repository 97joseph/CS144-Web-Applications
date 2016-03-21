
Question 1:
For which communication(s) do you use the SSL encryption?
(4)->(5) : As the user inputs sensitive credit card number, which should be encrypted and secured.
(5)->(6) : As the sensitive credit card number is being returned from the Tomcat server, information must be encrypted. So that it is not hacked by malicious users.

Question 2:
How do you ensure that the item was purchased exactly at the Buy_Price of that particular item?
Using HTTPSession. 
At the first instance when the user searches for an item, item's information comprising of itemid, itemname and buy price are stored in a session in getItem.jsp.
This stored information is further carried on to the transaction and confirmation page,leaving no gaps for user to alter the buy price. 
This also allows us to satisfy the constraint of minimizing number of request being sent to the Oak server.