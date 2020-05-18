package cn.wujunya.space.util;

public class SaltUtil {

	public static String getSalt() {
		String model="abcdefghijklmnopqrstuvwxyz1234567890";
		StringBuffer salt=new StringBuffer();
		char[] m=model.toCharArray();
		for(int i=0;i<4;i++) {
			char c=m[(int)(Math.random()*36)];
			salt.append(c);
		}
		return salt.toString();
	}
}
