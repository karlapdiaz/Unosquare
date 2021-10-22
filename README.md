# Unosquare

## Prerequisites 
Java 8 
Maven 

##Structure 
The project is in three main models to separate the POM, Page Factory and Rest Assued implementations. 

The tests have dependencies so if one fails, the following ones will be skipped. This happens mainly for the second test case since the api sometimes gives a 403 because it is having many requests. What should be done is to run the test again. 

To run it we can enter the following command in terminal:
mvn test -Dsuite=suiteFileName
Ex. mvn test -Dsuite=allTest

there are three suites in the project, to run the cases independently and to run both
