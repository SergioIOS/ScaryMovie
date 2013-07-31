/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StatesPackage;

import CharacterPackage.Killer;
import CharacterPackage.TeenagerManager;
import GuiPackage.Gui;
import MapPackage.Map;
import TrapPackage.TrapManager;
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
import scarymovie.Timer;

/**
 *
 * @author CarlosEduardo
 */
public class GamePlay extends BasicGameState{
    //Membros:
    int m_stateID = -1;
    ResourceManager rm = null;
    TeenagerManager tm = null;
    TrapManager m_trm = null;
    Camera m_camera = null;
    Map m_map = null;
    Killer m_killer = null;
    Gui m_gui = null;
    Timer m_spawnTimer = null;
    
    
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
        m_map = Map.getInstance("apartment");
        m_camera = Camera.getInstance(null);
        rm = ResourceManager.getInstance();
        tm = TeenagerManager.getInstance(null);
        m_trm = TrapManager.getInstance();
        m_killer = Killer.getInstance(null, null);
        
        //Travando a câmera no killer:
        m_camera.lockOnKiller(m_killer);
        
        //exibindo o aviso:
        m_gui = Gui.getInstance();
        
        m_gui.showScreenWarning(Gui.WARNING_TYPES.WARNING_HUNTING);
        
        m_spawnTimer = new Timer();
        
        m_spawnTimer.start();
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        //Resetando as variáveis:
        m_spawnTimer.reset();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        //Renderizando as layers baixas do mapa:
        m_map.drawLowerLayersMap(gc, m_camera);
        
        //Renderizando os atores:
        m_trm.drawTraps(m_camera);
        tm.drawTeenagers(grphcs);
        m_killer.draw(m_camera);
        
        //Por fim, renderizando as layers superiores do mapa:
        m_map.drawUpperLayersMap(gc, m_camera);
        
        //Desenhando a GUI:
        m_gui.drawHuntingGui();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        //Tratando os eventos do teclado e mouse:
        handleEvents(gc, sbg);
        
        //Atualizando a camera:
        m_camera.update();
        
        //Atualizando os teens:
        tm.updateTeens(m_map, m_trm);
        
        //Atualizando o killer:
        m_killer.update(m_map);
        
        //Atualizando as traps:
        m_trm.updateTraps();
        
        //Atualizando a GUI:
        m_gui.update(m_killer, m_map, tm, m_trm);
        
        //O killer entrou em algum spawn point?
        if(m_killer.getcurrentTile().isM_spawn() && m_spawnTimer.getElapsedTimeSecs() > 5){
            sbg.enterState(scarymovie.ScaryMovie.PLANNINGPHASE_STATE);
        }
    }
    
    private void handleEvents(GameContainer gc, StateBasedGame sbg){
        Input temp = gc.getInput();
        
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
        
        //ESC volta para o menu:
        if(temp.isKeyPressed(Input.KEY_ESCAPE)){
            sbg.enterState(scarymovie.ScaryMovie.MAINMENU_STATE, new FadeOutTransition(), new FadeInTransition());
        }
    }
}
