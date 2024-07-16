package com.josedimash.wsinmuebles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.josedimash.wsinmuebles.model.InmueblesVO;

@Repository
public interface InmueblesRepository extends JpaRepository<InmueblesVO, Integer> {

	@Query(value = "SELECT * FROM FNALLINMUEBLES();", nativeQuery = true)
	List<InmueblesVO> obtenerTodos();

	@Query(value = "SELECT * FROM FNFINDINMUEBLEBYID(:PA_IDINMUEBLE);", nativeQuery = true)
	InmueblesVO obtenerByID(@Param("PA_IDINMUEBLE") Integer idInmueble);
	
	@Query(value = "SELECT * FROM FNADDINMUEBLE(:PA_NOMBRE, :PA_DIRECCION, :PA_TELEFONO);", nativeQuery = true)
	InmueblesVO agregarNuevoInmuebleByID(@Param("PA_NOMBRE") String paNombre
			, @Param("PA_DIRECCION") String paDdireccion 
			, @Param("PA_TELEFONO") String paTelefono);
	
	@Query(value = "SELECT * FROM FNUPDINMUEBLEBYID(:PA_IDINMUEBLE, :PA_NOMBRE, :PA_DIRECCION, :PA_TELEFONO);", nativeQuery = true)
	InmueblesVO actualizarInmuebleByID(@Param("PA_IDINMUEBLE") Integer idInmueble
			, @Param("PA_NOMBRE") String paNombre
			, @Param("PA_DIRECCION") String paDdireccion 
			, @Param("PA_TELEFONO") String paTelefono);
	
	@Query(value = "SELECT * FROM FNDELINMUEBLEBYID(:PA_IDINMUEBLE);", nativeQuery = true)
	Integer eliminarInmuebleByID(@Param("PA_IDINMUEBLE") Integer idInmueble);
	
}
