package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

public class Atestado extends Registro{
    private String descricao;
    private String categoria;

    public Atestado(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante,
                    long matricula, String descricao, String categoria) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Atestado atestado = (Atestado) o;
        return Objects.equals(descricao, atestado.descricao) && Objects.equals(categoria, atestado.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), descricao, categoria);
    }
}
