package modelos;

import java.util.*;

import enums.StatusAvaliacao;

public abstract class Produto implements Comparable<Produto> {
    protected String nome;
    protected String marca;
    protected List<Avaliacao> avaliacoes = new ArrayList<>();

    public Produto() {
    }

    public Produto(String nome, String marca) {
        this.nome = nome;
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }
    
    public String getMarca() {
        return marca;
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
        return avaliacoes.stream().filter(avaliacao -> avaliacao.getStatus() == StatusAvaliacao.APROVADA).mapToInt(Avaliacao::getNota).average().orElse(0);
    }

    public abstract String exibirDetalhes();

    @Override
    public int compareTo(Produto outro) {
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Produto))
            return false;
        Produto p = (Produto) obj;
        return Objects.equals(nome, p.nome) && Objects.equals(marca, p.marca);
    }
}