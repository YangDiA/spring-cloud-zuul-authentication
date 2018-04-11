package com.liumapp.auth.zuul.personal.api.request;

import java.io.Serializable;

/**
 * (描述用途)
 *
 * @author Linrry
 * @date 2018-04-11 下午 16:03
 */
public class ListArticle implements Serializable {

    private  int page;

    private int limit;

    private String start;

    private String end;

    private String keyword;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
