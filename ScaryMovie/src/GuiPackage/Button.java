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
public class Button {
    //Membros:
    Rectangle m_colisionBox = null;
    String m_id;
    
    public boolean checkClick(int x, int y){
        return m_colisionBox.contains(x, y);
    }
    
    public float getX(){
        return m_colisionBox.getX();
    }
    
    public float getY(){
        return m_colisionBox.getY();
    }

    /**
     * @return the m_id
     */
    public String getM_id() {
        return m_id;
    }

    /**
     * @param m_id the m_id to set
     */
    public void setM_id(String m_id) {
        this.m_id = m_id;
    }
}
