import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Interface extends JFrame {
    private RoadPanel roadPanel;
    
    public JTextArea logTextArea;
    public ArrayList<Car> leftCars = new ArrayList<>();
    public ArrayList<Car> rightCars = new ArrayList<>();
    private CarManager manage = new CarManager(roadPanel, leftCars, rightCars);
    public final int maxCars = 10;
    private Timer timer;
    final int sizeX = 1200, sizeY = 1000;
    int i;

    public Interface() {
        setTitle("Car Simulation");
        setSize(sizeX, sizeY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        roadPanel = new RoadPanel(leftCars, rightCars);
        JPanel buttonPanel = new JPanel();
        JButton leftButton = new JButton("Criar Carro Esquerda");
        JButton rightButton = new JButton("Criar Carro Direita");

        JTextField idField = new JTextField(5);
        JTextField crossingTimeField = new JTextField(5);
        JTextField waitingTimeField = new JTextField(5);


        logTextArea = new JTextArea(8,20);
        logTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logTextArea);
        
        leftButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                int crossingTime = Integer.parseInt(crossingTimeField.getText());
                int waitingTime = Integer.parseInt(waitingTimeField.getText());
                manage.createCar(true, id, crossingTime, waitingTime);  
            }
        }
        );
        
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                int crossingTime = Integer.parseInt(crossingTimeField.getText());
                int waitingTime = Integer.parseInt(waitingTimeField.getText());
                manage.createCar(false, id, crossingTime, waitingTime);

            }
        });

        buttonPanel.add(leftButton);
        buttonPanel.add(rightButton);

        buttonPanel.add(new JLabel("No Identificador:"));
        buttonPanel.add(idField);
        buttonPanel.add(new JLabel("Tempo Travessia (s):"));
        buttonPanel.add(crossingTimeField);
        buttonPanel.add(new JLabel("Tempo Espera (s):"));
        buttonPanel.add(waitingTimeField);

        buttonPanel.add(scrollPane);

        
        add(buttonPanel, BorderLayout.SOUTH);
        add(roadPanel);

        // Inicializa o timer para atualizar a posição dos carros a cada 20 milissegundos
        timer = new Timer(20, new ActionListener() {
            CarManager manage = new CarManager(roadPanel, leftCars, rightCars);
            @Override
            public void actionPerformed(ActionEvent e) {
                manage.updateCarsPosition();
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Interface carSimulation = new Interface();
            carSimulation.setVisible(true);
        });
    }
    
}

