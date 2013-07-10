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
    private TRAP_ID m_ID;
    private String m_description;
    private int m_fearFactor = 0;
    private int m_curiosityFactor = 0;
    private float m_triggerDistance = 0;
    
    public TrapType(TRAP_ID type, String description, int fearFactor, int curiosityFactor, float triggerDistance){
        
    }
    

    /**
     * @return the m_ID
     */
    public TRAP_ID getM_ID() {
        return m_ID;
    }

    /**
     * @param m_ID the m_ID to set
     */
    public void setM_ID(TRAP_ID m_ID) {
        this.m_ID = m_ID;
    }

    /**
     * @return the m_description
     */
    public String getM_description() {
        return m_description;
    }

    /**
     * @param m_description the m_description to set
     */
    public void setM_description(String m_description) {
        this.m_description = m_description;
    }

    /**
     * @return the m_fearFactor
     */
    public int getM_fearFactor() {
        return m_fearFactor;
    }

    /**
     * @param m_fearFactor the m_fearFactor to set
     */
    public void setM_fearFactor(int m_fearFactor) {
        this.m_fearFactor = m_fearFactor;
    }

    /**
     * @return the m_curiosityFactor
     */
    public int getM_curiosityFactor() {
        return m_curiosityFactor;
    }

    /**
     * @param m_curiosityFactor the m_curiosityFactor to set
     */
    public void setM_curiosityFactor(int m_curiosityFactor) {
        this.m_curiosityFactor = m_curiosityFactor;
    }

    /**
     * @return the m_triggerDistance
     */
    public float getM_triggerDistance() {
        return m_triggerDistance;
    }

    /**
     * @param m_triggerDistance the m_triggerDistance to set
     */
    public void setM_triggerDistance(float m_triggerDistance) {
        this.m_triggerDistance = m_triggerDistance;
    }
}
