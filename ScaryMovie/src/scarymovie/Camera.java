/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scarymovie;

import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Anderson
 */
public class Camera {
    //Membros:
    private Vector2f m_position;
    
    //Construtor:
    public Camera(){
        m_position = new Vector2f(0, 0);
    }
    
    //Atualiza a posição da camera:
    public void update(){
        
    }
    
    public void move(CharacterPackage.Killer.DIRECTIONS dir){
        switch(dir){
            case DIR_LEFT:
                m_position.x -= 32;
                break;
            case DIR_RIGHT:
                m_position.x += 32;
                break;
            case DIR_UP:
                m_position.y -= 32;
                break;
            case DIR_DOWN:
                m_position.y += 32;
                break;
            default:
                throw new AssertionError(dir.name());
        }
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
}
