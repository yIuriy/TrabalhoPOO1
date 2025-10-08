package estudantes.entidades;

import estudantes.entidades.utils.ManipuladorDeProcesso;
import professor.entidades.Mesa;
import professor.entidades.Processo;
import professor.entidades.Universidade;

/**
 * Classe que traz a lógica do algoritmo de organização e despacho de processos.
 *
 * @author Iuri da Silva Fernandes
 * @version 1.0
 */
public class Burocrata {
    private int estresse = 0;
    private final Mesa mesa;
    private final Universidade universidade;
    private final ManipuladorDeProcesso manipuladorDeProcesso;

    /**
     * Construtor de Burocrata.
     *
     * <p>Internamente instancia a Classe Utilitária {@code ManipuladorDeProcesso}.</p>
     *
     * @param m mesa com os processos
     * @param u universidade com os montes dos cursos e a secretaria
     * @see ManipuladorDeProcesso
     * @since 1.0
     */
    public Burocrata(Mesa m, Universidade u) {
        this.mesa = m;
        this.universidade = u;
        manipuladorDeProcesso = new ManipuladorDeProcesso(universidade);
    }

    /**
     * Retorna o estresse do {@code Burocrata}.
     *
     * @return o estresse do {@code Burocrata}
     * @since 1.0
     */
    public int getEstresse() {
        return estresse;
    }

    /**
     * Aumenta o estresse do {@code Burocrata} em 1.
     *
     * @since 1.0
     */
    public void estressar() {
        estresse++;
    }

    /**
     * Aumenta o estresse do {@code Burocrata} em 10.
     *
     * @since 1.0
     */
    public void estressarMuito() {
        estresse += 10;
    }

    /**
     * Executa a lógica de criação e despacho dos processos.
     * <br><br>
     * Esse método é o único método de controle invocado durante a simulação
     * da universidade.
     * <br></br>
     * Esse método será chamado a cada 100 milissegundos pelo simulador da
     * universidade.
     * <br><br>
     * <strong>O burocrata não pode manter documentos com ele</strong> depois
     * que o método trabalhar terminar de executar, ou seja, você deve devolver
     * para os montes dos cursos todos os documentos que você removeu dos montes
     * dos cursos.
     *
     * <p>Todos os {@code Processos} são manipulados por {@code ManipuladorProcessos}.</p>
     *
     * @see professor.entidades.Universidade#despachar(Processo)
     * @see professor.entidades.Universidade#removerDocumentoDoMonteDoCurso(estudantes.entidades.Documento, professor.entidades.CodigoCurso)
     * @see professor.entidades.Universidade#devolverDocumentoParaMonteDoCurso(estudantes.entidades.Documento, professor.entidades.CodigoCurso)
     * @see ManipuladorDeProcesso
     */
    public void trabalhar() {
        for (int i = 0; i < 5; i++) {
            Processo processo = mesa.getProcesso(i);
            manipuladorDeProcesso.gerenciarProcesso(processo);
            manipuladorDeProcesso.despacharProcesso(processo, i + 1);
        }
    }
}