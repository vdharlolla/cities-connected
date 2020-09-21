# Getting Started

# How To Run

java -jar target/citiesconnection-0.0.1-SNAPSHOT.jar

mvn spring-boot:run.

# Verify Test Coverage

mvn clean verify

jcoco file in below location
/target/site/jacoco/index.html

:Current coverage is at 98% jacoco

## To determines if two cities are connected

This project will guide if two cities are connected if in same path (This application is mostly uses BFS search to determine the path).
The citiesProcesser will load static city.txt file from resource location.

http://localhost:8080/connected?origin=city1&destination=city2
This program will respond with ‘yes’ if city1 is connected to city2, ’no’ if city1 is not connected to city2.

Any unexpected input should result in a ’no’ response.

For Example:
city.txt content is:
Boston, New York
Philadelphia, Newark
Newark, Boston
Trenton, Albany

http://localhost:8080/connected?origin=Boston&destination=Newark

will return yes

http://localhost:8080/connected?origin=Boston&destination=Philadelphia

will return yes

http://localhost:8080/connected?origin=Philadelphia&destination=Albany

will return no


———————————————
