/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CharacterPackage;

import CharacterPackage.Teenager.MOVEMENT_STATES;
import MapPackage.Map;
import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import scarymovie.GameEntity;
import scarymovie.ResourceManager;
import scarymovie.Timer;

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
    private Timer m_atackAnimationTimer = null, m_atackDelayTimer = null;
    boolean m_isAtacking = false;
    TeenagerManager m_teenManager = null;
    
    static Killer instance = null;
    
    static public Killer getInstance(Vector2f position, ResourceManager rm){
        if(instance == null){
            instance = new Killer(position, rm);
        }
        
        return instance;
    }
    
    //Construtor:
    public Killer(Vector2f position, ResourceManager rm){
        //Salvando a posição:
        this.m_position = position;
        m_speed = new Vector2f(0,0);
        m_state = KILLER_STATES.STATE_IDLE;
        m_movementState = MOVEMENT_STATES.STATE_STANDING;
        m_colisionBox = new Rectangle(m_position.x, m_position.y + 32, 32, 32);
        m_atackAnimationTimer = new Timer();
        m_atackDelayTimer = new Timer();
        m_teenManager = TeenagerManager.getInstance(null);
        
        m_atackDelayTimer.start();
        
        //Criando as animações:
        m_sprites = new ArrayList<>();
        
        //Carregando as animações:
        this.m_sprites.add(MOVEMENT_STATES.STATE_DEAD.m_id, new Animation(rm.getTeenAnimation(Teenager.TEENAGER_GENDER.GENDER_MALE, m_movementState), 300));
        this.m_sprites.add(MOVEMENT_STATES.STATE_STANDING.m_id, new Animation(rm.getKillerAnimation(MOVEMENT_STATES.STATE_STANDING), 300));
        this.m_sprites.add(MOVEMENT_STATES.STATE_SWIMMING.m_id, new Animation(rm.getKillerAnimation(MOVEMENT_STATES.STATE_SWIMMING), 300));
        this.m_sprites.add(MOVEMENT_STATES.STATE_WALKING_LEFT.m_id, new Animation(rm.getKillerAnimation(MOVEMENT_STATES.STATE_WALKING_LEFT), 300));
        this.m_sprites.add(MOVEMENT_STATES.STATE_WALKING_RIGHT.m_id, new Animation(rm.getKillerAnimation(MOVEMENT_STATES.STATE_WALKING_RIGHT), 300));
        this.m_sprites.add(MOVEMENT_STATES.STATE_WALKING_UP.m_id, new Animation(rm.getKillerAnimation(MOVEMENT_STATES.STATE_WALKING_UP), 300));
        this.m_sprites.add(MOVEMENT_STATES.STATE_WALKING_DOWN.m_id, new Animation(rm.getKillerAnimation(MOVEMENT_STATES.STATE_WALKING_DOWN), 300));
        this.m_sprites.add(MOVEMENT_STATES.STATE_ATACK_LEFT.m_id, new Animation(rm.getKillerAnimation(MOVEMENT_STATES.STATE_ATACK_LEFT), 100));
        this.m_sprites.add(MOVEMENT_STATES.STATE_ATACK_RIGHT.m_id, new Animation(rm.getKillerAnimation(MOVEMENT_STATES.STATE_ATACK_RIGHT), 100));
        this.m_sprites.add(MOVEMENT_STATES.STATE_ATACK_UP.m_id, new Animation(rm.getKillerAnimation(MOVEMENT_STATES.STATE_ATACK_UP), 100));
        this.m_sprites.add(MOVEMENT_STATES.STATE_ATACK_DOWN.m_id, new Animation(rm.getKillerAnimation(MOVEMENT_STATES.STATE_ATACK_DOWN), 100));
        
        System.out.println("Killer Spawned!");
    }
    
    //Atualiza o Killer:
    public void update(Map m_map){
        m_position.add(m_speed);
        
        //Estamos atacando?
        if(m_isAtacking){
            //Esperando os 2 segundos de animação:
            if(m_atackAnimationTimer.getElapsedTime() >= 500){
                //Iniciando o timer para delay:
                
                
                //Parando o relógio da animação:
                m_atackAnimationTimer.reset();
                m_atackDelayTimer.reset();
                m_atackDelayTimer.start();
                m_isAtacking = false;
            }
            else{
                switch(m_movementState){
                    case STATE_ATACK_DOWN:
                        m_movementState = MOVEMENT_STATES.STATE_ATACK_DOWN;
                        break;
                    case STATE_ATACK_LEFT:
                        m_movementState = MOVEMENT_STATES.STATE_ATACK_LEFT;
                        break;
                    case STATE_ATACK_RIGHT:
                        m_movementState = MOVEMENT_STATES.STATE_ATACK_RIGHT;
                        break;
                    case STATE_ATACK_UP:
                        m_movementState = MOVEMENT_STATES.STATE_ATACK_UP;
                        break;
                }
                
                attack();
            }
            
            //Veriicando a colisão com os teens:
            Teenager temp = m_teenManager.checkTeenColision(m_colisionBox);
            
            if(temp != null){
                //Apagando o teen:
                temp.kill();
            }
        }
        
        //Colidimos?
        if(m_map.checkMapColision(new Rectangle(m_position.x, m_position.y + 32, 32, 32))){
            //Voltando:
            m_position.sub(m_speed);
        }else{
            //Movimento liberado:
            m_colisionBox.setLocation(m_position.x, m_position.y + 48);
            
            //Atualizando o tile atual:
            m_currentTile = m_map.getTileByPosition(m_position.x + 16, m_position.y + 48);
        }
    }
    
    //Movimenta o Killer:
    public void move(DIRECTIONS direction){
        if(!m_isAtacking){
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
    }
    
    //Atacar:
    public void attack(){
        //Se não estamos atacando e estamos no tempo permitido:
        if(m_isAtacking == false && m_atackDelayTimer.getElapsedTimeSecs() > 3){
            m_speed.x = 0;
            m_speed.y = 0;

            //Verificando a posição que estávamos andando:
            switch(m_movementState){
                case STATE_STANDING:
                    m_movementState = MOVEMENT_STATES.STATE_ATACK_DOWN;

                    m_speed.y = 4;
                    break;
                case STATE_WALKING_LEFT:
                    m_movementState = MOVEMENT_STATES.STATE_ATACK_LEFT;

                    m_speed.x = -4;
                    break;
                case STATE_WALKING_RIGHT:
                    m_movementState = MOVEMENT_STATES.STATE_ATACK_RIGHT;

                    m_speed.x = 4;
                    break;
                case STATE_WALKING_UP:
                    m_movementState = MOVEMENT_STATES.STATE_ATACK_UP;

                    m_speed.y = -4;
                    break;
                case STATE_WALKING_DOWN:
                    m_movementState = MOVEMENT_STATES.STATE_ATACK_DOWN;

                    m_speed.y = 4;
                    break;
                default:
                    throw new AssertionError(m_movementState.name());
            }

            m_atackAnimationTimer.start();

            System.out.println("Atacando!");

            m_isAtacking = true;
        }
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
