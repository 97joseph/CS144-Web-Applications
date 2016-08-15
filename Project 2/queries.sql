Use CS144;

select count(*) from  (select sellerid,bidderid from Seller LEFT Join Bidder on Seller.sellerid= Bidder.bidderid UNION select sellerid,bidderid from Seller Right Join Bidder on Seller.sellerid=Bidder.bidderid) as s;

select count(*) from Item where BINARY Location='New York';


Select count(*)
from (select count(DISTINCT Category) As CategoryCount From Category Group by Itemid having CategoryCount=4) As ans;

select Bids.Itemid 
from Bids Inner JOIN Item on Bids.Itemid=Item.Itemid 
where Ends>'2001-12-20 00:00:01' and Amount=(SELECT MAX(Amount) from Bids);


select count(*) from Seller where Rating>1000;


select count(*) 
from Seller 
Inner JOIN Bidder
on Seller.sellerid=Bidder.Bidderid;


SELECT COUNT(DISTINCT Category)
FROM Bids 
INNER JOIN Category
ON Category.Itemid = Bids.Itemid
WHERE Bids.Amount > 100; 