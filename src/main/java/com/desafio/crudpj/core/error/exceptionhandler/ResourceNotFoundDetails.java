package com.desafio.crudpj.core.error.exceptionhandler;

//Padrão de projeto Builder 
public class ResourceNotFoundDetails {
	private String title;
	private int status;
	private String detail;
	private long timestamp;
	private String developerMessage;
	
	
	public String getTitle() {
		return title;
	}

	public int getStatus() {
		return status;
	}

	public String getDetail() {
		return detail;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}
	
//	Metodo Contrutor
	public ResourceNotFoundDetails(String title, int status, String detail, long timestamp, String developerMessage) {
		this.title = title;
		this.status = status;
		this.detail = detail;
		this.timestamp = timestamp;
		this.developerMessage = developerMessage;
	}

//	Classe dentro de classe
	public static final class Builder{
		private String title;
		private int status;
		private String detail;
		private long timestamp;
		private String developerMessage;
		
		public Builder() {
			
		}
		
		public static Builder newBuilder() {
			return new Builder();
		}
//		Cada método retorna recursivamente a classe Builder
		public Builder title(String title) {
			this.title = title;
			return this;	
		}
		public Builder status(int status) {
			this.status = status;
			return this;	
		}
		public Builder detail(String detail) {
			this.detail = detail;
			return this;	
		}
		public Builder timestamp(long timestamp) {
			this.timestamp = timestamp;
			return this;	
		}
		public Builder developerMessage(String developerMessage) {
			this.developerMessage = developerMessage;
			return this;	
		}
		
		public ResourceNotFoundDetails builder() {
			return new ResourceNotFoundDetails(title, status, detail, timestamp, developerMessage);
			
		}
	}

}
