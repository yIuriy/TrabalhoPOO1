package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

public class Oficio extends Deliberacao{
    private String destinatario;

    public Oficio(String criador, CodigoCurso codigoCurso, int paginas,
                  String texto, String destinatario) {
        super(criador, codigoCurso, paginas, texto);
        this.destinatario = destinatario;
    }

    public String getDestinatario() {
        return destinatario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Oficio oficio = (Oficio) o;
        return Objects.equals(destinatario, oficio.destinatario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), destinatario);
    }
}
