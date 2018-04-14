package jlwcrews.flaggameserver;

public class Flag {

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
