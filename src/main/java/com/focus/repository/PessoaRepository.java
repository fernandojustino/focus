package com.focus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.focus.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
