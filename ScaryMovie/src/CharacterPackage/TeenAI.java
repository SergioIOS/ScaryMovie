/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CharacterPackage;

import static CharacterPackage.Killer.DIRECTIONS.DIR_DOWN;
import static CharacterPackage.Killer.DIRECTIONS.DIR_LEFT;
import static CharacterPackage.Killer.DIRECTIONS.DIR_RIGHT;
import static CharacterPackage.Killer.DIRECTIONS.DIR_STOP;
import static CharacterPackage.Killer.DIRECTIONS.DIR_UP;
import GuiPackage.BubbleManager;
import MapPackage.Map;
import TrapPackage.MovableTrap;
import TrapPackage.StaticTrap;
import TrapPackage.Trap;
import TrapPackage.TrapManager;
import java.util.Random;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import scarymovie.Timer;

/**
 *
 * @author CarlosEduardo
 */
public class TeenAI {
    //Os diferentes tipos de emoções:
    public enum EMOTIONS{
        EMOTION_NO(0),
        EMOTION_YES(1),
        EMOTION_CURIOSITY(2),
        EMOTION_SCARED(3),
        EMOTION_HAPPY(4),
        EMOTION_SAD(5);
        
        public int m_id = -1;

        //O nome de cada emoção (Para exibir na GUI):
	public String m_name;

        private EMOTIONS(int id) {
            this.m_id = id;
	    
            switch(m_id){
                case 0:
                    m_name = "No";
                    break;
                case 1:
                    m_name = "Yes";
                    break;
                case 2:
                    m_name = "Curiosity";
                    break;
                case 3:
                    m_name = "Scared";
                    break;
                case 4:
                    m_name = "Happy";
                    break;
                case 5:
                    m_name = "Sad";
                    break;
            }
		
        }
    }
    
    //As ações do teen:
    public enum ACTIONS{
        ACTION_MOVE(0),
        ACTION_CHASE_STATIC_TRAP(1),
        ACTION_CHASE_MOVABLE_TRAP(2),
        ACTION_IDLE(3),
        ACTION_PANIC(4);
        
        public int m_id = -1;

        private ACTIONS(int id) {
            this.m_id = id;
        }
    }
    
    //Membros:
    private int m_fear = 0;
    private int m_curiosity = 0;
    private float m_viewDistance = 0;
    private EMOTIONS m_curentEmotion;
    private ACTIONS m_curentAction;
    private Teenager m_teen;
    private Killer m_killer;
    private Random m_rand;
    private Timer m_timer;
    private StaticTrap m_desiredStaticTrap;
    private MovableTrap m_desiredMovableTrap;
    private boolean m_isBusy = false;
    private boolean gotData = false;
    
    //TEMP:
    private int distWalked = 0;
    private int dir = -1;
    
    //Construtor:
    public TeenAI(Teenager teen){
        this.m_viewDistance = 300;
        this.m_teen = teen;
        this.m_rand = new Random();
        this.m_timer = new Timer();
    }
    
    //Atualiza o Teenager:
    public void updateLogic(Map map, TeenagerManager tm, TrapManager trm){
//        //Verificar se o teen está vendo o killer:
//        if(m_killer.isM_spawned() == true){
//            float distance = m_teen.getM_position().distance(m_killer.getM_position());
//            if(distance <= this.m_viewDistance){
//                this.m_fear = 100;
//                m_isBusy = true;
//                this.m_curentAction = ACTIONS.ACTION_PANIC;
//            }
//        }

        if(m_isBusy == false){
            //Verificar se tem alguma trap por perto
            m_desiredMovableTrap = trm.checkMovableTrapTeenDistance(m_teen);
            if(m_desiredMovableTrap != null){
                m_isBusy = true;
                m_curentAction = ACTIONS.ACTION_CHASE_MOVABLE_TRAP;
            }
            else{
                m_desiredStaticTrap = trm.checkStaticTrapTeenDistance(m_teen);
                if(m_desiredStaticTrap != null){
                    m_isBusy = true;
                    m_curentAction = ACTIONS.ACTION_CHASE_STATIC_TRAP;
                }
            }
        }

        if(m_isBusy == false){
            boolean decision = m_rand.nextBoolean();
            if(decision == true){
                m_isBusy = true;
                m_curentAction = ACTIONS.ACTION_MOVE;
            }
            else{
                if(m_timer.isRunning() == false){
                    m_isBusy = true;
                    m_curentAction = ACTIONS.ACTION_IDLE;
                    m_timer.start();
                }
            }
        }
        
        processActions(map, tm, trm);
    }
    
