/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CharacterPackage;

import CharacterPackage.Teenager.MOVEMENT_STATES;
import MapPackage.Map;
import java.util.ArrayList;
import javax.annotation.Resource;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import scarymovie.GameEntity;
import scarymovie.ResourceManager;

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
        DIR_DOWN (3),
        DIR_STOP (4);
        
        //O ID de cada objeto:
        public int m_id;
        
        //Construtor:
        private DIRECTIONS(int m_id){
            this.m_id = m_id;
        }
    }
    
    //Membros:
    private boolean m_spawned = true;
    private KILLER_STATES m_state;
    
    //Construtor:
    public Killer(Vector2f position, ResourceManager rm){
        //Salvando a posição:
        this.m_position = position;
        m_speed = new Vector2f(0,0);
        m_state = KILLER_STATES.STATE_IDLE;
        m_movementState = MOVEMENT_STATES.STATE_STANDING;
        m_colisionBox = new Rectangle(m_position.x, m_position.y + 32, 32, 32);
        
        //Criando as animações:
        m_sprites = new ArrayList<>();
        
        //Carregando as animações:
        this.m_sprites.add(MOVEMENT_STATES.STATE_STANDING.m_id, new Animation(rm.getKillerAnimation(MOVEMENT_STATES.STATE_STANDING), 100));
        this.m_sprites.add(MOVEMENT_STATES.STATE_SWIMMING.m_id, new Animation(rm.getKillerAnimation(MOVEMENT_STATES.STATE_SWIMMING), 100));
        this.m_sprites.add(MOVEMENT_STATES.STATE_WALKING_LEFT.m_id, new Animation(rm.getKillerAnimation(MOVEMENT_STATES.STATE_WALKING_LEFT), 100));
        this.m_sprites.add(MOVEMENT_STATES.STATE_WALKING_RIGHT.m_id, new Animation(rm.getKillerAnimation(MOVEMENT_STATES.STATE_WALKING_RIGHT), 100));
        this.m_sprites.add(MOVEMENT_STATES.STATE_WALKING_UP.m_id, new Animation(rm.getKillerAnimation(MOVEMENT_STATES.STATE_WALKING_UP), 100));
        this.m_sprites.add(MOVEMENT_STATES.STATE_WALKING_DOWN.m_id, new Animation(rm.getKillerAnimation(MOVEMENT_STATES.STATE_WALKING_DOWN), 100));
        
        System.out.println("Killer Spawned!");
    }
    
    //Atualiza o Killer:
    public void update(Map m_map){
        m_position.add(m_speed);
        
        //Colidimos?
        if(m_map.checkMapColision(new Rectangle(m_position.x, m_position.y + 32, 32, 32))){
            //Voltando:
            m_position.sub(m_speed);
        }else{
            //Movimento liberado:
            m_colisionBox.setLocation(m_position.x, m_position.y + 32);
        }
    }
    
    //Movimenta o Killer:
    public void move(DIRECTIONS direction){
        m_speed.x = 0;
        m_speed.y = 0;
        
        switch(direction){
            case DIR_LEFT:
                m_movementState = MOVEMENT_STATES.STATE_WALKING_LEFT;
                m_speed.x -= 2;
                break;
            case DIR_RIGHT:
                m_movementState = MOVEMENT_STATES.STATE_WALKING_RIGHT;
                m_speed.x += 2;
                break;
            case DIR_UP:
                m_movementState = MOVEMENT_STATES.STATE_WALKING_UP;
                m_speed.y -= 2;
                break;
            case DIR_DOWN:
                m_movementState = MOVEMENT_STATES.STATE_WALKING_DOWN;
                m_speed.y += 2;
                break;
            case DIR_STOP:
                m_speed.x = 0;
                m_speed.y = 0;
                
                m_movementState = MOVEMENT_STATES.STATE_STANDING;
                break;
            default:
                throw new AssertionError(direction.name());
        }
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
}
