package estudantes.entidades.utils;

import estudantes.entidades.Ata;
import estudantes.entidades.Documento;
import professor.entidades.CodigoCurso;
import professor.entidades.Processo;
import professor.entidades.Universidade;

import java.util.ArrayList;
import java.util.Collections;

import static estudantes.entidades.utils.ColetorDocumentos.getTodosDocumentosDosCursos;
import static estudantes.entidades.utils.ValidadorDocumentos.*;
import static estudantes.entidades.utils.ValidadorProcesso.isTodosDocumentosAtas;

/**
 * Record que gerencia a lógica da manipulação dos {@code Processos}, adicionando {@code Documentos} no {@code
 * Processo} caso seja permitido e realizando o despache do {@code Processo}.
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 */
public record ManipuladorDeProcesso(Universidade universidade) {

    /**
     * Gerencia o processo, percorrendo a lista de todos os {@code Documentos} dos {@code Cursos} e verificando se
     * adiciona caso as condições sejam satisfeitas.
     *
     * <p>Utiliza {@code getTodosDocumentosDosCursos()}, embaralha a lista utilizando {@code Collections.shuffle()},
     * percorre a lista com {@code for()}, utilizando {@code verificarSeAdicionaDocumentoNoProcesso()} para cada
     * {@code Documento}.
     * </p>
     *
     * @param processo o processo a ser gerenciado
     * @see Processo
     * @see ColetorDocumentos#getTodosDocumentosDosCursos(Universidade)
     * @see ManipuladorDeProcesso#verificarSeAdicionaDocumentoNoProcesso(Processo, Documento)
     * @since 1.0
     */
    public void gerenciarProcesso(Processo processo) {
        ArrayList<Documento> finalTodosDocumentos = getTodosDocumentosDosCursos(universidade);
        Collections.shuffle(finalTodosDocumentos);
        for (Documento doc : finalTodosDocumentos) {
            verificarSeAdicionaDocumentoNoProcesso(processo, doc);
        }
    }

    /**
     * Despacha o {@code Processo} caso ele seja válido.
     *
     * <p>O {@code Processo} só será despachado caso a lista de {@code Documentos} dele não seja vazia e nem seja
     * formada somente por {@code Atas}.</p>
     *
     * @param processo       o processo a ser despachado
     * @param numeroProcesso o número do processo para exibição
     * @see Universidade#despachar(Processo)
     * @see Universidade#removerDocumentoDoMonteDoCurso(Documento, CodigoCurso)
     * @see Processo
     * @see ValidadorProcesso
     * @since 1.0
     */
    public void despacharProcesso(Processo processo, int numeroProcesso) {
        Documento[] documentos = processo.pegarCopiaDoProcesso();
//        printTodosTiposDeDocumentosPresentesNaListaDeDocumentos(documentos);
        if (!verificarSeAListaDeDocumentosEstaVazia(documentos)) {
            if (!isTodosDocumentosAtas(documentos)) {
                universidade.despachar(processo);
//                System.out.println("Processo " + numeroProcesso + " despachado.");
            } else {
//                System.out.println("Processo " + numeroProcesso + "não despachado.");
                // Remove o último elemento, garantindo que não vai ficar travado só com Ata
                Documento documento = documentos[documentos.length - 1];
                System.out.println("Processo não despachado");
                processo.removerDocumento(documento);
                universidade.devolverDocumentoParaMonteDoCurso(documento, documento.getCodigoCurso());
            }
        }
    }

    /**
     * Realiza uma série de verificações para avaliar se um {@code Documento} pode ser adicionado à lista do {@code
     * Processo}.
     * <p>Todas as verificações realizados são destinadas a impedir que o {@code Burocrata} se estresse.</p>
     * <p>Se já existe um {@code Documento} substancial no {@code Processo}, nenhum outro {@code Documento} será
     * adicionado.</p>
     * <p>{@code Atas} nunca são o primeiro {@code Documento} a ser adicionado na lista do {@code Processo}.</p>
     * <p>Caso a lista do {@code Processo} esteja vazia, o {@code Documento é adicionado sem passar pelas verificações.}
     * </p>
     * <p>Caso o {@code Documento} falhe em alguma das verificações, ele não será adicionado.</p>
     * <p>Caso o {@code Documento} passe em todas as verificações, ele será adicionado à lista do {@code Processo} e removido do monte do {@code Curso}.</p>
     *
     * @param processo  o processo a ser despachado
     * @param documento o documento a ser avaliado
     * @see Universidade
     * @see Processo
     * @see professor.entidades.Curso
     * @see ValidadorDocumentos
     * @see ValidadorProcesso
     * @since 1.0
     */
    private void verificarSeAdicionaDocumentoNoProcesso(Processo processo, Documento documento) {
        Documento[] documentos = processo.pegarCopiaDoProcesso();
        if (verificarSeJaExisteDocumentoSubstancial(documentos)) return;
        if (!verificarSeAListaDeDocumentosEstaVazia(documentos) || documento instanceof Ata) {
            if (!verificarSeAdicionaAtaVerificandoPorElementoAnterior(documentos, documento)) return;
            if (!verificarSeAdicionaDocumentoPorNivelDeEducacao(documentos, documento)) return;
            if (!verificarSeAdicionarDocumentoPorTipo(documentos, documento)) return;
            if (!verificarSeAdicionaDocumentoPorQuantidadeDePaginas(documentos, documento)) return;
            if (!verificarSeAdicionaDocumentoSubstancial(documentos, documento)) return;
            if (!verificarSeAdicionaDocumentoCasoCircularesEOficio(documentos, documento)) return;
            if (!verificarSeAdicionaDocumentoCasoDiploma(documentos, documento)) return;
            if (!verificarSeAdicionaDocumentoCasoAtestado(documentos, documento)) return;
        }
        processo.adicionarDocumento(documento);
        universidade.removerDocumentoDoMonteDoCurso(documento, documento.getCodigoCurso());
    }
}
