package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Arrays;
import java.util.Objects;

public class Circular extends Deliberacao{
    private String[] destinatarios;

    public Circular(String criador, CodigoCurso codigoCurso, int paginas, String texto,
                    String[] destinatarios) {
        super(criador, codigoCurso, paginas, texto);
        this.destinatarios = destinatarios;
    }

    public String[] getDestinatarios() {
        return destinatarios;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Circular circular = (Circular) o;
        return Arrays.equals(destinatarios, circular.destinatarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Arrays.hashCode(destinatarios));
    }
}
