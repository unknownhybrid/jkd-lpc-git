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
					if(ents[x][y][z].mob == true){
						if(ents[x][y][z].getClass() == Character.class){ 			//this only covers characters. if we want to have moving platforms (etc.) via this engine, we can
							Character currentChar = (Character)ents[x][y][z];		//add that functionality later since I put that .mob check in and we use that in every entity
							ents[x][y][z] = null;
							moveOneCharacter(currentChar, ents, x, y, z);
						}
					}
				}
			}
		}
	}
	public void moveOneCharacter(Character currentChar, Entity[][][] entityMap, int x, int y, int z){
		if(collision(currentChar, entityMap, x, y, z)) return; //check if we can move first
		currentChar.isRendered = false;
		switch(currentChar.getFacing()){
		case UP:
			entityMap[x][y+1][z] = currentChar;
			entityMap[x][y][z] = null;
			break;
		case RIGHT:
			entityMap[x+1][y][z] = currentChar;
			entityMap[x][y][z] = null;
			break;
		case DOWN:
			entityMap[x][y-1][z] = currentChar;
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
	public boolean collision(Character currentChar, Entity[][][] entityMap, int x, int y, int z){
		switch(currentChar.getFacing()){
		case UP:
			return (!entityMap[x][y+1][z].col && (currentChar.isPlayer || !entityMap[x][y+1][z].npcCol));
		case RIGHT:
			return (!entityMap[x+1][y][z].col && (currentChar.isPlayer || !entityMap[x+1][y][z].npcCol));
		case DOWN:
			return (!entityMap[x][y-1][z].col && (currentChar.isPlayer || !entityMap[x][y-1][z].npcCol));
		case LEFT:
			return (!entityMap[x-1][y][z].col && (currentChar.isPlayer || !entityMap[x-1][y][z].npcCol));
		default:
			System.err.println("Character " + currentChar.name + "couldn't move, had an invalid facing of " + currentChar.getFacing().toString() + ".");
			return true;
		}
	}
}
