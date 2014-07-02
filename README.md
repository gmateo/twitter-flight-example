#Spring MVC - Thymeleaf - Bootstrap - Twitter Flight
In this project we want to show:

I. How to make a simple web application using [Spring MVC](http://projects.spring.io/spring-framework/), [Thymeleaf](http://www.thymeleaf.org/) and [Bootstrap](http://getbootstrap.com/); this application will be responsive and will use only one ajax call [See Blog Entry](http://www.apprenticeshipnotes.org/2014/06/spring-mvc-thymeleaf-bootstrap-i.html) | [See Application](http://twitterflightexample.gamal-mateo.cloudbees.net/main.htm)

II. How to change the previous application in order to be a SPA (single page application). For this part we will use [Twitter Flight](http://flightjs.github.io/) because it provides us a way to mantain our code nicely separated and easy to make changes to the different components. We will make this possible by using two ways:

   - II.1 The response of the ajax request will return html fragments, and the javascript code only will replace the html content of the different zones. [See Blog Entry](http://www.apprenticeshipnotes.org/2014/06/spring-mvc-thymeleaf-bootstrap-twitter_26.html) | [See Application](http://twitterflightexample.gamal-mateo.cloudbees.net/main1.htm)
   - II.2 The response of the ajax request will return json objects, and the javascript code will make the following [See Blog Entry](http://www.apprenticeshipnotes.org/2014/06/spring-mvc-thymeleaf-bootstrap-twitter_8658.html) | [See Application](http://twitterflightexample.gamal-mateo.cloudbees.net/main2.htm):
       * Use the json object to populate the Mustache templates
       * Generate the html fragments using Mustache
       * Replace the html content of the different zones


##Application description
The application will be simple and contain the following characteristics:

- Show products and its price history
- Show different brands 
- Allow filtering by brands and names
- Allow pagination

You can see the application [here](http://twitterflightexample.gamal-mateo.cloudbees.net/main.htm)

##Notes

   - The application uses **gradle** so you can execute it with the command:
         `gradle jettyRun`
   - Version of the main components
       -   Spring = 4.1.0.BUILD-SNAPSHOT
       -   Thymeleaf = 2.1.3.RELEASE
       -   Jackson = 2.4.1
       -   Bootstrap = 3.1.1
