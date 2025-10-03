package estudantes.entidades;

import professor.entidades.*;

import javax.print.Doc;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static professor.entidades.CodigoCurso.*;

/**
 * Classe que traz a lógica do algoritmo de organização e despacho de processos.
 * <br><br>
 * Você pode incluir novos atributos e métodos nessa classe para criar
 * lógicas mais complexas para o gerenciamento da organização e despacho de
 * processos, mas esses <strong>atributos e métodos devem ser todos
 * privados</strong> e eles não serão invocados diretamente pelo simulador.
 * <br><br>
 * Os únicos métodos públicos devem ser: getEstresse, trabalhar, estressar e
 * estressarMuito.
 *
 * @author Iuri
 */
public class Burocrata {
    private int estresse = 0;
    private Mesa mesa;
    private Universidade universidade;

    /**
     * Construtor de Burocrata.
     *
     * @param m mesa com os processos
     * @param u universidade com os montes dos cursos e a secretaria
     */
    public Burocrata(Mesa m, Universidade u) {
        this.mesa = m;
        this.universidade = u;
    }

    public int getEstresse() {
        return estresse;
    }

    public void estressar() {
        estresse++;
    }

    public void estressarMuito() {
        estresse += 10;
    }

    /**
     * Executa a lógica de criação e despacho dos processos.
     * <br><br>
     * Esse método é o único método de controle invocado durante a simulação
     * da universidade.
     * <br><br>
     * Aqui podem ser feitas todas as verificações sobre os documentos nos
     * montes dos cursos e dos processos abertos na mesa do Burocrata. A partir
     * dessas informações, você pode colocar documentos nos processos abertos
     * e despachar os processos para a secretaria acadêmica.
     * <br><br>
     * Cuidado com a complexidade do seu algoritmo, porque se ele demorar muito
     * serão criados menos documentos na sua execução e sua produtividade geral
     * vai cair.
     * <br><br>
     * Esse método será chamado a cada 100 milissegundos pelo simulador da
     * universidade.
     * <br><br>
     * <strong>O burocrata não pode manter documentos com ele</strong> depois
     * que o método trabalhar terminar de executar, ou seja, você deve devolver
     * para os montes dos cursos todos os documentos que você removeu dos montes
     * dos cursos.
     *
     * @see professor.entidades.Universidade#despachar(Processo)
     * @see professor.entidades.Universidade#removerDocumentoDoMonteDoCurso(estudantes.entidades.Documento, professor.entidades.CodigoCurso)
     * @see professor.entidades.Universidade#devolverDocumentoParaMonteDoCurso(estudantes.entidades.Documento, professor.entidades.CodigoCurso)
     */
    public void trabalhar() {
//        System.out.println("Tamanho de lista de todos os Documentos:" + todosOsDocumentosDosCursos.size());
        for (int i = 0; i < 5; i++) {
            Processo processo = mesa.getProcesso(i);
            gerenciarProcesso(processo);
            despacharProcesso(processo, i + 1);
        }
    }

    private void gerenciarProcesso(Processo processo) {
        List<Documento> finalTodosDocumentos = getTodosDocumentosDosCursos();
        Collections.shuffle(finalTodosDocumentos); // aleatoriza os documentos;
        finalTodosDocumentos.forEach(doc -> {
            verificarSeAdicionaDocumentoNoProcesso(processo, doc);
        });
    }

    private List<Documento> getTodosDocumentosDosCursos() {
        var codigosCursos = List.of(GRADUACAO_CIENCIA_DA_COMPUTACAO, GRADUACAO_ENGENHARIA_AGRICOLA,
                GRADUACAO_ENGENHARIA_CIVIL, GRADUACAO_ENGENHARIA_ELETRICA, GRADUACAO_ENGENHARIA_MECANICA,
                GRADUACAO_ENGENHARIA_SOFTWARE, GRADUACAO_ENGENHARIA_TELECOMUNICACOES, POS_GRADUACAO_ENGENHARIA,
                POS_GRADUACAO_ENGENHARIA_ELETRICA, POS_GRADUACAO_ENGENHARIA_SOFTWARE);
        List<Documento> todosDocumentos = new ArrayList<>();
        for (CodigoCurso codigoCurso : codigosCursos) {
            todosDocumentos.addAll(List.of(universidade.pegarCopiaDoMonteDoCurso(codigoCurso)));
        }
        return todosDocumentos;
    }

