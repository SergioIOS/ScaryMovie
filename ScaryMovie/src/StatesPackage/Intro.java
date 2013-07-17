/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StatesPackage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import scarymovie.Timer;

/**
 *
 * @author Anderson
 */
public class Intro extends BasicGameState {
    //Membros:
    int m_stateID = -1;
    Image m_bg = null;
    Timer m_clock = null;
    
    public Intro(int state){
        this.m_stateID = state;
    }
    
    @Override
    public int getID() {
        return m_stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //Carregando as iamgens:
        m_bg = new Image("data/IntroBG.png");
        gc.setMouseCursor("data/Cursor.png", 1, 1);
    }
    
    //Esse método é chamado toda vez que entramos no estado.
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        //Precisamos carregar os recursos?
        if(m_bg == null){
            m_bg = new Image("data/IntroBG.png");
        }
        
        if(m_clock == null){
            m_clock = new Timer();
            m_clock.start();
        }
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        //Apagando os recursos:
        m_clock = null;
        m_bg = null;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        m_bg.draw();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        //Verificando no timer se é hora de mudar de estado:
        if(m_clock.getElapsedTimeSecs() >= 3){
            sbg.enterState(scarymovie.ScaryMovie.MAINMENU_STATE, new FadeOutTransition(), new FadeInTransition());
        }
    }
}
