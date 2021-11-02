# codefellows

* web app  allows users to add post,follow users and see their posts.
* you can see user’s username on every page ( in the navbar) after login.
* The site have a non-whitelabel error handling page.



## how to use this application:
* run the application by IDE then go to your browser and write <http://localhost:8080/>.
* now you are in the home page from navbar click on signup .
* write all your information then submit.
* you can login using your username and pass.
* the profile page now dispaly in navbar click in it to see your info.
* from profile page you can add new post and you can see your previous post in it.

* you can see user’s username on every page ( in the navbar) after login.
* The site have a non-whitelabel error handling page.






## Localhost
* localhost:8080

# API

## GET requests

* / 
  to show the home page
* /signup 
  shows the signup page
* /login 
  show the login page
* /profile 
  shows the profile of the logged user and your post .
* <http://localhost:8080/users/{id>} </br>
  which allows viewing the data about a single ApplicationUser by write their id .

## POST requests

* /signup 
  Gets the user credentials and signs up his data to the database and authorize the user.
* /addpost 
  get the body of the post from form and store it in database.






