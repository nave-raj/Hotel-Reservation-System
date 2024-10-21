# Naveena Kandasamy Rajkumar - Hotel Reservation System - README

# Project Summary

In this hotel management system, I have used several key entities: Guests, Hotels, Reservations, Rooms, and Staff. Guests are the individuals who stay at the hotel while Hotels represent the physical properties 
themselves with attribute hotel name. Reservations are records of bookings made by guests for specific rooms by getting the check-in and checkout dates, and Rooms represent the individual 
accommodations available within the hotel. Staff members handle tasks such as handling the rooms.

# Design
## User Roles and Functionalities

* The staff can create/edit/view/delete the rooms and can also see the logged in staff information along with the list of rooms.
* The Guest can book a room among the list of created rooms and can schedule the reservation for each booked room.
* The Guest can also View/Edit/Delete the reservation made. A Guest can have multiple reservations and multiple bookings of the room
* The Login Page can be used as a general login for all admin, staff and guest roles.
* The main signup page can be used for creating new guest users
* When Logged in as Admin user, the Admin can create a new staff and can assign to a hotel. The Admin can also see the list of guest users
* you can logout from each role with the logout button
  

# Requirements (Installation, Compile, Runtime, Database, etc) 

The requirements for the project are:
* Apache NetBeans Software is Connected with JAVA 8 and JAKARTA EE 8 
* MySQL Workbench with database ITMD4515 
* junit jupiter engine 
* apache maven plugin
* persistence
* bean Validation
* Payara Server.
   
# User Credentials
### Admin:
* Username - admin , password: admin
### Staff
* Username - staff1 , password: staff1
* Username - staff2 , password: staff2
### Guest
* Username - guest1 , password: guest1
* Username - guest2 , password: guest2

# Screen Captures

## Login Page
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/3a0b2c11-047a-4ec4-aa93-4b7dda4e032c">

### Guest SignUp Page
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/dab0b783-9573-49fe-8503-49cb3189737b">

### Admin Home Page
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/669cf2cb-e0eb-46bc-86f8-8abd47d31fa8">

### Admin able to view all the Staffs and Guests in the Database
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/16cd1356-db2a-46bb-9dd4-242e3eae9be8">

### Admin - create new Staff Page
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/826d739a-67de-4b01-9eff-e5646728b838">

### Admin - New Staff Created
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/81b06c7e-dd70-420d-b4e2-80fee7e2cf99">

### Staff - with username: Staff1 - Home Page
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/dbd06027-5ea4-41c1-bceb-c5b3d1582010">

### Staff - Ability to create a new room
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/df6d9ee2-47b1-47a1-84ad-d09db8120bd7">

### Staff - Ability to edit the created room
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/a1f548c4-377c-4131-87de-808a379bbd9d">
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/af13afd9-bb02-4018-8900-c031a5d6e7e3">

### Staff - Ability to delete the room - B12
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/a79d69f7-4a58-4235-a57c-6c753a65342a">

### Guest- Home Page with the ablity to create booking and Schedule Booking
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/ec0de7f0-08a8-4c6f-b64b-d412a359b67e">

### Guest - Book a new Room from the list of rooms
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/fd8ead4b-88a1-44bd-9846-93349d75066d">

### Guest - Booked a new room - Room Number: F31
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/631dd50e-aa5f-46cc-ac31-4cc8a18d61b4">

### Guest Schedule Reservation - Room Number: F31
* Schedule Reservation Page
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/46720d64-83bf-4301-a554-1d5cdff70848">
* The Scheduled Reservation for Room Number F31 with specific check in and check out dates
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/5023ba72-a3a7-46ea-bb59-8e7b04b3b96e">

### Guest Delete Reservation - Room Number: F31
* Delete Reservation Page
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/f12530bb-e44c-4c7d-8fd4-c31560cf144b">
* Reservation of Room with Number F31 got deleted
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/ffc62254-63c4-4a4d-bf97-ffb63676fc56">

### creating a new guest user - Naveena through signup page
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/86ed4617-ca9a-4f9b-8259-db23b6ceee69">
* Logged in as Guest user Naveena
<img width="1710" alt="image" src="https://github.com/itmd4515/itmd4515-s24-fp-nave-raj/assets/129906985/74d0e230-6882-4176-a235-4598ad446507">



# Development Insights

This course has been crucial in introducing me to the Java Enterprise Edition standard, covering key aspects like security, database persistence, and business and presentation components. Throughout the program, I've gained valuable knowledge about JPA annotations, JPA Query Language, authentication, authorization, and other relevant topics.Engaging in project work and reviewing concepts has greatly enhanced my learning experience. Looking ahead, I am eager to delve into Spring and Spring Boot. 



