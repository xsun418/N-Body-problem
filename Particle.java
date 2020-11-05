import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

/**
 * @author ThisAuthor
 * @version 1.0
 * This class represents a single particle. A new particle is created when its constructor is called.
 */
public class Particle {
    Double initX,initY,mass;
    int x,y,size;
    String name;

    /**
     *
     * @param properties The properties describing our particle
     */
    Particle(Object[] properties) {
        System.out.println("This particle has "+properties.length+" properties");
        setName(String.valueOf(properties[0]));
        setLocation(toInt(properties[2].toString()), toInt(properties[3].toString()));
        setInitXVelocity(toDouble(properties[4].toString()));
        setInitYVelocity(toDouble(properties[5].toString()));
        setSize(toInt(properties[6].toString()));
        setMass(toDouble(properties[1].toString()));


    }

    /**
     *
     * @param string Converts Strings to int, while removing the trailing . as in the input file.
     *               It is assumed all input files will match this format.
     * @return The converted Integer. This method was created to help explicitly convert string to integer.
     * t is assumed that all the Strings are numerical, and thus can be converted.
     * @throws NumberFormatException
     */
    public int toInt(String string){
        int integer= 0;
        try {
            integer = Integer.parseInt(string.substring(0, Math.min(string.length(), 10)));
        } catch (NumberFormatException e) {

        }
        return integer;
    }

    /**
     *
     * @param string Converts Strings to int, while removing the trailing . as in the input file.
     *               It is assumed all input files will match this format.
     * @return Converted Double
     */
    public Double toDouble(String string){
        Double doubled=Double.parseDouble(string.substring(0, Math.min(string.length(), 10)));
        return doubled;
    }

    /**
     *
     * @param x The X-coordinates of this particle
     * @param y The Y coordinates of this particle
     */
    public void setLocation(int x, int y){
     this.x=x;
     this.y=y;
}


    public int getY() {
        return this.y;
    }

    public void setInitXVelocity(Double initX) {
        this.initX = initX;
    }

    public Double getInitYVelocity() {
        return initY;
    }

    public void setInitYVelocity(Double initY) {
        this.initY = initY;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    /**
     * this method moves the specified particle, in relation to other particles in the list
     * @param particle The particular particle we want to move
     * @param particleList The list of all the particles
     */
    public void moveParticle(Particle particle,List<Particle> particleList){
        Simulator simulator=new Simulator();
        //look for the particle we want to move from the list of particles, If we find it, we Move it
        //else, we dont move any particle
        particleList.forEach(particle1 -> {
            if (particle.name.equals(particle1.name)){
                ActionListener listener = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //We get the particle that is just next to the particle we need to move
                      Particle  newParticle=particleList.get(particleList.indexOf(particle)+1);
                        simulator.repaint();
                    }
                };
                Timer timer = new Timer(250, listener);
                timer.start();
            }
        });


    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getInitXVelocity() {
        return this.initX;
    }

    public int getX() {
        return this.x;
    }



    @Override
    public int hashCode() {
        return Objects.hash(x, y, initX, initY, size, mass, name);
    }

    @Override
    public String toString() {
        return "Particle{}";
    }
}
