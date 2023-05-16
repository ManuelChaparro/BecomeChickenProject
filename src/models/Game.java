package models;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import views.Constants;

public class Game {

	private ConcurrentHashMap<Integer, Wolf> wolfs;
	private ConcurrentHashMap<Integer, Ghost> ghosts;
	private int stage = 1;
	private int distanceWolfs = 600;
	private int quantityInmunity;
	private int points;
	private int bestPoint;
	private boolean tutorial, moveWolfs, moveGhost, stageWolfs, stageGhosts, stageWizard, resetPositionWolf, inmunity,
			lostLife, music, autosave, powerUp, blockPowers, showIceCreams, screenShot;
	private int timeGhost, counterGhosts, counterWizard;
	private Chicken chicken;
	private IceCream iceCream;
	private Random random;
	private Wizard wizard;
	private boolean visibleGhost;
	private String date = "";
	private boolean moveWizardRight;
	private boolean moveWizardLeft;
	private boolean winGame;
	private boolean moveWizard;

	public Game(boolean init) {
		if (init) {
			initVariables();
			createWolfs();
			createGhosts();
		}
	}

	public void moveEnemies() {
		checkColisionIceCream();
		switch (stage) {
		case 1:
			if (stageWolfs) {
				stageWolfs(4);
			} else {
				stageGhosts(2);
			}
			break;
		case 2:
			if (stageWolfs) {
				stageWolfs(4);
			} else {
				stageGhosts(4);
			}
			break;
		case 3:
			tutorial = false;
			if (stageWolfs) {
				stageWolfs(8);
			} else {
				stageGhosts(6);
			}
			break;
		case 4:
			if (stageWolfs) {
				stageWolfs(12);
			} else {
				stageGhosts(8);
			}
			break;
		case 5:
			if (stageWolfs) {
				stageWolfs(16);
			} else {
				stageGhosts(10);
			}
			break;
		case 6:
			distanceWolfs = 500;
			if (stageWolfs) {
				stageWolfs(20);
			} else {
				stageGhosts(12);
			}
			break;
		case 7:
			distanceWolfs = 500;
			if (stageWolfs) {
				stageWolfs(24);
			} else {
				stageGhosts(14);
			}
			break;
		case 8:
			distanceWolfs = 480;
			if (stageWolfs) {
				stageWolfs(28);
			} else {
				stageGhosts(16);
			}
			break;
		case 9:
			distanceWolfs = 460;
			if (stageWolfs) {
				stageWolfs(30);
			} else {
				stageGhosts(20);
			}
			break;
		case 10:
			tutorial = false;
			stageWizard();
			break;
		}
	}

	private void stageWizard() {
		if (moveWizard) {
			checkColisionWizard();
			moveWizard();
		}
	}

	private void moveWizard() {
		counterWizard += 10;
		if (counterWizard > 0 && counterWizard < 20000) {
			moveWizardOne();
		} else if (counterWizard >= 20000 && counterWizard < 45000) {
			moveWizardTwo();
			counterGhosts = 0;
		} else if (counterWizard >= 45000 && counterWizard < 73000) {
			moveWizardThree();
		} else{
			winGame();
		}
	}

	private void winGame() {
		winGame = true;
	}

	private void moveWizardThree() {
		if (counterWizard > 45000 && counterWizard < 50000) {
			wizard.setShow(true);
			wizard.moveSpriteFront();
			wizard.setHorizontalMove(false);
		} else if(counterWizard > 70000 && counterWizard <= 73000) {
			wizard.setShow(false);
		} else {
			visibleGhost = true;
			moveGhost = true;
			stageGhosts = true;
			wizard.setHorizontalMove(true);
			stageGhosts(5);
			moveWizardRandom();
		}
	}

	private void moveWizardTwo() {
		wizard.setY(410);
		if (counterWizard >= 20000 && counterWizard < 23000) {
			wizard.setShow(true);
			wizard.moveSpriteFront();
			wizard.setHorizontalMove(false);
			stageWolfs = true;
		} else if(counterWizard > 40000 && counterWizard <= 45000) {
			wizard.setShow(false);
			wolfs.clear();
		} else {
			if (wizard.isShow()) {
				stageWolfs(16);
				moveWizardLeftAndRight();
			}
		}
	}

