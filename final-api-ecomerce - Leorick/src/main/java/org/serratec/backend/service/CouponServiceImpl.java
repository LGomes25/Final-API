package org.serratec.backend.service;

import org.serratec.backend.dto.CouponDTO;
import org.serratec.backend.entity.Coupon;
import org.serratec.backend.exception.ResourceNotFoundException;
import org.serratec.backend.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Autowired
    public CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public CouponDTO create(CouponDTO dto) {
        Coupon coupon = new Coupon();
        coupon.setCode(dto.getCode());
        coupon.setDiscountValue(dto.getDiscountValue());
        coupon.setExpiryDate(dto.getExpiryDate());
        coupon.setUsed(dto.getUsed() != null ? dto.getUsed() : false);

        Coupon salvo = couponRepository.save(coupon);
        return mapToDTO(salvo);
    }

    @Override
    public CouponDTO findById(Long id) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon", "id", id));
        return mapToDTO(coupon);
    }

    @Override
    public CouponDTO findByCode(String code) {
        Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon", "code", code));
        return mapToDTO(coupon);
    }

    @Override
    public List<CouponDTO> findAll() {
        List<Coupon> lista = couponRepository.findAll();
        return lista.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CouponDTO update(Long id, CouponDTO dto) {
        Coupon existente = couponRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon", "id", id));

        existente.setCode(dto.getCode());
        existente.setDiscountValue(dto.getDiscountValue());
        existente.setExpiryDate(dto.getExpiryDate());
        existente.setUsed(dto.getUsed() != null ? dto.getUsed() : existente.getUsed());

        Coupon atualizado = couponRepository.save(existente);
        return mapToDTO(atualizado);
    }

    @Override
    public void delete(Long id) {
        Coupon existente = couponRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon", "id", id));
        couponRepository.delete(existente);
    }

    // Método auxiliar para converter Entidade -> DTO
    private CouponDTO mapToDTO(Coupon coupon) {
        CouponDTO dto = new CouponDTO();
        dto.setId(coupon.getId());
        dto.setCode(coupon.getCode());
        dto.setDiscountValue(coupon.getDiscountValue());
        dto.setExpiryDate(coupon.getExpiryDate());
        dto.setUsed(coupon.getUsed());
        return dto;
    }
}
