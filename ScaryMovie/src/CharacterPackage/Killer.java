/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CharacterPackage;

import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author CarlosEduardo
 */
public class Killer {
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
    boolean m_spawned = false;
    KILLER_STATES m_state;
    Teenager.MOVEMENT_STATES m_movementState;
    SpriteSheet m_spriteSheet = null;
    
    //Construtor:
    public Killer(Vector2f position){
        
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
}