	private void moveWizardLeftAndRight() {
		if (wizard.getX() == 20 || moveWizardRight) {
			moveWizardRight = true;
			moveWizardLeft = false;
			wizard.moveRight();
		}
		if (wizard.getX() == 1200 || moveWizardLeft) {
			moveWizardLeft = true;
			moveWizardRight = false;
			wizard.moveLeft();
		}	
	}

	private void moveWizardOne() {
		if (counterWizard > 0 && counterWizard < 3000) {
			wizard.setShow(true);
			wizard.moveSpriteFront();
			wizard.setHorizontalMove(false);
		} else if(counterWizard > 17000 && counterWizard <= 20000) {
			wizard.setShow(false);
		} else {
			wizard.setHorizontalMove(true);
			moveWizardRandom();
		}
	}
	
	private void moveWizardRandom() {
		wizard.moveUp();
		if (wizard.getX() == 0 || moveWizardRight) {
			moveWizardRight = true;
			moveWizardLeft = false;
			wizard.moveRight();
		}
		if (wizard.getX() == 1280 || moveWizardLeft) {
			moveWizardLeft = true;
			moveWizardRight = false;
			wizard.moveLeft();
		}
	}

	private void checkColisionWizard() {
		if (wizard.getRectangle().intersects(chicken.getRectangle()) && inmunity == false
				&& wizard.isShow()) {
			lostLife = true;
		}
	}

	private void initVariables() {
		initObjects();
		initBooleans();
		quantityInmunity = 4;
		points = 0;
		counterWizard = 0;
	}

	private void initObjects() {
		random = new Random();
		wizard = new Wizard(Constants.PATH_WIZARD_L_1);
		chicken = new Chicken(Constants.PATH_CKN_FRONT);
		wolfs = new ConcurrentHashMap<Integer, Wolf>();
		ghosts = new ConcurrentHashMap<Integer, Ghost>();
		iceCream = new IceCream(random.nextInt(1000 - 20 - 1) + 20, random.nextInt(500 - 300 - 1) + 300, 40, 40);
	}

	private void initBooleans() {
		moveWolfs = true;
		moveGhost = true;
		moveWizard = true;
		stageWolfs = true;
		stageGhosts = false;
		stageWizard = true;
		resetPositionWolf = true;
		inmunity = true;
		screenShot = false;
		tutorial = true;
		lostLife = false;
		autosave = false;
		showIceCreams = false;
		moveWizardRight = false;
		moveWizardLeft = true;
		winGame = false;
	}

	private void createWolfs() {
		int distance = 0;
		for (int i = 0; i < Constants.QUANTITY_WOLFS; i++) {
			distance += distanceWolfs;
			Wolf wolf = new Wolf(Constants.PATH_WOLF_L_1, 1);
			wolf.setDelayJump(random.nextInt(100 - 20 - 1) + 20);
			wolf.setX(wolf.getX() + distance);
			wolfs.put(i, wolf);
		}
	}

	private void createGhosts() {
		for (int i = 0; i < Constants.QUANTITY_GHOSTS; i++) {
			Ghost ghost = new Ghost(Constants.PATH_GHOST_L_1, random.nextInt(1000 - 20 - 1) + 20);
			ghost.setX(ghost.getX());
			ghosts.put(i, ghost);
		}
	}

	private void stageGhosts(int quantityGhost) {
		if (counterGhosts < quantityGhost) {
			if (moveGhost) {
				if (timeGhost < 20000) {
					visibleGhost = true;
					moveGhosts();
				} else if (timeGhost >= 20000 && timeGhost < 30000) {
					for (Integer id : ghosts.keySet()) {
						visibleGhost = false;
						ghosts.get(id).setX(random.nextInt(1200 - 20 - 1) + 20);
						ghosts.get(id).moveSpriteFront();
						timeGhost += 10;
					}
				} else if (timeGhost >= 30000) {
					timeGhost = 0;
					counterGhosts++;
					visibleGhost = false;
				}
			}
		} else {
			if (stage!=10) {
				stage++;
			}
			wizard.setShow(false);
			stageGhosts = false;
			stageWolfs = true;
			visibleGhost = false;
		}
	}

