package presenters;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import models.Game;
import models.ScreenService;
import simulationmodels.Montecarlo;
import simulationmodels.RandomWalk;
import test.simulationmodel.TestMontecarlo;
import test.simulationmodel.TestRandomWalk;
import views.Constants;
import views.MainWindow;
import views.ScreenShot;

public class Presenter implements KeyListener {

	private MainWindow window;
	private Timer timer;
	private Game game;
	private volatile long startTime;
	private volatile long lastTime;
	private int timeSaveAndScreenshot;
	private Clip clipMegalovania, clipIntro, clipGame, clipGameOver, clipHit, clipInmunity, clipIceCream, clipFast;
	private boolean right, left, up, option, intro, gameBoolean, gameOver, gameWin, enter, music, pause, musicIntro,
			musicGame, musicMegalovania, musicGameOver, musicIceCream, musicInmunity, musicFast, musicHit, initTimerGameOver, blockPower;
	private int time, timeGame, select, timerGameOver, timerPower, counter, timerInmunity, counterIceCream,
			counterNextIceCream;
	private TestRandomWalk randomWalk;
	private TestMontecarlo montecarlo;
	private ScreenService screenService;

	private static ScheduledExecutorService executorService;

	public Presenter() {
		initModels();
		initMusic();
		initVariables();
		initFrame();
		initTimer();
	}

	private void initModels(){
		screenService = new ScreenService();
		randomWalk = new TestRandomWalk(screenService.getWidth(0.5), screenService.getWidth(1),
				screenService.getHeight(0.5), screenService.getHeight(0.8));
		montecarlo = new TestMontecarlo();
	};

	private void initFrame() {
		window = new MainWindow();
		window.addKeyListener(this);
	}

	private void initVariables() {
		loadGame();
		initBooleanMusic();
		initVariablesGame();
		counter = 0;
		timerGameOver = 0;
		counterIceCream = 2000;
		counterNextIceCream = 4000;
		timeSaveAndScreenshot = 10000;
	}
	
