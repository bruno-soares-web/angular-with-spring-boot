package com.bruno.helpdesk.service;

import com.bruno.helpdesk.domains.Pessoa;
import com.bruno.helpdesk.domains.Cliente;
import com.bruno.helpdesk.dto.ClienteDTO;
import com.bruno.helpdesk.exceptions.IntegrityViolationException;
import com.bruno.helpdesk.exceptions.ObjectnotFoundException;
import com.bruno.helpdesk.repositories.PessoaRepository;
import com.bruno.helpdesk.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository ClienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = ClienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Cliente> findAll() {
        return ClienteRepository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        Cliente newObj = new Cliente(objDTO);
        return ClienteRepository.save(newObj);
    }

    public Cliente update(Integer id, @Valid ClienteDTO objClienteDTO) {
        objClienteDTO.setId(id);
        Cliente oldobj = findById(id);
        validaPorCpfEEmail(objClienteDTO);
        oldobj = new Cliente(objClienteDTO);
        return ClienteRepository.save(oldobj);
    }

    public void delete(Integer id) {
        Cliente objCliente = findById(id);
        if(objCliente.getChamados().size() > 0){
            throw new IntegrityViolationException("Cliente Possui Odens De Serviço e Não Pode Eer Deletado");
        }
        ClienteRepository.deleteById(id);
    }

    private void validaPorCpfEEmail(ClienteDTO objDTO) {

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

