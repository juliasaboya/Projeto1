import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class Threads {
	static Semaphore semA = new Semaphore(0);
    static Semaphore semB = new Semaphore(1);
    private Parking parking;

    public Threads(Parking parking){
        this.parking = parking;
    }
	
	public static class ThreadA extends Thread {
        private ArrayList<Car> leftCars;
        private RoadPanel roadPanel;
        private Parking parking;

    
        public ThreadA(ArrayList<Car> leftCars, RoadPanel roadPanel, Parking parking) {
            this.leftCars = leftCars;
            this.roadPanel = roadPanel;
            this.parking = parking;

        }
    
        @Override
        public void run() {
            while (true) {
                
                try {
                    //parking.controlTraffic(true);
                    semA.acquire(); // Ajuste para controlar a velocidade do movimento
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (Car car : leftCars) {
                    car.move(); // Move os carros à esquerda
                }
                roadPanel.repaint();
                semB.release();
                //parking.releaseSemaphore(true);
            }
        }
    }
    
	
	public static class ThreadB extends Thread {
        private ArrayList<Car> rightCars;
        private RoadPanel roadPanel;
        private Parking parking;
    
        public ThreadB(ArrayList<Car> rightCars, RoadPanel roadPanel,Parking parking) {
            this.rightCars = rightCars;
            this.roadPanel = roadPanel;
            this.parking = parking;
        }
    
        @Override
        public void run() {
            while (true) {
                
                try {
                    //parking.controlTraffic(false);
                    semB.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } 
                for (Car car : rightCars) {
                    car.move(); // Move os carros à direita
                }
                roadPanel.repaint();
                semA.release();
                //parking.releaseSemaphore(false);
            }
        }
    }
}