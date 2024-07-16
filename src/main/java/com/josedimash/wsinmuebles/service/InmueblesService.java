package com.josedimash.wsinmuebles.service;

import java.util.List;

import com.josedimash.wsinmuebles.model.InmueblesVO;

public interface InmueblesService {

	List<InmueblesVO> obtenerTodos();
	
	InmueblesVO obtenerByID(Integer idInmueble);

	InmueblesVO agregarNuevoInmuebleByID(String paNombre, String paDdireccion, String paTelefono);

	InmueblesVO actualizarInmuebleByID(Integer idInmueble, String paNombre, String paDdireccion, String paTelefono);
	
	Integer eliminarInmuebleByID(Integer idInmueble);
}
