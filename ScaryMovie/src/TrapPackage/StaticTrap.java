/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrapPackage;

import org.newdawn.slick.geom.Vector2f;
import scarymovie.GameEntity;

/**
 *
 * @author CarlosEduardo
 */
public class StaticTrap extends GameEntity implements Trap{
    
    //Construtor:
    public StaticTrap(Vector2f position, TrapType type){
        this.m_position = position;
        
        //Tratativa para o tipo
    }
}
