/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiPackage;

import CharacterPackage.Killer;
import CharacterPackage.TeenagerManager;
import MapPackage.Map;
import TrapPackage.TrapManager;

/**
 *
 * @author CarlosEduardo
 */
public class Gui {
    //Membros:
    private int m_score = 0;
    
    //Desenha a Gui:
    public void draw(){
        
    }
    
    //Atualiza os componentes do jogo:
    public void update(Killer killer, Map map, TeenagerManager teenManager, TrapManager trapsManager){
        
    }

    /**
     * @return the m_score
     */
    public int getM_score() {
        return m_score;
    }

    /**
     * @param m_score the m_score to set
     */
    public void setM_score(int m_score) {
        this.m_score = m_score;
    }
}
