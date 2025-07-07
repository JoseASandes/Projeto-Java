/**
 * 
 */
package modelos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import enums.StatusAvaliacao;

/**
 * 
 */
public abstract class Produto implements Comparable<Produto> {
    protected String nome;
    protected String marca;
    protected List<Avaliacao> avaliacoes = new ArrayList<>();

    public Produto() {

    }

    /**
     * @param nome
     * @param marca
     * @param avaliacoes
     */
    public Produto(String nome, String marca) {
        this.nome = nome;
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }

    public List<Avaliacao> getAvaliacoes() {
        return Collections.unmodifiableList(avaliacoes);
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        if (!avaliacoes.contains(avaliacao)) {
            avaliacoes.add(avaliacao);
        } else {
            System.out.println("Produto ja avaliado pelo usuario.");
        }
        
    }

    public double calcularNotaMedia() {
        return avaliacoes.stream()
                .filter(avaliacao -> avaliacao.getStatus() == StatusAvaliacao.APROVADA)
                .mapToInt(Avaliacao::getNota)
                .average()
                .orElse(0);
    }

    public abstract String exibirDetalhes(); // toString

    @Override
    public int compareTo(Produto outro) {
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Produto p))
            return false;
        return Objects.equals(nome, p.nome) && Objects.equals(marca, p.marca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, marca);
    }
}
