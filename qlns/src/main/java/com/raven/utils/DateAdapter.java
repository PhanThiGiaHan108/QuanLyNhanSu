package com.raven.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {
    
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    @Override
    public Date unmarshal(String v) throws Exception {
        if (v == null || v.isEmpty()) {
            return null;
        }
        return dateFormat.parse(v);
    }
    
    @Override
    public String marshal(Date v) throws Exception {
        if (v == null) {
            return null;
        }
        return dateFormat.format(v);
    }
} 