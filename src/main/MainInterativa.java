package main;

import modelos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainInterativa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Plataforma plataforma = new Plataforma();

        System.out.print("Nome do usuário: ");
        String nome = sc.nextLine();
        System.out.print("Email do usuário: ");
        String email = sc.nextLine();

        Usuario usuario = new Usuario(nome, email);
        usuario.aprovar();
        plataforma.adicionarUsuario(usuario);

        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuarioAtual = null;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Avaliar Produto");
            System.out.println("3. Listar Produtos Ordenados");
            System.out.println("4. Ver Pendências de Moderação");
            System.out.println("5. Adicionar outro usuario");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            int opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> {
                    System.out.print("Tipo (1-Celular, 2-Notebook): ");
                    int tipo = Integer.parseInt(sc.nextLine());
                    System.out.print("Nome: ");
                    String nomeP = sc.nextLine();
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();

                    if (tipo == 1) {
                        System.out.print("RAM (GB): ");
                        int ram = Integer.parseInt(sc.nextLine());
                        System.out.print("Armazenamento (GB): ");
                        int armazenamento = Integer.parseInt(sc.nextLine());
                        plataforma.adicionarProduto(new Celular(nomeP, marca, ram, armazenamento));
                    } else {
                        System.out.print("Processador: ");
                        String proc = sc.nextLine();
                        System.out.print("Tamanho da tela: ");
                        double tela = Double.parseDouble(sc.nextLine());

                        // Correção: Incluir lista de avaliações (null) no construtor
                        plataforma.adicionarProduto(new Notebook(nomeP, marca, proc, tela));
                    }
                    System.out.println("Produto adicionado com sucesso!");
                }

                case 2 -> {
                    System.out.print("Nome do produto: ");
                    String nomeProduto = sc.nextLine();
                    Produto produto = plataforma.buscarProdutoPorNome(nomeProduto);
                    if (produto == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }

                    System.out.print("Nota (1 a 5): ");
                    int nota = Integer.parseInt(sc.nextLine());
                    System.out.print("Comentário: ");
                    String comentario = sc.nextLine();

                    try {
                        usuario.avaliarProduto(produto, nota, comentario);
                        System.out.println("Avaliação enviada!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }

                case 3 -> {
                    plataforma.listarProdutosOrdenadosPorNome();
                }

                case 4 -> {
                    var pendencias = plataforma.listarPendenciasDeModeracao();
                    System.out.println("\nItens pendentes de moderação: " + pendencias.size());
                    pendencias.forEach(p -> System.out.println(p.getClass().getSimpleName()));
                }

                case 5 -> {
                    System.out.print("Nome do usuário: ");
                    String nome1 = sc.nextLine();
                    System.out.print("Email do usuário: ");
                    String email1 = sc.nextLine();

                    usuarioAtual = usuarios.stream()
                            .filter(u -> u.getNome().equalsIgnoreCase(nome) && u.getEmail().equalsIgnoreCase(email1))
                            .findFirst()
                            .orElse(null);

                    if (usuarioAtual == null) {
                        usuarioAtual = new Usuario(nome, email1);
                        usuarioAtual.aprovar(); // Ativa o usuário diretamente
                        usuarios.add(usuarioAtual);
                        plataforma.adicionarUsuario(usuarioAtual);
                        System.out.println("Usuário criado e autenticado!");
                    } else {
                        System.out.println("Usuário autenticado!");
                    }
                }

                case 0 -> {
                    System.out.println("Encerrando...");
                    sc.close();
                    return;
                }

                default -> System.out.println("Opção inválida!");
            }
        }
    }
}