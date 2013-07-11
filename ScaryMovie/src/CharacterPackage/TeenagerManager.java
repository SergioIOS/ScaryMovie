/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CharacterPackage;

import java.util.ArrayList;
import org.newdawn.slick.geom.Vector2f;
import scarymovie.ResourceManager;

/**
 *
 * @author CarlosEduardo
 */
public class TeenagerManager {
    //Membros:
    private ArrayList<Teenager> m_teenagers;
    
    //Construtor:
    public TeenagerManager(){
        m_teenagers = new ArrayList<>();
    }
    
    //Atualiza os Teenagers
    public void updateTeens(){
        for(int x = 0; x < m_teenagers.size(); x++){
            m_teenagers.get(x).update();
        }
    }
    
    //Desenha os Teenagers
    public void drawTeenagers(){
        for(int x = 0; x < m_teenagers.size(); x++){
            m_teenagers.get(x).draw();
        }
    }
    
    //Adiciona Teenager
    public void addTeenager(ResourceManager rm, Vector2f position){        
        Teenager teenager = new Teenager(rm, position);
        this.m_teenagers.add(teenager);
    }
    
    //Remove Teenager
    public void removeTeenager(Teenager teenager){
        this.m_teenagers.remove(teenager);
    } 

    /**
     * @return the m_teenagers
     */
    public ArrayList<Teenager> getM_teenagers() {
        return m_teenagers;
    }

    /**
     * @param m_teenagers the m_teenagers to set
     */
    public void setM_teenagers(ArrayList<Teenager> m_teenagers) {
        this.m_teenagers = m_teenagers;
    }
            
}
