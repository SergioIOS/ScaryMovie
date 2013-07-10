/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrapPackage;

import CharacterPackage.Teenager;
import org.newdawn.slick.geom.Vector2f;
import scarymovie.GameEntity;

/**
 *
 * @author CarlosEduardo
 */
public class MovableTrap extends GameEntity implements Trap{
    //Membros:
    private Teenager.MOVEMENT_STATES m_movementState;
    
    //Contrutor:
    public MovableTrap(Vector2f position, TrapType type){
        
    }
    
    //Atualiza todos os dados da Trap:
    public void update(){
        
    }

    /**
     * @return the m_movementState
     */
    public Teenager.MOVEMENT_STATES getM_movementState() {
        return m_movementState;
    }

    /**
     * @param m_movementState the m_movementState to set
     */
    public void setM_movementState(Teenager.MOVEMENT_STATES m_movementState) {
        this.m_movementState = m_movementState;
    }
            
}
