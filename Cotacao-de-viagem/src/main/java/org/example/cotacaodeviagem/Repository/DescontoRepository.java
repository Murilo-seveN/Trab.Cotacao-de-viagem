package org.example.cotacaodeviagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DescontoRepository extends JpaRepository<Desconto, Long> {

    List<Desconto> findByCotacaoId(Long cotacaoId);
}
