# Mars Climate Orbiter Ground Station

## Introduction
You are a Software Developer at a big aerospace company.
Your next project is to develop a ground station for a NASA mission to Mars.
Since you are working with cutting edge technologies this is your stack:
- AWS Ground Station: Receives Telemetry Signals and passes them to your Microservice via REST (JSON)
- Kotlin: It just feels more modern therefore you need to use it
- Spring Boot: Since you have to develop a Microservice, this is state of the art (or isn't it?)
- Postgresql: Who uses anything else today? (Besides NoSQL)

### Mission
The Mars Climate Orbiter is a 638-kilogram robotic space probe, 
it will be launched by NASA to study the Martian climate, Martian atmosphere, 
and surface changes and to act as the communications relay in the Mars Surveyor program for Mars Polar Lander. 
The Mars Climate Orbiter probe will be launched from Space Launch Complex 17A 
at the Cape Canaveral Air Force Station in Florida, aboard a Delta II 7425 launch vehicle. 
The complete burn sequence will last 42 minutes bringing the spacecraft into a Hohmann transfer orbit, 
sending the probe into a 9.5 months, 669 million km trajectory. 
At launch, Mars Climate Orbiter will weigh 638 kg including propellant.
According to NASA the mission cost is $327.6 million US dollars total for orbiter and lander.

### Architecture
![Architecture](src/docs/resources/architecture.png)
The Satellite will communicate with AWS Ground Station,
which will convert the data to JSON and send it to the telemetry Microservice
running in EKS. The Microservice will then store the data in a PostgreSQL Database.

### Task
### Input
Write the telemetry Microservice which has one Endpoint to accept incoming data.
Each Transmission will be given as a JSON Document. Example:

```json
{
  "rtt": 236958,
  "fuel": {
    "nto": 61,
    "hydrazine": 212
  },
  "sensors": {
    "gyroscope0": {
      "x": 0.0002,
      "y": -0.0001,
      "z": 0.0006
    },
    "gyroscope1": {
      "x": -0.0001,
      "y": 0.0,
      "z": 0.0001
    },
    "sunSensor0": 0.8947,
    "sunSensor1": 0.1293
  },
  "energy": {
    "battery": 15.432,
    "solar": 489.2
  },
  "computer": {
    "clockSpeed": 5000000,
    "memory": {
      "free": 17401750,
      "used": 1472618
    }
  }
}
```
The "rtt" value is the round trip time between the AWS Ground Station and the Satellite.
The hydrazine fuel tank has a capacity of 230Kg, and the NTO fuel tank has a capacity of 61Kg.
You can find extended Documentation for the other values [here](https://mars.nasa.gov/internal_resources/818/).

### Output

Write an Endpoint which provides the following data:
- Distance to Earth in Km
- Speed relative to Earth in Km/h
- Current Acceleration
- Battery Power in KWh and Percent
- Solar Power in Watt and Percent
- Free Memory in Megabyte and Percent

### Challenge
As you might see this Task tries to tackle the problems,
seen in 1999, when the Mars Climate Orbiter crashed due to
a wrong conversion between SI- and Imperial Units.
Try to implement a Solution which ensures 
a conversion error between different units cannot occur.