/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scarymovie;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author CarlosEduardo
 */
public class GameEntity {
    private Vector2f m_position;
    private Vector2f m_speed;
    private Rectangle m_colisionBox;
    private Rectangle m_targetPosition;
    private Animation m_sprite = null;
    
    //Verifica a colisão desta entidade com uma entidade externa:
    public boolean checkColision(Rectangle entityRect){
        
        return true;
    }
    
    //Atualiza a animação do sprite:
    public void updateAnimation(){
        
    }
    
    //Desenha o sprite:
    public void draw(){
        
    }

    /**
     * @return the m_position
     */
    public Vector2f getM_position() {
        return m_position;
    }

    /**
     * @param m_position the m_position to set
     */
    public void setM_position(Vector2f m_position) {
        this.m_position = m_position;
    }

    /**
     * @return the m_speed
     */
    public Vector2f getM_speed() {
        return m_speed;
    }

    /**
     * @param m_speed the m_speed to set
     */
    public void setM_speed(Vector2f m_speed) {
        this.m_speed = m_speed;
    }

    /**
     * @return the m_colisionBox
     */
    public Rectangle getM_colisionBox() {
        return m_colisionBox;
    }

    /**
     * @param m_colisionBox the m_colisionBox to set
     */
    public void setM_colisionBox(Rectangle m_colisionBox) {
        this.m_colisionBox = m_colisionBox;
    }

    /**
     * @return the m_targetPosition
     */
    public Rectangle getM_targetPosition() {
        return m_targetPosition;
    }

    /**
     * @param m_targetPosition the m_targetPosition to set
     */
    public void setM_targetPosition(Rectangle m_targetPosition) {
        this.m_targetPosition = m_targetPosition;
    }

    /**
     * @return the m_sprite
     */
    public Animation getM_sprite() {
        return m_sprite;
    }

    /**
     * @param m_sprite the m_sprite to set
     */
    public void setM_sprite(Animation m_sprite) {
        this.m_sprite = m_sprite;
    }
}
