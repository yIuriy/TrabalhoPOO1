package estudantes.entidades.utils;

import estudantes.entidades.Documento;
import professor.entidades.CodigoCurso;
import professor.entidades.Universidade;

import java.util.ArrayList;
import java.util.List;

import static professor.entidades.CodigoCurso.*;

/**
 * Classe abstrata que realiza o acesso aos documentos presentes nos montes dos cursos.
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 * @see Universidade
 * @see CodigoCurso
 *
 */
public abstract class ColetorDocumentos {
    private static final List<CodigoCurso> CODIGOS_CURSOS = List.of(GRADUACAO_CIENCIA_DA_COMPUTACAO,
            GRADUACAO_ENGENHARIA_AGRICOLA,
            GRADUACAO_ENGENHARIA_CIVIL, GRADUACAO_ENGENHARIA_ELETRICA, GRADUACAO_ENGENHARIA_MECANICA,
            GRADUACAO_ENGENHARIA_SOFTWARE, GRADUACAO_ENGENHARIA_TELECOMUNICACOES, POS_GRADUACAO_ENGENHARIA,
            POS_GRADUACAO_ENGENHARIA_ELETRICA, POS_GRADUACAO_ENGENHARIA_SOFTWARE);

    /**
     * Realiza o acesso à lista de documentos de todos os cursos e retorna uma lista com todos eles.
     * <p>Utiliza {@code for()} para percorrer todos os {@code CODIGOS_CURSOS}, utilizando {@code List.of()} para
     * retirar
     * e converter todos os documentos presentes no monte dos curso e adicionar à lista principal com {@code addAll()}.
     * </p>
     *
     * @param universidade a universidade que possui os cursos
     * @return uma {@code ArrayList<Documento>} com todos os documentos de todos os cursos
     * @see Universidade
     * @see professor.entidades.Curso
     * @see Documento
     * @since 1.0
     *
     */
    protected static ArrayList<Documento> getTodosDocumentosDosCursos(Universidade universidade) {
        ArrayList<Documento> todosDocumentos = new ArrayList<>();
        for (CodigoCurso codigoCurso : CODIGOS_CURSOS) {
            todosDocumentos.addAll(List.of(universidade.pegarCopiaDoMonteDoCurso(codigoCurso)));
        }
        return todosDocumentos;
    }
}
