package estudantes.entidades.utils;

import estudantes.entidades.Documento;

import java.util.Arrays;

/**
 * Classe que fornece métodos de impressão
 * <br><br>
 * */
public abstract class Impressores {

    public static void printTamanhoDaListaDeDocumentosDoProcesso(int numeroProcesso, Documento[] documentos) {
        System.out.printf("Tamanho do processo %s: %d\n", numeroProcesso, documentos.length);
    }

    public static void printTotalDePaginasDosDocumentosDoProcesso(int numeroProcesso, Documento[] documentos) {
        System.out.printf("Páginas do processo %s: %d\n", numeroProcesso, Arrays.stream(documentos).mapToInt(Documento::getPaginas).sum());
    }

    public static void printTodosTiposDeDocumentosPresentesNaListaDeDocumentos(Documento[] documentos) {
        System.out.println("=================================");
        Arrays.stream(documentos).forEach(
                documento -> System.out.println(documento.getClass() + " | " + documento.getPaginas()));
        System.out.println("=================================");
    }

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
