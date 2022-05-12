/**
 * Created by IntelliJ IDEA.
 * User: tridentj
 * Date: 2018/7/21
 * Time: 13:10
 **/
package com.jyf.blog.util;

public class AjaxResponseList {
    
    private Integer code;
    private String msg;
    private Integer start;
    private Integer end;
    private Integer num;
    private Integer pageNum;
    private Object content;
    
    
    public Integer getPageNum() {
        return pageNum;
    }
    
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public Integer getStart() {
        return start;
    }
    
    public void setStart(Integer start) {
        this.start = start;
    }
    
    public Integer getEnd() {
        return end;
    }
    
    public void setEnd(Integer end) {
        this.end = end;
    }
    
    public Integer getNum() {
        return num;
    }
    
    public void setNum(Integer num) {
        this.num = num;
    }
    
    public Object getContent() {
        return content;
    }
    
    public void setContent(Object content) {
        this.content = content;
    }
}
