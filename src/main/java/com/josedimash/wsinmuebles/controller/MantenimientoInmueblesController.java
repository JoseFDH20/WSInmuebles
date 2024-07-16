package com.josedimash.wsinmuebles.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josedimash.wsinmuebles.model.GenericResponse;
import com.josedimash.wsinmuebles.model.InmueblesRequestBody;
import com.josedimash.wsinmuebles.model.InmueblesVO;
import com.josedimash.wsinmuebles.service.InmueblesService;
import com.josedimash.wsinmuebles.util.Constantes;
import com.josedimash.wsinmuebles.util.Utilerias;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping(value = "/inmuebles")
@Tag(name = "Mantenimiento de Inmuebles", description = "WS para administrar la información de los inmuebles.")
//@Hidden//No documentar
public class MantenimientoInmueblesController {
		
	@Autowired
	private InmueblesService inmueblesService;

	@GetMapping("/")
    @Operation(
            summary = "Para obtener una lista de todos los inmuebles",
            description = "Usar este endpoint para obtener todos los inmuebles"
    )
	public ResponseEntity<GenericResponse> obtenerTodos() {
		List<InmueblesVO> lista = new ArrayList<>();
		
		lista = inmueblesService.obtenerTodos();
		
		return ResponseEntity.ok().body(
                GenericResponse.builder()
                        .api("inmuebles/")
                        .httpStatus(HttpStatus.OK)
                        .backendStatus(Constantes.HTTP_BACKEND_CODE_OK)
                        .mensaje("Operación Exitosa")
                        .data(lista)
                        .build()
        );
	}
	
	@GetMapping("/{idInmueble}")
    @Operation(
            summary = "Para obtener el detalle de un inmueble",
            description = "Usar este endpoint para obtener el detalle de un inmueble por su ID"
    )
	public ResponseEntity<GenericResponse> obtenerByID(@PathVariable String idInmueble) {
		InmueblesVO item = new InmueblesVO();
		
		item = inmueblesService.obtenerByID(Utilerias.toInt(idInmueble));
		if(item==null) {
			return ResponseEntity.badRequest().body(
                    GenericResponse.builder()
                    		.api("inmuebles/"+idInmueble)
                            .httpStatus(HttpStatus.BAD_REQUEST)
                            .backendStatus(Constantes.HTTP_BACKEND_CODE_BAD_REQUEST)
                            .mensaje("No se encontro información.")
                            .detalle("No existe el inmueble con ID:"+ idInmueble)
                            .build()
            );
		}
		else {
			return ResponseEntity.ok().body(
	                GenericResponse.builder()
	                        .api("inmuebles/"+idInmueble)
	                        .httpStatus(HttpStatus.OK)
	                        .backendStatus(Constantes.HTTP_BACKEND_CODE_OK)
	                        .mensaje("Operación Exitosa")
	                        .data(item)
	                        .build()
	        );
		}
	}
	
	@PostMapping("/")
    @Operation(
            summary = "Para agregar un nuevo inmueble",
            description = "Usar este endpoint para agregar un nuevo inmueble al catalogo"
    )
	public ResponseEntity<GenericResponse> agregarNuevoInmueble(@NonNull @RequestBody InmueblesRequestBody inmueblesRequestBody) {
		InmueblesVO item = new InmueblesVO();
		
		item = inmueblesService.agregarNuevoInmuebleByID(inmueblesRequestBody.getNombre(),
				inmueblesRequestBody.getDireccion(), inmueblesRequestBody.getTelefono());

		if(item==null) {
			return ResponseEntity.badRequest().body(
                    GenericResponse.builder()
                    		.api("inmuebles/")
                            .httpStatus(HttpStatus.BAD_REQUEST)
                            .backendStatus(Constantes.HTTP_BACKEND_CODE_BAD_REQUEST)
                            .mensaje("No se logro cargar los datos del inmueble.")
                            .detalle("Por favor, revise que los campos esten correctos")
                            .build()
            );
		}
		else {
			return ResponseEntity.ok().body(
	                GenericResponse.builder()
	                        .api("inmuebles/")
	                        .httpStatus(HttpStatus.OK)
	                        .backendStatus(Constantes.HTTP_BACKEND_CODE_OK)
	                        .mensaje("Operación Exitosa")
	                        .data(item)
	                        .build()
	        );
		}
	}
	
