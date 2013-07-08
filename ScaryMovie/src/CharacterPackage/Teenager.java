/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CharacterPackage;

import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author CarlosEduardo
 */
public class Teenager {
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
    TEENAGER_GENDER m_gender;
    boolean m_panicMode = false;
    int m_fear = 0, m_curiosity = 0;
    MOVEMENT_STATES m_movementState;
    float viewDistance = 0;
    
    
    //Construtor:
    public Teenager(TeenagerSpriteManager sm, Vector2f position){
        
    }
    
    //Atualiza o teenager:
    public void update(){
        
    }    
    
}
