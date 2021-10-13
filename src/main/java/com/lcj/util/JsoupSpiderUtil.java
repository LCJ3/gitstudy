package com.lcj.util;

import com.lcj.constant.CommonConst;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * description 用于爬取桂工就业网的数据
 * author lichunjiang
 * createTime 2021/10/10 11:00
 **/
public class JsoupSpiderUtil {
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
        //mvn package -Dmaven.test.skip=true
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
        StringBuffer returnData=new StringBuffer(CommonDateUtil.changeToMonthDay(dateValue)+" ");

        //字符串拼接比较多
//        System.out.println(CommonDateUtil.changeToMonthDay(dateValue)+"\r\n");
        for (Element element : elements) {
            // \r空格
            // \n换行
            returnData.append("\n");
//                    System.out.println(""+"【"+(Integer.parseInt(String.valueOf(elements.indexOf(element)))+1)+"】"+ JsoupSpiderUtil.getLectureName(element)+""
//                        +element.getElementsByTag("div").select(".text-777.text-clip").text()+
//                        " "+CommonConst.URL_PRE+element.select(".a-main").attr("href"));
            //企业名
            returnData.append("【"+(Integer.parseInt(String.valueOf(elements.indexOf(element)))+1)+"】"+JsoupSpiderUtil.getLectureName(element));
            returnData.append("\n");
            //公司链接
            returnData.append(CommonConst.URL_PRE+element.select(".a-main").attr("href"));
            returnData.append("\n");
            //地点
            String temp=element.select("div.text-777.text-clip.mg-tb-5").text().replace(Jsoup.parse("&nbsp;").text(), "");
            returnData.append(temp);
            //时间
            //时间里面包括地点，把地点给取消掉
            returnData.append("\n");
            String s1=element.select("div.text-777.text-clip").text().replace(Jsoup.parse("&nbsp;").text(), " ");
            String s2=s1.replace(temp,"").replace(" ","");
            returnData.append(s2.substring(0,10)+"  "+s2.substring(10,s2.length()));

        }
        //创建存储数据的文件
        File file =new File("D:\\getData.txt");
        //创建一个用于操作文件的字节输出流对象。一创建就必须明确数据存储目的地。
        //输出流目的是文件，会自动创建，如果文件存在，则覆盖。
        FileOutputStream fos=new FileOutputStream(file);
        //调用父类中的write方法
        byte[] data=returnData.toString().getBytes();
        fos.write(data);
        //关闭流资源
        fos.close();

        System.out.println(returnData);
        return returnData.toString();
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
    JsoupSpiderUtil.httpPost("2021-10-14", CommonConst.SPIDER_URL, map, CommonConst.COOKIE_VALUE);
}
}