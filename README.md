# booking_challenge Biko Pougala 
Coding challenge for the Software Engineer Internship Programme Application


## Setup
Run the command: 
After downloading the project on your local machine run the command:
```
cd server/src/main/java/apis/booking_challenge
```

This will lead you to the directory where all the source code files are present. To compile the first part, 
run the command: 

```
javac -cp "json-20180813.jar" OutputParser.java PartOne.java && javac PartOneExtension.java
```

## Part One 
### Console application to print the search results for Dave's Taxis

```
java PartOne <arg1> <arg2> <arg3>
``` 
where `arg1` is the LatLng for the pickup location, `arg2` is the LatLng for the drop-off location and `arg3` is the number 
of passengers. Set it to `4` for the standard behaviour. Any integer between 1 and 16 will do. 

### Console application to print the search results for all Taxi companies

```
java PartOneExtension <arg1> <arg2> <arg3>
```
where `arg1`, `arg2` and `arg3` are defined as previously. 

## Part 2
### Launching the server
Go back to the main folder to launch the local server: 
```
cd && cd ~/path/to/downloaded/project/server && ./mvnw spring-boot:run 
```
Type `http://localhost:8080/<app> either on Postman or in any browser to start sending `GET`requests, where <app> is one of 
`partOne` or `extensionPartOne`, followed by the arguments as defined previously. 

Ex: 
```
http://localhost:8080/extensionPartOne?pickup=51.470020,-0.454296&dropoff=51.501366,-0.141890&numPassengers=4
```
