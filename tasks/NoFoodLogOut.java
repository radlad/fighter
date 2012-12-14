package tasks;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Lobby;
import org.powerbot.game.bot.Context;

import data.Data;

public class NoFoodLogOut extends Node {
	@Override
	public boolean activate() {		
		return Inventory.getCount(Data.foodId) == 0 && Data.useFood && Data.foodId != 0;
	}

	@Override
	public void execute() {	
		if (!Lobby.isOpen()) {
				Data.LOG_OUT = true;
				System.out.println("Out of food, now signing out.");
	            System.out.println("Stopping Brawler.");
	            System.out.println("Ran for: " + Data.runTime.toElapsedString());
	            System.out.println("Gained " + (Skills.getExperience(Data.chosenSkill) - Data.startXP) + " experience.");
	            System.out.println("Gained " + (Skills.getRealLevel(Data.chosenSkill) - Data.startLevel) + " levels in " + Data.skillString + ".");
				if (!Players.getLocal().isInCombat()) {
					Data.outOfFood = true;
					Game.logout(true);					
				}
		} 
	}	
		
}
