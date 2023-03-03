/**
 * Clase player donde se crea un jugador, carga sus texturas y checkea continuamente las colisiones del movimiento
 */

package engine.entity;

import java.util.Optional;

import dad.App;
import engine.Animation;
import engine.Direction;
import engine.GameVariables;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import threads.GameLoop;

public class Player extends Entity {

	private Canvas c;
	private GameLoop loop;
	private GraphicsContext context;
	private boolean idle;
	public final int screenX;
	public final int screenY;
	private final int layer = 0;
	private Animation animation;
	private double hearts;
	private int lastX;
	private int lastY;

	public Player(Canvas c, GameLoop loop) {
		super(loop);
		if (c == null || loop == null)
			throw new NullPointerException("Valor nulo.");
		this.c = c;
		this.context = c.getGraphicsContext2D();
		this.loop = loop;

		screenX = GameVariables.SCREEN_WIDTH / 2;
		screenY = GameVariables.SCREEN_HEIGHT / 2;

		areaSolid = new Rectangle(0, 0, GameVariables.TILE_SIZE, GameVariables.TILE_SIZE);

		setValoresPorDefecto();
		loadImages();
	}

	public void setValoresPorDefecto() {
		worldX = GameVariables.TILE_SIZE * 10;
		worldY = GameVariables.TILE_SIZE * 16;

		speed = GameVariables.SPEED;
		direction = Direction.DOWN;

		hearts = GameVariables.MAX_HEARTS;

	}
	
	public void stop() {
		setWorldX(lastX);
		setWorldY(lastY);
		idle = true;
	}

	public void update(long timeDifference) {
		lastX = worldX;
		lastY = worldY;
		//vuelta al mapa 1 desde el mapa 2
		if (loop.geteHandler().isMapChange && loop.geteHandler().getNumMap()==1) {
			loop.getaSetter().setNpc();
			worldX = GameVariables.TILE_SIZE * 9;
			worldY = GameVariables.TILE_SIZE * 1;
			loop.geteHandler().setMapChange(false);
		}
		//vuelta al mapa 2 desde el mapa 1
		if (loop.geteHandler().isMapChange && loop.geteHandler().getNumMap()==2) {
			loop.getaSetter().setNpc();
			worldX = GameVariables.TILE_SIZE * 6;
			worldY = GameVariables.TILE_SIZE * 23;
			loop.geteHandler().setMapChange(false);
		}
		//vuelta al mapa 2 desde el mapa 3
		if (loop.geteHandler().isMapChange && loop.geteHandler().getNumMap()==3) {
			loop.getaSetter().setNpc();
			worldX = GameVariables.TILE_SIZE * 7;
			worldY = GameVariables.TILE_SIZE * 23;
			loop.geteHandler().setMapChange(false);
		}
		//vuelta al mapa 4 desde el 2
		if (loop.geteHandler().isMapChange && loop.geteHandler().getNumMap()==4) {
			loop.getaSetter().setNpc();
			worldX = GameVariables.TILE_SIZE * 18;
			worldY = GameVariables.TILE_SIZE * 23;
			loop.geteHandler().setMapChange(false);
		}
		//vuelta al mapa 2 desde el 4
		if (loop.geteHandler().isMapChange && loop.geteHandler().getNumMap()==5) {
			loop.getaSetter().setNpc();
			loop.geteHandler().setNumMap(2);
			worldX = GameVariables.TILE_SIZE * 18;
			worldY = GameVariables.TILE_SIZE * 1;
			loop.geteHandler().setMapChange(false);
		}
		//vuelta al mapa 2 desde el 3
		if (loop.geteHandler().isMapChange && loop.geteHandler().getNumMap()==6) {
			loop.getaSetter().setNpc();
			loop.geteHandler().setNumMap(2);
			worldX = GameVariables.TILE_SIZE * 7;
			worldY = GameVariables.TILE_SIZE * 1;
			loop.geteHandler().setMapChange(false);
			
		}
		
		
		idle = (!loop.upPressed && !loop.downPressed && !loop.leftPressed && !loop.rightPressed);
		colision = false;

		if (loop.upPressed && worldY > 0) {
			direction = Direction.UP;
			loop.getCollisionChecker().checkTile(this);
			if (!colision)
				worldY -= speed;

		} else if (loop.downPressed
				&& worldY < (GameVariables.SCREEN_HEIGHT - GameVariables.TILE_SIZE * GameVariables.ESCALADO_PLAYER)) {
			direction = Direction.DOWN;
			loop.getCollisionChecker().checkTile(this);
			if (!colision)
				worldY += speed;

		} else if (loop.leftPressed && worldX > 0) {
			direction = Direction.LEFT;
			loop.getCollisionChecker().checkTile(this);
			if (!colision)
				worldX -= speed;

		} else if (loop.rightPressed
				&& worldX < GameVariables.SCREEN_WIDTH - GameVariables.TILE_SIZE * GameVariables.ESCALADO_PLAYER) {
			direction = Direction.RIGHT;
			loop.getCollisionChecker().checkTile(this);
			if (!colision)
				worldX += speed;
		}

		contImages++;
		if (contImages > 12) {
			if (spriteNum == 0)
				spriteNum = 1;
			else if (spriteNum == 1)
				spriteNum = 2;
			else if (spriteNum == 2)
				spriteNum = 1;
			else if (spriteNum == 1)
				spriteNum = 0;
			contImages = 0;
		}
		// animation.update(timeDifference);
		
		loop.geteHandler().checkEventChangeMap();
	}

