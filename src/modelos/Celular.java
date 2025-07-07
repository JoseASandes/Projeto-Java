package modelos;

import java.util.List;
public class Celular extends Produto {
    private int memoriaRAM;
    private int armazenamento;

    public Celular() {
    }

    public Celular(String nome, String marca, int memoriaRAM, int armazenamento) {
        super(nome, marca);
        this.memoriaRAM = memoriaRAM;
        this.armazenamento = armazenamento;
    }

    public int getMemoriaRAM() {
        return memoriaRAM;
    }

    public void setMemoriaRAM(int memoriaRAM) {
        if (memoriaRAM <= 0) {
            System.out.println("Memoria RAM deve ser maior do que 0.");
        }
        this.memoriaRAM = memoriaRAM;
    }

    public int getArmazenamento() {
        return armazenamento;
    }

    public void setArmazenamento(int armazenamento) {
        if (armazenamento <= 0) {
            System.out.println("Armazenamento deve ser maior que 0!");
        }
        this.armazenamento = armazenamento;
    }

    @Override
    public String exibirDetalhes() {
        return String.format("Celular: %s %s, %d, %d\"", nome, marca, memoriaRAM, armazenamento);
    }
}
