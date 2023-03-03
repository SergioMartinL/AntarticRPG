/**
 * Clase que establece los assets del juego.
 */

package engine;

import java.util.ArrayList;

import engine.entity.Npc;
import threads.GameLoop;

public class AssetSetter {

	GameLoop gl;
	
	/**
	 * Constructor de la clase AssetSetter.
	 * 
	 * @param gl El objeto GameLoop del juego.
	 */

	
	public AssetSetter(GameLoop gl) {
		this.gl = gl;
	}
	
	/**
	 * Establece un objeto en el juego.
	 */
	
	public void setObject() {
		//Metodo vacio
	}
	
	/**
	 * Establece los NPCs en el juego.
	 */
	
	public void setNpc() {
		
		// Establece 4 NPCs en el juego en posiciones específicas
		if(gl.getNpcs().size()==0) {
			gl.getNpcs().add(new Npc(gl));
			gl.getNpcs().add(new Npc(gl));
			gl.getNpcs().add(new Npc(gl));
			gl.getNpcs().add(new Npc(gl));
			
			gl.getNpcs().get(0).worldX = GameVariables.TILE_SIZE;
			gl.getNpcs().get(0).worldY = GameVariables.TILE_SIZE;
			gl.getNpcs().get(0).setContext(gl.getCanvas().getGraphicsContext2D());
			
			gl.getNpcs().get(1).worldX = GameVariables.TILE_SIZE*10;
			gl.getNpcs().get(1).worldY = GameVariables.TILE_SIZE*10;
			gl.getNpcs().get(1).setContext(gl.getCanvas().getGraphicsContext2D());
			
			gl.getNpcs().get(2).worldX = GameVariables.TILE_SIZE*19;
			gl.getNpcs().get(2).worldY = GameVariables.TILE_SIZE*17;
			gl.getNpcs().get(2).setContext(gl.getCanvas().getGraphicsContext2D());

			gl.getNpcs().get(3).worldX = GameVariables.TILE_SIZE*8;
			gl.getNpcs().get(3).worldY = GameVariables.TILE_SIZE*1;
			gl.getNpcs().get(3).setContext(gl.getCanvas().getGraphicsContext2D());
		}
	
	}
	
	/**
	 * Devuelve el primer NPC del juego.
	 * 
	 * @return El primer NPC del juego.
	 */
	
	public Npc getNpc() {
		return gl.getNpcs().get(0);
	}
}
