package modelos;

import java.util.List;
public class Notebook extends Produto {
    private String processador;
    private double tamanhoTela;

    public Notebook() {
    }

    public Notebook(String nome, String marca, String processador, double tamanhoTela) {
        super(nome, marca);
        this.processador = processador;
        this.tamanhoTela = tamanhoTela;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public double getTamanhoTela() {
        return tamanhoTela;
    }

    public void setTamanhoTela(double tamanhoTela) {
        if (tamanhoTela <= 0) {
            System.out.println("O tamanho da tela deve ser maior que 0.");
        }
        this.tamanhoTela = tamanhoTela;
    }

    @Override
    public String exibirDetalhes() {
        return String.format("Notebook: %s (%s), %s, %.1f", nome, marca, processador, tamanhoTela);
    }
}
