/**
 * @author               Tiago Brito
 * @Date                 20/04/2018
 */
 
package br.com.b2w.domain;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class Planeta.
 */
@Document(collection = "planeta")
public class Planeta implements Serializable{
 
		/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private static final String MSG_NOT_BLANK="O campo não pode ser nulo ou vazio.";
	/** The id. */
	@Id
    private String   id;
    
    /** The nome. */
    @Indexed(unique = true)
    @NotBlank(message = MSG_NOT_BLANK)
    private String nome;
    
    /** The clima. */
    @NotBlank(message = MSG_NOT_BLANK)
    private String clima;
    
    /** The terreno. */
    @NotBlank(message = MSG_NOT_BLANK)
    private String terreno;
    
    /** The apariacoes. */
    private int apariacoes;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String   getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String  id) {
		this.id = id;
	}
	
	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Gets the clima.
	 *
	 * @return the clima
	 */
	public String getClima() {
		return clima;
	}
	
	/**
	 * Sets the clima.
	 *
	 * @param clima the new clima
	 */
	public void setClima(String clima) {
		this.clima = clima;
	}
	
	/**
	 * Gets the terreno.
	 *
	 * @return the terreno
	 */
	public String getTerreno() {
		return terreno;
	}
	
	/**
	 * Sets the terreno.
	 *
	 * @param terreno the new terreno
	 */
	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}
	
	/**
	 * Gets the apariacoes.
	 *
	 * @return the apariacoes
	 */
	public int getApariacoes() {
		return apariacoes;
	}
	
	/**
	 * Sets the apariacoes.
	 *
	 * @param apariacoes the new apariacoes
	 */
	public void setApariacoes(int apariacoes) {
		this.apariacoes = apariacoes;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Planeta [nome=" + nome + ", clima=" + clima + ", terreno=" + terreno + ", apariacoes=" + apariacoes
				+ "]";
	}
	
	

     
}