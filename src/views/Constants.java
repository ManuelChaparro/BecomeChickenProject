package views;

import java.awt.Font;
import java.util.concurrent.TimeUnit;

public class Constants {
	
	//PATHS IMAGES
	public static final String PATH_CKN_L_1 = "/img/left1.png";
	public static final String PATH_CKN_L_2 = "/img/left2.png";
	public static final String PATH_CKN_L_3 = "/img/left3.png";
	public static final String PATH_CKN_R_1 = "/img/right1.png";
	public static final String PATH_CKN_R_2 = "/img/right2.png";
	public static final String PATH_CKN_R_3 = "/img/right3.png";
	public static final String PATH_CKN_FRONT = "/img/front.png";
	public static final String PATH_CKN_BACK = "/img/back.png";
	public static final String PATH_CKN_OVER = "/img/chickenOver.png";
	public static final String PATH_HEN= "/img/hen.png";
	public static final String PATH_CKNP_L_1 = "/img/chickenPowerL1.png";
	public static final String PATH_CKNP_L_2 = "/img/chickenPowerL2.png";
	public static final String PATH_CKNP_L_3 = "/img/chickenPowerL3.png";
	public static final String PATH_CKNP_R_1 = "/img/chickenPowerR1.png";
	public static final String PATH_CKNP_R_2 = "/img/chickenPowerR2.png";
	public static final String PATH_CKNP_R_3 = "/img/chickenPowerR3.png";
	public static final String PATH_CKNP_FRONT = "/img/chickenPowerF.png";
	public static final String PATH_WOLF_L_1 = "/img/wolfleft1.png"; 
	public static final String PATH_WOLF_L_2 = "/img/wolfleft2.png"; 
	public static final String PATH_WOLF_L_3 = "/img/wolfleft3.png"; 
	public static final String PATH_WOLF_R_1 = "/img/wolfright1.png"; 
	public static final String PATH_WOLF_R_2 = "/img/wolfright2.png"; 
	public static final String PATH_WOLF_R_3 = "/img/wolfright3.png"; 
	public static final String PATH_GHOST_L_1 = "/img/ghostLeft1.png"; 
	public static final String PATH_GHOST_L_2 = "/img/ghostLeft2.png"; 
	public static final String PATH_GHOST_L_3 = "/img/ghostLeft3.png"; 
	public static final String PATH_GHOST_R_1 = "/img/ghostRight1.png"; 
	public static final String PATH_GHOST_R_2 = "/img/ghostRight2.png"; 
	public static final String PATH_GHOST_R_3 = "/img/ghostRight3.png"; 
	public static final String PATH_GHOST_F_1 = "/img/ghostFront1.png"; 
	public static final String PATH_GHOST_F_2 = "/img/ghostFront2.png"; 
	public static final String PATH_GHOST_F_3 = "/img/ghostFront3.png"; 
	public static final String PATH_GHOST_A_1 = "/img/ghostActive1.png"; 
	public static final String PATH_GHOST_A_2 = "/img/ghostActive2.png"; 
	public static final String PATH_GHOST_A_3 = "/img/ghostActive3.png"; 
	public static final String PATH_WIZARD_L_1 = "/img/wizardL1.png";
	public static final String PATH_WIZARD_L_2 = "/img/wizardL2.png";
	public static final String PATH_WIZARD_L_3 = "/img/wizardL3.png";
	public static final String PATH_WIZARD_R_1 = "/img/wizardR1.png";
	public static final String PATH_WIZARD_R_2 = "/img/wizardR2.png";
	public static final String PATH_WIZARD_R_3 = "/img/wizardR3.png";
	public static final String PATH_WIZARD_F_1 = "/img/wizardF1.png";
	public static final String PATH_WIZARD_F_2 = "/img/wizardF2.png";
	public static final String PATH_WIZARD_F_3 = "/img/wizardF3.png";
	public static final String PATH_WALLPAPER_ONE = "/img/wallpaperOne.PNG";
	public static final String PATH_WALLPAPER_TWO = "/img/wallpaperTwo.PNG";
	public static final String PATH_WALLPAPER_THREE = "/img/wallpaperThree.PNG";
	public static final String PATH_WALLPAPER_FINAL = "/img/wallpaperFinal.PNG";
	public static final String PATH_WALL_GME_OVER = "/img/wallpaperGameOver.png";
	public static final String PATH_WALL_INIT = "/img/initWallpaper.jpeg";
	public static final String PATH_WALL_BCOME_CKN = "/img/becomeChicken.png";
	public static final String PATH_ICON_MUSIC = "/img/music.png";
	public static final String PATH_ICON_AUTOSAVE = "/img/save.png";
	public static final String PATH_ICON_SCREENSHOT = "/img/camera.png";
	public static final String PATH_INMUNITY = "/img/inmunity.png";
	public static final String PATH_FAST = "/img/fast.PNG";
	public static final String PATH_ICECREAM = "/img/iceCream.png";
	public static final String PATH_NOTHING = "/img/nada.png";
	
