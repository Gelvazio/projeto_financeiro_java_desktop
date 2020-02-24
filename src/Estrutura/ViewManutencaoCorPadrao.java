/*
 * Copyright (C) 2018 Gelvazio Camargo
 *
 * Este arquivo é parte do programa DTS SYSTEMS - SOFTWARE COMERCIAL;
 * O DTS SYSTEMS e um software livre; voce pode redistribui-lo e/ou modifica-lo
 * dentro dos termos da Licenca Publica Geral GNU como publicada pela Fundacao do    
 * Software Livre - FSF;
 *
 * Este sistema e distribuido na esperanca de ser util, mas SEM NENHUMA GARANTIA,
 * sem uma garantia implicita de ADEQUACAO a qualquer MERCADO ou APLICACAO EM PARTICULAR
 * Veja GNU General Public License para mais detalhes.
 *
 * Voce deve ter recebido uma cópia da Licenca Publica Geral GNU/GPL
 * com este programa.  Se não, veja <http://www.gnu.org/licenses/>.
 */
package Estrutura;

/**
 *
 * @author Jessica Moratelli
 */
public class ViewManutencaoCorPadrao extends TelaPadraoPrincipal {

    @Override
    public void ajustaAlturaLarguraJanela() {
        this.setLarguraJanela(350);
        this.setAlturaJanela(900);
    }

    @Override
    protected void adicionaCamposTela() {
        this.adicionaCampo("nome", "Nome", "15", 1, 50, 50);
        this.adicionaCampo("nome1", "Nome", "15", 2, 50, 50);
        this.adicionaCampo("nome11", "Nome", "15", 3, 50, 50);
        this.adicionaCampo("nome111", "Nome", "15", 4, 50, 50);
        this.adicionaCampo("nome111", "Nome", "15", 5, 50, 50);
//        adicionaCampo("nome", "Nome", "15", 1, 25, 25);
//        adicionaCampo("nome", "Nome", "15", 1, 25, 25);
//        adicionaCampo("nome", "Nome", "15", 1, 25, 25);
//        adicionaCampo("nome", "Nome", "15", 1, 25, 25);
    }

}
