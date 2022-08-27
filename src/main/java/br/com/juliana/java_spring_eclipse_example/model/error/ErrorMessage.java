package br.com.juliana.java_spring_eclipse_example.model.error;

public class ErrorMessage {
	private String titulo;
	private Integer statusError;
	private String mensagem;
	
	
	
	
	public ErrorMessage(String titulo, Integer statusError, String mensagem) {
		super();
		this.titulo = titulo;
		this.statusError = statusError;
		this.mensagem = mensagem;
	}
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Integer getStatusError() {
		return statusError;
	}
	public void setStatusError(Integer statusError) {
		this.statusError = statusError;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	

}