	private void moveGhosts() {
		timeGhost += 60;
		for (int id = 0; id <= counterGhosts; id++) {
			if (timeGhost >= 10000 && timeGhost <= 20000 && ghosts.get(id).getMoveDown() == true) {
				ghosts.get(id).moveSpriteActive();
				ghosts.get(id).moveDown();
				checkColisionGhosts();
			} else {
				ghosts.get(id).moveSpriteFront();
				ghosts.get(id).setMoveDown();
			}
		}
	}

	private void stageWolfs(int stage) {
		if (moveWolfs) {
			resetPositionWolfs();
			checkColisionWolfs();
			moveWolfs(stage);
			checkFinalWolf(stage);
		}
	}

	private void checkColisionIceCream() {
		if (chicken.getRectangle().intersects(iceCream.getRectangle()) && showIceCreams) {
			points += 4;
			showIceCreams = false;
		}
	}

	private void resetPositionWolfs() {
		if (resetPositionWolf) {
			int distance = 0;
			for (Integer id : wolfs.keySet()) {
				distance += distanceWolfs;
				wolfs.get(id).setX(1000 + distance);
				wolfs.get(id).setY(530);
			}
			resetPositionWolf = false;
		}

	}

	private void checkFinalWolf(int stage) {
		if (wolfs.get(stage - 1).getX() <= -500) {
			stageWolfs = false;
			stageGhosts = true;
			resetPositionWolf = true;
			wizard.setShow(false);
		}
	}

	private void checkColisionWolfs() {
		if (tutorial == false && inmunity == false) {
			for (Integer id : wolfs.keySet()) {
				if (chicken.getRectangle().intersects(wolfs.get(id).getRectangle())) {
					lostLife = true;
				}
			}
		}
	}

	private void checkColisionGhosts() {
		if (tutorial == false && inmunity == false) {
			for (int id = 0; id <= counterGhosts; id++) {
				if (chicken.getRectangle().intersects(ghosts.get(id).getRectangle())) {
					lostLife = true;
				}
			}
		}
	}

	public boolean checkLifes() {
		return lostLife;
	}

	private void moveWolfs(int stage) {
		for (int id = 0; id < stage; id++) {
			wolfs.get(id).setQuality(stage);
			wolfs.get(id).moveLeft();
			if (wolfs.get(id).getQuality() == 12 || wolfs.get(id).getQuality() == 24
					|| wolfs.get(id).getQuality() == 30) {
				wolfs.get(id).moveUp();
			}
		}
	}

	public void changeIceCream() {
		iceCream.setX(random.nextInt(1000 - 20 - 1) + 20);
		iceCream.setY(random.nextInt(500 - 300 - 1) + 300);
	}

	public void chickenLeft() {
		chicken.moveLeft();
	}

	public void chickenRight() {
		chicken.moveRight();
	}

	public boolean chickenJump() {
		return chicken.moveUp();
	}

	public void startStatic() {
		if (chicken.isInmunity()) {
			chicken.setPath(Constants.PATH_CKNP_FRONT);
		} else {
			chicken.setPath(Constants.PATH_CKN_FRONT);
		}
	}

	public void pause() {
		moveWolfs = !moveWolfs;
		moveGhost = !moveGhost;
		moveWizard = !moveWizard;
	}

	public int getLevel() {
		return stage;
	}

	public void setInmunity(boolean inmunity) {
		this.inmunity = inmunity;
		chicken.setInmunity(this.inmunity);
	}

	public void setIconMusic(boolean music) {
		this.music = music;
	}

	public void skipTutorial() {
		tutorial = false;
	}

	public void setLife(boolean life) {
		lostLife = life;
	}

	public ConcurrentHashMap<Integer, Wolf> getWolfs() {
		return wolfs;
	}

	public void setWolfs(ConcurrentHashMap<Integer, Wolf> wolfs) {
		this.wolfs = wolfs;
	}

