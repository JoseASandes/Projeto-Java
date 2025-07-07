package modelos;

import java.time.LocalDate;
import java.util.Objects;

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
        this.nota = nota;
        this.texto = texto;
        this.data = LocalDate.now();
        this.status = StatusAvaliacao.PENDENTE;
        this.autor = autor;
        this.produtoAvaliado = produtoAvaliado;
    }

    public Avaliacao() {
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Produto getProdutoAvaliado() {
        return produtoAvaliado;
    }

    public void setProdutoAvaliado(Produto produtoAvaliado) {
        this.produtoAvaliado = produtoAvaliado;
    }

    public void setNota(int nota) {
        if (nota <= 0 || nota > 5) {
            System.out.println("Informe um valor entre 1 e 5.");
        } else {
            this.nota = nota;
        }
    }

    public void setStatus(StatusAvaliacao status) {
        this.status = status;
    }

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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Avaliacao))
            return false;
        Avaliacao a = (Avaliacao) o;
        return nota == a.nota && Objects.equals(texto, a.texto)
                && Objects.equals(autor, a.autor)
                && Objects.equals(produtoAvaliado, a.produtoAvaliado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nota, texto, autor, produtoAvaliado);
    }

    public int getNota() {
        return nota;
    }

    public StatusAvaliacao getStatus() {
        return status;
    }
}
