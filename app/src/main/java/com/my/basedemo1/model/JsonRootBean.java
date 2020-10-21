/**
 * Copyright 2020 bejson.com
 */
package com.my.basedemo1.model;

import java.util.List;

/**
 * Auto-generated: 2020-10-16 2:45:13
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private String show_text;
    private List<List_info> list_info;

    public void setShow_text(String show_text) {
        this.show_text = show_text;
    }

    public String getShow_text() {
        return show_text;
    }

    public void setList_info(List<List_info> list_info) {
        this.list_info = list_info;
    }

    public List<List_info> getList_info() {
        return list_info;
    }

    @Override
    public String toString() {
        return "JsonRootBean{" +
                "show_text='" + show_text + '\'' +
                ", list_info=" + list_info +
                '}';
    }
}