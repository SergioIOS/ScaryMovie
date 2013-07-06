/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MapPackage;

import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Anderson
 */
public class Tile extends scarymovie.GameEntity {
    //Tipos de Tiles:
    public enum TILE_TYPES{
        TILE_GRASS(0),
        TILE_SAND(1),
        TILE_INSIDE_FLOOR(2),
        TILE_WALL(3),
        TILE_WATER(4);
        
        //O ID de cada objeto:
        public int m_id;
        
        //Cosntrutor:
        private TILE_TYPES(int m_id) {
            this.m_id = m_id;
        }
    }
    
    //Membros:
    TILE_TYPES m_type;
    int m_mapRelX, m_mapRelY;
    boolean m_passable = true;
    boolean m_spawn = false;

    //Construtor:
    public Tile(Vector2f position, TILE_TYPES type, boolean passable){
        
    }
}
