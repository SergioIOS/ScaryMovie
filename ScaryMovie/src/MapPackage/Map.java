/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MapPackage;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Anderson
 */
public class Map {
    //Membros:
    Tile m_tiles[][];
    TiledMap m_drawableMap;
    int m_mapSizeW, m_mapSizeH, m_tileSizeW, m_tileSizeH;
    
    //Construtor:
    public Map(){
        
    }
    
    //Desenha o mapa:
    public void drawMap() throws SlickException{
        m_drawableMap = new TiledMap("data/TestStage.tmx");
        
        
    }
    
    //Retorna um tile baseado em um ponto no mapa:
    public Tile getTileByPosition(Vector2f position){
        Tile temp = null;
        
        return temp;
    }
    
    //Atualiza todos os tiles na array:
    public void updateTiles(){
        
    }
}
