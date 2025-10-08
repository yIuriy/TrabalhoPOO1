package estudantes.entidades;

import professor.entidades.CodigoCurso;

/**
 * Representa um {@code DocumentoAdministrativo}, que é uma especialização <strong>abstrata</strong> de {@code Documento}.
 *
 * <p>É a superclasse de todos os Documentos Administrativos.</p>
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 * @see Documento
 */
public abstract class DocumentoAdministrativo extends Documento {

    /**
     * Cria uma instância de {@code DocumentAdministrativo}.
     * <p>Utiliza o construtor da superclasse {@link Documento} através de {@code super()} para inicializar os atributos
     * herdados.</p>
     *
     * <p>Não é utilizado diretamente, servindo para ser herdado pelas subclasses de {@code DocumentoAdministrativo}.</p>
     *
     * @param criador     o criador do {@code DocumentoAcademico}
     * @param codigoCurso o {@code Curso} ao qual este {@code DocumentoAcademico} está associado
     * @param paginas     a quantidade de páginas
     * @see CodigoCurso
     * @see Documento
     * @since 1.0
     */
    public DocumentoAdministrativo(String criador, CodigoCurso codigoCurso, int paginas) {
        super(criador, codigoCurso, paginas);
    }

    /**
     * Compara este {@code DocumentoAdministrativo} com o objeto especificado para verificar igualdade.
     *
     * <p>A comparação considera:
     * <ul>
     *   <li>Os campos herdados de {@code Documento}, por meio de {@code super.equals(o)};</li>
     * </ul>
     *
     * @param o o objeto a ser comparado
     * @return {@code true} se ambos os Objetos forem instâncias de {@code DocumentoAdministrativo} e possuírem os
     * mesmo valores nos atributos herdados; {@code false} caso contrário
     * @see #hashCode()
     * @see Documento#equals(Object)
     * @since 1.0
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * Retorna o código hash deste {@code DocumentoAdministrativo}.
     *
     * <p>O valor retornado é consistente com {@link #equals(Object)}, de modo que dois objetos
     * iguais produzem o mesmo código hash.</p>
     *
     * <p>O valor retornado é calculado a partir do código hash da superclasse {@code super.hashCode()}.</p>
     *
     * @return o código hash do objeto
     * @see #equals(Object)
     * @see Documento#hashCode()
     * @since 1.0
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
