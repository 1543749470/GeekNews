package com.jy.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MyLikeBeans {
    @Property(nameInDb = "llid")
    @Id
    private Long _id;
    private int id;
    private String title;
    private String image;
    private int type;
    @Generated(hash = 1702306378)
    public MyLikeBeans(Long _id, int id, String title, String image, int type) {
        this._id = _id;
        this.id = id;
        this.title = title;
        this.image = image;
        this.type = type;
    }
    @Generated(hash = 343842407)
    public MyLikeBeans() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    
    

}
