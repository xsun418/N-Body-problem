import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.*;
import java.util.*;
import java.util.List;

public class Motion extends JPanel{
    public Simulator simulator = new Simulator();
    List<Particle> particleArrayList= new ArrayList<>();

    /**
     *
     * @param args arguments from commandline
     */
    public void initComponents(String [] args){
        //create your simulation area and define it
        JFrame frame = new JFrame("Projectile Motion Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(768, 768);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        List<Particle> particleArrayList1 ;
        System.out.println(args[0]);
       particleArrayList1=getParticles(args);

       particleArrayList1.forEach(particle -> {
           System.out.println(particle.size);
           frame.add(simulator.setup(particle,particleArrayList));
       });
       frame.setVisible(true);

    }

    /**
     *
     * @param path We receive the path of the csv file
     * @return This method returns an arraylist of particle properties for every particle
     * @throws IOException
     */
    public List<Object[]> readData(String path) throws IOException {
        ArrayList<Object[] > list =new ArrayList<>();
        String file =path;
        String line;
        try  (BufferedReader br =
                      new BufferedReader(new FileReader(file))){
            //recursively read the comma seperated values into an arraylist, while splitting it with a comma
            while((line = br.readLine()) != null){
                list.add(line.split(","));
            }
        } catch (Exception e){
            //it is always advisable to catch any possible exceptions, And therefore use general
            //exception class,to catch any that may occur. You can however replace that with FileNotFoundException
            //which is the most likely here. We output it for logging
            System.out.println(e);
        }
        return list;
    }

    /**
     * is The main Method and starting point of our application
     * @param args receives commandline arguments
     */
    public static void main(String[] args) {
        new Motion().initComponents(args);

    }

    /**
     *
     * @param args We receive the file path, and assign them to an array
     * @return Particles. This Method returns a list of particles after reading the Csv
     * @see Particle
     * @see Motion
     */

    public List<Particle> getParticles(String[] args){
        try {

            if(args.length != 1) {
                System.out.println("Correct usage: Motion <AbsoluteCSV path>");
                System.exit(1);
                return null;
            }else {
                String filePath=args[0];
                //we call readData, and parse the file path to it, to receive the arraylist of particles
              List<Object[]> particles=readData(filePath);
                for (Object[] array:particles){
                    if (array.length<7) {
                        //Do not create particle if not all properties are specified
                        System.out.println("Particle not fully described!");
                       continue;
                    }else {
                        //recursively creating Particles, while parsing the characteristincs of our particle
                        particleArrayList.add(new Particle(array));
                     }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return particleArrayList;
        //we return our arraylist
    }

}

