package tcc.ufpb.com.br.ormlitetcctest;

/**
 * Created by Deyvison on 28/01/2017.
 */
public class Contexto {

    private String nome;
    private String pathImagem;

    private boolean isDefault;

    public Contexto(String nome, String pathImagem, boolean isDefault) {
        this.nome = nome;
        this.pathImagem = pathImagem;
        this.isDefault = isDefault;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPathImagem() {
        return pathImagem;
    }

    public void setPathImagem(String pathImagem) {
        this.pathImagem = pathImagem;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
