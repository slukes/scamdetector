# Scam detector

## Build

To build and test the project run

`mvn clean install`

Note that this project uses java 11

## Run

To run the project unzip the built archive ScamDetector-1.0-SNAPSHOT-dist.zip

Launch the provided script

`./scam-detector`

If you want to test a different advert, provide the fully qualified path to the script.

## Extensibility

- New rules can be added simply by implementing the rule interface.
- Other interfaces i.e. a web service can be simply built by using the ScamService class which is a facade to the logic of the program.
