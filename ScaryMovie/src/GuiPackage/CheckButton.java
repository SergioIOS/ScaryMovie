/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiPackage;

import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Anderson
 */
public class CheckButton extends Button {
    //Membros:
    private boolean m_toggled = false;
    
    //Construtor:
    public CheckButton(Rectangle sizeAndPosition, boolean toggled, String id) {
        m_colisionBox = sizeAndPosition;
        m_toggled = toggled;
        m_id = id;
    }

    /**
     * @return the m_toggled
     */
    public boolean isM_toggled() {
        return m_toggled;
    }

    /**
     * @param m_toggled the m_toggled to set
     */
    public void setM_toggled(boolean m_toggled) {
        this.m_toggled = m_toggled;
    }
    
    public void toggleState(){
        m_toggled = !m_toggled;
    }
}
