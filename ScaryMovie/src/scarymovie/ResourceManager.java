/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scarymovie;

import CharacterPackage.Teenager;
import GuiPackage.Gui;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Anderson
 */
public class ResourceManager {
    //Membros:
    SpriteSheet m_sprites = null;
    SpriteSheet m_trapSprites = null;
    SpriteSheet m_guiSpriteSheet = null;
    
    private static ResourceManager instance = null;
    
    public static ResourceManager getInstance() throws SlickException{
        if(instance == null){
            instance = new ResourceManager();
        }
        
        return instance;
    }

    //Construtor:
    public ResourceManager() throws SlickException{
        //Carregando a imagem:
        m_sprites = new SpriteSheet("data/TeenKillerSheet.png", 32, 64);
        m_trapSprites = new SpriteSheet("data/TrapSheet.png", 32, 32);
        m_guiSpriteSheet = new SpriteSheet("data/GuiSpriteSheet.png", 32, 32);
    }
    
    //Retorna uma array com as imagens dos teens:
    public Image[] getTeenAnimation(CharacterPackage.Teenager.TEENAGER_GENDER gender, Teenager.MOVEMENT_STATES state){
        Image[] temp = null;
        
        if(gender == Teenager.TEENAGER_GENDER.GENDER_MALE){
            //Que estado queremos obter?
            switch(state){
                case STATE_STANDING:
                    temp = new Image[1];
                    
                    temp[0] = m_sprites.getSprite(0, 0);
                    break;
                case STATE_WALKING_LEFT:
                    temp = new Image[2];
                    
                    temp[0] = m_sprites.getSprite(2, 0);
                    temp[1] = m_sprites.getSprite(3, 0);
                    break;
                case STATE_WALKING_RIGHT:
                    temp = new Image[2];
                    
                    temp[0] = m_sprites.getSprite(4, 0);
                    temp[1] = m_sprites.getSprite(5, 0);
                    break;
                case STATE_WALKING_UP:
                    temp = new Image[2];
                    
                    temp[0] = m_sprites.getSprite(6, 0);
                    temp[1] = m_sprites.getSprite(7, 0);
                    break;
                case STATE_WALKING_DOWN:
                    temp = new Image[2];
                    
                    temp[0] = m_sprites.getSprite(0, 0);
                    temp[1] = m_sprites.getSprite(1, 0);
                    break;
                case STATE_SWIMMING:
                    temp = new Image[2];
                    
                    temp[0] = m_sprites.getSprite(11, 0);
                    temp[1] = m_sprites.getSprite(12, 0);
                    break;
                case STATE_DEAD:
                    temp = new Image[1];
                    
                    temp[0] = m_sprites.getSubImage(416, 0, 64, 32);
                    break;
                default:
                    throw new AssertionError(state.name());
            }
        }else{
            //Que estado queremos obter?
            switch(state){
                case STATE_STANDING:
                    temp = new Image[1];
                    
                    temp[0] = m_sprites.getSprite(0, 1);
                    break;
                case STATE_WALKING_LEFT:
                    temp = new Image[2];
                    
                    temp[0] = m_sprites.getSprite(2, 1);
                    temp[1] = m_sprites.getSprite(3, 1);
                    break;
                case STATE_WALKING_RIGHT:
                    temp = new Image[2];
                    
                    temp[0] = m_sprites.getSprite(4, 1);
                    temp[1] = m_sprites.getSprite(5, 1);
                    break;
                case STATE_WALKING_UP:
                    temp = new Image[2];
                    
                    temp[0] = m_sprites.getSprite(6, 1);
                    temp[1] = m_sprites.getSprite(7, 1);
                    break;
                case STATE_WALKING_DOWN:
                    temp = new Image[2];
                    
                    temp[0] = m_sprites.getSprite(0, 1);
                    temp[1] = m_sprites.getSprite(1, 1);
                    break;
                case STATE_SWIMMING:
                    temp = new Image[2];
                    
                    temp[0] = m_sprites.getSprite(11, 1);
                    temp[1] = m_sprites.getSprite(12, 1);
                    break;
                case STATE_DEAD:
                    temp = new Image[1];
                    
                    temp[0] = m_sprites.getSubImage(416, 32, 64, 32);
                    break;
                default:
                    throw new AssertionError(state.name());
            }
        }
        
        return temp;
    }
    
    public Image[] getSpawnAnimation(){
        Image[] temp = new Image[2];
        
        temp[0] = m_sprites.getSprite(0 , 3);
        temp[1] = m_sprites.getSprite(1 , 3);
        
        return temp;
    }
    
