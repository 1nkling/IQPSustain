package com.example.helloworld.Struct;

/**
 * Created by danso on 12/11/2016.
 */

public class Question {

    private String q;
    private int id;
    private String respType;
    private double maxScore;
    private String rec;
    private boolean isLowGood;

    public String getRespType() {
        return respType;
    }
    public String getQ() {
        return q;
    }

    public int getId() {
        return id;
    }

    public double getMaxScore() {
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
}
