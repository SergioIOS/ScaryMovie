/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scarymovie;

import CharacterPackage.Teenager;
import MapPackage.Tile;
import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author CarlosEduardo
 */
public class GameEntity {
    protected Vector2f m_position;
    protected Vector2f m_speed;
    protected Rectangle m_colisionBox;
    protected Teenager.MOVEMENT_STATES m_movementState;
    protected ArrayList<Animation> m_sprites = null;
    protected Tile m_currentTile;
    
    //Verifica a colisão desta entidade com uma entidade externa:
    public boolean checkColision(Rectangle entityRect){
        
        return true;
    }
    
    //Atualiza a animação do sprite:
    public void updateAnimation(){
        
    }
    
    //Desenha o sprite:
    public void draw(Camera camera){
        m_sprites.get(m_movementState.m_id).draw(m_position.x - camera.getM_position().x, m_position.y - camera.getM_position().y);
    }
    
    public Animation getCurrentAnimation(){
        return m_sprites.get(m_movementState.m_id);
    }

    /**
     * @return the m_position
     */
    public Vector2f getM_position() {
        return m_position;
    }

    public Vector2f getM_TranslatedPosition() {
        return new Vector2f(m_position.x - Camera.getInstance(null).getM_position().x, m_position.y - Camera.getInstance(null).getM_position().y);
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
}
