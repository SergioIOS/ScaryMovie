/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scarymovie;

import CharacterPackage.Teenager;
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

    //Construtor:
    public ResourceManager() throws SlickException{
        //Carregando a imagem:
        m_sprites = new SpriteSheet("data/TeenKillerSheet.png", 32, 64);
    }
    
    //Retorna uma array com as imagens dos teens:
    public Image[] getTeenAnimation(CharacterPackage.Teenager.TEENAGER_GENDER gender, Teenager.MOVEMENT_STATES state){
        Image[] temp = null;
        
        if(gender == Teenager.TEENAGER_GENDER.GENDER_MALE){
            //Que estado queremos obter?
            switch(state){
                case STATE_STANDING:
                    temp = new Image[3];
                    
                    temp[0] = m_sprites.getSprite(0, 0);
                    temp[1] = m_sprites.getSprite(1, 0);
                    temp[2] = m_sprites.getSprite(2, 0);
                    break;
                case STATE_WALKING_LEFT:
                    
                    break;
                case STATE_WALKING_RIGHT:
                    
                    break;
                case STATE_WALKING_UP:
                    
                    break;
                case STATE_WALKING_DOWN:
                    
                    break;
                case STATE_SWIMMING:
                    temp = new Image[2];
                    
                    temp[0] = m_sprites.getSprite(3, 0);
                    temp[1] = m_sprites.getSprite(4, 0);
                    break;
                default:
                    throw new AssertionError(state.name());
            }
        }else{
            //Que estado queremos obter?
            switch(state){
                case STATE_STANDING:
                    temp = new Image[3];
                    
                    temp[0] = m_sprites.getSprite(0, 1);
                    temp[1] = m_sprites.getSprite(1, 1);
                    temp[2] = m_sprites.getSprite(2, 1);
                    break;
                case STATE_WALKING_LEFT:
                    
                    break;
                case STATE_WALKING_RIGHT:
                    
                    break;
                case STATE_WALKING_UP:
                    
                    break;
                case STATE_WALKING_DOWN:
                    
                    break;
                case STATE_SWIMMING:
                    temp = new Image[2];
                    
                    temp[0] = m_sprites.getSprite(3, 1);
                    temp[1] = m_sprites.getSprite(4, 1);
                    break;
                default:
                    throw new AssertionError(state.name());
            }
            
        }
        
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

                temp[0] = m_sprites.getSprite(3, 0);
                temp[1] = m_sprites.getSprite(4, 0);
                break;
            default:
                throw new AssertionError(state.name());
        }
        
        return temp;
    }
}
