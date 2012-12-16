package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import methods.Methods;

import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
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
    
    private static Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch(IOException e) {
            return null;
        }
    }    


    
    public static void drawNPC(final Graphics g1, final NPC npc, final Color color, final int alpha) {    	
        if (npc != null) {
                        g1.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
                        for (Polygon p1 : npc.getBounds()) {
                                        g1.fillPolygon(p1);
                        }
        }
    }
    
    public static void drawItem(final Graphics g1, final GroundItem item, final Color color, final int alpha) {    	
        if (item != null) {
                        g1.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
                        for (Polygon p1 : item.getBounds()) {
                                        g1.fillPolygon(p1);
                        }
        }
    }
    
    


    public final static Color color1 = new Color(0, 0, 0, 221);
    public final static Color color2 = new Color(0, 0, 0);
    public final static Color color3 = new Color(255, 255, 255, 221);
    public final static Color color4 = new Color(0, 0, 0, 75);
    public final static Color color5 = new Color(255, 0, 0);
    public final static Color color6 = new Color(255, 255, 255);
    public final static Color color_grey = new Color(73, 73, 73);
    public static Color color_skill = new Color(0, 0, 0, 170);
   // public final static Color color_skill = new Color(Data.skillColourR, Data.skillColourG, Data.skillColourB, 170);
    public final static Color color_const = new Color(255, 43, 43, 170);
    public final static Color color_def = new Color(89, 144, 255, 170);
    public final static Color color_mouse = new Color(0, 255, 0, 210);
    public final static Color color_mouse_opa = new Color(89, 255, 89, 120);

    public final static BasicStroke stroke1 = new BasicStroke(1);
    public final static BasicStroke stroke4 = new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);

    public final static Font font1 = new Font("Myriad Pro", 1, 16);
    public final static Font font2 = new Font("Myriad Pro", 0, 12);
    public final static Font font3 = new Font("Myriad Pro", 1, 10);
    public final static Font font4 = new Font("Myriad Pro", 0, 10); 
    


	public static void doPaint(Graphics g1) {		
		Graphics2D g = (Graphics2D)g1;
        g.setRenderingHints(antialiasing);		
        
        final NPC nearestMob = NPCs.getNearest(Combat.MobFilter);
        final GroundItem item = GroundItems.getNearest(LootItems.ItemFilter);
        int xpCurrent = Skills.getExperienceRequired(Skills.getRealLevel(Data.chosenSkill));
        int xpNext = Skills.getExperienceRequired(Skills.getRealLevel(Data.chosenSkill) + 1);
        int xpPerc = (xpCurrent / xpNext)*100;
        int xpBarLength = xpPerc*262;
        int xpCurrentConst = Skills.getExperienceRequired(Skills.getRealLevel(3));
        int xpNextConst = Skills.getExperienceRequired(Skills.getRealLevel(3) + 1);
        int xpPercConst = (xpCurrentConst / xpNextConst)*100;
        int xpBarLengthConst = xpPercConst*262;  
        
        if (showNPC) {        
	        if (nearestMob != null) {
	        	drawNPC(g1, nearestMob, Color.RED, 200);
	        }
	        if (item != null) {
	        	drawItem(g1, item, Color.CYAN, 200);	        	
	        	g1.setFont(font2);	  
	        	g1.setColor(Color.BLACK);
	        	g1.drawString(item.getGroundItem().getName() + " x" + item.getGroundItem().getStackSize(), (int) item.getCentralPoint().getX()+26, (int) (item.getCentralPoint().getY()+1));
	        	g1.setColor(Color.CYAN);
	        	g1.drawString(item.getGroundItem().getName() + " x" + item.getGroundItem().getStackSize(), (int) item.getCentralPoint().getX()+25, (int) (item.getCentralPoint().getY()));
	        	
	        }	        

        }
        
        if (showPaint) { 
    	  	final Point p = Mouse.getLocation(); 
    	    g.setColor(color_mouse_opa);
    	    g.drawLine(0, p.y, Game.getDimensions().width, p.y);
    	    g.drawLine(p.x, 0, p.x, Game.getDimensions().height);
    	    g.setColor(Mouse.isPressed() ? Color.RED : color_mouse);
    	    g.setStroke(stroke4);
    	    if (!Mouse.isPressed()) {
    	    g.drawLine(Mouse.getX(), Mouse.getY() + 8, Mouse.getX(), Mouse.getY() - 8);
    	    g.drawLine(Mouse.getX() + 8, Mouse.getY(), Mouse.getX() - 8, Mouse.getY());
    	    } else {
    	    	g.fillRect(Mouse.getX() - 5, Mouse.getY() + 5, 3, 3);
    	    	g.fillRect(Mouse.getX() + 5, Mouse.getY() + 5, 3, 3);
    	    	g.fillRect(Mouse.getX() - 5, Mouse.getY() - 5, 3, 3);
    	    	g.fillRect(Mouse.getX() + 5, Mouse.getY() - 5, 3, 3);
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
          g.fillRect(255, 348, 263, 20);          
          g.fillRect(255, 368, 263, 20);
          if (!showDef && Data.chosenSkill != 1) {
        	  g.setColor(color_skill);          
              //g.fillRect(256, 349, xpBarLength, 18);
              g.fillRect(256, 349, 131, 19);
          } else {
        	  g.setColor(color_def);          
              //g.fillRect(256, 349, xpBarLength, 18);
              g.fillRect(256, 349, 131, 19); 
          }
          g.setColor(color_const);          
          //g.fillRect(256, 369, xpBarLengthConst, 18);
          g.fillRect(256, 369, 188, 19);
          g.setColor(color2);
          g.drawLine(256, 368, 518, 368);
          g.setFont(font3);
          g.drawString("v1.0", 112, 364);
          g.setColor(color5);
          g.drawString("v1.0", 111, 363);
          g.setFont(font4);
          g.setColor(color6);
          if (!showDef && Data.chosenSkill != 1) {
              g.drawString("[ " + Data.skillString + ": " + Skills.getRealLevel(Data.chosenSkill) + " (+" + (Skills.getRealLevel(Data.chosenSkill) - Data.startLevel) + ") | " + Methods.kFormat(Methods.getXpHr(Data.chosenSkill)) + " xp/h | TTL: " + Methods.getTTNL(Data.chosenSkill) + " ]", 260, 362);
  
          } else {
              g.drawString("[ Defence: " + Skills.getRealLevel(1) + " (+" + (Skills.getRealLevel(1) - Data.startDefLevel) + ") | " + Methods.kFormat(Methods.getXpHr(1)) + " xp/h | TTL: " + Methods.getTTNL(1) + " ]", 260, 362);
          }
          g.drawString("[ Constitution: " + Skills.getRealLevel(Skills.CONSTITUTION) + " (+" + (Skills.getRealLevel(Skills.CONSTITUTION) - Data.startConstLevel) + ") | " + Methods.kFormat(Methods.getXpHr(3)) + " xp/h | TTL: " + Methods.getTTNL(3) + " ]", 260, 382);
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
        }
	}	
}
