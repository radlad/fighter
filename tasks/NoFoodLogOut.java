package tasks;

import methods.Methods;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.node.Item;

import data.Data;

public class NoFoodLogOut extends Node {
	@Override
	public boolean activate() {		
		return Inventory.getCount(Data.foodId) == 0 && Data.useFood && Data.foodId != 0;
	}

	@Override
	public void execute() {	
		if (Game.isLoggedIn()) {
				Data.LOG_OUT = true;
				Data.status = "Logging out...";
				System.out.println("Out of food, now signing out.");
	            System.out.println("Stopping Brawler.");
	            System.out.println("Ran for: " + Data.runTime.toElapsedString());
	            System.out.println("Gained " + (Skills.getExperience(Data.chosenSkill) - Data.startXP) + " experience.");
	            System.out.println("Gained " + (Skills.getRealLevel(Data.chosenSkill) - Data.startLevel) + " levels in " + Data.skillString + ".");
	            int tabID = Methods.getTeletabId();
	            Item teleTab = Inventory.getItem(tabID);
	            if (teleTab != null && Data.useTeletab && !Data.usedTeletab) {
	            	teleTab.getWidgetChild().click(true);
	            	Data.usedTeletab = true;
	            	Task.sleep(Random.nextInt(1000, 2000));
	            	Game.logout(false);
	            }
				if (!Players.getLocal().isInCombat()) {
					Data.outOfFood = true;
					Game.logout(false);					
				}
		} 
	}	
		
}
