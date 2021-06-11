package com.claro.sefisf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipdoc")  
public class TipDoc {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer idTipdoc;
	@Column(name = "descripcion")
	private String descripcion;
	
	public TipDoc() {
		
	}	
	public Integer getIdTipdoc() {
		return idTipdoc;
	}
	public void setIdTipdoc(Integer idTipdoc) {
		this.idTipdoc = idTipdoc;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
