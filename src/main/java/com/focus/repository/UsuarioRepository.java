package com.focus.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.focus.domain.security.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer > {
	
	 @Query("SELECT u FROM Usuario u where u.login = :login and u.password = :password") 
	 Usuario findByLogin(@Param("login") String login, @Param("password") String senha);

}
