package com.example.trabalhocotacao;

public class CotacaoResponseDTO {

    private Long id;
    private Long clienteId;
    private Long destinoId;
    private String dataViagem;
    private String dataRetorno;
    private int numeroPessoas;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}