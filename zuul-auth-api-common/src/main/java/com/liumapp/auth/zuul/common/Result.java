package com.liumapp.auth.zuul.common;

import java.io.Serializable;

/**
 * (描述用途)
 *
 * @author Linrry
 * @date 2018-04-10 下午 17:38
 */
public class Result implements Serializable {
    /* 结果状态 */
    private String code;
    /* 结果信息 */
    private String msg;
    /* 结果数据对象 */
    private Object data;

    private long count;


    public Result() {
    }

    public static Result ok(){

        return new Result().setMsg("success").setCode("0");

    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public Result setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * 检查是否成功
     *
     * @return
     */
    public boolean checkOk() {
        return msg==null||"000".equals(msg);
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
