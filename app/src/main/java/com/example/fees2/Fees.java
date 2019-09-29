package com.example.fees2;

public class Fees {

    public int general;
    public int oms;
    public int obc;
    public int sc;
    public int st;

    public Fees(int general, int oms, int obc, int sc, int st) {
        this.general = general;
        this.oms = oms;
        this.obc = obc;
        this.sc = sc;
        this.st = st;
    }

    public Fees() {
    }


    public int getGeneral() {
        return general;
    }

    public void setGeneral(int general) {
        this.general = general;
    }

    public int getOms() {
        return oms;
    }

    public void setOms(int oms) {
        this.oms = oms;
    }

    public int getObc() {
        return obc;
    }

    public void setObc(int obc) {
        this.obc = obc;
    }

    public int getSc() {
        return sc;
    }

    public void setSc(int sc) {
        this.sc = sc;
    }

    public int getSt() {
        return st;
    }

    public void setSt(int st) {
        this.st = st;
    }

    @Override
    public String toString() {
        return "Fees{" +
                "general=" + general +
                ", oms=" + oms +
                ", obc=" + obc +
                ", sc=" + sc +
                ", st=" + st +
                '}';
    }
}

