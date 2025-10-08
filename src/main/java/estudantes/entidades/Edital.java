package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Arrays;
import java.util.Objects;

/**
 * Representa um {@code Edital}, que é uma especialização de {@code Norma}.
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 * @see Norma
 */
public class Edital extends Norma {
    private String[] responsaveis;

    /**
     * Cria uma instância de {@code Edital}.
     * <p>Utiliza o construtor da superclasse {@link Norma} através de {@code super()} para inicializar os atributos
     * herdados.</p>
     *
     * @param criador      o criador do {@code Edital}
     * @param codigoCurso  o {@code Curso} ao qual este {@code Edital} está associado
     * @param paginas      a quantidade de páginas
     * @param numero       o número do {@code Edital}
     * @param valido       se o {@code Edital} está válido
     * @param texto        o texto do {@code Edital}
     * @param responsaveis os responsáveis pelo {@code Edital}
     * @see CodigoCurso
     * @see Documento
     * @see DocumentoAdministrativo
     * @see Norma
     * @since 1.0
     */
    public Edital(String criador, CodigoCurso codigoCurso, int paginas, int numero,
                  boolean valido, String texto, String[] responsaveis) {
        super(criador, codigoCurso, paginas, numero, valido, texto);
        this.responsaveis = responsaveis;
    }

    /**
     * Retorna os responsáveis pelo {@code Edital}.
     *
     * @return {@code String[]} com os responsáveis pelo {@code Edital}
     * @since 1.0
     */
    public String[] getResponsaveis() {
        return responsaveis;
    }

    /**
     * Compara este {@code Edital} com o objeto especificado para verificar igualdade.
     *
     * <p>A comparação considera:
     * <ul>
     *   <li>Os campos herdados de {@code Norma}, por meio de {@code super.equals(o)};</li>
     *   <li>O campo {@code responsaveis} desta classe.</li>
     * </ul>
     *
     * @param o o objeto a ser comparado
     * @return {@code true} se ambos os Objetos forem instâncias de {@code Edital} e possuírem os mesmo valores
     * nos atributos herdados e no campo {@code responsaveis}; {@code false} caso
     * contrário
     * @see #hashCode()
     * @see Norma#equals(Object)
     * @see Objects#equals(Object, Object)
     * @see Arrays#equals(Object[], Object[])
     * @since 1.0
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Edital edital = (Edital) o;
        return Arrays.equals(responsaveis, edital.responsaveis);
    }

    /**
     * Retorna o código hash deste {@code Edital}.
     *
     * <p>O valor retornado é consistente com {@link #equals(Object)}, de modo que dois objetos
     * iguais produzem o mesmo código hash.</p>
     *
     * <p>O valor retornado é calculado a partir do código hash da superclasse {@code super.hashCode()} combinado com
     * o campo {@code responsaveis}.</p>
     *
     * @return o código hash do objeto
     * @see #equals(Object)
     * @see Objects#hash(Object...)
     * @see Norma#hashCode()
     * @since 1.0
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Arrays.hashCode(responsaveis));
    }
}
