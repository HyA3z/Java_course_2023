public class TestApi {
    public static void main(String[] args) {
        CustomDispatcher dispatcher = new CustomDispatcher();

        // Create taxis
        TaxiService taxi1 = new TaxiService("Taxi 1", dispatcher);
        Taxi taxi2 = new TaxiService("Taxi 2", dispatcher);
        Taxi taxi3 = new TaxiService("Taxi 3", dispatcher);

        dispatcher.assignTaxi(taxi1);
        dispatcher.assignTaxi(taxi2);
        dispatcher.assignTaxi(taxi3);

        // Check if a taxi is busy before assigning a new order
        if (!taxi1.isBusy()) {
            dispatcher.enqueueOrder("Frank's order to Theater");
        } else {
            System.out.println("Taxi 1 is busy. Unable to assign a new order.");
        }

        // Simulate additional orders
        dispatcher.enqueueOrder("Alice's order to Airport");
        dispatcher.enqueueOrder("Bob's order to Station");
        dispatcher.enqueueOrder("Charlie's order to Mall");
        dispatcher.enqueueOrder("David's order to Restaurant");
        dispatcher.enqueueOrder("Eve's order to Park");

    }
}
