

General Description of the Library Database System 

The purpose of the system is to emulate a library that uses a database to store information about members, staff, and other forms of media.  For this system, four main entities will be considered: The librarians as administrators who manage the data of books, and the members. The media entity consists of the content and properties of all media. Furthermore, the member entity can request a viewing of the books and other information about the library. Finally, the library entity is the control system that keeps track of all the staff, media, and members. In the future, there will be some adjustments depending on the system we construct; hence, there will be some differences or some functions that we can add to our library to create an easily accessible and friendly environment for users. We will update our process along with those changes in the next reports if needed.

Usage of the System
The system described in this report is run via Jar File with Java versions 16 and 21, and includes all necessary dependencies in an Uber Jar format. The system makes use of the OJDBC Library to read and write to an Oracle 11g database system, alongside JavaFX and Apache POI to handle data and front-end interactions.

To date, we have compiled two different versions of the application, an Alpha version described in Assignment 9, which makes use of simple console interactions, and a more complete version described in Assignment 10, which makes use of a login system and graphical user interfaces. 

Using the Console Application

The Library System in Assignment 9 consists of a terminal based selection program, which creates interactions with the database depending on the option selected by the user.
 OpenVPN connections to the Toronto Metropolitan network are required to use the system properly, as the interactions with the database will not execute successfully otherwise.

Options A-C involve the manipulation of tables in the database; To use the tables, they first must be created and populated. If there is no data in the table when the queries are run, then there will be an empty result. 

Options from D-I correspond with queries in the database system, where each query performs a different action on the Database System. The output is formatted and printed to the console in each instance.

Inputting option J will exit the System and save the state of the database.


Using the Graphical Application
	The graphical user interface system described in Assignment 10 is a more completed version of the project, however not all functionality has been implemented due to time constraints in the project timeline. Future implementations of these functions are planned.

The graphical user interface currently consists of a login page, a query page, and a table modification area which allows the user to add new entries to the database system. The application is configured also to be an Uber jar containing all necessary dependencies for system to function, alongside resources which it uses to interact with tables.

When first running the system, the application will create several files which contain commands necessary for running the program. These files are then read by the program, of which the data inside is used to create, drop, and populate tables. Like the version described in Assignment 9, this version of the program also requires the user to connect to the Toronto Metropolitan network via VPN in order to execute queries successfully.

To use the system, the user must first log in as an existing user to the system. Functionality is planned to allow administrators to authenticate new users and create user accounts.
When a user logs in, they are presented by a panel which allows them to make specific queries, as well as search for media by name. An output log below shows the result of each interaction.

If the user chooses to add or delete data, they will be presented with a screen which allows them to add and delete media and member accounts. It was chosen to not allow the user to modify the history of transactions due to the possibility that they may tamper with the data. Functionality is planned to allow librarians to interact and view transactions and feedback tables.

AN EXAMPLE OF QUERRY EXPORT INTERFACE

![Screenshot 2024-01-16 212316](https://github.com/J-Talon/CPS510FX/assets/130938825/ca41ab99-a5f9-43eb-bbb1-662a34e332c3)


