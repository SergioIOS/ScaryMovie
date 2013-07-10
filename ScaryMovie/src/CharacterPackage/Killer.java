/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CharacterPackage;

import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;
import scarymovie.GameEntity;

/**
 *
 * @author CarlosEduardo
 */
public class Killer extends GameEntity{
    //States do Killer:
    public enum KILLER_STATES{
        STATE_ATTACKING (0),
        STATE_IDLE (1);
        
        //O ID de cada objeto:
        public int m_id;
        
        //Construtor:
        private KILLER_STATES(int m_id){
            this.m_id = m_id;
        }
    }
    
    //Direções do Killer:
    public enum DIRECTIONS{
        DIR_LEFT (0),
        DIR_RIGHT (1),
        DIR_UP (2),
        DIR_DOWN (3);
        
        //O ID de cada objeto:
        public int m_id;
        
        //Construtor:
        private DIRECTIONS(int m_id){
            this.m_id = m_id;
        }
    }
    
    //Membros:
    private boolean m_spawned = false;
    private KILLER_STATES m_state;
    private Teenager.MOVEMENT_STATES m_movementState;
    private SpriteSheet m_spriteSheet = null;
    
    //Construtor:
    public Killer(Vector2f position){
        this.m_position = position;
    }
    
    //Atualiza o Killer:
    public void update(){
        
    }
    
    //Movimenta o Killer:
    public void move(DIRECTIONS direction){
        
    }
    
    //Atacar:
    public void attack(){
        
    }   
    
    
    /**
     * @return the m_spawned
     */
    public boolean isM_spawned() {
        return m_spawned;
    }

    /**
     * @param m_spawned the m_spawned to set
     */
    public void setM_spawned(boolean m_spawned) {
        this.m_spawned = m_spawned;
    }

    /**
     * @return the m_state
     */
    public KILLER_STATES getM_state() {
        return m_state;
    }

    /**
     * @param m_state the m_state to set
     */
    public void setM_state(KILLER_STATES m_state) {
        this.m_state = m_state;
    }

    /**
     * @return the m_movementState
     */
    public Teenager.MOVEMENT_STATES getM_movementState() {
        return m_movementState;
    }

    /**
     * @param m_movementState the m_movementState to set
     */
    public void setM_movementState(Teenager.MOVEMENT_STATES m_movementState) {
        this.m_movementState = m_movementState;
    }

    /**
     * @return the m_spriteSheet
     */
    public SpriteSheet getM_spriteSheet() {
        return m_spriteSheet;
    }

    /**
     * @param m_spriteSheet the m_spriteSheet to set
     */
    public void setM_spriteSheet(SpriteSheet m_spriteSheet) {
        this.m_spriteSheet = m_spriteSheet;
    }
}
