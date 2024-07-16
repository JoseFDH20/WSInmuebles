package com.josedimash.wsinmuebles.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josedimash.wsinmuebles.model.InmueblesVO;
import com.josedimash.wsinmuebles.repository.InmueblesRepository;

@Service
public class InmueblesServiceImpl implements InmueblesService {
	private static final Logger LOGGER = LogManager.getLogger(InmueblesServiceImpl.class);

	@Autowired
	private InmueblesRepository inmueblesRepository;
	
	@Override
	public List<InmueblesVO> obtenerTodos(){
		List<InmueblesVO> list = new ArrayList<>();
		
		try {
			list = inmueblesRepository.obtenerTodos();
		}
		catch(Exception ex) {
			LOGGER.info(ex);
		}
		
		return list;
	}
	
	@Override
	public InmueblesVO obtenerByID(Integer idInmueble){
		InmueblesVO item = new InmueblesVO();
		
		try {
			item = inmueblesRepository.obtenerByID(idInmueble);
		}
		catch(Exception ex) {
			LOGGER.info(ex);
		}
		
		return item;
	}
	
	@Override
	public InmueblesVO agregarNuevoInmuebleByID(String paNombre, String paDdireccion, String paTelefono) {
		InmueblesVO item = new InmueblesVO();
		
		try {
			item = inmueblesRepository.agregarNuevoInmuebleByID(paNombre, paDdireccion, paTelefono);
		}
		catch(Exception ex) {
			LOGGER.info(ex);
		}
		
		return item;
	}
	
	@Override
	public 	InmueblesVO actualizarInmuebleByID(Integer idInmueble, String paNombre, String paDdireccion, String paTelefono) {
		InmueblesVO item = new InmueblesVO();
		
		try {
			item = inmueblesRepository.actualizarInmuebleByID(idInmueble, paNombre, paDdireccion, paTelefono);
		}
		catch(Exception ex) {
			LOGGER.info(ex);
		}
		
		return item;
	}
	
	@Override
	public Integer eliminarInmuebleByID(Integer idInmueble){
		Integer result = 0;
		
		try {
			result = inmueblesRepository.eliminarInmuebleByID(idInmueble);
		}
		catch(Exception ex) {
			result = 0;
			LOGGER.info(ex);
		}
		
		return result;
	}
	
}
