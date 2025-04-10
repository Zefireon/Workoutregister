package com.example.chaosworkout;


public class Tutorial {
    private String tid;
    private String tname;
    private String tmuscle;
    private String tdesc;
    private String tvariant;
    private int tbasept;
    private String tdiff;

    public Tutorial() {
        super();
    }

    @Override
    public String toString() {
        return "Tutorial{" +
                "tid='" + tid + '\'' +
                ", tname='" + tname + '\'' +
                ", tmuscle='" + tmuscle + '\'' +
                ", tdesc='" + tdesc + '\'' +
                ", tvariant='" + tvariant + '\'' +
                ", tdifftype='" + tbasept + '\'' +
                ", tdiff=" + tdiff +
                '}';
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTmuscle() {
        return tmuscle;
    }

    public void setTmuscle(String tmuscle) {
        this.tmuscle = tmuscle;
    }

    public String getTdesc() {
        return tdesc;
    }

    public void setTdesc(String tdesc) {
        this.tdesc = tdesc;
    }

    public String getTvariant() {
        return tvariant;
    }

    public void setTvariant(String tvariant) {
        this.tvariant = tvariant;
    }

    public int getTbasept() {
        return tbasept;
    }

    public void setTbasept(int tdifftype) {
        this.tbasept = tdifftype;
    }

    public String getTdiff() {
        return tdiff;
    }

    public void setTdiff(String tdiff) {
        this.tdiff = tdiff;
    }
}
