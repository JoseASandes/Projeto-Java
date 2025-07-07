package modelos;

import java.time.LocalDate;
import java.util.*;

import enums.StatusAvaliacao;
import interfaces.Moderacao;

public class Avaliacao implements Moderacao, Comparable<Avaliacao> {
    private int nota;
    private String texto;
    private LocalDate data;
    private StatusAvaliacao status;
    private Usuario autor;
    private Produto produtoAvaliado;

    public Avaliacao(int nota, String texto, Usuario autor, Produto produtoAvaliado) {
        this.setNota(nota);
        this.texto = texto;
        this.data = LocalDate.now();
        this.status = StatusAvaliacao.PENDENTE;
        this.autor = autor;
        this.produtoAvaliado = produtoAvaliado;
    }

    public void setNota(int nota) {
        if (nota >= 1 && nota <= 5) {
            this.nota = nota;
        } else {
            System.out.println("A nota deve ser um valor entre 1 e 5.");

        }
    }

    public int getNota() {
        return nota;
    }
    
    public StatusAvaliacao getStatus() {
        return status;
    }
    
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public Usuario getAutor() { return autor; }
    public void setAutor(Usuario autor) { this.autor = autor; }
    public Produto getProdutoAvaliado() { return produtoAvaliado; }
    public void setProdutoAvaliado(Produto produtoAvaliado) { this.produtoAvaliado = produtoAvaliado; }
    public void setStatus(StatusAvaliacao status) { this.status = status; }

    @Override
    public void aprovar() {
        status = StatusAvaliacao.APROVADA;
    }

    @Override
    public void rejeitar() {
        status = StatusAvaliacao.REJEITADA;
    }

    @Override
    public int compareTo(Avaliacao outra) {
        return this.data.compareTo(outra.data);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Avaliacao))
            return false;
        Avaliacao a = (Avaliacao) obj;
        return Objects.equals(autor, a.autor)
                && Objects.equals(produtoAvaliado, a.produtoAvaliado);
    }
}