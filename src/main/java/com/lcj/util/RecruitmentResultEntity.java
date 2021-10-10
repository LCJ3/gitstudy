package com.lcj.util;



/**
 * @description:
 * @projectName: githubdemopractice
 * @see: com.lcj.util
 * @author: lichunjiang
 * @createTime: 2021/10/10 20:50
 * @version: 1.0
 */
public class RecruitmentResultEntity {

    private boolean flag;
    private Integer code;
    private String message;
    private Object data;
    private Long count;

    private RecruitmentResultEntity() {
    }

    private RecruitmentResultEntity(boolean flag, Integer code, String message) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    private RecruitmentResultEntity(boolean flag, Integer code, String message, Object data) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private RecruitmentResultEntity(boolean flag, Integer code, String message, Object data, Long count) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
        this.count = count;
    }

    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public Long getCount() {
        return count;
    }
    public void setCount(Long count) {
        this.count = count;
    }

    /**
     * 返回成功消息
     * @return RecruitmentResultEntity         */
    public static RecruitmentResultEntity ok() {
        return new RecruitmentResultEntity(true, StatusCode.OK, "成功");
    }

    /**
     * 返回成功消息
     * @return RecruitmentResultEntity         */
    public static RecruitmentResultEntity ok(Object data) {
        return new RecruitmentResultEntity(true, StatusCode.OK, "成功", data);
    }

    /**
     * 返回成功消息
     * @return RecruitmentResultEntity         */
    public static RecruitmentResultEntity ok(String message, Object data) {
        return new RecruitmentResultEntity(true, StatusCode.OK, "成功", data);
    }

    /**
     * 返回成功消息
     * @return RecruitmentResultEntity         */
    public static RecruitmentResultEntity ok(Object data, Long count) {
        return new RecruitmentResultEntity(true, StatusCode.OK, "成功", data, count);
    }

    /**
     * 返回失败消息
     * @return RecruitmentResultEntity         */
    public static RecruitmentResultEntity    error() {
        return new RecruitmentResultEntity(false, StatusCode.ERROR, "失败");
    }

    /**
     * 返回失败消息
     * @return RecruitmentResultEntity         */
    public static RecruitmentResultEntity    error(String message) {
        return new RecruitmentResultEntity(false, StatusCode.ERROR, message);
    }

    /**
     * 返回失败消息
     * @return RecruitmentResultEntity         */
    public static RecruitmentResultEntity    error(Integer code, String message) {
        return new RecruitmentResultEntity(false, code, message);
    }

    /**
     * 返回登录失败的消息：用户名或密码错误
     * @return RecruitmentResultEntity         */
    public static RecruitmentResultEntity    loginError() {
        return new RecruitmentResultEntity(false, StatusCode.LOGINERROR, "用户名或密码错误");
    }

    /**
     * 返回权限不足
     * @return RecruitmentResultEntity         */
    public static RecruitmentResultEntity    accessError() {
        return new RecruitmentResultEntity(false, StatusCode.ACCESSERROR, "权限不足");
    }

    /**
     * 返回远程调用失败
     * @return RecruitmentResultEntity         */
    public static RecruitmentResultEntity    remoteError() {
        return new RecruitmentResultEntity(false, StatusCode.REMOTEERROR, "远程调用失败");
    }

    /**
     * 返回重复操作
     * @return RecruitmentResultEntity         */
    public static RecruitmentResultEntity    repError() {
        return new RecruitmentResultEntity(false, StatusCode.REPERROR, "重复操作");
    }
}

