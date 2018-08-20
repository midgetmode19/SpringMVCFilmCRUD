## Spring MVC project

### Description
In this project a user manipulates data in a database through a web page.

When the start page is loaded, the user is presented with a menu with the options to search for a film by its id, search for a film using a keyword, or to add a new film into the database.

#### Search By Film ID
When the user searches by id, they are presented with a page that displays the film with that id, or told that it was not found if the film id is not in the database or does not have a film associated with it. From this page the user can choose to edit the film by filling out the form, or to delete the film by entering in its id number.

When the film is updated, they are taken to a page that tells the user if the updated was successful or not. When the user follows the link to the home page, they can search for the film and see the updates.

#### Add a Film
When the user chooses to add a film, they are taken to a form page where they enter in all of the values for the film. When the user submits the add, if it is successful the page will display all of the new film's details including its ID so that a user may search for it again.

#### Search by Keyword
When a user chooses to search by keyword, They are taken to a page with the search bar. The user can enter in any keyword and the search will take the user to a page that displays all matches. Under each film is a form that allows the user to update or delete films in the list.

### Technologies Used

* Java
* JDBC
* Sql
* Mysql Workbench
* Gradle
* AWS