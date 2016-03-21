Part B: Design your relational schema
Now that you understand the data you'll be working with, design a good relational schema for it. Iterating through the following three steps should help you in the design process.
1.	List your relations. Please specify all keys that hold on each relation. You need not specify attribute types at this stage.

Item(itemid primary key, name not null, description not null, currently not null, buyprice ,firstbid not null ,noofbids not null ,location not null, country not null,latitude ,longitude,started not null, ends not null, sellerid foreign key references Seller (sellerid))
Category(itemid foreign key references Item(itemid) ,category,Primary Key(itemid,category))
Bids(bidderid foreign key references Bidder(bidderid), itemid foreign key references Item(itemid),time, amount, PrimaryKey(bidderid,itemid,time))
Bidder(bidderid primarykey, rating, location,country)
Seller(sellerid primary key, rating)

2.	List all completely nontrivial functional dependencies that hold on each relation, excluding those that effectively specify keys.
Trivial functional dependencies does not exist.
Each item has many categories and each item has its own features.
Thus I created two seperate tables, Itemid->category Primary key (Itemid, category)
Itemid->name,description,currently buyprice,fistbid,noofbids,location,country,latitude,longitude,started,ends,sellerid
bidderid,itemid,time ->amount
bidderid-> rating,location, country
sellerid-> rating



3.	Are all of your relations in Boyce-Codd Normal Form (BCNF)? If not, either redesign them and start over, or explain why you feel it is advantageous to use non-BCNF relations.
All the relations are in BCNF.
This schema is in BCNF because all the functional dependencies for each table contains a key in the left hand side. Thus each value on left hand side is associated with precisely one value on right hand side.

4.	Are all of your relations in Fourth Normal Form (4NF)? If not, either redesign them and start over, or explain why you feel it is advantageous to use non-4NF relations.
No multivalued dependency exists in my tables. Hence all the tables are in 4NF.




2) The only functional dependencies in each relation are for keys.

3) This schema should be in BCNF because all the functional dependencies for each table contains a key in the left hand side.
