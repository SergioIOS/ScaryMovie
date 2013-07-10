/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrapPackage;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author CarlosEduardo
 */
public class TrapSpriteManager {
    //Membros:
    private SpriteSheet m_movableTraps;
    private SpriteSheet m_staticTraps;
    
    //Construtor:
    public TrapSpriteManager(){
        
    }
    
    //Retorna a Sprite de acordo com o ID:
    public Image getSpriteByID(TrapType.TRAP_ID type){
        Image temp = null;
        
        return temp;
    }
    
    //Retorna a Animacao de acordo com o ID:
    public Image[] getAnimationByID(TrapType.TRAP_ID type){
        Image[] temp = null;
        
        return temp;
    }

    /**
     * @return the m_movableTraps
     */
    public SpriteSheet getM_movableTraps() {
        return m_movableTraps;
    }

    /**
     * @param m_movableTraps the m_movableTraps to set
     */
    public void setM_movableTraps(SpriteSheet m_movableTraps) {
        this.m_movableTraps = m_movableTraps;
    }

    /**
     * @return the m_staticTraps
     */
    public SpriteSheet getM_staticTraps() {
        return m_staticTraps;
    }

    /**
     * @param m_staticTraps the m_staticTraps to set
     */
    public void setM_staticTraps(SpriteSheet m_staticTraps) {
        this.m_staticTraps = m_staticTraps;
    }
}
