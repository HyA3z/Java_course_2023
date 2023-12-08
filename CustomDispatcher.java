import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomDispatcher implements Dispatcher {
    private final BlockingQueue<Taxi> availableTaxis;
    private final BlockingQueue<String> orderQueue;

    public CustomDispatcher() {
        this.availableTaxis = new LinkedBlockingQueue<>();
        this.orderQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void assignTaxi(Taxi taxi) {
        availableTaxis.add(taxi);

        if (!orderQueue.isEmpty()) {
            try {
                String order = orderQueue.take();
                Taxi availableTaxi = availableTaxis.take();
                System.out.println("Dispatcher requested " + availableTaxi.toString() + " for an order.");
                availableTaxi.receiveOrder(new Passenger(order.split("'s")[0]), order.split("to ")[1]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void notify(Taxi taxi) {
        availableTaxis.add(taxi);

        if (!orderQueue.isEmpty()) {
            try {
                String order = orderQueue.take();
                Taxi availableTaxi = availableTaxis.take();
                System.out.println("Dispatcher requested " + availableTaxi.toString() + " for an order.");
                availableTaxi.receiveOrder(new Passenger(order.split("'s")[0]), order.split("to ")[1]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void enqueueOrder(String order) {
        try {
            orderQueue.put(order);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!availableTaxis.isEmpty()) {
            try {
                Taxi availableTaxi = availableTaxis.take();
                String queuedOrder = orderQueue.take();
                System.out.println("Dispatcher requested " + availableTaxi.toString() + " for an order.");
                availableTaxi.receiveOrder(new Passenger(queuedOrder.split("'s")[0]), queuedOrder.split("to ")[1]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}