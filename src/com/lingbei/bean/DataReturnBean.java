package com.lingbei.bean;

import java.util.List;

/**
 * Created by gxy on 2016/12/4.
 */
public class DataReturnBean<T> {
    private int result=0;
    private String message="";
    private List<T> data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
