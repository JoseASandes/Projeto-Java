/**
 * 
 */
package modelos;

import java.util.List;

/**
 * 
 */
public class Celular extends Produto {
    private int memoriaRAM;
    private int armazenamento;

    /**
     * 
     */

    public Celular() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param memoriaRAM
     * @param armazenamento
     * @param nome
     * @param marca
     * @param avaliacoes
     */
    public Celular(String nome, String marca, int memoriaRAM, int armazenamento) {
        super(nome, marca);
        this.memoriaRAM = memoriaRAM;
        this.armazenamento = armazenamento;
    }

    /**
     * @return the memoriaRAM
     */
    public int getMemoriaRAM() {
        return memoriaRAM;
    }

    /**
     * @param memoriaRAM the memoriaRAM to set
     */
    public void setMemoriaRAM(int memoriaRAM) {
        if (memoriaRAM <= 0) {
            System.out.println("Memoria RAM deve ser maior do que 0.");
        }
        this.memoriaRAM = memoriaRAM;
    }

    /**
     * @return the armazenamento
     */
    public int getArmazenamento() {
        return armazenamento;
    }

    /**
     * @param armazenamento the armazenamento to set
     *                      EVITA O ERRO DE VALORES COMO 0 OU MENOR QUE ZERO PARA
     *                      MEMORIA
     */
    public void setArmazenamento(int armazenamento) {
        if (armazenamento <= 0) {
            throw new IllegalArgumentException("Armazenamento deve ser maior que 0!");
        }
        this.armazenamento = armazenamento;
    }

    @Override
    public String exibirDetalhes() {
        return String.format("Celular: %s %s, %d, %d\"", nome, marca, memoriaRAM, armazenamento);
    }
}
