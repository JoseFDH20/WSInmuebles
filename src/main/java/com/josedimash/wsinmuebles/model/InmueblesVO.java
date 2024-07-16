package com.josedimash.wsinmuebles.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InmueblesVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer fiid;
	
	@Column
	private String fcnombre;
	
	@Column
	private String fcdireccion;
	
	@Column
	private String fctelefono;
	
	@Column
	private Date fdfechaalta;
	
	@Column
	private Date fdfechamodificacion;
	
	@Column
	private String fcusuariomodifico;

	public Integer getFiid() {
		return fiid;
	}

	public void setFiid(Integer fiid) {
		this.fiid = fiid;
	}

	public String getFcnombre() {
		return fcnombre;
	}

	public void setFcnombre(String fcnombre) {
		this.fcnombre = fcnombre;
	}

	public String getFcdireccion() {
		return fcdireccion;
	}

	public void setFcdireccion(String fcdireccion) {
		this.fcdireccion = fcdireccion;
	}

	public String getFctelefono() {
		return fctelefono;
	}

	public void setFctelefono(String fctelefono) {
		this.fctelefono = fctelefono;
	}

	public Date getFdfechaalta() {
		return fdfechaalta;
	}

	public void setFdfechaalta(Date fdfechaalta) {
		this.fdfechaalta = fdfechaalta;
	}

	public Date getFdfechamodificacion() {
		return fdfechamodificacion;
	}

	public void setFdfechamodificacion(Date fdfechamodificacion) {
		this.fdfechamodificacion = fdfechamodificacion;
	}

	public String getFcusuariomodifico() {
		return fcusuariomodifico;
	}

	public void setFcusuariomodifico(String fcusuariomodifico) {
		this.fcusuariomodifico = fcusuariomodifico;
	}
	
}
