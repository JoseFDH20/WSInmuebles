package com.josedimash.wsinmuebles.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.gson.Gson;

public class Utilerias {

	public static boolean validaTelefono(String cadena) {
		if (cadena == null || (cadena.length() > 10 || cadena.length() < 10)) {
			return false;
		}

		Pattern p = Pattern.compile("[0-9]{10}");
		Matcher m = p.matcher(cadena);
		return m.matches();
	}

	public static Date convertYYYYMMDD(String fecha) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	public static String formatYYYYMMDDHHmmss(Date date) {
		String fechaTmp = null;
		if (date != null) {
			SimpleDateFormat smplDtFrmt = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			fechaTmp = smplDtFrmt.format(date);
		}
		return fechaTmp;
	}

	public static Long toLong(Object number) {
		if (number != null && (NumberUtils.isCreatable(number.toString().trim()))) {
			Double d = NumberUtils.toDouble(number.toString().trim());
			return d.longValue();
		}
		return 0L;
	}

	public static Integer toInt(Object number) {
		final String CADENA_A_REEMPLAZAR = "\"";
		Integer entero = 0;

		if (number != null && (NumberUtils.isCreatable(number.toString().trim().replaceAll(CADENA_A_REEMPLAZAR, "")))) {
			Double d = NumberUtils.toDouble(number.toString().trim().replaceAll(CADENA_A_REEMPLAZAR, ""));
			entero = d.intValue();
		}

		return Integer.parseInt(Integer.toString(entero));
	}

	public static String objectToJsonString(Object objeto) {
		Gson gson = new Gson();
		String json = gson.toJson(objeto);
		return json;
	}

	public static final String getCodigoAPI(int codigoHTTP, String API_NAME) {
		return codigoHTTP + "." + Constantes.PREFIJO_API + "-" + API_NAME;
	}
}
