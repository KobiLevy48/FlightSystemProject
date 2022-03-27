create table Administrators (
	Administrator_Id     SERIAL primary key,
	First_Name 			TEXT NOT NULL,
	Last_Name  			TEXT NOT NULL,
	User_Id    			BIGINT UNIQUE NOT NULL,
	FOREIGN KEY(User_Id) 
	REFERENCES Users(User_Id)
);
////////////////////////////////////////////////////////////

create table Airline_Companies (
	Airline_Company_Id    BIGSERIAL primary key,
	Airline_Company_Name  TEXT UNIQUE NOT NULL,
	Country_Id 				INT NOT NULL,
	User_Id                 BIGINT UNIQUE NOT NULL,
	FOREIGN KEY(Country_Id) 
	REFERENCES Countries(Country_Id),
	FOREIGN KEY(User_Id) 
	REFERENCES Users(User_Id)
);
//////////////////////////////////////////////////////////

create table Countries (
	Country_Id      SERIAL primary key,
	Country_Name  	TEXT UNIQUE NOT NULL	
);
//////////////////////////////////////////////////////////

create table Customers (
	Customer_Id         BIGSERIAL primary key,
	First_Name 			TEXT NOT NULL,
	Last_Name  			TEXT NOT NULL,
	Address 			TEXT NOT NULL,
	Phone_No  			TEXT UNIQUE NOT NULL,
	Credit_Card_No      TEXT UNIQUE NOT NULL,
	User_Id    			BIGINT UNIQUE NOT NULL,
	FOREIGN KEY(User_Id) 
	REFERENCES Users(User_Id)
);

//////////////////////////////////////////////////////////

create table Flights (
	Flight_Id              BIGSERIAL primary key,
	Airline_Company_Id     BIGINT NOT NULL,
	Origin_Country_Id      INT NOT NULL,      
	Destination_Country_Id INT NOT NULL,
	Departure_Time 		   TIMESTAMP NOT NULL,
	Landing_Time           TIMESTAMP NOT NULL,
	Remaining_Tickets      INT,
	FOREIGN KEY(Airline_Company_Id) 
	REFERENCES Airline_Companies(Airline_Company_Id),
	FOREIGN KEY(Origin_Country_Id) 
	REFERENCES Countries(Country_Id),
	FOREIGN KEY(Destination_Country_Id) 
	REFERENCES Countries(Country_Id)
);
//////////////////////////////////////////////////////////

create table Tickets (
	Ticket_Id     BIGSERIAL primary key,
	Flight_Id 	  BIGINT NOT NULL,
	Customer_Id   BIGINT NOT NULL,
	FOREIGN KEY(Flight_Id) 
	REFERENCES Flights(Flight_Id),
	FOREIGN KEY(Customer_Id) 
	REFERENCES Customers(Customer_Id),
	unique(Flight_Id,Customer_Id)
);

//////////////////////////////////////////////////////////

create table User_Roles (
	User_Role_Id      SERIAL primary key,
	Role_Name  	TEXT UNIQUE 	
);

//////////////////////////////////////////////////////////

create table Users (
	User_Id        BIGSERIAL primary key,
	Username  	   TEXT UNIQUE NOT NULL,
	User_Password  TEXT NOT NULL,
	Email          TEXT UNIQUE NOT NULL,
	User_Role      INT NOT NULL,
	FOREIGN KEY(User_Role) 
	REFERENCES User_Roles(User_Role_Id)
);