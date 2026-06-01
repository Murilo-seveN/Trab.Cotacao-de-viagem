package org.example.cotacaodeviagem.repository; 

 

import org.example.cotacaodeviagem.entity.DescontoEntity; 

import org.springframework.data.jpa.repository.JpaRepository; 

import org.springframework.stereotype.Repository; 

import java.util.List; 

 

@Repository 

public interface DescontoRepository extends JpaRepository<DescontoEntity, Long> { 

    List<DescontoEntity> findByCotacaoId(Long cotacaoId); 

}
