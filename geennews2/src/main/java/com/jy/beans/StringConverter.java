package com.jy.beans;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.Arrays;
import java.util.List;

public class StringConverter implements PropertyConverter<List<String>,String> {
    @Override
    public List<String> convertToEntityProperty(String databaseValue) {
        if (databaseValue==null){
            return null;
        }else{
            List<String> images = Arrays.asList(databaseValue.split(","));
            return images;
        }
    }

    @Override
    public String convertToDatabaseValue(List<String> entityProperty) {
        if (entityProperty==null){
            return null;
        }else{
            StringBuffer sb = new StringBuffer();
            for (String link:entityProperty){
                sb.append(link);
                sb.append(",");
            }
            return sb.toString();
        }
    }
}