    public Image[] getKillerAnimation(Teenager.MOVEMENT_STATES state){
        Image[] temp = null;
        
        //Que estado queremos obter?
        switch(state){
            case STATE_STANDING:
                temp = new Image[1];

                temp[0] = m_sprites.getSprite(0, 2);
                break;
            case STATE_WALKING_LEFT:
                temp = new Image[2];

                temp[0] = m_sprites.getSprite(2, 2);
                temp[1] = m_sprites.getSprite(3, 2);
                break;
            case STATE_WALKING_RIGHT:
                temp = new Image[2];

                temp[0] = m_sprites.getSprite(4, 2);
                temp[1] = m_sprites.getSprite(5, 2);
                break;
            case STATE_WALKING_UP:
                temp = new Image[2];

                temp[0] = m_sprites.getSprite(6, 2);
                temp[1] = m_sprites.getSprite(7, 2);
                break;
            case STATE_WALKING_DOWN:
                temp = new Image[2];

                temp[0] = m_sprites.getSprite(0, 2);
                temp[1] = m_sprites.getSprite(1, 2);
                break;
            case STATE_SWIMMING:
                temp = new Image[2];

                temp[0] = m_sprites.getSprite(3, 2);
                temp[1] = m_sprites.getSprite(4, 2);
                break;
            case STATE_ATACK_LEFT:
                temp = new Image[2];

                temp[0] = m_sprites.getSprite(8, 2);
                temp[1] = m_sprites.getSprite(9, 2);
                break;
            case STATE_ATACK_RIGHT:
                temp = new Image[2];

                temp[0] = m_sprites.getSprite(10, 2);
                temp[1] = m_sprites.getSprite(11, 2);
                break;
            case STATE_ATACK_UP:
                temp = new Image[2];

                temp[0] = m_sprites.getSprite(12, 2);
                temp[1] = m_sprites.getSprite(13, 2);
                break;
            case STATE_ATACK_DOWN:
                temp = new Image[2];

                temp[0] = m_sprites.getSprite(14, 2);
                temp[1] = m_sprites.getSprite(15, 2);
                break;
            default:
                throw new AssertionError(state.name());
        }
        
        return temp;
    }
    
    public Image[] getTrapAnimation(TrapPackage.TrapType.TRAP_ID id){
        Image[] temp = null;
        
        //Qual o tipo de trap?
        switch(id){
            case TRAP_BEER_BOTTLE:
                temp = new Image[2];
                temp[0] = m_trapSprites.getSprite(0, 0);
                temp[1] = m_trapSprites.getSprite(1, 0);
                break;
            case TRAP_NUDE_CARD:
                temp = new Image[2];
                temp[0] = m_trapSprites.getSprite(2, 0);
                temp[1] = m_trapSprites.getSprite(3, 0);
                break;
             
            default:
                throw new AssertionError(id.name());
        }
        
        return temp;
    }
    
    public Image[] getGuiElement(Gui.GUI_ELEMENTS element){
        Image temp[] = null;
        
        switch(element){
            case GUI_SELECTED_TEEN_ARROW:
                temp = new Image[2];
                
                temp[0] = m_guiSpriteSheet.getSprite(0, 0);
                temp[1] = m_guiSpriteSheet.getSprite(1, 0);
                break;
            case GUI_MAIN_MENU_SELECT_ARROW:
                temp = new Image[2];
                
                temp[0] = m_guiSpriteSheet.getSprite(0, 0);
                temp[1] = m_guiSpriteSheet.getSprite(1, 0);
                
                //Rotacionando apra a direita:
                temp[0].rotate(-90);
                temp[1].rotate(-90);
                break;
            case GUI_CHECKBOX_BTN_SPRITE:
                temp = new Image[1];
                
                temp[0] = m_guiSpriteSheet.getSprite(0, 1);
                break;
            case GUI_CHECKBOX_BTN_TRUE:
                temp = new Image[1];
                
                temp[0] = m_guiSpriteSheet.getSprite(1, 1);
                break;
            case GUI_CHECKBOX_BTN_FALSE:
                temp = new Image[1];
                
                temp[0] = m_guiSpriteSheet.getSprite(2, 1);
                break;
            case GUI_SELECTED_TEEN_GUI_BACKGROUND:
                temp = new Image[1];
                
                temp[0] = m_guiSpriteSheet.getSubImage(0, 64, 320, 128);
                break;
                
            case GUI_MAP_INFO_BACKGROUND:
                temp = new Image[1];
                
                temp[0] = m_guiSpriteSheet.getSubImage(0, 192, 320, 128);
                break;
            default:
                throw new AssertionError(element.name());            
        }
        
        return temp;
    }
}
