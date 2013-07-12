/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MapPackage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;
import scarymovie.Camera;

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
    public Map() throws SlickException{
        //Carregando o mapa:
        m_drawableMap = new TiledMap("data/BeachStage.tmx");
        m_tiles = new Tile[m_drawableMap.getWidth()][m_drawableMap.getHeight()];
        
        //Agora, percorrendo o mapa e criando os tiles:
        for(int y = 0; y < m_drawableMap.getWidth();y++){
            for(int x = 0; x < m_drawableMap.getHeight();x++){
                String temp = m_drawableMap.getTileProperty(m_drawableMap.getTileId(x, y, 0), "type", "1");
                
                if("0".equals(temp)){
                    m_tiles[x][y] = new Tile(new Vector2f(x * 32, y * 32), Tile.TILE_TYPES.TILE_WATER, false);
                }else{
                    m_tiles[x][y] = new Tile(new Vector2f(x * 32, y * 32), Tile.TILE_TYPES.TILE_WALKABLE, true);
                }
                
                System.out.print(temp);
            }
            
            System.out.println();
        }
        
        //Lendo a camada de colisão:
        for(int x = 0; x < m_drawableMap.getWidth();x++){
            for(int y = 0; y < m_drawableMap.getHeight();y++){
                if(m_drawableMap.getTileId(x, y, 1) > 0){
                    
                }
            }
        }
    }
    
    //Desenha o mapa:
    public void drawMap(GameContainer gc, Camera camera){
        //Calculado a posição para começar a desenhar:
        int tileOffsetX = (int) - (camera.getM_position().x % 32);
        int tileOffsetY = (int) - (camera.getM_position().y % 32);
        
        //Calculando o indice do tile mais pra esquerda:
        int tileIndexX = (int) (camera.getM_position().x / 32);
        int tileIndexY = (int) (camera.getM_position().y / 32);
        
        //Finalmente, desenhado:
        m_drawableMap.render(tileOffsetX + 0,
                     tileOffsetY + 0,
                     tileIndexX,
                     tileIndexY,
                     (gc.getWidth() - tileOffsetX) / 32 + 1,
                     (gc.getHeight()- tileOffsetY) / 32 + 1);
    }
    
    //Retorna um tile baseado em um ponto no mapa:
    public Tile getTileByPosition(Vector2f position){
        Tile temp = null;
        
        //Procurando o tile:
        for(int x = 0; x < m_drawableMap.getWidth();x++){
            for(int y = 0; y < m_drawableMap.getHeight();y++){
                if(m_tiles[x][y].getM_colisionBox().contains(position.x, position.y)){
                    return m_tiles[x][y];
                }
            }
        }
        
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
