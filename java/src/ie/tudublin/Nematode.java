package ie.tudublin;

import processing.core.PApplet;
import processing.data.TableRow;

public class Nematode extends PApplet{
    private String name;
    private int length;
    private boolean limbs;
    private String gender;
    private boolean eyes;

    float cx = width/2;
    float cy = height/2;
    
    public Nematode(TableRow tr)
    {
        this(
            tr.getString("name"), 
            tr.getInt("length"),      
            tr.getInt("limbs") == 1,
            tr.getString("gender"),
            tr.getInt("eyes")== 1       
        );
    }//default constructor ends

    public Nematode(String name, int length, boolean limbs, String gender, boolean eyes) {
        this.name = name;
        this.length = length;
        this.limbs = limbs;
        this.gender = gender;
        this.eyes = eyes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isLimbs() {
        return limbs;
    }

    public void setLimbs(boolean limbs) {
        this.limbs = limbs;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isEyes() {
        return eyes;
    }

    public void setEyes(boolean eyes) {
        this.eyes = eyes;
    }

    @Override
    public String toString() {
        return "Nematode [eyes = " + eyes + ", gender = " + gender + ", length = " + length + ", limbs = " + limbs + ", name = "
                + name + "]";
    }
    
    
}
