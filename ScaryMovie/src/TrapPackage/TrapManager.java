/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrapPackage;

import java.util.ArrayList;

/**
 *
 * @author CarlosEduardo
 */
public class TrapManager {
    //Membros:
    private ArrayList<Trap> m_traps;
    
    //Atualiza todas as Traps:
    public void updateTraps(){
        
    }
    
    //Desenha as Traps:
    public void drawTraps(){
        
    }
    
    //Adiciona Trap:
    public void addTrap(Trap trap){
        this.m_traps.add(trap);
    }
    
    //Remove Trap:
    public void removeTrap(Trap trap){
        this.m_traps.remove(trap);
    }

    /**
     * @return the m_Traps
     */
    public ArrayList<Trap> getM_traps() {
        return m_traps;
    }

    /**
     * @param m_Traps the m_Traps to set
     */
    public void setM_traps(ArrayList<Trap> m_traps) {
        this.m_traps = m_traps;
    }
}
