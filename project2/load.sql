USE CS144;
LOAD DATA LOCAL INFILE 'Sellersort.dat' INTO Table Seller Fields Terminated by '|*|';
LOAD DATA LOCAL INFILE 'Buyersort.dat' INTO Table Bidder Fields Terminated by '|*|';
LOAD DATA LOCAL INFILE 'Itemsort.dat' INTO Table Item Fields Terminated by '|*|';
LOAD DATA LOCAL INFILE 'Categorysort.dat' INTO Table Category Fields Terminated by '|*|';
LOAD DATA LOCAL INFILE 'Bidssort.dat' INTO Table Bids Fields Terminated by '|*|';