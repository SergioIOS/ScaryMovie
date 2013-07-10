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
    private Tile m_tiles[][];
    private TiledMap m_drawableMap;
    private int m_mapSizeW;
    private int m_mapSizeH;
    private int m_tileSizeW;
    private int m_tileSizeH;
    
    //Construtor:
    public Map(){
        
    }
    
    //Desenha o mapa:
    public void drawMap() throws SlickException{
        setM_drawableMap(new TiledMap("data/TestStage.tmx"));
        
        
    }
    
    //Retorna um tile baseado em um ponto no mapa:
    public Tile getTileByPosition(Vector2f position){
        Tile temp = null;
        
        return temp;
    }
    
    //Atualiza todos os tiles na array:
    public void updateTiles(){
        
    }

    /**
     * @return the m_tiles
     */
    public Tile[][] getM_tiles() {
        return m_tiles;
    }

    /**
     * @param m_tiles the m_tiles to set
     */
    public void setM_tiles(Tile[][] m_tiles) {
        this.m_tiles = m_tiles;
    }

    /**
     * @return the m_drawableMap
     */
    public TiledMap getM_drawableMap() {
        return m_drawableMap;
    }

    /**
     * @param m_drawableMap the m_drawableMap to set
     */
    public void setM_drawableMap(TiledMap m_drawableMap) {
        this.m_drawableMap = m_drawableMap;
    }

    /**
     * @return the m_mapSizeW
     */
    public int getM_mapSizeW() {
        return m_mapSizeW;
    }

    /**
     * @param m_mapSizeW the m_mapSizeW to set
     */
    public void setM_mapSizeW(int m_mapSizeW) {
        this.m_mapSizeW = m_mapSizeW;
    }

    /**
     * @return the m_mapSizeH
     */
    public int getM_mapSizeH() {
        return m_mapSizeH;
    }

    /**
     * @param m_mapSizeH the m_mapSizeH to set
     */
    public void setM_mapSizeH(int m_mapSizeH) {
        this.m_mapSizeH = m_mapSizeH;
    }

    /**
     * @return the m_tileSizeW
     */
    public int getM_tileSizeW() {
        return m_tileSizeW;
    }

    /**
     * @param m_tileSizeW the m_tileSizeW to set
     */
    public void setM_tileSizeW(int m_tileSizeW) {
        this.m_tileSizeW = m_tileSizeW;
    }

    /**
     * @return the m_tileSizeH
     */
    public int getM_tileSizeH() {
        return m_tileSizeH;
    }

    /**
     * @param m_tileSizeH the m_tileSizeH to set
     */
    public void setM_tileSizeH(int m_tileSizeH) {
        this.m_tileSizeH = m_tileSizeH;
    }
}
