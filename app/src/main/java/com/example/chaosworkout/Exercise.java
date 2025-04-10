package com.example.chaosworkout;



public class Exercise {

    private String eid;
    private String ename;
    private String emuscle;
    private int ediff;
    private int ebase;
    private int erep;
    private int eduration;
    private int eyear;
    private int emonth;
    private int eweek;
    private int eday;

    @Override
    public String toString() {
        return "Exercise{" +
                "eid='" + eid + '\'' +
                ", ename='" + ename + '\'' +
                ", emuscle='" + emuscle + '\'' +
                ", ediff=" + ediff +
                ", ebase='" + ebase + '\'' +
                ", erep=" + erep +
                ", eduration=" + eduration +
                ", eyear=" + eyear +
                ", emonth=" + emonth +
                ", eweek=" + eweek +
                ", eday=" + eday +
                '}';
    }




    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEmuscle() {
        return emuscle;
    }

    public void setEmuscle(String emuscle) {
        this.emuscle = emuscle;
    }

    public int getEdiff() {
        return ediff;
    }

    public void setEdiff(int ediff) {
        this.ediff = ediff;
    }

    public int getEbase() {
        return ebase;
    }

    public void setEbase(int ebase) {
        this.ebase = ebase;
    }

    public int getErep() {
        return erep;
    }

    public void setErep(int erep) {
        this.erep = erep;
    }

    public int getEduration() {
        return eduration;
    }

    public void setEduration(int eduration) {
        this.eduration = eduration;
    }

    public int getEyear() {
        return eyear;
    }

    public void setEyear(int eyear) {
        this.eyear = eyear;
    }

    public int getEday() {
        return eday;
    }

    public void setEday(int eday) {
        this.eday = eday;
    }

    public int getEmonth() {
        return emonth;
    }

    public void setEmonth(int emonth) {
        this.emonth = emonth;
    }

    public int getEweek() {
        return eweek;
    }

    public void setEweek(int eweek) {
        this.eweek = eweek;
    }

}
