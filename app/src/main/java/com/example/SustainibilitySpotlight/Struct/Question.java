package com.example.SustainibilitySpotlight.Struct;

/**
 * Created by danso on 12/11/2016.
 */

public class Question {

    private String q;
    private int id;
    private String respType;
    private int maxScore;
    private String rec;
    private boolean isLowGood;
    private String dimension;
    private int famID;
    private int famType;
    private int myFamType;

    public int getMyFamType() {
        return myFamType;
    }

    public void setMyFamType(int myFamType) {
        this.myFamType = myFamType;
    }

    public int getFamType() {
        return famType;
    }

    public void setFamType(int famType) {
        this.famType = famType;
    }

    public String getRespType() {
        return respType;
    }
    public String getQ() {
        return q;
    }

    public int getId() {
        return id;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public String getRec() {
        return rec;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRespType(String respType) {
        this.respType = respType;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public void setRec(String rec) {
        this.rec = rec;
    }

    public String toString() {
        return q + respType + maxScore + rec;
    }

    public boolean isLowGood() {
        return isLowGood;
    }

    public void setLowGood(boolean lowGood) {
        isLowGood = lowGood;
    }

    public void setDimension(String dim){
        this.dimension = dim;
    }

    public String getDimension(){
        return this.dimension;
    }

    public int getFamID() {
        return famID;
    }

    public void setFamID(int famID) {
        this.famID = famID;
    }
}
