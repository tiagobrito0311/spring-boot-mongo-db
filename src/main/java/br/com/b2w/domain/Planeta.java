package br.com.b2w.domain;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planeta")
public class Planeta implements Serializable{
 
	private static final long serialVersionUID = 1L;
	@Id
    private ObjectId  id;
    @Indexed(unique = true)
    private String nome;
    private String clima;
    private String terreno;
    private int apariacoes;
	public ObjectId  getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getClima() {
		return clima;
	}
	public void setClima(String clima) {
		this.clima = clima;
	}
	public String getTerreno() {
		return terreno;
	}
	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}
	public int getApariacoes() {
		return apariacoes;
	}
	public void setApariacoes(int apariacoes) {
		this.apariacoes = apariacoes;
	}
	
	@Override
	public String toString() {
		return "Planeta [nome=" + nome + ", clima=" + clima + ", terreno=" + terreno + ", apariacoes=" + apariacoes
				+ "]";
	}
	
	

     
}