package ForCustom;

import Cars.Car;
import Users.User;

public class Order {
    private Car car;
    private User customer;
    private String status;

    public Order(Car car, User customer, String status) {
        this.car = car;
        this.customer = customer;
        this.status = status;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order: " + car.toString() + ", Customer: " + customer.getUsername() + ", Status: " + status;
    }
}
