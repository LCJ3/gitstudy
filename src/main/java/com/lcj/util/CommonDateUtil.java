package com.lcj.util;

/**
 * @description: 处理日期的工具类
 * @projectName: githubdemopractice
 * @see: com.lcj.util
 * @author: lichunjiang
 * @createTime: 2021/10/10 19:55
 * @version: 1.0
 */
public class CommonDateUtil {

    public static  String  changeToMonthDay(String date){
        String [] stringArray=date.split("-");
        return  stringArray[1]+"月"+stringArray[2]+"日来校招聘名单";
    }
}
