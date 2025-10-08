package estudantes.entidades.utils;

import estudantes.entidades.*;

import javax.print.Doc;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

import static professor.entidades.CodigoCurso.*;

/**
 * Classe abstrata que agrupa todos os métodos destinadas a verificar se um documento pode ser adicionado em um processo
 * sem
 * causar estresse ao Burocrata.
 *
 * @author Iuri da Silva Fenrnandes
 * @version 1.0
 * @see estudantes.entidades.Burocrata
 *
 */
public abstract class ValidadorDocumentos {

    /**
     * Verifica se é válido adicionar um {@code Documento} do tipo {@code Ata} com base nas restrições impostas.
     *
     * <p>Utiliza a API de Streams juntamente com {@code anyMatch}, verificando se existe algum {@code
     * DocumentoAdministrativo} ou {@code DocumentoAcademico} na lista de Documentos.</p>
     * <p>Se um {@code Processo} for despachado somente com {@code Atas}, o estresse do {@code Burocrata} aumentará.</p>
     *
     * @param documentos a lista de documentos existentes no {@code Processo}
     * @param documento  o documento a ser avaliado
     * @return {@code true} se o {@code Documento} não for uma {@code Ata}, ou se houver {@code Documentos} que não
     * sejam {@code Ata} na lista; {@code false} se o {@code Documento} for uma {@code Ata} e todos os documentos na
     * lista também forem {@code Ata}
     * @see Ata
     * @see DocumentoAcademico
     * @see DocumentoAdministrativo
     * @see Burocrata
     * @since 1.0
     *
     *
     */
    protected static boolean verificarSeAdicionaAtaVerificandoPorElementoAnterior(Documento[] documentos, Documento documento) {
        if (!(documento instanceof Ata)) {
            return true;
        }
        return Arrays.stream(documentos).anyMatch(d -> (d instanceof DocumentoAdministrativo || d instanceof DocumentoAcademico));
    }

    /**
     * Verifica se é válido adicionar um {@code Documento} com base nos níveis de educação dos {@code Documentos}
     * presentes na lista.
     *
     * <p>Utiliza a API de Streams juntamente com {@code allMatch}, verificando se todos os {@code Documentos} da
     * lista são do mesmo nível de educação do {@code Documento} passado.
     * </p>
     * <p>Existem dois níveis de educação: "<strong>Graduação</strong>" e "<strong>Pós-Graduação</strong>"</p>
     * <p>Caso {@code Documentos} de níveis de educação diferentes sejam despachados no mesmo {@code Processo}, o
     * estresse do {@code Burocrata} aumentará.</p>
     *
     * @param documentos a lista de documentos existentes no processo
     * @param documento  o documento a ser avaliado
     * @return {@code true} se o {@code Documento} possui o mesmo nível de educação dos {@code
     * Documentos} da lista; {@code false} se o {@code Documento} é de um
     * nível de educação diferente dos {@code Documentos} da lista
     * @see Ata
     * @see DocumentoAcademico
     * @see DocumentoAdministrativo
     * @see Burocrata
     * @see ValidadorDocumentos#verificarSeODocumentoEDePosGraduacao(Documento)
     * @since 1.0
     *
     *
     */
    protected static boolean verificarSeAdicionaDocumentoPorNivelDeEducacao(Documento[] documentos, Documento documento) {
        if (verificarSeODocumentoEDePosGraduacao(documento)) { // é pós-graduação
            return Arrays.stream(documentos).allMatch(ValidadorDocumentos::verificarSeODocumentoEDePosGraduacao);
        } else { // é de graduação
            return Arrays.stream(documentos).noneMatch(ValidadorDocumentos::verificarSeODocumentoEDePosGraduacao);
        }
    }

