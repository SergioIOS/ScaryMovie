/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CharacterPackage;

import GuiPackage.BubbleManager;
import MapPackage.Map;
import MapPackage.Tile;
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

    /**
     * @return the m_curentEmotion
     */
    public BubbleManager.EMOTIONS getM_curentEmotion() {
        return m_curentEmotion;
    }

    /**
     * @param m_curentEmotion the m_curentEmotion to set
     */
    public void setM_curentEmotion(BubbleManager.EMOTIONS m_curentEmotion) {
        this.m_curentEmotion = m_curentEmotion;
    }
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
    private BubbleManager.EMOTIONS m_curentEmotion;
    
    //Construtor:
    public Teenager(ResourceManager sm, Vector2f position, Tile tile){
        //Salvando valores:
        m_currentTile = tile;
        m_viewDistance = 150;
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
        
        this.m_sprites.add(MOVEMENT_STATES.STATE_STANDING.m_id, new Animation(sm.getTeenAnimation(m_gender, MOVEMENT_STATES.STATE_STANDING), 200));
        this.m_sprites.add(MOVEMENT_STATES.STATE_SWIMMING.m_id, new Animation(sm.getTeenAnimation(m_gender, MOVEMENT_STATES.STATE_SWIMMING), 200));
        this.m_sprites.add(MOVEMENT_STATES.STATE_WALKING_LEFT.m_id, new Animation(sm.getTeenAnimation(m_gender, MOVEMENT_STATES.STATE_WALKING_LEFT), 200));
        this.m_sprites.add(MOVEMENT_STATES.STATE_WALKING_RIGHT.m_id, new Animation(sm.getTeenAnimation(m_gender, MOVEMENT_STATES.STATE_WALKING_RIGHT), 200));
        this.m_sprites.add(MOVEMENT_STATES.STATE_WALKING_UP.m_id, new Animation(sm.getTeenAnimation(m_gender, MOVEMENT_STATES.STATE_WALKING_UP), 200));
        this.m_sprites.add(MOVEMENT_STATES.STATE_WALKING_DOWN.m_id, new Animation(sm.getTeenAnimation(m_gender, MOVEMENT_STATES.STATE_WALKING_DOWN), 200));
    }
    
    //TEMP:
    int distWalked = 0;
    int timeStanding = 400;
    int dir = -1;
    
    //Atualiza o teenager:
    public void update(Map map, TeenagerManager tm, TrapManager trm){
        //Testando movimentos aleatórios:
        //if(m_movementState != MOVEMENT_STATES.STATE_STANDING){
            if(distWalked == 0){
                Random rand = new Random();
                dir = rand.nextInt(4);
            }
            if(distWalked < 80){
                switch(dir){
                    //Andando para esquerda:
                    case 0:
                        if(this.m_position.x - 2 >= 0){
                            move(Killer.DIRECTIONS.DIR_LEFT);
                        }
                        else{
                            distWalked = 800;
                        }
                        break;

                    //Andando para direita:
                    case 1:
                        if(this.m_position.x + 2 <= map.getM_mapSizeW() - 32){
                            move(Killer.DIRECTIONS.DIR_RIGHT);
                        }
                        else{
                            distWalked = 800;
                        }
                        break;

                    //Andando para cima:
                    case 2:
                        if(this.m_position.y - 2 >= 0){
                            move(Killer.DIRECTIONS.DIR_UP);
                        }
                        else{
                            distWalked = 800;
                        }
                        break;

                    //Andando para baixo:
                    default:
                        if(this.m_position.y + 2 <= map.getM_mapSizeH() - 64){
                            move(Killer.DIRECTIONS.DIR_DOWN);
                        }
                        else{
                            distWalked = 800;
                        }
                        break;
                }
                
                //Atualizando a posição desejada:
                m_position.add(m_speed);
                
                //Colidimos com algo?
                if(map.checkMapColision(new Rectangle(m_position.x, m_position.y + 32, 32, 32)) 
                        || tm.checkTeenColision(new Rectangle(m_position.x, m_position.y + 32, 32, 32), this)){
                    //Colidimos! Voltando para trás!
                    m_position.sub(m_speed);
                    distWalked = 0;
                    timeStanding = 400;
                    move(Killer.DIRECTIONS.DIR_STOP);
                }
                else{
                    //Sem colisão! Continuando o movimento...
                    distWalked += 2;
                    
                    //Atualizando as colision boxes:
                    m_colisionBox.setLocation(m_position.x, m_position.y + 32);
                }
            }
            else{
                move(Killer.DIRECTIONS.DIR_STOP);
                timeStanding -= 5;
                if(timeStanding == 0){
                    distWalked = 0;
                    timeStanding = 400; 
                }
            }
            
            //Checando colisão com as traps:
            if(trm.checkTrapTeenColision(this)){
                System.out.println("Teen pegou trap! Curiosity: " + this.m_curiosity + " - Fear: " + this.m_fear);
            }
        //}
    }
    
    //Função que processa as emoções e randomiza as mesmas:
    public void updateEmotions(BubbleManager bm){
        Random temp = new Random();
        
        //Decidindo a emoção atual:
        int tempID = temp.nextInt(6);
        
        switch(tempID){
            case 0:
                m_curentEmotion = BubbleManager.EMOTIONS.EMOTION_NO;
                break;
            case 1:
                m_curentEmotion = BubbleManager.EMOTIONS.EMOTION_YES;
                break;
            case 2:
                m_curentEmotion = BubbleManager.EMOTIONS.EMOTION_CURIOSITY;
                break;
            case 3:
                m_curentEmotion = BubbleManager.EMOTIONS.EMOTION_SCARED;
                break;
            case 4:
                m_curentEmotion = BubbleManager.EMOTIONS.EMOTION_HAPPY;
                break;
            case 5:
                m_curentEmotion = BubbleManager.EMOTIONS.EMOTION_SAD;
                break;
            default:
                throw new AssertionError(getM_curentEmotion().name());
        }
        
        //Adicionando a bolha:
        bm.addBubble(this);
    }
    
    public void move(CharacterPackage.Killer.DIRECTIONS direction){
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