    private void despacharProcesso(Processo processo, int numeroProcesso) {
        Documento[] documentos = processo.pegarCopiaDoProcesso();
        printTodosTiposDeDocumentosPresentesNaListaDeDocumentos(documentos);
        if (!isListaDeDocumentosVazia(documentos)) {
            if (!isTodosDocumentosAtas(processo)) {
                universidade.despachar(processo);
//                System.out.println("Processo " + numeroProcesso + " despachado.");
            } else {
                // Remove o último elemento, garantindo que não vai ficar travado só com Ata
                Documento documento = documentos[documentos.length - 1];
                processo.removerDocumento(documento);
                universidade.devolverDocumentoParaMonteDoCurso(documento, documento.getCodigoCurso());
            }
        }
    }
    //        printResultadoDasVerificacoesParaAdicionarDocumentos(isListaDeDocumentosVazia,
//                deveAdicionarPorNivelDeEducacao, deveAdicionarPorTipo, deveAdicionarPorQuantidadeDePaginas,
//                deveAdicionarPorDocumentoSubstancial, deveAdicionarPorDestinatarioDeOficioECircular,
//                deveAdicionarPorDiploma, deveAdicionarPorCategoriaAtestado);

    private void verificarSeAdicionaDocumentoNoProcesso(Processo processo, Documento documento) {

        Documento[] documentos = processo.pegarCopiaDoProcesso();
        if (isListaDeDocumentosVazia(documentos) && !(documento instanceof Ata)) {
            processo.adicionarDocumento(documento);
            universidade.removerDocumentoDoMonteDoCurso(documento, documento.getCodigoCurso());
        } else {
            boolean deveAdicionarPorNivelDeEducacao = verificarSeAdicionaDocumentoPorNivelDeEducacao(processo, documento);
            boolean deveAdicionarPorTipo = verificarSeAdicionarDocumentoPorTipo(processo, documento);
            boolean deveAdicionarPorQuantidadeDePaginas = verificarSeAdicionaDocumentoPorQuantidadeDePaginas(processo,
                    documento);
            boolean deveAdicionarPorDocumentoSubstancial = verificarSeAdicionaDocumentoSubstancial(processo, documento);
            boolean deveAdicionarPorDestinatarioDeOficioECircular =
                    verificarSeAdicionaDocumentoCasoCircularesEOficio(processo, documento);
            boolean deveAdicionarPorDiploma = verificarSeAdicionaDocumentoCasoDiploma(processo, documento);
            boolean deveAdicionarPorCategoriaAtestado = verificarSeAdicionaDocumentoCasoAtestado(processo, documento);
            boolean deveAdicionarAtaComBaseNoElementoAnterior = verificarSeAdicionaAtaVerificandoPorElementoAnterior(processo, documento);

            if (deveAdicionarPorNivelDeEducacao
                    && deveAdicionarPorTipo
                    && deveAdicionarPorQuantidadeDePaginas
                    && deveAdicionarPorDocumentoSubstancial
                    && deveAdicionarPorDestinatarioDeOficioECircular
                    && deveAdicionarPorDiploma
                    && deveAdicionarPorCategoriaAtestado
                    && deveAdicionarAtaComBaseNoElementoAnterior
                    && !verificarSeJaExisteDocumentoSubstancial(processo)
            ) {
                processo.adicionarDocumento(documento);
                universidade.removerDocumentoDoMonteDoCurso(documento, documento.getCodigoCurso());
            }
        }
    }

    private boolean isListaDeDocumentosVazia(Documento[] documentos) {
        return documentos.length == 0;
    }

    private boolean verificarSeAdicionaAtaVerificandoPorElementoAnterior(Processo processo, Documento documento) {
        Documento[] documentos = processo.pegarCopiaDoProcesso();
        if (!(documento instanceof Ata)) {
            return true;
        }

        for (Documento doc : documentos) {
            if (doc instanceof DocumentoAdministrativo || doc instanceof DocumentoAcademico) {
                return true;
            }
        }

        return false;
    }

