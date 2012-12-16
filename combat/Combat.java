package combat;

import methods.Methods;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.interactive.Player;
import org.powerbot.game.api.wrappers.node.Item;
import data.Data;

public class Combat extends Node {
	
	public static Filter<NPC> MobFilter = new Filter<NPC>(){		
		@Override
		public boolean accept(NPC n) {
			for(int i : Data.selectedMonstersInt) {
				return n != null && Data.selectedMonstersInt.contains(n.getId()) && n.getLocation().canReach() 
						&& Calculations.distanceTo(Data.START_LOCATION.getLocation()) <= Data.fighterRadius 
						&& n.getHpPercent() > 0 && (n.getLocation() != Players.getLocal().getLocation()) &&
						!n.isInCombat();
			}
			return false;
		}		
	};
	
	public static Filter<NPC> CombatFilter = new Filter<NPC>(){		
		@Override
		public boolean accept(NPC n) {
			for(int i : Data.selectedMonstersInt) {
				return n != null && n.getLocation().canReach()						
						&& n.getHpPercent() > 0 && n.getInteracting().equals(Players.getLocal());
			}
			return false;
		}		
	};
	
	@Override
	public boolean activate() {		
		return Calculations.distanceTo(Data.START_LOCATION) < Data.fighterRadius
				&& Players.getLocal() != null && Players.getLocal().getInteracting() == null && !Data.LOG_OUT;
	}

	@Override
	public void execute() {	
		final NPC npc = NPCs.getNearest(MobFilter);
		final NPC cnpc = NPCs.getNearest(CombatFilter);
		final Player p = Players.getLocal();
		Data.oldKills = Data.killCounter;	

		if (cnpc == null) {
			if (npc == null) {
				Data.status = "Waiting for NPC...";
				Task.sleep(50);
			} else {		
				if (npc.isOnScreen()) {
					if (p.getInteracting() == null && !p.isMoving()) {
						npc.interact("Attack");
						Data.status = "Fighting NPC!";
						System.out.println("Attacking monster.");
						Task.sleep(Random.nextInt(400, 600));
					}
				} else if (!npc.isOnScreen()){
					Camera.setPitch(Random.nextInt(1, 25));
					Camera.turnTo(npc);				
					Data.status = "Finding NPC.";
					System.out.println("Finding monster.");
				} else if (npc.getLocation() == p.getLocation()) {
					p.getLocation().click(true);
				}
			}	
		} else {
			if (p.getInteracting() == null && !p.isMoving()) {
				cnpc.interact("Attack");
					Data.status = "Fighting NPC!";
					System.out.println("Attacking monster.");
					Task.sleep(Random.nextInt(400, 600));
				} else if (!cnpc.isOnScreen()){
					Camera.setPitch(Random.nextInt(1, 25));
					Camera.turnTo(cnpc);				
					Data.status = "Finding NPC.";
					System.out.println("Finding monster.");
				}	
		}

		Item invAmmo = Inventory.getItem(Data.arrowsToPickup);
		
		if (Inventory.getCount(true, Data.arrowsToPickup) >= 1) {
			invAmmo.getWidgetChild().click(true);
		}
		if (Settings.get(463) == 0 && Data.runEnergy >= 50) {
			Data.runButton.click(true);
		}
		
		int currentHp = Integer.parseInt(Widgets.get(748, 8).getText());
		if (currentHp < Data.totalHp*(Data.eatAt*0.01) && Data.useFood) {
			Item food = Inventory.getItem(Data.foodId);			
			if (food != null) {				
				System.out.println("Eating food.");
				Data.status = "Eating food.";
				food.getWidgetChild().click(true); 						
			}
		}		
		if (Data.useMomentum) {
			if (Methods.getAdrPercent() == 100) {				
				Methods.useMomentum();						
			}
		}
	} 
}
