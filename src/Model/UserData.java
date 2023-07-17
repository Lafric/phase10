package Model;

public class UserData {
    private String nutzername = "";
    private int siege = 0;
    private int gespielteSpiele = 0;
    private double siegesrate = 0;

    public UserData(String nutzername, int gespielteSpiele, int siege) {
        this.nutzername = nutzername;
        this.gespielteSpiele = gespielteSpiele;
        this.siege = siege;
        if (gespielteSpiele == 0) {
            this.siegesrate = 0;
        } else {
            this.siegesrate = (double) siege / gespielteSpiele;
            this.siegesrate = Math.round(this.siegesrate * 100.0) / 100.0;
        }
    }

    
    /** 
     * @return String
     */
    public String getNutzername() {
        return nutzername;
    }

    public int getGespielteSpiele() {
        return gespielteSpiele;
    }

    public int getSiege() {
        return siege;
    }

    public double getSiegesrate() {
        return siegesrate;
    }
}