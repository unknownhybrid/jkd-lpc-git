package com.pixilic.javakat.basicrpg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.pixilic.javakat.framework.RenderData;

public class BasicRPGNewCharRenderData extends RenderData {

	private Character player;
	
	private int cursor;
	
	private BufferedImage background;
	private BufferedImage roleimg;
	private BufferedImage cursorimg;
	private BufferedImage nametext;
	private BufferedImage roletext;
	private BufferedImage hptext;
	private BufferedImage mptext;
	private BufferedImage strtext;
	private BufferedImage deftext;
	private BufferedImage spdtext;
	private BufferedImage magtext;
	private BufferedImage mdftext;
	private BufferedImage lcktext;
	private BufferedImage bonustext;
	private BufferedImage done;
	
	private BufferedImage warrimg;
	private BufferedImage mageimg;
	private BufferedImage rgeimg;
	
	public BasicRPGNewCharRenderData(Character player){
		this.player = player;
		cursor = 0;
		
        URL url = com.pixilic.javakat.basicrpg.BasicRPG.class.getResource("rsrc/images/sword.png");
        try {
            warrimg = ImageIO.read(url);
        } catch (IOException e) {
			System.err.println("Couldn't find /rsrc/images/sword.png. Try again. :/");
		}
        url = com.pixilic.javakat.basicrpg.BasicRPG.class.getResource("rsrc/images/staff.png");
        try {
            mageimg = ImageIO.read(url);
        } catch (IOException e) {
			System.err.println("Couldn't find /rsrc/images/staff.png. Try again. :/");
		}
        url = com.pixilic.javakat.basicrpg.BasicRPG.class.getResource("rsrc/images/dagger.png");
        try {
            rgeimg = ImageIO.read(url);
        } catch (IOException e) {
			System.err.println("Couldn't find /rsrc/images/dagger.png. Try again. :/");
		}
		Graphics2D g;
		
		background = new BufferedImage(600, 480, BufferedImage.TYPE_INT_ARGB);
			g = background.createGraphics();
			g.setColor(new Color(100, 100, 120));
			g.fillRect(0, 0, background.getWidth(), background.getHeight());
		cursorimg = new BufferedImage(5, 5, BufferedImage.TYPE_INT_ARGB);
			g = cursorimg.createGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, cursorimg.getWidth(), cursorimg.getHeight());
		done = new BufferedImage(60, 20, BufferedImage.TYPE_INT_ARGB);
			g = done.createGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, done.getWidth(), done.getHeight());
			g.setColor(Color.BLACK);
			g.drawString("Done", 0, 15);
		update();
	}
	@Override
	public void forceRender() {
		// TODO Auto-generated method stub

	}

	@Override
	public Image render() {
		BufferedImage render = new BufferedImage(600, 480, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = render.createGraphics();
			g.drawImage(background, 0, 0, null);
			g.drawImage(nametext, 0, 0, null);
			g.drawImage(roletext, 0, 30, null);
			g.drawImage(roleimg, 210, 5, null);
			g.drawLine(0, 75, 140, 75);
			g.drawImage(hptext, 0, 80, null);
			g.drawImage(mptext, 0, 110, null);
			g.drawImage(strtext, 0, 140, null);
			g.drawImage(deftext, 0, 170, null);
			g.drawImage(magtext, 0, 200, null);
			g.drawImage(mdftext, 0, 230, null);
			g.drawImage(spdtext, 0, 260, null);
			g.drawImage(lcktext, 0, 290, null);
			g.drawLine(0, 345, 140, 345);
			g.drawImage(bonustext, 0, 350, null);
			if(player.points == 0) g.drawImage(done, 30, 380, null);
			if(cursor == 0 || cursor == 1){
				g.drawImage(cursorimg, 20, 20+((cursor)*30), null);
			}
			else{
				switch(cursor){
				case 2:
					g.drawImage(cursorimg, 20, 70+(30*(cursor-1)), null);
					break;
				case 3:
					g.drawImage(cursorimg, 20, 70+(30*(cursor-1)), null);
					break;
				case 4:
					g.drawImage(cursorimg, 20, 70+(30*(cursor-1)), null);
					break;
				case 5:
					g.drawImage(cursorimg, 20, 70+(30*(cursor-1)), null);
					break;
				case 6:
					g.drawImage(cursorimg, 20, 70+(30*(cursor-1)), null);
					break;
				case 7:
					g.drawImage(cursorimg, 20, 70+(30*(cursor-1)), null);
					break;
				case 8:
					g.drawImage(cursorimg, 20, 70+(30*(cursor-1)), null);
					break;
				case 9:
					g.drawImage(cursorimg, 20, 70+(30*(cursor-1)), null);
					break;
				case 10:
					g.drawImage(cursorimg, 20, 390, null);
				default:
					break;
				}
			}
		return render;
	}
	
	public void update(int cursor, Character player){
		this.cursor = cursor;
		this.player = player;
		update();
	}
	
	private void update(){
		Graphics2D g;
		roleimg = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
			g = roleimg.createGraphics();
			if(player.role == "Warrior") g.drawImage(warrimg.getScaledInstance(50, 50, 0), 0, 0, null);
			if(player.role == "Mage") g.drawImage(mageimg.getScaledInstance(50, 50, 0), 0, 0, null);
			if(player.role == "Rogue") g.drawImage(rgeimg.getScaledInstance(50, 50, 0), 0, 0, null);
		nametext = new BufferedImage(200, 30, BufferedImage.TYPE_INT_ARGB);
			g = nametext.createGraphics();
			g.setColor(Color.WHITE);
			g.setFont(Font.getFont("Arial"));
			g.drawString("Name: " + player.name, 30, 25);
		roletext = new BufferedImage(200, 30, BufferedImage.TYPE_INT_ARGB);
			g = roletext.createGraphics();
			g.setColor(Color.WHITE);
			g.setFont(Font.getFont("Arial"));
			g.drawString("Class: " + player.role, 30, 25);
		hptext = new BufferedImage(200, 30, BufferedImage.TYPE_INT_ARGB);
			g = hptext.createGraphics();
			g.setColor(Color.WHITE);
			g.setFont(Font.getFont("Arial"));
			g.drawString("Health (HP): " + player.HP, 30, 25);
		mptext = new BufferedImage(200, 30, BufferedImage.TYPE_INT_ARGB);
			g = mptext.createGraphics();
			g.setColor(Color.WHITE);
			g.setFont(Font.getFont("Arial"));
			g.drawString("Mana (MP): " + player.MP, 30, 25);
		strtext = new BufferedImage(200, 30, BufferedImage.TYPE_INT_ARGB);
			g = strtext.createGraphics();
			g.setColor(Color.WHITE);
			g.setFont(Font.getFont("Arial"));
			g.drawString("Strength (STR): " + player.STR, 30, 25);
		deftext = new BufferedImage(200, 30, BufferedImage.TYPE_INT_ARGB);
			g = deftext.createGraphics();
			g.setColor(Color.WHITE);
			g.setFont(Font.getFont("Arial"));
			g.drawString("Defense (DEF): " + player.DEF, 30, 25);
		spdtext = new BufferedImage(200, 30, BufferedImage.TYPE_INT_ARGB);
			g = spdtext.createGraphics();
			g.setColor(Color.WHITE);
			g.setFont(Font.getFont("Arial"));
			g.drawString("Speed (SPD): " + player.SPD, 30, 25);
		magtext = new BufferedImage(200, 30, BufferedImage.TYPE_INT_ARGB);
			g = magtext.createGraphics();
			g.setColor(Color.WHITE);
			g.setFont(Font.getFont("Arial"));
			g.drawString("Magic Strength (MAG): " + player.MAG, 30, 25);
		mdftext = new BufferedImage(200, 30, BufferedImage.TYPE_INT_ARGB);
			g = mdftext.createGraphics();
			g.setColor(Color.WHITE);
			g.setFont(Font.getFont("Arial"));
			g.drawString("Magic Defense (MDF): " + player.MDF, 30, 25);
		lcktext = new BufferedImage(200, 30, BufferedImage.TYPE_INT_ARGB);
			g = lcktext.createGraphics();
			g.setColor(Color.WHITE);
			g.setFont(Font.getFont("Arial"));
			g.drawString("Luck (LCK): " + player.LCK, 30, 25);
		bonustext = new BufferedImage(200, 30, BufferedImage.TYPE_INT_ARGB);
			g = bonustext.createGraphics();
			g.setColor(Color.WHITE);
			g.setFont(Font.getFont("Arial"));
			g.drawString("Bonus Points: " + player.points, 30, 25);
	}
}
