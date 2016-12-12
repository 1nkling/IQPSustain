package com.example.helloworld.Struct;

/**
 * Created by danso on 12/11/2016.
 */

public class Question {

    private String q;
    private int id;
    private String respType;
    private int respNum;
    private String rec;

    public String getRespType() {
        return respType;
    }
    public String getQ() {
        return q;
    }

    public int getId() {
        return id;
    }

    public int getRespNum() {
        return respNum;
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

    public void setRespNum(int respNum) {
        this.respNum = respNum;
    }

    public void setRec(String rec) {
        this.rec = rec;
    }

    public String toString() {
        return q + respType + respNum + rec;
    }
}
