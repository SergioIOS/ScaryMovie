/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CharacterPackage;

import GuiPackage.BubbleManager;
import MapPackage.Map;
import TrapPackage.TrapManager;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import scarymovie.Camera;
import scarymovie.ResourceManager;

/**
 *
 * @author CarlosEduardo
 */
public class TeenagerManager {
    //Membros:
    private ArrayList<Teenager> m_teenagers;
    private Camera m_camera = null;
    
    private static TeenagerManager instance = null;
    
    public static TeenagerManager getInstance(Camera camera) throws SlickException{
        if(instance == null){
            instance = new TeenagerManager(camera);
        }
        
        return instance;
    }
    
    //Construtor:
    public TeenagerManager(Camera camera) throws SlickException{
        m_teenagers = new ArrayList<>();
        m_camera = camera;
    }
    
    //Atualiza os Teenagers
    public void updateTeens(Map map, TrapManager trm){
        for(Teenager teen : m_teenagers){
            teen.update(map, this, trm);
        }
    }
    
    //Desenha os Teenagers
    public void drawTeenagers(Graphics grphcs){
        for(int x = 0; x < m_teenagers.size(); x++){
            m_teenagers.get(x).draw(m_camera);
        }
        
        //Desenhando as informações de debug:
        grphcs.drawString("Nbr of Teens: " + String.valueOf(m_teenagers.size()), 600, 15);
    }
    
    //Adiciona Teenager
    public void addTeenager(ResourceManager rm, Vector2f position, Map map, BubbleManager bm){        
        Teenager teenager = new Teenager(rm, position, map.getTileByPosition(position));
        teenager.updateEmotions(bm);
        bm.addBubble(teenager);
        this.m_teenagers.add(teenager);
        
    }
    
    //Remove Teenager
    public void removeTeenager(Teenager teenager){
        this.m_teenagers.remove(teenager);
    } 
    
    //Verifica colisão do Teenager com todos os outros:
    public boolean checkTeenColision(Rectangle rect, Teenager teen){
        for(Teenager targetTeen : this.m_teenagers){
            if(!(teen == targetTeen)){
                if(rect.intersects(targetTeen.getM_colisionBox())){
                    return true;
                }
            }
        }
        return false;
    }
    
    //Função que checa se clicamos em algum teen, e retorna o mesmo:{
    public Teenager checkMouseClickColision(float x, float y){
        for(Teenager targetTeen : this.m_teenagers){
            if(targetTeen.getM_colisionBox().contains(x + m_camera.getM_position().x, y + m_camera.getM_position().y)){
                return targetTeen;
            }
        }
        
        return null;
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
