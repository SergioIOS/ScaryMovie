/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scarymovie;

import StatesPackage.GameOver;
import StatesPackage.GamePlay;
import StatesPackage.Intro;
import StatesPackage.MainMenu;
import StatesPackage.MapChooser;
import StatesPackage.Options;
import StatesPackage.Pause;
import StatesPackage.PlanningPhaseState;
import StatesPackage.ScoreTable;
import StatesPackage.Shop;
import java.io.File;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author CarlosEduardo
 */
public class ScaryMovie extends StateBasedGame {
    //Estados:
    public static final int INTRO_STATE = 0;
    public static final int MAINMENU_STATE = 1;
    public static final int OPTIONS_STATE = 2;
    public static final int SHOP_STATE = 3;
    public static final int MAPCHOOSER_STATE = 4;
    public static final int GAMEPLAY_STATE = 5;
    public static final int PAUSE_STATE = 6;
    public static final int SCORETABLE_STATE = 7;
    public static final int GAMEOVER_STATE = 8;
    public static final int PLANNINGPHASE_STATE = 9;
    
    //Construtor:
    public ScaryMovie(String tituloDaJanela){
        super(tituloDaJanela);
        
        this.addState(new Intro(INTRO_STATE));
        this.addState(new MainMenu(MAINMENU_STATE));
        this.addState(new Options(OPTIONS_STATE));
        this.addState(new Shop(SHOP_STATE));
        this.addState(new MapChooser(MAPCHOOSER_STATE));
        this.addState(new GamePlay(GAMEPLAY_STATE));
        this.addState(new Pause(PAUSE_STATE));
        this.addState(new ScoreTable(SCORETABLE_STATE));
        this.addState(new GameOver(GAMEOVER_STATE));
        this.addState(new PlanningPhaseState(PLANNINGPHASE_STATE));
    }
    
    //Inicializa os estados armazenados:
    @Override
    public void initStatesList(GameContainer gc) throws SlickException{
        this.getState(INTRO_STATE).init(gc, this);
        this.getState(MAINMENU_STATE).init(gc, this);
        this.getState(OPTIONS_STATE).init(gc, this);
        this.getState(SHOP_STATE).init(gc, this);
        this.getState(MAPCHOOSER_STATE).init(gc, this);
        this.getState(GAMEPLAY_STATE).init(gc, this);
        this.getState(PAUSE_STATE).init(gc, this);
        this.getState(SCORETABLE_STATE).init(gc, this);
        this.getState(GAMEOVER_STATE).init(gc, this);
        this.getState(PLANNINGPHASE_STATE).init(gc, this);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SlickException {
        //System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
        
        System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "natives"), LWJGLUtil.getPlatformName()).getAbsolutePath());
        System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
        
        AppGameContainer appgc;
        
        appgc = new AppGameContainer(new ScaryMovie("Scary Movie"));
        appgc.setDisplayMode(800, 600, false);
        appgc.setVSync(true);
        appgc.setTargetFrameRate(60);
        appgc.setMultiSample(0);
        appgc.start();
    }
}
