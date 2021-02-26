package fr.doranco.ecommerce.utils;

import java.util.Date;

public abstract class OrderUtils {
	
	public static final String generateOrderNumber(Integer userId) {
		String baseDateStr = Dates.customDateToString(new Date(System.currentTimeMillis()), "_yyyyMMdd_hhmmss");
		return "C_" + userId + baseDateStr;
	}
}
