/**
 * Created by yin on 2018/6/28.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提供常用正则验证
 * yinweitao-2018-6-28
 */
public class RegexUtil {
    /**
     * 是否为纯数字
     * @param str
     * @return
     */

    private static Pattern emailCheck = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");

    private static Pattern reg_isun = Pattern.compile("^(?i)[a-z][a-z0-9_]{4,19}$");

    private static Pattern reg_code = Pattern.compile("^[0-9]+$");

    private static Pattern reg_cnMobile = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$");

    private static Pattern reg_naMobile = Pattern.compile("^001[0-9]{10}$");

    //验证邮箱格式是否正确
    public static boolean isEmail(String str){
        if(str==null || "".equals(str))
            return false;
        return emailCheck.matcher(str).matches();
    }

     //验证手机号格式是否正确(分国内和国外）
    public static boolean isMobile(String str) {
        if(str == null || "".equals(str))
            return false;
        return isChineseMobile(str) || isNorthAmericaMobile(str);
    }

  //验证国内手机号格式是否正确
    public static boolean isChineseMobile(String str) {
        if(str.length()!=11)
            return false;
        return reg_cnMobile.matcher(str).matches();
    }

   //验证国外手机号格式是否正确
    public static boolean isNorthAmericaMobile(String str) {
        int len = str.length();
        if(len == 13)
            return reg_naMobile.matcher(str).matches();
        return false;
    }

    // 检验是否为数字
    public static boolean isCode(String str) {
        return reg_code.matcher(str).matches();
    }

    /**
     *  检查密码是否够强
     * @param str
     * @return
     */
    public static boolean isStrong(String str){
        if ("".equals(filterNull(str)))
            return false;
        int c = strongLevel(str);
        return c >= 3;
    }

    /**
     * 0  不安全
     * 1,2  弱度安全
     * 3  中度安全
     * 4  高度安全
     * @param str
     * @return
     */
    private static Pattern reg_number = Pattern.compile("[0-9]+?");
    private static Pattern reg_lcase = Pattern.compile("[a-z]+?");
    private static Pattern reg_ucase = Pattern.compile("[A-Z]+?");
    private static Pattern reg_punctuation = Pattern.compile("[^a-zA-Z0-9\\s]+");

    public static int strongLevel(String str){

        int c = 0;
        if(str.length()<11)
            return c;
        else
            c++;

        Matcher m=reg_number.matcher(str);
        if(m.find()){
            c++;
        }

        m=reg_lcase.matcher(str);
        if(m.find()){
            c++;
        }

        m=reg_ucase.matcher(str);
        if(m.find()){
            c++;
        }

        if (c > 1){
            if (str.length() >= 15) c++;
            m=reg_punctuation.matcher(str);
            if(m.find())
                c++;
        }

        return c ;
    }

/* —————————————内部调用方法 start——————————————*/
    /**
     * 字符串滤空
     * @param str 需要过滤的字符串
     * @return  过滤后返回的字符串
     */
    public static String filterNull(final String str){
        String s = "";
        if(str==null || "null".equals(str.trim())){
            s = "";
        }else{
            s = new String(str.trim());
        }
        return s;
    }

    /* —————————————内部调用方法 end——————————————*/


    /// <summary>
    /// 验证是否为用户名格式（字母、数字、下划线）
    /// </summary>
    public static boolean isUsername(String str) {
        return reg_isun.matcher(str).matches();
    }
}
