package org.serratec.backend.service;

import jakarta.validation.Valid;
import org.serratec.backend.dto.PedidoProdutoDTO;
import org.serratec.backend.dto.PedidoRequestDTO;
import org.serratec.backend.dto.PedidoResponseDTO;
import org.serratec.backend.entity.Cliente;
import org.serratec.backend.entity.Pedido;
import org.serratec.backend.entity.PedidoProduto;
import org.serratec.backend.entity.Produto;
import org.serratec.backend.enums.StatusPedido;
import org.serratec.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private PedidoProdutoRepository pedidoProdutoRepository;
    @Autowired
    private ProdutoService produtoService;

    public PedidoResponseDTO criarPedido(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.valueOf(dto.getStatus()));
        pedido.setData(LocalDate.now());

        Cliente cliente = clienteRepository.findById(dto.getId_cliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        pedido.setCliente(cliente);

        List<PedidoProduto> listaPedidoProdutos = new ArrayList<>();
        for (PedidoProdutoDTO item : dto.getProdutos()) {
            Produto produto = produtoRepository.findById(item.getId_produto())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            PedidoProduto pedidoProduto = new PedidoProduto();
            pedidoProduto.setPedido(pedido);
            pedidoProduto.setProduto(produto);
            pedidoProduto.setQuantidade(item.getQuantidade());
            pedidoProduto.setValorVenda(item.getValorVenda());
            pedidoProduto.setDesconto(item.getDesconto());

            listaPedidoProdutos.add(pedidoProduto);
        }
        pedido.setPedidoProdutos(listaPedidoProdutos);

        pedido = pedidoRepository.save(pedido);

        return toPedidoResponseDTO(pedido);
    }

    public PedidoResponseDTO buscarPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return toPedidoResponseDTO(pedido);
    }

    public List<PedidoResponseDTO> listarPedidos() {
        return pedidoRepository.findAll().stream()
                .map(this::toPedidoResponseDTO)
                .toList();
    }

    public PedidoResponseDTO atualizarPedido(Long id, PedidoRequestDTO dto) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        Cliente cliente = clienteRepository.findById(dto.getId_cliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        pedido.setCliente(cliente);

        pedido.setStatus(StatusPedido.valueOf(dto.getStatus()));

        // Remove os produtos antigos e adiciona os novos
        pedido.setProduto().clear();
        List<PedidoProduto> listaPedidoProdutos = new ArrayList<>();
        for (PedidoProdutoDTO item : dto.getProdutos()) {
            Produto produto = produtoRepository.findById(item.getId_produto())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            PedidoProduto pedidoProduto = new PedidoProduto();
            pedidoProduto.setPedido(pedido);
            pedidoProduto.setProduto(produto);
            pedidoProduto.setQuantidade(item.getQuantidade());
            pedidoProduto.setValorVenda(item.getValorVenda());
            pedidoProduto.setDesconto(item.getDesconto());

            listaPedidoProdutos.add(pedidoProduto);
        }
        pedido.setPedidoProdutos(listaPedidoProdutos);

        pedido = pedidoRepository.save(pedido);

        return toPedidoResponseDTO(pedido);
    }

    public void deletarPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
    }

    public PedidoResponseDTO alterarStatus(Long id, String status) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(StatusPedido.valueOf(status));
        pedido = pedidoRepository.save(pedido);

        return toPedidoResponseDTO(pedido);
    }

    public Double totalizarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return pedido.getPedidoProdutos().stream()
                .mapToDouble(pp -> (pp.getValorVenda() - (pp.getDesconto() != null ? pp.getDesconto() : 0)) * pp.getQuantidade())
    }

    // Método utilitário de conversão para ResponseDTO
    private PedidoResponseDTO toPedidoResponseDTO(Pedido pedido) {
        PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.setId(pedido.getId());
        dto.setStatus(pedido.getStatus().name());
        dto.setData(pedido.getData());
        dto.setCliente(pedido.getCliente().getId());

        List<PedidoProdutoDTO> itens = pedido.getPedidoProdutos().stream().map(pp -> {
            PedidoProdutoDTO itemDto = new PedidoProdutoDTO();
            itemDto.setId_produto(pp.getProduto().getId());
            itemDto.setQuantidade(pp.getQuantidade());
            itemDto.setValorVenda(pp.getValorVenda());
            itemDto.setDesconto(pp.getDesconto());
            return itemDto;
        }).toList();

        dto.setProdutos(produtos);
        dto.setTotal(totalizarPedido(pedido.getId()));
        return dto;
    }
}
