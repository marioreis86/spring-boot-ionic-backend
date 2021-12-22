package com.mariopimenta.cursomc.resources.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String msg;
	private Long timestamp;
	
	public StandardError(Integer status, String msg, Long timestamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timestamp = timestamp;
	}
}
