package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Arrays;
import java.util.Objects;

public class Ata extends Documento {
    private int numero;
    private String texto;
    private String[] presentes;

    public Ata(String criador, CodigoCurso codigoCurso, int paginas,
               int numero, String texto, String[] presentes) {
        super(criador, codigoCurso, paginas);
        this.numero = numero;
        this.texto = texto;
        this.presentes = presentes;
    }

    public int getNumero() {
        return numero;
    }

    public String getTexto() {
        return texto;
    }

    public String[] getPresentes() {
        return presentes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ata ata = (Ata) o;
        return numero == ata.numero && texto.equals(ata.texto) && Arrays.equals(presentes, ata.presentes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numero, texto, Arrays.hashCode(presentes));
    }
}
