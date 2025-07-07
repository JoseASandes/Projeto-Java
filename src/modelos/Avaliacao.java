/**
 * 
 */
package modelos;

import java.time.LocalDate;
import java.util.Objects;

import enums.StatusAvaliacao;
import interfaces.Moderacao;

/**
 * 
 */
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

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the data
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * @return the autor
     */
    public Usuario getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    /**
     * @return the produtoAvaliado
     */
    public Produto getProdutoAvaliado() {
        return produtoAvaliado;
    }

    /**
     * @param produtoAvaliado the produtoAvaliado to set
     */
    public void setProdutoAvaliado(Produto produtoAvaliado) {
        this.produtoAvaliado = produtoAvaliado;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(int nota) {
        if (nota <= 0 || nota > 5) {
            System.out.println("Informe um valor entre 1 e 5.");
        } else {
            this.nota = nota;
        }
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusAvaliacao status) {
        this.status = status;
    }

    // ================== MÉTODOS =====================================

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
        if (!(o instanceof Avaliacao a))
            return false;
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
