package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

public class Portaria extends Norma{
    private int anoInicio;

    public Portaria(String criador, CodigoCurso codigoCurso, int paginas, int numero,
                    boolean valido, String texto, int anoInicio) {
        super(criador, codigoCurso, paginas, numero, valido, texto);
        this.anoInicio = anoInicio;
    }

    public int getAnoInicio() {
        return anoInicio;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Portaria portaria = (Portaria) o;
        return anoInicio == portaria.anoInicio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), anoInicio);
    }
}
