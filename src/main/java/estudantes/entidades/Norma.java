package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

/**
 * Representa uma {@code Norma}, que é uma especialização de {@code DocumentoAdministrativo}.
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 * @see DocumentoAdministrativo
 */
public class Norma extends DocumentoAdministrativo {
    private int numero;
    private boolean valido;
    private String texto;

    /**
     * Cria uma instância de {@code Norma}.
     * <p>Utiliza o construtor da superclasse {@link DocumentoAdministrativo} através de {@code super()} para inicializar os atributos
     * herdados.</p>
     *
     * @param criador     o criador do {@code Norma}
     * @param codigoCurso o {@code Curso} ao qual este {@code Norma} está associado
     * @param paginas     a quantidade de páginas
     * @param numero      o número da {@code Norma}
     * @param valido      se a {@code Norma} está válida
     * @param texto       o texto da {@code Norma}
     * @see CodigoCurso
     * @see Documento
     * @see DocumentoAdministrativo
     * @since 1.0
     *
     */
    public Norma(String criador, CodigoCurso codigoCurso, int paginas,
                 int numero, boolean valido, String texto) {
        super(criador, codigoCurso, paginas);
        this.numero = numero;
        this.valido = valido;
        this.texto = texto;
    }

    /**
     * Retorna o número da {@code Norma}.
     *
     * @return o número da {@code Norma}
     * @since 1.0
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Retorna se {@code Norma} está válida.
     *
     * @return a validade da {@code Norma}
     * @since 1.0
     */
    public boolean getValido() {
        return valido;
    }

    /**
     * Retorna o texto da {@code Norma}.
     *
     * @return o texto da {@code Norma}
     * @since 1.0
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Compara esta {@code Norma} com o objeto especificado para verificar igualdade.
     *
     * <p>A comparação considera:
     * <ul>
     *   <li>Os campos herdados de {@code DocumentoAdministrativo}, por meio de {@code super.equals(o)};</li>
     *   <li>Os campos {@code numero}, {@code valido} e {@code texto} desta classe.</li>
     * </ul>
     *
     * @param o o objeto a ser comparado
     * @return {@code true} se ambos os Objetos forem instâncias de {@code Deliberacao} e possuírem os mesmo valores
     * nos atributos herdados e nos campos {@code numero}, {@code valido} e {@code texto}; {@code false} caso contrário
     * @see #hashCode()
     * @see DocumentoAdministrativo#equals(Object)
     * @see Objects#equals(Object, Object)
     * @since 1.0
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Norma norma = (Norma) o;
        return numero == norma.numero && valido == norma.valido && Objects.equals(texto, norma.texto);
    }

    /**
     * Retorna o código hash desta {@code Norma}.
     *
     * <p>O valor retornado é consistente com {@link #equals(Object)}, de modo que dois objetos
     * iguais produzem o mesmo código hash.</p>
     *
     * <p>O valor retornado é calculado a partir do código hash da superclasse {@code super.hashCode()} combinado com
     * os campos {@code numero}, {@code valido} e {@code texto}.</p>
     *
     * @return o código hash do objeto
     * @see #equals(Object)
     * @see Objects#hash(Object...)
     * @see DocumentoAdministrativo#hashCode()
     * @since 1.0
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numero, valido, texto);
    }
}
