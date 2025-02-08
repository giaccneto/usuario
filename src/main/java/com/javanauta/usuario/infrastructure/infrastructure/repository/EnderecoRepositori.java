package com.javanauta.usuario.infrastructure.infrastructure.repository;

import com.javanauta.aprendendo_spring.infraestructure.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepositori extends JpaRepository<Endereco, Long> {
}
