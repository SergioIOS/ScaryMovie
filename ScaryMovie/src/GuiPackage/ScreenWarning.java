/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiPackage;

import CharacterPackage.Killer;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author lorencini
 */
public class ScreenWarning {
    Image m_sprite;
    Vector2f m_position;
    Killer.DIRECTIONS m_movingDirection;
    private boolean m_readyToDelete = false;
    
    //Construtor:
    public ScreenWarning(Image img, Vector2f position, Killer.DIRECTIONS movingDirection) {
        m_sprite = img;
        m_position = new Vector2f(position);
        m_movingDirection = movingDirection;
    }
    
    public void draw(){
        m_sprite.draw(m_position.x, m_position.y);
    }
    
    public void update(){
        if(m_movingDirection == Killer.DIRECTIONS.DIR_RIGHT){
            m_position.x += 5;
            
            if(m_position.x > 800)
                m_readyToDelete = true;
        }
        else{
            m_position.x -= 5;
            
            if(m_position.x < -400)
                m_readyToDelete = true;
        }
    }

    /**
     * @return the m_readyToDelete
     */
    public boolean isM_readyToDelete() {
        return m_readyToDelete;
    }
}
