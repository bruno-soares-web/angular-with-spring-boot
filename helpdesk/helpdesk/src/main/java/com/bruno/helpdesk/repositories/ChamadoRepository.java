package com.bruno.helpdesk.repositories;


import com.bruno.helpdesk.domains.Chamado;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
