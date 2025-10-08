package estudantes.entidades.utils;

import estudantes.entidades.Documento;

import java.util.Arrays;

/**
 * Classe abstrata que fornece métodos de impressão.
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 */
public abstract class Impressores {

    /**
     * Imprime no console o tamanho da lista de documentos presente no processo.
     *
     * @param numeroProcesso número do processo, utilizado para formatação e não acesso via ìndex
     * @param documentos     lista de documentos do processo
     * @since 1.0
     */
    public static void printTamanhoDaListaDeDocumentosDoProcesso(int numeroProcesso, Documento[] documentos) {
        System.out.printf("Tamanho do processo %s: %d\n", numeroProcesso, documentos.length);
    }

    /**
     * Imprime no console a soma do total de páginas de todos os {@code Documentos} presentes no processo.
     * <p> Utiliza a API de Streams, mapeando cada {@code Documento} com {@code mapToInt(Documentos::getPaginas)} e
     * obtendo o total com {@code sum()}.</p>
     *
     * @param numeroProcesso número do processo, utilizado para formatação e não acesso via ìndex
     * @param documentos     lista de documentos do processo
     * @since 1.0
     */
    public static void printTotalDePaginasDosDocumentosDoProcesso(int numeroProcesso, Documento[] documentos) {
        System.out.printf("Páginas do processo %s: %d\n", numeroProcesso, Arrays.stream(documentos).mapToInt(Documento::getPaginas).sum());
    }

    /**
     * Imprime no console quais tipos de documentos estão presentes na lista de documentos e quantas páginas
     * cada um possui.
     * <p>Utiliza a API de Streams juntamente com {@code forEach()}, percorrendo cada elemento da lista e utilizando
     * {@code documento.getClass()} e {@code document.getPaginas()}.</p>
     *
     * @param documentos lista de documentos do processo
     * @since 1.0
     */
    public static void printTodosTiposDeDocumentosPresentesNaListaDeDocumentos(Documento[] documentos) {
        System.out.println("=================================");
        Arrays.stream(documentos).forEach(
                documento -> System.out.println(documento.getClass() + " | " + documento.getPaginas()));
        System.out.println("=================================");
    }

    /**
     * Imprime no console o resultado de todas as validações realizadas antes de se adicionar um documento no processo
     *
     * @param isListaDeDocumentosVazia                      se a lista está vazia
     * @param deveAdicionarPorNivelDeEducacao               se adiciona baseado no nível de educação do documento
     *                                                      (pós-graduação ou graduação)
     * @param deveAdicionarPorTipo                          se adiciona baseado no tipo de documento (Administrativo, Academico, Ata)
     * @param deveAdicionarPorQuantidadeDePaginas           se adiciona baseado na quantidade total de páginas
     * @param deveAdicionarPorDocumentoSubstancial          se adiciona baseado se é documento substancial
     * @param deveAdicionarPorDiploma                       se adiciona baseado nas regras do Diploma
     * @param deveAdicionarPorDestinatarioDeOficioECircular se adiciona baseado nas regras do Oficio e Circular
     * @param deveAdicionarPorCategoriaAtestado             se adiciona baseado nas regras de Categoria do Atestado
     * @since 1.0
     */
    public static void printResultadoDasVerificacoesParaAdicionarDocumentos(
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
