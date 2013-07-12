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
    private float m_speed = 16f;
    private int m_mapSizeW, m_mapSizeH;
    
    //Construtor:
    public Camera(MapPackage.Map map){
        m_mapSizeW = map.getM_drawableMap().getWidth() * 32;
        m_mapSizeH = map.getM_drawableMap().getHeight() * 32;
        
        m_position = new Vector2f((m_mapSizeW/ 2) - (800 / 2), (m_mapSizeH / 2) - (600 / 2));
    }
    
    //Atualiza a posição da camera:
    public void update(){
        
    }
    
    public void move(CharacterPackage.Killer.DIRECTIONS dir){
        switch(dir){
            case DIR_LEFT:
                m_position.x -= m_speed;
                
                //Saímos do mapa?
                if(m_position.x < 0){
                    m_position.x = 0;
                }
                break;
            case DIR_RIGHT:
                m_position.x += m_speed;
                
                //Saímos do mapa?
                if((m_position.x + 800) > m_mapSizeW){
                    m_position.x = m_mapSizeW - 800;
                }
                break;
            case DIR_UP:
                m_position.y -= m_speed;
                
                //Saímos do mapa?
                if(m_position.y < 0){
                    m_position.y = 0;
                }
                break;
            case DIR_DOWN:
                m_position.y += m_speed;
                
                //Saímos do mapa?
                if((m_position.y + 600) > m_mapSizeH){
                    m_position.y = m_mapSizeH - 600;
                }
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
