/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MapPackage;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Anderson
 */
public class Tile {
    //Tipos de Tiles:
    public enum TILE_TYPES{
        TILE_WALKABLE(0),
        TILE_WATER(1),
        TILE_NON_WALKABLE(2);
        
        //O ID de cada objeto:
        public int m_id;
        
        //Cosntrutor:
        private TILE_TYPES(int m_id) {
            this.m_id = m_id;
        }
    }
    
    //Membros:
    private TILE_TYPES m_type;
    private int m_mapRelX;
    private int m_mapRelY;
    private boolean m_passable = true;
    private boolean m_spawn = false;
    private Vector2f m_position;
    private Rectangle m_colisionBox = null;
    
    //Membros a serem utilizados pelo PathFinding:
    private Tile m_prev;
    private int m_costG; //Custo para andar do tile inicial até o atual
    private int m_costH; //Custo para andar do tile atual até o destino
    private int m_totalCostF; //Soma de G e H

    //Construtor:
    public Tile(Vector2f position, TILE_TYPES type, boolean passable){
        m_position = position;
        m_mapRelX = (int)position.x / 32;
        m_mapRelY = (int)position.y / 32;
        
        m_passable = passable;
        m_spawn = false;
        m_type = type;
        
        m_colisionBox = new Rectangle(getM_position().x, getM_position().y, 32, 32);
    }
    

    /**
     * @return the m_type
     */
    public TILE_TYPES getM_type() {
        return m_type;
    }

    /**
     * @param m_type the m_type to set
     */
    public void setM_type(TILE_TYPES m_type) {
        this.m_type = m_type;
    }

    /**
     * @return the m_mapRelX
     */
    public int getM_mapRelX() {
        return m_mapRelX;
    }

    /**
     * @param m_mapRelX the m_mapRelX to set
     */
    public void setM_mapRelX(int m_mapRelX) {
        this.m_mapRelX = m_mapRelX;
    }

    /**
     * @return the m_mapRelY
     */
    public int getM_mapRelY() {
        return m_mapRelY;
    }

    /**
     * @param m_mapRelY the m_mapRelY to set
     */
    public void setM_mapRelY(int m_mapRelY) {
        this.m_mapRelY = m_mapRelY;
    }

    /**
     * @return the m_passable
     */
    public boolean isM_passable() {
        return m_passable;
    }

    /**
     * @param m_passable the m_passable to set
     */
    public void setM_passable(boolean m_passable) {
        this.m_passable = m_passable;
    }

    /**
     * @return the m_spawn
     */
    public boolean isM_spawn() {
        return m_spawn;
    }

    /**
     * @param m_spawn the m_spawn to set
     */
    public void setM_spawn(boolean m_spawn) {
        this.m_spawn = m_spawn;
    }

    /**
     * @return the m_position
     */
    public Vector2f getM_position() {
        return m_position;
    }

    /**
     * @param m_position the m_position to set
     */
    public void setM_position(Vector2f m_position) {
        this.m_position = m_position;
    }
    
    /**
     * @return the m_colisionBox
     */
    public Rectangle getM_colisionBox() {
        return m_colisionBox;
    }

    /**
     * @param m_colisionBox the m_colisionBox to set
     */
    public void setM_colisionBox(Rectangle m_colisionBox) {
        this.m_colisionBox = m_colisionBox;
    }
    
    public boolean getM_spawn(){
        return isM_spawn();
    }

    /**
     * @return the m_prev
     */
    public Tile getM_prev() {
        return m_prev;
    }

    /**
     * @param m_prev the m_prev to set
     */
    public void setM_prev(Tile m_prev) {
        this.m_prev = m_prev;
    }

    /**
     * @return the m_costG
     */
    public int getM_costG() {
        return m_costG;
    }

    /**
     * @param m_costG the m_costG to set
     */
    public void setM_costG(int m_costG) {
        this.m_costG = m_costG;
    }

    /**
     * @return the m_costH
     */
    public int getM_costH() {
        return m_costH;
    }

    /**
     * @param m_costH the m_costH to set
     */
    public void setM_costH(int m_costH) {
        this.m_costH = m_costH;
    }

    /**
     * @return the m_totalCostF
     */
    public int getM_totalCostF() {
        return m_totalCostF;
    }

    /**
     * @param m_totalCostF the m_totalCostF to set
     */
    public void setM_totalCostF(int m_totalCostF) {
        this.m_totalCostF = m_totalCostF;
    }
}
