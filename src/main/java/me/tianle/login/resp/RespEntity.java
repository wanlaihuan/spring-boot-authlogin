package me.tianle.login.resp;

public class RespEntity {

    private int code;
    private String msg;
    private Object results;

    public RespEntity(RespCode respCode) {
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public RespEntity(RespCode respCode, Object results) {
        this(respCode);
        this.results = results;
    }

    public void setCode(int code){
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setResults(Object results){
        this.results = results;
    }

    public Object getResults() {
        return this.results;
    }
}
