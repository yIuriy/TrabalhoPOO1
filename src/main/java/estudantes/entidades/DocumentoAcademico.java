package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

/**
 * Representa um {@code DocumentoAcademico}, que é uma especialização <strong>abstrata</strong> de {@code Documento}.
 *
 * <p>É a superclasse de todos os Documentos Academicos.</p>
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 * @see Documento
 */
public abstract class DocumentoAcademico extends Documento {
    private long autenticacao;

    /**
     * Cria uma instância de {@code DocumentoAcademico}.
     * <p>Utiliza o construtor da superclasse {@link Documento} através de {@code super()} para inicializar os atributos
     * herdados.</p>
     *
     * <p>Não é utilizado diretamente, servindo para ser herdado pelas subclasses de {@code DocumentoAcademico}.</p>
     *
     * @param criador      o criador do {@code DocumentoAcademico}
     * @param codigoCurso  o {@code Curso} ao qual este {@code DocumentoAcademico} está associado
     * @param paginas      a quantidade de páginas
     * @param autenticacao o número da autorização
     * @see CodigoCurso
     * @see Documento
     * @since 1.0
     *
     *
     */
    public DocumentoAcademico(String criador, CodigoCurso codigoCurso,
                              int paginas, long autenticacao) {
        super(criador, codigoCurso, paginas);
        this.autenticacao = autenticacao;
    }

    /**
     * Retorna o número de autenticação do {@code DocumentoAcademico}.
     *
     * @return o número de autenticação do {@code DocumentoAcademico}
     * @since 1.0
     *
     */
    public long getAutenticacao() {
        return autenticacao;
    }


    /**
     * Compara este {@code DocumentoAcademico} com o objeto especificado para verificar igualdade.
     *
     * <p>A comparação considera:
     * <ul>
     *   <li>Os campos herdados de {@code Documento}, por meio de {@code super.equals(o)};</li>
     *   <li>O campo {@code autenticacao} desta classe.</li>
     * </ul>
     *
     * @param o o objeto a ser comparado
     * @return {@code true} se ambos os Objetos forem instâncias de {@code DocumentoAcademico} e possuírem os mesmo valores
     * nos atributos herdados e no campo {@code autenticacao}; {@code false} caso contrário
     * @see #hashCode()
     * @see Documento#equals(Object)
     * @since 1.0
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DocumentoAcademico d = (DocumentoAcademico) o;
        return autenticacao == d.autenticacao;
    }

    /**
     * Retorna o código hash deste {@code DocumentoAcademico}.
     *
     * <p>O valor retornado é consistente com {@link #equals(Object)}, de modo que dois objetos
     * iguais produzem o mesmo código hash.</p>
     *
     * <p>O valor retornado é calculado a partir do código hash da superclasse {@code super.hashCode()} combinado com o campo
     * {@code autenticacao}.</p>
     *
     * @return o código hash do objeto
     * @see #equals(Object)
     * @see Objects#hash(Object...)
     * @see Documento#hashCode()
     * @since 1.0
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), autenticacao);
    }
}
