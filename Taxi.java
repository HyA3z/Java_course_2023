public interface Taxi {
    void receiveOrder(Passenger passenger, String destination);
    void processOrder();
}