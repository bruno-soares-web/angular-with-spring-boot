package com.bruno.helpdesk.service;

import com.bruno.helpdesk.domains.Chamado;
import com.bruno.helpdesk.domains.Cliente;
import com.bruno.helpdesk.domains.Pessoa;
import com.bruno.helpdesk.dto.ChamadoDTO;
import com.bruno.helpdesk.dto.ClienteDTO;
import com.bruno.helpdesk.exceptions.IntegrityViolationException;
import com.bruno.helpdesk.exceptions.ObjectnotFoundException;
import com.bruno.helpdesk.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Registro " + id +" Não Encontrado."));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

}

