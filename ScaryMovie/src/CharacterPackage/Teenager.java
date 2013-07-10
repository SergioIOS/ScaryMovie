/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CharacterPackage;

import org.newdawn.slick.geom.Vector2f;
import scarymovie.GameEntity;

/**
 *
 * @author CarlosEduardo
 */
public class Teenager extends GameEntity{
    //States de Movimentos:
    public enum MOVEMENT_STATES{
        STATE_WALKING_LEFT (0),
        STATE_WALKING_RIGHT (1),
        STATE_WALKING_UP (2),
        STATE_WALKING_DOWN (3),
        STATE_STANDING (4);
        
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
    private boolean m_panicMode = false;
    private int m_fear = 0;
    private int m_curiosity = 0;
    private MOVEMENT_STATES m_movementState;
    private float viewDistance = 0;
    
    
    //Construtor:
    public Teenager(TeenagerSpriteManager sm, Vector2f position){
        //Tratativa do TeenagerSpriteManager
        
        this.m_position = position;
    }
    
    //Atualiza o teenager:
    public void update(){
        
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
     * @return the m_panicMode
     */
    public boolean isM_panicMode() {
        return m_panicMode;
    }

    /**
     * @param m_panicMode the m_panicMode to set
     */
    public void setM_panicMode(boolean m_panicMode) {
        this.m_panicMode = m_panicMode;
    }

    /**
     * @return the m_fear
     */
    public int getM_fear() {
        return m_fear;
    }

    /**
     * @param m_fear the m_fear to set
     */
    public void setM_fear(int m_fear) {
        this.m_fear = m_fear;
    }

    /**
     * @return the m_curiosity
     */
    public int getM_curiosity() {
        return m_curiosity;
    }

    /**
     * @param m_curiosity the m_curiosity to set
     */
    public void setM_curiosity(int m_curiosity) {
        this.m_curiosity = m_curiosity;
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
     * @return the viewDistance
     */
    public float getViewDistance() {
        return viewDistance;
    }

    /**
     * @param viewDistance the viewDistance to set
     */
    public void setViewDistance(float viewDistance) {
        this.viewDistance = viewDistance;
    }
    
}
