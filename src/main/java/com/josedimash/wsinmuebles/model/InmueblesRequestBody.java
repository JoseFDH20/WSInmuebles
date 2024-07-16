package com.josedimash.wsinmuebles.model;

public class InmueblesRequestBody {
	
	private String nombre;
	
	private String direccion;
	
	private String telefono;
	
	public InmueblesRequestBody() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
