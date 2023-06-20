package com.bruno.helpdesk.repositories;


import com.bruno.helpdesk.domains.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
