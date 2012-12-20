package data;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class Data {
	
	public static ArrayList<String> selectedMonsters = new ArrayList<String>();
	public static ArrayList<String> selectedItems = new ArrayList<String>();
	public static ArrayList<String> selectedAlchemyItems = new ArrayList<String>();
	public static ArrayList<Integer> selectedItemsInt = new ArrayList<Integer>();
	public static ArrayList<Integer> selectedMonstersInt = new ArrayList<Integer>();
	
	public static DefaultListModel NPC_unselected_list_model = new DefaultListModel();
	public static DefaultListModel LOOT_selected_list_model = new DefaultListModel();
	public static DefaultListModel NPC_selected_model = new DefaultListModel();
	public static DefaultListModel LOOT_selected_item = new DefaultListModel();	
	public static DefaultListModel alchemy_selected_item = new DefaultListModel();	
	public static DefaultListModel alchemy_selected_list_model = new DefaultListModel();
	
	public static int foodId;
	public static int RARE_ITEM_LIST[] = {1617,1631,1615,987,985,1247,1249,1216,1201,2366,1149,892,9342,2362,452,258,2999,3001,
		270,6686,5315,5316,5289,5300,5304,1516,1392,574,570,20667,7937,561,560,565,1441,1443,372,533,446,454,450};
	public static int Bones[] = { 526, 532, 534, 536 };
	public static int BtpTab = 8015;
	public static int[] TeleTab = { 8007, 8008, 8009, 8010, 8011, 8013 };
	public static int arrowsToPickup;
	
	public static boolean paused;
	
	public static int totalHp;
	public static Tile START_LOCATION;
	public static Tile START_POS_X;
	public static Tile START_POS_Y;
	public static Tile START_NEG_X;
	public static Tile START_NEG_Y;
	public static String fighterRadiusStr;
	public static String lootRadiusStr;
	public static int fighterRadius;
	public static int lootRadius;
	public static int chosenSkill;
	public static String skillString;
	public static boolean lootSetting;
	public static boolean lootRareSetting;
	public static long startTime;
	public static int startXP;
	public static int startConstXP;
	public static int startLevel;
	public static int startConstLevel;
	public static int startDefLevel;
	public static String status = "Waiting...";
	public static int oldKills;
	public static int killCounter = 0;
	public static int killsPerHour;
	
	public static Timer runTime = new Timer(0);	
	
	public static int antibanValue1;
	public static int antibanValue2;
	public static boolean moveMouse;
	public static boolean rotateScreen;
	public static boolean turnToNpc;
	public static boolean randomTab;
	public static boolean checkSkill;
	public static boolean hoverPlayer;
	public static int eatAt;
	public static boolean LOG_OUT;
	public static boolean outOfFood;
	public static boolean buryBones;
	public static boolean bonesToPeaches;
	public static boolean useMomentum;
	public static boolean useTeletab;
	public static boolean usedTeletab = false;
	public static boolean useAlchemy;
	public static boolean useFood;
	public static boolean usePotions;
	public static boolean useActionSlot23;
	public static boolean useActionSlot3;
	public static boolean useActionSlot4;
	public static boolean useActionSlot5;
	public static boolean chatWarning = false;
	public static boolean useChatWarning;
	public static Timer chatTimer = new Timer(0);
	public static int potionSkill2;
	public static int[] strPotion = {161, 159, 157, 2440, 119, 117, 115, 113};
	public static int[] atkPotion = {149, 147, 145, 2436, 125, 123, 121, 2428};
	public static int[] defPotion = {167, 165, 163, 2442, 137, 135, 133, 2432};
	public static int[] prayPotion = {143, 141, 139, 2434};
	public static int[] afPotion = {2458, 2456, 2454, 2452};
	
	public static WidgetChild eocMaximise = Widgets.get(640, 3);
	public static WidgetChild eocMinimise = Widgets.get(640, 30);
	public static WidgetChild eocButton1 = Widgets.get(640, 36);
	public static WidgetChild eocButton2 = Widgets.get(640, 37);
	public static WidgetChild eocButton3 = Widgets.get(640, 40);
	public static WidgetChild eocButton4 = Widgets.get(640, 43);
	public static WidgetChild eocButton5 = Widgets.get(640, 46);
	public static WidgetChild eocButton6 = Widgets.get(640, 49);
	public static WidgetChild runButton = Widgets.get(750, 0);		
	public static int runEnergy = Integer.parseInt(Widgets.get(750, 6).getText());
	public static int prayerPoints = Integer.parseInt(Widgets.get(749, 6).getText());
}
