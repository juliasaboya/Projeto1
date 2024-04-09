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
    public final int maxCars = 10;
    public Threads.ThreadA threadA;
    public Threads.ThreadB threadB;
    final int sizeX = 1200, sizeY = 1000;
    public int[] identifierVerification;
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
            CarManager manage = new CarManager(roadPanel, leftCars, rightCars);
            

            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                int crossingTime = Integer.parseInt(crossingTimeField.getText());
                int waitingTime = Integer.parseInt(waitingTimeField.getText());
                manage.createCar(true, id, crossingTime, waitingTime);
                String idLabel = id + "A";
                addToLog("Carro "+idLabel+" criado a esquerda");
  
            }
        }
        );
        
        rightButton.addActionListener(new ActionListener() {
            CarManager manage = new CarManager(roadPanel, leftCars, rightCars);
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                int crossingTime = Integer.parseInt(crossingTimeField.getText());
                int waitingTime = Integer.parseInt(waitingTimeField.getText());
                manage.createCar(false, id, crossingTime, waitingTime);
                String idLabel = id + "B";
                addToLog("Carro "+idLabel+" criado a direita");

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

        threadA = new Threads.ThreadA(leftCars, roadPanel);
        threadB = new Threads.ThreadB(rightCars, roadPanel);
        threadA.start();
        threadB.start();
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Interface carSimulation = new Interface();
            carSimulation.setVisible(true);
        });
    }
    public void addToLog(String message){
        logTextArea.append(message + "\n");
    }
}
