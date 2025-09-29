package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

public class Certificado extends Registro{
    private String descricao;

    public Certificado(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante,
                       long matricula, String descricao) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Certificado that = (Certificado) o;
        return Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), descricao);
    }
}


