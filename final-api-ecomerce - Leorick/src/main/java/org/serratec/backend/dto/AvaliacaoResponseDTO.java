package org.serratec.backend.dto;

import org.serratec.backend.entity.Avaliacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoResponseDTO {

    private Long id;
    private Integer nota;
    private Long idProduto;
    private String nomeProduto;
    private Long idCliente;
    private String nomeCliente;

    public AvaliacaoResponseDTO(Avaliacao avaliacao) {
        this.id = avaliacao.getId();
        this.nota = avaliacao.getNota();
        this.idProduto = avaliacao.getProduto().getId();
        this.nomeProduto = avaliacao.getProduto().getNome();
        this.idCliente = avaliacao.getCliente().getId();
        this.nomeCliente = avaliacao.getCliente().getNome();
    }
}