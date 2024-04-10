import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Parking {
    @SuppressWarnings("unused")
    private boolean isBridgeOccupied = false; 
    private ArrayList<Car> leftCars;
    private ArrayList<Car> rightCars;
    private Semaphore semaphoreLeft = new Semaphore(1); // Semáforo para controlar a passagem dos carros da esquerda
    private Semaphore semaphoreRight = new Semaphore(1);


    public boolean isOppositeSideOccupied(boolean left){
        ArrayList<Car> carsOnBridge = left ? rightCars : leftCars;
        for (Car car : carsOnBridge){
            if(car.getX() >= 350 && car.getX() <= 750){
                return true;
            }
        }
        return false;
    }
    public void setBridgeOccupied(boolean occupied) {
        this.isBridgeOccupied = occupied;
    }
    public void controlTraffic(boolean left) throws InterruptedException {
        Semaphore semaphore = left ? semaphoreLeft : semaphoreRight; 
        semaphore.acquire(); 
        if (isOppositeSideOccupied(!left)) {
            setBridgeOccupied(true); 
        }
    }

    // Método para liberar o semáforo e permitir que carros da direção especificada continuem
    public void releaseSemaphore(boolean left) {
        Semaphore semaphore = left ? semaphoreLeft : semaphoreRight; // Seleciona o semáforo apropriado
        semaphore.release(); // Libera o semáforo para permitir que carros da direção especificada continuem
        setBridgeOccupied(false); // Define a ponte como livre
    }
    
}
