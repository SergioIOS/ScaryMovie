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
        m_sprites = new SpriteSheet("data/Charset.png", 32, 64);
    }
    
    //Retorna uma array com as imagens dos teens:
    public Image[] getTeenAnimation(CharacterPackage.Teenager.TEENAGER_GENDER gender){
        Image[] temp = new Image[2];
        
        if(gender == Teenager.TEENAGER_GENDER.GENDER_MALE){
            temp[0] = m_sprites.getSprite(0, 0);
            temp[1] = m_sprites.getSprite(1, 0);
        }else{
            temp[0] = m_sprites.getSprite(0, 1);
            temp[1] = m_sprites.getSprite(1, 1);
        }
        
        return temp;
    }
}
