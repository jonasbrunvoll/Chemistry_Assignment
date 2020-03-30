import sun.applet.AppletResourceLoader;

import javax.xml.stream.events.StartDocument;
import java.util.ArrayList;

 class NumericDensity{

     private final int ROWSIZE = 9;

    //Object variables
    private String nameAtom;
    private String symbolAtom;
    private double density;
    private double zettaUnit;
    private int atomicNumber;
    private String description;
    private String hardness;
    private String color;
    private String notes;

     public void submitList(String[] densities){
         String[] densityList = chechIfMissingColums(densities);
         this.nameAtom = densityList[0];
         this.symbolAtom = densityList[1];
         this.density = Double.parseDouble(densityList[2]);
         this.zettaUnit = Double.parseDouble(densityList[3]);
         this.atomicNumber = Integer.parseInt(densityList[4]);
         this.description = densityList[5];
         this.hardness = densityList[6];
         this.color = densityList[7];
         this.notes = densityList[8];
     }

    //Get methods
    public double getDensity(){ return density; }
    public double getZettaUnit(){ return zettaUnit; }
    public int getAtomicNumber(){ return atomicNumber; }

     public String toString(){
         String message = "\n"+nameAtom+", "+symbolAtom+", "+density+","+zettaUnit+", "+atomicNumber+", "+description+", "+hardness+", "+color+", "+notes;
         return message ;
     }

    //Method to write 'no data' where the numericDensity is missing columns
     private String[] chechIfMissingColums(String[] numericDensityValue){
        ArrayList<String> helpArray = new ArrayList<>();

        //Copies data
        for (int i = 0; i < numericDensityValue.length; i++){
            helpArray.add(numericDensityValue[i]);
        }

        //adds "no data" in missing columns
        fillNoData(helpArray, helpArray.size());

        //fills empty cpolumns with "no data"
        String[] density = new String[ROWSIZE];
        for (int i = 0; i < density.length; i++){
            if (helpArray.get(i).equals("")){
                density[i] ="no data";
            } else {
                density[i] = helpArray.get(i);
            }

        }
        return density;
    }

    private void fillNoData(ArrayList<String> helpArray, int stopAfter){
        for (int i = 0; i < stopAfter; i ++){
            helpArray.add("no data");
        }
    }


}



