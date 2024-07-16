package com.josedimash.wsinmuebles.model;

import java.io.Serializable;

import com.josedimash.wsinmuebles.util.RestResponseBuilder;
import com.josedimash.wsinmuebles.util.Utilerias;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Clase con la finalidad de devolver un response generico
 *
 */
@Schema(description = "Respuesta generica a llamados del Api")
public class GenericResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Schema(example = "Codigo para poder mapear el servicio consultado. Formato: HTTPStatus.NombreServicio")
	private String codigo;
	
	@Schema(example = "Mensaje de la operacion")
	private String mensaje;
	
	@Schema(example = "Data")
	private Object data;

	public GenericResponse() {
		super();
	}

	public GenericResponse(String codigo, String mensaje, Object data) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.data = data;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static RestResponseBuilder builder() {
		return new RestResponseBuilder();
	}

    public String toJson() {
        return Utilerias.objectToJsonString(this);
    }
}
