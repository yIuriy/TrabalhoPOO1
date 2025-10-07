package estudantes.entidades.utils;

import estudantes.entidades.Ata;
import estudantes.entidades.Documento;
import professor.entidades.Processo;
import professor.entidades.Universidade;

import java.util.ArrayList;
import java.util.Collections;

import static estudantes.entidades.utils.ColetorDocumentos.getTodosDocumentosDosCursos;
import static estudantes.entidades.utils.Validadores.*;

public record ManipuladorDeProcesso(Universidade universidade) {

    public void gerenciarProcesso(Processo processo) {
        ArrayList<Documento> finalTodosDocumentos = getTodosDocumentosDosCursos(universidade);
        Collections.shuffle(finalTodosDocumentos);
        for (Documento doc : finalTodosDocumentos) {
            verificarSeAdicionaDocumentoNoProcesso(processo, doc);
        }
    }

    public void despacharProcesso(Processo processo, int numeroProcesso) {
        Documento[] documentos = processo.pegarCopiaDoProcesso();
//        printTodosTiposDeDocumentosPresentesNaListaDeDocumentos(documentos);
        if (!isListaDeDocumentosVazia(documentos)) {
            if (!isTodosDocumentosAtas(documentos)) {
                universidade.despachar(processo);
//                System.out.println("Processo " + numeroProcesso + " despachado.");
            } else {
//                System.out.println("Processo " + numeroProcesso + "não despachado.");
                // Remove o último elemento, garantindo que não vai ficar travado só com Ata
                Documento documento = documentos[documentos.length - 1];
                processo.removerDocumento(documento);
                universidade.devolverDocumentoParaMonteDoCurso(documento, documento.getCodigoCurso());
            }
        }
    }

    private void verificarSeAdicionaDocumentoNoProcesso(Processo processo, Documento documento) {
        Documento[] documentos = processo.pegarCopiaDoProcesso();
        if (verificarSeJaExisteDocumentoSubstancial(documentos)) return;
        boolean isListaDeDocumentosVazia = isListaDeDocumentosVazia(documentos);
        if (!isListaDeDocumentosVazia || documento instanceof Ata) {
            if (!verificarSeAdicionaDocumentoPorNivelDeEducacao(documentos, documento)) return;
            if (!verificarSeAdicionarDocumentoPorTipo(documentos, documento)) return;
            if (!verificarSeAdicionaDocumentoPorQuantidadeDePaginas(documentos, documento)) return;
            if (!verificarSeAdicionaDocumentoSubstancial(documentos, documento)) return;
            if (!verificarSeAdicionaDocumentoCasoCircularesEOficio(documentos, documento)) return;
            if (!verificarSeAdicionaDocumentoCasoDiploma(documentos, documento)) return;
            if (!verificarSeAdicionaDocumentoCasoAtestado(documentos, documento)) return;
            if (!verificarSeAdicionaAtaVerificandoPorElementoAnterior(documentos, documento)) return;
        }
        processo.adicionarDocumento(documento);
        universidade.removerDocumentoDoMonteDoCurso(documento, documento.getCodigoCurso());
    }
}
