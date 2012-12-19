package methods;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.SkillData;
import org.powerbot.game.api.util.SkillData.Rate;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.Item;

import data.Data;

public class Methods {
	
	public static NumberFormat k = new DecimalFormat("###,###,###");
	public static NumberFormat z = new DecimalFormat("#");
	
	public static String kFormat(final int num) {
		return num / 1000 + "." + (num % 1000) / 100 + "K";
		}
	
	public static int getAdrPercent() {		
		return Settings.get(679) / 10;		
	}
	
	public static int getXpHr(int skill) {
		SkillData sr = new SkillData(Data.runTime);
		return sr.experience(Rate.HOUR, skill);		
	}
	
	public static long getTTNL(int skill) {
		SkillData sr = new SkillData(Data.runTime);
		return sr.timeToLevel(Rate.HOUR, skill);
	}
	
	public static double getExpBarLength(int skill, int maxlength) {
        int xpCurrent = Skills.getExperienceRequired(Skills.getRealLevel(skill));
        int xpNext = Skills.getExperienceRequired(Skills.getRealLevel(skill) + 1);        
        double xpBarLength = (xpCurrent / xpNext)*maxlength;
		return xpBarLength;   
	}
	
	public static int getPerHour(int integer) {
		int perHour = (int) (integer * 3600000D / (System.currentTimeMillis() - Data.startTime));
		return perHour;		
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
    
    public static void drawTile(final Graphics g1, final Tile tile, final Color color, final int alpha) {    	
        if (tile != null) {
                        g1.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
                        for (Polygon p1 : tile.getBounds()) {
                                        g1.drawPolygon(p1);
                        }
        }
    }
    
    public static void fillTile(final Graphics g1, final Tile tile, final Color color, final int alpha) {    	
        if (tile != null) {
                        g1.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
                        for (Polygon p1 : tile.getBounds()) {
                                        g1.fillPolygon(p1);
                        }
        }
    }
	
	public static void useMomentum() {
		Data.eocMaximise.click(true);				
		if (Data.eocButton1.validate()) {			
			System.out.println("Activating Momentum.");					
			Data.eocButton1.click(true);					
			Data.eocMinimise.click(true);					
		}	
	}
	
	public static void useActionBarButton2() {
		Data.eocMaximise.click(true);				
		if (Data.eocButton2.validate()) {			
			Data.eocButton2.click(true);					
			Data.eocMinimise.click(true);					
		}	
	}
	public static void useActionBarButton3() {
		Data.eocMaximise.click(true);				
		if (Data.eocButton3.validate()) {			
			Data.eocButton3.click(true);					
			Data.eocMinimise.click(true);					
		}	
	}
	public static void useActionBarButton4() {
		Data.eocMaximise.click(true);				
		if (Data.eocButton4.validate()) {			
			Data.eocButton4.click(true);					
			Data.eocMinimise.click(true);					
		}	
	}
	public static void useActionBarButton5() {
		Data.eocMaximise.click(true);				
		if (Data.eocButton5.validate()) {			
			Data.eocButton5.click(true);					
			Data.eocMinimise.click(true);					
		}	
	}
	public static void useActionBarButton6() {
		Data.eocMaximise.click(true);				
		if (Data.eocButton6.validate()) {			
			Data.eocButton6.click(true);								
		}	
	}
	
	public static boolean hasPotion(int[] array) {
        for(int id : array) {
            if(Inventory.getCount(id) > 0) {
                return true;      
            }
        }
		return false;		
	}
	
	public static boolean hasAlchemyItem() {
        for(Item i : Inventory.getItems()) {
            if(Data.alchemy_selected_item.contains(i.getName())) {
                return true;      
            }
        }
		return false;		
	}
	
    public static boolean hasAntifireOn() {
        return Settings.get(1299) > 0;
}
}
