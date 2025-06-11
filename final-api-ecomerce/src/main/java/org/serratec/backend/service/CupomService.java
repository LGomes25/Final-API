package org.serratec.backend.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.dto.CupomRequestDTO;
import org.serratec.backend.dto.CupomResponseDTO;
import org.serratec.backend.entity.Cupom;
import org.serratec.backend.exception.CupomException;
import org.serratec.backend.repository.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CupomService{

	@Autowired
    private CupomRepository cupomRepo;

    public CupomResponseDTO criarCupom(CupomRequestDTO dto) {
        Cupom cupom = new Cupom();
        cupom.setCodigo(dto.getCodigo());
        cupom.setValorDesconto(dto.getValorDesconto());
        cupom.setValidadeData(dto.getValidadeData());
        cupom.setUsado(false);
        
        Cupom salvo = cupomRepo.save(cupom);
        
        return converteParaDTO(salvo);
    }

    public List<CupomResponseDTO> listarTodos() {
        List<Cupom> cupons = cupomRepo.findAll();
        List<CupomResponseDTO> cuponsDTO = new ArrayList<>();
        for (Cupom cupom : cupons) {
			cuponsDTO.add(converteParaDTO(cupom));
		}
        return cuponsDTO;
    }

    public CupomResponseDTO listarPorCodigo(String codigo) {
        Cupom cupom = cupomRepo.findByCodigo(codigo)
                .orElseThrow(() -> new CupomException("Cupom "+codigo+" não encontrado."));
        CupomResponseDTO cupomDTO = converteParaDTO(cupom);
        return cupomDTO;
    }

    public CupomResponseDTO atualizar(String codigo, CupomRequestDTO dto) {
        Cupom cupomExiste = cupomRepo.findByCodigo(codigo)
                .orElseThrow(() -> new CupomException("Cupom "+codigo+" não encontrado."));
        
        cupomExiste.setValidadeData(dto.getValidadeData() != null ? dto.getValidadeData() : cupomExiste.getValidadeData());
        cupomExiste.setValorDesconto(dto.getValorDesconto() != null ? dto.getValorDesconto() : cupomExiste.getValorDesconto());
        cupomExiste.setUsado(dto.getUsado() != null ? dto.getUsado() : cupomExiste.getUsado());
        
        Cupom atualizado = cupomRepo.save(cupomExiste);

        return converteParaDTO(atualizado);
    }

    public void excluir(String codigo) {
        Cupom cupomExiste = cupomRepo.findByCodigo(codigo)
                .orElseThrow(() -> new CupomException("Cupom "+codigo+" não encontrado."));
        cupomRepo.delete(cupomExiste);
    }

    // Método auxiliar para converter Entidade -> DTO
    private CupomResponseDTO converteParaDTO(Cupom cupom) {
        String codigo = cupom.getCodigo();
        LocalDate validade = cupom.getValidadeData();
        BigDecimal valor = cupom.getValorDesconto();
        String status = cupom.getUsado()?"Que pena, já foi usado":"Válido para uso";
    	CupomResponseDTO dto = new CupomResponseDTO(codigo, validade, valor, status);
        return dto;
    }
}
