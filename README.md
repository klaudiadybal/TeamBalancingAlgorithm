# **Team Balancer**

This Java program is designed to balance teams based on the skills of individual players. 
It provides methods to find teams with the lowest overall skill and the smallest number of players.
The algorithm adjusts the number of teams if the total number of players is not divisible by the specified number of teams.

## Prerequisites

* Java Development Kit (JDK) installed on your system.
* Gradle build tool installed on your system.

## How to run tests

You can run the tests using Gradle from the command line, as described below.
Alternatively, you can also perform these actions using an Integrated Development Environment (IDE) of your choice.

### Using Command Line (Gradle):

**1. Clone the repository (or unpack ZIP file):**

`git clone <https://github.com/klaudiadybal/TeamBalancingAlgorithm.git>
cd TeamBalancingAlgorithm`

**2. Open created folder in terminal, compile and run test using Gradle:**

`gradle test`

This command compiles the code and runs all the unit tests. The test results will be displayed in the terminal.

### Using Integrated Development Environment (IDE):

1. Import the project into your IDE.

2. Run the tests from the IDE's testing interface.

## Running the algorithm

To use team balancing algorithm in your Java project, follow these steps:

**1. Import the TeamBalancer class.**

**2. Adjust the algorithm parameters.**

To execute the team balancing algorithm, utilize the balanceTeams method from the TeamBalancer class within your Java code.
Modify the 'numberOfTeams' variable to specify the desired number of teams for balancing.
Ensure that the 'individuals' map contains the player names as keys and their corresponding skills as values.

**3. Run Your Application.**

Compile and run your Java application to see balanced teams based on the provided input data.

