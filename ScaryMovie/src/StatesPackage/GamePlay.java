/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StatesPackage;

import CharacterPackage.Killer;
import CharacterPackage.TeenagerManager;
import GuiPackage.BubbleManager;
import MapPackage.Map;
import TrapPackage.TrapManager;
import TrapPackage.TrapType;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import scarymovie.Camera;
import scarymovie.ResourceManager;

/**
 *
 * @author CarlosEduardo
 */
public class GamePlay extends BasicGameState{
    //Membros:
    int m_stateID = -1;
    Image m_bg = null;
    ResourceManager rm = null;
    TeenagerManager tm = null;
    BubbleManager m_bm = null;
    TrapManager m_trm = null;
    Camera m_camera = null;
    Map m_map = null;
    Killer m_killer = null;
    
    public GamePlay(int state){
        this.m_stateID = state;
    }
    
    @Override
    public int getID() {
        return m_stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
    }
    
    //Esse método é chamado toda vez que entramos no estado.
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        //Criando as Variáveis:
        m_map = Map.getInstance();
        m_camera = Camera.getInstance(m_map);
        rm = ResourceManager.getInstance();
        tm = TeenagerManager.getInstance(m_camera);
        m_trm = TrapManager.getInstance();
        m_bm = new BubbleManager();
        m_killer = new Killer(new Vector2f(m_camera.getM_position().x + 384, m_camera.getM_position().y + 284), rm);
        
        m_camera.lockOnKiller(m_killer);
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        //Resetando as variáveis:
        m_map = null;
        m_camera = null;
        m_bm = null;
        m_killer = null;
        m_trm = null; 
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        m_map.drawMap(gc, m_camera);
        tm.drawTeenagers(grphcs);
        m_killer.draw(m_camera);
        m_bm.drawBubbles(m_camera);
        m_trm.drawTraps(m_camera);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        //Tratando os eventos do teclado e mouse:
        handleEvents(gc, sbg);
        
        //Atualizando a camera:
        m_camera.update();
        
        //Atualizando os teens:
        tm.updateTeens(m_map);
        
        //Atualizando o killer:
        m_killer.update(m_map);
        
        //Atualizando as bolhas:
        m_bm.updateBubbles();
        
        //Atualizando as traps:
        m_trm.updateTraps();
    }
    
    private void handleEvents(GameContainer gc, StateBasedGame sbg){
        Input temp = gc.getInput();
        
        //Botão direito cria um novo jovem na posição do mouse.
        if(temp.isMousePressed(Input.MOUSE_RIGHT_BUTTON)){
            //Estamos tentando criar um personagem na parede?
            if(!m_map.checkMapColision(new Rectangle(temp.getMouseX() + m_camera.getM_position().x, (temp.getMouseY() + 32) + m_camera.getM_position().y, 32, 32)) && 
                    !(tm.checkTeenColision(new Rectangle(temp.getMouseX() + m_camera.getM_position().x, (temp.getMouseY() + 32) + m_camera.getM_position().y, 32, 32), null))){
                Vector2f pos = new Vector2f((temp.getMouseX()+ m_camera.getM_position().x), (temp.getMouseY() + m_camera.getM_position().y));
                tm.addTeenager(rm, pos, m_map, m_bm);
            }
        }
        
//        //Botão do meio adiciona uma trap.
//        if(temp.isMousePressed(Input.MOUSE_MIDDLE_BUTTON)){
//            if(!m_map.checkMapColision(new Rectangle(temp.getMouseX() + m_camera.getM_position().x, (temp.getMouseY() + 32) + m_camera.getM_position().y, 32, 32)) && 
//                    !(tm.checkTeenColision(new Rectangle(temp.getMouseX() + m_camera.getM_position().x, (temp.getMouseY() + 32) + m_camera.getM_position().y, 32, 32), null))){
//                Vector2f pos = new Vector2f((temp.getMouseX()+ m_camera.getM_position().x), (temp.getMouseY() + m_camera.getM_position().y));
//                m_trm.addStaticTrap(rm, pos, TrapType.TRAP_ID.TRAP_BEER_BOTTLE);
//            }
//        }
        
        if(temp.isKeyPressed(Input.KEY_SPACE)){
            sbg.enterState(scarymovie.ScaryMovie.PLANNINGPHASE_STATE);
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
        
        //Movimentos do killer:
        if(temp.isKeyDown(Input.KEY_UP)){
            m_killer.move(Killer.DIRECTIONS.DIR_UP);
        }
        else if(temp.isKeyDown(Input.KEY_DOWN)){
            m_killer.move(Killer.DIRECTIONS.DIR_DOWN);
        }
        else if(temp.isKeyDown(Input.KEY_LEFT)){
            m_killer.move(Killer.DIRECTIONS.DIR_LEFT);
        }
        else if(temp.isKeyDown(Input.KEY_RIGHT)){
            m_killer.move(Killer.DIRECTIONS.DIR_RIGHT);
        }
        else{
            //Estamos parados:
            m_killer.move(Killer.DIRECTIONS.DIR_STOP);
        }
        
        //Desenhamos as bubbles?
        if(temp.isKeyDown(Input.KEY_LSHIFT)){
            m_bm.setM_shouldDraw(true);
        }
        else{
            m_bm.setM_shouldDraw(false);
        }
        
        //ESC volta para o menu:
        if(temp.isKeyPressed(Input.KEY_ESCAPE)){
            sbg.enterState(scarymovie.ScaryMovie.MAINMENU_STATE, new FadeOutTransition(), new FadeInTransition());
        }
    }
}