    /**
     * Verifica se é válido adicionar um {@code Documento} com base no tipo dos {@code Documentos} presentes na lista.
     *
     * <p>Utiliza a API de Streams juntamente com {@code allMatch}, verificando se todos os {@code Documentos} da
     * lista são do mesmo tipo do {@code Documento} passado utilizando {@code instanceof}.
     * </p>
     * <p>Existem tipos de {@code Documentos}: "<strong>{@code DocumentoAdministrativo}</strong>" e
     * "<strong>{@code DocumentoAcademico}</strong>"</p>
     * <p>Caso {@code Documentos} de tipos diferentes sejam despachados no mesmo {@code Processo}, o
     * estresse do {@code Burocrata} aumentará.</p>
     * <p>{@code Atas} podem ser adicionados com qualquer tipo de {@code Documento}.</p>
     *
     * @param documentos a lista de documentos existentes no processo
     * @param documento  o documento a ser avaliado
     * @return {@code true} se {@code Documento} for {@code Ata} ou se possui o mesmo tipo dos {@code Documentos} da
     * lista; {@code false} se o {@code Documento} é de um tipo diferente dos {@code Documentos} da lista
     * @see Ata
     * @see DocumentoAcademico
     * @see DocumentoAdministrativo
     * @see Burocrata
     * @since 1.0
     *
     *
     */
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

    /**
     * Verifica se é válido adicionar um {@code Documento} com base na soma do total de páginas dos {@code
     * Documentos} presentes na lista.
     *
     * <p> Utiliza a API de Streams, mapeando cada {@code Documento} da lista com {@code mapToInt(Documentos::getPaginas)} e obtendo o
     * total com {@code sum()}, armazenando em {@code totalPaginas}, somando-o com {@code Documento.getPaginas()} e
     * comparando com o valor máximo.</p>
     *
     * <p>Caso a soma do total de páginas do {@code Processo} ultrapasse 250, todos os {@code Documentos} do {@code
     * Processo} serão perdidos e o estresse do {@code Burocrata} aumentará.</p>
     *
     * @param documentos a lista de documentos existentes no processo
     * @param documento  o documento a ser avaliado
     * @return {@code true} se a soma entre o total de páginas do {@code Processo} e o total de páginas do {@code
     * Documento} for menor que <strong>250</strong>; {@code false} caso contrário
     * @see Documento
     * @see Burocrata
     * @since 1.0
     *
     *
     */
    protected static boolean verificarSeAdicionaDocumentoPorQuantidadeDePaginas(Documento[] documentos, Documento documento) {
        int totalPaginas = Arrays.stream(documentos).mapToInt(Documento::getPaginas).sum();
        return ((documento.getPaginas() + totalPaginas) <= 250);
    }

    /**
     * Verifica já existe um {@code Documento} Substancial na lista de {@code Documentos}.
     *
     * <p>Utiliza a API de Streams, juntamente com {@code anyMatch()} para verificar se já existe pelo menos um
     * {@code Documento} Substancial presente na lista.
     * </p>
     *
     * <p>Considere um {@code Documento} Substancial como um {@code Edital} ou {@code Portaria} <strong>válido</strong>
     * e com mais de 99 páginas.</p>
     *
     * <p>Um {@code Documento} Substancial deve ser despachado sozinho no {@code Processo}, caso contrário, o estresse
     * do {@code Burocrata} aumentará.</p>
     *
     * @param documentos a lista de documentos existentes no processo
     * @return {@code true} se já existe um {@code Documento} Substancial presente na lista; {@code false} caso
     * contrário
     * @see Oficio
     * @see Circular
     * @see Norma#getValido()
     * @see Documento#getPaginas()
     * @see Burocrata
     * @since 1.0
     *
     */
    protected static boolean verificarSeJaExisteDocumentoSubstancial(Documento[] documentos) {
        return Arrays.stream(documentos).anyMatch(doc ->
                (doc instanceof Edital || doc instanceof Portaria) && doc.getPaginas() >= 100
                        && ((Norma) doc).getValido()
        );
    }

