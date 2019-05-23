package br.com.tiagobrito.dto;

import java.util.List;

public class ResultDTO {
	
	private int count;
	private String next;
	private String previous;
	private List<DetalheResultDTO> results;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public List<DetalheResultDTO> getResults() {
		return results;
	}
	public void setResults(List<DetalheResultDTO> results) {
		this.results = results;
	}
	
	

}
