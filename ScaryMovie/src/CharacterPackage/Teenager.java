/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CharacterPackage;

import MapPackage.Map;
import TrapPackage.TrapManager;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Rectangle;
import scarymovie.ResourceManager;
import org.newdawn.slick.geom.Vector2f;
import scarymovie.GameEntity;

/**
 *
 * @author CarlosEduardo
 */
public class Teenager extends GameEntity{
    //States de Movimentos:
    public enum MOVEMENT_STATES{
        STATE_DEAD(0),
        STATE_STANDING (1),
        STATE_SWIMMING (2),
        STATE_WALKING_LEFT (3),
        STATE_WALKING_RIGHT (4),
        STATE_WALKING_UP (5),
        STATE_WALKING_DOWN (6),
        STATE_ATACK_LEFT (7),
        STATE_ATACK_RIGHT (8),
        STATE_ATACK_UP (9),
        STATE_ATACK_DOWN (10);
        
        //O ID de cada objeto:
        public int m_id;
        
        //Cosntrutor:
        private MOVEMENT_STATES(int m_id) {
            this.m_id = m_id;
        }
    }
    
    
    //Gêneros de Teenager:
    public enum TEENAGER_GENDER{
        GENDER_MALE (0),
        GENDER_FEMALE (1);
        
        //O ID de cada objeto:
        public int m_id;
        
        //Cosntrutor:
        private TEENAGER_GENDER(int m_id) {
            this.m_id = m_id;
        }
    }
    
    //Membros:
    private TEENAGER_GENDER m_gender;
    private TeenAI m_ai;
    
    //Construtor:
    public Teenager(ResourceManager sm, Vector2f position, Killer killer){
        //Salvando valores:
        m_ai = new TeenAI(this);
        m_ai.setM_killer(killer);
        m_speed = new Vector2f(0, 0);
        m_position = position;        
        m_colisionBox = new Rectangle(m_position.x, m_position.y + 32, 32, 32);
        
        
        //Gerando o gênero aleatoriamente:
        Random rand = new Random();
        int number = rand.nextInt(2);
        if(number == 0){
            this.m_gender = TEENAGER_GENDER.GENDER_MALE;
        }
        else{
            this.m_gender = TEENAGER_GENDER.GENDER_FEMALE;
        }
        
        //Definindo o estado atual:
        m_movementState = MOVEMENT_STATES.STATE_STANDING;
        
        //Carregando as animações:
        m_sprites = new ArrayList<>();
        
        this.m_sprites.add(MOVEMENT_STATES.STATE_DEAD.m_id, new Animation(sm.getTeenAnimation(getM_gender(), MOVEMENT_STATES.STATE_DEAD), 200));
        this.m_sprites.add(MOVEMENT_STATES.STATE_STANDING.m_id, new Animation(sm.getTeenAnimation(getM_gender(), MOVEMENT_STATES.STATE_STANDING), 200));
        this.m_sprites.add(MOVEMENT_STATES.STATE_SWIMMING.m_id, new Animation(sm.getTeenAnimation(getM_gender(), MOVEMENT_STATES.STATE_SWIMMING), 200));
        this.m_sprites.add(MOVEMENT_STATES.STATE_WALKING_LEFT.m_id, new Animation(sm.getTeenAnimation(getM_gender(), MOVEMENT_STATES.STATE_WALKING_LEFT), 200));
        this.m_sprites.add(MOVEMENT_STATES.STATE_WALKING_RIGHT.m_id, new Animation(sm.getTeenAnimation(getM_gender(), MOVEMENT_STATES.STATE_WALKING_RIGHT), 200));
        this.m_sprites.add(MOVEMENT_STATES.STATE_WALKING_UP.m_id, new Animation(sm.getTeenAnimation(getM_gender(), MOVEMENT_STATES.STATE_WALKING_UP), 200));
        this.m_sprites.add(MOVEMENT_STATES.STATE_WALKING_DOWN.m_id, new Animation(sm.getTeenAnimation(getM_gender(), MOVEMENT_STATES.STATE_WALKING_DOWN), 200));
    }
    
    //Atualiza o teenager:
    public void update(Map map, TeenagerManager tm, TrapManager trm){
        //Estamos mortos?
        if(m_movementState != MOVEMENT_STATES.STATE_DEAD){
            //Verificando o tile que estamos:
            m_currentTile = map.getTileByPosition(m_position.x + 16, m_position.y + 48);

            //Atualziando a AI:
            m_ai.updateLogic(map, tm, trm);
        }
    }  
    

    /**
     * @return the m_gender
     */
    public TEENAGER_GENDER getM_gender() {
        return m_gender;
    }

    /**
     * @param m_gender the m_gender to set
     */
    public void setM_gender(TEENAGER_GENDER m_gender) {
        this.m_gender = m_gender;
    }

    /**
     * @return the m_movementState
     */
    public MOVEMENT_STATES getM_movementState() {
        return m_movementState;
    }

    /**
     * @param m_movementState the m_movementState to set
     */
    public void setM_movementState(MOVEMENT_STATES m_movementState) {
        this.m_movementState = m_movementState;
    }

    /**
     * @return the m_ai
     */
    public TeenAI getM_ai() {
        return m_ai;
    }

    /**
     * @param m_ai the m_ai to set
     */
    public void setM_ai(TeenAI m_ai) {
        this.m_ai = m_ai;
    }
    
    public void kill(){
        m_movementState = MOVEMENT_STATES.STATE_DEAD;
        
        m_position.set(m_position.x - 16, m_position.y + 32);
        
        m_colisionBox.setHeight(32);
        m_colisionBox.setWidth(64);
        m_colisionBox.setLocation(m_position);
        
    }
}