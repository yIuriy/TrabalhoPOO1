package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

/**
 * Representa um {@code Oficio}, que é uma especialização de {@code Deliberacao}.
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 * @see Deliberacao
 */
public class Oficio extends Deliberacao {
    private String destinatario;

    /**
     * Cria uma instância de {@code Oficio}.
     * <p>Utiliza o construtor da superclasse {@link Deliberacao} através de {@code super()} para inicializar os atributos
     * herdados.</p>
     *
     * @param criador      o criador do {@code Oficio}
     * @param codigoCurso  o {@code Curso} ao qual este {@code Oficio} está associado
     * @param paginas      a quantidade de páginas
     * @param texto        o texto da {@code Oficio}
     * @param destinatario o destinatario do {@code Oficio}
     * @see CodigoCurso
     * @see Documento
     * @see DocumentoAdministrativo
     * @see Deliberacao
     * @since 1.0
     */
    public Oficio(String criador, CodigoCurso codigoCurso, int paginas,
                  String texto, String destinatario) {
        super(criador, codigoCurso, paginas, texto);
        this.destinatario = destinatario;
    }

    /**
     * Retorna o destinatario do {@code Oficio}.
     *
     * @return o destinatario do {@code Oficio}
     * @since 1.0
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     * Compara este {@code Oficio} com o objeto especificado para verificar igualdade.
     *
     * <p>A comparação considera:
     * <ul>
     *   <li>Os campos herdados de {@code Deliberacao}, por meio de {@code super.equals(o)};</li>
     *   <li>O campo {@code destinatario} desta classe.</li>
     * </ul>
     *
     * @param o o objeto a ser comparado
     * @return {@code true} se ambos os Objetos forem instâncias de {@code Oficio} e possuírem os mesmo valores
     * nos atributos herdados e no campo {@code destinatario}; {@code false} caso contrário
     * @see #hashCode()
     * @see Deliberacao#equals(Object)
     * @see Objects#equals(Object, Object)
     * @since 1.0
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Oficio oficio = (Oficio) o;
        return Objects.equals(destinatario, oficio.destinatario);
    }

    /**
     * Retorna o código hash deste {@code Oficio}.
     *
     * <p>O valor retornado é consistente com {@link #equals(Object)}, de modo que dois objetos
     * iguais produzem o mesmo código hash.</p>
     *
     * <p>O valor retornado é calculado a partir do código hash da superclasse {@code super.hashCode()} combinado com
     * o campo {@code destinatario}.</p>
     *
     * @return o código hash do objeto
     * @see #equals(Object)
     * @see Objects#hash(Object...)
     * @see Deliberacao#hashCode()
     * @since 1.0
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), destinatario);
    }
}
