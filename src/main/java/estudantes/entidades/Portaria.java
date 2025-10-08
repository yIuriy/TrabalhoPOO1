package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

/**
 * Representa uma {@code Portaria}, que é uma especialização de {@code Norma}.
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 * @see Norma
 */
public class Portaria extends Norma {
    private int anoInicio;

    /**
     * Cria uma instância de {@code Portaria}.
     * <p>Utiliza o construtor da superclasse {@link Norma} através de {@code super()} para inicializar os atributos
     * herdados.</p>
     *
     * @param criador     o criador da {@code Portaria}
     * @param codigoCurso o {@code Curso} ao qual esta {@code Portaria} está associada
     * @param paginas     a quantidade de páginas
     * @param numero      o número da {@code Portaria}
     * @param valido      se a {@code Portaria} está válida
     * @param texto       o texto da {@code Portaria}
     * @param anoInicio   o ano de início da {@code Portaria}
     * @see CodigoCurso
     * @see Documento
     * @see DocumentoAdministrativo
     * @see Norma
     * @since 1.0
     */
    public Portaria(String criador, CodigoCurso codigoCurso, int paginas, int numero,
                    boolean valido, String texto, int anoInicio) {
        super(criador, codigoCurso, paginas, numero, valido, texto);
        this.anoInicio = anoInicio;
    }

    /**
     * Retorna o ano de início da {@code Portaria}.
     *
     * @return o ano de início da {@code Portaria}
     * @since 1.0
     */
    public int getAnoInicio() {
        return anoInicio;
    }

    /**
     * Compara esta {@code Portaria} com o objeto especificado para verificar igualdade.
     *
     * <p>A comparação considera:
     * <ul>
     *   <li>Os campos herdados de {@code Norma}, por meio de {@code super.equals(o)};</li>
     *   <li>O campo {@code anoDeInicio} desta classe.</li>
     * </ul>
     *
     * @param o o objeto a ser comparado
     * @return {@code true} se ambos os Objetos forem instâncias de {@code Portaria} e possuírem os mesmo valores
     * nos atributos herdados e no campo {@code anoDeInicio}; {@code false} caso
     * contrário
     * @see #hashCode()
     * @see Norma#equals(Object)
     * @since 1.0
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Portaria portaria = (Portaria) o;
        return anoInicio == portaria.anoInicio;
    }

    /**
     * Retorna o código hash deste {@code Portaria}.
     *
     * <p>O valor retornado é consistente com {@link #equals(Object)}, de modo que dois objetos
     * iguais produzem o mesmo código hash.</p>
     *
     * <p>O valor retornado é calculado a partir do código hash da superclasse {@code super.hashCode()} combinado com
     * o campo {@code anoDeInicio}.</p>
     *
     * @return o código hash do objeto
     * @see #equals(Object)
     * @see Objects#hash(Object...)
     * @see Norma#hashCode()
     * @since 1.0
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), anoInicio);
    }
}
