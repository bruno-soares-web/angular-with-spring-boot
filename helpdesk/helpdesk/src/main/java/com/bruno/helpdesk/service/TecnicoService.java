package com.bruno.helpdesk.service;

import com.bruno.helpdesk.domains.Pessoa;
import com.bruno.helpdesk.domains.Tecnico;
import com.bruno.helpdesk.dto.TecnicoDTO;
import com.bruno.helpdesk.exceptions.IntegrityViolationException;
import com.bruno.helpdesk.repositories.PessoaRepository;
import com.bruno.helpdesk.repositories.TecnicoRepository;
import com.bruno.helpdesk.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Registro " + id +" Não Encontrado."));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);
        return tecnicoRepository.save(newObj);
    }

    public Tecnico update(Integer id, @Valid TecnicoDTO objTecnicoDTO) {
        objTecnicoDTO.setId(id);
        Tecnico oldobj = findById(id);
        validaPorCpfEEmail(objTecnicoDTO);
        oldobj = new Tecnico(objTecnicoDTO);
        return tecnicoRepository.save(oldobj);
    }

    public void delete(Integer id) {
        Tecnico objTecnico = findById(id);
        if(objTecnico.getChamados().size() > 0){
            throw new IntegrityViolationException("Técnico Possui Odens De Serviço e Não Pode Eer Deletado");
        }
        tecnicoRepository.deleteById(id);
    }

    private void validaPorCpfEEmail(TecnicoDTO objDTO) {

        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new IntegrityViolationException("CPF " + objDTO.getCpf() + " Já Cadastrado No Sistema");

        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new IntegrityViolationException("E-mail " + objDTO.getEmail() + " Já Cadastrado No Sistema!");
        }
    }


}

