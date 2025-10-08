package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Arrays;
import java.util.Objects;

/**
 * Representa uma {@code Ata}, que é uma especialização de {@code Documento}.
 *
 * <p>Não pode ser herdada.</p>
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 * @see Documento
 */
public final class Ata extends Documento {
    private int numero;
    private String texto;
    private String[] presentes;

    /**
     * Cria uma instância de {@code Ata}.
     * <p>Utiliza o construtor da superclasse {@link Documento} através de {@code super()} para inicializar os atributos
     * herdados.</p>
     *
     * @param criador     o criador da {@code Ata}
     * @param codigoCurso o {@code Curso} ao qual este {@code Ata} está associado
     * @param paginas     a quantidade de páginas do {@code Ata}
     * @param numero      o número da {@code Ata}
     * @param texto       o texto da {@code Ata}
     * @param presentes   os presentes na {@code Ata}
     * @see CodigoCurso
     * @see Documento
     * @since 1.0
     */
    public Ata(String criador, CodigoCurso codigoCurso, int paginas,
               int numero, String texto, String[] presentes) {
        super(criador, codigoCurso, paginas);
        this.numero = numero;
        this.texto = texto;
        this.presentes = presentes;
    }

    /**
     * Retorna o número da {@code Ata}.
     *
     * @return o número da {@code Ata}
     * @since 1.0
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Retorna o texto da {@code Ata}.
     *
     * @return o texto da {@code Ata}
     * @since 1.0
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Retorna os presentes da {@code Ata}.
     *
     * @return um {@code String[]} com os presentes da {@code Ata}
     * @since 1.0
     */
    public String[] getPresentes() {
        return presentes;
    }

    /**
     * Compara esta {@code Ata} com o objeto especificado para verificar igualdade.
     *
     * <p>A comparação considera:
     * <ul>
     *   <li>Os campos herdados de {@code Documento}, por meio de {@code super.equals(o)};</li>
     *   <li>Os campos {@code numero}, {@code texto} e {@code presentes} desta classe.</li>
     * </ul>
     *
     * @param o o objeto a ser comparado
     * @return {@code true} se ambos os Objetos forem instâncias de {@code Ata} e possuírem os mesmo valores
     * nos atributos herdados e nos campos {@code numero}, {@code texto} e {@code presentes}; {@code false} caso contrário
     * @see #hashCode()
     * @see Documento#equals(Object)
     * @since 1.0
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ata ata = (Ata) o;
        return numero == ata.numero && texto.equals(ata.texto) && Arrays.equals(presentes, ata.presentes);
    }

    /**
     * Retorna o código hash desta {@code Ata}.
     *
     * <p>O valor retornado é consistente com {@link #equals(Object)}, de modo que dois objetos
     * iguais produzem o mesmo código hash.</p>
     *
     * <p>O valor retornadao é calculado a partir do código hash da superclasse {@code super.hashCode()} combinado com os campos
     * {@code numero}, {@code texto} e {@code presentes}.</p>
     *
     * @return o código hash do objeto
     * @see #equals(Object)
     * @see Objects#hash(Object...)
     * @see Documento#hashCode()
     * @since 1.0
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numero, texto, Arrays.hashCode(presentes));
    }
}
