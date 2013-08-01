/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrapPackage;

import CharacterPackage.Teenager;
import MapPackage.Map;
import MapPackage.Tile;
import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import scarymovie.Camera;
import scarymovie.GameEntity;
import scarymovie.ResourceManager;

/**
 *
 * @author CarlosEduardo
 */
public class StaticTrap implements Trap{
    //Membros:
    private TrapType m_type;
    private Vector2f m_position;
    private Vector2f m_speed;
    private Rectangle m_colisionBox;
    private Animation m_sprite;
    private Tile m_currentTile;
    
    //Construtor:
    public StaticTrap(ResourceManager rm, Vector2f position, TrapType type, Map map){
        m_position = position;
        m_speed = new Vector2f(0,0);
        m_colisionBox = new Rectangle(position.x, position.y, 32, 32);
        m_type = type;
        
        m_currentTile = map.getTileByPosition(m_position.x + 16, m_position.y + 16);
        
        //Carregando animações
        m_sprite = new Animation(rm.getTrapAnimation(type.getM_ID()), 200);
        
    }

    /**
     * @return the type
     */
    public TrapType getM_type() {
        return m_type;
    }

    /**
     * @param type the type to set
     */
    public void setM_type(TrapType type) {
        this.m_type = type;
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
     * @return the m_speed
     */
    public Vector2f getM_speed() {
        return m_speed;
    }

    /**
     * @param m_speed the m_speed to set
     */
    public void setM_speed(Vector2f m_speed) {
        this.m_speed = m_speed;
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

    /**
     * @return the m_sprites
     */
    public Animation getM_sprite() {
        return m_sprite;
    }

    /**
     * @param m_sprites the m_sprites to set
     */
    public void setM_sprite(Animation m_sprite) {
        this.m_sprite = m_sprite;
    }

    @Override
    public void draw(Camera camera) {
        getM_sprite().draw(getM_position().x - camera.getM_position().x, getM_position().y - camera.getM_position().y);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the m_currentTile
     */
    public Tile getM_currentTile() {
        return m_currentTile;
    }

    /**
     * @param m_currentTile the m_currentTile to set
     */
    public void setM_currentTile(Tile m_currentTile) {
        this.m_currentTile = m_currentTile;
    }

}
