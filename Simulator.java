import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.Random;

public class Simulator extends JPanel{

    private int x;
    private int y;
    private double initialX;
    private double initialY;
    private double angle;
    private double velocity;
    private double xVelocity;
    private double yVelocity;
    private double time;
    private int size;
    Particle particleNew;
    List<Particle> particles;


    public void setSize(int size) {
        this.size = size;
    }

    public final double GRAVITY = 9.81;

    public JPanel setup(Particle particle, List<Particle> list){
        particles=list;
        System.out.println(particle.getX()+", Y"+particle.getY());
        this.particleNew=particle;
        x = particleNew.getX();
        y = particleNew.getY();
        System.out.println("Particle is "+particle.size);
        setSize(particleNew.getSize());
        initialX = particleNew.getInitXVelocity();
        initialY = particleNew.getInitYVelocity();
        angle = 45;
        velocity = 80;
        xVelocity = velocity * Math.cos(Math.toRadians(angle));
        yVelocity = velocity * Math.sin(Math.toRadians(angle));
        time = 0;

        repaint();
        return this;

    }

    public Simulator() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(768,768);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        draw(g2);
    }

    /**
     * We use this method to begin simulation, and draw the particles
     * @param g 2D graphics instantiated by the super class
     * @see JPanel
     */
    public void draw(Graphics2D g){
        particles.forEach(particle1 -> {
            try {

                System.out.println("Starting drawing!");
                System.out.println(particle1.x+", "+particle1.y+", "+particle1.size);
                g.fillOval(particle1.x, particle1.y, particle1.size, particle1.size);
                Random r = new Random();
                Color randomColor = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
                g.setColor(randomColor);
                System.out.println("Moving Particle "+ particle1.name);
                particle1.moveParticle(particle1,particles);


                    } catch (Exception e) {
                e.printStackTrace();
            }
        });



    }
    public int toInt(Double dou){
        int integer= 0;
        try {
            integer =Integer.parseInt(String.valueOf(Math.round(dou)));
        } catch (NumberFormatException e) {

        }
        return integer;
    }
}