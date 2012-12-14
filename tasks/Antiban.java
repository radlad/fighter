package tasks;

import java.awt.Point;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import data.Data;

public class Antiban extends Node {
    private Timer antiBanTimer;
    private int minTime = Data.antibanValue1;
    private int maxTime = Data.antibanValue2;

    public Antiban() {
            antiBanTimer = new Timer(Random.nextInt(minTime, maxTime));
    }

    private void resetAntiBanTime() {
            antiBanTimer.setEndIn(Random.nextInt(minTime, maxTime));
    }

	@Override
	public boolean activate() {
		return !antiBanTimer.isRunning();
	}

	@Override
	public void execute() {
		int randomAntiban = Random.nextInt(0, 5);
		int randomTab = Random.nextInt(0, 5);
		antiBanTimer = new Timer(Random.nextInt(minTime, maxTime));	
		
		//temporary food fix, also in combat
		//TODO remove once hooks are fixed
		int currentHp = Integer.parseInt(Widgets.get(748, 8).getText());
		if (currentHp < Data.totalHp*(Data.eatAt*0.01) && Data.useFood) {
			Item food = Inventory.getItem(Data.foodId);			
			if (food != null) {				
				System.out.println("Eating food.");
				Data.status = "Eating food.";
				food.getWidgetChild().click(true); 						
			}
		}
        
        switch(randomAntiban) {
        case 0:
            int randomSkill = Random.nextInt(0, 24);
            System.out.println("Checking random skill.");
            Tabs.STATS.open();
            WidgetChild randStat = Skills.getWidgetChild(randomSkill);
            Point randStatPoint = randStat.getAbsoluteLocation();
            randStatPoint.x += Random.nextInt(-8, 8);
            randStatPoint.y += Random.nextInt(-8, 8);
            Task.sleep(Random.nextInt(200,300));
            Mouse.move(randStatPoint);
            Task.sleep(Random.nextInt(600,800));
            break;
        case 1:
        	if(Players.getLocal().getInteracting() != null) {
        		System.out.println("Moving mouse around.");
                int currentMouseX = Mouse.getX();
                int currentMouseY = Mouse.getY();
                Mouse.move(currentMouseX + Random.nextInt(-200, 200), currentMouseY + Random.nextInt(-200, 200));
	        } 
	        break;
        case 2: 
            default:
                    if(Players.getLocal().getInteracting() != null) {
                    	System.out.println("Turning camera to what we're doing.");
                            Camera.turnTo(Players.getLocal().getInteracting());
                            int currentPitch = Camera.getPitch();
                            int currentYaw = Camera.getYaw();
                            Camera.setPitch(currentPitch + Random.nextInt(-53, 53));
                            Camera.setAngle(currentYaw + Random.nextInt(-75, 75));
                    } else {
                    	System.out.println("Moving camera a bit.");
                            int currentPitch = Camera.getPitch();
                            int currentYaw = Camera.getYaw();
                            Camera.setPitch(currentPitch + Random.nextInt(-200, 200));
                            Camera.setAngle(currentYaw + Random.nextInt(-200, 200));
                    }
                    break;
        case 3:
        	if(Players.getLocal() != null) {
                System.out.println("Checking random useful tab.");
                switch(randomTab) {                            
                case 1:                	
            	default:
                		Tabs.FRIENDS_CHAT.open();
                		Task.sleep(Random.nextInt(800,1200));
                		break;
                case 2:
                		Tabs.QUESTS.open();
                		Task.sleep(Random.nextInt(800,1200));
                		break;
                case 3:
                		Tabs.FRIENDS.open();
                		Task.sleep(Random.nextInt(800,1200));
                		break;
                case 4:
                		Tabs.CLAN_CHAT.open();
                		Task.sleep(Random.nextInt(800,1200));
                		break;
                case 5:
                		Tabs.STATS.open();
                		Task.sleep(Random.nextInt(800,1200));
                		break;                
                }                
        	}
        	break;
             
                
        case 4:
        	if(Players.getLocal() != null) {
                System.out.println("Moving camera a bit.");
                int currentPitch = Camera.getPitch();
                int currentYaw = Camera.getYaw();
                Camera.setPitch(currentPitch + Random.nextInt(-500, 500));
                Camera.setAngle(currentYaw + Random.nextInt(-500, 500));
        	}
        	break;
        }
        resetAntiBanTime();		
	}
}