/**
 * @author               Tiago Brito
 * @Date                 20/04/2018
 */

package br.com.b2w.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
 

// TODO: Auto-generated Javadoc
/**
 * The Class DateHelper.
 */
public class DateHelper {
	
	/** The Constant FORMAT_DATE_2. */
	static final String FORMAT_DATE_2 = "dd/MM/yyyy HH:mm:ss";
	
	/** The Constant LC_BRAZIL. */
	static final Locale LC_BRAZIL = new Locale("pt", "BR");


	/** The Constant dateFormat. */
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE_2,LC_BRAZIL);

	/**
	 * Retorna data formatada.
	 *
	 * @param date the date
	 * @return the string
	 */
	public static String retornaDataFormatada(Date date){
		
		return dateFormat.format(date);
	}
}
