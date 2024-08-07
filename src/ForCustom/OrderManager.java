package ForCustom;

import Cars.Car;
import Users.User;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> orders;

    public OrderManager() {
        orders = new ArrayList<>();
    }

    public void createOrder(Car car, User customer) {
        orders.add(new Order(car, customer, "Pending"));
    }

    public void updateOrderStatus(int index, String status) {
        if (index >= 0 && index < orders.size()) {
            orders.get(index).setStatus(status);
        }
    }

    public void cancelOrder(int index) {
        if (index >= 0 && index < orders.size()) {
            orders.remove(index);
        }
    }

    public List<Order> getOrders() {
        return orders;
    }
}
