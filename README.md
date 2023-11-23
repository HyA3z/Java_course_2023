# Taxi Dispatcher System
This Java program simulates a simple taxi dispatcher system where taxis receive and fulfill orders from passengers.

## Classes
### 1. Passenger
Represents a passenger with a name.
### 2. Taxi
Interface defining methods for placing orders and fulfilling orders.
### 3. Dispatcher
Interface defining methods for requesting taxis and notifying completion.
### 4. TaxiImpl
Implementation of the Taxi interface.
Represents a taxi with a name, a dispatcher it is associated with, and a queue of orders.
Implements the Runnable interface to simulate order fulfillment in a separate thread.
### 5. DispatcherImpl
Implementation of the Dispatcher interface.
Manages available taxis and a queue of orders.
Handles requests for taxis and notifies completion.
### 6. TestApi
Main class to test the functionality of the taxi dispatcher system.

## Running the Program
```bash
javac TestApi.java
java TestApi
