package com.example.trabalhocotacao;

public class CotacaoRequestDTO {

    private Long clienteId;
    private Long destinoId;
    private String dataViagem;
    private String dataRetorno;
    private int numeroPessoas;

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public Long getDestinoId() { return destinoId; }
    public void setDestinoId(Long destinoId) { this.destinoId = destinoId; }

    public String getDataViagem() { return dataViagem; }
    public void setDataViagem(String dataViagem) { this.dataViagem = dataViagem; }

    public String getDataRetorno() { return dataRetorno; }
    public void setDataRetorno(String dataRetorno) { this.dataRetorno = dataRetorno; }

    public int getNumeroPessoas() { return numeroPessoas; }
    public void setNumeroPessoas(int numeroPessoas) { this.numeroPessoas = numeroPessoas; }
}