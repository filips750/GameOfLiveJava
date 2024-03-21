package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import Helpers.coordinates;
import Organisms.Organism;
import Worlds.Hexagon;
import Worlds.World;
import Worlds.WorldHex;
import UI.ListOfOrganisms;

public class Console implements ActionListener{
    World world;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    JFrame frame;
    JPanel panel;
    JButton button;
    JButton load;
    JButton save;
    Container contentPane;
    MyMouseListener mouseListener;
    MyKeyListener myKeyListener;
    Organism lastPickedOrganism;
    ListOfOrganisms listOfOrganisms;
    JTextField jTextField;
    boolean isNewTurn = false;

    public Console(){
        frame = new JFrame("193512 Filip Stankiewicz");
        panel = new JPanel();
        button = new JButton("New turn");
        load = new JButton("Load");
        save = new JButton("Save");

        button.setFocusable(false);
        load.setFocusable(false);
        save.setFocusable(false);
        int BUTTON_LOCATION_X = 500;  // location x
        int BUTTON_LOCATION_Y = 50;   // location y
        int BUTTON_SIZE_X = 140;      // size height
        int BUTTON_SIZE_Y = 50;       // size width
        mouseListener = new MyMouseListener();
        myKeyListener = new MyKeyListener(this);
        contentPane = frame.getContentPane();
        contentPane.addMouseListener(mouseListener);
        contentPane.addKeyListener(myKeyListener);
        frame.addKeyListener(myKeyListener);
        button.addActionListener(this);
        load.addActionListener(this);
        save.addActionListener(this);
        frame.setFocusable(true);
        button.setBounds(BUTTON_LOCATION_X, BUTTON_LOCATION_Y,
                BUTTON_SIZE_X, BUTTON_SIZE_Y );
        load.setBounds(BUTTON_LOCATION_X, BUTTON_LOCATION_Y + BUTTON_SIZE_Y,
                BUTTON_SIZE_X, BUTTON_SIZE_Y );
        save.setBounds(BUTTON_LOCATION_X, BUTTON_LOCATION_Y + BUTTON_SIZE_Y * 2,
                BUTTON_SIZE_X, BUTTON_SIZE_Y );


        contentPane.add(button);
        contentPane.add(load);
        contentPane.add(save);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setBounds(200, 50, WIDTH, HEIGHT);



    }

    public int getLastPressedKey() {
        return myKeyListener.getLastClickedKey();
    }

    public boolean getIsNewTurn(){
        return isNewTurn;
    }
    public boolean isFigureClicked(World world){
        Point point = mouseListener.lastClickedPoint;
        return world.isClickedFigureCoordinates(point);
    }

    public coordinates getFigureClicked(World world){
        Point point = mouseListener.lastClickedPoint;
        return world.getClickedFigureCoordinates(point);
    }

    public JPanel getPanel(){
        return panel;
    }

    public void save(){
        world.saveToCSV();
    }

    public void load(){

        Scanner in = new Scanner(System.in);
        String path = in.nextLine();

        world = World.loadFromCSV(path);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            isNewTurn = true;
        } else if (e.getSource() == save) {
            save();
        }else if (e.getSource() == load) {
            load();
        }

    }
    public void setNewTurnFalse(){
        isNewTurn = false;
    }

    public MyMouseListener getMouseListener() {
        return mouseListener;
    }

    public void showOrganisms(){
        listOfOrganisms = new ListOfOrganisms();
    }

    public Organism getLastPickedOrganism(){
        return listOfOrganisms.getLastPickedOrganism();
    }

    public void setWorld(World world){
        this.world = world;
    }

    public World getWorld() {
        return world;
    }
}
