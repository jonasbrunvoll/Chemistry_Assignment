import java.util.ArrayList;

class Atom {

    //Read in object variables
    private String atomicNumber;
    private String symbolAtom;
    private String nameAtom;
    private String empericalValue;
    private String calculatedValue;
    private String vanDerWaalsValue;
    private String covalentSingle;
    private String covalentTriple;
    private String metallic;

    //Calculated object variables
    private String empericalDensity;
    private String empiricalVolum;
    private String empiricalDiscrepency;
    private String empiricalDistanceBetweenAtoms;
    private String calculatedDensity;
    private String calculatedVolum;
    private String calculatedDiscrepency;
    private String calculatedDistanceBetweenAtoms;

    //Get methods
    public String getAtomicNumber(){ return atomicNumber;}
    public String getSymbolAtom() { return symbolAtom; }
    public String getNameAtom() {
        return nameAtom;
    }
    public String getEmpericalValue() { return empericalValue; }
    public String getCalculatedValue() { return calculatedValue; }
    public String getVanDerWaalsValue() { return vanDerWaalsValue; }
    public String getCovalentSingle() { return covalentSingle; }
    public String getCovalentTriple() { return covalentTriple; }
    public String getMetallic() { return metallic;}
    public String getEmpericalDensity(){return empericalDensity;}
    public String getEmpiricalVolum(){return empiricalVolum;}
    public String getEmpiricalDiscrepency(){return empiricalDiscrepency;}
    public String getEmpiricalDistanceBetweenAtoms() { return empiricalDistanceBetweenAtoms;}
    public String getCalculatedDensity() { return calculatedDensity; }
    public String getCalculatedDiscrepency() { return calculatedDiscrepency; }
    public String getCalculatedDistanceBetweenAtoms() {return calculatedDistanceBetweenAtoms;}
    public String getCalculatedVolum(){return calculatedVolum;}

    public void submitAtom(String[] atomList){
        String[] atom = chechIfMissingColums(atomList);
        this.atomicNumber = atom[0];
        this.symbolAtom = atom[1];
        this.nameAtom = atom[2];
        this.empericalValue = atom[3];
        this.calculatedValue = atom[4];
        this.vanDerWaalsValue = atom[5];
        this.covalentSingle = atom[6];
        this.covalentTriple = atom[7];
        this.metallic = atom[8];

    }

    private String[] chechIfMissingColums(String[] data){
        ArrayList<String> chechList = new ArrayList<>();

        for (int i = 0; i < data.length; i++){
            chechList.add(data[i]);
        }

        int rows = Math.abs(chechList.size() - 9);
        if (rows < 9){
            chechList.add("no data");
        }
        String[] atom = new String[9];
        for (int i = 0; i < atom.length; i++){
            atom[i] = chechList.get(i);
        }
        return atom;
    }

    public String toString(){
        String message="\n"+atomicNumber+", "+symbolAtom+", " +nameAtom+", "+empericalValue+ ", "+calculatedValue+", "+vanDerWaalsValue+", "+
                covalentSingle+", "+covalentTriple+", "+metallic;
        return message;
    }
/*
    public void setEmpericalDensity(){
        if (this.empiricalVolum != "no data"){
            double eV = (Double.parseDouble(this.mass) / Double.parseDouble(this.empiricalVolum));
            this.empericalDensity = ""+eV;
        } else {
            this.empericalDensity = "no data";
        }
    }


 */
    public void setCalculatedDensity(){

    }

    public void setEmpiricalVolum(){

    }

    public void setCalculatedValue(String calculatedValue) {
        this.calculatedValue = calculatedValue;
    }
    /*
    def calculateEpiricalDensity(self):
        if(self.empirical_volume != "no data"):
            self.empirical_density = (self.mass / self.empirical_volume)
        else:
            self.empirical_density = "no data"

    def calculateCalculatedDensity(self):
        if(self.calculated_volume != "no data"):
            self.calculated_density = (self.mass / self.calculated_volume)
        else:
            self.calculated_density = "no data"

    def calculateEmpiricalVolume(self):
        if self.empirical_atomic_radius != "no data":
            self.empirical_volume = (4 / 3) * math.pi * math.pow(self.empirical_atomic_radius, 3)
        else:
            self.empirical_volume = "no data"

    def calculateCalculatedVolume(self):
        if self.calculated_atomic_radius != "no data":
            self.calculated_volume = (4 / 3) * math.pi * math.pow(self.calculated_atomic_radius, 3)
        else:
            self.calculated_volume = "no data"

    def setEmpiricalDiscrepency(self):
        if self.empirical_density != "no data":
            self.empirical_discrepancy = self.stated_density - self.empirical_density
        else:
            self.empirical_discrepancy = "no data"


    def setCalculatedDiscrepency(self):
        if self.calculated_density != "no data":
            self.calculated_discrepancy = self.stated_density - self.calculated_density
        else:
            self.calculated_discrepancy = "no data"


    def calculateEmpiricalDistanceBetweenAtoms(self):
        if(self.empirical_discrepancy != None):
            self.empirical_distance_between_atoms = ((self.mass / (math.pi * (4/3) * self.stated_density))**(1/3)) - self.empirical_atomic_radius

    def calculateCalculatedDistanceBetweenAtoms(self):
        if(self.calculated_discrepancy != None):
            self.calculated_distance_between_atoms = ((self.mass / (math.pi * (4/3) * self.stated_density))**(1/3)) - self.calculated_atomic_radius
     */

    //Set methods
    /*
    public void setAtomicNumber(String number){ this.atomicNumber = number; }
    public void setSymbolAtom(String symbolAtom) { this.symbolAtom = symbolAtom; }
    public void setNameAtom(String nameAtom) { this.nameAtom = nameAtom; }
    public void setEmpericalValue(String empericalValue) { this.empericalValue = empericalValue;}
    public void setCalculatedValue(String calculatedValue) { this.calculatedValue = calculatedValue; }
    public void setVanDerWaalsValue(String vanDerWaalsValue) { this.vanDerWaalsValue = vanDerWaalsValue; }
    public void setCovalentSingle(String covalentSingle) { this.covalentSingle = covalentSingle; }
    public void setCovalentTriple(String covalentTriple) { this.covalentTriple = covalentTriple; }
    public void setMetallic(String metallic) { this.metallic = metallic; }

     */
}
