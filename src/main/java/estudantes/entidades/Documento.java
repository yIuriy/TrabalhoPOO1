package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

/**
 * Classe que representa um documento genérico.
 * <br><br>
 * <strong>Seu trabalho começa aqui...</strong>
 * 
 * @author coloque os nomes dos autores aqui
 */
public abstract class Documento {
    private String criador;
    private CodigoCurso codigoCurso;
    private int paginas;

    public Documento(String criador, CodigoCurso codigoCurso, int paginas) {
        this.criador = criador;
        this.codigoCurso = codigoCurso;
        this.paginas = paginas;
    }

    public String getCriador() {
        return criador;
    }

    public CodigoCurso getCodigoCurso() {
        return codigoCurso;
    }

    public int getPaginas() {
        return paginas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Documento documento = (Documento) o;
        return paginas == documento.paginas && Objects.equals(criador, documento.criador) && codigoCurso == documento.codigoCurso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(criador, codigoCurso, paginas);
    }
}

//quadrados vermelhos vazados indicam
// visibilidade privada e círculos verdes indicam visibilidade pública.
