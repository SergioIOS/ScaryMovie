/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StatesPackage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Anderson
 */
public class MainMenu extends BasicGameState{
    //Membros:
    int m_stateID = -1, m_selectedBtn = -1;
    Image m_bg = null, m_arrow = null;
    float m_arrowX, m_arrowY;
    Rectangle m_btnNewGame, m_btnLoadGame, m_btnHighScores, m_btnOptions, m_btnExit;
    
    public MainMenu(int state){
        this.m_stateID = state;
    }
    
    @Override
    public int getID() {
        return m_stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println("MainMenu.");
        
        //Carregando as imagens:
        m_bg = new Image("data/MainMenuBG.png");
        m_arrow = new Image("data/Arrow.png");
        
        //Criando os rects dos botões:
        m_btnNewGame = new Rectangle(530, 185, 200, 45);
        m_btnLoadGame = new Rectangle(455, 255, 265, 45);
        m_btnHighScores = new Rectangle(515, 325, 225, 45);
        m_btnOptions = new Rectangle(555, 395, 140, 45);
        m_btnExit = new Rectangle(575, 465, 105, 45);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        m_bg.draw();
        m_arrow.draw(m_arrowX, m_arrowY);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input temp = gc.getInput();
        
        if(temp.isMousePressed(Input.MOUSE_LEFT_BUTTON) && m_selectedBtn >= 0){
            switch(m_selectedBtn){
                case 0:
                    System.out.println("Novo Jogo!");
                    sbg.enterState(scarymovie.ScaryMovie.GAMEPLAY_STATE);
                break;
                    
                case 1:
                    System.out.println("Carregar Jogo!");
                break;
                    
                case 2:
                    System.out.println("ScoreBoard!");
                break;
                    
                case 3:
                    System.out.println("Opções!");
                break;
                    
                case 4:
                    System.out.println("Sair!!");
                break;
            }
        }
        
        //Atualizanod a posição da flecha:
        float mouseX = temp.getMouseX();
        float mouseY = temp.getMouseY();
        
        if(m_btnNewGame.contains(mouseX, mouseY)){
            m_arrowX = 420;
            m_arrowY = 195;
            m_selectedBtn = 0;
        }else if(m_btnLoadGame.contains(mouseX, mouseY)){
            m_arrowX = 395;
            m_arrowY = 265;
            m_selectedBtn = 1;
        }else if(m_btnHighScores.contains(mouseX, mouseY)){
            m_arrowX = 420;
            m_arrowY = 325;
            m_selectedBtn = 2;
        }else if(m_btnOptions.contains(mouseX, mouseY)){
            m_arrowX = 475;
            m_arrowY = 400;
            m_selectedBtn = 3;
        }else if(m_btnExit.contains(mouseX, mouseY)){
            m_arrowX = 485;
            m_arrowY = 465;
            m_selectedBtn = 4;
        }
        else{
            m_arrowX = 850;
            m_arrowY = 650;
            m_selectedBtn = -1;
        }
    }
}
