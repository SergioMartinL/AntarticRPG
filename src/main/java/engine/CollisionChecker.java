/**
 * Clase que comprueba la colisión de una entidad con los objetos del juego.
 */

package engine;

import engine.entity.Entity;
import engine.entity.Npc;
import engine.entity.Player;
import threads.GameLoop;

public class CollisionChecker {

	private GameLoop loop;
	
	/**
	 * Constructor de la clase CollisionChecker.
	 * 
	 * @param loop El objeto GameLoop del juego.
	 * @throws NullPointerException si loop es nulo.
	 */
	
	public CollisionChecker(GameLoop loop) {
		if(loop == null)
			throw new NullPointerException("Valor nulo.");
		this.loop = loop;
	}
	
	/**
	 * Comprueba la colisión de una entidad con los objetos del juego.
	 * 
	 * @param e La entidad a comprobar la colisión.
	 */
	
	public void checkTile(Entity e) {

	    int leftX = (int) (e.worldX + e.areaSolid.getX());
	    int rightX =  (int) (e.worldX + e.areaSolid.getX() + e.areaSolid.getWidth());
	    int topY = (int) (e.worldY + e.areaSolid.getY());
	    int bottomY = (int) (e.worldY + e.areaSolid.getY() + e.areaSolid.getHeight());
	    
	    int leftCol = Math.max(0, leftX / GameVariables.TILE_SIZE);
	    int rightCol = Math.min(loop.getHandler().getLayers().get(0)[0].length - 1, rightX / GameVariables.TILE_SIZE);
	    int topRow = Math.max(0, topY / GameVariables.TILE_SIZE);
	    int bottomRow = Math.min(loop.getHandler().getLayers().get(0).length - 1, bottomY / GameVariables.TILE_SIZE);
	    
	    int tile1, tile2;

	    switch(e.direction) {
	        case UP: 
	            topRow = Math.max(0, (int) ((topY - e.speed) / GameVariables.TILE_SIZE)); 
	            tile1 = loop.getHandler().getLayers().get(0)[leftCol][topRow];
	            tile2 = loop.getHandler().getLayers().get(0)[rightCol][topRow];
	            e.colision = loop.getHandler().getTiles()[tile1].colision || loop.getHandler().getTiles()[tile2].colision;
	            break;
	        case DOWN: 
	            bottomRow = Math.min(loop.getHandler().getLayers().get(0).length - 1, (int) ((bottomY + e.speed) / GameVariables.TILE_SIZE));
	            tile1 = loop.getHandler().getLayers().get(0)[leftCol][bottomRow];
	            tile2 = loop.getHandler().getLayers().get(0)[rightCol][bottomRow];
	            e.colision = loop.getHandler().getTiles()[tile1].colision || loop.getHandler().getTiles()[tile2].colision;
	            break;
	        case LEFT: 
	            leftCol = Math.max(0, (int) ((leftX - e.speed) / GameVariables.TILE_SIZE));
	            tile1 = loop.getHandler().getLayers().get(0)[leftCol][topRow];
	            tile2 = loop.getHandler().getLayers().get(0)[leftCol][bottomRow];
	            e.colision = loop.getHandler().getTiles()[tile1].colision || loop.getHandler().getTiles()[tile2].colision;
	            break;
	        case RIGHT:
	        	rightCol = (int) ((rightX + e.speed) / GameVariables.TILE_SIZE);
	            tile1 = loop.getHandler().getLayers().get(0)[rightCol][topRow];
	            tile2 = loop.getHandler().getLayers().get(0)[rightCol][bottomRow];
	        	e.colision = loop.getHandler().getTiles()[tile1].colision || loop.getHandler().getTiles()[tile2].colision;
	        	break;
	    }
		
	}
	
	public void checkCollision(Entity e, Entity e2) {
		if(!(e2 instanceof Npc)) {
			Player p = (Player) e2;
			int leftX1 = (int) (e.worldX + e.areaSolid.getX());
			int rightX1 =  (int) (e.worldX + e.areaSolid.getX() + e.areaSolid.getWidth());
			int topY1 = (int) (e.worldY + e.areaSolid.getY());
			int bottomY1 = (int) (e.worldY + e.areaSolid.getY() + e.areaSolid.getHeight());
			
			int leftX2 = (int) (e2.worldX + e2.areaSolid.getX());
			int rightX2 =  (int) (e2.worldX + e2.areaSolid.getX() + e2.areaSolid.getWidth());
			int topY2 = (int) (e2.worldY + e2.areaSolid.getY());
			int bottomY2 = (int) (e2.worldY + e2.areaSolid.getY() + e2.areaSolid.getHeight());
			
			int leftCol1 = Math.max(0, leftX1 / GameVariables.TILE_SIZE);
			int rightCol1 = Math.min(loop.getHandler().getLayers().get(0)[0].length - 1, rightX1 / GameVariables.TILE_SIZE);
			int topRow1 = Math.max(0, topY1 / GameVariables.TILE_SIZE);
			int bottomRow1 = Math.min(loop.getHandler().getLayers().get(0).length - 1, bottomY1 / GameVariables.TILE_SIZE);
			
			int leftCol2 = Math.max(0, leftX2 / GameVariables.TILE_SIZE);
			int rightCol2 = Math.min(loop.getHandler().getLayers().get(0)[0].length - 1, rightX2 / GameVariables.TILE_SIZE);
			int topRow2 = Math.max(0, topY2 / GameVariables.TILE_SIZE);
			int bottomRow2 = Math.min(loop.getHandler().getLayers().get(0).length - 1, bottomY2 / GameVariables.TILE_SIZE);

			if(leftCol1 == leftCol2 && topRow1 == topRow2 && bottomRow1 == bottomRow2) {
				p.stop();
				loop.geteHandler().damagePit();
				loop.getTileHandler().getNPCs().remove(e);
			}
			
			if(rightCol1 == rightCol2 && topRow1 == topRow2 && bottomRow1 == bottomRow2) {
				p.stop();
				loop.geteHandler().damagePit();
				loop.getTileHandler().getNPCs().remove(e);
			}

				
		}
	
	}
	
	
}
