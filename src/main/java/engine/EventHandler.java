package engine;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import engine.entity.Player;
import javafx.scene.shape.Rectangle;
import threads.GameLoop;

/**
*Clase encargada de manejar los eventos que ocurren en el juego.
*/

public class EventHandler {

	public boolean isMapChange = false;  // Indica si se ha producido un cambio de mapa
	GameLoop gl; // Referencia al GameLoop principal
	Rectangle eventRect = new Rectangle(23, 23, 2, 2); // Rectángulo para detectar colisiones con los eventos
	int eventRectDefaultX, eventRectDefaultY; // Posición por defecto del rectángulo de colisiones
	boolean check = true; // Booleano para chequear la colisión con el pozo una sola vez
	Timer timer = new Timer(); 
	int numMap = 1; // Número del mapa actual

	/**
	*Constructor de la clase. Recibe una referencia al GameLoop principal.
	*
	*@param gl Referencia al GameLoop principal
	*/
	
	public EventHandler(GameLoop gl) {
		this.gl = gl;
		// Inicialización del rectángulo de colisiones
		eventRect.setX(23);
		eventRect.setY(23);
		eventRect.setWidth(2);
		eventRect.setHeight(2);
		eventRectDefaultX = (int) eventRect.getX();
		eventRectDefaultY = (int) eventRect.getY();

	}
	
	/**
	*Chequea si se ha producido una colisión con el evento de daño (el pozo).
	*Si es así, reduce la vida del jugador.
	*/

	public void checkEventDamage() {
		if (hit(2, 2, Direction.RIGHT)) {
			System.out.println(gl.getPlayer().getHearts());
			damagePit();
		}
	}
	
	/**
	*Chequea si se ha producido una colisión con un evento de cambio de mapa.
	*En caso de ser así, cambia el mapa actual y carga las capas correspondientes.
	*/

	public void checkEventChangeMap() {
		
		//De mapa 1 a mapa 2
		if (hit(8, 1, Direction.UP) && numMap == 1) {
			numMap=2;
			System.out.println("hit");
			File nuevoMapa = new File("src/main/resources/maps/mapa2.tmj");
			gl.getTileHandler().setMapFile(nuevoMapa);
			gl.getTileHandler().loadLayers(nuevoMapa);
			isMapChange = true;
			
		}
		// De mapa 2 a mapa 1
		if (hit(6, 23, Direction.DOWN) && numMap == 2) {
			numMap=1;
			System.out.println("hit");
			File nuevoMapa = new File("src/main/resources/maps/mapa1.tmj");
			gl.getTileHandler().setMapFile(nuevoMapa);
			gl.getTileHandler().loadLayers(nuevoMapa);
			isMapChange = true;
			
		}
		// De mapa 2 a mapa 3
		if (hit(7, 1, Direction.UP) && numMap == 2) {
			numMap=3;
			System.out.println(numMap);
			File nuevoMapa = new File("src/main/resources/maps/mapa3.tmj");
			gl.getTileHandler().setMapFile(nuevoMapa);
			gl.getTileHandler().loadLayers(nuevoMapa);
			isMapChange = true;
			
		}
		// De mapa 2 a mapa 4
		if (hit(18, 1, Direction.UP) && numMap == 2) {
			numMap=4;
			System.out.println(numMap);
			File nuevoMapa = new File("src/main/resources/maps/mapa4.tmj");
			gl.getTileHandler().setMapFile(nuevoMapa);
			gl.getTileHandler().loadLayers(nuevoMapa);
			isMapChange = true;
			
		}
		// De mapa 3 a mapa 2

		if (hit(7, 23, Direction.DOWN) && numMap == 3) {
			System.out.println("De mapa 3 a mapa 2");
			numMap=6;
			System.out.println(numMap);
			File nuevoMapa = new File("src/main/resources/maps/mapa2.tmj");
			gl.getTileHandler().setMapFile(nuevoMapa);
			gl.getTileHandler().loadLayers(nuevoMapa);
			isMapChange = true;
			
		}
		
		// De mapa 4 a mapa 2
		
		if (hit(18, 23, Direction.DOWN) && numMap == 4) {
			System.out.println("De mapa 4 a mapa 2");
			numMap=5;
			System.out.println(numMap);
			File nuevoMapa = new File("src/main/resources/maps/mapa2.tmj");
			gl.getTileHandler().setMapFile(nuevoMapa);
			gl.getTileHandler().loadLayers(nuevoMapa);
			isMapChange = true;
			
		}

	}


	/**
	 * Se crea un damagePit donde el jugador recibirá daño.
	 */
	
	public void damagePit() {

		gl.player.damage(0.5);
	}
	
	/**
	 * Crea un tile del mapa donde si el jugador colisiona devolverá un hit.
	 * @param eventCol Columna del tile que se quiere calcular.
	 * @param eventRow Columna del tile que se quiere calcular
	 * @param reqDirection La direccion hacia donde deberás estar mirando.
	 * @return Devuelve un hit si ese tile ha colisionado con el jugador
	 */

	public boolean hit(int eventCol, int eventRow, Direction reqDirection) {
		boolean hit = false;
		double defaultX = gl.player.areaSolid.getX();
		double defaultY = gl.player.areaSolid.getY();
		gl.player.areaSolid.setX(gl.player.worldX + gl.player.areaSolid.getX());
		gl.player.areaSolid.setY(gl.player.worldY + gl.player.areaSolid.getY());
		eventRect.setX(eventCol * GameVariables.TILE_SIZE + eventRect.getX());
		eventRect.setY(eventRow * GameVariables.TILE_SIZE + eventRect.getY());

		if (gl.player.areaSolid.getBoundsInParent().intersects(eventRect.getBoundsInParent())) {
			if (gl.player.direction.equalsByDireccion(reqDirection)) {
				hit = true;
			}
		}
		gl.player.areaSolid.setX(defaultX);
		gl.player.areaSolid.setY(defaultY);
		eventRect.setX(eventRectDefaultX);
		eventRect.setY(eventRectDefaultY);

		return hit;
	}

	public int getNumMap() {
		return numMap;
	}

	public void setNumMap(int numMap) {
		this.numMap = numMap;
	}

	public boolean isMapChange() {
		return isMapChange;
	}

	public void setMapChange(boolean isMapChange) {
		this.isMapChange = isMapChange;
	}

}
