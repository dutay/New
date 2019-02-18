package com.study.duxiaodong.baseframe;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.text.TextUtils;


import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * <p>
 * 字符串操作工具，封装判空，是否是手机号，等字符串操作
 */
public class StringUtils {

    /**
     * @return <p/>
     * 是否为手机号
     */
    public static boolean isMobileNO(String mobiles) {
        if (mobiles == null) {
            return false;
        }
        Pattern p = Pattern.compile("^(1[3456789])[0-9]{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isNumeric(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        str = str.trim();
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 格式化手机号 让中间为****
     */
    public static String hiddenMobileCenter(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return "";
        }
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 验证身份证号号码格式是否正确.
     */
    public static boolean isCardNo(String cardNo) {
        if (cardNo == null) {
            return false;
        }
        Pattern p = Pattern.compile("^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X|x)$");
        Matcher m = p.matcher(cardNo);
        return m.matches();
    }

    /**
     * 验证联系人姓名.
     *
     * @param contactName
     * @return
     */
    public static boolean isContactName(String contactName) {
        if (TextUtils.isEmpty(contactName) || contactName.length() > 10) {
            return false;
        }
        return true;
    }

    /**
     * <p/>
     * 验证码长度
     */
    public static boolean isSmsCode(String smsCode) {
        return !StringUtils.isEmpty(smsCode) && smsCode.length() == 6;
    }

    /**
     * <p/>
     * 验证密码长度
     */
    public static boolean verifyPwd(String pwd) {
        return pwd != null && pwd.length() >= 6;
    }

    /**
     * <p/>
     * 判断非空
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim()) || "null".equals(str.trim()) || "[null]".equals(str.trim()) || "nullnull".equals(str.trim());
    }

    /**
     * <p/>
     * 替换Null为""
     */
    public static String replaceNull(String str) {
        return str == null ? "" : str.trim();
    }

    public static int stringToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * @param str
     * @param split
     * @param index
     * @return 取一段字符串
     */
    public static String getSplitString(String str, String split, int index) {
        String[] strArr = str.split(split);
        if (index < 0) {
            if (strArr.length + index < 0) {
                return "";
            } else {
                return strArr[strArr.length + index];
            }
        } else {
            return strArr[index];
        }
    }

    /**
     * 转换图片大小 地址
     */
    public static String changeImageParam(String url, String param) {
        if (url != null) {
            StringBuilder sb = new StringBuilder();
            if (url.contains("?")) {
                sb.append(url.substring(0, url.indexOf("?")));
            } else {
                sb.append(url);
            }
            if (param != null) {
                sb.append(param);
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * 字符串 后面的数+1
     */
    public static String changeNum(String str, int flag) {
        String strs[] = str.split(" ");
        String text = Integer.parseInt(strs[1]) + (flag == 1 ? 1 : -1) + "";
        return strs[0] + " " + text;
    }

    /**
     * 字符串 去掉数字 加上参数
     */
    public static String changeText(String str, String num) {
        String strs[] = str.split(" ");
        return strs[0] + " " + num;
    }

    /**
     * 字符串 去掉数字 加上参数
     */
    public static String changeText(String str, long num) {
        String strs[] = str.split(" ");
        return strs[0] + " " + num;
    }

    public static String trim(String name) {
        return name.trim();
    }

    // 去掉http请求？后面的字符串, 返回带？
    public static String removeHttpSuffix(String imgUrl) {
        if (imgUrl.contains("?")) {
            int tempIndex = imgUrl.indexOf("?");
            return imgUrl.substring(0, tempIndex + 1);
        }
        return imgUrl;
    }

    public static String removeWeexHttpSuffix(String weexUrl) {
        String url = "";
        if (!TextUtils.isEmpty(weexUrl)) {
            int indexOf = weexUrl.indexOf("/build");
            if (indexOf != -1) {
                url = weexUrl.substring(indexOf);
            }
        }
        return url;
    }

    public static boolean equals(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        } else if (str1 != null && str2 != null) {
            if (str1.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    //处理地址名称
    public static String dealCityName(String cityName) {
        if (cityName.equals("市辖区") || cityName.equals("县") || cityName.equals("市")) {
            return "";
        } else {
            return "-" + cityName;
        }
    }

    //更改万单位
    public static String convertCount(Integer count) {
        if (count == null) {
            return "0";
        }
        String strCount = "";
        strCount = count + "";
        if (count < 10000) {
            return strCount;
        } else if (count < 10000 * 10000) {
            return strCount.substring(0, strCount.length() - 4) + "." + strCount.substring(strCount.length() - 4, strCount.length() - 2) + "万";
        } else {
            return strCount.substring(0, strCount.length() - 8) + "." + strCount.substring(strCount.length() - 8, strCount.length() - 6) + "亿";
        }
    }

    //int转字符串
    public static String convertToCount(Integer count) {
        if (count == null) {
            return "0";
        } else {
            return count + "";
        }
    }

    //字符串集合中移除字符串
    public static void removeStringFromList(ArrayList<String> arrayList, String str) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).trim().equals(str.trim())) {
                arrayList.remove(i);
            }
        }
    }

    //截取字符串前部分，再加上省略号
    public static String getShortString(String str, int count) {
        if (str != null && str.length() > count) {
            return str.substring(0, count) + "...";
        } else {
            return str;
        }
    }

    public static int getPrice(String s) {
        int price = 1;
        if (s.startsWith("¥")) {
            s = s.substring(1);
            try {
                price = (int) (Float.parseFloat(s) * 100);
                return price;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return price;
    }


    /**
     * @param actionArgument
     * @return edited on 2016/3/2, 防止传入为 json 时， 无法解析。
     */
    public static JSONObject convertUrlParamToJson(String actionArgument) {
        JSONObject jsonObject = new JSONObject();
        if (isEmpty(actionArgument)) {
            return null;
        }
        actionArgument = actionArgument.trim();
        if (actionArgument.startsWith("heatedloan://")) {
            String param = actionArgument.replace("heatedloan://", "");
            String[] params = param.split("&&");
            for (String paramStr : params) {
                try {
                    String[] keyValue = paramStr.split("=", 2);
                    jsonObject.put(keyValue[0], keyValue[1]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                jsonObject = new JSONObject(actionArgument);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }


    //unicode 转中文
    public static String decodeUnicode(String theString) {

        char aChar;

        int len = theString.length();

        StringBuffer outBuffer = new StringBuffer(len);

        for (int x = 0; x < len; ) {

            aChar = theString.charAt(x++);

            if (aChar == '\\') {

                aChar = theString.charAt(x++);

                if (aChar == 'u') {

                    // Read the xxxx

                    int value = 0;

                    for (int i = 0; i < 4; i++) {

                        aChar = theString.charAt(x++);

                        switch (aChar) {

                            case '0':

                            case '1':

                            case '2':

                            case '3':

                            case '4':

                            case '5':

                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';

                    else if (aChar == 'n')

                        aChar = '\n';

                    else if (aChar == 'f')

                        aChar = '\f';

                    outBuffer.append(aChar);

                }

            } else

                outBuffer.append(aChar);

        }

        return outBuffer.toString();

    }

    public static String removeNewlineChar(String text) {
        if (text == null) return "";
        text = text.replace("\\s", "");
        return text.replaceAll("\n", "");
    }

    //把错误的json字符串替换
    public static String delToJsonString(String json) {
        //json 字符串 替换 "[  替换为 [ ]"替换为] "{替换为{ }"替换为}
        return json.replace("\"[", "[")
                .replace("]\"", "]")
                .replace("\"{", "{")
                .replace("}\"", "}");
    }

    //取访问页面名字，比如http://m2.xunbaozl.com/index.html 则取 index
    public static String getHtmlName(String url) {
        if (StringUtils.isEmpty(url)) {
            return "";
        }
        int lastSpliteIndex = url.lastIndexOf("?");
        if (lastSpliteIndex > -1) {
            url = url.substring(0, lastSpliteIndex);
        }
        int lastIndexStart = url.lastIndexOf("/");
        int lastIndexEnd = url.lastIndexOf(".");
        if (lastIndexEnd > lastIndexStart && url.length() > lastIndexEnd - 1) {
            return url.substring(lastIndexStart + 1, lastIndexEnd);
        }
        //不是 .html这种方式
        if (lastSpliteIndex > lastIndexStart && url.length() > lastSpliteIndex - 1) {
            return url.substring(lastIndexStart + 1, lastSpliteIndex);
        }
        return "";
    }

    /**
     * 获取drawable本地路径uri
     */

    public static String getFileUri(Resources resource, int resId) {
        StringBuilder sb = new StringBuilder(ContentResolver.SCHEME_ANDROID_RESOURCE + "://");
        sb.append(resource.getResourcePackageName(resId)).append("/")
                .append(resource.getResourceTypeName(resId)).append("/")
                .append(resource.getResourceEntryName(resId));
        return sb.toString();
    }

    /**
     * 截取url中的imagekey
     */
    public static String cropImageKey(String imageKey) {
        //如果imagekey为http开头，则取其中的imgKey
        String temp1 = imageKey;
        int end = temp1.lastIndexOf("?");
        if (end < 0) {
            end = imageKey.length();
        }
        String temp2 = temp1.substring(0, end);
        int start = temp2.lastIndexOf("/");
        if (start < 0) {
            start = -1;
        }
        return temp2.substring(start + 1, end);
    }

    //判断价格是否为0
    public static boolean priceIsZero(String price) {
        if (StringUtils.isEmpty(price)) return true;
        float priceFloat = Float.parseFloat(price);
        return priceFloat == 0;
    }

    // 判断一个字符是否是中文
    public static boolean isChinese(char c) {
        return c >= 0x4E00 && c <= 0x9FA5;// 根据字节码判断
    }

    // 判断一个字符串是否含有中文
    public static boolean withChinese(String str) {
        if (str == null)
            return false;
        for (char c : str.toCharArray()) {
            if (isChinese(c))
                return true;// 有一个中文字符就返回
        }
        return false;
    }

    /**
     * 去掉小数
     *
     * @return
     */
    public static String intPrice(String price) {
        if (price == null) {
            return "0";
        }
        int dotIndex = price.lastIndexOf(".");
        if (dotIndex == -1) {
            return price;
        }
        return price.substring(0, dotIndex);
    }


    /**
     * 转义+号
     */
    public static String setPlusToB(String text) {
        if (text != null) {
            text = text.replaceAll("\\+", "%2B");
        }
        return text;
    }

    /**
     * 转义%2b成+号
     */
    public static String setBToPlus(String text) {
        if (text != null) {
            text = text.replaceAll("%2B", "\\+");
        }
        return text;
    }

    public static String hideIdCardInfo(String idCard) {
        if (isCardNo(idCard)) {
            return idCard.substring(0, 4) + "＊＊＊" + idCard.substring(idCard.length() - 4);
        }
        return idCard;
    }

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 加密key.
     */
    private static final String KEY = "zOdlEDQRlw2Hat9eZDM8O7z3";
    /**
     * 加密向量.
     */
    private static final String IV = "Z60YSosC";
    private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private static long lastClickTime;

    /**
     * 防暴力点击 true则return false则走方法
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 过滤中文
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String stringFilter(String str) throws PatternSyntaxException {
        // 只允许字母和数字
        String regEx = "[^a-zA-Z0-9*_@#]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 保留小数
     *
     * @param value 原值
     * @param n     几位
     * @return
     */
    public static double get2(double value, int n) {
        BigDecimal bg = new BigDecimal(value);

        return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * 规则3：必须同时包含大小写字母及数字
     * 是否包含
     *
     * @param str
     * @return
     */
    public static boolean isContainAll(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLowerCase = false;//定义一个boolean值，用来表示是否包含字母
        boolean isUpperCase = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLowerCase(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLowerCase = true;
            } else if (Character.isUpperCase(str.charAt(i))) {
                isUpperCase = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLowerCase || isUpperCase && str.matches(regex);
        return isRight;
    }

    public static double getCeil(double d, int n) {
        BigDecimal b = new BigDecimal(String.valueOf(d));
        b = b.divide(BigDecimal.ONE, n, BigDecimal.ROUND_CEILING);
        return b.doubleValue();
    }

    /**
     * 格式化phone  去除头部的+86  和里面的空格 和横杠
     */
    public static String formatPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return "";
        }

        String replacePhone = phone.replaceAll(" ", "").replaceAll("-", "").replace("+86", "");
        if (!TextUtils.isEmpty(replacePhone) && replacePhone.startsWith("86")) {
            return replacePhone.replaceFirst("86", "");
        } else {
            return replacePhone;
        }

    }

    /**
     * 遍历字符串 是否全部都是同一个char
     */
    public static boolean isAllSameNum(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return false;
        }
        char[] chars = phone.toCharArray();
        char firstChar = chars[0];
        //遍历
        for (char aChar : chars) {
            if (firstChar != aChar) {
                return false;
            }
        }
        return true;
    }

    /**
     * 校验命名  人际关系认证的名字校验
     */
    public static boolean checkNameFormat(String name) {
        if (TextUtils.isEmpty(name)) {
            return false;
        }

        List<String> nameList = Arrays.asList( "父亲", "母亲", "媳妇", "爸", "妈", "女儿", "儿子", "爸爸", "妈妈");
        for (String itemName : nameList) {
            if (name.equals(itemName)) {
                return false;
            }
        }
        if (name.startsWith("小") || name.startsWith("大") || name.startsWith("老") || name.startsWith("阿") || name.startsWith("啊") || name.startsWith("表")) {
            return false;
        }
        char[] chars = name.toCharArray();
        for (char aChar : chars) {
            if (!isChinese(aChar)) {
                return false;
            }
        }

        return !isAllSameNum(name);//叠词是false

    }
}