    public void processActions(Map map, TeenagerManager tm, TrapManager trm){
        switch(m_curentAction){
            case ACTION_PANIC:
                //Fazer ele correr freneticamente e procurar a saída.
                evadeFromKiller();
                break;
            case ACTION_CHASE_STATIC_TRAP:
                //Fazer ele andar em direção à trap passada.
                chaseStaticTrap(trm);
                break;
            case ACTION_CHASE_MOVABLE_TRAP:
                //Fazer ele andar em direção à trap passada.
                break;
            case ACTION_MOVE:
                moveRandomly(map, tm, trm);
                break;
            case ACTION_IDLE:
                if(m_timer.getElapsedTimeSecs() >= 2){
                    m_timer.reset();
                    m_isBusy = false;
                }
                break;
            default:
                break;
        }
        
    }
    
    
    //Primeiramente, só vai correr em direção oposta ao killer:
    public void evadeFromKiller(){
        if(m_teen.getM_position().x < m_killer.getM_position().x){
            move(Killer.DIRECTIONS.DIR_LEFT, 2);
        }
        else if(m_teen.getM_position().x > m_killer.getM_position().x){
            move(Killer.DIRECTIONS.DIR_RIGHT, 2);
        }
        
        if(m_teen.getM_position().y+32 < m_killer.getM_position().y){
            move(Killer.DIRECTIONS.DIR_UP, 2);
        }
        else if(m_teen.getM_position().y+32 > m_killer.getM_position().y){
            move(Killer.DIRECTIONS.DIR_DOWN, 2);
        }
        
        getM_teen().getM_position().add(getM_teen().getM_speed());
        
    }
    
    //Algoritmo simples temporário de busca das traps:
    public void chaseStaticTrap(TrapManager trm){
        if(m_teen.getM_position().x < m_desiredStaticTrap.getM_position().x){
            move(Killer.DIRECTIONS.DIR_RIGHT, 1);
        }
        else if(m_teen.getM_position().x > m_desiredStaticTrap.getM_position().x){
            move(Killer.DIRECTIONS.DIR_LEFT, 1);
        }
        
        if(m_teen.getM_position().y+32 < m_desiredStaticTrap.getM_position().y){
            move(Killer.DIRECTIONS.DIR_DOWN, 1);
        }
        else if(m_teen.getM_position().y+32 > m_desiredStaticTrap.getM_position().y){
            move(Killer.DIRECTIONS.DIR_UP, 1);
        }
        
        getM_teen().getM_position().add(getM_teen().getM_speed());
        
        //A trap foi ativada?
        if((m_teen.getM_position().distance(m_desiredStaticTrap.getM_position())<= m_desiredStaticTrap.getM_type().getM_triggerDistance()) && gotData == false){
            //Atualizar dados do teen
            m_fear += m_desiredStaticTrap.getM_type().getM_fearFactor();
            m_curiosity += m_desiredStaticTrap.getM_type().getM_curiosityFactor();
            gotData = true;
        }
        
        //Colidiu com a trap?
        if(m_teen.checkColision(new Rectangle(m_desiredStaticTrap.getM_position().x, m_desiredStaticTrap.getM_position().y, 32, 32))){
            trm.removeStaticTrap(m_desiredStaticTrap);
            m_isBusy = false;
            gotData = false;
            m_teen.setM_movementState(Teenager.MOVEMENT_STATES.STATE_STANDING);
        }
    }
    
    public void moveRandomly(Map map, TeenagerManager tm, TrapManager trm){
        //Testando movimentos aleatórios:
        //if(m_movementState != MOVEMENT_STATES.STATE_STANDING){
        if(getDistWalked() == 0){
            Random rand = new Random();
            setDir(rand.nextInt(4));
        }
        if(getDistWalked() < 80){
            switch(getDir()){
                //Andando para esquerda:
                case 0:
                    if(getM_teen().getM_position().x - 2 >= 0){
                        move(Killer.DIRECTIONS.DIR_LEFT, 2);
                    }
                    else{
                        setDistWalked(800);
                    }
                    break;

                //Andando para direita:
                case 1:
                    if(getM_teen().getM_position().x + 2 <= map.getM_mapSizeW() - 32){
                        move(Killer.DIRECTIONS.DIR_RIGHT, 2);
                    }
                    else{
                        setDistWalked(800);
                    }
                    break;

                //Andando para cima:
                case 2:
                    if(getM_teen().getM_position().y - 2 >= 0){
                        move(Killer.DIRECTIONS.DIR_UP, 2);
                    }
                    else{
                        setDistWalked(800);
                    }
                    break;

                //Andando para baixo:
                default:
                    if(getM_teen().getM_position().y + 2 <= map.getM_mapSizeH() - 64){
                        move(Killer.DIRECTIONS.DIR_DOWN, 2);
                    }
                    else{
                        setDistWalked(800);
                    }
                    break;
            }

            //Atualizando a posição desejada:
            getM_teen().getM_position().add(getM_teen().getM_speed());

            //Colidimos com algo?
            if(map.checkMapColision(new Rectangle(getM_teen().getM_position().x, getM_teen().getM_position().y + 32, 32, 32)) 
                    || tm.checkTeenColision(new Rectangle(getM_teen().getM_position().x, getM_teen().getM_position().y + 32, 32, 32), getM_teen())){
                //Colidimos! Voltando para trás!
                getM_teen().getM_position().sub(getM_teen().getM_speed());
                setDistWalked(0);
                move(Killer.DIRECTIONS.DIR_STOP, 2);
            }
            else{
                //Sem colisão! Continuando o movimento...
                setDistWalked(getDistWalked() + 2);

                //Atualizando as colision boxes:
                getM_teen().getM_colisionBox().setLocation(getM_teen().getM_position().x, getM_teen().getM_position().y + 32);
            }
        }
        else{
            move(Killer.DIRECTIONS.DIR_STOP, 2);
            setDistWalked(0);
            m_isBusy = false;
        }
    }
    
