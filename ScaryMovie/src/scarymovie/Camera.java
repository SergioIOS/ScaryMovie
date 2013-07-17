/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scarymovie;

import CharacterPackage.Killer;
import CharacterPackage.Teenager;
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
    Killer m_killer = null;
    Teenager m_teen = null;
    boolean m_lockedOnKiller = false, m_lockedOnTeenager = false;
    
    //Construtor:
    public Camera(MapPackage.Map map){
        m_mapSizeW = map.getM_drawableMap().getWidth() * 32;
        m_mapSizeH = map.getM_drawableMap().getHeight() * 32;
        
        m_position = new Vector2f((getM_mapSizeW()/ 2) - (800 / 2), (getM_mapSizeH() / 2) - (600 / 2));
    }
    
    //Atualiza a posição da camera:
    public void update(){
        if(m_killer != null && m_lockedOnKiller){
            //Obtendo a posição do killer:
            Vector2f temp = m_killer.getM_position();
            
            m_position.x = temp.x - 384;
            m_position.y = temp.y - 268;
        }
        else if(m_teen != null && m_lockedOnTeenager){
            //Obtendo a posição do teen:
            Vector2f temp = m_teen.getM_position();
            
            m_position.x = temp.x - 384;
            m_position.y = temp.y - 268;
        }
    }
    
    public void lockOnKiller(Killer killer){
        m_killer = killer;
        m_lockedOnKiller = true;
    }
    
    public void lockOnTeen(Teenager teen){
        m_teen = teen;
        m_lockedOnTeenager = true;
    }
    
    public void unlockCamera(){
        m_killer = null;
        m_teen = null;
        
        m_lockedOnKiller = false;
        m_lockedOnTeenager = false;
    }
    
    public void move(CharacterPackage.Killer.DIRECTIONS dir){
        switch(dir){
            case DIR_LEFT:
                m_position.x -= getM_speed();
                
                //Saímos do mapa?
                if(m_position.x < 0){
                    m_position.x = 0;
                }
                break;
            case DIR_RIGHT:
                m_position.x += getM_speed();
                
                //Saímos do mapa?
                if((m_position.x + 800) > getM_mapSizeW()){
                    m_position.x = getM_mapSizeW() - 800;
                }
                break;
            case DIR_UP:
                m_position.y -= getM_speed();
                
                //Saímos do mapa?
                if(m_position.y < 0){
                    m_position.y = 0;
                }
                break;
            case DIR_DOWN:
                m_position.y += getM_speed();
                
                //Saímos do mapa?
                if((m_position.y + 600) > getM_mapSizeH()){
                    m_position.y = getM_mapSizeH() - 600;
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

    /**
     * @return the m_speed
     */
    public float getM_speed() {
        return m_speed;
    }

    /**
     * @param m_speed the m_speed to set
     */
    public void setM_speed(float m_speed) {
        this.m_speed = m_speed;
    }

    /**
     * @return the m_mapSizeW
     */
    public int getM_mapSizeW() {
        return m_mapSizeW;
    }

    /**
     * @param m_mapSizeW the m_mapSizeW to set
     */
    public void setM_mapSizeW(int m_mapSizeW) {
        this.m_mapSizeW = m_mapSizeW;
    }

    /**
     * @return the m_mapSizeH
     */
    public int getM_mapSizeH() {
        return m_mapSizeH;
    }

    /**
     * @param m_mapSizeH the m_mapSizeH to set
     */
    public void setM_mapSizeH(int m_mapSizeH) {
        this.m_mapSizeH = m_mapSizeH;
    }
}
