package com.wjy.space.util;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

public class EmptyStringToNullUtil {
	
	public static Object EmptyStringToNull(Object object) {
		Class<? extends Object> class1 = object.getClass();
		Field[] fields = class1.getDeclaredFields();
		for(Field f1:fields) {
			String name = f1.getName();
			String name2="get"+name.substring(0, 1).toUpperCase()+name.substring(1);
			try {
				Method method = class1.getDeclaredMethod(name2, null);
				AnnotatedType type = method.getAnnotatedReturnType();
				if(type.getType().getTypeName().equals("java.lang.String")) {
					String name3=(String) method.invoke(object, null);
					if(StringUtils.isBlank(name3.trim())) {
						f1.setAccessible(true);
						f1.set(object, null);
						f1.setAccessible(false);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return object;
	}
}
