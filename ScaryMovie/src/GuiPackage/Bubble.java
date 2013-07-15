/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiPackage;

import CharacterPackage.Teenager;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Anderson
 */
public class Bubble {
    //Membros:
    Image m_sprite;
    Vector2f m_position;
    Teenager m_teen = null;

    //Construtor:
    public Bubble(Image m_sprite, Teenager teen) {
        this.m_sprite = m_sprite;
        this.m_teen = teen;
        this.m_position = new Vector2f(m_teen.getM_position());
        
        //Corrigindo a posição:
        m_position.x += 32;
        m_position.y += 16;
    }
    
    public void draw(Vector2f camPosition){
        m_sprite.draw(m_position.x - camPosition.x, m_position.y - camPosition.y);
    }
    
    public void update(){
        m_position.set(m_teen.getM_position());
        m_position.x += 32;
        m_position.y -= 16;
    }
}
