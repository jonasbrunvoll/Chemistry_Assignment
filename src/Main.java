import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    //Tables to read data from
    static String atomicRadiiData = "src/tables/AtomicRadii";
    static String numericDensity = "src/tables/numericDensities";
    static String atomicWeigth ="src/tables/AtomicWeight";

    BufferedReader reader;
    static String line;
    static String cvsSplitBy = ",";

    //Constants used
    double picometer_T0_CM = Math.pow(10,-10);                   //converts from pico meter to meter
    double AtomWeigtInGram = 1.66053906660 * Math.pow(10,-24.0); //atom weight in gram
    double AvogadrosNumber = 6.022 * Math.pow(10, 23);           //Avogadros number


    //create atoms from the data found in AtomicRadiiData
    public ArrayList getAtomicRadiiData() {
        ArrayList<Atom> atoms = new ArrayList<>();

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
        return atoms;
    }
    //Reads in data form the numericDensity document.
    public ArrayList getNumericDensity(){
        ArrayList<NumericDensity> densityList = new ArrayList<>();
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


        return densityList;
    }
    //Reads in atomic mass
    public double[] getAtomicMass(){
        ArrayList<Double> weightListArray = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(atomicWeigth));
            while ((line = reader.readLine()) != null ){
                String[] data = line.split(cvsSplitBy);
                double weight = Double.parseDouble(data[3]);
                weightListArray.add(weight);
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        double[] weightList = new double[weightListArray.size()];
        for (int i = 0; i < weightList.length; i++){
            weightList[i] = weightListArray.get(i);
        }
        return weightList;
    }



    //Task 1. Calculates volum out of a chosen radius
    private double calculateVolum(double radius){
        if (radius != -1.0) {
            radius = radius * picometer_T0_CM;  //Transform radius from pico meter to centi meter.
        }
        return (4 * Math.PI * Math.pow(radius, 3));
    }
    public double calculateDensity(double weight, double radius){
        double density = -1;
        double volum = calculateVolum(radius);
        weight = weight * AtomWeigtInGram;

        if(weight != -1.0 || radius != -1.0){
            density = weight * 3 / volum;
        }
        return density;
    }

    //Task 2
    public double atomsPerVolum(double atomicMass, double density){
        double atomsPerVolum = -1.0;
        if (density != -1.0 || atomicMass != -1.0){
            atomsPerVolum = (density / atomicMass) * AvogadrosNumber * Math.pow(10, -21) ;
        }
        return atomsPerVolum;
    }


    //Compares and finds the difference of two values
    public double error(double a, double b){
        return Math.sqrt(Math.pow(a - b, 2));
    }






    public static void main(String[] args) {
        Main main = new Main();
        ArrayList<Atom> atoms = main.getAtomicRadiiData();
        ArrayList<NumericDensity> densityList = main.getNumericDensity();
        double[] weightList = main.getAtomicMass();


        //Ensure that the lists are sorted in ascending order by atomic number
        atoms.sort(Comparator.comparing(Atom::getAtomicNumber));
        densityList.sort(Comparator.comparing(NumericDensity::getAtomicNumber));



        //Task 1. Calculate density and compare the results with the density from numericDensities.txt.
        double density;
        double error;

        System.out.println("\nTask1__Calculate the density using Middle Point Value of calculated and empirical radius");
        for (int i = 0; i < atoms.size(); i++){
            if (atoms.get(i).getCalculatedRadius() != -1.0 || atoms.get(i).getEmpiricalRadius() != -1.0){
                density = main.calculateDensity(weightList[i], atoms.get(i).getTheMiddleValue());
                error = main.error(density, densityList.get(i).getDensity());
                System.out.println( atoms.get(i).getAtomicNumber() + ".   "+ String.format("Density = %.6f", density) + " g/cm^3" +  String.format("    Error: %.6f", error) + " g/cm^3");

            } else {
                System.out.println(atoms.get(i).getAtomicNumber() + ".   Unknown.");
            }
        }

        //Task 2. Find the distances between the atoms
        System.out.println("\n__Task2. Find the distance between atoms using given Zetta Units");
        for (int i = 0; i < atoms.size(); i++){
            if (densityList.get(i).getZettaUnit() != -1.0){
                density = densityList.get(i).getDensity();
                double distanceBetweenAtoms = main.atomsPerVolum(weightList[i], density);
                error = main.error(distanceBetweenAtoms, densityList.get(i).getZettaUnit());
                System.out.println( atoms.get(i).getAtomicNumber() +  String.format("   Distance between atoms: %.6f Atoms/cm^3" , distanceBetweenAtoms) + String.format(" Error:  %.6f Atoms/cm^3", + error));
            } else {
                System.out.println(atoms.get(i).getAtomicNumber() + "   Distance between atoms unknown.");
            }

        }
/*
        System.out.println("\nTask1__Finding__EmpiricalDensity");
        for (int i = 0; i < atoms.size(); i++){
            if (atoms.get(i).getCalculatedRadius() != -1.0 || atoms.get(i).getEmpericalRadius() != -1.0) {
                density = main.calculateDensity(weightList[i], atoms.get(i).getCalculatedRadius());
                error = main.error(density, densityList.get(i).getDensity());
                System.out.println(atoms.get(i).getAtomicNumber() + "." + String.format("    EmpiricalDensity: %.6f", density) + String.format("g/cm^3    Error: %.6f ", +error) + "g/cm^3");
            } else {
                System.out.println(atoms.get(i).getAtomicNumber() + ".    EmpiricalDensity: Unknown" );
            }
        }

        //with empiricalRadius
        System.out.println("\nTask1__Finding__Calculated density");
        for (int i = 0; i < atoms.size(); i++){
            if (atoms.get(i).getCalculatedRadius() != -1.0 || atoms.get(i).getEmpericalRadius() != -1.0) {
                density = main.calculateDensity(weightList[i], atoms.get(i).getEmpericalRadius());
                error = main.error(density, densityList.get(i).getDensity());
                System.out.println(atoms.get(i).getAtomicNumber() + "." + String.format("    Calculated Density: %.6f ", density) + String.format("g/cm^3    Error: %.6f ", error) + "g/cm^3");
            } else {
                System.out.println(atoms.get(i).getAtomicNumber() + ".    CalculatedDensity: Unknown");
            }
        }
        System.out.println("\nTask2__Distance between atoms using Middle Point between calculated and empirical radius");
        for (int i = 0; i < atoms.size(); i++){
            if (densityList.get(i).getZettaUnit() != -1.0){
                density  = main.calculateDensity(weightList[i], atoms.get(i).getTheMiddleValue());
                double distanceBetweenAtoms = main.atomsPerVolum(density, weightList[i]);
                error = main.error(distanceBetweenAtoms, atoms.get(i).getTheMiddleValue());
                System.out.println( atoms.get(i).getAtomicNumber() +  String.format("    Distance between atoms: %.6f " , distanceBetweenAtoms) + String.format("Atoms/cm^3.    Error:  %.6f ", + error) + "Atoms/cm^3");
            } else {
                System.out.println(atoms.get(i).getAtomicNumber() + "    Distance between atoms: unknown.");
            }


        }

 */
    }
}


