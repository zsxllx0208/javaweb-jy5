package com.itdr.zsx.utils;

public class PathUtil {
    public  static  String getPath(String path){
        String s = path.replace(".","/");
        String [] ss= s.split("/");
        return ss[1];
    }

}
