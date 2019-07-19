package com.jy.utils;

import android.content.res.XmlResourceParser;
import android.util.ArrayMap;

public class XmlUtils {
    public static ArrayMap<String, ArrayMap<String, String>> parseNodes(XmlResourceParser xmlResourceParser) throws Exception {
        if (xmlResourceParser == null)
            return null;
        ArrayMap<String, ArrayMap<String, String>> map = null;
        ArrayMap<String, String> nodeMap = null;
        int root = xmlResourceParser.getEventType();
        while (root != XmlResourceParser.END_DOCUMENT) {  //开始解析
            switch (root) {
                case XmlResourceParser.START_DOCUMENT:
                    map = new ArrayMap<>();
                    break;
                case XmlResourceParser.START_TAG:  //解析标签
                    if ("index".equals(xmlResourceParser.getName())) {
                        String nodeName = xmlResourceParser.getAttributeValue(0);
                        nodeMap = new ArrayMap<>();
                        if (map != null) {
                            map.put(nodeName, nodeMap);
                        }
                    } else if ("node".equals(xmlResourceParser.getName())) { // 解析子节点
                        String node = xmlResourceParser.getAttributeValue(0);
                        String nodeName = xmlResourceParser.nextText();
                        if (nodeMap != null) {
                            nodeMap.put(node, nodeName);
                        }
                    }
                    break;
                case XmlResourceParser.END_TAG:   //结束解析
                    if ("index".equals(xmlResourceParser.getName())) {
                        nodeMap = null;
                    }
                    break;
            }
            root = xmlResourceParser.next();
        }
        return map;
    }
}
