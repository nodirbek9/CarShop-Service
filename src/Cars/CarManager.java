package Cars;

import java.util.ArrayList;
import java.util.List;

public class CarManager {
        private List<Car> cars;

        public CarManager() {
            cars = new ArrayList<>();
        }

        public void addCar(String brand, String model, int year, double price, String condition) {
            cars.add(new Car(brand, model, year, price, condition));
        }

        public void editCar(int index, String brand, String model, int year, double price, String condition) {
            if (index >= 0 && index < cars.size()) {
                cars.set(index, new Car(brand, model, year, price, condition));
            }
        }

        public void deleteCar(int index) {
            if (index >= 0 && index < cars.size()) {
                cars.remove(index);
            }
        }

        public List<Car> getCars() {
            return cars;
        }
    }
