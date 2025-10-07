package estudantes.entidades.utils;

import estudantes.entidades.Documento;
import professor.entidades.CodigoCurso;
import professor.entidades.Universidade;

import java.util.ArrayList;
import java.util.List;

import static professor.entidades.CodigoCurso.*;

public abstract class ColetorDocumentos {
    private static final List<CodigoCurso> CODIGOS_CURSOS = List.of(GRADUACAO_CIENCIA_DA_COMPUTACAO,
            GRADUACAO_ENGENHARIA_AGRICOLA,
            GRADUACAO_ENGENHARIA_CIVIL, GRADUACAO_ENGENHARIA_ELETRICA, GRADUACAO_ENGENHARIA_MECANICA,
            GRADUACAO_ENGENHARIA_SOFTWARE, GRADUACAO_ENGENHARIA_TELECOMUNICACOES, POS_GRADUACAO_ENGENHARIA,
            POS_GRADUACAO_ENGENHARIA_ELETRICA, POS_GRADUACAO_ENGENHARIA_SOFTWARE);

    protected static ArrayList<Documento> getTodosDocumentosDosCursos(Universidade universidade) {
        ArrayList<Documento> todosDocumentos = new ArrayList<>();
        for (CodigoCurso codigoCurso : CODIGOS_CURSOS) {
            todosDocumentos.addAll(List.of(universidade.pegarCopiaDoMonteDoCurso(codigoCurso)));
        }
        return todosDocumentos;
    }
}
