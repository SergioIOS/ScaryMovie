/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrapPackage;

/**
 *
 * @author CarlosEduardo
 */
public class TrapType {
    //Tipos de Traps:
    public enum TRAP_ID{
        TRAP_NUDE_CARD (0),
        TRAP_BEER_BOTTLE (1),
        TRAP_DISCARDED_WOMAN_CLOTHES (2),
        TRAP_HUMAN_BONES (3),
        TRAP_CAT (4);
        
        //O ID de cada objeto:
        public int m_id;
        
        //Cosntrutor:
        private TRAP_ID(int m_id) {
            this.m_id = m_id;
        }
    }
    
    //Membros:
    TRAP_ID m_ID;
    String m_description;
    int m_fearFactor = 0, m_curiosityFactor = 0;
    float m_triggerDistance = 0;
    
    public TrapType(TRAP_ID type, String description, int fearFactor, int curiosityFactor, float triggerDistance){
        
    }
}
