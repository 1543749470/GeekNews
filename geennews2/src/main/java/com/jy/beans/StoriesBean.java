package com.jy.beans;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.Arrays;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

public class StoriesBean {

    private int type;
    private int id;
    private String ga_prefix;
    private String title;

    private List<String> images;

    public StoriesBean(int type, int id, String ga_prefix, String title,
            List<String> images) {
        this.type = type;
        this.id = id;
        this.ga_prefix = ga_prefix;
        this.title = title;
        this.images = images;
    }

    public StoriesBean() {
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getGa_prefix() {
        return this.ga_prefix;
    }
    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<String> getImages() {
        return this.images;
    }
    public void setImages(List<String> images) {
        this.images = images;
    }


}
