Use CS144;
create Table Seller(Sellerid varchar(100),Rating int,Primary key(Sellerid));
create Table Bidder(Rating INT,Bidderid varchar(100),Location varchar(100),Country varchar(200),Primary key(Bidderid));
create Table Item(Itemid INT, Name Varchar(200) Not null,Description Varchar(4000) Not null,Currently Decimal(8,2) Not null,Buy_price Decimal(8,2) Not null,First_bid Decimal(8,2),No_of_bids INT,Location varchar(100) Not null,Country varchar(200) Not Null,Latitude varchar(50),Longitude varchar(50),Started TIMESTAMP not null,Sellerid varchar(100),Ends TIMESTAMP not null, Primary key(Itemid), Foreign key (Sellerid) references Seller(Sellerid));
create table Category(Itemid INT,Category varchar(100),Foreign key(Itemid) references Item(Itemid),Primary Key(Itemid,Category));
create table Bids(Bidderid varchar(100),Itemid INT,Time TIMESTAMP,Amount Decimal(8,2),Primary Key(Bidderid,Itemid,Time),Foreign key (Bidderid) references Bidder(Bidderid),Foreign key (Itemid) references Item(Itemid));
