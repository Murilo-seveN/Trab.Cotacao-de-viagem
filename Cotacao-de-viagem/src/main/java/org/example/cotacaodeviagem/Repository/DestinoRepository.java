package org.example.cotacaodeviagem.repository;

import org.example.cotacaodeviagem.entity.DestinoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinoRepository extends JpaRepository<DestinoEntity, Long> {
}
