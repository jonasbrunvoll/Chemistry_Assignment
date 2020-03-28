import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class Main {
    static String atomicRadiiData = "/Users/jonaslarsson/IdeaProjects/Chemistry/src/csv/AtomicRadiiData";
    static String numericDensity = "/Users/jonaslarsson/IdeaProjects/Chemistry/src/csv/numericDensity";
    static String atomicWeigth ="/Users/jonaslarsson/IdeaProjects/Chemistry/src/csv/AtomicWeight";
    static String line;
    static String cvsSplitBy = ",";


    public static void main(String[] args) throws FileNotFoundException, ParseException {

        ArrayList<Atom> atoms = new ArrayList<>();
        ArrayList<NumericDensity> densityList = new ArrayList<>();
        ArrayList<String> weightList = new ArrayList<>();
        BufferedReader reader;


        //adds one atom from AtomicRadiiData to the ArrayList atoms
        try {
            reader = new BufferedReader(new FileReader(atomicRadiiData));
            while ((line = reader.readLine()) != null ){
                Atom atom = new Atom();
                String[] data = line.split(cvsSplitBy);
                //Creates an atom object
                atom.submitAtom(data);
                //Adds the object to the ArrayList atoms
                atoms.add(atom);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Reads in data form the numericDensity list.
        try {
             reader = new BufferedReader(new FileReader(numericDensity));
            while ((line = reader.readLine()) != null ){
                NumericDensity density = new NumericDensity();
                String[] data = line.split(cvsSplitBy);
                density.submitList(data);
                densityList.add(density);

            }
        } catch (IOException ex){
            ex.printStackTrace();
        }

        //Reads in atomic mass
        try {
            reader = new BufferedReader(new FileReader(atomicWeigth));
            while ((line = reader.readLine()) != null ){
                weightList.add(line);
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }


        //Calculate
        for (int i = 0; i < atoms.size(); i++){
            atoms.get(i).setAtomicMass(weightList.get(i));
            atoms.get(i).setVolum();
            atoms.get(i).setDensity();
            atoms.get(i).setDiscepancy(densityList.get(i).getDensity());
            atoms.get(i).setDistanceBetweenAtoms();

            //System.out.println(atoms.get(i).getCalculatedVolum());
            //System.out.println("\n" + (1+i)+  " Distance between atoms cal: " + atoms.get(i).getCalculatedDistanceBetweenAtoms() + ". Distance between atoms emp: " + atoms.get(i).getEmpiricalDistanceBetweenAtoms());
        }




        //System.out.println("\nAtomicNumber, Symbol, empericalValue, CalculatedValue, VanDerWaalsValue, CovalenSingel, CovalentTripel, Metallic:\n" + atoms.toString());
    }
}


