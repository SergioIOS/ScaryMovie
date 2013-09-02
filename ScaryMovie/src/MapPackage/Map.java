/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MapPackage;

import CharacterPackage.Killer;
import CharacterPackage.TeenagerManager;
import java.util.ArrayList;
import java.util.Random;
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
        
        //Populando o mapa:
        //populateMap();
        
        //Carregando a animação do spawn:
        m_spawnAnimation = new Animation(ResourceManager.getInstance().getSpawnAnimation(), 300);
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
        m_tiles = new Tile[m_loadedMap.getHeight()][m_loadedMap.getWidth()];
        
        //Camada do chão:
        System.out.println("\tProcessing the floor Layer. (ID: 0)");
        
        System.out.println("\tArray Size (W/H): " + m_tiles[0].length + "/" + m_tiles.length);
        
        for(int w = 0; w < m_tiles[0].length; w++){
            for(int h = 0; h < m_tiles.length; h++){
                //Lendo a property do tile:
                String temp = m_loadedMap.getTileProperty(m_loadedMap.getTileId(w, h, 0), "type", "-1");
                
                //Processando:
                switch(temp){
                    case "0":
                        m_tiles[h][w] = new Tile(new Vector2f(w * 32, h * 32), Tile.TILE_TYPES.TILE_WALKABLE, true);
                    break;
                        
                    case "1":
                        m_tiles[h][w] = new Tile(new Vector2f(w * 32, h * 32), Tile.TILE_TYPES.TILE_WATER, false);
                        
                        //Criando a animação da água:
                    break;
                        
                    default: 
                        m_tiles[h][w] = new Tile(new Vector2f(w * 32, h * 32), Tile.TILE_TYPES.TILE_NON_WALKABLE, false);
                    break;
                }
            }
        }
        
        //DEBUG:
        for(int w = 0; w < m_tiles.length; w++){
            for(int h = 0; h < m_tiles[0].length; h++){
                if(!m_tiles[w][h].isM_passable()){
                    System.out.print("0");
                }
                else{
                    System.out.print("-");
                }
            }
            
            System.out.println();
        }
        
        //Lendo e instanciando o mapa de colisão:
        m_colisionArray = new ArrayList<>();
        
        for(int x = 0; x < m_loadedMap.getObjectCount(0);x++){
            m_colisionArray.add(new Rectangle(m_loadedMap.getObjectX(0, x), m_loadedMap.getObjectY(0, x), m_loadedMap.getObjectWidth(0, x), m_loadedMap.getObjectHeight(0, x)));
        }
        
        //Tudo correu bem:
        return true;
    }
    
    public void populateMap(){
        System.out.print("\tPopulating Map...");
        
        //Escolhendo aleatóriamente a quantidade de teens que teremos:
        Random rand = new Random();
        
        //Temps:
        int qtdTeensToCreate = rand.nextInt(3) + 1;    //de 5 à 19
        
        //Escolhendo uma posição:
        for(int x = 0; x < qtdTeensToCreate; x++){
            //Escolhendo o tile:
            Tile temp = getRandomWalkableTile();
            
            //Corrigindo a posição do tile:
            Vector2f newPosition = new Vector2f(temp.getM_position().x, temp.getM_position().y + 32);
            
            TeenagerManager.getInstance(null).addTeenager(ResourceManager.getInstance(), newPosition, this, Killer.getInstance(null, null));
        }
        
        System.out.println("\tNbr of teen created: " + qtdTeensToCreate);
    }
    
    //DEV NOTE: Essa função pode teoricamente ficar extremamente lenta e travar o jogo. Depende da sorte. :D
    public Tile getRandomWalkableTile(){
        Random rand = new Random();
        boolean done = false;
        int x = 0, y = 0;
        
        while(!done){
            //Randomizando:
            x = rand.nextInt(m_loadedMap.getWidth());
            y = rand.nextInt(m_loadedMap.getHeight());
            
            //É um tile walkable?
            if(m_tiles[y][x].isM_passable()){
                done = true;
            }
        }
        
        //Retornando:
        return m_tiles[y][x];
    }
    
    public void setSpawnPoint(Tile tile){
        if(tile != null && tile.isM_passable() == true && tile.getM_spawn() == false){
            m_spawnPoints.add(tile);
            tile.setM_spawn(true);
        }
        else{
            System.out.println("NOPE.");
        }
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
                m_spawnAnimation.draw(tile.getM_position().x - camera.getM_position().x, (tile.getM_position().y - camera.getM_position().y) - 32);
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
        //Decobrindo o X e o Y:
        int x = (int)position.x / 32;
        int y = (int)position.y / 32;
        
//        System.out.println("X/Y: " + x + "/" + y);
//        System.out.println("Passable: " + m_tiles[y][x].isM_passable());
        
        return m_tiles[y][x];
    }
    
    //Retorna um tile baseado em um ponto no mapa:
    public Tile getTileByPosition(float xpos, float ypos){        
        //Decobrindo o X e o Y:
        int x = (int)xpos / 32;
        int y = (int)ypos / 32;
        
//        System.out.println("X/Y: " + x + "/" + y);
//        System.out.println("Passable: " + m_tiles[y][x].isM_passable());
        
        return m_tiles[y][x];
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
