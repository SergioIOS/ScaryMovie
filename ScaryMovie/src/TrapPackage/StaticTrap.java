/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrapPackage;

import CharacterPackage.Teenager;
import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import scarymovie.GameEntity;
import scarymovie.ResourceManager;

/**
 *
 * @author CarlosEduardo
 */
public class StaticTrap extends GameEntity implements Trap{
    //Membros:
    private TrapType m_type;
    
    //Construtor:
    public StaticTrap(ResourceManager rm, Vector2f position, TrapType type){
        m_position = position;
        m_speed = new Vector2f(0,0);
        m_colisionBox = new Rectangle(position.x, position.y + 32, 32, 32);
        m_type = type;
        m_movementState = Teenager.MOVEMENT_STATES.STATE_STANDING;
        
        //Carregando animações
        m_sprites = new ArrayList<>();
        this.m_sprites.add(m_movementState.m_id, new Animation(rm.getTrapAnimation(TrapType.TRAP_ID.TRAP_BEER_BOTTLE), 200));
 /*     this.m_sprites.add(TrapType.TRAP_ID.TRAP_CAT.m_id, new Animation(rm.getTrapAnimation(TrapType.TRAP_ID.TRAP_CAT), 200));
        this.m_sprites.add(TrapType.TRAP_ID.TRAP_DISCARDED_WOMAN_CLOTHES.m_id, new Animation(rm.getTrapAnimation(TrapType.TRAP_ID.TRAP_DISCARDED_WOMAN_CLOTHES), 200));
        this.m_sprites.add(TrapType.TRAP_ID.TRAP_HUMAN_BONES.m_id, new Animation(rm.getTrapAnimation(TrapType.TRAP_ID.TRAP_HUMAN_BONES), 200));
        this.m_sprites.add(TrapType.TRAP_ID.TRAP_NUDE_CARD.m_id, new Animation(rm.getTrapAnimation(TrapType.TRAP_ID.TRAP_NUDE_CARD), 200));  */
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
}
