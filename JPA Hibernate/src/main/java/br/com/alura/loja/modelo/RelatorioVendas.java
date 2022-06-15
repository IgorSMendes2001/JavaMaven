package br.com.alura.loja.modelo;

import java.time.LocalDate;

public class RelatorioVendas {
    private String nomeProduto;
    private Long quantidadeVendida;
    private LocalDate dataVenda;
    public RelatorioVendas(String nomeProduto, Long quantidadeVendida, LocalDate dataVenda) {
        this.nomeProduto = nomeProduto;
        this.quantidadeVendida = quantidadeVendida;
        this.dataVenda = dataVenda;
    }
    public String getNomeProduto() {
        return nomeProduto;
    }
    public Long getQuantidadeVendida() {
        return quantidadeVendida;
    }
    public LocalDate getDataVenda() {
        return dataVenda;
    }
    @Override
    public String toString() {
        return "RelatorioVendas [dataVenda=" + dataVenda + ", nomeProduto=" + nomeProduto + ", quantidadeVendida="
                + quantidadeVendida + "]";
    }
    
}
