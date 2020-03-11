package com.wjy.space.util;


import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

public class EmptyStringToNullUtil {
	
	public static Object EmptyStringToNull(Object object) {
		Class<? extends Object> class1 = object.getClass();
		Field[] fields = class1.getDeclaredFields();
		System.out.println("fileds:"+fields.length);
		for(Field f1:fields) {
			System.out.println("file:"+f1);
			System.out.println(1);
			String name = f1.getName();
			System.out.println(2);
			String name2="get"+name.substring(0, 1).toUpperCase()+name.substring(1);
			System.out.println(3);
			System.out.println(name2);
			try {
				Method method = class1.getDeclaredMethod(name2, null);
				System.out.println(4);
				System.out.println("method"+method);
				AnnotatedType type = method.getAnnotatedReturnType();
				System.out.println(5);
				System.out.println(type);
				if(type.getType().getTypeName().equals("java.lang.String")) {
					System.out.println(6);
					System.out.println("String");
					String name3=(String) method.invoke(object, null);
					System.out.println(7);
					if(StringUtils.isBlank(name3.trim())) {
						System.out.println("xiugai");
						f1.setAccessible(true);
						f1.set(object, null);
						f1.setAccessible(false);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("god");
		return object;
	}
}
