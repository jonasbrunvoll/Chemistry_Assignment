import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static String atomicRadiiData = "/Users/jonaslarsson/IdeaProjects/Chemistry/src/csv/AtomicRadiiData.csv";
    static String numericDensity = "/Users/jonaslarsson/IdeaProjects/Chemistry/src/csv/numericDensity.csv";
    static String line;
    static String cvsSplitBy = ",";


    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Atom> atoms = new ArrayList<>();
        ArrayList<NumericDensity> densityList = new ArrayList<>();
        BufferedReader reader;


        //adds one atom from AtomicRadiiData.csv to the ArrayList atoms
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

        //Compare the calculated atom values to the second table.
        //Write the results to a third csv file. Also print the results out.

        System.out.println(atoms.get(0).getSymbolAtom());

        System.out.println("\nAtomicNumber, Symbol, empericalValue, CalculatedValue, VanDerWaalsValue, CovalenSingel, CovalentTripel, Metallic:\n" + atoms.toString());
        System.out.println("\nName, Symbol, Density (g/cm³), number of atoms per volume unit (Zetta-atoms/cm³), Atomic number, Description/Mohs' hardness, Color, Notes\n" + densityList.toString());
    }
}


