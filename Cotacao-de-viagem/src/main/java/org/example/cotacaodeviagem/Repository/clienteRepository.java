package org.example.cotacaodeviagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface clienteRepository extends JpaRepository<clienteEntity, Long> {
    Optional<Cliente> findByEmail(String email);
}
