package br.com.cadeup.blog.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Error {

	private boolean error;
	private String detail;
	
	private Error() {}
	
	@Override
	public String toString() {
		return "{error: " + this.error + ", detail: " + this.detail + "}";
	}
}
