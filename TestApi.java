import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class TestApi {
    public static void main(String[] args) {
        DispatcherImpl dispatcher = new DispatcherImpl();

        Taxi taxi1 = new TaxiImpl("Taxi 1", dispatcher);
        Taxi taxi2 = new TaxiImpl("Taxi 2", dispatcher);
        Taxi taxi3 = new TaxiImpl("Taxi 3", dispatcher);

        dispatcher.requestTaxi(taxi1);
        dispatcher.requestTaxi(taxi2);
        dispatcher.requestTaxi(taxi3);

        // Simulate additional orders
        dispatcher.addOrderToQueue("Alice's order to Airport");
        dispatcher.addOrderToQueue("Bob's order to Station");
        dispatcher.addOrderToQueue("Charlie's order to Mall");
        dispatcher.addOrderToQueue("David's order to Restaurant");
        dispatcher.addOrderToQueue("Eve's order to Park");
    }
}
