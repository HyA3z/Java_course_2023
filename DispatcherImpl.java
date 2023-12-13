import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class DispatcherImpl implements Dispatcher {
    private final BlockingQueue<Taxi> availableTaxis;
    private final BlockingQueue<String> orderQueue;

    public DispatcherImpl() {
        this.availableTaxis = new LinkedBlockingQueue<>();
        this.orderQueue = new LinkedBlockingQueue<>();
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

            String[] orderParts = queuedOrder.split("to ");
            if (orderParts.length == 2) {
                availableTaxi.placeOrder(new Passenger(orderParts[0]), orderParts[1]);
            } else {
                System.out.println("Invalid order format: " + queuedOrder);
            }
        }
    }
}
