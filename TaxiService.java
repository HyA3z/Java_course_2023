import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaxiService implements Taxi, Runnable {
    private final String name;
    private final Dispatcher dispatcher;
    private boolean busy;
    private final BlockingQueue<String> orders;

    public TaxiService(String name, Dispatcher dispatcher) {
        this.name = name;
        this.dispatcher = dispatcher;
        this.busy = false;
        this.orders = new LinkedBlockingQueue<>();
    }

    @Override
    public void receiveOrder(Passenger passenger, String destination) {
        String order = passenger.getName() + "'s order to " + destination;
        orders.add(order);
        System.out.println(name + " received an order: " + order);
        new Thread(this).start();
    }

    @Override
    public void processOrder() {
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
        dispatcher.notify(this);
    }

    @Override
    public void run() {
        busy = true;
        processOrder();
    }

    public boolean isBusy() {
        return busy;
    }
}