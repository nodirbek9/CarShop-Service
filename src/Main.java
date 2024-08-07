import Cars.Car;
import Cars.CarManager;
import ForCustom.Order;
import ForCustom.OrderManager;
import Users.User;
import Users.UserManager;

import java.util.List;
import java.util.Scanner;

public class Main {
        private static UserManager userManager = new UserManager();
        private static CarManager carManager = new CarManager();
        private static OrderManager orderManager = new OrderManager();

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        registerUser(scanner);
                        break;
                    case 2:
                        User user = loginUser(scanner);
                        if (user != null) {
                            if (user.getRole().equals("admin")) {
                                adminMenu(scanner);
                            } else if (user.getRole().equals("manager")) {
                                managerMenu(scanner);
                            } else {
                                customerMenu(scanner, user);
                            }
                        } else {
                            System.out.println("Invalid username or password.");
                        }
                        break;
                    case 3:
                        System.exit(0);
                }
            }
        }

        private static void registerUser(Scanner scanner) {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            System.out.print("Role (admin/manager/customer): ");
            String role = scanner.nextLine();

            userManager.registerUser(username, password, role);
            System.out.println("User registered successfully.");
        }

        private static User loginUser(Scanner scanner) {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            return userManager.loginUser(username, password);
        }

        private static void adminMenu(Scanner scanner) {
            while (true) {
                System.out.println("Admin Menu:");
                System.out.println("1. Manage Cars");
                System.out.println("2. Manage Orders");
                System.out.println("3. Manage Users");
                System.out.println("4. Logout");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        manageCars(scanner);
                        break;
                    case 2:
                        manageOrders(scanner);
                        break;
                    case 3:
                        manageUsers(scanner);
                        break;
                    case 4:
                        return;
                }
            }
        }

        private static void managerMenu(Scanner scanner) {
            while (true) {
                System.out.println("Manager Menu:");
                System.out.println("1. Manage Cars");
                System.out.println("2. Manage Orders");
                System.out.println("3. Logout");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        manageCars(scanner);
                        break;
                    case 2:
                        manageOrders(scanner);
                        break;
                    case 3:
                        return;
                }
            }
        }
    private static void customerMenu(Scanner scanner, User customer) {
        while (true) {
            System.out.println("Customer Menu:");
            System.out.println("1. View Cars");
            System.out.println("2. Place Order");
            System.out.println("3. View My Orders");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    viewCars();
                    break;
                case 2:
                    placeOrder(scanner, customer);
                    break;
                case 3:
                    viewCustomerOrders(customer);
                    break;
                case 4:
                    return;
            }
        }
    }

    private static void manageCars(Scanner scanner) {
        while (true) {
            System.out.println("Car Management:");
            System.out.println("1. View Cars");
            System.out.println("2. Add Car");
            System.out.println("3. Edit Car");
            System.out.println("4. Delete Car");
            System.out.println("5. Back to Admin/Manager Menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewCars();
                    break;
                case 2:
                    addCar(scanner);
                    break;
                case 3:
                    editCar(scanner);
                    break;
                case 4:
                    deleteCar(scanner);
                    break;
                case 5:
                    return;
            }
        }
    }

    private static void manageOrders(Scanner scanner) {
        while (true) {
            System.out.println("Order Management:");
            System.out.println("1. View Orders");
            System.out.println("2. Update Order Status");
            System.out.println("3. Cancel Order");
            System.out.println("4. Back to Admin/Manager Menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewOrders();
                    break;
                case 2:
                    updateOrderStatus(scanner);
                    break;
                case 3:
                    cancelOrder(scanner);
                    break;
                case 4:
                    return;
            }
        }
    }

    private static void manageUsers(Scanner scanner) {
        System.out.println("User Management:");
        List<User> users = userManager.getUsers();
        for (User user : users) {
            System.out.println(user.getUsername() + " - " + user.getRole());
        }
    }

    private static void viewCars() {
        List<Car> cars = carManager.getCars();
        for (int i = 0; i < cars.size(); i++) {
            System.out.println((i + 1) + ". " + cars.get(i).toString());
        }
    }

    private static void addCar(Scanner scanner) {
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        System.out.print("Condition: ");
        String condition = scanner.nextLine();

        carManager.addCar(brand, model, year, price, condition);
        System.out.println("Car added successfully.");
    }

    private static void editCar(Scanner scanner) {
        viewCars();
        System.out.print("Enter car number to edit: ");
        int carIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume newline

        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        System.out.print("Condition: ");
        String condition = scanner.nextLine();

        carManager.editCar(carIndex, brand, model, year, price, condition);
        System.out.println("Car edited successfully.");
    }

    private static void deleteCar(Scanner scanner) {
        viewCars();
        System.out.print("Enter car number to delete: ");
        int carIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume newline

        carManager.deleteCar(carIndex);
        System.out.println("Car deleted successfully.");
    }

    private static void viewOrders() {
        List<Order> orders = orderManager.getOrders();
        for (int i = 0; i < orders.size(); i++) {
            System.out.println((i + 1) + ". " + orders.get(i).toString());
        }
    }

    private static void updateOrderStatus(Scanner scanner) {
        viewOrders();
        System.out.print("Enter order number to update: ");
        int orderIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume newline

        System.out.print("Enter new status: ");
        String status = scanner.nextLine();

        orderManager.updateOrderStatus(orderIndex, status);
        System.out.println("Order status updated successfully.");
    }

    private static void cancelOrder(Scanner scanner) {
        viewOrders();
        System.out.print("Enter order number to cancel: ");
        int orderIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume newline

        orderManager.cancelOrder(orderIndex);
        System.out.println("Order cancelled successfully.");
    }

    private static void placeOrder(Scanner scanner, User customer) {
        viewCars();
        System.out.print("Enter car number to place order: ");
        int carIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume newline

        Car car = carManager.getCars().get(carIndex);
        orderManager.createOrder(car, customer);
        System.out.println("Order placed successfully.");
    }

    private static void viewCustomerOrders(User customer) {
        List<Order> orders = orderManager.getOrders();
        for (Order order : orders) {
            if (order.getCustomer().equals(customer)) {
                System.out.println(order.toString());
            }
        }
    }
}