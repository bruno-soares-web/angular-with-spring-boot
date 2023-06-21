package com.bruno.helpdesk.resources;

import com.bruno.helpdesk.domains.Chamado;
import com.bruno.helpdesk.domains.Cliente;
import com.bruno.helpdesk.dto.ChamadoDTO;
import com.bruno.helpdesk.dto.ClienteDTO;
import com.bruno.helpdesk.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamado")
public class ChamadoResources {

    @Autowired
    private ChamadoService ChamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
        Chamado obj = ChamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll() {
        List<Chamado> list = ChamadoService.findAll();
        List<ChamadoDTO> listDTO = list.stream().map(obj -> new ChamadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);

    }

}