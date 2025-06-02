package org.serratec.backend.service;

import org.serratec.backend.dto.ListadeDesejosDTO;
import org.serratec.backend.repository.ClienteRepository;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListadeDesejos {
    @Autowired
    private ListadeDesejos listadeDesejosRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public ListadeDesejosDTO

}
