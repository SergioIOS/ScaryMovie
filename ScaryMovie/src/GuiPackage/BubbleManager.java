/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiPackage;

import CharacterPackage.Teenager;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import scarymovie.Camera;

/**
 *
 * @author Anderson
 */
public class BubbleManager {

    /**
     * @return the m_shouldDraw
     */
    public boolean isM_shouldDraw() {
        return m_shouldDraw;
    }

    /**
     * @param m_shouldDraw the m_shouldDraw to set
     */
    public void setM_shouldDraw(boolean m_shouldDraw) {
        this.m_shouldDraw = m_shouldDraw;
    }
    //Os diferentes tipos de emoções:
    public enum EMOTIONS{
        EMOTION_NO(0),
        EMOTION_YES(1),
        EMOTION_CURIOSITY(2),
        EMOTION_SCARED(3),
        EMOTION_HAPPY(4),
        EMOTION_SAD(5);
        
        public int m_id = -1;

        private EMOTIONS(int id) {
            this.m_id = id;
        }
    }
    
    //Speites usados pelo bubble manager:
    SpriteSheet m_sheet = null;
    ArrayList<Bubble> m_bubbles;
    private boolean m_shouldDraw = false;

    public BubbleManager() throws SlickException {
        //Carregando o spritesheet:
        m_sheet = new SpriteSheet("data/BubblesSheet.png", 32, 32);
        
        //Inicializando a lista:
        m_bubbles = new ArrayList<>();
    }
    
    public void addBubble(Teenager teen){
        //Criando a bubble:
        switch(teen.getM_curentEmotion()){
            case EMOTION_NO:
                m_bubbles.add(new Bubble(m_sheet.getSprite(0, 0), teen));
                break;
            case EMOTION_YES:
                m_bubbles.add(new Bubble(m_sheet.getSprite(1, 0), teen));
                break;
            case EMOTION_CURIOSITY:
                m_bubbles.add(new Bubble(m_sheet.getSprite(2, 0), teen));
                break;
            case EMOTION_SCARED:
                m_bubbles.add(new Bubble(m_sheet.getSprite(3, 0), teen));
                break;
            case EMOTION_HAPPY:
                m_bubbles.add(new Bubble(m_sheet.getSprite(0, 1), teen));
                break;
            case EMOTION_SAD:
                m_bubbles.add(new Bubble(m_sheet.getSprite(1, 1), teen));
                break;
            default:
                throw new AssertionError(teen.getM_curentEmotion().name());
        }
    }
    
    public void updateBubbles(){
            for(Bubble bubble : m_bubbles){
                if(bubble != null){
                    bubble.update();
                }
            }
    }
    
    public void drawBubbles(Camera cam){
        if(isM_shouldDraw()){
            for(Bubble bubble : m_bubbles){
                bubble.draw(cam.getM_position());
            }
        }
    }
}
