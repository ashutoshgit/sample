package com.ashu.springbootproject.model;

import java.util.ArrayList;
import java.util.List;

public class Response {

    String status;
    List<String> msgList;

    public Response(){
        msgList = new ArrayList<>();
    }

    public Response(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<String> msgList) {
        this.msgList = msgList;
    }
}
