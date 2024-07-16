package com.josedimash.wsinmuebles.interceptor;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.josedimash.wsinmuebles.model.GenericResponse;
import com.josedimash.wsinmuebles.util.Constantes;
import com.josedimash.wsinmuebles.util.RestResponseBuilder;

/**
 * Clase manejar respuestas HTTP de errores genericos
 */
@RestControllerAdvice
public class GlobalControllerAdvice {


    @ExceptionHandler(Throwable.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericResponse problem(final Throwable e) {
        return GenericResponse.builder()
                .api("")
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .backendStatus(Constantes.HTTP_BACKEND_CODE_SERVER_ERROR)
                .mensaje("Error de Servidor")
                .detalle(e.getMessage())
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public GenericResponse handleAccessDeniedException(AccessDeniedException ex) {
        return GenericResponse.builder()
                .api("")
                .httpStatus(HttpStatus.FORBIDDEN)
                .backendStatus(Constantes.HTTP_BACKEND_CODE_FORBIDDEN)
                .mensaje("Acceso denegado")
                .detalle(ex.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public GenericResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            errors.add(error);
        }

        //Object result=ex.getBindingResult();//instead of above can allso pass the more detailed bindingResult

        RestResponseBuilder builder = GenericResponse.builder();
        builder
                .api("")
                .httpStatus(HttpStatus.BAD_REQUEST)
                .backendStatus(Constantes.HTTP_BACKEND_CODE_BAD_REQUEST)
                .mensaje("Argumentos de metodo no validos");

        errors.stream().forEach(d -> builder.detalle(d));

        return builder.build();



    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public GenericResponse handleConstraintViolatedException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();


        List<String> errors = new ArrayList<>(constraintViolations.size());
        String error;
        for (ConstraintViolation constraintViolation : constraintViolations) {

            error = constraintViolation.getMessage();
            errors.add(error);
        }

        RestResponseBuilder builder = GenericResponse.builder();
        builder
                .api("")
                .httpStatus(HttpStatus.BAD_REQUEST)
                .backendStatus(Constantes.HTTP_BACKEND_CODE_BAD_REQUEST)
                .mensaje("Violacion de Restricciones");

        errors.stream().forEach(d -> builder.detalle(d));

        return builder.build();


    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public GenericResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {

        List<String> errors = new ArrayList<>();
        String error = ex.getParameterName() + ", " + ex.getMessage();
        errors.add(error);

        RestResponseBuilder builder = GenericResponse.builder();
        builder
                .api("")
                .httpStatus(HttpStatus.BAD_REQUEST)
                .backendStatus(Constantes.HTTP_BACKEND_CODE_BAD_REQUEST)
                .mensaje("Argumentos Faltantes");

        errors.stream().forEach(d -> builder.detalle(d));

        return builder.build();

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public GenericResponse handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return GenericResponse.builder()
                .api("")
                .httpStatus(HttpStatus.BAD_REQUEST)
                .backendStatus(Constantes.HTTP_BACKEND_CODE_BAD_REQUEST)
                .mensaje("Mensaje no legible")
                .detalle(ex.getMessage())
                .build();
    }


    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public GenericResponse handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        String unsupported = "Content Type no soportado: " + ex.getContentType();
        String supported = "Content Types soportados: " + MediaType.toString(ex.getSupportedMediaTypes());

        return GenericResponse.builder()
                .api("")
                .httpStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .backendStatus(Constantes.HTTP_BACKEND_CODE_UNSUPPORTED_MEDIA_TYPE)
                .mensaje("Media Type no soportado")
                .detalle(unsupported)
                .detalle(supported)
                .build();
    }

}
