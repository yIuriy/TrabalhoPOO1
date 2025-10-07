package estudantes.entidades;

import estudantes.entidades.utils.Impressores;
import estudantes.entidades.utils.ManipuladorDeProcesso;
import estudantes.entidades.utils.Validadores;
import professor.entidades.Mesa;
import professor.entidades.Processo;
import professor.entidades.Universidade;

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
    private final Mesa mesa;
    private final Universidade universidade;
    private final ManipuladorDeProcesso manipuladorDeProcesso;

    /**
     * Construtor de Burocrata.
     *
     * @param m mesa com os processos
     * @param u universidade com os montes dos cursos e a secretaria
     */
    public Burocrata(Mesa m, Universidade u) {
        this.mesa = m;
        this.universidade = u;
        manipuladorDeProcesso = new ManipuladorDeProcesso(universidade);
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
        for (int i = 0; i < 5; i++) {
            Processo processo = mesa.getProcesso(i);
            manipuladorDeProcesso.gerenciarProcesso(processo);
            manipuladorDeProcesso.despacharProcesso(processo, i + 1);
        }
    }
}