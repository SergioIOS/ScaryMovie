/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrapPackage;

import java.util.ArrayList;
import org.newdawn.slick.geom.Vector2f;
import scarymovie.Camera;
import scarymovie.ResourceManager;

/**
 *
 * @author CarlosEduardo
 */
public class TrapManager {
    //Membros:
    private ArrayList<StaticTrap> m_staticTraps;
    private ArrayList<MovableTrap> m_movableTraps;
    private ArrayList<TrapType> m_trapTypes;
    
    private static TrapManager instance = null;
    
    public static TrapManager getInstance(){
        if(instance == null){
            instance = new TrapManager();
        }
        
        return instance;
    }
    
    //Construtor:
    public TrapManager(){
        m_staticTraps = new ArrayList<>();
        m_movableTraps = new ArrayList<>();
        m_trapTypes = new ArrayList<>();
        m_trapTypes.add(TrapType.TRAP_ID.TRAP_BEER_BOTTLE.m_id, new TrapType(TrapType.TRAP_ID.TRAP_BEER_BOTTLE, "Beer Bottle", 0, 15, 128));
        
        //Adicionar todos os tipos de traps.
    }
    
    //Atualiza todas as Traps:
    public void updateTraps(){
        
    }
    
    //Desenha as Traps:
    public void drawTraps(Camera cam){
        for(int x = 0; x < m_staticTraps.size(); x++){
            getM_staticTraps().get(x).draw(cam);
        }
        
       /* for(int x = 0; x < m_movableTraps().size(); x++){
            getM_movableTraps().get(x).draw(cam);
        }       */ 
    }
    
    //Adiciona Static Trap:
    public void addStaticTrap(ResourceManager rm, Vector2f position, TrapType.TRAP_ID id){
        StaticTrap trap = new StaticTrap(rm, position, getM_trapTypes().get(id.m_id));
        this.getM_staticTraps().add(trap);
    }
    
    //Remove Static Trap:
    public void removeStaticTrap(StaticTrap trap){
        this.getM_staticTraps().remove(trap);
    }
    
    //Adiciona Movable Trap:
    public void addMovableTrap(ResourceManager rm, Vector2f position, TrapType.TRAP_ID id){
        //MovableTrap trap = new MovableTrap(rm, position, m_trapTypes.get(id.m_id));
        //this.getM_movableTraps().add(trap);
    }
    
    //Remove Movable Trap:
    public void removeMovableTrap(MovableTrap trap){
        this.getM_movableTraps().remove(trap);
    }

    /**
     * @return the m_trapTypes
     */
    public ArrayList<TrapType> getM_trapTypes() {
        return m_trapTypes;
    }

    /**
     * @param m_trapTypes the m_trapTypes to set
     */
    public void setM_trapTypes(ArrayList<TrapType> m_trapTypes) {
        this.m_trapTypes = m_trapTypes;
    }

    /**
     * @return the m_staticTraps
     */
    public ArrayList<StaticTrap> getM_staticTraps() {
        return m_staticTraps;
    }

    /**
     * @param m_staticTraps the m_staticTraps to set
     */
    public void setM_staticTraps(ArrayList<StaticTrap> m_staticTraps) {
        this.m_staticTraps = m_staticTraps;
    }

    /**
     * @return the m_movableTraps
     */
    public ArrayList<MovableTrap> getM_movableTraps() {
        return m_movableTraps;
    }

    /**
     * @param m_movableTraps the m_movableTraps to set
     */
    public void setM_movableTraps(ArrayList<MovableTrap> m_movableTraps) {
        this.m_movableTraps = m_movableTraps;
    }
}
