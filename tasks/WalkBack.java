package tasks;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Random;

import data.Data;

public class WalkBack extends Node {	
	@Override
	public boolean activate() {		
		return Calculations.distanceTo(Data.START_LOCATION) >= Data.fighterRadius && Players.getLocal().getInteracting() == null && !Data.LOG_OUT;		
	}
	
	public void execute() {		
		Data.status = "Walking back.";
		System.out.println("Walking back to start location.");
        do {
        	if (Data.START_LOCATION.isOnScreen()) {
        		Data.START_LOCATION.interact("Walk");
        		Task.sleep(Random.nextInt(400, 600));
        	} else {
        		Data.START_LOCATION.clickOnMap();
        		Task.sleep(Random.nextInt(400, 600));
        	}        	
        } while (Calculations.distanceTo(Data.START_LOCATION) >= 1 && !Players.getLocal().isMoving());		
	}	
}
