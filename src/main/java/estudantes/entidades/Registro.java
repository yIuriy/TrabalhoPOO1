package estudantes.entidades;

import professor.entidades.CodigoCurso;

import java.util.Arrays;
import java.util.Objects;

/**
 * Representa um {@code Registro}, que é uma especialização de {@code DocumentoAcademico}.
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 * @see Documento
 */
public class Registro extends DocumentoAcademico {
    private String estudante;
    private long matricula;

    /**
     * Cria uma instância de {@code Registro}.
     * <p>Utiliza o construtor da superclasse {@link DocumentoAcademico} através de {@code super()} para inicializar os atributos
     * herdados.</p>
     *
     * @param criador      o criador do {@code Registro}
     * @param codigoCurso  o {@code Curso} ao qual este {@code Registro} está associado
     * @param paginas      a quantidade de páginas
     * @param autenticacao o número da autorização
     * @param estudante    o estudante do {@code Registro}
     * @param matricula    o número da matrícula do {@code Plano}
     * @see CodigoCurso
     * @see Documento
     * @see DocumentoAcademico
     * @since 1.0
     *
     */
    public Registro(String criador, CodigoCurso codigoCurso, int paginas, long autenticacao,
                    String estudante, long matricula) {
        super(criador, codigoCurso, paginas, autenticacao);
        this.estudante = estudante;
        this.matricula = matricula;
    }

    /**
     * Retorna o estudante associado ao {@code Registro}.
     *
     * @return o estudante associado ao {@code Registro}
     * @since 1.0
     *
     */
    public String getEstudante() {
        return estudante;
    }


    /**
     * Retorna a matrícula do {@code Registro}.
     *
     * @return a matrícula do {@code Registro}
     * @since 1.0
     *
     */
    public long getMatricula() {
        return matricula;
    }

    /**
     * Compara este {@code Registro} com o objeto especificado para verificar igualdade.
     *
     * <p>A comparação considera:
     * <ul>
     *   <li>Os campos herdados de {@code DocumentoAcademico}, por meio de {@code super.equals(o)};</li>
     *   <li>Os campos {@code estudante} e {@code matricula} desta classe.</li>
     * </ul>
     *
     * @param o o objeto a ser comparado
     * @return {@code true} se ambos os Objetos forem instâncias de {@code Registro} e possuírem os mesmo valores
     * nos atributos herdados e nos campos {@code estudante} e {@code matricula}; {@code false} caso contrário
     * @see #hashCode()
     * @see DocumentoAcademico#equals(Object)
     * @see Objects#equals(Object, Object)
     * @since 1.0
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Registro registro = (Registro) o;
        return matricula == registro.matricula && Objects.equals(estudante, registro.estudante);
    }

    /**
     * Retorna o código hash deste {@code Registro}.
     *
     * <p>O valor retornado é consistente com {@link #equals(Object)}, de modo que dois objetos
     * iguais produzem o mesmo código hash.</p>
     *
     * <p>O valor retornado é calculado a partir do código hash da superclasse {@code super.hashCode()} combinado com
     * os campos {@code estudante} e {@code matricula}.</p>
     *
     * @return o código hash do objeto
     * @see #equals(Object)
     * @see Objects#hash(Object...)
     * @see DocumentoAcademico#hashCode()
     * @since 1.0
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), estudante, matricula);
    }
}
