package jlwcrews.flaggame;

import java.io.Serializable;

//container class for the flag object.
public class Flag implements Serializable{

    //serialVersionUID is necessary in order to be able to serialize the Flag
    //objects between the client and server
    //Otherwise, standard getters/setters, several of which are never used
    private static final long serialVersionUID = 1L;
    private int flag_id;
    private String flag;
    private String country;

    public int getFlag_id() {
        return flag_id;
    }

    public void setFlag_id(int flag_id) {
        this.flag_id = flag_id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String toString(){
        return this.flag_id + " " + this.country + " " + this.flag;
    }
}