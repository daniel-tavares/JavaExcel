package util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class ConverterDados {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault());
	private static DecimalFormat df = new DecimalFormat("###0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

	public ConverterDados() {

	}

	public static String removeCaracteres(String s) {
		String xmlAlterado = s.replace("&", "&amp;").replace(">", "&gt;").replace("<", "&lt;").replace("\"", "&quot;");
		return xmlAlterado;
	}

	public static String getBigDecimalToString(BigDecimal valor) {

		if (valor != null) {
			return valor.toString();
		}
		return null;
	}

	public static String getCharToString(char valor) {
		return String.valueOf(valor);
	}

	public static String getLongToString(Long valor) {

		if (valor != null) {
			return String.valueOf(valor);
		}
		return "";
	}

	public static String getBigintToString(BigInteger valor) {
		if (valor != null) {
			return String.valueOf(valor.intValue());
		}

		return null;
	}
	

	public static Long getShortToLong(Short valor) {
		if (valor != null) {
			return Long.parseLong(String.valueOf(valor));
		}

		return null;
	}

	public static Long getIntegerToLong(Integer valor) {

		if (valor != null) {
			return Long.parseLong(valor.toString());
		}
		return null;
	}

	public static String getIntegerToString(Integer valor) {

		if (valor != null) {
			return String.valueOf(valor);
		}
		return null;
	}

	public static String getShortToString(Short valor) {

		if (valor != null) {
			return String.valueOf(valor);
		}
		return "";
	}

	public static String getDateUtilToDateUTC(Date data) {
		if (data != null) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(data);
			XMLGregorianCalendar xmlCalendar = null;
			try {
				xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
				xmlCalendar.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);

				return (xmlCalendar.toString());
			} catch (DatatypeConfigurationException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public static String completeToLeft(String value, char c, int size) {
		String result = value;
		while (result.length() < size) {
			result = c + result;
		}
		return result;
	}

	public static String lpadTo(String input, int width, char ch) {
		String strPad = "";

		StringBuffer sb = new StringBuffer(input.trim());
		while (sb.length() < width)
			sb.insert(0, ch);
		strPad = sb.toString();

		if (strPad.length() > width) {
			strPad = strPad.substring(0, width);
		}
		return strPad;
	}

	public static String getDataFormatada(Date data) {
		if (data != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(data);
		}
		return null;
	}

	public static Long getStringToLong(String valor){
		 if(valor!=null){

		 	try {
				return Long.parseLong(valor);
			} catch (NumberFormatException e) {
		 		return null;
			}
		 }
		return null; 
	 }
	 

	public static String getDataEmissaoFormatada(Date data) {
		if (data != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
			return sdf.format(data);
		}
		return null;
	}



	public static Integer getStringToInteger(String valor) {
		if (valor != null) {
			return Integer.parseInt(valor);
		}
		return null;
	}

	public static String formataMoedaWs(Double valor) throws Exception {
		if (valor != null) {
			return df.format(valor).replace(".", ",");
		}

		return "";
	}

	public static Date getDateUTCToDateUtil(String data) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		if (data != null && !data.equals("")) {
			try {

				return format.parse(data.replace("T", " "));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	

	public static String getQuatroCasasDecimais(BigDecimal b ){
		if(b!=null)
		   return getApplicationDecimalFormat(4).format(b.doubleValue()).replace(",", ".");
		else
		   return getApplicationDecimalFormat(4).format(0d).replace(",", ".");	
	}
	
	public static String getDuasCasasDecimais(BigDecimal b ){
		if(b!=null)
		   return getApplicationDecimalFormat(2).format(b.doubleValue()).replace(",", ".");
		else
		   return getApplicationDecimalFormat(2).format(0d).replace(",", ".");	
	}
	
	 private static DecimalFormat getApplicationDecimalFormat(int numeroCasas) {
	        Locale brasil = new Locale( "pt", "BR" );
	        DecimalFormat numberFormat = new DecimalFormat( "#######0.0000", new DecimalFormatSymbols( brasil ) );
	        numberFormat.setParseBigDecimal( true );
	        numberFormat.setDecimalSeparatorAlwaysShown( true );
	        numberFormat.setMinimumFractionDigits(numeroCasas);
	        return numberFormat;
	 }
 
	 public static String getStackTraceFromException(Exception e, final int limitLength){
	        StringBuilder builder = new StringBuilder();
	        if(e != null){
	            builder.append("Caused by: "+e+"\n");
	            if(e.getStackTrace() != null){
	                for(StackTraceElement ste : e.getStackTrace()){
	                    builder.append(ste+"\n");
	                }
	            }
	            if(e.getCause() != null){
	                builder.append("\nCaused by: "+e.getCause()+"\n");
	                if(e.getCause().getStackTrace() != null){
	                    for(StackTraceElement ste : e.getCause().getStackTrace()){
	                        builder.append(ste+"\n");
	                    }
	                }
	                if(e.getCause().getCause() != null){
	                    builder.append("\nCaused by: "+e.getCause().getCause()+"\n");
	                    if(e.getCause().getCause().getStackTrace() != null){
	                        for(StackTraceElement ste : e.getCause().getCause().getStackTrace()){
	                            builder.append(ste+"\n");
	                        }
	                    }
	                }
	            }
	        }
	        if(builder.length() > limitLength){
	            return builder.toString().substring(0, limitLength);
	        }
	        return builder.toString();
	    }
	 
	 
	 public  static String tratarValorMoeda(String valor){
			if(valor!=null && !valor.equals(""))
				return "\'"+valor.replace("$", "").replace(".","").trim()+"\'";
			
			return null;
	}
	 
	public static void main(String[] args) {
		String valor="Calculado sobre o débito do ICMS.";
		System.out.println(padronizaString(valor));
	} 
	public static String  padronizaString(String valor){
		return valor.replaceAll("[' ']","")
				    .replaceAll("[éêè]", "e")
				    .replaceAll("[çÇ]","c")
				    .replaceAll("[ãáàâ]", "a")
				    .replace(".", "").toUpperCase();
	}
}