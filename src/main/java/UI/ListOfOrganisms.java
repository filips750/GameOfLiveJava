package UI;

import Organisms.Animals.*;
import Organisms.Organism;
import Organisms.Plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListOfOrganisms implements ActionListener {
    JFrame frame;
    Container contentPane;
    JPanel panel;
    JButton Grass;
    JButton Dandelion;
    JButton Guarana;
    JButton SosnowskyHogweed;
    JButton Nightshade;
    JButton Wolf;
    JButton Sheep;
    JButton Fox;
    JButton Turtle;
    JButton Antelope;


    Organism lastPickedOrganism;
    private final int WIDTH = 200;
    private final int HEIGHT = 30;
    public ListOfOrganisms(){
        frame = new JFrame("Pick organism");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = frame.getContentPane();
        Grass = new JButton("Grass");
        Grass.setBounds(10,10,WIDTH,HEIGHT);
        Grass.addActionListener(this);
        contentPane.add(Grass);

        Dandelion = new JButton("Dandelion");
        Dandelion.setBounds(10,10 + HEIGHT,WIDTH,HEIGHT);
        Dandelion.addActionListener(this);
        contentPane.add(Dandelion);

        Guarana = new JButton("Guarana");
        Guarana.setBounds(10,10 + HEIGHT * 2,WIDTH,HEIGHT);
        Guarana.addActionListener(this);
        contentPane.add(Guarana);

        Nightshade = new JButton("Nightshade");
        Nightshade.setBounds(10,10 + HEIGHT * 3,WIDTH,HEIGHT);
        Nightshade.addActionListener(this);
        contentPane.add(Nightshade);

        SosnowskyHogweed = new JButton("SosnowskyHogweed");
        SosnowskyHogweed.setBounds(10,10 + HEIGHT * 4,WIDTH,HEIGHT);
        SosnowskyHogweed.addActionListener(this);
        contentPane.add(SosnowskyHogweed);

        Wolf = new JButton("Wolf");
        Wolf.setBounds(10 + WIDTH,10,WIDTH,HEIGHT);
        Wolf.addActionListener(this);
        contentPane.add(Wolf);

        Sheep = new JButton("Sheep");
        Sheep.setBounds(10 + WIDTH,10 + HEIGHT,WIDTH,HEIGHT);
        Sheep.addActionListener(this);
        contentPane.add(Sheep);

        Fox = new JButton("Fox");
        Fox.setBounds(10 + WIDTH,10 + HEIGHT * 2,WIDTH,HEIGHT);
        Fox.addActionListener(this);
        contentPane.add(Fox);

        Turtle = new JButton("Turtle");
        Turtle.setBounds(10 + WIDTH,10 + HEIGHT * 3,WIDTH,HEIGHT);
        Turtle.addActionListener(this);
        contentPane.add(Turtle);

        Antelope = new JButton("Antelope");
        Antelope.setBounds(10 + WIDTH,10 + HEIGHT * 4,WIDTH,HEIGHT);
        Antelope.addActionListener(this);
        contentPane.add(Antelope);

        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public Organism getLastPickedOrganism() {
        return lastPickedOrganism;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Grass){
            lastPickedOrganism = new Grass();
            frame.dispose();
        } else if (e.getSource() == Dandelion) {
            lastPickedOrganism = new Dandelion();
            frame.dispose();
        } else if (e.getSource() == Guarana) {
            lastPickedOrganism = new Guarana();
            frame.dispose();
        } else if (e.getSource() == Nightshade) {
            lastPickedOrganism = new Nightshade();
            frame.dispose();
        } else if (e.getSource() == SosnowskyHogweed) {
            lastPickedOrganism = new SosnowskyHogweed();
            frame.dispose();
        } else if (e.getSource() == Wolf) {
            lastPickedOrganism = new Wolf();
            frame.dispose();
        }else if (e.getSource() == Sheep) {
            lastPickedOrganism = new Sheep();
            frame.dispose();
        }else if (e.getSource() == Fox) {
            lastPickedOrganism = new Fox();
            frame.dispose();
        }else if (e.getSource() == Turtle) {
            lastPickedOrganism = new Turtle();
            frame.dispose();
        }else if (e.getSource() == Antelope) {
            lastPickedOrganism = new Antelope();
            frame.dispose();
        }
    }
}
