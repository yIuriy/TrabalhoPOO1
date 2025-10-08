package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Arrays;
import java.util.Objects;

/**
 * Representa um {@code Certificado}, que é uma especialização de {@code Registro}.
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 * @see Registro
 */
public class Certificado extends Registro {
    private String descricao;

    /**
     * Cria uma instância de {@code Registro}.
     * <p>Utiliza o construtor da superclasse {@link Registro} através de {@code super()} para inicializar os atributos
     * herdados.</p>
     *
     * @param criador      o criador do {@code Certificado}
     * @param codigoCurso  o {@code Curso} ao qual este {@code Certificado} está associado
     * @param paginas      a quantidade de páginas
     * @param autenticacao o número da autenticação
     * @param estudante    o estudante do {@code Certificado}
     * @param matricula    o número da matrícula do estudante associado ao {@code Certificado}
     * @param descricao    a descrição do {@code Certificado}
     * @see CodigoCurso
     * @see Documento
     * @see DocumentoAcademico
     * @see Registro
     * @since 1.0
     *
     */
    public Certificado(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante,
                       long matricula, String descricao) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);
        this.descricao = descricao;
    }

    /**
     * Retorna a descrição do {@code Certificado}.
     *
     * @return a descrição do {@code Certificado}
     * @since 1.0
     *
     */
    public String getDescricao() {
        return descricao;
    }


    /**
     * Compara este {@code Certificado} com o objeto especificado para verificar igualdade.
     *
     * <p>A comparação considera:
     * <ul>
     *   <li>Os campos herdados de {@code Registro}, por meio de {@code super.equals(o)};</li>
     *   <li>O campo {@code descricao} desta classe.</li>
     * </ul>
     *
     * @param o o objeto a ser comparado
     * @return {@code true} se ambos os Objetos forem instâncias de {@code Certificado} e possuírem os mesmo valores
     * nos atributos herdados e no campo {@code descricao}; {@code false} caso contrário
     * @see #hashCode()
     * @see Registro#equals(Object)
     * @see Objects#equals(Object, Object)
     * @since 1.0
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Certificado c = (Certificado) o;
        return Objects.equals(descricao, c.descricao);
    }

    /**
     * Retorna o código hash deste {@code Certificado}.
     *
     * <p>O valor retornado é consistente com {@link #equals(Object)}, de modo que dois objetos
     * iguais produzem o mesmo código hash.</p>
     *
     * <p>O valor retornado é calculado a partir do código hash da superclasse {@code super.hashCode()} combinado com
     * o campo {@code descricao}.</p>
     *
     * @return o código hash do objeto
     * @since 1.0
     * @see #equals(Object)
     * @see Objects#hash(Object...)
     * @see Registro#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), descricao);
    }
}


