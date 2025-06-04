package org.serratec.backend.service;

import org.serratec.backend.dto.CouponDTO;

import java.util.List;

;

public interface CouponService {

    CouponDTO create(CouponDTO dto);

    CouponDTO findById(Long id);

    CouponDTO findByCode(String code);

    List<CouponDTO> findAll();

    CouponDTO update(Long id, CouponDTO dto);

    void delete(Long id);
}
