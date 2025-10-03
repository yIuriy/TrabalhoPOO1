package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Arrays;
import java.util.Objects;

public class Edital extends Norma{
    private String[] responsaveis;

    public Edital(String criador, CodigoCurso codigoCurso, int paginas, int numero,
                  boolean valido, String texto, String[] responsaveis) {
        super(criador, codigoCurso, paginas, numero, valido, texto);
        this.responsaveis = responsaveis;
    }

    public String[] getResponsaveis() {
        return responsaveis;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Edital edital = (Edital) o;
        return Arrays.equals(responsaveis, edital.responsaveis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Arrays.hashCode(responsaveis));
    }
}
