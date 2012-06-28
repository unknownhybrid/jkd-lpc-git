import java.util.Queue;
import com.pixilic.javakat.*;

/**
 * @author clay
 */

/** a loader for XML maps of game areas*/
public class MapLoader {
	
	/** this map's width */
	int width;
	/** this map's height */
	int height;
	/** Queue of all String names of Permanents in this map */
	Queue<String> names;
	/** Queue of all int x-vals of Permanents in this map */
	Queue<Integer> xs;
	/** Queue of all int y-vals of Permanents in this map */
	Queue<Integer> ys;
	/** Queue of all int z-vals of Permanents in this map */
	Queue<Integer> zs;
	/** Queue of all boolean mobility vals of Permanents in this map */
	Queue<Boolean> mobilities;
	/** Queue of all boolean unit-collision vals of Permanents in this map */
	Queue<Boolean> unitCols;
	/** Queue of all boolean NPC-collision vals of Permanents in this map */
	Queue<Boolean> npcCols;
	/** Queue of all Animations of Permanents in this map */
	Queue<Animation> anims;
	
	/** creates a new map with given width and height */
	MapLoader(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/** 
	 * creates new map with given width, height and Queue of map permanents 
	 * @param width int
	 * @param height int
	 * @param permanents Queue of type Permanent
	 */
	MapLoader(int width, int height, Queue<Permanent> permanents) {
		this.width = width;
		this.height = height;
		this.init(permanents);
	}
	
	/** initializes this map with the given Queue of map permanents */
	private void init(Queue<Permanent> ps) {
		for (Permanent p : ps)
			this.addPermanent(p);
	}
	
	/** add the given Permanent to this MapLoader's Queues
	 * @param p the Permanent being added
	 */
	void addPermanent(Permanent p) {
		this.names.add(p.name);
		this.xs.add(p.xPos);
		this.ys.add(p.yPos);
		this.zs.add(p.zPos);
		this.anims.add(p.anim);
		this.unitCols.add(p.hasUnitCollision);
		this.npcCols.add(p.hasNPCUnitCollision);
		this.mobilities.add(p.isMobile);
	}
}
