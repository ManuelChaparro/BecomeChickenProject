package models;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import simulationmodels.Montecarlo;
import views.Constants;

public class Game {

	private ConcurrentHashMap<Integer, Wolf> wolfs;
	private ConcurrentHashMap<Integer, Ghost> ghosts;
	private int stage = 1;
	private int distanceWolfs;
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
	private int wallpaperX;
	private ArrayList<double[]> dataMontecarlo;
	private int width;
	private int quantityWolfs;

	public Game(boolean init) {
		if (init) {
			initVariables();
			createWolfs();
			createGhosts();
		}
	}

	public void setMoveChicken(int move) {
		chicken.setX(chicken.getX() - move);
	}

	public int getQuantityWolfs() {
		return quantityWolfs;
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
				stageWolfs(7);
			} else {
				stageGhosts(4);
			}
			break;
		case 3:
			distanceWolfs = resetPositionWolf ? (int) (width*0.6): distanceWolfs;
			tutorial = false;
			if (stageWolfs) {
				stageWolfs(10);
			} else {
				stageGhosts(6);
			}
			break;
		case 4:
			distanceWolfs = resetPositionWolf ? (int) (width*0.65): distanceWolfs;
			if (stageWolfs) {
				stageWolfs(12);
			} else {
				stageGhosts(8);
			}
			break;
		case 5:
			distanceWolfs = resetPositionWolf ? (int) (width*0.7): distanceWolfs;
			if (stageWolfs) {
				stageWolfs(16);
			} else {
				stageGhosts(10);
			}
			break;
		case 6:
			distanceWolfs = resetPositionWolf ? (int) (width*0.72): distanceWolfs;
			if (stageWolfs) {
				stageWolfs(20);
			} else {
				stageGhosts(12);
			}
			break;
		case 7:
			distanceWolfs = resetPositionWolf ? (int) (width*0.75): distanceWolfs;
			if (stageWolfs) {
				stageWolfs(24);
			} else {
				stageGhosts(14);
			}
			break;
		case 8:
			distanceWolfs = resetPositionWolf ? (int) (width*0.77): distanceWolfs;
			if (stageWolfs) {
				stageWolfs(28);
			} else {
				stageGhosts(16);
			}
			break;
		case 9:
			distanceWolfs = resetPositionWolf ? (int) (width*0.80): distanceWolfs;
			if (stageWolfs) {
				stageWolfs(30);
			} else {
				stageGhosts(20);
			}
			break;
		case 10:
			distanceWolfs = resetPositionWolf ? (int) (width*0.75): distanceWolfs;
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
		dataMontecarlo = montecarloWolfsProbability(new Montecarlo());
		initObjects();
		initBooleans();
		quantityInmunity = 4;
		points = 0;
		counterWizard = 0;
		wallpaperX = 0;
		width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		distanceWolfs = (int) (width*0.5);
		quantityWolfs = 4;
	}

	private ArrayList<double[]> montecarloWolfsProbability(Montecarlo montecarlo) {
		ArrayList<double[]> toReturn = new ArrayList<double[]>();
		for (int i = 1; i <= 5; i++) {
			double[] toAssign = new double[3];
			toAssign[0] = montecarlo.calculateProbabilityWolfOne(i);
			toAssign[1] = montecarlo.calculateProbabilityWolfTwo(i);
			toAssign[2] = montecarlo.calculateProbabilityWolfThree(i);
			toReturn.add(toAssign);
		}
		return toReturn;
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
		for (int i = 0; i < Constants.QUANTITY_WOLFS; i++) {
			Wolf wolf = new Wolf(Constants.PATH_WOLF_L_1);
			wolf.setDelayJump(random.nextInt(200 - 20 - 1) + 20);
			wolf.setX(width);
			wolfs.put(i, wolf);
		}
		createWolfsQuality(stage);
	}

	private void createWolfsQuality(int stage){
		if(this.stage < 2){
			setWolfsQuality(dataMontecarlo.get(0), stage);
		}else if(this.stage < 5){
			setWolfsQuality(dataMontecarlo.get(1), stage);
		}else if(this.stage < 9){
			setWolfsQuality(dataMontecarlo.get(2), stage);
		}else{
			setWolfsQuality(dataMontecarlo.get(3), stage);
		}

	}

	private static int generateNextNumber(int seed) {
		int square = seed * seed;
		String squareString = String.valueOf(square);
		while (squareString.length() < 8) {
			squareString = "0" + squareString;
		}
		int startIndex = (squareString.length() - 8) / 2;
		int endIndex = startIndex + 8;
		String middleDigits = squareString.substring(startIndex, endIndex);
		return Math.abs(Integer.parseInt(middleDigits));
	}

	private void setWolfsQuality(double[] probabilities, int stage) {
		int seed = 49284567;
		for (int i = 0; i < wolfs.size(); i++) {
			seed = generateNextNumber(seed);
			double toCompare = Double.parseDouble("0."+String.valueOf(seed));
			if (toCompare >= 0 && toCompare < probabilities[0]){
				wolfs.get(i).setQuality(1);
			}else if(toCompare >= probabilities[0] && toCompare < probabilities[1]+probabilities[0]){
				wolfs.get(i).setQuality(2);
				wolfs.get(i).setDelayJump(20);
			}else{
				wolfs.get(i).setQuality(3);
			}
		}
	}

	private void createGhosts() {
		for (int i = 0; i < Constants.QUANTITY_GHOSTS; i++) {
			Ghost ghost = new Ghost(Constants.PATH_GHOST_F_1, random.nextInt((int) (width*1.1)));
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
				ghosts.get(id).moveRight();
			} else {
				ghosts.get(id).moveLeft();
				ghosts.get(id).moveSpriteFront();
				ghosts.get(id).setMoveDown();
			}
		}
	}

	private void stageWolfs(int stage) {
		quantityWolfs = stage;
		if (moveWolfs) {
			resetPositionWolfs();
			checkColisionWolfs();
			validateWolfs(stage);
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
			for (Integer id : wolfs.keySet()) {
				wolfs.get(id).setX(width);
				wolfs.get(id).setY(530);
			}
			resetPositionWolf = false;
		}
	}

	private void checkFinalWolf(int stage) {
		stageWolfs = false;
		stageGhosts = true;
		resetPositionWolf = true;
		wizard.setShow(false);
		createWolfsQuality(stage);
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

	private void validateWolfs(int stage) {
		int idFinalWolf = 0, xFinalWolf = wolfs.get(0).getX();
		for (int id = 0; id < stage; id++) {
			if (wolfs.get(id).getX() > xFinalWolf) {
				xFinalWolf = wolfs.get(id).getX();
				idFinalWolf = id;
			}
			moveWolfs(id);
		}
		if(wolfs.get(idFinalWolf).getX() <= -100){
			checkFinalWolf(stage);
		}
	}

	private void moveWolfs(int id) {
		if(wolfs.get(id).getX() >= -(width*0.1)){
			if (id==0){
				wolfs.get(id).moveLeft();
			}else if(wolfs.get(id-1).getX() <= distanceWolfs){
				wolfs.get(id).moveLeft();
			}

			if(wolfs.get(id).getQuality()==2){
				wolfs.get(id).moveUp();
			}
		}
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


	public void setIceCreamX(int nextX) {
		iceCream.setX(nextX);
	}

	public void setIceCreamY(int nextY) {
		iceCream.setY(nextY);
	}

	public int getWallpaperX() {
		return wallpaperX;
	}

	public void setWallpaperX(int wallpaperX) {
		this.wallpaperX = wallpaperX;
	}
}