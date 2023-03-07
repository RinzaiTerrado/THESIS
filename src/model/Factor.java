package model;

public class Factor {
    String factor;
    String factVal;

    public Factor(String factor){
        this.factor = factor;
    }

    public String getFactor(){
        return factor;
    }

    public void setFactor(String factor){
        this.factor = factor;
    }

    public String getFactVal(){
        return factVal;
    }

    public void setFactVal(String factVal){
        this.factVal = factVal;
    }
}
