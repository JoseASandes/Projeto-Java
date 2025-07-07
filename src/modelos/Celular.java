package modelos;

public class Celular extends Produto {
    private int memoriaRAM;
    private int armazenamento;

    public Celular(String nome, String marca, int memoriaRAM, int armazenamento) {
        super(nome, marca);
        this.setMemoriaRAM(memoriaRAM);
        this.setArmazenamento(armazenamento);
    }
    
    public void setMemoriaRAM(int memoriaRAM) {
        if (memoriaRAM > 0) {
            this.memoriaRAM = memoriaRAM;
        } else {
            System.out.println("A memoria RAM deve ser maior do que 0.");
        }
    }

    public void setArmazenamento(int armazenamento) {
        if (armazenamento > 0) {
            this.armazenamento = armazenamento;
        } else {
            System.out.println("O armazenamento deve ser maior que 0.");
        }
    }

    public int getMemoriaRAM() { 
        return memoriaRAM; 
    }

    public int getArmazenamento() { 
        return armazenamento; 
    }
    
    @Override
    public String exibirDetalhes() {
        return String.format("Celular: %s %s, RAM: %dGB, Armazenamento: %dGB", nome, marca, memoriaRAM, armazenamento);
    }
}