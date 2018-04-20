/**
 * @author               Tiago Brito
 * @Date                 20/04/2018
 */
package br.com.b2w.exception;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.b2w.helper.DateHelper;
import br.com.b2w.helper.Helper;
 

// TODO: Auto-generated Javadoc
/**
 * The Class PlanetasException.
 */
public class PlanetasException extends Exception implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2838021031046304638L;

	/** The log. */
	private final Logger LOG = LoggerFactory.getLogger(Helper.class);
	
	/** The Constant PKG. */
	private static final String PKG ="br.com.b2w";

	
	/**
	 * Instantiates a new planetas exception.
	 *
	 * @param String erro
	 */
	public PlanetasException(final String erro){
		super(erro);
		LOG.error(erro);


	}
	
	/**
	 * Instantiates a new planetas exception.
	 *
	 * @param msg the msg
	 * @param e the e
	 */
	public PlanetasException(final String msg, final Exception e) {
		String msgErro = retornaMsgErro(msg, e);
		LOG.error(msgErro);

	}

	/**
	 * Retorna msg erro.
	 *
	 * @param msg the msg
	 * @param e the e
	 * @return the string
	 */
	public String retornaMsgErro(final String msg, final Exception e) {

		StringBuilder sb = new StringBuilder();
		sb.append(" [Data]: ");
		sb.append(DateHelper.retornaDataFormatada(new Date()));
		sb.append("\n");
		int nivel = 1;
		for (StackTraceElement l : e.getStackTrace()) {
			if (l.getClassName().contains(PKG)) {
				sb.append(" [Nivel]: ");
				sb.append(nivel);
				sb.append(" [Classe]: ");
				sb.append(l.getClassName());
				sb.append(" [Metodo]: ");
				sb.append(l.getMethodName());
				sb.append(" [Linha]: ");
				sb.append(l.getLineNumber());
				sb.append("\n");
				nivel += 1;
			}

		}

		sb.append(" ");
		sb.append("[Exception]: ");
		sb.append(e);
		sb.append("\n");
		sb.append("[Mensagem]: ");
		sb.append(msg);

		return sb.toString();
	}

}
