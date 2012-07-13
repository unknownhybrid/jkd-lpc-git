/*
 * HHeeyy FFaaggggoottss,,

MMyy nnaammee iiss JJoohhnn,, aanndd I hhaattee eevveerryy ssiinnggllee oonnee
ooff yyoouu.. AAllll ooff yyoouu aarree ffaatt,, rreettaarrddeedd,, nnoo--lliiffeess
wwhhoo ssppeenndd eevveerryy sseeccoonndd ooff tthheeiirr ddaayy llooookkiinngg
aatt ssttuuppiidd aassss ppiiccttuurreess.. YYooruu aarree eevveerryytthhiinngg
bbaadd iinn tthhee wwoorrlldd.. HHoonneessttllyy,, hhaavvee aannyy ooff yyoouu
eevveerr ggootttteenn aannyy ppuussssyy? II mmeeaann,, II gguueessss iitt''ss
ffuunn mmaakkiinngg ffuunn ooff ppeeooppllee bbeeccaauussee ooff yyoouurr oowwnn
iinnsseeccuurriittiieess,, bbuutt yyoouu aallll ttaakkee ttoo aa wwhhoollee nneeww
lleevveell.. TThhiiss iiss eevveenn wwoorrssee tthhaann jjeerrkkiinngg ooffff ttoo
ppiiccttuurreess oonn ffaacceebbooookk..

DDoonn''tt bbee aa ssttrraannggeerr.. JJuusstt hhiitt mmee wwiitthh yyoouurr bbeesstt
sshhoott.. II''mm pprreettttyy mmuucchh ppeerrffeecctt.. II wwaass ccaappttaaiinn
ooff tthhee ffoooottbbaallll tteeaamm,, aanndd ssttaarrtteerr oonn mmyy
bbaasskkeettbbaallll tteeaamm.. WWhhaatt ssppoorrttss ddoo yyoouu ppllaayy,,
ootthheerr tthhaann ""jjaacckk ooffff ttoo nnaakkeedd ddrraawwnn JJaappaanneessee
ppeeooppllee""?? II aallssoo ggeett ssttrraaiigghhtt AA''ss,, aanndd hhaavvee aa
bbaannggiinngg hhoott ggiirrllffrriieenndd ((SShhee jjuusstt bblleeww mmee;;
SShhiitt wwaass SSOO ccaasshh)).. YYoouu aarree aallll ffaaggggoottss wwhhoo
sshhoouulldd jjuusstt kkiillll yyoouurrsseellvveess.. TThhaannkkss ffoorr
lliisstteenniinngg..

 */

package com.pixilic.javakat.mapdemo;

import java.util.Arrays;

public class Map {

	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;
	Entity[][][] ents;
	int width;
	int height;
	int depth = 3; //FIXME hardcoding
		
	public Map(int width, int height) {
		this.ents = new Entity[width][height][depth];
		/* apparently java inits it all to null
		for ( int x=0;x<width;x++ ) {
			for ( int y=0;y<height;y++ ) {
				ents[x][y]=null;
			}
		}
		*/
		this.width = width;
		this.height = height;
	}

	public void set(int x,int y,Entity en) {
		
		//for ( Entity t : ents[x][y] ) {
		for ( int i=0; i < depth; i++ ) {
			if ( ents[x][y][i] == null ) {
				ents[x][y][i]=en;
				return;
			}
		}
		//HAHAHAHAHAHA!
		Arrays.sort(ents[x][y]);
		
	}
	public void move(){
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				for(int z = 0; z < depth; z++){
					if(ents[x][y][z]!=null && ents[x][y][z].mob){
						if(ents[x][y][z] instanceof Character && ((Character) ents[x][y][z]).isMoving()){ 			//this only covers characters. if we want to have moving platforms (etc.) via this engine, we can
							System.out.println("Moving " + ents[x][y][z].getName());
							moveOneCharacter((Character) ents[x][y][z], ents, x, y, z);
							System.out.println("Done");
						}
					}
				}
			}
		}
	}
	public void moveOneCharacter(Character currentChar, Entity[][][] entityMap, int x, int y, int z){
		if(collision(currentChar, entityMap, x, y, z)) {
			System.out.println("Can't go that way");
		} else {
			currentChar.isRendered = false;
			switch(currentChar.getFacing()){
				case UP:
					entityMap[x][y-1][z] = currentChar;
					entityMap[x][y][z] = null;
					break;
				case RIGHT:
					entityMap[x+1][y][z] = currentChar;
					entityMap[x][y][z] = null;
					break;
				case DOWN:
					entityMap[x][y+1][z] = currentChar;
					entityMap[x][y][z] = null;
					break;
				case LEFT:
					entityMap[x-1][y][z] = currentChar;
					entityMap[x][y][z] = null;
					break;
				default:
					System.err.println("Character " + currentChar.name + "couldn't move, had an invalid facing of " + currentChar.getFacing().toString() + ".");
			}
		}
		currentChar.setMotion(false);
			
	}
	public boolean collision(Character currentChar, Entity[][][] entityMap, int x, int y, int z){
		int targetX=x, targetY=y, targetZ=z;
		switch(currentChar.getFacing()){
			case UP:
				targetY--;
				break;
			case RIGHT:
				targetX++;
				break;
			case DOWN:
				targetY++;
				break;
			case LEFT:
				targetX--;
				break;
			default:
				//when would this ever happen
				System.err.println("Character " + currentChar.name + "couldn't move, had an invalid facing of " + currentChar.getFacing().toString() + ".");
				return true;
		}
		//return ( entityMap[targetX][targetY][targetZ]!=null && (entityMap[targetX][targetY][targetZ].col && (currentChar instanceof Player || entityMap[targetX][targetY][targetZ].npcCol) ) ); //TODO is this right?
		if ( targetX >= this.width || targetX < 0 || targetY >= this.height || targetY < 0 || targetZ >= this.depth || targetZ < 0 ) //sanity check
			return true;
		else if ( entityMap[targetX][targetY][targetZ] == null ) //there's nothing there
			return false;
		else if ( entityMap[targetX][targetY][targetZ].col && currentChar instanceof Player ) //we're a player and col is true
			return true;
		else return ( entityMap[targetX][targetY][targetZ].npcCol );
	}

	public Player getPlayerCharacter() {
		// TODO Auto-generated method stub
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				for(int z = 0; z < depth; z++){
					Entity e = ents[x][y][z];
					if ( e != null && e instanceof Player ) {
						return (Player) e;
					}
				}
			}
		}
		return null;
	}
}