	private void loadGame() {
		JsonParser json = new JsonParser();
		Gson gson = new Gson();
		try {
			Object string = json.parse(new FileReader(Constants.PATH_DATA_GAME));
			game = gson.fromJson(string.toString(), Game.class);
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void initVariablesGame() {
		pause = false;
		intro = true;
		option = false;
		gameBoolean = false;
		gameOver = false;
		initTimerGameOver = false;
		blockPower = true;
		gameWin = false;
	}

	private void initBooleanMusic() {
		music = true;
		musicGame = false;
		musicIntro = true;
		musicIceCream = false;
		musicInmunity = false;
		musicMegalovania = false;
	}

	private void initSave() {
		Thread save = new Thread(new Runnable() {
			public void run() {
				try {
					saveGame();
				} catch (Exception e) {
				}
			}

			private void saveGame() throws InterruptedException, IOException {
				game.setAutosave(true);
				Thread.sleep(2000);
				game.setDate("Ultima partida jugada el: " + Calendar.getInstance().getTime().toString());
				FileWriter fileWriter;
				fileWriter = new FileWriter(Constants.PATH_DATA_GAME);
				fileWriter.write(new Gson().toJson(game));
				fileWriter.close();
				game.setAutosave(false);
			}
		});
		save.start();
	}

	private void initMusic() {
		initMusicIntro();
		initMusicGame();
		initMusicGameOver();
		initMusicHit();
		initMusicInmunity();
		initMusicIceCream();
		initMusicFast();
		initMusicMegalovania();
		Thread threadMusic = new Thread(new Runnable() {
			public void run() {
				while (true) {
					if (music) {
						managerMusicGame();
						managerMusicActions();
					} else {
						clipIntro.stop();
						clipGame.stop();
						clipGameOver.stop();
					}
				}
			}

			private void managerMusicActions() {
				if (musicIceCream) {
					clipIceCream.setFramePosition(0);
					clipIceCream.start();
					musicIceCream = false;
				}
				if (musicInmunity) {
					clipInmunity.setFramePosition(0);
					clipInmunity.start();
					musicInmunity = false;
				}
				if (musicFast) {
					clipFast.setFramePosition(0);
					clipFast.start();
					musicFast = false;
				}
				if (musicHit) {
					clipHit.setFramePosition(0);
					clipHit.start();
					musicHit = false;
				}
			}

			private void managerMusicGame() {
				if (musicIntro) {
					clipGame.stop();
					clipIntro.start();
					clipMegalovania.stop();
				} else if (musicGame) {
					clipIntro.stop();
					clipGameOver.stop();
					clipGame.start();
					clipGame.loop(Clip.LOOP_CONTINUOUSLY);
				} else if (musicGameOver) {
					clipGame.stop();
					clipMegalovania.stop();
					clipGameOver.start();
				} else if (musicMegalovania) {
					clipGame.stop();
					clipGameOver.stop();
					clipMegalovania.start();
				}  else {
					clipIntro.stop();
					clipGame.stop();
					clipGameOver.stop();
					clipMegalovania.stop();
				}
			}
		});
		threadMusic.start();
	}

	private void initMusicFast() {
		try {
			AudioInputStream sound = AudioSystem.getAudioInputStream(new File("sounds/fast.wav"));
			clipFast = AudioSystem.getClip();
			clipFast.open(sound);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initMusicMegalovania() {
		try {
			AudioInputStream sound = AudioSystem.getAudioInputStream(new File("sounds/megalovania.wav"));
			clipMegalovania = AudioSystem.getClip();
			clipMegalovania.open(sound);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initMusicIceCream() {
		try {
			AudioInputStream sound = AudioSystem.getAudioInputStream(new File("sounds/iceCream.wav"));
			clipIceCream = AudioSystem.getClip();
			clipIceCream.open(sound);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initMusicInmunity() {
		try {
			AudioInputStream sound = AudioSystem.getAudioInputStream(new File("sounds/inmunity.wav"));
			clipInmunity = AudioSystem.getClip();
			clipInmunity.open(sound);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initMusicHit() {
		try {
			AudioInputStream sound = AudioSystem.getAudioInputStream(new File("sounds/hit.wav"));
			clipHit = AudioSystem.getClip();
			clipHit.open(sound);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initMusicGameOver() {
		try {
			AudioInputStream sound = AudioSystem.getAudioInputStream(new File("sounds/gameOver.wav"));
			clipGameOver = AudioSystem.getClip();
			clipGameOver.open(sound);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initMusicGame() {
		try {
			AudioInputStream sound = AudioSystem.getAudioInputStream(new File("sounds/gameSound.wav"));
			clipGame = AudioSystem.getClip();
			clipGame.open(sound);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initMusicIntro() {
		try {
			AudioInputStream sound = AudioSystem.getAudioInputStream(new File("sounds/intro.wav"));
			clipIntro = AudioSystem.getClip();
			clipIntro.open(sound);
			clipIntro.start();
			musicIntro = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initTimer() {
		timer = new Timer((int) Constants.FPMS, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				calibrateFps();
				time += 10;
				if (intro) {
					timerIntro();
				} else if (option) {
					timerOption();
				} else if (gameBoolean) {
					timeGame += 10;
					verifySaveAndScreenshot();
					timerGame();
				} else if (gameOver) {
					timerGameOver();
				} else if (gameWin) {
					timerGameWin();
				}
			}

			private void timerGameWin() {
				if (time <= 5000) {
					window.endFirstAct();
				} else if (time > 5000 && time <= 10000) {
					window.endSecondAct();
				} else if (time > 10000 && time <= 15000) {
					window.endThirdAct();
				} else if (time > 15000 && time <= 18000) {
					window.endFourAct();
				} else if (time > 18000) {
					window.changeCardLayout("Option");
					intro = false;
					option = true;
				}
				
			}

			private void calibrateFps() {
				try {
					startTime = System.nanoTime();
					Toolkit.getDefaultToolkit().sync();
					long delta = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
					int delay = (int) (Constants.FPS - delta) < 0 ? 1 : (int) (Constants.FPMS - delta);
					timer.setDelay(delay);
					if (System.nanoTime() - lastTime >= TimeUnit.SECONDS.toNanos(1)) {
						lastTime = System.nanoTime();
					}
				} catch (Exception e) {}
			}

			private void verifySaveAndScreenshot() {
				if (timeGame >= timeSaveAndScreenshot) {
					initSave();
					timeSaveAndScreenshot += 10000;
				}
			}

			private void timerGameOver() {
				if (select == 1) {
					window.optionInitGame();
					if (enter) {
						game = new Game(true);
						restartGame();
					}
				} else if(select == 2){
					window.loadGame();
					loadGame();
					if (enter) {
						window.setGame(game);
						window.changeCardLayout("MainPanel");
						option = false;
						musicIntro = false;
						musicGame = true;
						gameBoolean = true;
						musicGameOver = false;
					}
				} else if(select == 3) {
					select = 3;
					window.exit();
					if (enter) {
						System.exit(0);
						window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}
				}
				if (initTimerGameOver) {
					timerGameOver += 10;
					if (timerGameOver == 2000) {
						window.changeCardLayout("GameOver");
						clipGameOver.setFramePosition(0);
						musicGameOver = true;
						musicGame = false;
						initTimerGameOver = false;
					}
				}
			}

			private void restartGame() {
				gameOver = false;
				gameBoolean = true;
				musicIntro = false;
				musicGame = true;
				musicGameOver = false;
				option = false;
				window.changeCardLayout("MainPanel");
				clipGame.setFramePosition(0);
				initTimerGameOver = false;
			}

			private void timerGame() {
				if (!pause) {
					game.setIconMusic(music);
					window.setGame(game);
					moveIceCream();
					checkWinGame();
					checkLifes();
					checkFast();
					checkInmunity();
					checkIceCreams();
					checkMusicMegalovania();
					game.moveEnemies();
					window.setLevel(game.getLevel());
					if (right && !left) {
						game.chickenRight();
					}
					if (left && !right) {
						game.chickenLeft();
					}
					if (up) {
						up = game.chickenJump();
					}
					if (!right && !left && !up) {
						game.startStatic();
					}
				}
			}

			private void moveIceCream(){
				if(time % 100 == 0){
					game.setIceCreamX(randomWalk.getNextX());
					game.setIceCreamY(randomWalk.getNextY());
				}
			}

			private void checkMusicMegalovania() {
				if (game.getStage() == 10 && game.checkLifes()==false) {
					musicGame = false;
					musicMegalovania = true;
				}	
			}

			private void checkWinGame() {
				if (game.isWinGame()) {
					game.pause();
					gameBoolean = false;
					gameWin = true;
					gameOver = false;
					musicGame = false;
					musicIntro = true;
					musicMegalovania = false;
					time = 0;
					clipIntro.setFramePosition(0);
					window.changeCardLayout("EndGame");
				}
			}

			private void checkIceCreams() {
				if (time == counterIceCream) {
					game.setShowIceCreams(true);
					musicIceCream = true;
				} else if (time > counterNextIceCream) {
					game.setShowIceCreams(false);
					musicIceCream = false;
					counterIceCream += 4000;
					counterNextIceCream += 4000;
				}

			}

			private void checkInmunity() {
				if (game.isInmunity()) {
					game.setBlockPowers(false);
					timerInmunity += 10;
					if (timerInmunity == 10) {
						game.setInmunity(true);
						musicInmunity = true;
					} else if (timerInmunity > 10000) {
						game.setInmunity(false);
						game.setQuantityInmunity(game.getQuantityInmunity() - 1);
						timerInmunity = 0;
						blockPower = true;
					}
				}

			}

			private void checkFast() {
				if (game.getPowerUp()) {
					game.setBlockPowers(false);
					timerPower += 10;
					if (timerPower == 10) {
						musicFast = true;
						game.setPower(5);
					} else if (timerPower == 1000) {
						game.setPower(4);
					} else if (timerPower == 2000) {
						game.setPower(3);
					} else if (timerPower == 4000) {
						game.setPower(2);
					} else if (timerPower == 6000) {
						game.setPower(1);
					} else if (timerPower > 25000) {
						timerPower = 0;
						game.setPower(0);
						game.setPowerUp(false);
						blockPower = true;
					}
				}
			}

			private void checkLifes() {
				if (game.checkLifes()) {
					musicHit = true;
					game.pause();
					gameBoolean = false;
					gameOver = true;
					musicGame = false;
					musicMegalovania = false;
					initTimerGameOver = true;
					timerGameOver = 0;
				}
			}

			private void timerOption() {
				if (select == 1) {
					window.optionInitGame();
					if (enter) {
						window.setGame(game);
						game = new Game(true);
						window.changeCardLayout("MainPanel");
						option = false;
						musicIntro = false;
						musicGame = true;
						gameBoolean = true;
					}
				} else if (select == 2) {
					window.optionLoadGame(game.getDate().toString());
					if (enter) {
						window.setGame(game);
						window.changeCardLayout("MainPanel");
						option = false;
						musicIntro = false;
						musicGame = true;
						gameBoolean = true;
					}
				} else if (select == 3) {
					window.optionExitGame();
					if (enter) {
						System.exit(0);
						window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}
				}
			}

			private void timerIntro() {
				if (!right || !left || !up) {
					if (time <= 5000) {
						window.initFirstAct();
					} else if (time > 5000 && time <= 10000) {
						window.initSecondAct();
					} else if (time > 10000 && time <= 15000) {
						window.initThirdAct();
					} else if (time > 15000 && time <= 18000) {
						window.initFourAct();
					} else if (time > 18000 && time <= 23000) {
						window.initFiveAct();
					} else if (time > 23000) {
						window.changeCardLayout("Option");
						intro = false;
						option = true;
					}
				} else {
					window.changeCardLayout("Option");
					intro = false;
					option = true;
				}
			}

		});
		timer.start();
		lastTime = System.nanoTime();
	}

	public static void main(String[] args) {
		new Presenter();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			game.setMoveGhost(pause);
			pause = !pause;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			left = true;
			if (select > 1) {
				select--;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			right = true;
			if (select < 3) {
				select++;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			up = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			enter = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_M) {
			music = !music;
			game.setIconMusic(music);
			window.setIconMusic(music);
		}
		if (e.getKeyCode() == KeyEvent.VK_T) {
			game.skipTutorial();
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			if (blockPower) {
				game.setPowerUp(true);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (blockPower && game.getQuantityInmunity() != 0) {
				game.setInmunity(true);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) {
			left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			right = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			enter = false;
		}
	}

	public void keyTyped(KeyEvent e) {
	}
}
