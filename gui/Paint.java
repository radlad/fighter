package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import methods.Methods;

import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;

import tasks.LootItems;

import combat.Combat;

import data.Data;

public class Paint {	
	public static boolean showPaint = true;
	public static boolean showNPC = true;
	public static boolean showMouse = true;
	public static boolean showDef = false;	
    private final static RenderingHints antialiasing = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    public final static Color color1 = new Color(0, 0, 0, 180);
    public final static Color color2 = new Color(0, 0, 0);
    public final static Color color3 = new Color(255, 255, 255, 221);
    public final static Color color4 = new Color(0, 0, 0, 75);
    public final static Color color5 = new Color(255, 0, 0);
    public final static Color color6 = new Color(255, 255, 255);
    public final static Color color7 = new Color(255, 0, 0, 200);
    public final static Color color8 = new Color(0, 0, 0, 160);
    public final static Color color_npc = new Color(255, 0, 0, 225);
    public final static Color color_npc2 = new Color(255, 0, 0, 100);
    public final static Color color_item = new Color(0, 255, 255, 225);
    public final static Color color_item2 = new Color(0, 255, 255, 100);
    public final static Color color_grey = new Color(73, 73, 73);
    public static Color color_skill = new Color(0, 0, 0, 170);
    public final static Color color_const = new Color(255, 43, 43, 170);
    public final static Color color_def = new Color(89, 144, 255, 170);
    public final static Color color_mouse = new Color(250, 250, 250, 200);
    public final static Color color_mouse_opa = new Color(222, 222, 222, 150);

