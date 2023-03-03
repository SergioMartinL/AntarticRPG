/**
 * Clase que establece los assets del juego.
 */

package engine;

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
		
		gl.getNpcs().add(0, new Npc(gl));
		gl.getNpcs().get(0).worldX = GameVariables.TILE_SIZE;
		gl.getNpcs().get(0).worldY = GameVariables.TILE_SIZE;
		
		gl.getNpcs().add(1, new Npc(gl));
		gl.getNpcs().get(1).worldX = GameVariables.TILE_SIZE*10;
		gl.getNpcs().get(1).worldY = GameVariables.TILE_SIZE*10;
	
		
		gl.getNpcs().add(2, new Npc(gl));
		gl.getNpcs().get(2).worldX = GameVariables.TILE_SIZE*19;
		gl.getNpcs().get(2).worldY = GameVariables.TILE_SIZE*17;

		
		gl.getNpcs().add(3, new Npc(gl));
		gl.getNpcs().get(3).worldX = GameVariables.TILE_SIZE*8;
		gl.getNpcs().get(3).worldY = GameVariables.TILE_SIZE*1;
	
	
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
