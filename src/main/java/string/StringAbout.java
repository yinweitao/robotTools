package string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yin on 2018/7/12.
 */
public class StringAbout {
    //清洗html
    public static String delHTMLTag(String htmlStr){
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式
        String regEx_br="\r|\n";

        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
        Matcher m_script=p_script.matcher(htmlStr);
        htmlStr=m_script.replaceAll(""); //过滤script标签

        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(htmlStr);
        htmlStr=m_style.replaceAll(""); //过滤style标签

        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(htmlStr);
        htmlStr=m_html.replaceAll(""); //过滤html标签

        Pattern p_br=Pattern.compile(regEx_br,Pattern.CASE_INSENSITIVE);
        Matcher m_br=p_html.matcher(htmlStr);
        htmlStr=m_br.replaceAll(""); //过滤换行、回车符

        return htmlStr.trim(); //返回文本字符串
    }



    /**
     *  转义特殊字符
     * 用法：string keywords = escape(key); //字符需要进行转义
     keywords = URLEncoder.encode(keywords, "UTF-8");
     */
    public static String escape(String s) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '\\' || c == '+' || c == '-' || c == '!' || c == '(' || c == ')' || c == ':' || c == '^' || c == '[' || c == ']' || c == '"' || c == '{' || c == '}' || c == '~' || c == '*' || c == '?' || c == '|' || c == '&' || c == '/') {
                sb.append('\\');
            }

            sb.append(c);
        }
        return sb.toString();
    }

}
