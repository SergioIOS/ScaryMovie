/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CharacterPackage;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author CarlosEduardo
 */
public class TeenagerSpriteManager {
    //Membros:
    private SpriteSheet m_maleTeenagers;
    private SpriteSheet m_femaleTeenagers;
    
    //Retorna Animacao de acordo com o ID:
    public Image[] getAnimationByID(Teenager.TEENAGER_GENDER gender){
        Image[] temp = null;
        
        return temp;
    }

    /**
     * @return the m_maleTeenagers
     */
    public SpriteSheet getM_maleTeenagers() {
        return m_maleTeenagers;
    }

    /**
     * @param m_maleTeenagers the m_maleTeenagers to set
     */
    public void setM_maleTeenagers(SpriteSheet m_maleTeenagers) {
        this.m_maleTeenagers = m_maleTeenagers;
    }

    /**
     * @return the m_femaleTeenagers
     */
    public SpriteSheet getM_femaleTeenagers() {
        return m_femaleTeenagers;
    }

    /**
     * @param m_femaleTeenagers the m_femaleTeenagers to set
     */
    public void setM_femaleTeenagers(SpriteSheet m_femaleTeenagers) {
        this.m_femaleTeenagers = m_femaleTeenagers;
    }
}
