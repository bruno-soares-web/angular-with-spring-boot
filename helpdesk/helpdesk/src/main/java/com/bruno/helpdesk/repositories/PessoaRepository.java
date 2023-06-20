package com.bruno.helpdesk.repositories;


import com.bruno.helpdesk.domains.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
