package estudantes.entidades.utils;

import estudantes.entidades.Documento;
import estudantes.entidades.DocumentoAcademico;
import estudantes.entidades.DocumentoAdministrativo;

import java.util.Arrays;

/**
 * Classe abstrata que agrupa todos os métodos destinadas a verificar se um processo pode ser despachado
 * sem causar estresse ao Burocrata.
 *
 * @author Iuri da Silva Fenrnandes
 * @version 1.0
 * @see estudantes.entidades.Burocrata
 */
public abstract class ValidadorProcesso {

    /**
     * Verifica se todos os elementos da lista são {@code Atas}.
     *
     * <p></p>
     *
     * <p>Um {@code Processo} não pode ser despachado somente com {@code Atas}, caso contrário, o estresse do {@code
     * Burocrata} aumentará.</p>
     *
     * @param documentos a lista de documentos existentes no processo
     * @return {@code true} caso todos os {@code Documentos} sejam {@code Ata}; {@code false} caso contrário
     * @see estudantes.entidades.Ata
     * @see estudantes.entidades.Burocrata
     * @since 1.0
     */
    protected static boolean isTodosDocumentosAtas(Documento[] documentos) {
        if (documentos.length == 0) return false;
        return Arrays.stream(documentos).noneMatch(
                doc -> (doc instanceof DocumentoAdministrativo || doc instanceof DocumentoAcademico)
        );
    }
}
