package tasks;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.node.GroundItem;

import data.Data;

public class LootItems extends Node {	
	public static Filter<GroundItem> ItemFilter = new Filter<GroundItem>(){
		@Override
		public boolean accept(GroundItem n) {
			for(String i : Data.selectedItems) {
				return n != null && n.getLocation().canReach() && 
						Data.selectedItems.contains(n.getGroundItem().getName()) &&
						Calculations.distanceTo(Data.START_LOCATION.getLocation()) <= Data.lootRadius;				
			}
			return false;
		}		
	};	

	@Override
	public boolean activate() {		
		GroundItem item = GroundItems.getNearest(ItemFilter);
		GroundItem rareitem = GroundItems.getNearest(Data.RARE_ITEM_LIST);
		return (item != null || rareitem != null) && Data.lootSetting == true && !Data.LOG_OUT && 
				!Players.getLocal().isMoving() && !Inventory.isFull() && Players.getLocal().getInteracting() == null;		
	}

	@Override
	public void execute() {		
		int currentMouseX = Mouse.getX();
        int currentMouseY = Mouse.getY();
        int currentPitch = Camera.getPitch();
        int currentAngle = Camera.getYaw();
		GroundItem item = GroundItems.getNearest(ItemFilter);
		GroundItem rareitem = GroundItems.getNearest(Data.RARE_ITEM_LIST);
		
		if (!item.isOnScreen()) {
			System.out.println("Turning to item.");
			Camera.setPitch(Random.nextInt(10, 25));
			Camera.turnTo(item);
		} else if (!Inventory.isFull()){		
			System.out.println("Picking up " + item.getGroundItem().getName() + ".");
			Data.status = "Picking up " + item.getGroundItem().getName() + ".";
				if(item.click(false) && Menu.isOpen() && Menu.contains("Take")) {						
					Menu.select("Take", item.getGroundItem().getName());				
				}
			Mouse.move(currentMouseX + Random.nextInt(-20, 20), currentMouseY + Random.nextInt(-20, 20));
			Camera.setPitch(currentPitch + Random.nextInt(20, 70));
	        Camera.setAngle(currentAngle + Random.nextInt(-200, 200));
		}

		
		if (Data.lootRareSetting == true && rareitem != null) {
			if (!rareitem.isOnScreen()) {
				System.out.println("Turning to item.");
				Camera.setPitch(Random.nextInt(10, 25));
				Camera.turnTo(rareitem);
			} else if (!Inventory.isFull()){
				System.out.println("Picking up " + rareitem.getGroundItem().getName() + ".");
				Data.status = "Picking up " + rareitem.getGroundItem().getName() + ".";
				item.interact("Take", rareitem.getGroundItem().getName());
				Mouse.move(currentMouseX + Random.nextInt(-20, 20), currentMouseY + Random.nextInt(-20, 20));
				Camera.setPitch(currentPitch + Random.nextInt(-70, 70));
	            Camera.setAngle(currentAngle + Random.nextInt(-200, 200));
			}
		}
	}	
}