    public final static BasicStroke stroke1 = new BasicStroke(1);
    public final static BasicStroke stroke3 = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);

    public final static Font font1 = new Font("Myriad Pro", 1, 16);
    public final static Font font2 = new Font("Myriad Pro", 0, 12);
    public final static Font font3 = new Font("Myriad Pro", 1, 10);
    public final static Font font4 = new Font("Myriad Pro", 0, 10); 
    public final static Font font5 = new Font("Myriad Pro", 1, 32);
    public final static Font font6 = new Font("Myriad Pro", 0, 16);

	public static void doPaint(Graphics g1) {		
		Graphics2D g = (Graphics2D)g1;
        g.setRenderingHints(antialiasing);		
        
        final NPC nearestMob = NPCs.getNearest(Combat.MobFilter);
        final GroundItem item = GroundItems.getNearest(LootItems.ItemFilter);

        
        if (showNPC) {     
        	
        	if (nearestMob != null && nearestMob.isOnScreen()) {
        		g1.setFont(font3); 
        		g1.setColor(color_npc);
	        	Methods.drawNPC(g1, nearestMob, color_npc, 75);
	        	g1.setColor(Color.BLACK);
	        	g1.drawString(nearestMob.getName(), (int) nearestMob.getCentralPoint().getX()+26, (int) (nearestMob.getCentralPoint().getY()-6));
	        	g1.setColor(color_npc);
	        	g1.drawString(nearestMob.getName(), (int) nearestMob.getCentralPoint().getX()+25, (int) (nearestMob.getCentralPoint().getY()-5));
	        	g1.setColor(Color.BLACK);
	        	g1.drawString("Level: " + nearestMob.getLevel(), (int) nearestMob.getCentralPoint().getX()+26, (int) (nearestMob.getCentralPoint().getY()+5));
	        	g1.setColor(color_npc);
	        	g1.drawString("Level: " + nearestMob.getLevel(), (int) nearestMob.getCentralPoint().getX()+25, (int) (nearestMob.getCentralPoint().getY()+4));

	        }
	        if (item != null) {
	        	Methods.drawTile(g1, item.getLocation(), color_item, 225);
	        	Methods.fillTile(g1, item.getLocation(), color_item2, 100);	        	
	        	g1.setFont(font3);	  
	        	g1.setColor(Color.BLACK);
	        	g1.drawString(item.getGroundItem().getName() + " x" + item.getGroundItem().getStackSize(),
	        			(int) item.getCentralPoint().getX()+26, (int) (item.getCentralPoint().getY()+1));
	        	g1.setColor(Color.CYAN);
	        	g1.drawString(item.getGroundItem().getName() + " x" + item.getGroundItem().getStackSize(),
	        			(int) item.getCentralPoint().getX()+25, (int) (item.getCentralPoint().getY()));
	        	
	        }	        

        }
        
        if (showPaint) { 
    	  	final Point p = Mouse.getLocation(); 
    	    g.setColor(color_mouse_opa);
    	    g.drawLine(0, p.y, Game.getDimensions().width, p.y);
    	    g.drawLine(p.x, 0, p.x, Game.getDimensions().height);
    	    g.setColor(Mouse.isPressed() ? Color.RED : color_mouse);
    	    g.setStroke(stroke3);
    	    if (!Mouse.isPressed()) {
    	    g.drawLine(Mouse.getX(), Mouse.getY() + 8, Mouse.getX(), Mouse.getY() - 8);
    	    g.drawLine(Mouse.getX() + 8, Mouse.getY(), Mouse.getX() - 8, Mouse.getY());
    	    } else {
    	    	g.fillRect(Mouse.getX() - 6, Mouse.getY() + 6, 3, 3);
    	    	g.fillRect(Mouse.getX() + 6, Mouse.getY() + 6, 3, 3);
    	    	g.fillRect(Mouse.getX() - 6, Mouse.getY() - 6, 3, 3);
    	    	g.fillRect(Mouse.getX() + 6, Mouse.getY() - 6, 3, 3);
    	    }
          g.setColor(color1);
          g.fillRect(1, 348, 517, 40);
          g.setColor(color2);
          g.setStroke(stroke1);
          g.drawRect(1, 348, 517, 40);
          g.drawLine(255, 388, 255, 348);
          g.setFont(font1);
          g.drawString("MondoFighter", 9, 364);
          g.setColor(color3);
          g.drawString("MondoFighter", 8, 363);
          g.setFont(font2);
          g.drawString("Run Time: " + Data.runTime.toElapsedString(), 8, 380);
          g.drawString("Status: " + Data.status, 132, 363);
          g.drawString("Kills: " + Data.killCounter + " (" + Methods.getPerHour(Data.killCounter) + " p/h)", 132, 380);
          g.setColor(color4);
          if (!showDef && Data.chosenSkill != 1) {
        	  g.setColor(color_skill);          
              g.fillRect(256, 349, (int) Methods.getExpBarLength(Data.chosenSkill, 262), 19);              
          } else {
        	  g.setColor(color_def);          
              g.fillRect(256, 349, (int) Methods.getExpBarLength(1, 262), 19);              
          }
          g.setColor(color_const);          
          g.fillRect(256, 369, (int) Methods.getExpBarLength(3, 262), 19);          
          g.setColor(color2);
          g.drawLine(256, 368, 518, 368);
          g.setFont(font3);
          g.drawString("v1.0", 112, 364);
          g.setColor(color5);
          g.drawString("v1.0", 111, 363);
          g.setFont(font4);
          g.setColor(color6);
          if (!showDef && Data.chosenSkill != 1) {
              g.drawString("[ " + Data.skillString + ": " + Skills.getRealLevel(Data.chosenSkill) + " (+" + (Skills.getRealLevel(Data.chosenSkill) - Data.startLevel) +
            		  ") | " + Methods.t.format(Methods.getExpPercent(Data.chosenSkill)) + "% | " + Methods.kFormat(Methods.getXpHr(Data.chosenSkill))
            		  + " xp/h | TTL: " + Methods.getTTNL(Data.chosenSkill) + " ]", 260, 362);  
          } else {
              g.drawString("[ Defence: " + Skills.getRealLevel(1) + " (+" + (Skills.getRealLevel(1) - Data.startDefLevel) + ") | " 
            		  + Methods.t.format(Methods.getExpPercent(Data.chosenSkill)) + "% | " + Methods.kFormat(Methods.getXpHr(1)) + " xp/h | TTL: "
            		  + Methods.getTTNL(1) + " ]", 260, 362);
          }
          g.drawString("[ Constitution: " + Skills.getRealLevel(Skills.CONSTITUTION) + " (+" + (Skills.getRealLevel(Skills.CONSTITUTION) - Data.startConstLevel) + ") | " 
        		  	+ Methods.t.format(Methods.getExpPercent(Data.chosenSkill)) + "% | " + Methods.kFormat(Methods.getXpHr(3))
        		  	+ " xp/h | TTL: " + Methods.getTTNL(3) + " ]", 260, 382);
          g.setColor(color1);
          g.fillRect(8, 334, 69, 14);
          g.fillRect(82, 334, 69, 14);
          g.fillRect(156, 334, 69, 14);
          g.fillRect(230, 334, 69, 14);
          g.setColor(color6);
          g.drawString("Toggle Paint", 15, 345);
          g.drawString("Toggle Draw", 91, 345);
          g.drawString("Show GUI", 168, 345);
          g.drawString("Pause Bot", 244, 345); 
          
	        if (Data.chatWarning && Data.chatTimer.isRunning()) {
		        g.setColor(color8);
		        g.fillRect(236, 414, 255, 67);
		        g.setColor(color7);
		        g.setStroke(stroke3);
		        g.drawRect(236, 414, 255, 67);
		        g.setFont(font5);
		        g.drawString("WARNING", 299, 449);
		        g.setFont(font6);
		        g.drawString("Last Message: " + Data.chatTimer.toElapsedString() + " ago", 278, 467);
		        g.drawLine(474, 420, 484, 430);
		        g.drawLine(474, 430, 484, 420);
	        }
        }
	}	
}
