package modelos;

import java.util.*;

import enums.StatusAvaliacao;
import enums.StatusUsuario;
import interfaces.Moderacao;

public class Plataforma {
    private List<Produto> produtos = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    public void adicionarProduto(Produto p) {
        if (!produtos.contains(p)) {
            produtos.add(p);
            System.out.println("Produto " + p.getNome() + " adicionado com sucesso.");
        } else {
            System.out.println("O produto " + p.getNome() + " ja se encontra no sistema.");
        }
    }

    public void adicionarUsuario(Usuario u) {
        if (!usuarios.contains(u)) {
            usuarios.add(u);
            System.out.println("Usuario " + u.getNome() + " cadastrado com sucesso.");
        } else {
            System.out.println("O usuario " + u.getNome() + " ja foi cadastrado no sistema.");
        }
    }

    public Produto buscarProdutoPorNome(String nome) {
        return produtos.stream().filter(p -> p.getNome().equalsIgnoreCase(nome)).findFirst().orElse(null);
    }

    public void listarProdutosOrdenadosPorNome() {
        List<Produto> produtosOrdenados = new ArrayList<>(this.produtos);
        Collections.sort(produtosOrdenados);
        for (Produto p : produtosOrdenados) {
            System.out.println(p.exibirDetalhes());
        }
    }

    public List<Moderacao> listarPendenciasDeModeracao() {
        List<Moderacao> pendentes = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u.getStatus() == StatusUsuario.PENDENTE_APROVACAO) {
                pendentes.add(u);
            }
        }
        for (Produto p : produtos) {
            for (Avaliacao a : p.getAvaliacoes()) {
                if (a.getStatus() == StatusAvaliacao.PENDENTE) {
                    pendentes.add(a);
                }
            }
        }
        return pendentes;
    }
}