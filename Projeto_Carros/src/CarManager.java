import javax.swing.*;
import java.util.ArrayList;

public class CarManager {
    private RoadPanel roadPanel;
    private ArrayList<Car> leftCars;
    private ArrayList<Car> rightCars;
    private final int maxCars = 10;
    public int leftCarX = 2;
    public int rightCarX = 1080;
    public int leftCarY = 300;
    public int rightCarY = 410;
    private Interface instance;

    public CarManager(RoadPanel roadPanel, ArrayList<Car> leftCars, ArrayList<Car> rightCars) {
        this.roadPanel = roadPanel;
        this.leftCars = leftCars;
        this.rightCars = rightCars;
    }

    public void createCar(boolean left, int identifier, int crossingTime) {
        if (left) {
            if (leftCars.size() < maxCars) {
                Car newCar = new Car(leftCarX, leftCarY, true, identifier, crossingTime, instance);
                leftCars.add(newCar);
                roadPanel.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Limite de carros à esquerda atingido!");
            }
        } else {
            if (rightCars.size() < maxCars) {
                Car newCar = new Car(rightCarX, rightCarY, false, identifier, crossingTime,instance);
                rightCars.add(newCar);
                roadPanel.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Limite de carros à direita atingido!");
            }
        }
    }

    public void updateCarsPosition() {
        for (Car car : leftCars) {
            car.move(); // Move os carros para a esquerda
        }
        for (Car car : rightCars) {
            car.move(); // Move os carros para a direita
        }
        roadPanel.repaint();
    }
}


