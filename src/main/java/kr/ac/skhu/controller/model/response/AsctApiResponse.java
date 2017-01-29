package kr.ac.skhu.controller.model.response;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Manki Kim on 2016. 12. 30..
 */
public class AsctApiResponse<T> {
    //Common
    public static final Integer OK = 200;

    public static final Integer INVALID_USERPASSWORD = 1002;
    public static final Integer INVALID_COOKIE = 1003;
    public static final Integer DUPLICATE_LOGINID = 1004;
    public static final Integer TOKEN_LESS = 1005;

    public static final Integer EXCEPTION = 3001;
    public static final Integer NOT_FOUND = 3002;
    public static final Integer INVALID_STATUS = 3003;

    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private Integer code;
    private T result;
    private String statusDate;

    public AsctApiResponse() {
        this.code = OK;
        this.statusDate = new SimpleDateFormat(TIME_FORMAT).format(new Date());
    }

    public AsctApiResponse(Integer code) {
        this.code = code;
        this.statusDate = new SimpleDateFormat(TIME_FORMAT).format(new Date());
    }

    public AsctApiResponse(T result) {
        this.code = OK;
        this.result = result;
        this.statusDate = new SimpleDateFormat(TIME_FORMAT).format(new Date());
    }

    public AsctApiResponse(Integer code, T result) {
        this.code = code;
        this.result = result;
        this.statusDate = new SimpleDateFormat(TIME_FORMAT).format(new Date());
    }

    public Integer getCode() {
        return code;
    }

    public T getResult() {
        return result;
    }

    public String getStatusDate(){ return statusDate; }
}
