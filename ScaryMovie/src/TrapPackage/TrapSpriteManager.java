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
    SpriteSheet m_movableTraps, m_staticTraps;
    
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
}
