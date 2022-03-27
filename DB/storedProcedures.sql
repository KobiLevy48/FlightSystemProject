create function get_airline_by_username(_username text)
returns TABLE("Airline_Company_Id" BIGINT,"Airline_Company_Name" TEXT,
			 "Country_Id" INT,"User_Id"  BIGINT) 
LANGUAGE plpgsql
AS 
$$
BEGIN
RETURN QUERY 
select Airline_Company_Id,Airline_Company_Name,Country_Id,Airline_Companies.User_Id   
from Airline_Companies
JOIN Users on Airline_Companies.User_Id = Users.User_Id																																												
where Username =_username;

END;
$$

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

create function get_customer_by_username(_username text)
returns TABLE("Customer_Id" BIGINT,"First_Name" TEXT,"Last_Name" TEXT,
			  "Address" TEXT,"Phone_No" TEXT,"Credit_Card_No" TEXT,"Customers.User_Id" BIGINT) 
LANGUAGE plpgsql
AS 
$$
BEGIN
RETURN QUERY 
select Customer_Id,First_Name,Last_Name,Address,Phone_No,Credit_Card_No,Customers.User_Id   
from Customers
JOIN Users on Customers.User_Id = Users.User_Id																																												
where Username =_username;

END;
$$

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


create function get_user_by_username(_username text)
returns TABLE("User_Id" BIGINT,"Username" TEXT,"User_Password" TEXT,
			  "Email" TEXT,"User_Role" INT) 
LANGUAGE plpgsql
AS 
$$
BEGIN
RETURN QUERY 
select User_Id,Username,User_Password,Email,User_Role   
from Users																																											
where Username =_username;

END;
$$

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

create function get_flights_by_parameters(_origin_country_id int, _destination_country_id int,_date date)
returns TABLE("Flight_Id" BIGINT,"Airline_Company_Id" BIGINT,"Origin_Country_Id" INT,"Destination_Country_Id" INT,
			  "Departure_Time" TIMESTAMP,"Landing_Time" TIMESTAMP,"Remaining_Tickets" INT) 
LANGUAGE plpgsql
AS 
$$
BEGIN
RETURN QUERY 
select Flight_Id,Airline_Company_Id,Origin_Country_Id,Destination_Country_Id,Departure_Time,Landing_Time,Remaining_Tickets  
from Flights																																											
where _origin_country_id = Origin_Country_Id AND _destination_country_id = Destination_Country_Id
AND DATE(Departure_Time) = _date;

END;
$$

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

create function get_flights_by_airline_id(_airline_id bigint)

returns TABLE("Flight_Id" BIGINT,"Airline_Company_Id" BIGINT,"Origin_Country_Id" INT,"Destination_Country_Id" INT,
			  "Departure_Time" TIMESTAMP,"Landing_Time" TIMESTAMP,"Remaining_Tickets" INT) 
LANGUAGE plpgsql
AS 
$$
BEGIN
RETURN QUERY 
select Flight_Id,Airline_Company_Id,Origin_Country_Id,Destination_Country_Id,Departure_Time,Landing_Time,Remaining_Tickets  
from Flights																																											
where Airline_Company_Id =_airline_id;

END;
$$
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

create function get_arrival_flights(_country_id int)
returns TABLE("Flight_Id" BIGINT,"Airline_Company_Id" BIGINT,"Origin_Country_Id" INT,"Destination_Country_Id" INT,
			  "Departure_Time" TIMESTAMP,"Landing_Time" TIMESTAMP,"Remaining_Tickets" INT) 
LANGUAGE plpgsql
AS 
$$
BEGIN
RETURN QUERY 
select Flight_Id,Airline_Company_Id,Origin_Country_Id,Destination_Country_Id,Departure_Time,Landing_Time,Remaining_Tickets  
from Flights																																											
where _country_id = Destination_Country_Id AND 
(Landing_Time BETWEEN
(select Now()::timestamp)
AND
(select Now()::timestamp+interval '12 hours'));

END;
$$

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
create function get_departure_flights(_country_id int)

returns TABLE("Flight_Id" BIGINT,"Airline_Company_Id" BIGINT,"Origin_Country_Id" INT,"Destination_Country_Id" INT,
			  "Departure_Time" TIMESTAMP,"Landing_Time" TIMESTAMP,"Remaining_Tickets" INT) 
LANGUAGE plpgsql
AS 
$$
BEGIN
RETURN QUERY 
select Flight_Id,Airline_Company_Id,Origin_Country_Id,Destination_Country_Id,Departure_Time,Landing_Time,Remaining_Tickets  
from Flights																																											
where _country_id = Origin_Country_Id AND 
(Departure_Time BETWEEN
(select Now()::timestamp)
AND
(select Now()::timestamp+interval '12 hours'));

END;
$$

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
create function get_tickets_by_customer(_customer_id bigint)
returns TABLE("Ticket_Id" BIGINT,"Flight_Id" BIGINT,"Customer_Id" BIGINT) 
LANGUAGE plpgsql
AS 
$$
BEGIN
RETURN QUERY 
select * from Tickets 																																										
where _customer_id =Customer_Id;

END;
$$
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

create function get_administrator_by_username(_username text)
returns TABLE("Administrator_Id" INT,"First_Name" TEXT,
			 "Last_Name" TEXT,"User_Id"  BIGINT) 
LANGUAGE plpgsql
AS 
$$
BEGIN
RETURN QUERY 
select Administrator_Id,First_Name,Last_Name,Administrators.User_Id  from Administrators
JOIN Users on Administrators.User_Id = Users.User_Id																																												
where Username =_username;

END;
$$