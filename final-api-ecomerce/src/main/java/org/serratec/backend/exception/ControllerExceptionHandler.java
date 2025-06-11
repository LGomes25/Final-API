package org.serratec.backend.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<String> erros = new ArrayList<>();

		for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
			erros.add(erro.getField() + ":" + erro.getDefaultMessage());
		}
		ErroResposta erroResposta = new ErroResposta(status.value(), "Existem campos inválidos", LocalDateTime.now(),
				erros);

		return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<String> erros = new ArrayList<>();
		erros.add(ex.getMessage());
		ErroResposta erroResposta = new ErroResposta(status.value()
				,"Existem campos inválidos"
				,LocalDateTime.now()
				,erros);
		return super.handleExceptionInternal(ex, erroResposta, headers, status, request);

	}

	@ExceptionHandler(ClienteException.class)
	protected ResponseEntity<Object> handleClienteException(ClienteException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
	    String mensagemErro = ex.getConstraintViolations()
	        .stream()
	        .map(ConstraintViolation::getMessage)
	        .findFirst()
	        .orElse("Erro de validação.");
	    
	   return ResponseEntity.badRequest().body(mensagemErro);
	}

	@ExceptionHandler(ProdutoException.class)
	public ResponseEntity<Object> handleProdutoException(ProdutoException ex, WebRequest request) {
		List<String> erros = new ArrayList<>();
		erros.add(ex.getMessage());
		ErroResposta erroResposta = new ErroResposta(
				HttpStatus.BAD_REQUEST.value(),
				"Erro no Produto",
				LocalDateTime.now(),
				erros
		);
		return new ResponseEntity<>(erroResposta, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IsbnException.class)
	protected ResponseEntity<Object> handleIsbnException(IsbnException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(CategoriaException.class)
	protected ResponseEntity<Object> handleCategoriaException(CategoriaException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(FuncionarioException.class)
	protected ResponseEntity<Object> handleFuncionarioException(FuncionarioException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(AvaliacaoExistenteException.class)
	protected ResponseEntity<Object> handleAvaliacaoExistenteException(AvaliacaoExistenteException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(ListadeDesejosException.class)
	protected ResponseEntity<Object> handleListadeDesejosException(ListadeDesejosException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(CupomException.class)
	protected ResponseEntity<Object> handleCupomException(CupomException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
