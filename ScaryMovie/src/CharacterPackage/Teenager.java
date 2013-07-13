/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CharacterPackage;

import MapPackage.Tile;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Animation;
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
        STATE_STANDING (0),
        STATE_SWIMMING (1),
        STATE_WALKING_LEFT (2),
        STATE_WALKING_RIGHT (3),
        STATE_WALKING_UP (4),
        STATE_WALKING_DOWN (5);
        
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
    private float m_viewDistance = 0;
    
    
    
    
    //Construtor:
    public Teenager(ResourceManager sm, Vector2f position, Tile tile){
        //Salvando valores:
        m_currentTile = tile;
        this.m_position = position;
        this.m_viewDistance = 150;
        
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
        if(m_currentTile.getM_type() == Tile.TILE_TYPES.TILE_WALKABLE){
            m_movementState = MOVEMENT_STATES.STATE_STANDING;
        }else{
            m_movementState = MOVEMENT_STATES.STATE_SWIMMING;
        }
        
        m_sprites = new ArrayList<>();
        
        //Carregando as animações:
        this.m_sprites.add(MOVEMENT_STATES.STATE_STANDING.m_id, new Animation(sm.getTeenAnimation(m_gender, MOVEMENT_STATES.STATE_STANDING), 100));
        m_sprites.get(MOVEMENT_STATES.STATE_STANDING.m_id).setPingPong(true);
        
        this.m_sprites.add(MOVEMENT_STATES.STATE_SWIMMING.m_id, new Animation(sm.getTeenAnimation(m_gender, MOVEMENT_STATES.STATE_SWIMMING), 400));
        m_sprites.get(MOVEMENT_STATES.STATE_STANDING.m_id).setPingPong(true);
        
        System.out.println(m_movementState.m_id);
    }
    
    //TEMP:
    int distWalked = 0;
    int distStanding = 400;
    int dir = -1;
    
    //Atualiza o teenager:
    public void update(){
        //Verificando o tipo do tile que estamos:
        if(m_currentTile.getM_type() == Tile.TILE_TYPES.TILE_WATER){
            m_movementState = MOVEMENT_STATES.STATE_SWIMMING;
        }
        else{
            m_movementState = MOVEMENT_STATES.STATE_STANDING;
        }
        
        //Testando movimentos aleatórios:
        if(m_movementState == MOVEMENT_STATES.STATE_STANDING){
            if(distWalked == 0){
                Random rand = new Random();
                dir = rand.nextInt(4);
            }
            if(distWalked < 80){
                switch(dir){
                    //Andando para esquerda:
                    case 0:
                        this.m_position.x -= 2;
                        break;

                    //Andando para direita:
                    case 1:
                        this.m_position.x += 2;
                        break;

                    //Andando para cima:
                    case 2:
                        this.m_position.y -= 2;
                        break;

                    //Andando para baixo:
                    default:
                        this.m_position.y += 2;
                        break;
                }
                distWalked += 2;
            }
            else{
                distStanding -= 5;
                if(distStanding == 0){
                    distWalked = 0;
                    distStanding = 400; 
                }
            }
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
        return m_viewDistance;
    }

    /**
     * @param viewDistance the viewDistance to set
     */
    public void setM_viewDistance(float m_viewDistance) {
        this.m_viewDistance = m_viewDistance;
    }
    
}