    // Funcionando corretamente
    private boolean verificarSeAdicionaDocumentoPorNivelDeEducacao(Processo processo, Documento documento) {
        Documento[] documentos = processo.pegarCopiaDoProcesso();
        if (isDocumentoDePosGraduacao(documento)) { // é pós graduação
            for (Documento doc : documentos) {
                if (!isDocumentoDePosGraduacao(doc)) {
                    return false;
                }
            }
        } else { // é de graduacao
            for (Documento doc : documentos) {
                if (isDocumentoDePosGraduacao(doc)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Funcionando corretamente
    private boolean verificarSeAdicionarDocumentoPorTipo(Processo processo, Documento documento) {
        Documento[] documentos = processo.pegarCopiaDoProcesso();
        if (documento instanceof Ata) {
            return true;
        }
        if (documento instanceof DocumentoAdministrativo) {
            for (Documento doc : documentos) {
                if (!(doc instanceof DocumentoAdministrativo)) {
                    return false;
                }
            }
        } else if (documento instanceof DocumentoAcademico) {
            for (Documento doc : documentos) {
                if (!(doc instanceof DocumentoAcademico)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Funcionando corretamente
    private boolean verificarSeAdicionaDocumentoPorQuantidadeDePaginas(Processo processo, Documento documento) {
        Documento[] documentos = processo.pegarCopiaDoProcesso();
        int totalPaginas = 0;
        for (Documento doc : documentos) {
            totalPaginas += doc.getPaginas();
        }
        return ((documento.getPaginas() + totalPaginas) <= 250);
    }

    // Funcionando corretamente
    private boolean verificarSeJaExisteDocumentoSubstancial(Processo processo) {
        Documento[] documentos = processo.pegarCopiaDoProcesso();
        for (Documento doc : documentos) {
            if ((doc instanceof Edital || doc instanceof Portaria) && doc.getPaginas() >= 100
                    && ((Norma) doc).getValido()) {
                return true;
            }
        }
        return false;
    }

    // Funcionando corretamente
    private boolean verificarSeAdicionaDocumentoSubstancial(Processo processo, Documento documento) {
        Documento[] documentos = processo.pegarCopiaDoProcesso();
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


    private boolean verificarSeAdicionaDocumentoCasoCircularesEOficio(Processo processo, Documento documento) {
        Documento[] documentos = processo.pegarCopiaDoProcesso();
        if (!(documento instanceof Circular || documento instanceof Oficio)) {
            return true;
        }
        // Se chegou aqui, ele é Circular ou Oficio
        for (Documento doc : documentos) {
            if (!(doc instanceof Oficio || doc instanceof Circular)) {
                return false;
            }
        }
        // Se chegou aqui, significa que o processo já contém um circular ou um ofício
        // Abaixo, pegamos todos os documentos do tipo Circular ou Oficio presentes no processo
        List<Documento> oficiosOuCircularesJaPresentesNoProcesso = new ArrayList<>();

        for (Documento doc : documentos) {
            if (doc instanceof Circular || doc instanceof Oficio) {
                oficiosOuCircularesJaPresentesNoProcesso.add(doc);
            }
        }

        boolean contemMesmoDestinatario = true;

        for (Documento doc : oficiosOuCircularesJaPresentesNoProcesso) {
            if (doc instanceof Circular) { // Circular comparando com Circular
                if (documento instanceof Circular) {
                    boolean encontrou = false;
                    for (String destinatario : ((Circular) documento).getDestinatarios()) {
                        for (String destinatario2 : ((Circular) doc).getDestinatarios()) {
                            if (destinatario.equals(destinatario2)) {
                                encontrou = true;
                                break;
                            }
                        }
                        if (encontrou) break;
                    }
                    if (!encontrou) {
                        contemMesmoDestinatario = false;
                        break;
                    }
                } else { // Circular comparando com Oficio
                    boolean encontrou = false;
                    for (String destinatario : ((Circular) doc).getDestinatarios()) {
                        if (destinatario.equals(((Oficio) documento).getDestinatario())) {
                            encontrou = true;
                            break;
                        }
                    }
                    if (!encontrou) {
                        contemMesmoDestinatario = false;
                        break;
                    }
                }
            } else if (doc instanceof Oficio) {
                if (documento instanceof Circular) { // Comparando Ofício com Circular
                    boolean encontrou = false;
                    for (String destinatario : ((Circular) documento).getDestinatarios()) {
                        if (destinatario.equals(((Oficio) doc).getDestinatario())) {
                            encontrou = true;
                            break;
                        }
                    }
                    if (!encontrou) {
                        contemMesmoDestinatario = false;
                        break;
                    }
                } else { // Comparando Ofício com Ofício
                    if (!((Oficio) documento).getDestinatario().equals(((Oficio) doc).getDestinatario())) {
                        contemMesmoDestinatario = false;
                        break;
                    }
                }
            }
        }

        return contemMesmoDestinatario;
    }

    private boolean verificarSeAdicionaDocumentoCasoDiploma(Processo processo, Documento documento) {
        Documento[] documentos = processo.pegarCopiaDoProcesso();
        if (!(documento instanceof Diploma)) {
            // Ata e Certificados podem ficar com Diploma
            if (documento instanceof Ata || documento instanceof Certificado) {
                return true;
            }
            // Se conter um diploma, automaticamente é inválido adicionar
            for (Documento doc : documentos) {
                if (doc instanceof Diploma) {
                    return false;
                }
            }
        } else {
            // Ao chegar aqui automaticamente é Diploma
            // Se conter apenas Diploma, Certificado ou Ata, pode ser adicionado
            for (Documento doc : documentos) {
                if (!(doc instanceof Certificado || doc instanceof Ata)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean verificarSeAdicionaDocumentoCasoAtestado(Processo processo, Documento documento) {
        Documento[] documentos = processo.pegarCopiaDoProcesso();
        if (!(documento instanceof Atestado)) {
            return true;
        }
        // Ao chegar aqui, automaticamente é Atestado
        boolean contemAtestado = false;
        for (Documento doc : documentos) {
            // Se o processo não contém nenhum Atestado, não há como ter conflito de categoria
            if (doc instanceof Atestado) {
                contemAtestado = true;
                break;
            }
        }
        if (!contemAtestado){
            return true;
        }

        // Chegando aqui, automaticamente o processo contém um ou mais Atestado
        List<String> categoriasDosAtestadosNoProcesso = new ArrayList<>();

        for (Documento doc : documentos) {
            if (doc instanceof Atestado) {
                categoriasDosAtestadosNoProcesso.add(((Atestado) doc).getCategoria());
            }
        }

        String categoriaDoAtestado = ((Atestado) documento).getCategoria();
        // Se a categoria do atestado no processo for diferente do diploma a ser adicionado, automaticamente não
        // adiciona
        // A categoria do atestado no processo é definido pelo primeiro atestado adicionado no processo
        return categoriasDosAtestadosNoProcesso.contains(categoriaDoAtestado);
    }

    private boolean isTodosDocumentosAtas(Processo processo) {
        Documento[] documentos = processo.pegarCopiaDoProcesso();
        for (Documento doc : documentos) {
            if (doc instanceof DocumentoAdministrativo || doc instanceof DocumentoAcademico) {
                return false;
            }
        }
        return true;
    }

    private boolean isDocumentoDePosGraduacao(Documento doc) {
        return doc.getCodigoCurso() == POS_GRADUACAO_ENGENHARIA || doc.getCodigoCurso() == POS_GRADUACAO_ENGENHARIA_ELETRICA
                || doc.getCodigoCurso() == POS_GRADUACAO_ENGENHARIA_SOFTWARE;
    }

    private void printTamanhoDaListaDeDocumentosDoProcesso(int numeroProcesso, Documento[] documentos) {
        System.out.printf("Tamanho do processo %s: %d\n", numeroProcesso, documentos.length);
    }

    private void printTotalDePaginasDosDocumentosDoProcesso(int numeroProcesso, Documento[] documentos) {
        System.out.printf("Páginas do processo %s: %d\n", numeroProcesso, Arrays.stream(documentos).mapToInt(Documento::getPaginas).sum());
    }

    private void printTodosTiposDeDocumentosPresentesNaListaDeDocumentos(Documento[] documentos) {
        System.out.println("=================================");
        Arrays.stream(documentos).forEach(
                documento -> System.out.println(documento.getClass() + " | " + documento.getPaginas()));
        System.out.println("=================================");
    }

    private void printResultadoDasVerificacoesParaAdicionarDocumentos(
            boolean isListaDeDocumentosVazia,
            boolean deveAdicionarPorNivelDeEducacao,
            boolean deveAdicionarPorTipo,
            boolean deveAdicionarPorQuantidadeDePaginas,
            boolean deveAdicionarPorDocumentoSubstancial,
            boolean deveAdicionarPorDestinatarioDeOficioECircular,
            boolean deveAdicionarPorDiploma,
            boolean deveAdicionarPorCategoriaAtestado
    ) {
        System.out.println("Lista de documentos do processo está vazia: " + (isListaDeDocumentosVazia ? "Sim" : "Não"));
        System.out.println("É válido para adição quanto ao: ");
        System.out.println("    Nível de Educação: " + (deveAdicionarPorNivelDeEducacao
                ? "Sim" : "Não"));
        System.out.println("    Tipo de Documento: " + (deveAdicionarPorTipo ? "Sim" : "Não"));
        System.out.println("    Quantidade de Páginas:" + (deveAdicionarPorQuantidadeDePaginas ? "Sim" : "Não"));
        System.out.println("    Ausência de Documento Substancial: " + (deveAdicionarPorDocumentoSubstancial ? "Sim" : "Não"));
        System.out.println("    Destinatário de Circular ou Ofício:    " + (deveAdicionarPorDestinatarioDeOficioECircular ? "Sim" : "Não"));
        System.out.println("    Caso seja Diploma: " + (deveAdicionarPorDiploma ? "Sim" : "Não"));
        System.out.println("    Categoria do Atestado: " + (deveAdicionarPorCategoriaAtestado ? "Sim" : "Não"));
    }
}