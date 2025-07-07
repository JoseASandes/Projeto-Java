package main;

import modelos.Avaliacao;
import modelos.Celular;
import modelos.Notebook;
import modelos.Plataforma;
import modelos.Usuario;
import modelos.Produto;

import interfaces.Moderacao;
import enums.StatusUsuario;

import java.util.*;

public class MainInterativa {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Plataforma plataforma = new Plataforma();
        List<Usuario> todosOsUsuarios = new ArrayList<>();
        Usuario usuarioLogado = null;

        Usuario admin = new Usuario("Admin", "admin@email.com");
        admin.aprovar();
        todosOsUsuarios.add(admin);
        plataforma.adicionarUsuario(admin);


        while (true) {
            System.out.println("\n--- Menu Principal ---");
            if (usuarioLogado != null) {
                System.out.println("Logado como: " + usuarioLogado.getNome() + " (" + usuarioLogado.getEmail() + ")");
            }
            System.out.println("1. Criar ou Logar com Usuário");
            System.out.println("2. Adicionar Produto");
            System.out.println("3. Avaliar Produto");
            System.out.println("4. Listar Produtos");
            System.out.println("5. Ver e Gerenciar Pendências de Moderação");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = Integer.parseInt(teclado.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Digite seu email: ");
                    String email = teclado.nextLine();
                    
                    final String userEmail = email;
                    usuarioLogado = todosOsUsuarios.stream().filter(u -> u.getEmail().equalsIgnoreCase(userEmail)).findFirst().orElse(null);

                    if (usuarioLogado == null) {
                        System.out.print("Email não encontrado. Digite seu nome para se cadastrar: ");
                        String nome = teclado.nextLine();
                        usuarioLogado = new Usuario(nome, email);
                        todosOsUsuarios.add(usuarioLogado);
                        plataforma.adicionarUsuario(usuarioLogado);
                        System.out.println("Novo usuário cadastrado! Seu acesso está pendente de aprovação.");
                    } else {
                        System.out.println("Login realizado com sucesso! Bem-vindo(a) " + usuarioLogado.getNome());
                    }
                    break;

                case 2:
                    if (usuarioLogado == null) {
                        System.out.println("Você precisa estar logado para adicionar um produto.");
                        break;
                    }
                    System.out.print("Tipo do Produto (1-Celular, 2-Notebook): ");
                    int tipo = Integer.parseInt(teclado.nextLine());
                    System.out.print("Nome do Produto: ");
                    String nomeP = teclado.nextLine();
                    System.out.print("Marca do Produto: ");
                    String marca = teclado.nextLine();

                    if (tipo == 1) {
                        System.out.print("Memória RAM (GB): ");
                        int ram = Integer.parseInt(teclado.nextLine());
                        System.out.print("Armazenamento (GB): ");
                        int armazenamento = Integer.parseInt(teclado.nextLine());
                        plataforma.adicionarProduto(new Celular(nomeP, marca, ram, armazenamento));
                    } else if (tipo == 2) {
                        System.out.print("Processador: ");
                        String proc = teclado.nextLine();
                        System.out.print("Tamanho da tela (polegadas): ");
                        double tela = Double.parseDouble(teclado.nextLine());
                        plataforma.adicionarProduto(new Notebook(nomeP, marca, proc, tela));
                    } else {
                        System.out.println("Tipo de produto inválido.");
                    }
                    break;
                
                case 3:
                    if (usuarioLogado == null) {
                        System.out.println("Você precisa estar logado para avaliar um produto.");
                        break;
                    }

                    if(usuarioLogado.getStatus() != StatusUsuario.ATIVO){
                        System.out.println("Seu usuário não está ativo. Você não pode avaliar produtos.");
                        break;
                    }

                    System.out.print("Nome do produto que deseja avaliar: ");
                    String nomeProduto = teclado.nextLine();
                    Produto produtoParaAvaliar = plataforma.buscarProdutoPorNome(nomeProduto);

                    if (produtoParaAvaliar == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }

                    System.out.print("Nota (1 a 5): ");
                    int nota = Integer.parseInt(teclado.nextLine());
                    System.out.print("Comentário: ");
                    String comentario = teclado.nextLine();

                    usuarioLogado.avaliarProduto(produtoParaAvaliar, nota, comentario);
                    System.out.println("Avaliação enviada para moderação!");
                    break;
                
                case 4:
                    System.out.println("\n--- Lista de Produtos ---");
                    plataforma.listarProdutosOrdenadosPorNome();
                    break;

                case 5:
                    if (usuarioLogado == null || !usuarioLogado.getEmail().equals("admin@email.com")) {
                        System.out.println("Acesso negado. Apenas o admin pode gerenciar pendências.");
                        break;
                    }

                    List<Moderacao> pendencias = plataforma.listarPendenciasDeModeracao();
                    if(pendencias.isEmpty()){
                        System.out.println("\nNenhum item pendente de moderação.");
                        break;
                    }
                    
                    System.out.println("\n--- Itens Pendentes de Moderação ---");
                    for (int i = 0; i < pendencias.size(); i++) {
                        Moderacao item = pendencias.get(i);
                        System.out.print((i + 1) + ". ");
                        if (item instanceof Usuario) {
                            System.out.println("Usuário: " + ((Usuario) item).getNome() + " (" + ((Usuario) item).getEmail() + ")");
                        } else if (item instanceof Avaliacao) {
                            Avaliacao avaliacao = (Avaliacao) item;
                            System.out.println("Avaliação do produto '" + avaliacao.getProdutoAvaliado().getNome() + "' por " + avaliacao.getAutor().getNome() + ": \"" + avaliacao.getTexto() + "\"");
                        }
                    }

                    System.out.print("\nEscolha um item para moderar (ou 0 para voltar): ");
                    int itemIndex = Integer.parseInt(teclado.nextLine());

                    if (itemIndex > 0 && itemIndex <= pendencias.size()) {
                        Moderacao itemSelecionado = pendencias.get(itemIndex - 1);
                        System.out.print("Aprovar (A) ou Rejeitar (R)? ");
                        String acao = teclado.nextLine();
                        if (acao.equalsIgnoreCase("A")) {
                            itemSelecionado.aprovar();
                            System.out.println("Item aprovado!");
                        } else if (acao.equalsIgnoreCase("R")) {
                            itemSelecionado.rejeitar();
                            System.out.println("Item rejeitado!");
                        } else {
                            System.out.println("Ação inválida.");
                        }
                    }
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    teclado.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}