    public void move(CharacterPackage.Killer.DIRECTIONS direction, int speedFactor){
        getM_teen().setM_speed(new Vector2f(0,0));
        
        switch(direction){
            case DIR_LEFT:
                getM_teen().setM_movementState(Teenager.MOVEMENT_STATES.STATE_WALKING_LEFT);
                m_teen.getM_speed().x -= speedFactor;
                break;
            case DIR_RIGHT:
                getM_teen().setM_movementState(Teenager.MOVEMENT_STATES.STATE_WALKING_RIGHT);
                m_teen.getM_speed().x += speedFactor;
                break;
            case DIR_UP:
                getM_teen().setM_movementState(Teenager.MOVEMENT_STATES.STATE_WALKING_UP);
                m_teen.getM_speed().y -= speedFactor;
                break;
            case DIR_DOWN:
                getM_teen().setM_movementState(Teenager.MOVEMENT_STATES.STATE_WALKING_DOWN);
                m_teen.getM_speed().y += speedFactor;
                break;
            case DIR_STOP:
                m_teen.getM_speed().x = 0;
                m_teen.getM_speed().y = 0;
                
                getM_teen().setM_movementState(Teenager.MOVEMENT_STATES.STATE_STANDING);
                break;
            default:
                throw new AssertionError(direction.name());
        }
    }
    
    //Função que processa as emoções e randomiza as mesmas:
    public void updateEmotions(Teenager teen, BubbleManager bm){
        Random temp = new Random();
        
        //Decidindo a emoção atual:
        int tempID = temp.nextInt(6);
        
        switch(tempID){
            case 0:
                setM_curentEmotion(EMOTIONS.EMOTION_NO);
                break;
            case 1:
                setM_curentEmotion(EMOTIONS.EMOTION_YES);
                break;
            case 2:
                setM_curentEmotion(EMOTIONS.EMOTION_CURIOSITY);
                break;
            case 3:
                setM_curentEmotion(EMOTIONS.EMOTION_SCARED);
                break;
            case 4:
                setM_curentEmotion(EMOTIONS.EMOTION_HAPPY);
                break;
            case 5:
                setM_curentEmotion(EMOTIONS.EMOTION_SAD);
                break;
            default:
                throw new AssertionError(getM_curentEmotion().name());
        }
        
        //Adicionando a bolha:
        bm.addBubble(teen);
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
     * @return the m_viewDistance
     */
    public float getM_viewDistance() {
        return m_viewDistance;
    }

    /**
     * @param m_viewDistance the m_viewDistance to set
     */
    public void setM_viewDistance(float m_viewDistance) {
        this.m_viewDistance = m_viewDistance;
    }

    /**
     * @return the m_curentEmotion
     */
    public EMOTIONS getM_curentEmotion() {
        return m_curentEmotion;
    }

    /**
     * @param m_curentEmotion the m_curentEmotion to set
     */
    public void setM_curentEmotion(EMOTIONS m_curentEmotion) {
        this.m_curentEmotion = m_curentEmotion;
    }

    /**
     * @return the m_curentAction
     */
    public ACTIONS getM_curentAction() {
        return m_curentAction;
    }

    /**
     * @param m_curentAction the m_curentAction to set
     */
    public void setM_curentAction(ACTIONS m_curentAction) {
        this.m_curentAction = m_curentAction;
    }

    /**
     * @return the distWalked
     */
    public int getDistWalked() {
        return distWalked;
    }

    /**
     * @param distWalked the distWalked to set
     */
    public void setDistWalked(int distWalked) {
        this.distWalked = distWalked;
    }

    /**
     * @return the dir
     */
    public int getDir() {
        return dir;
    }

    /**
     * @param dir the dir to set
     */
    public void setDir(int dir) {
        this.dir = dir;
    }

    /**
     * @return the m_teen
     */
    public Teenager getM_teen() {
        return m_teen;
    }

    /**
     * @param m_teen the m_teen to set
     */
    public void setM_teen(Teenager m_teen) {
        this.m_teen = m_teen;
    }

    /**
     * @return the m_killer
     */
    public Killer getM_killer() {
        return m_killer;
    }

    /**
     * @param m_killer the m_killer to set
     */
    public void setM_killer(Killer m_killer) {
        this.m_killer = m_killer;
    }
}
