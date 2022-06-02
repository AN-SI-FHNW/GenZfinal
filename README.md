# IT_Project_Generation_Z

This project focuses on a web service that calculates the shipping costs of an order.

#### Contents
- [Analysis](#analysis)
  - [Scenario](#scenario)
  - [Shipping Cost Calculation](#shipping-cost-calculation)
  - [User Roles and Permissions](#user-roles-and-permissions)
  - [User Stories](#user-stories)
  - [Use Cases](#use-cases)
- [Minimal Requirements](#minimal-requirements)
  - [Architecture](#architecture)
  - [Functional Requirements](#functional-requirements)
  - [User Interface](#user-interface)
- [Prototype Design](#prototype-design)
- [Implementation](#implementation)
- [Deployment](#deployment)
- [Installation Guide](#installation-guide)
- [User Guide](#user-guide)
- [Features](#features)
- [Project Management](#project-management)
  - [Roles](#roles)
  - [Milestones](#milestones)
  - [Maintainers](#maintainers)
- [Further Development](#further-development)
- [Resources](#Resources) 


## Analysis 

### Scenario
The goals of this IT project is to create a webshop with different user roles (Administrator and User/Customer). Please note: The aim of the web shop is not that end users can order directly, but that users create customers and then place the orders. The users are the employees of the company, and the customers are the end users of the purchase product. The users act as a kind of intermediary. Therefore, administrators can create products and users can create customers and place the orders. 

The focus is not on the front-end but on the shipping cost calculation based on the the cusotmers location in Switzerlan and pallet sizes. The aim of the webshop is not that end users can order directly, but that users create customers and then place the orders. The users therefore act as a kind of intermediary.

Keeping the data updated is an essential point from an administrator's view. Consequently, the project enables them to add more products, edit their information or delete them if they are no longer available. Administrators also can change their login credentials and see all customers and order histories.

From a user's view, the classic functions like creating accounts, logging in, change their account information are enabled. Furthermore, they can create, edit or delete customers as they are the intermediaries. After adding products to the shopping cart, the shipping costs are calculated based on distance and pallet size.

The webshop has a navigation bar at the top for fast navigation between the different web pages. Furthermore, to increase the efficiency when working with the webshop, there is the possibility to search for products or customers by their name.

(Please note: There are several functions that the webshop does not support yet, as, in this IT project, we focused on the implementation of the MVP requirements and then started working on the further ones as soon as the basic requirements were met. Additionally, it is essential to mention that the focus of this semester's project was on the shipping cost calculation and not the front end and user design.)

### Shipping Cost Calculation
This project focuses on a web service that calculates shipping costs. The company sells products that cannot be sent by mail or package. The shipping costs are determined by the distance from the seller to the buyer and by the amount of space the ordered products need in the truck. 

The web service calculates the shipping costs of an order according to the following conditions: 
![image](https://user-images.githubusercontent.com/80820037/171218107-e436b6ab-2fdd-4127-ac9e-81866b504fec.png)

Interpretation
- Max 15 piece of product p3 can be shipped with 2.5 pallet spaces. If only 15 piece of product p3 is ordered an nothing else you need 3 pallet spaces because at the end of an order the pallet spaces are rounded up.
- Max 30 pieces of product p3 can be shipped with 5 pallet spaces.
- 1 piece of product p3 also needs 2.5 pallet spaces. 
- It’s possible to pile different products. An order of 12 piece of p2 and 5 piece of p1 needs 4 pallet spaces. 12 piece of p2 need at least 4 pallet spaces, 5 piece of p1 need at least 1.2 pallet spaces. therefore together they need at least 4 pallet spaces. 2 products of p2 need 0.4 pallet
spaces and 5 piece of p1 need 0.24 pallet spaces, togehter 0.64 pallet spaces.

### User Roles and Permissions

#### Administrator
- Has the permission to add, edit and delete products
- Has the permission to change Adminsitrator information
- Has the permission to see all user profiles and order histories

#### User
- Has the permission to change personal information
- Has the permission to add, edit, delete customers
- Has the permission to purchase prodcuts and see personal order history

### User Stories
1. As a user, I want to have a webshop where I can search for products, read product information and add them to my shopping cart to purchase them.
2. As a user, I want to edit my personal information like email address, password, name and see see my order history.
3. As a user, I want to see a consistent visual appearance so that I can navigate easily with the web application. To navigate on the web application, I want to use a navigation bar to access the desired pages faster.
4. As an administrator, I want to have the permission to add new products, edit or delete them.
5. As an administrator, I want to see all user profiles and order histories.

### Use Cases

#### Use Cases Package 1 - User
- UC-101 [Register]: The system shall allow the user to create a new account.
- UC 102 [User Login]: The system shall allow the user to log in.
- UC 103 [Update Profile]: The system shall allow the user to change personal data.
- UC 104 [Search product]: The system shall allow the user to search products by their name.
- UC 105 [Shopping cart]: The system shall provide the user with the ability to add products to the shopping cart or to remove them.
- UC 106 [Purchase product]: The system shall allow the user to purchase products.
- UC 107 [View order history]: The system shall allow the user to see previous orders.
- UC 108 [User logout]: The system shall allow the user to log out.

#### Use Cases Package 2 – Administrator
- UC 201 [Administrator login]: The system shall allow the administration to log in.
- UC 202 [Add products]: The system shall allow the administrator to add, edit or delete products.
- UC 203 [Delete product]: The system shall allow the administrator to delete products.
- UC 204 [View orders and customers]: The system shall provide the administrator to view all cusotmers and order histories.
- UC 205 [Administrator logout]: The system shall allow the administration to log out.

## Minimal Requirement

### Architecture
- Client-/Server implementation: Server implemented in Java and Client is browser-based (HTML / CSS / JavaScript)
- Server-side database stores relevant data (e.g. accounts, work-times, log in/log out times, etc.)
- Stateless communication (i.e., RESTful)

### Functional Requirements
- Web shop functionalities: Register, Login, Manage Userdata, Logout
- Products: at least 4 Products in the web shop, products have only the properties name, max products, min number of pallet places (no price, availability, weight)
- Shipping costs: only one warehouse location and only one provider of shipping services (one price plan). It has to work only for addresses in Switzerland
- Design and Usability: minimal design and usability is sufficient 

### User Interface
- Server: Does not have a user interface and does not write to the console (no “System.out.println”). BUT: The server does write log entries to a text file (e.g. “Joe logged in”, “Michelle logged out”, etc.)
- Client: The client runs in a standard browser and the interface is not totally ugly

## Prototype Design
A bootstrap based static prototype has been created by using a prototyping application "Bootstrap Studio", based on an HTML grid, Bootstrap CSS and JavaScript, including the selection of web fonts and font-based icons. 

## Implementation
The assets (HTML, CSS, JavaScript, image and font files) have been exported and will be extended in the later during implementation to build a dynamic website.

## Deployment
The User Client and the Web Server are represented as the project's front-end. The Webserver will host the website. The Application Server will consist of the business logic and the data access layer. A in-memory database is used to store data.

## Installation Guide
1.	Open GitHub link to our project: https://github.com/kevinpini/Generation_Z_IT_Project.git
2.	Click on “Code”, and copy the link
3.	Open IntelliJ IDEA and click on “file”, “new”, “project from version control”
4.	Paste the link into the “URL” space and click on “clone”
5.	After IntelliJ has opened the project, click on the “RUN” button  
6.	Open Google Chrome and enter “localhost:8080” into the search bar to get on the login page. 

## User Guide
The user guide is a quick explanation of how to use the web shop and it is separated into a user guide for administrators and one for the users. 

*Please note* The aim of the web shop is not that end users can order directly, but that users create customers and then place the orders. The users are the employees of the company, and the customers are the end users of the purchase product. The users act as a kind of intermediary. Therefore, administrators can create products and users can create customers and place the orders. 

Administrator Guide:

![image](https://user-images.githubusercontent.com/80820037/171643514-0a8d7e25-2f65-4727-887a-831b1a9a97f8.png)



User (Customer) Guide:

![image](https://user-images.githubusercontent.com/80820037/171643588-b7fd0798-84b1-4309-8886-127170c42c5f.png)



## Features
Administrator features:
1.	Login
2.	Create Product
3.	Save Product
4.	Search Product
5.	Edit Product
6.	Delete Product
7.	Logout
8.	Edit Profile
9.	Save Profile
10.	See user profiles (and corresponding order history)
11.	Delete user profiles (and corresponding order history)
12.	Logout

User (customer) features
1.	Register
2.	Login
3.	Create Customer
4.	Save Customer
5.	Search Customer
6.	Edit Customer
7.	Delete Customer
8.	Edit Profile
9.	Save Profile
10.	Add to Shopping Cart
11.	Delete from Shopping Cart
12.	View Orders
13.	Logout

## Project management

### Roles
Andrea Simonek & Kevin Pini: Setting up GitHub Repository, writing the README.md file, coding issues related to the “user profiles” and the “ordering process”
Moana Kleiner & Carla Kaufmann: coding issues related to the products and the database.
Please note: All group members stay in close contact during the whole project, and there will be regular exchanges. Therefore, it is conceivable that the division depicted in the table above will not be the same as in the result. However, the comments in the source code will definitively reveal which group member
developed which part of the code.

### Milestones
1. **Analysis**: Scenario ideation, use case analysis and user story writing.
2. **Prototype Design**: Creation of Bootstrap static web-design prototype.
3. **Domain Design**: Definition of domain model.
4. **Business Logic and API Design**: Definition of business logic and API.
5. **Data and API Implementation**: Implementation of data access and business logic layers, and API.
6. **Security and Frontend Implementation**: Integration of security framework and frontend realisation.

#### Maintainers
- Andrea Alec Simonek
- Kevin Pini
- Carla Kaufmann
- Moana Kleiner

## Further Development
User interface
- Usability: Client-side data validation to prevent user mistakes
- Appearance: Good styling (CSS), attractive interface
- Multilingual: Two or more languages supported in the UI
- Responsive design: also usable on mobile devices

More web shop functionalities
- Save orders / order history
- User defined prices, discounts
- Allow different addresses for an user (multiple shipping addresses, billing address)

Support for more complex shipping cost calculations
- more shipping service providers, with different price models (chose the cheapest one)
- some amounts of some products may be shipped by package. E.g. 1 or 2 piece of product p1 may be shipped by package. But 1 piece of product p2 may not be shipped by package.
- The weight of a product has an impact of the pallet spaces billed by the service provider. On one pallet space can be products of max 300 kg. otherwise an additional pallet space is billed for each additional 300 kg.

Administrator Login
- see all orders
- manage products
- manage users

## Resources
https://opendata.swiss/de/dataset/reisezeit-und-distanz-2017

