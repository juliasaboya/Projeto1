import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CarManager {
    private Set<Integer> usedIds = new HashSet<>();
    private RoadPanel roadPanel;
    private ArrayList<Car> leftCars;
    private ArrayList<Car> rightCars;
    private boolean isBridgeOccupied = false; 
    private final int maxCars = 10;
    public final int leftCarX = 2;
    public int rightCarX = 1080;
    public int leftCarY = 300;
    public int rightCarY = 410;
    

    public CarManager(RoadPanel roadPanel, ArrayList<Car> leftCars, ArrayList<Car> rightCars) {
        this.roadPanel = roadPanel;
        this.leftCars = leftCars;
        this.rightCars = rightCars;
    }

    public void createCar(boolean left, int id, int crossingTime, int waitingTime) {
        String idLabel = left ? id + "A" : id + "B";
        if (!usedIds.contains(id)){
            if (left) {
                if (leftCars.size() < maxCars) {
                    Car newCar = new Car(leftCarX, leftCarY, idLabel, crossingTime, waitingTime, true);
                    leftCars.add(newCar);
                    usedIds.add(id);
                    roadPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Limite de carros à esquerda atingido!");
                }
            } else {
                if (rightCars.size() < maxCars) {
                    Car newCar = new Car(rightCarX, rightCarY, idLabel, crossingTime, waitingTime, false);
                    rightCars.add(newCar);
                    usedIds.add(id);
                    roadPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Limite de carros à direita atingido!");
                }
            }
            
        } else{
            JOptionPane.showMessageDialog(null, "O Id "+id+" indisponivel.");
        }
    }
}


