package kr.or.dgit.sql_lite_study;

/**
 * Created by HKM on 2017-06-16.
 */

public class Dic {
    private int id;
    private String engStr;
    private String hanStr;

    public Dic(int id, String engStr, String hanStr) {
        this.id = id;
        this.engStr = engStr;
        this.hanStr = hanStr;
    }

    public Dic(String engStr, String hanStr) {
        this.engStr = engStr;
        this.hanStr = hanStr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEngStr() {
        return engStr;
    }

    public void setEngStr(String engStr) {
        this.engStr = engStr;
    }

    public String getHanStr() {
        return hanStr;
    }

    public void setHanStr(String hanStr) {
        this.hanStr = hanStr;
    }
}
