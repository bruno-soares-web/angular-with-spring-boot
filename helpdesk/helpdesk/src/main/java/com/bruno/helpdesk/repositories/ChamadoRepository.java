package com.bruno.helpdesk.repositories;


import com.bruno.helpdesk.domains.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
