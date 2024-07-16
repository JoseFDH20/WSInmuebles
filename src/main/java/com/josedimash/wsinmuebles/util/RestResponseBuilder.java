package com.josedimash.wsinmuebles.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.josedimash.wsinmuebles.model.GenericResponse;

public class RestResponseBuilder {

    private String api;
    private int httpStatus;
    private int backendStatus;
    private String mensaje;
    private List<String> detalles = new ArrayList<>();
    private Object data;

    public RestResponseBuilder api(String api) {
        this.api = api;
        return this;
    }

    public RestResponseBuilder httpStatus(HttpStatus status) {
        this.httpStatus = status.value();

        return this;
    }

    public RestResponseBuilder backendStatus (int backendStatus) {
        this.backendStatus = backendStatus;
        return this;
    }

    public RestResponseBuilder mensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    public RestResponseBuilder detalle(String detalle) {
    	this.detalles.add(detalle);
        return this;
    }
    
    public RestResponseBuilder data(Object data) {
        this.data = data;
        return this;
    }


    public GenericResponse build() {
        return new GenericResponse(
                Utilerias.getCodigoAPI(httpStatus, api),
                mensaje,
                data
        );
    }


    public ResponseEntity<GenericResponse> entity() {
        return ResponseEntity.status(httpStatus).headers(HttpHeaders.EMPTY).body(build());
    }

    public String json() {
        return build().toJson();
    }

}
