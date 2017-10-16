# acceptance_test-project

My Solution
------------
I have used the BDD approach for testing this application. 
I have first used the cucumber for creating the feature files and then created the step definition functions for
each of the Given, When, and Then scenarios. I have used the Selenium Webdriver using Java to create the acceptance testing
scripts. I have also used Junit framework for assertions with selenium code.

Technology Stack
----------------
Cucumber
Selenium WebDriver using Java
Junit
AShot library for taking webelements screenshot and comparison

How to Setup and Execute
------------------------
Build the application under test on local machine:

$ npm install
$ npm run develop
Note: Application can be downloaded from this URL https://github.com/buildit/acceptance-testing

Pre-Requsits for Test Project:

Ensure that latest geckodriver.exe is placed in this path C:\\geckodriver.exe
Import the project into your IDE
Ensure latest Selenium WebDriver jars are imported into your project
Ensure latest cucumber jars are imported into your project
Ensure that you have installed cucumber plugin into your IDE (http://cucumber.github.io/cucumber-eclipse/update-site/)
Ensure that JUnit jars are imported into your project

Running the Tests:

1. Right click on any of the cucumber feature in the Feature folder
2. Run As > Cucumber Feature (This will invoke the step definition scripts)

Note: Use latest Selenium WebDriver and Mozila Firefox


If I had More Time
------------------

Using TestNG Framework:

TestNG provides a very good framework for automation design and execution. I would have used it because it provides very good
libraries for data driven testing, it is very good for grouping the test cases and executing them in the batch, it
provides very good support for assertions, and TestNG reports are very powerful. Though I have used cucumber and JUnit
which are sufficent for BDD test automation but if I had more time then I would have used TestNG.

Data Driven Testing:

If I had more time I would have tested the 'city name' control by parameterizing the data from external source 
for different positive and negative scenarios.

Cross Browser Testing:

Right now my scripts only work for Mozila Firefox. If I had more time I would have provided the feature in my framework 
to invoke different browsers such as (Chrome, IE, and Safari).

Image Comparison with a More Smarter Solution:

While Selenium WebDriver lets you take screenshots, it does not come with any image comparison functionality out of 
the box. For this we have to use external libraries such as ImageJ, ImageMagick, or AShot. I have used AShot for this
project but there are some smart solutions out ther to achieve it with more control and speed. Such as we could 
use BufferedImage stream to get ByteArrayOutputStream, and from there we could derive a byte[] representation of the 
PNG image. Now we could easily compare the byte arrays of the two different images and check for any differences
all within the Java ecosystem. If I had more time I would have definately spent more time on this.

More Positive and Negative Test Cases:

If I had more time I would have created more positive and negative test scenarios for this application.

Reporting:

If I had time more than 4 hrs I would have used Extent reports with cucumber



  
