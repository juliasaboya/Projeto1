import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;

public class RoadPanel extends JPanel {

    public Image leftCarImage, rightCarImage;

    private List<Car> leftCars;
    private List<Car> rightCars;

    public RoadPanel(List<Car> leftCars, List<Car> rightCars) {
        this.leftCars = leftCars;
        this.rightCars = rightCars;
        try {
            leftCarImage = ImageIO.read(new File("caryellow.png")).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            rightCarImage = ImageIO.read(new File("carpink.png")).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        backGround(g);
        drawClouds(g);
        drawBorder(g, 295);
        drawBigRoad(g);
        drawBorder(g, 500);
        drawLargeBorder(g, 450);
        drawLargeBorder(g, 295);
        int i =0;
        while (i<2000){
            drawLines(g, i, 405);
            i+=50;
        }
        drawRoad(g);
        for (Car car : leftCars) {
            car.draw(g); // Desenha o carro com as coordenadas do carro atual
        }
    
        // Desenhar carros à direita
        for (Car car : rightCars) {
            car.draw(g); // Desenha o carro com as coordenadas do carro atual
        }
        
        grass(g);
    }

    private void backGround(Graphics g){
        Color bgColor = new Color(65,192,182);
        g.setColor(bgColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    private void grass(Graphics g){
        Color grassColor = new Color(87,192,65);
        g.setColor(grassColor);
        g.fillRect(0, 650, getWidth(), getHeight());
    }
    private void drawBorder(Graphics g,int pontoy) {
        Color borderColor = new Color(0,0,0);
        g.setColor(borderColor);
        g.fillRect(0, pontoy, getWidth(), 5);
    }
    private void drawLines(Graphics g,int pontox, int pontoy) {
        Color whiteColor = new Color(255,255,255);
        g.setColor(whiteColor);
        g.fillRect(pontox, pontoy, 25, 5);
    }
    
    private void drawLargeBorder(Graphics g, int pontoy) {
        Color bgColor = new Color(65,192,182);
        g.setColor(bgColor);
        g.fillRect(getWidth()/3, pontoy, getWidth()/3, 55);
    }

    private void drawBigRoad(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 300, getWidth(), 200);
    }

    private void drawRoad(Graphics g) {
        Color roadColor = new Color(36,35,36);
        g.setColor(roadColor);
        g.fillRect(getWidth()/3, 350, getWidth() /3, 100);
    }
    private void drawClouds(Graphics g) {
        // Definindo a cor das nuvens
        Color cloudColor = new Color(255, 255, 255);

        // Definindo o número de nuvens e suas posições
        int numClouds = 3;
        int[] cloudX = {50, 200, 400}; // Posições X das nuvens
        int[] cloudY = {100, 50, 150}; // Posições Y das nuvens

        // Desenhando cada nuvem
        for (int i = 0; i < numClouds; i++) {
            drawSingleCloud(g, cloudX[i], cloudY[i], cloudColor);
        }
    }

    // Função auxiliar para desenhar uma única nuvem
    private void drawSingleCloud(Graphics g, int x, int y, Color color) {
        g.setColor(color);

        // Desenhando a forma da nuvem
        int[] xPoints = {x, x + 20, x + 40, x + 60, x + 80, x + 100};
        int[] yPoints = {y + 40, y + 20, y + 40, y + 20, y + 40, y + 20};
        int numPoints = xPoints.length;

        // Desenhando a forma poligonal da nuvem
        Polygon cloud = new Polygon(xPoints, yPoints, numPoints);
        g.fillPolygon(cloud);
    }
}
