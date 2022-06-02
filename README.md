# IT_Project_Generation_Z

This is a Webshop where products can be purchased. There are customer log in and administrator login with different functionalities. 

#### Contents
- [Analysis](#analysis)
  - [Scenario](#scenario)
  - [User Roles and Permissions](#User-Roles-and-Permissions) 
  - [User Stories](#User-stories)
  - [Use Cases](#use-cases)
- [Design](#design)
  - [Prototype Design](#prototype-design)
  - [Domain Design](#domain-design)
  - [Business Logic Design](#business-logic-design)
  - [Endpoint Design](#endpoint-design)
- [Implementation](#implementation)
  - [Backend Technology](#backend-technology)
  - [Frontend Technology](#frontend-technology)
- [Deployment](#deployment)
- [User Guide](#User-guide)
- [Project Management](#project-management)
  - [Roles](#roles)
  - [Milestones](#milestones)
  - [Maintainers](#maintainers)
  - [Licence](#licence)



## Analysis 

### Scenario
This IT project creates a webshop that allows users with predefined administrator rights to add, edit, or delete products and to customise their personal customer data.
Customers can create accounts, log in, change their personal information and order articles on the webshop. The corresponding shipping costs are calculated after adding specific prodcuts to their shopping cart. Our user-friendly design also provides users with a navigation bar for better navigation between the different web pages and search and sort functions in addition to a classic product description.

From an administrator's view, one of the most essential points of the webshop is keeping the data updated. Consequently, it is crucial to have the possibility to add more products, edit their information or delete them if they are no longer available. Furthermore, as these products can then later be purchased by customers, the shipping costs calculation must be correct.

Note: There are several functions that the webshop does not support yet, as in this IT project, we focused mainly on the MVP requirements and only started working on the further ones as soon as the basic requirements were met. Many additional requirements/functionalities can later be implemented in the further development of this web application. Additionally, it is important to mention that the focus of this semester's project was on the shipping cost calculation during the ordering process.


### User Roles and Permissions

#### Administrator
- Has the permission to add, edit and delete products.
- Has the permission to see all user profiles and order histories.

#### Customer
- Has the permission to change personal information.
- Has the permission to purchase prodcuts and see personal order history.

### User Stories
1. As a user, I want to have a webshop where I can search and sort products and also read products information and add them to my shopping card that I can purchase them at a wished time.
2. As a user, I want to edit my personal information like email address, password, name, shipping address, date of birth, and also see my order history.
3. As a user, I want to see a consistent visual appearance so that I can navigate easily with the web application. To navigate on the web application, I want to use a navigation bar to orientate myself on the web application and to access the desired part of the pages faster.
4. As an administrator, I want to have the permission to add new products, edit or delete them. 

### Use Cases

#### Use Cases Package 1 - Customer
- UC-101 [Register]: The system shall allow the user (customer) to create a new account.
- UC 102 [User Login]: The system shall allow the user to log in.
- UC 103 [Update Profile]: The system shall allow the user to change personal data like name, date of birth, shipping address, email address and a valid password.
 - UC 104 [Sort product]: The system shall allow the user to sort products based on price.
("Start with the lowest price" or "start with the highest price")
- UC 105 [Search product]: The system shall allow the user to search products by their name.
- UC 106 [Shopping cart]: The system shall provide the user with the ability to add products to the shopping cart or to remove them.
- UC 107 [Purchase product]: The system shall allow the user to purchase products.
- UC 108 [View order history]: The system shall allow the user to see previous orders.
- UC 109 [User logout]: The system shall allow the user to log out.
- UC 110 [Reset password]: The system shall allow the user to reset the password.

#### Use Cases Package 2 – Administrator
- UC 201 [Administrator login]: The system shall allow the administration to log in.
- UC 202 [Add products]: The system shall allow the administrator to add new products to the web shop.
- UC 203 [Edit product information]: The system shall allow the administrator to change product prices and product descriptions.
- UC 204 [Delete product]: The system shall allow the administrator to delete products.
- UC 205 [Administrator logout]: The system shall allow the administration to log out.

## Design

### Prototype Design
A bootstrap based static prototype has been created by using a prototyping application.
In this case, the prototype application Bootstrap Studio has been used to create a basic user interface design based on an HTML grid, Bootstrap CSS and JavaScript, including the selection of web fonts and font-based icons.
The assets (HTML, CSS, JavaScript, image and font files) has been exported and will be extended in the later during implementation with jQuery and Vue.js to build a dynamic website.

### Domain Design

### Business Logic Design

### Endpoint Design

## Implementation

### Backend Technology
This Web application is relying on…..

### Frontend Technology
This Web application is relying on the following frontend technology/libraries:
- Bootstrap

## Deployment
blablabla..

## User guide

## Project management

### Roles
Andrea Simonek: Setting up GitHub Repository, writing the README.md file, coding issues related to the “user profiles” and the “ordering process”
Kevin Pini: Coding issues about the “user profiles” and the “ordering process”
Moana Kleiner and Carla Kaufmann: coding issues related to the products and the database.

### Milestones
1. **Analysis**: Scenario ideation, use case analysis and user story writing.
2. **Prototype Design**: Creation of Bootstrap static web-design prototype.
3. **Domain Design**: Definition of domain model.
4. **Business Logic and API Design**: Definition of business logic and API.
5. **Data and API Implementation**: Implementation of data access and business logic layers, and API.
6. **Security and Frontend Implementation**: Integration of security framework and frontend realisation.
7. **Deployment**: Deployment of Web application on cloud infrastructure.

#### Maintainers
- Andrea Alec Simonek
- Kevin Pini
- Carla Kaufmann
- Moana Kleiner

#### Licence
