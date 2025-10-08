package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

/**
 * Classe abstrata que representa um documento genérico.
 * <p>Todos os documentos do sistema herdam de {@code Documento}.</p>
 * 
 * @author Iuri da Silva Fernandes
 * @version 1.0
 */
public abstract class Documento {
    private String criador;
    private CodigoCurso codigoCurso;
    private int paginas;

    /**
     * Cria uma instância de {@code Documento}.
     * <p>Não é utilizado diretamente, servindo para ser herdado pelas subclasses de {@code Documento}.</p>
     *
     * @param criador o criador do {@code Documento}
     * @param codigoCurso o {@code Curso} ao qual este {@code Documento} está associado
     * @param paginas a quantidade de páginas do {@code Documento}
     *
     * @see CodigoCurso
     *
     * */
    public Documento(String criador, CodigoCurso codigoCurso, int paginas) {
        this.criador = criador;
        this.codigoCurso = codigoCurso;
        this.paginas = paginas;
    }

    /**
     * Retorna o criador do {@code Documento}.
     *
     * @return o criador do {@code Documento}
     *
     * @since 1.0
     * */
    public String getCriador() {
        return criador;
    }

    /**
     * Retorna o código do curso do {@code Documento}.
     *
     * @return o criador do {@code Documento}
     *
     * @since 1.0
     *
     * */
    public CodigoCurso getCodigoCurso() {
        return codigoCurso;
    }

    /**
     * Retorna o número de páginas do {@code Documento}.
     *
     * @return o número de páginas do {@code Documento}
     *
     * @since 1.0
     *
     * */
    public int getPaginas() {
        return paginas;
    }

    /**
     * Compara este {@code Documento} com o objeto especificado para verificar igualdade.
     *
     * <p>Dois {@code Documentos} são considerados iguais se tiverem o mesmo criador, código do curso e criador.</p>
     *
     * @param o o objeto a ser comparado
     * @return {@code true} se os objetos forem iguais; {@code false} caso contrário
     *
     * @see #hashCode()
     * @since 1.0
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Documento documento = (Documento) o;
        return paginas == documento.paginas && Objects.equals(criador, documento.criador) && codigoCurso == documento.codigoCurso;
    }


    /**
     * Retorna o código hash deste {@code Documento}.
     *
     * <p>O valor retornado é consistente com {@link #equals(Object)}, de modo que dois objetos
     * iguais produzem o mesmo código hash.</p>
     *
     * @return o código hash do objeto
     *
     * @see #equals(Object)
     * @see Objects#hash(Object...)
     * @since 1.0
     */
    @Override
    public int hashCode() {
        return Objects.hash(criador, codigoCurso, paginas);
    }
}
