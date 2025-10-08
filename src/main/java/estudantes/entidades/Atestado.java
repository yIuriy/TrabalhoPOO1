package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;


/**
 * Representa um {@code Atestado}, que é uma especialização de {@code Registro}.
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 * @see Registro
 */
public class Atestado extends Registro {
    private String descricao;
    private String categoria;

    /**
     * Cria uma instância de {@code Registro}.
     * <p>Utiliza o construtor da superclasse {@link Registro} através de {@code super()} para inicializar os atributos
     * herdados.</p>
     *
     * @param criador      o criador do {@code Atestado}
     * @param codigoCurso  o {@code Curso} ao qual este {@code Atestado} está associado
     * @param paginas      a quantidade de páginas
     * @param autenticacao o número da autenticação
     * @param estudante    o estudante do {@code Atestado}
     * @param matricula    o número da matrícula do estudante associado ao {@code Atestado}
     * @param descricao    a descricao do {@code Atestado}
     * @param categoria    a categoria do {@code Atestado}
     * @see CodigoCurso
     * @see Documento
     * @see DocumentoAcademico
     * @see Registro
     * @since 1.0
     */
    public Atestado(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante,
                    long matricula, String descricao, String categoria) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);
        this.descricao = descricao;
        this.categoria = categoria;
    }

    /**
     * Retorna a descricao do {@code Atestado}.
     *
     * @return a descricao do {@code Atestado}
     * @since 1.0
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna a categoria do {@code Atestado}.
     *
     * @return a categoria do {@code Atestado}
     * @since 1.0
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Compara este {@code Atestado} com o objeto especificado para verificar igualdade.
     *
     * <p>A comparação considera:
     * <ul>
     *   <li>Os campos herdados de {@code Registro}, por meio de {@code super.equals(o)};</li>
     *   <li>Os campos {@code descricao} e {@code categoria} desta classe.</li>
     * </ul>
     *
     * @param o o objeto a ser comparado
     * @return {@code true} se ambos os Objetos forem instâncias de {@code Atestado} e possuírem os mesmo valores
     * nos atributos herdados e nos campos {@code descricao} e {@code categoria}; {@code false} caso contrário
     * @see #hashCode()
     * @see Registro#equals(Object)
     * @see Objects#equals(Object, Object)
     * @since 1.0
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Atestado atestado = (Atestado) o;
        return Objects.equals(descricao, atestado.descricao) && Objects.equals(categoria, atestado.categoria);
    }

    /**
     * Retorna o código hash deste {@code Atestado}.
     *
     * <p>O valor retornado é consistente com {@link #equals(Object)}, de modo que dois objetos
     * iguais produzem o mesmo código hash.</p>
     *
     * <p>O valor retornado é calculado a partir do código hash da superclasse {@code super.hashCode()} combinado com
     * os campos {@code descricao} e {@code categoria}.</p>
     *
     * @return o código hash do objeto
     * @see #equals(Object)
     * @see Objects#hash(Object...)
     * @see Registro#hashCode()
     * @since 1.0
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), descricao, categoria);
    }
}
