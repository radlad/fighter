package tasks;

import methods.Methods;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;

import data.Data;

public class Potions extends Node {

	@Override
	public boolean activate() {
		return Data.usePotions && (Data.useActionSlot23 || Data.useActionSlot4 || Data.useActionSlot5);
	}

	@Override
	public void execute() {
		
		if (Data.useActionSlot3 && (Methods.hasPotion(Data.strPotion) || Methods.hasPotion(Data.atkPotion))) {
			if ((Skills.getLevel(Skills.ATTACK) - Skills.getRealLevel(Skills.ATTACK)) <= 1 && Methods.hasPotion(Data.atkPotion)) {
				Data.status = "Drinking Attack Potion.";
				System.out.println("Drinking Attack Potion.");
				Methods.useActionBarButton2();
			}
			if ((Skills.getLevel(Skills.STRENGTH) - Skills.getRealLevel(Skills.STRENGTH)) <= 1 && Methods.hasPotion(Data.strPotion)) {
				Data.status = "Drinking Strength Potion.";
				System.out.println("Drinking Strength Potion.");
				Methods.useActionBarButton3();
			}
		} else {		
			if (Data.useActionSlot23 && Methods.hasPotion(Data.strPotion) && !Data.useActionSlot3 && Data.potionSkill2 == 2) {
				if ((Skills.getLevel(Skills.STRENGTH) - Skills.getRealLevel(Skills.STRENGTH)) <= 1) {
					Data.status = "Drinking Strength Potion.";
					System.out.println("Drinking Strength Potion.");
					Methods.useActionBarButton2();
				}
			}			
			if (Data.useActionSlot23 && Methods.hasPotion(Data.atkPotion) && !Data.useActionSlot3 && Data.potionSkill2 == 0) {
				if ((Skills.getLevel(Skills.ATTACK) - Skills.getRealLevel(Skills.ATTACK)) <= 1) {
					Data.status = "Drinking Attack Potion.";
					System.out.println("Drinking Attack Potion.");
					Methods.useActionBarButton2();
				}
			}			
			if (Data.useActionSlot23 && Methods.hasPotion(Data.defPotion) && !Data.useActionSlot3 && Data.potionSkill2 == 1) {
				if ((Skills.getLevel(Skills.DEFENSE) - Skills.getRealLevel(Skills.DEFENSE)) <= 1) {
					Data.status = "Drinking Defence Potion.";
					System.out.println("Drinking Defence Potion.");
					Methods.useActionBarButton2();
				}
			}
		}
		
		if (Data.useActionSlot4 && Methods.hasPotion(Data.prayPotion)) {
			if (Data.prayerPoints <= 100) {
				Data.status = "Drinking Prayer Potion.";
				System.out.println("Drinking Prayer Potion.");
				Methods.useActionBarButton4();
			}
		}
		
	}

}
