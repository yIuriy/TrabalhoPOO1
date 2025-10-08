package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Arrays;
import java.util.Objects;


/**
 * Representa um {@code Historico}, que é uma especialização de {@code Registro}.
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 * @see Registro
 */
public class Historico extends Registro {
    private double coeficiente;
    private String[] componentes;


    /**
     * Cria uma instância de {@code Registro}.
     * <p>Utiliza o construtor da superclasse {@link Registro} através de {@code super()} para inicializar os atributos
     * herdados.</p>
     *
     * @param criador      o criador do {@code Historico}
     * @param codigoCurso  o {@code Curso} ao qual este {@code Historico} está associado
     * @param paginas      a quantidade de páginas
     * @param autenticacao o número da autenticação
     * @param estudante    o estudante do {@code Historico}
     * @param matricula    o número da matrícula do estudante associado ao {@code Historico}
     * @param coeficiente  o coeficiente do {@code Historico}
     * @param componentes  os componentes do {@code Historico}
     * @see CodigoCurso
     * @see Documento
     * @see DocumentoAcademico
     * @since 1.0
     *
     */
    public Historico(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao, String estudante, long matricula,
                     double coeficiente, String[] componentes) {
        super(criador, codigoCurso, paginas, autenticacao, estudante, matricula);
        this.coeficiente = coeficiente;
        this.componentes = componentes;
    }

    /**
     * Retorna o coeficiente do {@code Historico}.
     *
     * @return o coeficiente do {@code Historico}
     * @since 1.0
     *
     */
    public double getCoeficiente() {
        return coeficiente;
    }

    /**
     * Retorna os componentes do {@code Historico}.
     *
     * @return {@code String[]} com os componentes do {@code Historico}
     * @since 1.0
     *
     */
    public String[] getComponentes() {
        return componentes;
    }

    /**
     * Compara este {@code Historico} com o objeto especificado para verificar igualdade.
     *
     * <p>A comparação considera:
     * <ul>
     *   <li>Os campos herdados de {@code Registro}, por meio de {@code super.equals(o)};</li>
     *   <li>Os campos {@code coeficiente} e {@code componentes} desta classe.</li>
     * </ul>
     *
     * @param o o objeto a ser comparado
     * @return {@code true} se ambos os Objetos forem instâncias de {@code Historico} e possuírem os mesmo valores
     * nos atributos herdados e nos campos {@code coeficiente} e {@code componentes}; {@code false} caso contrário
     * @since 1.0
     * @see #hashCode()
     * @see Registro#equals(Object)
     * @see Double#compare(double, double)
     * @see Arrays#equals(Object[], Object[])
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Historico historico = (Historico) o;
        return Double.compare(coeficiente, historico.coeficiente) == 0 && Arrays.equals(componentes, historico.componentes);
    }

    /**
     * Retorna o código hash deste {@code Historico}.
     *
     * <p>O valor retornado é consistente com {@link #equals(Object)}, de modo que dois objetos
     * iguais produzem o mesmo código hash.</p>
     *
     * <p>O valor retornado é calculado a partir do código hash da superclasse {@code super.hashCode()} combinado com
     * os campos {@code coeficiente} e {@code componentes}.</p>
     *
     * @return o código hash do objeto
     * @since 1.0
     * @see #equals(Object)
     * @see Objects#hash(Object...)
     * @see Arrays#hashCode(Object[])
     * @see Registro#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coeficiente, Arrays.hashCode(componentes));
    }
}
