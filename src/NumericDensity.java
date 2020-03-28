import java.util.ArrayList;

class NumericDensity {

    //Read in object variables
    private String nameAtom;
    private String symbolAtom;
    private String density;
    private String zettaUnit;
    private String atomicNumber;
    private String description;
    private String hardness;
    private String color;
    private String notes;

    //Get
    public String getDensity(){
        return density;
    }


    public void submitList(String[] densities){
        String[] densityList = chechIfMissingColums(densities);
        this.nameAtom = densityList[0];
        this.symbolAtom = densityList[1];
        this.density = densityList[2];
        this.zettaUnit = densityList[3];
        this.atomicNumber = densityList[4];
        this.description = densityList[5];
        this.hardness = densityList[6];
        this.color = densityList[7];
        this.notes = densityList[8];
    }

    private String[] chechIfMissingColums(String[] data){
        ArrayList<String> chechList = new ArrayList<>();
        for (int i = 0; i < data.length; i++){
            chechList.add(data[i]);
        }
        int rows = chechList.size();

        fillNoData(chechList, rows);

        String[] density = new String[9];
        //|| chechList.get(i).equals("Unknown")
        for (int i = 0; i < density.length; i++){
            if (chechList.get(i).equals("")){
                density[i] ="no data";
            } else {
                density[i] = chechList.get(i);
            }

        }
        return density;
    }

    private void fillNoData(ArrayList<String> chechList, int rows){
        for (int i = 0; i < rows; i ++){
            chechList.add("no data");
        }
    }
    public String toString(){
        String message = "\n"+nameAtom+", "+symbolAtom+", "+density+","+zettaUnit+", "+atomicNumber+", "+description+", "+hardness+", "+color+", "+notes;
        return message ;
    }
}
