/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiPackage;

import CharacterPackage.Killer;
import CharacterPackage.TeenagerManager;
import MapPackage.Map;
import TrapPackage.TrapManager;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author CarlosEduardo
 */
public class Gui {
    //
    public enum WARNING_TYPES{
        WARNING_HUNTING,
        WARNING_PLANNING,
    }
    
    //Membros:
    private int m_score = 0;
    ScreenWarning m_currentWarning = null;
    
    //Imagens usadas:
    Image m_PlanningPhaseSign, m_HuntingPhaseSign;
    
    static private Gui instance = null;
    
    //Singleton:
    static public Gui getInstance(){
        if(instance == null){
            try{
                instance = new Gui();
            }
            catch(SlickException e){
                
            }
        }
        
        return instance;
    }
    
    //Cosntrutor:
    public Gui() throws SlickException{
        //Carregando as imagens:
        m_PlanningPhaseSign = new Image("data/PlanningPhaseSign.png");
        m_HuntingPhaseSign = new Image("data/HuntingPhaseSign.png");
        
        m_currentWarning = null;
    }
    
    //Desenha a Gui:
    public void draw(){
        //Temos algum aviso?
        if(m_currentWarning != null)
            m_currentWarning.draw();
    }
    
    //Atualiza os componentes do jogo:
    public void update(Killer killer, Map map, TeenagerManager teenManager, TrapManager trapsManager){
        //Temos algum aviso?
        if(m_currentWarning != null){
            m_currentWarning.update();
            
            //Pronto apra apagar?
            if(m_currentWarning.isM_readyToDelete()){
                m_currentWarning = null;
            }
        }
            
    }
    
    public void showScreenWarning(WARNING_TYPES type){
        if(m_currentWarning == null){
            System.out.println("Criou aviso nessa porra!");
            
            if(type == WARNING_TYPES.WARNING_HUNTING){
                m_currentWarning = new ScreenWarning(m_HuntingPhaseSign, new Vector2f(-400, 100), Killer.DIRECTIONS.DIR_RIGHT);
            }else{
                m_currentWarning = new ScreenWarning(m_PlanningPhaseSign, new Vector2f(-400, 100), Killer.DIRECTIONS.DIR_RIGHT);
            }
        }
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
