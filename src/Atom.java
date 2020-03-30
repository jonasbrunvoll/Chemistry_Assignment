class Atom {

    //Read in object variables
    private int atomicNumber;
    private String symbolAtom;
    private String nameAtom;
    private double empiricalRadius;
    private double calculatedRadius;
    private double vanDerWaalsValue;
    private double covalentSingle;
    private double covalentTriple;
    private double metallic;


    //initialize the read in objectvariables.
    public void submitAtom(String[] atom) {
        this.atomicNumber = Integer.parseInt(atom[0]);
        this.symbolAtom = atom[1];
        this.nameAtom = atom[2];
        this.empiricalRadius = Double.parseDouble(atom[3]);
        this.calculatedRadius = Double.parseDouble(atom[4]);
        this.vanDerWaalsValue = Double.parseDouble(atom[5]);
        this.covalentSingle = Double.parseDouble(atom[6]);
        this.covalentTriple = Double.parseDouble(atom[7]);
        this.metallic = Double.parseDouble(atom[8]);
    }

    //Get methods
    public int getAtomicNumber() { return atomicNumber; }
    public String getSymbolAtom() { return symbolAtom; }
    public String getNameAtom() { return nameAtom; }
    public double getEmpiricalRadius() { return empiricalRadius; }
    public double getCalculatedRadius() { return calculatedRadius; }
    public double getVanDerWaalsValue() { return vanDerWaalsValue; }
    public double getCovalentSingle() { return covalentSingle; }
    public double getCovalentTriple() { return covalentTriple; }
    public double getMetallic() { return metallic; }

    public double getTheMiddleValue(){
        double middleValue = Math.sqrt(Math.pow(this.empiricalRadius + this.calculatedRadius, 2));
        return middleValue;
    }

    public String toString() {
        String message = "\n" + this.atomicNumber + ", " + this.symbolAtom + ", " + this.nameAtom;
        return message;
    }
}