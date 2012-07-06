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

	Entity[][][] ents;
	int width;
	int height;
	int depth = 3; //FIXME hardcoding
		
	public Map(int width, int height) {
		this.ents = new Entity[width][height][depth];
		for ( int x=0;x<width;x++ ) {
			for ( int y=0;y<height;y++ ) {
				ents[x][y]=null;
			}
		}
		this.width = width;
		this.height = height;
	}

	public void set(int x,int y,Entity en) {
		
		for ( Entity t : ents[x][y] ) {
			if ( t == null ) {
				t=en;
				return;
			}
		}
		//HAHAHAHAHAHA!
		Arrays.sort(ents[x][y]);
		
	}

}