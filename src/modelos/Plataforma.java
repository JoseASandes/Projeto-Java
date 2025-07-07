package modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import enums.StatusAvaliacao;
import interfaces.Moderacao;

public class Plataforma {
    private List<Produto> produtos = new ArrayList<Produto>();
    private List<Usuario> usuarios = new ArrayList<Usuario>();

    public void adicionarProduto(Produto p) {
        if (!produtos.contains(p)) {
            produtos.add(p);
            System.out.println("Produto " + p.getNome() + " adicionado com sucesso.");
        }
        else {
            System.out.println("O produto " + p.getNome() + " ja se encontra no sistema.");
        }
    }

    public void adicionarUsuario(Usuario u) {
        if (!usuarios.contains(u)) {
            usuarios.add(u);
            System.out.println("Usuario " + u.getNome() + " cadastrado com sucesso.");
        }
        else {
            System.out.println("O usuario " + u.getNome() + " ja foi cadastrado no sistema.");
        }
    }

    public Produto buscarProdutoPorNome(String nome) {
        return produtos.stream()
                .filter(p -> p.nome.equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public void listarProdutosOrdenadosPorNome() {
        produtos.stream()
                .sorted()
                .forEach(p -> System.out.println(p.exibirDetalhes()));
    }

    public List<Moderacao> listarPendenciasDeModeracao() {
        List<Moderacao> pendentes = new ArrayList<>();

        for (Usuario u : usuarios) {
            if (u.getAvaliacoesFeitas().stream().anyMatch(a -> a.getStatus() == StatusAvaliacao.PENDENTE)) {
                pendentes.add(u);
            }
        }

        for (Produto p : produtos) {
            pendentes.addAll(p.getAvaliacoes().stream().filter(a -> a.getStatus() == StatusAvaliacao.PENDENTE).collect(Collectors.toList()));
        }

        return pendentes;
    }
}
