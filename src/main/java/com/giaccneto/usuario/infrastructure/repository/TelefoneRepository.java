package com.giaccneto.usuario.infrastructure.repository;

import com.giaccneto.aprendendo_spring_03.infraestructure.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
