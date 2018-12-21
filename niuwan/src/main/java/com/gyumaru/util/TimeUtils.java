package com.gyumaru.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TimeUtils {

	public static Map<String,Date> getToday(){
//		   String[] str = new String[2];
		   Map<String,Date> map = new HashMap<String,Date>();
		   /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);*/
		   Calendar calendar = Calendar.getInstance(Locale.CHINA);
           calendar.setTime(new Date());
           calendar.set(Calendar.HOUR_OF_DAY, 0);
           calendar.set(Calendar.MINUTE, 0);
           calendar.set(Calendar.SECOND, 0);
//           str[0] = simpleDateFormat.format(calendar.getTime());
           Date date1 = calendar.getTime();
           map.put("beginTime", date1);
           calendar.set(Calendar.HOUR_OF_DAY, 23);
           calendar.set(Calendar.MINUTE, 59);
           calendar.set(Calendar.SECOND, 59);
           Date date2 = calendar.getTime();
           map.put("endTime", date2);
//           str[1] = simpleDateFormat.format(calendar.getTime());
//		return str;
           return map;
	}
	
	public static Map<String,String> getToday1(){
//		   String[] str = new String[2];
	   Map<String,String> map = new HashMap<String,String>();
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
	   Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
//        str[0] = simpleDateFormat.format(calendar.getTime());
        Date date1 = calendar.getTime();
        map.put("beginTime", sdf.format(date1));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date date2 = calendar.getTime();
        map.put("endTime", sdf.format(date2));
//        str[1] = simpleDateFormat.format(calendar.getTime());
//		return str;
        return map;
	}
	public static void main(String[] args) {
		TimeUtils.getToday();
	}
}
