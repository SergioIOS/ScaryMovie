/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StatesPackage;

/**
 *
 * @author Anderson
 */
import CharacterPackage.Killer;
import CharacterPackage.TeenagerManager;
import MapPackage.Map;
import TrapPackage.TrapManager;
import TrapPackage.TrapType;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import scarymovie.Camera;
import scarymovie.ResourceManager;
import scarymovie.ScaryMovie;

/**
 *
 * @author CarlosEduardo
 */
public class PlanningPhaseState extends BasicGameState{
    //Membros:
    int m_stateID = -1;
    
    Map m_map;
    Camera m_camera;
    TeenagerManager tm;
    TrapManager m_trm;
    ResourceManager rm;
    
    public PlanningPhaseState(int state){
        this.m_stateID = state;
    }
    
    @Override
    public int getID() {
        return m_stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        m_trm = new TrapManager();
    }
    
    //Esse método é chamado toda vez que entramos no estado.
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        //Criando as Variáveis:
        m_map = Map.getInstance();
        m_camera = Camera.getInstance(m_map);
        tm = TeenagerManager.getInstance(m_camera);
        rm = ResourceManager.getInstance();
        m_trm = TrapManager.getInstance();
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        //Resetando as variáveis:
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        m_map.drawLowerLayersMap(gc, m_camera);
        m_trm.drawTraps(m_camera);
        tm.drawTeenagers(grphcs);
        m_map.drawUpperLayersMap(gc, m_camera);
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        //Tratando os eventos do teclado e mouse:
        handleEvents(gc, sbg);
    }
    
    private void handleEvents(GameContainer gc, StateBasedGame sbg){
        Input temp = gc.getInput();
        
        if(temp.isKeyPressed(Input.KEY_SPACE)){
            sbg.enterState(ScaryMovie.GAMEPLAY_STATE);
        }
        
        //Botão do meio adiciona uma trap.
        if(temp.isMousePressed(Input.MOUSE_MIDDLE_BUTTON)){
            if(!m_map.checkMapColision(new Rectangle(temp.getMouseX() + m_camera.getM_position().x, (temp.getMouseY() + 32) + m_camera.getM_position().y, 32, 32)) && 
                    !(tm.checkTeenColision(new Rectangle(temp.getMouseX() + m_camera.getM_position().x, (temp.getMouseY() + 32) + m_camera.getM_position().y, 32, 32), null)) &&
                        !m_trm.checkTrapColision(new Rectangle(temp.getMouseX() + m_camera.getM_position().x, (temp.getMouseY() + 32) + m_camera.getM_position().y, 32, 32))){
                Vector2f pos = new Vector2f((temp.getMouseX()+ m_camera.getM_position().x), (temp.getMouseY() + m_camera.getM_position().y));
                m_trm.addStaticTrap(rm, pos, TrapType.TRAP_ID.TRAP_BEER_BOTTLE);
            }
        }
        
        //Movimentos da Câmera:
        if(temp.isKeyDown(Input.KEY_UP)){
            m_camera.move(Killer.DIRECTIONS.DIR_UP);
        }
        
        if(temp.isKeyDown(Input.KEY_DOWN)){
            m_camera.move(Killer.DIRECTIONS.DIR_DOWN);
        }
        
        if(temp.isKeyDown(Input.KEY_LEFT)){
            m_camera.move(Killer.DIRECTIONS.DIR_LEFT);
        }
        
        if(temp.isKeyDown(Input.KEY_RIGHT)){
            m_camera.move(Killer.DIRECTIONS.DIR_RIGHT);
        }
    }
}
