package com.lcj.spider;

import com.lcj.constant.CommonConst;
import com.lcj.util.CommonDateUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * description 用于爬取桂工就业网的数据
 * author lichunjiang
 * createTime 2021/10/10 11:00
 **/
public class JsoupSpider {
    /**
     * description 爬取桂工就业网主要方法
     * param [dateValue, url, map, cookie]
     * return java.lang.String
     * author lichunjiang
     * createTime 2021/10/10 11:00
     **/
    public static String httpPost(String dateValue, String url, Map<String, String> map, String cookie) throws IOException {
        //获取请求连接
        Connection con = Jsoup.connect(url);
        //遍历生成参数
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                //添加参数
                con.data(entry.getKey(), entry.getValue());
            }
        }
        //插入cookie（头文件形式）
        con.data(CommonConst.DATE_NAME, dateValue);
        con.header(CommonConst.COOKIE_NAME, cookie);
        //模拟浏览器
        Document doc = con.userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; MALC)")
                .timeout(999999999)
                .post();
        //下面感觉就是对Document的操作学习
        Element body = doc.body();
       //System.out.println(body.toString());
        Elements elements=body.getElementsByTag("li");
        //element.select(".text-777.text-clip.mg-tb-5").text()
        //字符串拼接比较多
        System.out.println(CommonDateUtil.changeToMonthDay(dateValue));
        for (Element element : elements) {
                    System.out.println(""+"【"+(Integer.parseInt(String.valueOf(elements.indexOf(element)))+1)+"】"+JsoupSpider.getLectureName(element)+""
                        +element.getElementsByTag("div").select(".text-777.text-clip").text()+
                        " "+CommonConst.URL_PRE+element.select(".a-main").attr("href"));
        }
        //System.out.println(doc.toString());
        return doc.toString();
    }
    /**
     * description 去除“专场”、“招聘会”字样
     * param [element]
     * return java.lang.String
     * author lichunjiang
     * createTime 2021/10/10 19:41
     **/
    public static  String getLectureName(Element element){
        String judgeText=element.getElementsByTag("a").select(".badge").text();
        String  returnText=element.getElementsByTag("a").text();
        if (CommonConst.Recruitment_TYPE_ZC.equals(judgeText)){
            return returnText.substring(3);
        }else{
            return returnText.substring(4);
        }
    }
    public static void main(String[] args) throws IOException {
        Map map = new HashMap();
        JsoupSpider.httpPost("2021-11-01", CommonConst.SPIDER_URL, map, CommonConst.COOKIE_VALUE);
    }
}
