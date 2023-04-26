package com.projedata.folha.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Util {
	
	
	public static String formatarDecimal(BigDecimal valor){
		
		return NumberFormat.getCurrencyInstance().format(valor);
	}
	
	

}
