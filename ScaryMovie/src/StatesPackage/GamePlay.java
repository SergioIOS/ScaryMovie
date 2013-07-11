/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StatesPackage;

import CharacterPackage.Teenager;
import CharacterPackage.TeenagerManager;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
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
    Vector2f posTeen = new Vector2f(150,200);
    
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
        
        rm = new ResourceManager();
        tm = new TeenagerManager();
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        //m_bg.draw();
        tm.drawTeenagers();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input temp = gc.getInput();
        
        if(temp.isKeyPressed(Input.KEY_SPACE)){
            Random rand = new Random();
            float posX = rand.nextInt(gc.getWidth()-32);
            float posY = rand.nextInt(gc.getHeight()-64);
            Vector2f pos = new Vector2f(posX, posY);
            tm.addTeenager(rm,pos);
            System.out.println("Teenager criado!");
        }
    }
}
