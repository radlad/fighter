package tasks;

import methods.Methods;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;

import data.Data;

public class Alchemy extends Node {

	@Override
	public boolean activate() {		
		return Methods.hasAlchemyItem() && Data.useAlchemy;
	}

	@Override
	public void execute() {		
		while (Methods.hasAlchemyItem()) {
			for (Item i : Inventory.getItems()) {
	           if (Data.alchemy_selected_item.contains(i.getName())) {
	            	Data.status = "Alchemizing...";
	                Methods.useActionBarButton6();
	                i.getWidgetChild().interact("Cast");
	           }
			}   
		}
        Data.eocMinimise.click(true);		
	}
}
