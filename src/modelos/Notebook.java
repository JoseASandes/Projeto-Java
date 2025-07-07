/**
 * 
 */
package modelos;

import java.util.List;

/**
 * 
 */
public class Notebook extends Produto {
    private String processador;
    private double tamanhoTela;

    /**
     * CONSTRUCTOR VAZIO
     */
    public Notebook() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param processador
     * @param tamanhoTela
     * @param nome
     * @param marca
     * @param avaliacoes
     */
    public Notebook(String nome, String marca, String processador, double tamanhoTela) {
        super(nome, marca);
        this.processador = processador;
        this.tamanhoTela = tamanhoTela;
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the processador
     */
    public String getProcessador() {
        return processador;
    }

    /**
     * @param processador the processador to set
     */
    public void setProcessador(String processador) {
        this.processador = processador;
    }

    /**
     * @return the tamanhoTela
     */
    public double getTamanhoTela() {
        return tamanhoTela;
    }

    /**
     * @param tamanhoTela the tamanhoTela to set
     */
    public void setTamanhoTela(double tamanhoTela) {
        if (tamanhoTela <= 0) {
            System.out.ptintln("O tamanho da tela deve ser maior que 0.");
        }
        this.tamanhoTela = tamanhoTela;
    }

    @Override
    public String exibirDetalhes() {
        return String.format("Notebook: %s (%s), %s, %.1f\"", nome, marca, processador, tamanhoTela);
    }
}
