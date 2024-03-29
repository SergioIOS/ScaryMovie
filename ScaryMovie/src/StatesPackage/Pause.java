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

/**
 *
 * @author CarlosEduardo
 */
public class Pause extends BasicGameState{
    //Membros:
    int m_stateID = -1;
    Image m_bg = null;
    
    public Pause(int state){
        this.m_stateID = state;
    }
    
    @Override
    public int getID() {
        return m_stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println("Pause.");
        
        //Carregando as imagens:
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        m_bg.draw();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
    }
}
