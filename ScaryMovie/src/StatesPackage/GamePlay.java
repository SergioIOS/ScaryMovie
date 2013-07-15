/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StatesPackage;

import CharacterPackage.Killer;
import CharacterPackage.TeenagerManager;
import GuiPackage.BubbleManager;
import MapPackage.Map;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
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
    BubbleManager m_bm = null;
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
        m_bm = new BubbleManager();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        m_map.drawMap(gc, m_camera);
        tm.drawTeenagers(grphcs);
        m_bm.drawBubbles(m_camera);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input temp = gc.getInput();
        
        //Botão direito cria um novo jovem na posição do mouse.
        if(temp.isMousePressed(Input.MOUSE_RIGHT_BUTTON)){
            //Estamos tentando criar um personagem na parede?
            if(!m_map.checkMapColision(new Rectangle(temp.getMouseX() + m_camera.getM_position().x, (temp.getMouseY() + 32) + m_camera.getM_position().y, 32, 32))){
                Vector2f pos = new Vector2f((temp.getMouseX()+ m_camera.getM_position().x), (temp.getMouseY() + m_camera.getM_position().y));
                tm.addTeenager(rm, pos, m_map, m_bm);
                System.out.println("Teenager criado!");
            }
            else{
                System.out.println("Local Proibido para criar personagem! (Colision!)");
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
        
        if(temp.isKeyDown(Input.KEY_LSHIFT)){
            m_bm.setM_shouldDraw(true);
        }
        else{
            m_bm.setM_shouldDraw(false);
        }
        
        //Atualizando os teens:
        tm.updateTeens(m_map);
        
        //Atualizando a camera:
        m_camera.update();
        
        m_bm.updateBubbles();
    }
}
