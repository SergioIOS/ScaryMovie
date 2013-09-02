/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CharacterPackage;

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
    
    public static TeenagerManager getInstance(Camera camera){
        try{
            if(instance == null){
                instance = new TeenagerManager(camera);
            }
        }
        catch(SlickException e){
            
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
    }
    
    //Adiciona Teenager
    public void addTeenager(ResourceManager rm, Vector2f position, Map map, Killer killer){        
        Teenager teenager = new Teenager(rm, position, killer);
        teenager.getM_ai().updateEmotions(teenager);
        this.m_teenagers.add(teenager);
        
    }
    
    //Remove Teenager
    public void removeTeenager(Teenager teenager){
        this.m_teenagers.remove(teenager);
    } 
    
    public void killTeenager(Teenager teenager){
        teenager.kill();
    } 
    
    //Verifica colisão do Teenager com todos os outros:
    public boolean checkTeenColision(Rectangle rect, Teenager teen){
        for(Teenager targetTeen : this.m_teenagers){
            if(!(teen == targetTeen)){
                if(rect.intersects(new Rectangle(targetTeen.getM_position().x, targetTeen.getM_position().y + 32, 32, 32))){
                    return true;
                }
            }
        }
        return false;
    }
    
    //Verifica colisão com todos os teens:
    public Teenager checkTeenColision(Rectangle rect){
        for(Teenager targetTeen : this.m_teenagers){
            if(targetTeen.getM_colisionBox().intersects(rect) && targetTeen.getM_movementState() != Teenager.MOVEMENT_STATES.STATE_DEAD){
                return targetTeen;
            }
        }
        return null;
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
    
    public boolean checkTeenDistance(Vector2f position){
        for(Teenager teen : this.m_teenagers){
            if(teen.getM_position().distance(position) <= teen.getM_ai().getM_viewDistance()){
                return true;
            }
        }
        return false;
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