	//PATHS STRINGS
	public static final String TUTORIAL_TITLE = "Eres completamente inmune durante el tutorial...";
	public static final String TUTORIAL_JUMP = "* Para saltar sobre los lobos, presiona 'W'";
	public static final String TUTORIAL_JUMP_MOVE = "* Come los helados para sumar puntos";
	public static final String SKIP_TUTORIAL = "Omite el tutorial con 'T'";
	public static final String TUTORIAL_MOVE = "Los fantasmas blancos son peligrosos!";
	public static final String TUTORIAL_MOVE_SECOND = "* Usa 'A' y 'D' para esquivarlos";
	public static final String TUTORIAL_PAUSE = "* Puedes pausar el juego en cualquier momento pulsando 'ESC'";
	public static final String TUTORIAL_FAST = "Puedes aumentar tu velocidad pulsando 'Shift'";
	public static final String TUTORIAL_FAST_INFO = "* Dura aprox 6 segundos";
	public static final String TUTORIAL_FAST_INFO_2 = "* Se recarga despues de cierto tiempo";
	public static final String TUTORIAL_INMUNITY = "Usa inmunidad para ser invencible! Con 'Espacio'";
	public static final String TUTORIAL_INMUNITY_INFO = "* No es infinito";
	public static final String TUTORIAL_INMUNITY_INFO_2 = "* Solo puedes usarla un numero de veces limitado";
	public static final String TUTORIAL_END_INMUNITY = "Ya no eres inmune...";
	public static final String PATH_DATA_GAME = "dataGame/dataGame.json";
	public static final String PATH_DATA_CHICKEN = "dataGame/chicken.json";
	public static final String STORY_ONE = "Hace mucho tiempo, existia una gallina llamada Dora, la "
			+ "cual tenia una gran cualidad...";
	public static final String STORY_ONE_2 = "ponia huevos de oro.";
	public static final String STORY_TWO = "Era muy conocida, y gracias a ello se gano"
			+ " el reconocimiento de todo el reino.";
	public static final String STORY_THREE = "Pero un dia, un malvado hechicero"
			+ " escucho acerca de Dora, el decidio secuestrarla, ";
	public static final String STORY_THREE_2 = "ya que dichos huevos poseian una gran cantidad de magia";			
	public static final String STORY_FOUR = "Asi, su hijo llamado Dorito, se percato de la"
			+ " desaparicion de su querida madre.";
	public static final String STORY_FIVE = "El, que gracias a su madre poseia poderes magicos, prometio"
			+ " salvarla...";
	public static final String END_GAME_ONE = "Gracias a los poderes de Dorito, el hechicero no "
			+ "fue rival para el...";
	public static final String END_GAME_TWO = "Cansado por el enfrentamiento, el hechicero se"
			+ " da cuenta que agoto toda su magia...";
	public static final String END_GAME_THREE = "Con miedo, libera a Dora, ofreciendo disculpas"
			+ " a Dorito";
	public static final String END_GAME_FOUR = "Finalmente, madre e hijo se encuentran, "
			+ "siendo nuevamente felices...";
	
	//PATHS FONTS
	public static final Font FONT_DEFAULT = new Font("Consolas", Font.PLAIN, 40);
	public static final Font FONT_MIN = new Font("Consolas", Font.PLAIN, 20);
	
	//PATHS NUMBERS
	public static final int FPS = 80;
	public static final long FPMS = TimeUnit.SECONDS.toMillis(1) / FPS;
	public static final int TOP_JUMP = 200;
	public static final int QUANTITY_WOLFS = 30;
	public static final int QUANTITY_GHOSTS = 25;
}
