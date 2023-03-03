/**
 * Clase donde crearemos los objetos que permaneceran en la interfaz del jugador.
 */

package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import object.HeartObject;
import threads.GameLoop;

public class UserInterface {

	private WritableImage corazon, medio_corazon, corazon_vacio; //Imagenes de los corazones
	private GameLoop loop;
	private GraphicsContext context;
	
	/**
	 * Constructor de la interfaz de usuario.
	 * @param loop Recibe el game loop por parametro.
	 */
	
	public UserInterface(GameLoop loop){
		
		this.loop = loop;
		
		//Se crea el objeto corazón y se instancian las imagenes preparadas dentro del objeto heart
		
		HeartObject heart = new HeartObject();
		corazon = heart.image;
		medio_corazon = heart.image2;
		corazon_vacio = heart.image3;
	}
	
	/**
	 * Calcula los corazones que debe pintar segun la vida acutal del jugador.
	 */
	
	public void paint() {
		
		int x = GameVariables.TILE_SIZE/2;
		int y = GameVariables.TILE_SIZE/2;
		
		//Pinta los corazones enteros
		
		for(int i = 0; i < loop.getPlayer().getHearts()-0.5; i++) {
			context.drawImage(corazon, x, y, GameVariables.TILE_SIZE, GameVariables.TILE_SIZE);
			x += GameVariables.TILE_SIZE;
		}
		
		//Pinta los medios orazones
		
		if(loop.getPlayer().getHearts()%1==0.5) {
			context.drawImage(medio_corazon, x, y, GameVariables.TILE_SIZE, GameVariables.TILE_SIZE);
			x += GameVariables.TILE_SIZE;
		}
		
		//Pinta los corazones vacios faltantes para 3 contenedores
		
		for(int i = 0; i < GameVariables.MAX_HEARTS-loop.getPlayer().getHearts()-0.5; i++) {
			context.drawImage(corazon_vacio, x, y, GameVariables.TILE_SIZE, GameVariables.TILE_SIZE);
			x += GameVariables.TILE_SIZE;
		}

	}
	
	public void setContext(GraphicsContext context) {
		this.context = context;
	}
}
