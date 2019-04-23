# booking_challenge Biko Pougala 
Coding challenge for the Software Engineer Internship Programme Application


## Setup

Navigate to the `server` folder. There are two executables called `PartOne.jar` and `PartOneExtended.jar`.
You can either run the two JAR files at the command line or double-click them if your operating system allows it. 

## Part One 
### Console application to print the search results for Dave's Taxis

```
java -jar PartOne.jar <arg1> <arg2> <arg3>
``` 
where `arg1` is the LatLng for the pickup location, `arg2` is the LatLng for the drop-off location and `arg3` is the number 
of passengers. Set it to `4` or less to turn off filter by car type. Any integer between 1 and 16 will do. 
Ex:
```
java -jar PartOne.jar 51.470020,-0.454296 51.501366,-0.141890 4

```
### Console application to print the search results for all Taxi companies

```
java -jar PartOneExtension.jar <arg1> <arg2> <arg3>
```
where `arg1`, `arg2` and `arg3` are defined as previously. 

## Part 2
### Launching the server
You need to be in the `server` folder as previously and run the following command to start the server on port 8080:
```
./mvnw spring-boot:run 
```
Type `http://localhost:8080/<app>` either on Postman or in any browser to start sending `GET`requests, where `app` is one of 
`partOne` or `extensionPartOne`, followed by the arguments as defined previously. 

Ex: 
```
http://localhost:8080/partOne?pickup=51.470020,-0.454296&dropoff=51.501366,-0.141890&numPassengers=4
```
