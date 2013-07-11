/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StatesPackage;

import CharacterPackage.Killer;
import MapPackage.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import scarymovie.Camera;

/**
 *
 * @author CarlosEduardo
 */
public class MapChooser extends BasicGameState{
    //Membros:
    int m_stateID = -1;
    Image m_bg = null;
    Map m_map;
    Camera m_camera;
    
    public MapChooser(int state){
        this.m_stateID = state;
    }
    
    @Override
    public int getID() {
        return m_stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println("MapChooser.");
        
        //Carregando as imagens:
        m_map = new Map();
        m_camera = new Camera();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        m_map.drawMap(gc, m_camera);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input temp = gc.getInput();
        
        if(temp.isKeyPressed(Input.KEY_UP)){
            m_camera.move(Killer.DIRECTIONS.DIR_UP);
        }
        
        if(temp.isKeyPressed(Input.KEY_DOWN)){
            m_camera.move(Killer.DIRECTIONS.DIR_DOWN);
        }
        
        if(temp.isKeyPressed(Input.KEY_LEFT)){
            m_camera.move(Killer.DIRECTIONS.DIR_LEFT);
        }
        
        if(temp.isKeyPressed(Input.KEY_RIGHT)){
            m_camera.move(Killer.DIRECTIONS.DIR_RIGHT);
        }
    }
}
