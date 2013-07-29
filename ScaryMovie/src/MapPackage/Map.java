/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MapPackage;

import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;
import scarymovie.Camera;
import scarymovie.ResourceManager;

/**
 *
 * @author Anderson
 */
public class Map {
    //Membros:
    private Tile m_tiles[][];
    private ArrayList<Tile> m_spawnPoints;
    private Animation m_spawnAnimation = null;
    private TiledMap m_loadedMap;
    private int m_mapWidthInPixels, m_mapHeightInPixels;
    private Image m_mapBadge = null;
    private String m_mapName = "";
    
    private static Map instance = null;
    
    public static Map getInstance(String mapName) throws SlickException{
        if(instance == null){
            instance = new Map(mapName);
        }
        
        return instance;
    }
    
    //Array de rects para colisão:
    ArrayList<Rectangle> m_colisionArray;
    
    //Construtor:
    public Map(String mapName) throws SlickException{
        //Inicializando:
        m_spawnPoints = new ArrayList<>();
        
        //Devemos carregar algum mapa novo?
        if(mapName != null)
            loadTmxMap(mapName);
        
        //Carregando a animação do spawn:
        m_spawnAnimation = new Animation(ResourceManager.getInstance().getSpawnAnimation(), 100);
    }
    
    private boolean loadTmxMap(String mapName) throws SlickException{
        //Temps:
        String mapFileAdress = "data/maps/" + mapName + "/map.tmx";
        String mapBadgeAdress = "data/maps/" + mapName + "/badge.png";
        
        //Começando:
        System.out.println("Starting loading of the map: '" + mapFileAdress + "'");
        
        m_loadedMap = new TiledMap(mapFileAdress);
        m_mapBadge = new Image(mapBadgeAdress);
        m_mapName = mapName;
        m_mapWidthInPixels = m_loadedMap.getWidth() * 32;
        m_mapHeightInPixels = m_loadedMap.getHeight() * 32;
        
        System.out.println("\tSize (W/H): " + m_loadedMap.getWidth() + "/" + m_loadedMap.getHeight());
        
        //Percorrendo os tiles e criando os mesmos:
        m_tiles = new Tile[m_loadedMap.getWidth()][m_loadedMap.getHeight()];
        
        for(int x = 0; x < m_loadedMap.getWidth();x++){
            for(int y = 0; y < m_loadedMap.getHeight();y++){
                String temp = m_loadedMap.getTileProperty(m_loadedMap.getTileId(x, y, 0), "type", "-1");
                
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
        
        //Lendo e instanciando o mapa de colisão:
        m_colisionArray = new ArrayList<>();
        
        for(int x = 0; x < m_loadedMap.getObjectCount(0);x++){
            m_colisionArray.add(new Rectangle(m_loadedMap.getObjectX(0, x), m_loadedMap.getObjectY(0, x), m_loadedMap.getObjectWidth(0, x), m_loadedMap.getObjectHeight(0, x)));
        }
        
        //Tudo correu bem:
        return true;
    }
    
    public void setSpawnPoint(Tile tile){
//        for(int x = 0; x < m_loadedMap.getWidth();x++){
//            for(int y = 0; y < m_loadedMap.getHeight();y++){
//                m_spawnPoints.add(m_tiles[x][y]);
//            }
//        }
        
        m_spawnPoints.add(tile);
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
        m_loadedMap.render(tileOffsetX + 0, tileOffsetY + 0, tileIndexX, tileIndexY, 
                (gc.getWidth() - tileOffsetX) / 32 + 1, 
                (gc.getHeight()- tileOffsetY) / 32 + 1, 0, false);
        
        //Layer de colisão:
        m_loadedMap.render(tileOffsetX + 0, tileOffsetY + 0, tileIndexX, tileIndexY, 
                (gc.getWidth() - tileOffsetX) / 32 + 1, 
                (gc.getHeight()- tileOffsetY) / 32 + 1, 1, false);
        
        //Temos que desehar o spawn?
        if(m_spawnPoints.size() > 0){
            for(Tile tile : m_spawnPoints){
                m_spawnAnimation.draw(tile.getM_position().x, tile.getM_position().y);
            }
        }
    }
    
    public void drawUpperLayersMap(GameContainer gc, Camera camera){
        //Calculado a posição para começar a desenhar:
        int tileOffsetX = (int) - (camera.getM_position().x % 32);
        int tileOffsetY = (int) - (camera.getM_position().y % 32);
        
        //Calculando o indice do tile mais pra esquerda:
        int tileIndexX = (int) (camera.getM_position().x / 32);
        int tileIndexY = (int) (camera.getM_position().y / 32);
        
        //Layer sobreposta:
        m_loadedMap.render(tileOffsetX + 0, tileOffsetY + 0, tileIndexX, tileIndexY, 
                (gc.getWidth() - tileOffsetX) / 32 + 1, 
                (gc.getHeight()- tileOffsetY) / 32 + 1, 2, false);
    }
    
    //Retorna um tile baseado em um ponto no mapa:
    public Tile getTileByPosition(Vector2f position){
        //Procurando o tile:
        for(int x = 0; x < m_loadedMap.getWidth();x++){
            for(int y = 0; y < m_loadedMap.getHeight();y++){
                if(m_tiles[x][y].getM_colisionBox().contains(position.x, position.y)){
                    System.out.println("Tile (X/Y): " + m_tiles[x][y].getM_position().x + "/" + m_tiles[x][y].getM_position().y);
                    System.out.println("Tile (X/Y): " + m_tiles[x][y].getM_position().x + "/" + m_tiles[x][y].getM_position().y);
                    
                    return m_tiles[x][y];
                }
            }
        }
        
        System.out.println("THIS SHOULD NOT HAPPEN.");
        
        return null;
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
        return m_loadedMap;
    }

    /**
     * @param m_drawableMap the m_drawableMap to set
     */
    public void setM_drawableMap(TiledMap m_drawableMap) {
        this.m_loadedMap = m_drawableMap;
    }

    /**
     * @return the m_mapSizeW
     */
    public int getM_mapSizeW() {
        return m_mapWidthInPixels;
    }

    /**
     * @param m_mapSizeW the m_mapSizeW to set
     */
    public void setM_mapSizeW(int m_mapSizeW) {
        this.m_mapWidthInPixels = m_mapSizeW;
    }

    /**
     * @return the m_mapSizeH
     */
    public int getM_mapSizeH() {
        return m_mapHeightInPixels;
    }

    /**
     * @param m_mapSizeH the m_mapSizeH to set
     */
    public void setM_mapSizeH(int m_mapSizeH) {
        this.m_mapHeightInPixels = m_mapSizeH;
    }
    
    public Image getMapBadge(){
        return m_mapBadge;
    }
    
    public String getMapName(){
        return m_mapName;
    }
    
    public int getNumberOfSpawns(){
        return m_spawnPoints.size();
    }
}
