package my.freemarker.util;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class StringUtil {
	//phoneNum[0]移动，phoneNum[1]联通，phoneNum[2]电信
	private static final String[][] phoneNum={{"139","138","137","136","135","134","159","158","157","150","151","152","188"},
			{"130","131","132","156","155"},{"133","153","189"}};
	private static final Random random=new Random();
	
	
	public  static void main(String ... strings){
	    System.out.println(getUUID());
	}
	/**
	 * 
	  * 字符串是否为空的判断，空为true
	  *@param str
	  *@return 
	  *@date 2016年8月31日 上午11:05:20
	  *@author zxn
	 */
	public static boolean isEmpty(String str){
		return str==null||"".equals(str);
	}
	
	public static boolean isNotEmpty(String str){
		return str!=null&&(!"".equals(str));
	}

	/**
	 * 
	  * 获取随机手机号码
	  *@return 
	  *@date 2016年8月31日 上午11:05:06
	  *@author zxn
	 */
	public static String getRandomPhone(){
		//运营商
		int yys=random.nextInt(phoneNum.length);
		String phone=phoneNum[yys][random.nextInt(phoneNum[yys].length)];
		for(int i=0;i<8;i++){
			phone+=random.nextInt(10);
		}
		return phone;
	}
	
	/**
	 * 
	  * 字符串倒置
	  *@param str
	  *@return 
	  *@date 2016年8月31日 上午11:05:49
	  *@author zxn
	 */
	public static String reverse(String str){
		List<String > list=getList(str);
		Collections.reverse(list);
		str="";
		for(String s : list){
			str+=s;
		}
		return str;
	}
	
	private static List<String> getList(String str){
		List<String> list=new ArrayList<>();
		String [] s=str.split("");
		for(int i=0;i<s.length;i++){
			list.add(s[i]);
		}
		return list;
	}
	
	/**
	 * 
	  * 获取num位的随机字符串
	  *@param num
	  *@return 
	  *@date 2017年8月25日 上午9:30:50
	  *@author zxn
	 */
	public static String getRdStr(int num){
		String[] s={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0",
				"1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S",
				"T","U","V","W","X","Y","Z"};
		String str="";
		for(int i=0;i<num;i++){
			str+=s[random.nextInt(61)];
		}
		return str;
 	}
	
	/**
	 * 
	  * md5加密
	  *@param str
	  *@return
	  *@throws Exception 
	  *@date 2017年8月25日 上午9:29:47
	  *@author zxn
	 */
	public static String md5Encode(String str) throws Exception{
	    MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = str.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
	}
	
	/**
	 * 
	  * 获取32位uuid（没有 -）
	  *@return 
	  *@date 2017年8月25日 上午9:29:30
	  *@author zxn
	 */
	public static String getUUID(){
	    UUID uuid = UUID.randomUUID();
	    String uuidstr = uuid.toString().replaceAll("-", "");
	    return uuidstr;
	}
	
	/**
     * 
      * 下划线转驼峰
      *@param s
      *@return 
      *@date 2017年8月25日 上午9:28:01
      *@author zxn
     */
    public static String underlineToCamel(String s){
        int len = s.length();
        StringBuffer sb = new StringBuffer(len);
        for(int i=0;i<len;i++){
            char c = s.charAt(i);
            if( c == '_'){
                i++;
                sb.append(Character.toUpperCase(s.charAt(i)));
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    /**
     * 
      * 驼峰转下划线
      *@param s
      *@return 
      *@date 2017年8月25日 上午9:28:15
      *@author zxn
     */
    public static String camelToUnderline(String s){
        int len = s.length();
        StringBuffer sb = new StringBuffer(len);
        for(int i=0;i<len;i++){
            char c = s.charAt(i);
            if( c == Character.toUpperCase(c)){
                sb.append("_");
                sb.append(Character.toLowerCase(s.charAt(i)));
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    
    /**
     * 
      * 下划线装首字母大写驼峰
      *@param s
      *@return 
      *@date 2017年8月25日 上午9:28:01
      *@author zxn
     */
    public static String underlineToUpperCamel(String s){
        int len = s.length();
        StringBuffer sb = new StringBuffer(len);
        if(s.indexOf("_")==-1){
            return s;
        }
        for(int i=0;i<len;i++){
            char c = s.charAt(i);
            if(i==0){
                sb.append(Character.toUpperCase(s.charAt(i)));
                continue;
            }
            if( c == '_'){
                i++;
                sb.append(Character.toUpperCase(s.charAt(i)));
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
//	public static String[] split(String str,String regex){
//	    String[] result = null; 
//	    String[] array = str.split(regex);
//	    try {
//            String s= array[array.length-1];
//            result = array;
//        } catch (NullPointerException e) {
//            result=new String[array.length-1];
//            for (int i = 0;i<array.length-1;i++) {
//                result[i]=array[i];
//            }
//        }
//	    return result;
//	}
}
