/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiPackage;

import CharacterPackage.Killer;
import CharacterPackage.Teenager;
import CharacterPackage.TeenagerManager;
import MapPackage.Map;
import TrapPackage.TrapManager;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Vector2f;
import scarymovie.ResourceManager;

/**
 *
 * @author CarlosEduardo
 */
public class Gui {
    //Os diferentes elementos da GUI:
    public enum GUI_ELEMENTS{
        GUI_SELECTED_TEEN_ARROW,
        GUI_MAIN_MENU_SELECT_ARROW,
        GUI_CHECKBOX_BTN_SPRITE,
        GUI_CHECKBOX_BTN_TRUE,
        GUI_CHECKBOX_BTN_FALSE,
        GUI_SELECTED_TEEN_GUI_BACKGROUND,
        GUI_MAP_INFO_BACKGROUND,
    }
    
    //
    public enum WARNING_TYPES{
        WARNING_HUNTING,
        WARNING_PLANNING,
        WARNING_CHOOSE_SPAWN_POINT,
    }
    
    //Membros:
    private int m_score = 0;
    ScreenWarning m_currentWarning = null;
    UnicodeFont m_guiFont = null;
    private Teenager m_selectedTeen = null;
    
    //Imagens usadas:
    Image m_PlanningPhaseSign, m_HuntingPhaseSign, m_chooseSpawnSign ,m_selectedTeenBG, m_mapInfoBG;
    Animation m_selectedTeenArrow;
    
    static private Gui instance = null;
    
    //Singleton:
    static public Gui getInstance(){
        if(instance == null){
            try{
                instance = new Gui();
            }
            catch(SlickException e){
                
            }
        }
        
        return instance;
    }
    
    //Cosntrutor:
    public Gui() throws SlickException{
        //Carregando as imagens:
        m_PlanningPhaseSign = new Image("data/PlanningPhaseSign.png");
        m_HuntingPhaseSign = new Image("data/HuntingPhaseSign.png");
        m_chooseSpawnSign = new Image("data/SpawnPointSign.png");
        
        m_selectedTeenBG = ResourceManager.getInstance().getGuiElement(GUI_ELEMENTS.GUI_SELECTED_TEEN_GUI_BACKGROUND)[0];
        m_mapInfoBG = ResourceManager.getInstance().getGuiElement(GUI_ELEMENTS.GUI_MAP_INFO_BACKGROUND)[0];
        m_selectedTeenArrow = new Animation(ResourceManager.getInstance().getGuiElement(GUI_ELEMENTS.GUI_SELECTED_TEEN_ARROW), 300);
        
        //Carregando a fonte:
        m_guiFont = new UnicodeFont("data/Bloody.ttf", 24, false, false);
        m_guiFont.addAsciiGlyphs();
        m_guiFont.getEffects().add(new ColorEffect());
        m_guiFont.loadGlyphs();
        
        m_currentWarning = null;
    }
    
    public void drawHuntingGui(){
        //Temos algum aviso?
        if(m_currentWarning != null)
            m_currentWarning.draw();
    }
    
    public void drawPlanningGui() throws SlickException{
        //Temos algum aviso?
        if(m_currentWarning != null)
            m_currentWarning.draw();
        
        //Desenhando os BG's:
        m_selectedTeenBG.draw(0, 600 - 128);
        m_mapInfoBG.draw(800 - 320, 600 - 128);
        
        //Desenhando a map badge:
        Map.getInstance(null).getMapBadge().draw(800 - 100, 600 - 128);
        m_guiFont.drawString(560, 513, Map.getInstance(null).getMapName());
        m_guiFont.drawString(630, 537, String.valueOf(TeenagerManager.getInstance(null).getM_teenagers().size()));
        m_guiFont.drawString(640, 563, String.valueOf(TrapManager.getInstance().getTotalTraps()));
        
        
        //Temos ums teen selecionado?
        if(m_selectedTeen != null){
            //Desenhando as informações dele:
            m_guiFont.drawString(175, 515, m_selectedTeen.getM_ai().getM_curentEmotion().m_name);
            m_guiFont.drawString(185, 538, String.valueOf(m_selectedTeen.getM_ai().getM_curiosity()));
            m_guiFont.drawString(150, 563, String.valueOf(m_selectedTeen.getM_ai().getM_fear()));
            
            //Desenhando ele:
            m_selectedTeen.getCurrentAnimation().draw(35, 600 - 85);
            
            //Desenhando a flecha em cima dele:
            m_selectedTeenArrow.draw(m_selectedTeen.getM_TranslatedPosition().x, m_selectedTeen.getM_TranslatedPosition().y - 32);
        }
    }
    
    //Atualiza os componentes do jogo:
    public void update(Killer killer, Map map, TeenagerManager teenManager, TrapManager trapsManager){
        //Temos algum aviso?
        if(m_currentWarning != null){
            m_currentWarning.update();
            
            //Pronto apra apagar?
            if(m_currentWarning.isM_readyToDelete()){
                m_currentWarning = null;
            }
        }
    }
    
    public void showScreenWarning(WARNING_TYPES type){
        if(m_currentWarning != null){
            m_currentWarning = null;
        }
        
        if(type == WARNING_TYPES.WARNING_HUNTING){
            m_currentWarning = new ScreenWarning(m_HuntingPhaseSign, new Vector2f(-400, 100), Killer.DIRECTIONS.DIR_RIGHT);
        }else if(type == WARNING_TYPES.WARNING_PLANNING){
            m_currentWarning = new ScreenWarning(m_PlanningPhaseSign, new Vector2f(-400, 100), Killer.DIRECTIONS.DIR_RIGHT);
        }
        else{
            m_currentWarning = new ScreenWarning(m_chooseSpawnSign, new Vector2f(-400, 100), Killer.DIRECTIONS.DIR_RIGHT);
        }
    }

    /**
     * @return the m_score
     */
    public int getM_score() {
        return m_score;
    }

    /**
     * @param m_score the m_score to set
     */
    public void setM_score(int m_score) {
        this.m_score = m_score;
    }
    
    /**
     * @return the m_selectedTeen
     */
    public Teenager getM_selectedTeen() {
        return m_selectedTeen;
    }

    /**
     * @param m_selectedTeen the m_selectedTeen to set
     */
    public void setM_selectedTeen(Teenager m_selectedTeen) {
        this.m_selectedTeen = m_selectedTeen;
    }
}
