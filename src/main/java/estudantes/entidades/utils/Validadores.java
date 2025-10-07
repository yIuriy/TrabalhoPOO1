package estudantes.entidades.utils;

import estudantes.entidades.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static professor.entidades.CodigoCurso.*;

public abstract class Validadores {
    protected static boolean verificarSeAdicionaAtaVerificandoPorElementoAnterior(Documento[] documentos, Documento documento) {
        if (!(documento instanceof Ata)) {
            return true;
        }
        return Arrays.stream(documentos).anyMatch(d -> (d instanceof DocumentoAdministrativo || d instanceof DocumentoAcademico));
    }

    // Funcionando corretamente
    protected static boolean verificarSeAdicionaDocumentoPorNivelDeEducacao(Documento[] documentos, Documento documento) {
        if (isDocumentoDePosGraduacao(documento)) { // é pós-graduação
            return Arrays.stream(documentos).allMatch(Validadores::isDocumentoDePosGraduacao);
        } else { // é de graduação
            return Arrays.stream(documentos).noneMatch(Validadores::isDocumentoDePosGraduacao);
        }
    }

    // Funcionando corretamente
    protected static boolean verificarSeAdicionarDocumentoPorTipo(Documento[] documentos, Documento documento) {
        if (documento instanceof Ata) {
            return true;
        }
        if (documento instanceof DocumentoAdministrativo) {
            return Arrays.stream(documentos).allMatch(doc -> doc instanceof DocumentoAdministrativo);
        } else if (documento instanceof DocumentoAcademico) {
            return Arrays.stream(documentos).allMatch(doc -> doc instanceof DocumentoAcademico);
        }
        return false;
    }

    // Funcionando corretamente
    protected static boolean verificarSeAdicionaDocumentoPorQuantidadeDePaginas(Documento[] documentos, Documento documento) {
        int totalPaginas = Arrays.stream(documentos).mapToInt(Documento::getPaginas).sum();
        return ((documento.getPaginas() + totalPaginas) <= 250);
    }

    // Funcionando corretamente
    protected static boolean verificarSeJaExisteDocumentoSubstancial(Documento[] documentos) {
        return Arrays.stream(documentos).anyMatch(doc ->
                (doc instanceof Edital || doc instanceof Portaria) && doc.getPaginas() >= 100
                        && ((Norma) doc).getValido()
        );
    }

    // Funcionando corretamente
    protected static boolean verificarSeAdicionaDocumentoSubstancial(Documento[] documentos, Documento documento) {
        if (!(documento instanceof Portaria || documento instanceof Edital)) {
            return true;
        }

        // Se for Portaria ou Edital com menos de 100 página, não é substancial
        if (documento.getPaginas() < 100) {
            return true;
        }

        // Se for inválido, também pode adicionar
        if (!((Norma) documento).getValido()) {
            return true;
        }
        return isListaDeDocumentosVazia(documentos);
    }

