/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiPackage;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import scarymovie.Camera;
import scarymovie.Timer;

/**
 *
 * @author Anderson
 */
public class InfoBubble {
    //Membros:
    Image m_sprite = null;
    Vector2f m_position = null;
    Timer m_timer = null;
    boolean m_readyToGo = false;
    
    //Construtor:
    public InfoBubble(Image sprite, Vector2f position) {
        //Salvando os atributos:
        m_sprite = sprite;
        m_position = new Vector2f(position);
        
        //Arrumando a posição:
        m_position.x += 48;
        
        //Criando e inicializando o timer:
        m_timer = new Timer();
        m_timer.start();
    }
    
    public void draw(){
        m_sprite.draw(m_position.x - Camera.getInstance(null).getM_position().x, m_position.y - Camera.getInstance(null).getM_position().y);
    }
    
    public void update(){
        //Subindo...
        m_position.y -= 0.5;
        
        //É hora de morrer?
        if(m_timer.getElapsedTimeSecs() >= 2){
            m_readyToGo = true;
        }
    }
    
    boolean getReadyToGo(){
        return m_readyToGo;
    }
}