	@PutMapping("/{idInmueble}")
    @Operation(
            summary = "Para actualizar el detalle de un inmueble",
            description = "Usar este endpoint para actualizar el detalle de un inmueble por su ID"
    )
	public ResponseEntity<GenericResponse> actualizarInmuebleByID(@PathVariable String idInmueble,
			@NonNull @RequestBody InmueblesRequestBody inmueblesRequestBody) {
		InmueblesVO item = new InmueblesVO();
		
		item = inmueblesService.actualizarInmuebleByID(Utilerias.toInt(idInmueble), inmueblesRequestBody.getNombre(),
				inmueblesRequestBody.getDireccion(), inmueblesRequestBody.getTelefono());

		if(item==null) {
			return ResponseEntity.badRequest().body(
                    GenericResponse.builder()
                    		.api("inmuebles/"+idInmueble)
                            .httpStatus(HttpStatus.BAD_REQUEST)
                            .backendStatus(Constantes.HTTP_BACKEND_CODE_BAD_REQUEST)
                            .mensaje("No se encontro información.")
                            .detalle("No existe el inmueble con ID:"+ idInmueble)
                            .build()
            );
		}
		else {
			return ResponseEntity.ok().body(
	                GenericResponse.builder()
	                        .api("inmuebles/"+idInmueble)
	                        .httpStatus(HttpStatus.OK)
	                        .backendStatus(Constantes.HTTP_BACKEND_CODE_OK)
	                        .mensaje("Operación Exitosa")
	                        .data(item)
	                        .build()
	        );
		}
	}

	@DeleteMapping("/{idInmueble}")
    @Operation(
            summary = "Para eliminar un inmueble",
            description = "Usar este endpoint para eliminar un inmueble por su ID"
    )
	public ResponseEntity<GenericResponse> eliminarInmuebleByID(@PathVariable String idInmueble) {
		Integer result = 0;
		
		result = inmueblesService.eliminarInmuebleByID(Utilerias.toInt(idInmueble));
		if(result==null || result==0) {
			return ResponseEntity.badRequest().body(
                    GenericResponse.builder()
                    		.api("inmuebles/"+idInmueble)
                            .httpStatus(HttpStatus.BAD_REQUEST)
                            .backendStatus(Constantes.HTTP_BACKEND_CODE_BAD_REQUEST)
                            .mensaje("No se elimino el inmueble.")
                            .detalle("No se logro eliminar el inmueble con ID:"+ idInmueble)
                            .build()
            );
		}
		if(result==2) {
			return ResponseEntity.badRequest().body(
                    GenericResponse.builder()
                    		.api("inmuebles/"+idInmueble)
                            .httpStatus(HttpStatus.BAD_REQUEST)
                            .backendStatus(Constantes.HTTP_BACKEND_CODE_BAD_REQUEST)
                            .mensaje("El inmueble no existe.")
                            .detalle("No existe el inmueble con ID:"+ idInmueble)
                            .build()
            );
		}
		else {
			return ResponseEntity.ok().body(
	                GenericResponse.builder()
	                        .api("inmuebles/"+idInmueble)
	                        .httpStatus(HttpStatus.OK)
	                        .backendStatus(Constantes.HTTP_BACKEND_CODE_OK)
	                        .mensaje("Operación Exitosa")
	                        .detalle("Se elimino el inmueble con ID:"+ idInmueble)
	                        .build()
	        );
		}
	}
}
