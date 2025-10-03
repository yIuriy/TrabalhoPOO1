package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

public abstract class DocumentoAcademico extends Documento{
    private long autenticacao;

    public DocumentoAcademico(String criador, CodigoCurso codigoCurso,
                              int paginas, long autenticacao) {
        super(criador, codigoCurso, paginas);
        this.autenticacao = autenticacao;
    }

    public long getAutenticacao() {
        return autenticacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DocumentoAcademico d = (DocumentoAcademico) o;
        return autenticacao == d.autenticacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), autenticacao);
    }
}
