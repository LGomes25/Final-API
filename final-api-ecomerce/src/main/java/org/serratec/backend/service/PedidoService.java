package org.serratec.backend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.dto.PedidoProdutoDTO;
import org.serratec.backend.dto.PedidoRequestDTO;
import org.serratec.backend.dto.PedidoResponseDTO;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.entity.PedidoProduto;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.enums.StatusPedido;
import org.serratec.backend.repository.ClienteRepository;
import org.serratec.backend.repository.PedidoRepository;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    //Criar um pedido
    @Transactional
    public PedidoResponseDTO criarPedido(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.PENDENTE);       					// Define o status inicial do pedido
        pedido.setDataPedido(LocalDateTime.now());						// Grava a data/hora atual do sistema

        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));	// Busca o cliente no banco
        
        pedido.setCliente(cliente);

        // Monta a lista de produtos do pedido
        List<PedidoProduto> listaPedidoProdutos = new ArrayList<>();
        for (PedidoProdutoDTO item : dto.getProdutos()) {
            Produto produto = produtoRepository.findById(item.getIdProduto())  // Busca produto no banco
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            PedidoProduto pedidoProduto = new PedidoProduto();
            pedidoProduto.setPedido(pedido);              // Relaciona o pedido
            pedidoProduto.setProduto(produto);            // Relaciona o produto
            pedidoProduto.setQuantidade(item.getQuantidade());
            pedidoProduto.setValorVenda(item.getValorVenda());
            pedidoProduto.setDesconto(item.getDesconto() != null ? item.getDesconto() : 0.0);
            

            listaPedidoProdutos.add(pedidoProduto);       // Adiciona na lista de produtos do pedido
        }
        pedido.setProdutosPedidos(listaPedidoProdutos);

        pedido = pedidoRepository.save(pedido);           // Salva o pedido no banco

        return formatoPedidoResponseDTO(pedido);          // Retorna DTO de resposta com os dados do pedido criado
    }

    //Busca um pedido pelo ID.
    public PedidoResponseDTO buscarPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return formatoPedidoResponseDTO(pedido);
    }

    //Lista todos os pedidos cadastrados.
    public List<PedidoResponseDTO> listarPedidos() {
        return pedidoRepository.findAll().stream()
                .map(this::formatoPedidoResponseDTO)
                .toList();
    }

    //Remove um pedido pelo seu ID, se ele existir.
    public void deletarPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
    }

    //Altera apenas o status de um pedido, sem mexer nos outros dados.
    public PedidoResponseDTO alterarStatus(Long id, StatusPedido status) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(status);
        pedido = pedidoRepository.save(pedido);
        
        return formatoPedidoResponseDTO(pedido);
    }

    /**
     * Método utilitário que converte a entidade Pedido para o DTO de resposta.
     * Monta a lista de produtos e calcula o subTotal com descontos e o total.
     */
    private PedidoResponseDTO formatoPedidoResponseDTO(Pedido pedido) {
        PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.setId(pedido.getId());
        dto.setStatus(pedido.getStatus());
        dto.setDataPedido(pedido.getDataPedido()); 			// LocalDateTime
        dto.setCliente(pedido.getCliente().getId());
        
        // Monta lista de produtos do pedido
        List<PedidoProdutoDTO> itens = pedido.getProdutosPedidos().stream().map(pp -> {
            PedidoProdutoDTO itemDto = new PedidoProdutoDTO();
            itemDto.setIdProduto(pp.getProduto().getId());
            itemDto.setQuantidade(pp.getQuantidade());
            itemDto.setValorVenda(pp.getValorVenda());
            itemDto.setDesconto(pp.getDesconto());
            itemDto.setSubTotal((pp.getQuantidade()*pp.getValorVenda())-pp.getDesconto()); //calcula o subtotal por pedido
            return itemDto;
        }).toList();
        
        Double total = 0.0;										//Criar total e percorre lista acumulando.
        for (PedidoProdutoDTO subtotal : itens) {
			total += subtotal.getSubTotal();
		}

        dto.setProdutos(itens);
        
        dto.setTotal(total);
        
        return dto;
    }
}
