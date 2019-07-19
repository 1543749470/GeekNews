package com.jy.utils;

public class GoldStatus {
    public GoldStatus(boolean isSelected, int index, String title) {
        this.isSelected = isSelected;
        this.index = index;
        this.title = title;
    }

    private boolean isSelected;
    private int index;
    private String title;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