	public void paint() {
		drawPlayer();

	}

	private void drawPlayer() {

		Image img = null;

		switch (direction) {
		case UP:
			if (spriteNum == 1)
				img = up1;
			else if (spriteNum == 2)
				img = up2;
			else if (spriteNum == 3)
				img = up3;
			break;
		case DOWN:
			if (spriteNum == 1)
				img = down1;
			else if (spriteNum == 2)
				img = down2;
			else if (spriteNum == 3)
				img = down3;
			break;
		case LEFT:
			if (spriteNum == 1)
				img = left1;
			else if (spriteNum == 2)
				img = left2;
			else if (spriteNum == 3)
				img = left3;
			break;
		case RIGHT:
			if (spriteNum == 1)
				img = right1;
			else if (spriteNum == 2)
				img = right2;
			else if (spriteNum == 3)
				img = right3;
			break;
		}

		if (idle) {
			if (direction == Direction.UP)
				img = upIdle;
			else if (direction == Direction.DOWN)
				img = downIdle;
			else if (direction == Direction.LEFT)
				img = leftIdle;
			else if (direction == Direction.RIGHT)
				img = rightIdle;
		}

		context.drawImage(img, screenX, screenY, GameVariables.TILE_SIZE * GameVariables.ESCALADO_PLAYER,
				GameVariables.TILE_SIZE * GameVariables.ESCALADO_PLAYER);

	}

	private void loadImages() {
		up1 = new Image(getClass().getResourceAsStream("/assets/player/upEsquimal.png"));
		up2 = new Image(getClass().getResourceAsStream("/assets/player/up1Esquimal.png"));
		up3 = new Image(getClass().getResourceAsStream("/assets/player/up2Esquimal.png"));
		down1 = new Image(getClass().getResourceAsStream("/assets/player/downEsquimal.png"));
		down2 = new Image(getClass().getResourceAsStream("/assets/player/down1Esquimal.png"));
		down3 = new Image(getClass().getResourceAsStream("/assets/player/down2Esquimal.png"));
		left1 = new Image(getClass().getResourceAsStream("/assets/player/leftEsquimal.png"));
		left2 = new Image(getClass().getResourceAsStream("/assets/player/left1Esquimal.png"));
		left3 = new Image(getClass().getResourceAsStream("/assets/player/left2Esquimal.png"));
		right1 = new Image(getClass().getResourceAsStream("/assets/player/rightEsquimal.png"));
		right2 = new Image(getClass().getResourceAsStream("/assets/player/right1Esquimal.png"));
		right3 = new Image(getClass().getResourceAsStream("/assets/player/right2Esquimal.png"));
		upIdle = new Image(getClass().getResourceAsStream("/assets/player/upIdle.gif"));
		downIdle = new Image(getClass().getResourceAsStream("/assets/player/downIdle.gif"));
		leftIdle = new Image(getClass().getResourceAsStream("/assets/player/leftIdle.gif"));
		rightIdle = new Image(getClass().getResourceAsStream("/assets/player/rightIdle.gif"));
	}
	public void damage(double damage) {
		if (hearts > 0 && hearts != 0.5) {
			hearts = hearts - damage;
		} else {
			hearts=0;
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Antartic RPG");
			a.setHeaderText("¡Has perdido!");
			a.setContentText("Se generará un reporte de las estadísticas de tu partida.");
			a.show();
			loop.inGame = false;
			App.primaryStage.close();
		}
			
	}
	public int getWorldX() {
		return worldX;
	}

	public int getWorldY() {
		return worldY;
	}

	public int getScreenX() {
		return screenX;
	}

	public int getScreenY() {
		return screenY;
	}

	public boolean isIdle() {
		return idle;
	}

	public int getLayer() {
		return layer;
	}

	public double getHearts() {
		return hearts;
	}

	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}
	
	public void setWorldY(int worldY) {
		this.worldY = worldY;
	}


}