    /**
     * Verifica se é válido adicionar um {@code Documento} Substancial na lista.
     *
     * <p>Verifica se o {@code Documento} é um {@code Edital} ou {@code Portaria} <strong>válido</strong> com
     * mais de 99 páginas e se a lista de {@code Documentos} está vazia.</p>
     * <p>Considere um {@code Documento} Substancial como um {@code Edital} ou {@code Portaria} <strong>válido</strong>
     * e com mais de 99 páginas.</p>
     *
     * <p>Um {@code Documento} Substancial deve ser despachado sozinho no {@code Processo}, caso contrário, o estresse
     * do {@code Burocrata} aumentará.</p>
     *
     * @param documentos a lista de documentos existentes no processo
     * @param documento  o documento a ser avaliado
     * @return {@code true} se o {@code Documento} não é {@code Portaria} ou {@code Edital}, se não é um {@code
     * Documento} Substancial ou se é {@code Documento} Substancial e a lista de {@code Documentos} está vazia;
     * {@code false} se é um {@code Documento} Substancial e a lista não está vazia
     * @since 1.0
     *
     * @see Edital
     * @see Portaria
     * @see Burocrata
     *
     */
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
        return verificarSeAListaDeDocumentosEstaVazia(documentos);
    }


    /**
     * Verifica se é válido adicionar um {@code Circular} ou {@code Ofício} na lista.
     *
     * <p>Verifica se o {@code Documento} é {@code Circular} ou {@code Ofício}, caso sejam, verifica se a lista
     * contém algum {@code Circular} ou {@code Ofício}, caso contenham, verifica se os destinatários do {@code
     * Documento} passado possui alguma correspondência com os já existentes.
     *
     * <p>{@code Circulares} e {@code Ofícios} só podem ser despachados no mesmo {@code Processo} caso contenham
     * pelo menos um destinatário em comum entre si, caso contrário, o estresse do {@code Burocrata} aumentará.</p>
     *
     * @param documentos a lista de documentos existentes no processo
     * @param documento  o documento a ser avaliado
     * @return {@code true} se o {@code Documento} não seja {@code Circular} ou {@code Ofício} e se o {@code
     * Documento} possui algum destinatário em comum com os já existentes na lista; {@code false} se o {@code
     * Documento} não possui nenhum destinatário em comum com os já existentes na lista
     * @since 1.0
     * @see Oficio
     * @see Circular
     * @see Burocrata
     *
     */
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

    /**
     * Verifica se é válido adicionar um {@code Diploma} na lista e se é valido adicionar um {@code Documento} caso
     * haja um diploma na lista.
     *
     * <p>Verifica se o {@code Documento} passado é um {@code Diploma}, caso não seja, verifica se o {@code Documento}
     * é uma {@code Ata} ou {@code Certificado}, caso não seja, verifica se nenhum {@code Documento} da lista é Diploma.
     * <br></br>
     * Caso o {@code Documento} seja um diploma, verifica se todos os {@code Documentos} da listas são {@code Atas}
     * ou {@code Certificados}.
     * </p>
     *
     * <p>{@code Diplomas} só podem ser despachados com {@code Atas}, {@code Certificados} e outros {@code Diplomas},
     * caso contrário, o estresse do {@code Burocrata} aumentará.</p>
     *
     * @param documentos a lista de documentos existentes no processo
     * @param documento  o documento a ser avaliado
     * @return {@code true} se o {@code Documento} é {@code Ata} ou {@code Certificado}, se o {@code Documento} não
     * possui nenhum {@code Diploma}, se o {@code Documento} é {@code Diploma} e a lista é composta somente por
     * {@code Ata}, {@code Certificados} ou {@code Diplomas}; {@code false} se o {@code Documento} não é {@code Ata},
     * {@code Certificado} e {@code Diploma} e a lista contém pelo menos um {@code Diploma} e se {@code Documento} é um
     * {@code Diploma} e a lista contém {@code Documentos} que não são {@code Ata}, {@code Certificado} ou {@code
     * Diploma}
     * @since 1.0
     *
     * @see Ata
     * @see Diploma
     * @see Certificado
     * @see Burocrata
     *
     */
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


    /**
     * Verifica se é válido adicionar um {@code Atestado} na lista.
     *
     * <p>Verifica se o {@code Documento} é um {@code Atestado}, após, verifica se não existe nenhum outro
     * {@code Atestado} na lista utilizando a API de Streams e {@code noneMatch()}, caso haja um {@code Atestado},
     * cria uma {@code List<String>} para a categoria dos {@code Atestados} presentes na lista, após, verifica se a
     * categoria do {@code Atestado} passado corresponde à categoria presente na {@code List<String>} de categorias.
     * </p>
     *
     * <p>Um processo não pode ser despachado com {@code Atestados} de Categorias diferentes, caso contrário, o
     * estresse do {@code Burocrata} aumentará.</p>
     *
     * @param documentos a lista de documentos existentes no processo
     * @param documento  o documento a ser avaliado
     * @return {@code true} se o {@code Documento} não é um {@code Atestado}, se não existe nenhum {@code Atestado}
     * presente na lista ou se a categoria do {@code Atestado} passado é o mesmo dos {@code Atestados} presentes na
     * lista; {@code false} se a categoria do {@code Atestado} passado é diferente da categoria dos {@code Atestados}
     * presentes na lista
     * @since 1.0
     * @see Atestado
     * @see Burocrata
     * @see List#contains(Object)
     * @see java.util.stream.Stream#noneMatch(Predicate)
     *
     *
     */
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

        // Se a categoria do atestado no processo for diferente do atestado a ser adicionado, automaticamente não
        // adiciona
        // A categoria do atestado no processo é definido pelo primeiro atestado adicionado no processo
        return categoriasDosAtestadosNoProcesso.contains(categoriaDoAtestado);
    }

    /**
     * Verifica se a lista de {@code Documentos} está vazia.
     *
     * <p>Utiliza {@code documentos.length} e verifica se ele é igual a 0.</p>
     *
     * @param documentos a lista de documentos existentes no processo
     * @return {@code true} se {@code documentos.length == 0}; {@code false} caso contrário
     * @since 1.0
     *
     *
     */
    protected static boolean verificarSeAListaDeDocumentosEstaVazia(Documento[] documentos) {
        return documentos.length == 0;
    }


    /**
     * Verifica se o {@code Documento} é de Pós-Graduação.
     *
     *
     * <p>Utiliza {@code document.getCodigoCurso()} para comparar se o Código corresponde a um dos cursos de
     * "Pós-Graduação" existentes.</p>
     *
     * <p>Existem 3 cursos de "Pós-Graduação": {@code POS_GRADUACAO_ENGENHARIA}, {@code
     * POS_GRADUACAO_ENGENHARIA_ELETRICA} e {@code POS_GRADUACAO_ENGENHARIA_SOFTWARE}.</p>
     *
     * @param documento o documento a ser avaliado
     * @return {@code true} se o {@code Documento.getCodigoCurso} corresponde à {@code POS_GRADUACAO_ENGENHARIA} ou
     * {@code POS_GRADUACAO_ENGENHARIA_ELETRICA} ou {@code POS_GRADUACAO_ENGENHARIA_SOFTWARE}; {@code false} caso
     * contrário
     * @since 1.0-
     * @see professor.entidades.CodigoCurso
     * @see Documento#getCodigoCurso()
     * @see ValidadorDocumentos#verificarSeAdicionaDocumentoPorNivelDeEducacao(Documento[], Documento)
     *
     */
    protected static boolean verificarSeODocumentoEDePosGraduacao(Documento documento) {
        return documento.getCodigoCurso() == POS_GRADUACAO_ENGENHARIA || documento.getCodigoCurso() == POS_GRADUACAO_ENGENHARIA_ELETRICA
                || documento.getCodigoCurso() == POS_GRADUACAO_ENGENHARIA_SOFTWARE;
    }
}
