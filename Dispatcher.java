interface Dispatcher {
    void requestTaxi(Taxi taxi);
    void notifyCompletion(Taxi taxi);
}