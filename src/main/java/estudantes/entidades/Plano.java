package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Arrays;
import java.util.Objects;


/**
 * Representa um {@code Plano}, que é uma especialização de {@code DocumentoAcademico}.
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 * @see Documento
 */
public class Plano extends DocumentoAcademico {
    private String responsavel;
    private String[] planejamento;

    /**
     * Cria uma instância de {@code Plano}.
     * <p>Utiliza o construtor da superclasse {@link DocumentoAcademico} através de {@code super()} para inicializar os atributos
     * herdados.</p>
     *
     * @param criador      o criador do {@code Plano}
     * @param codigoCurso  o {@code Curso} ao qual este {@code Plano} está associado
     * @param paginas      a quantidade de páginas
     * @param autenticacao o número da autorização
     * @param responsavel  o responsável pelo {@code Plano}
     * @param planejamento o planejamento do {@code Plano}
     * @see CodigoCurso
     * @see Documento
     * @see DocumentoAcademico
     * @since 1.0
     *
     */
    public Plano(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao,
                 String responsavel, String[] planejamento) {
        super(criador, codigoCurso, paginas, autenticacao);
        this.responsavel = responsavel;
        this.planejamento = planejamento;
    }

    /**
     * Retorna o responsável pelo {@code Plano}.
     *
     * @return o responsável pelo {@code Plano}
     * @since 1.0
     *
     */
    public String getResponsavel() {
        return responsavel;
    }


    /**
     * Retorna o planejamento do {@code Plano}.
     *
     * @return {@code String[]} com o planejamento do {@code Plano}
     * @since 1.0
     *
     */
    public String[] getPlanejamento() {
        return planejamento;
    }


    /**
     * Compara este {@code Plano} com o objeto especificado para verificar igualdade.
     *
     * <p>A comparação considera:
     * <ul>
     *   <li>Os campos herdados de {@code DocumentoAcademico}, por meio de {@code super.equals(o)};</li>
     *   <li>Os campo {@code responsavel} e {@code planejamento} desta classe.</li>
     * </ul>
     *
     * @param o o objeto a ser comparado
     * @return {@code true} se ambos os Objetos forem instâncias de {@code Plano} e possuírem os mesmo valores
     * nos atributos herdados e nos campos {@code responsavel} e {@code planejamento}; {@code false} caso contrário
     * @see #hashCode()
     * @see DocumentoAcademico#equals(Object)
     * @see Objects#equals(Object, Object)
     * @see Arrays#equals(Object[], Object[])
     * @since 1.0
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Plano plano = (Plano) o;
        return Objects.equals(responsavel, plano.responsavel) && Arrays.equals(planejamento, plano.planejamento);
    }

    /**
     * Retorna o código hash deste {@code Plano}.
     *
     * <p>O valor retornado é consistente com {@link #equals(Object)}, de modo que dois objetos
     * iguais produzem o mesmo código hash.</p>
     *
     * <p>O valor retornado é calculado a partir do código hash da superclasse {@code super.hashCode()} combinado com
     * os campos {@code responsavel} e {@code planejamento}.</p>
     *
     * @return o código hash do objeto
     * @see #equals(Object)
     * @see Objects#hash(Object...)
     * @see Arrays#hashCode(Object[])
     * @see DocumentoAcademico#hashCode()
     * @since 1.0
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), responsavel, Arrays.hashCode(planejamento));
    }
}
