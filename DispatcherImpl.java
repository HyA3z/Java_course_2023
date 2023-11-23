import java.util.LinkedList;
import java.util.Queue;

class DispatcherImpl implements Dispatcher {
    private final Queue<Taxi> availableTaxis;
    private final Queue<String> orderQueue;

    public DispatcherImpl() {
        this.availableTaxis = new LinkedList<>();
        this.orderQueue = new LinkedList<>();
    }

    @Override
    public synchronized void requestTaxi(Taxi taxi) {
        availableTaxis.add(taxi);

        if (!orderQueue.isEmpty()) {
            String order = orderQueue.poll();
            Taxi availableTaxi = availableTaxis.poll();
            System.out.println("Dispatcher requested " + availableTaxi.toString() + " for an order.");
            availableTaxi.placeOrder(new Passenger(order.split("'s")[0]), order.split("to ")[1]);
        }
    }

    @Override
    public synchronized void notifyCompletion(Taxi taxi) {
        availableTaxis.add(taxi);

        if (!orderQueue.isEmpty()) {
            String order = orderQueue.poll();
            Taxi availableTaxi = availableTaxis.poll();
            System.out.println("Dispatcher requested " + availableTaxi.toString() + " for an order.");
            availableTaxi.placeOrder(new Passenger(order.split("'s")[0]), order.split("to ")[1]);
        }
    }

    public synchronized void addOrderToQueue(String order) {
        orderQueue.add(order);

        if (!availableTaxis.isEmpty()) {
            Taxi availableTaxi = availableTaxis.poll();
            String queuedOrder = orderQueue.poll();
            System.out.println("Dispatcher requested " + availableTaxi.toString() + " for an order.");
            availableTaxi.placeOrder(new Passenger(queuedOrder.split("'s")[0]), queuedOrder.split("to ")[1]);
        }
    }
}