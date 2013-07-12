/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StatesPackage;

import CharacterPackage.Killer;
import CharacterPackage.TeenagerManager;
import MapPackage.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
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
    Camera m_camera = null;
    Map m_map = null;
    
    public GamePlay(int state){
        this.m_stateID = state;
    }
    
    @Override
    public int getID() {
        return m_stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println("GamePlay.");
        
        m_map = new Map();
        m_camera = new Camera(m_map);
        rm = new ResourceManager();
        tm = new TeenagerManager(m_camera);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        m_map.drawMap(gc, m_camera);
        tm.drawTeenagers();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input temp = gc.getInput();
        
//        if(temp.isKeyPressed(Input.KEY_SPACE)){
//            Random rand = new Random();
//            float posX = rand.nextInt(gc.getWidth()-32);
//            float posY = rand.nextInt(gc.getHeight()-64);
//            Vector2f pos = new Vector2f(posX, posY);
//            tm.addTeenager(rm,pos);
//            System.out.println("Teenager criado!");
//        }
        
        //Botão direito cria um novo jovem na posição do mouse.
        if(temp.isMousePressed(Input.MOUSE_RIGHT_BUTTON)){
            Vector2f pos = new Vector2f((temp.getMouseX()+ m_camera.getM_position().x) - 16, (temp.getMouseY() + m_camera.getM_position().y) - 32);
            tm.addTeenager(rm, pos, m_map);
            System.out.println("Teenager criado!");
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
        
        //Atualizando os teens:
        tm.updateTeens();
        
        //Atualizando a camera:
        m_camera.update();
    }
}
