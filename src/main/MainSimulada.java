package main;

import interfaces.Moderacao;
import modelos.*;
import enums.StatusUsuario;

public class MainSimulada {

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma();

        System.out.println("--- Etapa 1: Cadastrando usuários ---");
        Usuario ana = new Usuario("Ana", "ana@email.com");
        Usuario joao = new Usuario("João", "joao@email.com");

        plataforma.adicionarUsuario(ana);
        plataforma.adicionarUsuario(joao);
        System.out.println("Usuários 'Ana' e 'João' adicionados. Status inicial: " + ana.getStatus());

        System.out.println("\n--- Etapa 2: Moderação de novos usuários ---");
        System.out.println("Itens pendentes antes da moderação de usuários:");
        plataforma.listarPendenciasDeModeracao().forEach(item -> System.out.println("- " + item.getClass().getSimpleName()));

        System.out.println("\nAprovando usuários...");
        ana.aprovar();
        joao.aprovar();
        System.out.println("Status da 'Ana' após aprovação: " + ana.getStatus());
        System.out.println("Status do 'João' após aprovação: " + joao.getStatus());

        System.out.println("\n--- Etapa 3: Adicionando produtos à plataforma ---");
        Celular celular = new Celular("Galaxy S22", "Samsung", 8, 128);
        Notebook notebook = new Notebook("Dell XPS", "Dell", "Intel i7", 15.6);

        plataforma.adicionarProduto(celular);
        plataforma.adicionarProduto(notebook);

        System.out.println("\n--- Etapa 4: Usuários realizam avaliações ---");
        ana.avaliarProduto(celular, 5, "Muito bom!");
        joao.avaliarProduto(celular, 4, "Boa performance.");
        ana.avaliarProduto(notebook, 5, "Máquina excelente.");
        System.out.println("Avaliações enviadas para moderação.");

        System.out.println("\n--- Etapa 5: Moderação das avaliações ---");
        System.out.println("Nota média do celular (antes de aprovar avaliações): " + celular.calcularNotaMedia());

        System.out.println("\nItens pendentes para moderação (esperado: 3 avaliações):");
        for (Moderacao pendencia : plataforma.listarPendenciasDeModeracao()) {
            if (pendencia instanceof Avaliacao) {
                System.out.println("- Avaliação do produto: " + ((Avaliacao) pendencia).getProdutoAvaliado().getNome());
                pendencia.aprovar();
            }
        }
        System.out.println("Todas as avaliações pendentes foram aprovadas.");

        System.out.println("\n--- Etapa 6: Resultados Finais ---");
        System.out.println("Listando produtos ordenados por nome:");
        plataforma.listarProdutosOrdenadosPorNome();

        System.out.println("\nCalculando notas médias após aprovação:");
        System.out.println("Nota média do celular: " + String.format("%.2f", celular.calcularNotaMedia()));
        System.out.println("Nota média do notebook: " + String.format("%.2f", notebook.calcularNotaMedia()));
    }
}