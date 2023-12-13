import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class TaxiImpl implements Taxi, Runnable {
    private final String name;
    private final Dispatcher dispatcher;
    private boolean busy;
    private final Queue<String> orders;

    public TaxiImpl(String name, Dispatcher dispatcher) {
        this.name = name;
        this.dispatcher = dispatcher;
        this.busy = false;
        this.orders = new LinkedList<>();
    }

    @Override
    public void placeOrder(Passenger passenger, String destination) {
        String order = passenger.getName() + "'s order to " + destination;
        orders.add(order);
        System.out.println(name + " received an order: " + order);
        new Thread(this).start();
    }

    @Override
    public void fulfillOrder() {
        while (!orders.isEmpty()) {
            String currentOrder = orders.poll();
            // Sleeping for a random time
            try {
                Thread.sleep(new Random().nextInt(5000) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " completed the order: " + currentOrder);
        }

        busy = false;
        dispatcher.notifyCompletion(this);
    }

    @Override
    public void run() {
        busy = true;
        fulfillOrder();
    }

    public boolean is_Busy() {
        return busy;
    }
}