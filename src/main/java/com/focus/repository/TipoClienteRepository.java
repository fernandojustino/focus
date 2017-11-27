package com.focus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.focus.domain.TipoCliente;

public interface TipoClienteRepository extends JpaRepository<TipoCliente, Long> {

}
