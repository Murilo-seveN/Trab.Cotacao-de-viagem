package org.example.cotacaodeviagem;

import java.math.BigDecimal;

public class DescontoDTO {

    private Long cotacaoId;
    private BigDecimal valorDesconto;
    private String descricao;

    // Getters e Setters
    public Long getCotacaoId() { return cotacaoId; }
    public void setCotacaoId(Long cotacaoId) { this.cotacaoId = cotacaoId; }

    public BigDecimal getValorDesconto() { return valorDesconto; }
    public void setValorDesconto(BigDecimal valorDesconto) { this.valorDesconto = valorDesconto; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
