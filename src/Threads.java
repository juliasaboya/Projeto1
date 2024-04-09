import java.util.concurrent.Semaphore;
import java.util.List;


public class Threads {
	static Semaphore semA = new Semaphore(0);
    static Semaphore semB = new Semaphore(1);
	
	public static class ThreadA extends Thread {
        private List<Car> leftCars;
        private RoadPanel roadPanel;
    
        public ThreadA(List<Car> leftCars, RoadPanel roadPanel) {
            this.leftCars = leftCars;
            this.roadPanel = roadPanel;
        }
    
        @Override
        public void run() {
            while (true) {
                
                try {
                    semA.acquire(); // Ajuste para controlar a velocidade do movimento
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (Car car : leftCars) {
                    car.move(); // Move os carros à esquerda
                }
                roadPanel.repaint();
                semB.release();
            }
        }
    }
    
	
	public static class ThreadB extends Thread {
        private List<Car> rightCars;
        private RoadPanel roadPanel;
    
        public ThreadB(List<Car> rightCars, RoadPanel roadPanel) {
            this.rightCars = rightCars;
            this.roadPanel = roadPanel;
        }
    
        @Override
        public void run() {
            while (true) {
                
                try {
                    semB.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (Car car : rightCars) {
                    car.move(); // Move os carros à direita
                }
                roadPanel.repaint();
                semA.release();
            }
        }
    }
}