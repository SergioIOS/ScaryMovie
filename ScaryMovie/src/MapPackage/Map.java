/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MapPackage;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
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
    
    private static Map instance = null;
    
    public static Map getInstance() throws SlickException{
        if(instance == null){
            instance = new Map();
        }
        
        return instance;
    }
    
    //Array de rects para colisão:
    ArrayList<Rectangle> m_colisionArray;
    
    //Construtor:
    public Map() throws SlickException{
        System.out.println("Starting loading of the map: 'data/StageBeach.tmx'");
        
        //Carregando o mapa:
        m_drawableMap = new TiledMap("data/StageBeach.tmx");
        m_tiles = new Tile[m_drawableMap.getWidth()][m_drawableMap.getHeight()];
        
        System.out.println("Size (W/H): " + m_drawableMap.getWidth() + "/" + m_drawableMap.getHeight());
        
        //Agora, percorrendo o mapa e criando os tiles:
        for(int y = 0; y < m_drawableMap.getWidth();y++){
            for(int x = 0; x < m_drawableMap.getHeight();x++){
                String temp = m_drawableMap.getTileProperty(m_drawableMap.getTileId(x, y, 0), "type", "-1");
                
                switch(temp){
                    case "0":
                        m_tiles[x][y] = new Tile(new Vector2f(x * 32, y * 32), Tile.TILE_TYPES.TILE_WALKABLE, false);
                    break;
                        
                    case "1":
                        m_tiles[x][y] = new Tile(new Vector2f(x * 32, y * 32), Tile.TILE_TYPES.TILE_WATER, true);
                        
                        //Criando a animação da água:
                    break;
                        
                    default: 
                        m_tiles[x][y] = new Tile(new Vector2f(x * 32, y * 32), Tile.TILE_TYPES.TILE_NON_WALKABLE, false);
                    break;
                }
            }
        }
        
        //Lendo a camada de colisão:
        m_colisionArray = new ArrayList<>();
        
        for(int x = 0; x < m_drawableMap.getObjectCount(0);x++){
            m_colisionArray.add(new Rectangle(m_drawableMap.getObjectX(0, x), m_drawableMap.getObjectY(0, x), m_drawableMap.getObjectWidth(0, x), m_drawableMap.getObjectHeight(0, x)));
        }
        
        //Salvando as variáveis restantes:
        m_mapSizeW = m_drawableMap.getWidth() * 32;
        m_mapSizeH = m_drawableMap.getHeight() * 32;
        
        //DEBUG:
        System.out.println("Top Left (X/Y/W/H): " + m_tiles[0][0].getM_position().x + "/" + m_tiles[0][0].getM_position().y + "/" + 
                m_tiles[0][0].getM_colisionBox().getWidth() + "/" + m_tiles[0][0].getM_colisionBox().getHeight());
        
        System.out.println("Top Right (X/Y/W/H): " + m_tiles[49][0].getM_position().x + "/" + m_tiles[0][0].getM_position().y + "/" + 
                m_tiles[0][0].getM_colisionBox().getWidth() + "/" + m_tiles[0][0].getM_colisionBox().getHeight());
        
        System.out.println("Bottom Left (X/Y/W/H): " + m_tiles[0][49].getM_position().x + "/" + m_tiles[0][0].getM_position().y + "/" + 
                m_tiles[0][0].getM_colisionBox().getWidth() + "/" + m_tiles[0][0].getM_colisionBox().getHeight());
        
        System.out.println("Bottom Right (X/Y/W/H): " + m_tiles[49][49].getM_position().x + "/" + m_tiles[0][0].getM_position().y + "/" + 
                m_tiles[0][0].getM_colisionBox().getWidth() + "/" + m_tiles[0][0].getM_colisionBox().getHeight());
    }
    
    //Desenha o mapa:
    public void drawLowerLayersMap(GameContainer gc, Camera camera){
        //Calculado a posição para começar a desenhar:
        int tileOffsetX = (int) - (camera.getM_position().x % 32);
        int tileOffsetY = (int) - (camera.getM_position().y % 32);
        
        //Calculando o indice do tile mais pra esquerda:
        int tileIndexX = (int) (camera.getM_position().x / 32);
        int tileIndexY = (int) (camera.getM_position().y / 32);
        
        //Layer do chão:
        m_drawableMap.render(tileOffsetX + 0, tileOffsetY + 0, tileIndexX, tileIndexY, 
                (gc.getWidth() - tileOffsetX) / 32 + 1, 
                (gc.getHeight()- tileOffsetY) / 32 + 1, 0, false);
        
        //Layer de colisão:
        m_drawableMap.render(tileOffsetX + 0, tileOffsetY + 0, tileIndexX, tileIndexY, 
                (gc.getWidth() - tileOffsetX) / 32 + 1, 
                (gc.getHeight()- tileOffsetY) / 32 + 1, 1, false);
    }
    
    public void drawUpperLayersMap(GameContainer gc, Camera camera){
        //Calculado a posição para começar a desenhar:
        int tileOffsetX = (int) - (camera.getM_position().x % 32);
        int tileOffsetY = (int) - (camera.getM_position().y % 32);
        
        //Calculando o indice do tile mais pra esquerda:
        int tileIndexX = (int) (camera.getM_position().x / 32);
        int tileIndexY = (int) (camera.getM_position().y / 32);
        
        //Layer sobreposta:
        m_drawableMap.render(tileOffsetX + 0, tileOffsetY + 0, tileIndexX, tileIndexY, 
                (gc.getWidth() - tileOffsetX) / 32 + 1, 
                (gc.getHeight()- tileOffsetY) / 32 + 1, 2, false);
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
    
    public boolean checkMapColision(Rectangle rect){
        for(Rectangle tileRect : m_colisionArray){
            if(rect.intersects(tileRect)){
                return true;
            }
        }
        
        return false;
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
}
