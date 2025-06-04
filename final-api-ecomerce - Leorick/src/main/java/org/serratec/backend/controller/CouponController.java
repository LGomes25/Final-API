package org.serratec.backend.controller;

import jakarta.validation.Valid;
import org.serratec.backend.dto.CouponDTO;
import org.serratec.backend.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cupons")
public class CouponController {

    private final CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping
    public ResponseEntity<CouponDTO> create(@Valid @RequestBody CouponDTO dto) {
        CouponDTO criado = couponService.create(dto);
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<CouponDTO>> getAll() {
        List<CouponDTO> lista = couponService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponDTO> getById(@PathVariable Long id) {
        CouponDTO dto = couponService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/codigo/{code}")
    public ResponseEntity<CouponDTO> getByCode(@PathVariable String code) {
        CouponDTO dto = couponService.findByCode(code);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CouponDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody CouponDTO dto) {
        CouponDTO atualizado = couponService.update(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        couponService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
