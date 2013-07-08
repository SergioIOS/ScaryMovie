/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scarymovie;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author CarlosEduardo
 */
public class GameEntity {
    //Membros:
    Vector2f m_position, m_speed;
    Rectangle m_colisionBox, m_targetPosition;
    Animation m_sprite = null;
    
    //Verifica a colisão desta entidade com uma entidade externa:
    public boolean checkColision(Rectangle entityRect){
        
        return true;
    }
    
    //Atualiza a animação do sprite:
    public void updateAnimation(){
        
    }
    
    //Desenha o sprite:
    public void draw(){
        
    }
}
