/**
 * @author               Tiago Brito
 * @Date                 {date}
 */
package br.com.tiagobrito.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class MensagemDTO.
 */
public class MensagemDTO {
	
	/** The codigo. */
	private String codigo;
	
	/** The mensagem. */
	private String mensagem;
	
	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Gets the mensagem.
	 *
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}
	
	/**
	 * Sets the mensagem.
	 *
	 * @param mensagem the new mensagem
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{\"codigo\":\"" + codigo + "\", \"mensagem\":\"" + mensagem + "\"}";
	}
	
	

}
