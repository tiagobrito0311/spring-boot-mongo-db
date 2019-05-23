/**
 * @author               Tiago Brito
 * @Date                 20/04/2018
 */
package br.com.tiagobrito.exception;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.tiagobrito.helper.Helper;
 

// TODO: Auto-generated Javadoc
/**
 * The Class PlanetasException.
 */
public class PlanetasException extends Exception implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2838021031046304638L;

	/** The log. */
	private final Logger LOG = LoggerFactory.getLogger(Helper.class);
	 
	
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
 		LOG.error(msg + " - " +e);

	}
 

}