	public ConcurrentHashMap<Integer, Ghost> getGhosts() {
		return ghosts;
	}

	public void setGhosts(ConcurrentHashMap<Integer, Ghost> ghosts) {
		this.ghosts = ghosts;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public boolean isTutorial() {
		return tutorial;
	}

	public boolean isMoveWolfs() {
		return moveWolfs;
	}

	public void setMoveWolfs(boolean moveWolfs) {
		this.moveWolfs = moveWolfs;
	}

	public boolean isMoveGhost() {
		return moveGhost;
	}

	public void setMoveGhost(boolean moveGhost) {
		this.moveGhost = moveGhost;
	}

	public boolean isStageWolfs() {
		return stageWolfs;
	}

	public void setStageWolfs(boolean stageWolfs) {
		this.stageWolfs = stageWolfs;
	}

	public boolean isStageGhosts() {
		return stageGhosts;
	}

	public void setStageGhosts(boolean stageGhosts) {
		this.stageGhosts = stageGhosts;
	}

	public boolean isStageWizard() {
		return stageWizard;
	}

	public void setStageWizard(boolean stageMago) {
		this.stageWizard = stageMago;
	}

	public boolean isResetPositionWolf() {
		return resetPositionWolf;
	}

	public void setResetPositionWolf(boolean resetPositionWolf) {
		this.resetPositionWolf = resetPositionWolf;
	}

	public boolean isInmunity() {
		return inmunity;
	}

	public boolean isLostLife() {
		return lostLife;
	}

	public void setLostLife(boolean lostLife) {
		this.lostLife = lostLife;
	}

	public boolean isMusic() {
		return music;
	}

	public void setMusic(boolean music) {
		this.music = music;
	}

	public boolean isAutosave() {
		return autosave;
	}

	public void setAutosave(boolean autosave) {
		this.autosave = autosave;
	}

	public int getTimeGhost() {
		return timeGhost;
	}

	public void setTimeGhost(int timeGhost) {
		this.timeGhost = timeGhost;
	}

	public int getCounterGhosts() {
		return counterGhosts;
	}

	public void setCounterGhosts(int counterGhosts) {
		this.counterGhosts = counterGhosts;
	}

	public Chicken getChicken() {
		return chicken;
	}

	public void setChicken(Chicken chicken) {
		this.chicken = chicken;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public Wizard getWizard() {
		return wizard;
	}

	public void set(Wizard mago) {
		this.wizard = mago;
	}

	public int getDistanceWolfs() {
		return distanceWolfs;
	}

	public boolean getVisibleGhost() {
		return visibleGhost;
	}

	public int getPoints() {
		return points;
	}

	public void setDate(String fechaString) {
		this.date = fechaString;
	}

	public String getDate() {
		return date;
	}

	public void setPower(int power) {
		chicken.setPower(power);
	}

	public void setPowerUp(boolean powerUp) {
		this.powerUp = powerUp;
	}

	public boolean getPowerUp() {
		return powerUp;
	}

	public boolean isBlockPowers() {
		return blockPowers;
	}

	public void setBlockPowers(boolean block) {
		this.blockPowers = block;
	}

	public int getQuantityInmunity() {
		return quantityInmunity;
	}

	public void setQuantityInmunity(int quantityInmunity) {
		this.quantityInmunity = quantityInmunity;
	}

	public boolean isShowIceCreams() {
		return showIceCreams;
	}

	public void setShowIceCreams(boolean showIceCreams) {
		this.showIceCreams = showIceCreams;
	}

	public IceCream getIceCream() {
		return iceCream;
	}

	public void setIceCream(IceCream iceCream) {
		this.iceCream = iceCream;
	}

	public void setScreenShot(boolean screenShot) {
		this.screenShot = screenShot;
	}

	public boolean getScreenShot() {
		return screenShot;
	}

	public boolean isWinGame() {
		return winGame;
	}

	public void setWinGame(boolean winGame) {
		this.winGame = winGame;
	}

	public int getBestPoint() {
		return bestPoint;
	}

	public void setBestPoint(int bestPoint) {
		this.bestPoint = bestPoint;
	}
	
	
}