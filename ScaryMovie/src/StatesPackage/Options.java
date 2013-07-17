/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StatesPackage;

import GuiPackage.CheckButton;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author CarlosEduardo
 */
public class Options extends BasicGameState{
    //Membros:
    int m_stateID = -1;
    Image m_bg = null;
    SpriteSheet m_toggleBtn = null;
    ArrayList<CheckButton> m_buttonList;
    
    public Options(int state){
        this.m_stateID = state;
    }
    
    @Override
    public int getID() {
        return m_stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        //Precisamos carregar os recursos?
        if(m_bg == null){
            m_bg = new Image("data/OptionsBG.png");
        }
        
        if(m_toggleBtn == null){
            m_toggleBtn = new SpriteSheet("data/ToggleBtn.png", 32, 32);
        }
        
        if(m_buttonList == null){
            m_buttonList = new ArrayList<>();
            
            //Criando os botões:
            m_buttonList.add(new CheckButton(new Rectangle(195, 165, 32, 32), container.isFullscreen(), "fullscreen"));
            m_buttonList.add(new CheckButton(new Rectangle(195, 230, 32, 32), container.isVSyncRequested(), "framesync"));
        }
        
        
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        //Apagando os recursos:
        m_bg = null;
        m_toggleBtn = null;
        m_buttonList.clear();
        m_buttonList = null;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        m_bg.draw();
        
        for(CheckButton btn : m_buttonList){
            m_toggleBtn.getSprite(0, 0).draw(btn.getX(), btn.getY());
            
            //Checked?
            if(btn.isM_toggled()){
                m_toggleBtn.getSprite(1, 0).draw(btn.getX(), btn.getY());
            }
            else{
                m_toggleBtn.getSprite(2, 0).draw(btn.getX(), btn.getY());
            }
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input temp = gc.getInput();
        
        if(temp.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
            //Verificando se apertamos em algum botão:
            for(CheckButton btn : m_buttonList){
                if(btn.checkClick(temp.getMouseX(), temp.getMouseY())){
                    btn.toggleState();
                    
                    if("fullscreen".equals(btn.getM_id())){
                        //Mudando a janela do jogo:
                        gc.setFullscreen(!gc.isFullscreen());
                    }
                    else if("framesync".equals(btn.getM_id())){
                        //Mudando a vsync:
                        gc.setVSync(!gc.isVSyncRequested());
                    }
                }
            }
        }
        
        if(temp.isKeyPressed(Input.KEY_ESCAPE)){
            sbg.enterState(scarymovie.ScaryMovie.MAINMENU_STATE, new FadeOutTransition(), new FadeInTransition());
        }
    }
}
