/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StatesPackage;

/**
 *
 * @author Anderson
 */
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author CarlosEduardo
 */
public class PlanningPhaseState extends BasicGameState{
    //Membros:
    int m_stateID = -1;
    
    public PlanningPhaseState(int state){
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
        
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        //Resetando as variáveis:
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        //Tratando os eventos do teclado e mouse:
        handleEvents(gc, sbg);
    }
    
    private void handleEvents(GameContainer gc, StateBasedGame sbg){
        Input temp = gc.getInput();
        
        
    }
}
