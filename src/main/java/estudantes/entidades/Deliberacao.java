package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Objects;

/**
 * Representa uma {@code Deliberacao}, que é uma especialização de {@code DocumentoAdministrativo}.
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 * @see DocumentoAdministrativo
 */
public class Deliberacao extends DocumentoAdministrativo {
    private String texto; // Era String, mas tava dando erro

    /**
     * Cria uma instância de {@code Registro}.
     * <p>Utiliza o construtor da superclasse {@link DocumentoAdministrativo} através de {@code super()} para inicializar os atributos
     * herdados.</p>
     *
     * @param criador     o criador do {@code Deliberacao}
     * @param codigoCurso o {@code Curso} ao qual este {@code Deliberacao} está associado
     * @param paginas     a quantidade de páginas
     * @param texto       o texto da {@code Deliberacao}
     * @see CodigoCurso
     * @see Documento
     * @see DocumentoAdministrativo
     * @since 1.0
     *
     */
    public Deliberacao(String criador, CodigoCurso codigoCurso,
                       int paginas, String texto) {
        super(criador, codigoCurso, paginas);
        this.texto = texto;
    }

    /**
     * Retorna o texto da {@code Deliberacao}.
     *
     * @return o texto da {@code Deliberacao}
     * @since 1.0
     */
    public String getTexto() {
        return texto;
    }


    /**
     * Compara esta {@code Deliberacao} com o objeto especificado para verificar igualdade.
     *
     * <p>A comparação considera:
     * <ul>
     *   <li>Os campos herdados de {@code DocumentoAdministrativo}, por meio de {@code super.equals(o)};</li>
     *   <li>O campo {@code texto} desta classe.</li>
     * </ul>
     *
     * @param o o objeto a ser comparado
     * @return {@code true} se ambos os Objetos forem instâncias de {@code Deliberacao} e possuírem os mesmo valores
     * nos atributos herdados e no campo {@code texto}; {@code false} caso contrário
     * @see #hashCode()
     * @see DocumentoAdministrativo#equals(Object)
     * @see Objects#equals(Object, Object)
     * @since 1.0
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Deliberacao that = (Deliberacao) o;
        return Objects.equals(texto, that.texto);
    }

    /**
     * Retorna o código hash desta {@code Deliberacao}.
     *
     * <p>O valor retornado é consistente com {@link #equals(Object)}, de modo que dois objetos
     * iguais produzem o mesmo código hash.</p>
     *
     * <p>O valor retornado é calculado a partir do código hash da superclasse {@code super.hashCode()} combinado com
     * o campo {@code texto}.</p>
     *
     * @return o código hash do objeto
     * @see #equals(Object)
     * @see Objects#hash(Object...)
     * @see DocumentoAdministrativo#hashCode()
     * @since 1.0
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Objects.hash(texto));
    }
}
