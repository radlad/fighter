import gui.GUI;
import gui.Paint;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Job;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Lobby;
import org.powerbot.game.api.methods.widget.WidgetCache;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.bot.Context;
import org.powerbot.game.client.Client;

import tasks.Antiban;
import tasks.LootItems;
import tasks.NoFoodLogOut;
import tasks.Potions;
import tasks.WalkBack;

import combat.Combat;
import data.Data;

@Manifest(authors = { "nootz" }, name = "MondoFighter", description = "AIO fighter, looter and eater.", version = 1.0 )

public class MondoFighter extends ActiveScript implements PaintListener, MouseListener, MessageListener {
	
	@Override
	public void onRepaint(Graphics g1) {		
			Paint.doPaint(g1);		
	}

	
    private final List<Node> jobsCollection = Collections.synchronizedList(new ArrayList<Node>());
    private Tree jobContainer = null;

    public synchronized final void provide(final Node... jobs) {
            for (final Node job : jobs) {
                    if(!jobsCollection.contains(job)) {
                            jobsCollection.add(job);
                    }
            }
            jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
    }

    public synchronized final void revoke(final Node... jobs) {
            for (final Node job : jobs) {
                    if(jobsCollection.contains(job)) {
                            jobsCollection.remove(job);
                    }
            }
            jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
    }

    public final void submit(final Job... jobs) {
            for (final Job job : jobs) {
                    getContainer().submit(job);
            }
    }
	
    @Override
    public void onStart() {    	
    		GUI gui = new GUI();
			gui.setVisible(true);
			GUI.guiWait = true;
			while(GUI.guiWait) {
				Task.sleep(100);
			}			
			provide(new WalkBack(), new NoFoodLogOut(), new Potions(), new LootItems(), new Combat(), new Antiban()); 
			
            Data.startXP = Skills.getExperience(Data.chosenSkill);
            Data.startTime = System.currentTimeMillis();
            Data.startLevel = Skills.getRealLevel(Data.chosenSkill);     
            Data.startConstLevel = Skills.getRealLevel(Skills.CONSTITUTION);  
            Data.LOG_OUT = false;
            Data.START_LOCATION = Players.getLocal().getLocation(); 
            Data.oldKills = Data.killCounter;
            System.out.println("Starting MondoFighter.");
            Data.totalHp = Integer.parseInt(Widgets.get(748, 8).getText());
            System.out.println("Total HP: " + Data.totalHp);
            System.out.println("Location: (toString) " + Data.START_LOCATION.toString());
            System.out.println("Eat at: " + Data.eatAt);
            System.out.println("HP to eat at: " + Data.totalHp*(Data.eatAt*0.01));            
            System.out.println("Fighter Radius: " + Data.fighterRadius);
            System.out.println("Level: " + Skills.getRealLevel(Skills.ATTACK));
            System.out.println("Food ID: " + Data.foodId);
            System.out.println("Arrow to equip: " + Data.arrowsToPickup);
            System.out.println("skillColourR: " + Data.skillColourR);
            System.out.println("skillColourG: " + Data.skillColourG);
            System.out.println("skillColourB: " + Data.skillColourB);
    }
    
    @Override
    public void onStop() {
            System.out.println("Stopping MondoFighter.");
            System.out.println("Ran for: " + Data.runTime.toElapsedString());
            System.out.println("Gained " + (Skills.getExperience(Data.chosenSkill) - Data.startXP) + " experience.");
            System.out.println("Gained " + (Skills.getRealLevel(Data.chosenSkill) - Data.startLevel) + " levels in " + Data.skillString + ".");
    }

	private Client client = Context.client();
	
	@Override
	public int loop() {
		if (Data.paused) {			
			Task.sleep(100);
		} else if (jobContainer != null) {
		            final Node job = jobContainer.state();
		            if (job != null) {
		                    jobContainer.set(job);
		                    getContainer().submit(job);
		                    job.join();
		            }
		}
		if (Players.getLocal().getInteracting() != null && Players.getLocal().getInteracting().getHpPercent() <= 0) {
			if (Data.killCounter - Data.oldKills < 1) {
			Data.killCounter+=1;
			}
		}
		if (Game.getClientState() != Game.INDEX_MAP_LOADED) {
		        return 1000;
		}
		
		if (client != Context.client()) {
		        WidgetCache.purge();
		        Context.get().getEventManager().addListener(this);
		        client = Context.client();
		}		
		for (int i = 0; i < Data.NPC_selected_model.getSize(); i++) {
			Data.selectedMonsters.add(Data.NPC_selected_model.get(i).toString());
			}		
		for (int i = 0; i < Data.LOOT_selected_list_model.getSize(); i++) {
			Data.selectedItems.add(Data.LOOT_selected_list_model.get(i).toString());
			}
		// exits accidental skill guide opening
		if (Widgets.get(1218, 73).isOnScreen()) {
			Widgets.get(1218, 73).click(true);
		}
		if (Settings.get(463) == 0 && Data.runEnergy >= 50) {
			Data.runButton.click(true);
		}
		if (Lobby.isOpen()) {
			stop();
		}

		    return Random.nextInt(50, 100);
	}

	@Override
	public void mouseClicked(MouseEvent p) {		
		/*final Rectangle togglePaint = new Rectangle(4, 373, 56, 17);
		final Rectangle toggleNPC = new Rectangle(62, 373, 56, 17);
		final Rectangle toggleMouse = new Rectangle(120, 373, 66, 17);
		final Rectangle toggleGUI = new Rectangle(188, 373, 56, 17);
		final Rectangle pauseBot = new Rectangle(262, 373, 56, 17);*/
		final Rectangle togglePaint = new Rectangle(8, 334, 69, 14);
		final Rectangle toggleNPC = new Rectangle(82, 334, 69, 14);		
		final Rectangle toggleGUI = new Rectangle(156, 334, 69, 14);
		final Rectangle pauseBot = new Rectangle(230, 334, 69, 14);
		if(togglePaint.contains(p.getPoint())) {
			Paint.showPaint = !Paint.showPaint;
        }		
		if(toggleNPC.contains(p.getPoint())) {
			Paint.showNPC = !Paint.showNPC;
        }
		if (toggleGUI.contains(p.getPoint())) {
			GUI gui = new GUI();
			GUI.guiWait = true;
			gui.setVisible(true);
		}
		if (pauseBot.contains(p.getPoint())) {
			Data.paused = !Data.paused;
			Data.status = Data.paused ? "Paused." : "Waiting...";
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {	
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {		
		
	}

	@Override
	public void messageReceived(MessageEvent e) {
		String ammomsg = e.getMessage();
		if (ammomsg.contains("You have no ammo equipped.") && e.getSender() == "") {
			System.out.println("No ammo equipped, logging out.");
			Data.LOG_OUT = true;
			if (!Players.getLocal().isInCombat()) {				
				Game.logout(true);
				stop();	
			}					
		}
	}
}
