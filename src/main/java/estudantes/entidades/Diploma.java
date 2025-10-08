package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

/**
 * Representa um {@code Diploma}, que é uma especialização de {@code Certificado}.
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 * @see Certificado
 */
public class Diploma extends Certificado {
    private String habilitacao;

    /**
     * Cria uma instância de {@code Diploma}.
     * <p>Utiliza o construtor da superclasse {@link Certificado} através de {@code super()} para inicializar os atributos
     * herdados.</p>
     *
     * @param criador      o criador do {@code Diploma}
     * @param codigoCurso  o {@code Curso} ao qual este {@code Diploma} está associado
     * @param paginas      a quantidade de páginas
     * @param autenticacao o número da autenticação
     * @param estudante    o estudante do {@code Diploma}
     * @param matricula    o número da matrícula do estudante associado ao {@code Diploma}
     * @param descricao    a descrição do {@code Diploma}
     * @param habilitacao  a habilitação do {@code Diploma}
     * @see CodigoCurso
     * @see Documento
     * @see DocumentoAcademico
     * @see Registro
     * @see Certificado
     * @since 1.0
     *
     */
    public Diploma(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante,
                   long matricula, String descricao, String habilitacao) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula, descricao);
        this.habilitacao = habilitacao;
    }

    /**
     * Retorna a habilitação do {@code Diploma}.
     *
     * @return a habilitação do {@code Diploma}
     * @since 1.0
     *
     */
    public String getHabilitacao() {
        return habilitacao;
    }

    /**
     * Compara este {@code Certificado} com o objeto especificado para verificar igualdade.
     *
     * <p>A comparação considera:
     * <ul>
     *   <li>Os campos herdados de {@code Certificado}, por meio de {@code super.equals(o)};</li>
     *   <li>O campo {@code habilitacao} desta classe.</li>
     * </ul>
     *
     * @param o o objeto a ser comparado
     * @return {@code true} se ambos os Objetos forem instâncias de {@code Diploma} e possuírem os mesmo valores
     * nos atributos herdados e no campo {@code habilitacao}; {@code false} caso contrário
     * @see #hashCode()
     * @see Certificado#equals(Object)
     * @see Objects#equals(Object, Object)
     * @since 1.0
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Diploma diploma = (Diploma) o;
        return Objects.equals(habilitacao, diploma.habilitacao);
    }

    /**
     * Retorna o código hash deste {@code Diploma}.
     *
     * <p>O valor retornado é consistente com {@link #equals(Object)}, de modo que dois objetos
     * iguais produzem o mesmo código hash.</p>
     *
     * <p>O valor retornado é calculado a partir do código hash da superclasse {@code super.hashCode()} combinado com
     * o campo {@code habilitacao}.</p>
     *
     * @return o código hash do objeto
     * @see #equals(Object)
     * @see Objects#hash(Object...)
     * @see Certificado#hashCode()
     * @since 1.0
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), habilitacao);
    }
}
