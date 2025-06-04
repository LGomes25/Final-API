package org.serratec.backend.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CouponDTO {

    private Long id;

    @NotBlank(message = "O código do cupom não pode ser vazio")
    @Size(max = 50, message = "O código deve ter no máximo 50 caracteres")
    private String code;

    @NotNull(message = "O valor de desconto não pode ser nulo")
    @DecimalMin(value = "0.00", inclusive = false, message = "O desconto deve ser maior que 0")
    private BigDecimal discountValue;

    @NotNull(message = "A data de validade não pode ser nula")
    @FutureOrPresent(message = "A data de validade deve ser hoje ou futura")
    private LocalDate expiryDate;

    private Boolean used;

    // Construtores
    public CouponDTO() {
    }

    public CouponDTO(Long id, String code, BigDecimal discountValue, LocalDate expiryDate, Boolean used) {
        this.id = id;
        this.code = code;
        this.discountValue = discountValue;
        this.expiryDate = expiryDate;
        this.used = used;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }
}
