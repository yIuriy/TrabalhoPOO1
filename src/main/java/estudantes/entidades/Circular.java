package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Arrays;
import java.util.Objects;

/**
 * Representa um {@code Circular}, que é uma especialização de {@code Deliberacao}.
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 * @see Deliberacao
 */
public class Circular extends Deliberacao {
    private String[] destinatarios;

    /**
     * Cria uma instância de {@code Circular}.
     * <p>Utiliza o construtor da superclasse {@link Deliberacao} através de {@code super()} para inicializar os atributos
     * herdados.</p>
     *
     * @param criador       o criador do {@code Circular}
     * @param codigoCurso   o {@code Curso} ao qual este {@code Circular} está associado
     * @param paginas       a quantidade de páginas
     * @param texto         o texto da {@code Circular}
     * @param destinatarios os destinatario do {@code Circular}
     * @see CodigoCurso
     * @see Documento
     * @see DocumentoAdministrativo
     * @see Deliberacao
     * @since 1.0
     */
    public Circular(String criador, CodigoCurso codigoCurso, int paginas, String texto,
                    String[] destinatarios) {
        super(criador, codigoCurso, paginas, texto);
        this.destinatarios = destinatarios;
    }

    /**
     * Retorna os destinatarios do {@code Circular}.
     *
     * @return {@code String[]} com os destinatarios do {@code Circular}
     * @since 1.0
     */
    public String[] getDestinatarios() {
        return destinatarios;
    }

    /**
     * Compara este {@code Circular} com o objeto especificado para verificar igualdade.
     *
     * <p>A comparação considera:
     * <ul>
     *   <li>Os campos herdados de {@code Deliberacao}, por meio de {@code super.equals(o)};</li>
     *   <li>O campo {@code destinatarios} desta classe.</li>
     * </ul>
     *
     * @param o o objeto a ser comparado
     * @return {@code true} se ambos os Objetos forem instâncias de {@code Oficio} e possuírem os mesmo valores
     * nos atributos herdados e no campo {@code destinatarios}; {@code false} caso contrário
     * @see #hashCode()
     * @see Deliberacao#equals(Object)
     * @see Arrays#equals(Object[], Object[])
     * @since 1.0
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Circular circular = (Circular) o;
        return Arrays.equals(destinatarios, circular.destinatarios);
    }

    /**
     * Retorna o código hash deste {@code Circular}.
     *
     * <p>O valor retornado é consistente com {@link #equals(Object)}, de modo que dois objetos
     * iguais produzem o mesmo código hash.</p>
     *
     * <p>O valor retornado é calculado a partir do código hash da superclasse {@code super.hashCode()} combinado com
     * o campo {@code destinatarios}.</p>
     *
     * @return o código hash do objeto
     * @see #equals(Object)
     * @see Objects#hash(Object...)
     * @see Arrays#hashCode(Object[])
     * @see Deliberacao#hashCode()
     * @since 1.0
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Arrays.hashCode(destinatarios));
    }
}
