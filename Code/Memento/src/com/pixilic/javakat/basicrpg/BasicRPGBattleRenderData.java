package com.pixilic.javakat.basicrpg;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.pixilic.javakat.framework.RenderData;

public class BasicRPGBattleRenderData extends RenderData {

	private Character player, enemy;
	
	private BufferedImage playerSprite;
	private BufferedImage enemySprite;
	
	private int cursor;
	
	private BufferedImage cursorimg;
	private BufferedImage playernametext;
	private BufferedImage enemynametext;
	private BufferedImage hpimg;
	private BufferedImage mpimg;
	private BufferedImage menuimg;
	private BufferedImage enemyhpbar;
	//private BufferedImage damageimg;
	
	//TEST IMAGE
	private BufferedImage levelimg;
	
	public BasicRPGBattleRenderData(Character player, Character enemy){
		this.player = player;
		this.enemy = enemy;
		playerSprite = player.sprite.getPlayerBattleRender(); //this is here to reduce runtime overhead; technically we could make this call every update though
		enemySprite = enemy.sprite.getEnemyBattleRender();
		cursor = 0;
		basicImages();
		updateImages();
	}
	
	@Override
	public void forceRender() {
		
	}

	@Override
	public Image render() {
		Graphics2D g;
		BufferedImage render = new BufferedImage(600, 480, BufferedImage.TYPE_INT_ARGB);
			g = render.createGraphics();
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, 600, 240);
			g.setColor(Color.GREEN);
			g.fillRect(0, 241, 600, 120);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 361, 600, 120);
			g.drawImage(playerSprite, 30, 230, null);
			g.drawImage(enemySprite, render.getWidth() - enemySprite.getWidth(), 230, null);
			g.drawImage(playernametext, 0, 361, null);
			g.drawImage(hpimg, 0, 381, null);
			g.drawImage(mpimg, 0, 396, null);
			g.drawImage(levelimg, 0, 411, null);
			g.drawImage(menuimg, 120, 361, null);
			g.drawImage(cursorimg, 165 - 3, 361 + 35 + (15*cursor), null);
			g.drawImage(enemynametext, render.getWidth() - (enemynametext.getWidth()), 361, null);
			g.drawImage(enemyhpbar, render.getWidth() - (enemynametext.getWidth()), 391, null);
		return render;
	}
	public void update(Character player, Character enemy, int cursor){
		this.player = player;
		this.enemy = enemy;
		this.cursor = cursor;
		this.playerSprite = player.sprite.getPlayerBattleRender();
		this.enemySprite = enemy.sprite.getEnemyBattleRender();
		
		updateImages();
	}
	private void basicImages(){
		
		Graphics2D g;
		
		cursorimg = new BufferedImage(5, 5, BufferedImage.TYPE_INT_ARGB);
			g = cursorimg.createGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, cursorimg.getWidth(), cursorimg.getHeight());
		levelimg = new BufferedImage(100, 30, BufferedImage.TYPE_INT_ARGB);
			g = levelimg.createGraphics();
			g.setColor(Color.WHITE);
			g.drawString("Level: " + player.level, 0, 25);
	}
	private void updateImages(){

		Graphics2D g;
			
		playernametext = new BufferedImage(("Player: " + player.name).length()*8, 100, BufferedImage.TYPE_INT_ARGB);
			g = playernametext.createGraphics();
			g.setColor(Color.WHITE);
			g.drawString("Name: " + player.name, 0, 25);
			
		enemynametext = new BufferedImage(("Enemy: " + enemy.name).length()*8, 100, BufferedImage.TYPE_INT_ARGB);
			g = enemynametext.createGraphics();
			g.setColor(Color.WHITE);
			g.drawString("Enemy: " + enemy.name, 0, 25);
			
		hpimg = new BufferedImage(100, 30, BufferedImage.TYPE_INT_ARGB);
			g = hpimg.createGraphics();
			g.setColor(Color.WHITE);
			g.drawString("HP: " + player.HP + " / " + player.HP, 0, 25);
		
		mpimg = new BufferedImage(100, 30, BufferedImage.TYPE_INT_ARGB);
			g = mpimg.createGraphics();
			g.setColor(Color.WHITE);
			g.drawString("MP: " + player.MP + " / " + player.MP, 0, 25);
			
		enemyhpbar = new BufferedImage(120, 15, BufferedImage.TYPE_INT_ARGB);
			g = enemyhpbar.createGraphics();
			g.setColor(Color.WHITE);
			g.drawRect(0, 0, enemyhpbar.getWidth()-3, enemyhpbar.getHeight()-1);
			g.setColor(calculateColorBasedOnRemainingHP(enemy.currentHP, enemy.HP));
			g.fillRect(4, 4, calculateWidthBasedOnRemainingHP(enemy.currentHP, enemy.HP, enemyhpbar.getWidth()-5), enemyhpbar.getHeight()-5);
		
		menuimg = new BufferedImage(200, 120, BufferedImage.TYPE_INT_ARGB);
			g = menuimg.createGraphics();
			g.setColor(Color.WHITE);
			g.drawRect(0, 0, menuimg.getWidth()-3, menuimg.getHeight());
			g.drawRect(1, 1, menuimg.getWidth()-4, menuimg.getHeight());
			g.drawString("MENU", 5, 25);
			g.drawString("Attack", 50, 40);
			g.drawString(player.abilityset.getAvailableAbilities().get(0).getDisplayableType(), 50, 55);
			g.setColor(Color.GRAY);
			g.drawString("Item", 50, 70);
			g.setColor(Color.WHITE);
			g.drawString("Run", 50, 85);
	}

	private int calculateWidthBasedOnRemainingHP(int currentHP, int HP, int width) {
		double percentRemaining = (double) currentHP / (double) HP;
		width = (int) (width*percentRemaining);
		return width;
	}

	private Color calculateColorBasedOnRemainingHP(int remainingHP, int maxHP) {
		int netDamage = maxHP - remainingHP;
		if(netDamage >= maxHP) return Color.DARK_GRAY; //this is the color of the background, just in case something messed up it will color over it
		else if(netDamage >= maxHP*.7) return Color.RED;
		else if(netDamage >= maxHP*.5) return Color.YELLOW;
		else return Color.GREEN;
	}
}
