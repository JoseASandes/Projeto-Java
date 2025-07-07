package modelos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import enums.StatusUsuario;
import interfaces.Moderacao;
public class Usuario implements Moderacao {
    private String nome;
    private String email;
    private StatusUsuario status;
    private List<Avaliacao> avaliacoesFeita = new ArrayList<Avaliacao>();

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.status = status.PENDENTE_APROVACAO;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }

    public List<Avaliacao> getAvaliacoesFeitas() {
        return Collections.unmodifiableList(avaliacoesFeita);
    }

    public void setAvaliacoesFeita(List<Avaliacao> avaliacoesFeita) {
        this.avaliacoesFeita = avaliacoesFeita;
    }

    public void avaliarProduto(Produto produto, int nota, String texto) {
        Avaliacao a = new Avaliacao(nota, texto, this, produto);
        avaliacoesFeita.add(a);
        produto.adicionarAvaliacao(a);
    }

    @Override
    public void aprovar() {
        this.status = StatusUsuario.ATIVO;
    }

    @Override
    public void rejeitar() {
        this.status = StatusUsuario.BLOQUEADO;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }
}
