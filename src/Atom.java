import java.awt.image.DataBufferDouble;
import java.util.ArrayList;
import java.util.zip.DeflaterOutputStream;


//Atomic mass = g / mol?
// Massen i gram til en atom-masse-enhet har blitt målt til 1.660 x 10^-24g


import java.util.ArrayList;

class Atom {

    static String CANNOTCALCULATE = "can not calculate";
    static double pico_to_centi = Math.pow(10, 10);  //Convert from piko magnitude to centi magnitude
    static double U = 1.66 * Math.pow(10, -27);  // Atomic weight


    //Read in object variables
    private String atomicNumber;
    private String symbolAtom;
    private String nameAtom;
    private String empericalRadius;
    private String calculatedRadius;
    private String vanDerWaalsValue;
    private String covalentSingle;
    private String covalentTriple;
    private String metallic;
    private String atomicMass;

    //Calculated object variables
    private String empericalDensity = CANNOTCALCULATE;
    private String empiricalVolum = CANNOTCALCULATE;
    private String empiricalDiscrepency;
    private String empiricalDistanceBetweenAtoms;

    private String calculatedDensity = CANNOTCALCULATE;
    private String calculatedVolum = CANNOTCALCULATE;
    private String calculatedDiscrepency;
    private String calculatedDistanceBetweenAtoms;

    //Get methods
    public String getAtomicNumber() { return atomicNumber; }
    public String getSymbolAtom() { return symbolAtom; }
    public String getNameAtom() { return nameAtom; }
    public String getEmpericalRadius() { return empericalRadius; }
    public String getCalculatedRadius() { return calculatedRadius; }
    public String getVanDerWaalsValue() { return vanDerWaalsValue; }
    public String getCovalentSingle() { return covalentSingle; }
    public String getCovalentTriple() { return covalentTriple; }
    public String getMetallic() { return metallic; }
    public String getAtomicMass(){ return atomicMass; }
    public String getEmpericalDensity() { return empericalDensity; }
    public String getEmpiricalVolum() { return empiricalVolum; }
    public String getEmpiricalDiscrepency() { return empiricalDiscrepency; }
    public String getEmpiricalDistanceBetweenAtoms() { return empiricalDistanceBetweenAtoms; }
    public String getCalculatedDensity() { return calculatedDensity; }
    public String getCalculatedDiscrepency() { return calculatedDiscrepency; }
    public String getCalculatedDistanceBetweenAtoms() { return calculatedDistanceBetweenAtoms; }
    public String getCalculatedVolum() { return calculatedVolum; }

    //Set methods
    public void setAtomicMass(String mass) {
        if (dataExist(mass)){
            this.atomicMass = Double.toString(Double.parseDouble(mass) * U * 1000);
        }
        this.atomicMass = mass;
    }

    public void setVolum(){
        if (dataExist(empericalRadius)) {
            double eV = (4 / 3) * Math.PI * Math.pow(Double.parseDouble(this.empericalRadius), 3);
            this.empiricalVolum = Double.toString(eV);
        }
        if (dataExist(this.calculatedRadius)) {
            double cV = (4 / 3) * Math.PI * Math.pow(Double.parseDouble(this.calculatedRadius), 3);
            this.calculatedVolum = Double.toString(cV);
        }
    }

    public void setDensity(){
        if (dataExist(this.empiricalVolum) && dataExist(this.atomicMass)) {
            if (!(Double.parseDouble(this.empiricalVolum) == 0.0)) {
                double eD = (Double.parseDouble(this.atomicMass) / Double.parseDouble(this.empiricalVolum));
                this.empericalDensity = Double.toString(eD);
            }
        }
        if (dataExist(this.calculatedVolum) && dataExist(this.atomicMass)) {
            if (!(Double.parseDouble(this.calculatedVolum) == 0.0)) {
                double cD = (Double.parseDouble(this.atomicMass) / Double.parseDouble(this.calculatedVolum));
                this.calculatedDensity = Double.toString(cD);
            }
        }


    }

    public void setDiscepancy(String stated_density){
        if (dataExist(empericalDensity)) {

            this.empiricalDiscrepency = Double.toString(Double.parseDouble(stated_density) - Double.parseDouble(this.empericalDensity));
        } else {
            this.empiricalDiscrepency = "no data";
        }

        if (dataExist(this.calculatedDensity)) {
            this.calculatedDiscrepency = Double.toString(Double.parseDouble(stated_density) - Double.parseDouble(this.calculatedDensity));
        } else {
            this.calculatedDiscrepency = "no data";
        }
    }

    public void setDistanceBetweenAtoms() {
        if (dataExist(this.empiricalDiscrepency) && dataExist(this.atomicMass) && dataExist(this.empericalRadius)) {
            if (this.empiricalDiscrepency != null && this.atomicMass != null && this.empericalRadius != null){
                double mass = Double.parseDouble(this.atomicMass);
                double discrepency = Double.parseDouble(this.empiricalDiscrepency);
                double radius = Double.parseDouble(this.empericalRadius);
                this.empiricalDistanceBetweenAtoms = Double.toString(Math.pow(mass / (Math.PI * 4 / 3) * discrepency, (1 / 3)) - radius);
            }
        }

        if (dataExist(this.calculatedDiscrepency) && dataExist(this.atomicMass) && dataExist(this.calculatedRadius) ) {
            if (this.calculatedDiscrepency != null && this.atomicMass != null && this.empericalRadius != null) {
                double mass = Double.parseDouble(this.atomicMass);
                double discrepency = Double.parseDouble(this.calculatedDiscrepency);
                double radius = Double.parseDouble(this.calculatedRadius);
                this.calculatedDistanceBetweenAtoms = Double.toString(Math.pow(mass / (Math.PI * 4 / 3) * discrepency, (1 / 3)) - radius);
            }
        }
    }

    //initialize the read in objectvariables.
    public void submitAtom(String[] atomList) {
        String[] atom = chechIfMissingColums(atomList);
        this.atomicNumber = atom[0];
        this.symbolAtom = atom[1];
        this.nameAtom = atom[2];
        if (dataExist(atom[3])){
            this.empericalRadius = Double.toString(Double.parseDouble(atom[3]) / pico_to_centi);
        } else {
            this.empericalRadius = atom[3];
        }
        if (dataExist(atom[4])){
            this.calculatedRadius = Double.toString(Double.parseDouble(atom[4]) / pico_to_centi);
        } else {
            this.calculatedRadius = atom[4];
        }
        this.vanDerWaalsValue = atom[5];
        this.covalentSingle = atom[6];
        this.covalentTriple = atom[7];
        this.metallic = atom[8];

    }

    //checks if there exist data inside column.
    private boolean dataExist(String input) {
        if (input.equals("no data")|| input.equals("can not calculate") || input.equals("Unknown")) {
            return false;
        }
        return true;
    }

    //checks if there is missing colums.
    private String[] chechIfMissingColums(String[] data) {
        ArrayList<String> chechList = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            chechList.add(data[i]);
        }

        int rows = Math.abs(chechList.size() - 9);
        if (rows < 9) {
            chechList.add("no data");
        }
        String[] atom = new String[9];
        for (int i = 0; i < atom.length; i++) {
            atom[i] = chechList.get(i);
        }
        return atom;
    }


    public String toString() {
        String message = "\n" + atomicNumber + ", " + symbolAtom + ", " + nameAtom + ", " + empericalRadius + ", " + calculatedRadius + ", " + vanDerWaalsValue + ", " +
                covalentSingle + ", " + covalentTriple + ", " + metallic;
        return message;
    }

}
