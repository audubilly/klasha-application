# klasha-application
A simple population application.

Table of Contents

- [About]
- [Prerequisites]
- [API Documentation]

 1. About
        
        This application is a simple springboot application that mainly calculates populations of countries and cities and also gives vital informations concerning them which includes population
        capital cities, locations, currencies, ISO2&3(These are Latiudes and Longitudes) of locations.


 2. Getting Started

 2.1 Prerequisites

        Before you begin, make sure you have the following installed:

        Java Development Kit (JDK): You need Java to run Spring Boot applications. Download and install the latest version from the Oracle website or OpenJDK.

        Integrated Development Environment (IDE): You can use popular IDEs like Eclipse, IntelliJ IDEA, or Visual Studio Code.

        Build Tool (Optional): Spring Boot projects can be built and managed using Maven or Gradle. Make sure you have one of these installed:

        Apache Maven
        Gradle
    
  2.2 Steps to Set Up and Run the Project
    
          Clone the Repository:
        
          Open a terminal or command prompt.
          Use the git clone command to clone the project repository from GitHub:
          bash
        
          git clone https://github.com/your-username/your-project.git
        
          Import the Project (IDE-Dependent):
        
          If you are using an IDE like IntelliJ IDEA or Eclipse:
        
          Open your IDE.
          
          Choose "Import" or "Open" and select the project folder.
          
          The IDE should detect the project as a Spring Boot project and set up the necessary configurations.
          
          Build the Project (Optional):
        
          use a build tool like called  Maven to build the project to resolve dependencies and compile code.
        
          For Maven, run mvn clean install in the project's root directory.
        
          Configure Application Properties:
          Locate the application.properties file in your project's src/main/resources folder.
        
          Run the Application
            
3.  Api Documentation

        CountriesAPI Documentation
        
        Introduction
        
        This documentation provides details about the CountriesAPI, which allows you to perform various operations related to countries, cities, and currency conversions.
        
        ## API Endpoints
        
        ### 1. Get City By Population
        
        - **Method**: GET
        - **URL**: `http://localhost:8080/countries/get-city-by-population?number_of_cities=10`
        - **Description**: Get a list of cities by population.
        - **Parameters**:
          - `number_of_cities` (Query Parameter): The number of cities to retrieve (e.g., 10).
        - **Response**: A list of cities and their population data.
        
        ### 2. Get Country Data
        
        - **Method**: GET
        - **URL**: `http://localhost:8080/countries/get-country-data?country=Nigeria`
        - **Description**: Get data about a specific country.
        - **Parameters**:
          - `country` (Query Parameter): The name of the country for which data is requested (e.g., "Nigeria").
        - **Headers**:
          - `Content-Type`: `application/json`
        - **Response**: Information about the requested country, including population data, capital, location, and currency.
        
        ### 3. Retrieve State Detail
        
        - **Method**: GET
        - **URL**: `http://localhost:8080/countries/retrieve-state-detail?country=Nigeria`
        - **Description**: Retrieve details about states within a country.
        - **Parameters**:
          - `country` (Query Parameter): The name of the country for which state details are requested (e.g., "Nigeria").
        - **Response**: Information about the states within the specified country, including their names and cities.
        
        ### 4. Currency Conversion
        
        - **Method**: POST
        - **URL**: `http://localhost:8080/countries/currency-conversion?country=Italy&amount=20&target-currency=NGN`
        - **Description**: Perform currency conversion.
        - **Parameters**:
          - `country` (Query Parameter): The source country for currency conversion (e.g., "Italy").
          - `amount` (Query Parameter): The amount to convert (e.g., 20).
          - `target-currency` (Query Parameter): The target currency code (e.g., "NGN").
        - **Response**: The converted currency amount and the source country's currency.
        
        ## Postman Collection
        
        You can access the complete Postman collection for these API endpoints by clicking [here].
        
        Please ensure that you have the API server running locally at `http://localhost:8080` to test these endpoints.
        
        That's it! You can use this documentation to understand and interact with the CountriesAPI endpoints.
  

            
    
  

  
