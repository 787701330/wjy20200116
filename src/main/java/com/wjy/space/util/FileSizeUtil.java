package com.wjy.space.util;

import java.text.DecimalFormat;



public class FileSizeUtil {
	public static String FormetFileSize(long size) {
		DecimalFormat df=new DecimalFormat("#.00");
		String fileSize="";
		if(size<1024) {
			fileSize=df.format((double)size)+"B";
		}else if(size<1048576) {
			fileSize=df.format((double)size/1024)+"K";
		}else if(size<1073741824) {
			fileSize=df.format((double)size/1048576)+"M";
		}else {
			fileSize=df.format((double)size/1073741824)+"G";
		}
		return fileSize;
	}
}