    protected static boolean verificarSeAdicionaDocumentoCasoCircularesEOficio(Documento[] documentos, Documento documento) {
        if (!(documento instanceof Circular || documento instanceof Oficio)) {
            return true;
        }
        // Se chegou aqui, ele é Circular ou Oficio
        boolean processoJaContemCircularOuOficio = Arrays.stream(documentos)
                .anyMatch(doc -> doc instanceof Oficio || doc instanceof Circular);
        if (!processoJaContemCircularOuOficio) {
            return true;
        }
        // Se chegou aqui, significa que o processo já contém um circular ou um ofício
        // Abaixo, pegamos todos os documentos do tipo Circular ou Oficio presentes no processo
        Object[] oficiosOuCircularesJaPresentesNoProcesso = Arrays.stream(documentos)
                .filter(doc -> doc instanceof Circular || doc instanceof Oficio
                ).toArray();

        AtomicBoolean contemMesmoDestinatario = new AtomicBoolean(true);
        Arrays.stream(oficiosOuCircularesJaPresentesNoProcesso).forEach(
                doc -> {
                    if (doc instanceof Circular) {
                        if (documento instanceof Circular) {
                            for (String destinatario : ((Circular) documento).getDestinatarios()) {
                                if (Arrays.stream(((Circular) doc).getDestinatarios()).noneMatch(
                                        s -> s.equals(destinatario)
                                )) {
                                    contemMesmoDestinatario.set(false);
                                }
                            }
                        } else {
                            if (Arrays.stream(((Circular) doc).getDestinatarios()).noneMatch(
                                    s -> s.equals(((Oficio) documento).getDestinatario())
                            )) {
                                contemMesmoDestinatario.set(false);
                            }
                        }
                    } else if (doc instanceof Oficio) {
                        if (documento instanceof Circular) {
                            if (Arrays.stream(((Circular) documento).getDestinatarios()).noneMatch(
                                    s -> s.equals(((Oficio) doc).getDestinatario())
                            )) {
                                contemMesmoDestinatario.set(false);
                            }
                        } else {
                            if (!((Oficio) documento).getDestinatario().equals(((Oficio) doc).getDestinatario())) {
                                contemMesmoDestinatario.set(false);
                            }
                        }
                    }
                });
        return contemMesmoDestinatario.get();
    }

    protected static boolean verificarSeAdicionaDocumentoCasoDiploma(Documento[] documentos, Documento documento) {
        if (!(documento instanceof Diploma)) {
            // Ata e Certificados podem ficar com Diploma
            if (documento instanceof Ata || documento instanceof Certificado) {
                return true;
            }
            // Se conter um diploma, automaticamente é inválido adicionar
            return Arrays.stream(documentos).noneMatch(
                    doc -> doc instanceof Diploma);
        } else {
            // Ao chegar aqui automaticamente é Diploma
            // Se conter apenas Diploma, Certificado ou Ata, pode ser adicionado
            return Arrays.stream(documentos).allMatch(
                    doc -> doc instanceof Certificado || doc instanceof Ata
            );
        }
    }

    protected static boolean verificarSeAdicionaDocumentoCasoAtestado(Documento[] documentos, Documento documento) {
        if (!(documento instanceof Atestado)) {
            return true;
        }
        // Ao chegar aqui, automaticamente é Atestado
        boolean processoNaoContemNenhumAtestado = Arrays.stream(documentos).noneMatch(
                doc -> doc instanceof Atestado
        );
        // Se o processo não contém nenhum Atestado, não há como ter conflito de categoria
        if (processoNaoContemNenhumAtestado) {
            return true;
        }
        // Chegando aqui, automaticamente o processo contém um ou mais Atestado
        List<String> categoriasDosAtestadosNoProcesso = Arrays.stream(documentos).filter(
                doc -> doc instanceof Atestado
        ).map(doc -> ((Atestado) doc).getCategoria()).toList();

        String categoriaDoAtestado = ((Atestado) documento).getCategoria();

        // Se a categoria do atestado no processo for diferente do diploma a ser adicionado, automaticamente não
        // adiciona
        // A categoria do atestado no processo é definido pelo primeiro atestado adicionado no processo
        return categoriasDosAtestadosNoProcesso.contains(categoriaDoAtestado);
    }

    protected static boolean isListaDeDocumentosVazia(Documento[] documentos) {
        return documentos.length == 0;
    }

    protected static boolean isTodosDocumentosAtas(Documento[] documentos) {
        if (documentos.length == 0) return false;
        return Arrays.stream(documentos).noneMatch(
                doc -> (doc instanceof DocumentoAdministrativo || doc instanceof DocumentoAcademico)
        );
    }

    protected static boolean isDocumentoDePosGraduacao(Documento doc) {
        return doc.getCodigoCurso() == POS_GRADUACAO_ENGENHARIA || doc.getCodigoCurso() == POS_GRADUACAO_ENGENHARIA_ELETRICA
                || doc.getCodigoCurso() == POS_GRADUACAO_ENGENHARIA_SOFTWARE;
    }
}
