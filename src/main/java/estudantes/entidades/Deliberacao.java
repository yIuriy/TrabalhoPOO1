package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Arrays;
import java.util.Objects;

public class Deliberacao extends DocumentoAdministrativo {
    private String texto; // Era String, mas tava dando erro

    public Deliberacao(String criador, CodigoCurso codigoCurso,
                       int paginas, String texto) {
        super(criador, codigoCurso, paginas);
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Deliberacao that = (Deliberacao) o;
        return Objects.deepEquals(texto, that.texto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Objects.hash(texto));
    }
}
