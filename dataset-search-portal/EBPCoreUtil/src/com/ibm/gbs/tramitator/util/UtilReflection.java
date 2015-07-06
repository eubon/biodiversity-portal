package com.ibm.gbs.tramitator.util;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

public class UtilReflection {

	Logger log = Logger.getLogger(UtilReflection.class);

	@SuppressWarnings("rawtypes")
	public static Object obtenerValor(Object fv, String campo,
			Class typeParameterClass) throws NoSuchFieldException, ParseException {
		Object value = null;

		Class claseTipo = null;
		claseTipo = obtenerClassType(campo, typeParameterClass);
		if (claseTipo.isPrimitive()) {
			value = paresearPrimitivas(fv, claseTipo);
			return value;
		}
		value = paresearValoresNumericos(fv, claseTipo);
		if (value == null) {
			value = paresearValoresTemporales(fv, claseTipo);
			if (value == null)
				if (claseTipo.equals(String.class)) {
					value = fv.toString();
				}
		}
		return value;

	}
	
	public static Method getMethod(Object obj, String methodName) {
		Method tratamiento = null;
		Method[] methods = obj.getClass().getDeclaredMethods();
		for (Method aux : methods) {
			if (aux.getName().equals(methodName)) {
				tratamiento = aux;
			}
		}
		return tratamiento;
	}

	@SuppressWarnings("rawtypes")
	public static Object obtenerValorCampo(Object fv, String campo,
			Class typeParameterClass)  {
		Object value = null;
		Object inst = fv;
		String[] split = campo.split("\\.");
		Class parameterClass = typeParameterClass;
		Field field = null;
		try {
			for (int i = 0; i < split.length; i++) {
				field = parameterClass.getDeclaredField(split[i]);
				parameterClass = (Class) field.getType();
				try {
					inst = field.get(inst);
				} catch (IllegalAccessException e) {
					for(PropertyDescriptor propertyDescriptor : 
					    Introspector.getBeanInfo(inst.getClass()).getPropertyDescriptors()){
					    if (propertyDescriptor.getName().equals(split[i])) {
							inst = propertyDescriptor.getReadMethod().invoke(inst, new Object[0]);
							break;
						}
					}
				}
			}
			value = inst;
		} catch (Exception e) {
			System.err.println("Error UtilReflection.obtenerValorCampo "+e.getLocalizedMessage());
		}
		
		return value;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmbeddedId(String campo, Class clazz)
			throws SecurityException, NoSuchFieldException {
		String[] aux = campo.split("\\.");
		Field field = clazz.getDeclaredField(aux[0]);
		Annotation annotation = field.getAnnotation(javax.persistence.EmbeddedId.class);
		if (annotation == null) {
			return false;
		} else {
			return true;
		}
		
	}

	@SuppressWarnings({ "rawtypes" })
	public static Class obtenerClassType(String campo, Class typeParameterClass)
			throws NoSuchFieldException {
		String[] split = campo.split("\\.");

		Class parameterClass = typeParameterClass;
		for (int i = 0; i < split.length; i++) {
			Field field = parameterClass.getDeclaredField(split[i]);
			System.out.println(field.getType());
			parameterClass = (Class) field.getType();
		}
		return parameterClass;
	}

	@SuppressWarnings("rawtypes")
	public static Object paresearPrimitivas(Object fv, Class tipo) {
		Object value = null;
		try 
		{
		if (Byte.TYPE.equals(tipo)) {
			byte valor = Byte.parseByte((String) fv);
			value = valor;
		} else if (Short.TYPE.equals(tipo)) {
			short valor = Short.parseShort((String) fv);
			value = valor;
		} else if (Integer.TYPE.equals(tipo)) {
			int valor = Integer.parseInt((String) fv);
			value = valor;
		} else if (Long.TYPE.equals(tipo)) {
			long valor = Long.parseLong((String) fv);
			value = valor;
		} else if (Float.TYPE.equals(tipo)) {
			float valor = Float.parseFloat((String) fv);
			value = valor;
		} else if (Double.TYPE.equals(tipo)) {
			double valor = Double.parseDouble((String) fv);
			value = valor;
		} else if (Boolean.TYPE.equals(tipo)) {
			boolean valor = Boolean.parseBoolean((String) fv);
			value = valor;
		} else if (Character.TYPE.equals(tipo)) {
			char valor = (Character) fv;
			value = valor;
		}
		} catch (Exception e)
		{
			Logger.getLogger(UtilReflection.class).debug("UtilReflection.parsearPrimitivas ="+fv+"-"+tipo + "-"); 
			Logger.getLogger(UtilReflection.class).debug("UtilReflection.parsearPrimitivas =Exception"+ e + "-");
			value = null;
		}
		
		return value;
	}

	@SuppressWarnings("rawtypes")
	public static Object paresearValoresNumericos(Object fv, Class tipo) {
		Object value = null;
		if (tipo.equals(Long.class)) {
			value = new Long(fv.toString());
		} else if (tipo.equals(Integer.class)) {
			value = new Integer(fv.toString());
		} else if (tipo.equals(BigDecimal.class)) {
			value = new BigDecimal(fv.toString());
		}
		return value;
	}
	
	
	@SuppressWarnings("rawtypes")
	public static Object paresearValoresBoolean(Object fv, Class tipo) {
		Object value = null;
		if (tipo.equals(Boolean.class)) {
			value = new Boolean(fv.toString());
		}
		return value;
	}

	@SuppressWarnings("rawtypes")
	public static Object paresearValoresTemporales(Object fv, Class tipo) throws ParseException {
		Object value = null;
		// Locale locale =
		// FacesContext.getCurrentInstance().getViewRoot().getLocale();
		// Thu Dec 05 00:00:00 CET 2013
		// jueves 5 de diciembre de 2013 11H37' CET
		SimpleDateFormat sdf = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss z yyyy", Locale.US);
		//value = sdf.parse(fv.toString());
		if (tipo.equals(Date.class)) {
			value = sdf.parse(fv.toString());
		} else if (tipo.equals(Timestamp.class)) {
			value = new Timestamp(sdf.parse(fv.toString()).getTime());
		}
		return value;
	}
}