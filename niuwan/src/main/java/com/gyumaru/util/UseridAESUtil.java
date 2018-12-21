package com.gyumaru.util;
import java.util.Random;


public class UseridAESUtil {
/*public static User UT = new User();*/
    // 生成16位随机字符串
    public static StringBuilder Decrypt() throws Exception {  
    	StringBuilder s = new StringBuilder();
    	Random random = new Random();
    	Integer ints;
    	for (int i = 0; i < 4 ; i++) {
    	ints = (random.nextInt(10000)+100000)%10000;
    	// System.out.println(ints.toString());
    	s.append(ints.toString());
    	}
    	return s;
    } 
   
	
	
	
	
	
    public static void main(String[] args) throws Exception {  
    	StringBuilder s = new StringBuilder();
    	Random random = new Random();
    	Integer ints;
    	for (int i = 0; i < 4 ; i++) {
    	ints = (random.nextInt(10000)+100000)%10000;
    	// System.out.println(ints.toString());
    	s.append(ints.toString());
    	}
    	System.out.println(s);
    }  
}  