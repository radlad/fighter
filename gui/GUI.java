package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import methods.Methods;

import org.powerbot.game.api.methods.interactive.NPCs;

import data.Data;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static GUI gui;
	public static boolean guiWait = true;

	public static boolean guiExit;
	
	public GUI() {
		initComponents();
	}
	
	private void startButtonActionPerformed(ActionEvent e) {
		
		String foodChosen = food_type.getSelectedItem().toString();
		String skillChosen = skillChoose.getSelectedItem().toString();
		
			if(foodChosen.equals("Salmon")){
				Data.foodId = 329;
			}else if(foodChosen.equals("Tuna")){
				Data.foodId = 361;
			}else if(foodChosen.equals("Lobster")){
				Data.foodId = 379;
			}else if(foodChosen.equals("Swordfish")){
				Data.foodId = 373;
			}else if(foodChosen.equals("Monkfish")){
				Data.foodId = 7946;
			}else if(foodChosen.equals("Shark")){
				Data.foodId = 385;
			}else if(foodChosen.equals("Rocktail")){
				Data.foodId = 15272;
			}
			
			if(skillChosen.equals("Attack")){
				Data.chosenSkill = 0;
				Data.skillString = "Attack";
				Paint.color_skill = new Color(192, 46, 46, 170);
			}else if(skillChosen.equals("Strength")){
				Data.chosenSkill = 2;
				Data.skillString = "Strength";
				Paint.color_skill = new Color(46, 134, 55, 170);
			}else if(skillChosen.equals("Defence")){
				Data.chosenSkill = 1;
				Data.skillString = "Defence";
				Paint.color_skill = new Color(89, 144, 255, 170);
			}else if(skillChosen.equals("Range")){
				Data.chosenSkill = 4;
				Data.skillString = "Range";
				Paint.color_skill = new Color(168, 108, 39, 170);
			}else if(skillChosen.equals("Magic")){
				Data.chosenSkill = 6;
				Data.skillString = "Magic";
				Paint.color_skill = new Color(35, 158, 210, 170);
			}
			
			Data.fighterRadiusStr = fight_radius_field.getText();
			Data.lootRadiusStr = loot_radius_field.getText();
			Data.fighterRadius = Integer.parseInt(Data.fighterRadiusStr);
			Data.lootRadius = Integer.parseInt(Data.lootRadiusStr);
			

			Data.lootSetting = enable_loot_chkbox.isSelected() ? true : false;
			Data.lootRareSetting = LOOT_rdt_chkbox.isSelected() ? true : false;
			Data.moveMouse = ANTIBAN_chkbox_movemouse.isSelected() ? true : false;
			Data.rotateScreen = ANTIBAN_chkbox_rotate.isSelected() ? true : false;
			Data.turnToNpc = ANTIBAN_chkbox_turn.isSelected() ? true : false;
			Data.randomTab = ANTIBAN_chkbox_randtab.isSelected() ? true : false;
			Data.checkSkill = ANTIBAN_chkbox_checkskill.isSelected() ? true : false;
			Data.hoverPlayer = ANTIBAN_chkbox_hoverplayer.isSelected() ? true : false;
			Data.eatAt = eat_at_slider.getValue();		
			Data.useMomentum = MISC_momentum_chkbox.isSelected() ? true : false;
			Data.useAlchemy = alchemy_enable.isSelected() ? true : false;
			Data.useFood = food_use_chkbox.isSelected() ? true : false;
			Data.usePotions = use_potions_chkbox.isSelected() ? true : false;
			
			if (potions_actionbar_combo_slot23.getSelectedItem().toString() != "None") {
				Data.useActionSlot23 = true;
				if (potions_actionbar_combo_slot23.getSelectedItem().toString() == "(Super) Attack + Strength") {
					Data.useActionSlot3 = true;
				} else {
					if (potions_actionbar_combo_slot23.getSelectedItem().toString() == "(Super) Strength Potion") {
						Data.potionSkill2 = 2;
						Data.useActionSlot23 = true;
					} else if (potions_actionbar_combo_slot23.getSelectedItem().toString() == "(Super) Attack Potion") {
						Data.potionSkill2 = 0;
						Data.useActionSlot23 = true;
					} else if (potions_actionbar_combo_slot23.getSelectedItem().toString() == "(Super) Defence Potion") {
						Data.potionSkill2 = 1;
						Data.useActionSlot23 = true;
					}
				}
			}
			if (potions_actionbar_combo_slot4.getSelectedItem().toString() != "None") {
				Data.useActionSlot4 = true;
			}
			if (potions_actionbar_combo_slot5.getSelectedItem().toString() != "None") {
				Data.useActionSlot5 = true;
			}

			
			Data.arrowsToPickup = Integer.parseInt(((misc_arrow_box.getSelectedItem().toString()).replaceAll("(.*?): *", "")));
		
		if ( antiban_freq_slider.getValue() == 0) {
			Data.antibanValue1 = 20*1000;
			Data.antibanValue2 = 60*1000;							
		} else if ( antiban_freq_slider.getValue() == 20) {
			Data.antibanValue1 = 15*1000;
			Data.antibanValue2 = 45*1000;	
		} else if ( antiban_freq_slider.getValue() == 40) {
			Data.antibanValue1 = 10*1000;
			Data.antibanValue2 = 30*1000;	
		} else if ( antiban_freq_slider.getValue() == 50) {
			Data.antibanValue1 = 7*1000;
			Data.antibanValue2 = 20*1000;	
		} else if ( antiban_freq_slider.getValue() == 60) {
			Data.antibanValue1 = 7*1000;
			Data.antibanValue2 = 20*1000;	
		} else if ( antiban_freq_slider.getValue() == 80) {
			Data.antibanValue1 = 5*1000;
			Data.antibanValue2 = 10*1000;	
		} else if ( antiban_freq_slider.getValue() == 100) {
			Data.antibanValue1 = 2*1000;
			Data.antibanValue2 = 5*1000;	
		}
		
		guiWait = false;
		dispose();
		
	}
	
	private void exitButtonActionPerformed(ActionEvent e) {
		guiWait = false;
		guiExit = true;
		dispose();		
	}
	
	private void NPC_refresh_btnActionPerformed(ActionEvent e) {
		Data.NPC_unselected_list_model.clear();
		for (int i = 0; i < NPCs.getLoaded().length; i++) {
			Data.NPC_unselected_list_model.addElement(NPCs.getLoaded()[i].getName() + " - Level " + NPCs.getLoaded()[i].getLevel() + " ID: " + NPCs.getLoaded()[i].getId());
			}
		NPC_unselected_list.setModel(Data.NPC_unselected_list_model);
		NPC_selected_list.setModel(Data.NPC_selected_model);
	}
	
	
		
	
	private void NPC_add_btnActionPerformed(ActionEvent e) {
		Data.selectedMonstersInt.add(Integer.parseInt(((NPC_unselected_list.getSelectedValue().toString()).replaceAll("(.*?): *", ""))));
		Data.NPC_selected_model.addElement(NPC_unselected_list.getSelectedValue().toString());
		NPC_selected_list.setModel(Data.NPC_selected_model);
	}
	
	private void NPC_clear_btnActionPerformed(ActionEvent e) {
		NPC_selected_list.removeAll();
		Data.selectedMonstersInt.clear();
		Data.NPC_selected_model.clear();
		NPC_selected_list.setModel(Data.NPC_selected_model);
	}
	
	private void npc_save_btnActionPerformed(ActionEvent e) {
		//TODO		
	}
	private void npc_load_btnActionPerformed(ActionEvent e) {
		//TODO		
	}
	
	private void LOOT_add_btnActionPerformed(ActionEvent e) {
		if ((loot_name_field.getText() != "") && (loot_name_field.getText() != null)) {			
			Data.selectedItems.add(loot_name_field.getText());
			Data.LOOT_selected_item.addElement(loot_name_field.getText());			
		}
		LOOT_selected_list.setModel(Data.LOOT_selected_item);
	}
	
	private void LOOT_refresh_btnActionPerformed(ActionEvent e) {			
		LOOT_selected_list.setModel(Data.LOOT_selected_item);
	}
	
	private void LOOT_clear_btnActionPerformed(ActionEvent e) {		
		LOOT_selected_list.removeAll();
		Data.selectedItemsInt.clear();
		Data.LOOT_selected_item.clear();
		LOOT_selected_list.setModel(Data.LOOT_selected_item);
	}
	
	private void alchemy_addActionPerformed(ActionEvent e) {
		if ((alchemy_txt_field.getText() != "") && (alchemy_txt_field.getText() != null)) {			
			Data.selectedAlchemyItems.add(alchemy_txt_field.getText());
			Data.alchemy_selected_item.addElement(alchemy_txt_field.getText());			
		}
		alchemy_list.setModel(Data.alchemy_selected_item);
	}
	
	private void alchemy_refreshActionPerformed(ActionEvent e) {			
		alchemy_list.setModel(Data.alchemy_selected_item);
	}
	
	private void alchemy_clearActionPerformed(ActionEvent e) {		
		alchemy_list.removeAll();
		Data.selectedAlchemyItems.clear();
		Data.alchemy_selected_item.clear();
		alchemy_list.setModel(Data.alchemy_selected_item);
	}
	
	private void initComponents() {
		tabbed_pane = new JTabbedPane();
		panel1 = new JPanel();
		radius_reminder1 = new JLabel();
		radius_reminder2 = new JLabel();
		panel7 = new JPanel();
		ANTIBAN_chkbox_movemouse = new JCheckBox();
		ANTIBAN_chkbox_rotate = new JCheckBox();
		ANTIBAN_chkbox_checkskill = new JCheckBox();
		ANTIBAN_chkbox_randtab = new JCheckBox();
		ANTIBAN_chkbox_hoverplayer = new JCheckBox();
		ANTIBAN_chkbox_turn = new JCheckBox();
		antiban_freq_slider = new JSlider();
		panel14 = new JPanel();
		fight_radius = new JLabel();
		fight_radius_field = new JTextField();
		loot_radius = new JLabel();
		loot_radius_field = new JTextField();
		skill_to_track = new JLabel();
		skillChoose = new JComboBox<>();
		panel15 = new JPanel();
		panel2 = new JPanel();
		scrollPane1 = new JScrollPane();
		NPC_unselected_list = new JList();
		scrollPane2 = new JScrollPane();
		NPC_selected_list = new JList();
		NPC_refresh_btn = new JButton();
		NPC_add_btn = new JButton();
		NPC_clear_btn = new JButton();
		label19 = new JLabel();
		panel8 = new JPanel();
		panel9 = new JPanel();
		panel10 = new JPanel();
		npc_save_btn = new JButton();
		npc_load_btn = new JButton();
		npc_preset_combobox = new JComboBox();
		panel3 = new JPanel();
		enable_loot_chkbox = new JCheckBox();
		scrollPane3 = new JScrollPane();
		LOOT_selected_list = new JList();
		loot_name_field = new JTextField();
		LOOT_add_btn = new JButton();
		LOOT_refresh_btn = new JButton();
		LOOT_clear_btn = new JButton();
		LOOT_rdt_chkbox = new JCheckBox();
		loot_rdt_label = new JLabel();
		loot_help4 = new JLabel();
		panel11 = new JPanel();
		panel16 = new JPanel();
		panel5 = new JPanel();
		panel22 = new JPanel();
		panel23 = new JPanel();
		alchemy_txt_field = new JTextField();
		panel25 = new JPanel();
		scrollPane4 = new JScrollPane();
		alchemy_list = new JList();
		alchemy_refresh = new JButton();
		alchemy_clear = new JButton();
		alchemy_help1 = new JLabel();
		alchemy_add = new JButton();
		alchemy_enable = new JCheckBox();
		panel24 = new JPanel();
		panel4 = new JPanel();
		food_type = new JComboBox<>();
		eat_at_slider = new JSlider();
		food_use_chkbox = new JCheckBox();
		panel12 = new JPanel();
		panel13 = new JPanel();
		panel19 = new JPanel();
		panel20 = new JPanel();
		potions_actionbar_combo_slot23 = new JComboBox<>();
		potions_actionbar_slot23 = new JLabel();
		potions_actionbar_combo_slot4 = new JComboBox<>();
		potions_actionbar_slot4 = new JLabel();
		potions_actionbar_combo_slot5 = new JComboBox<>();
		potions_actionbar_slot5 = new JLabel();
		use_potions_chkbox = new JCheckBox();
		panel21 = new JPanel();
		panel6 = new JPanel();
		panel17 = new JPanel();
		misc_arrow_help = new JLabel();
		misc_arrow_box = new JComboBox<>();
		MISC_momentum_chkbox = new JCheckBox();
		misc_help_label1 = new JLabel();
		panel18 = new JPanel();
		startButton = new JButton();
		exitButton = new JButton();
		fill_reminder = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		separator1 = new JSeparator();
		
		NPC_selected_list.setModel(new javax.swing.AbstractListModel() {
			String[] strings = {};
			public int getSize() {
				 return strings.length;
			}
			public Object getElementAt(int i) {
				 return strings[i];
			}
			});
		
		LOOT_selected_list.setModel(new javax.swing.AbstractListModel() {
			String[] strings = {};
			public int getSize() {
				 return strings.length;
			}
			public Object getElementAt(int i) {
				 return strings[i];
			}
			});
		
		alchemy_list.setModel(new javax.swing.AbstractListModel() {
			String[] strings = {};
			public int getSize() {
				 return strings.length;
			}
			public Object getElementAt(int i) {
				 return strings[i];
			}
			});

		//======== this ========
		setTitle("MondoFighter v1.0 Settings");
		setBackground(new Color(153, 0, 0));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== tabbed_pane ========
		{
			tabbed_pane.setTabPlacement(SwingConstants.LEFT);

			//======== panel1 ========
			{
				panel1.setLayout(null);

				//---- radius_reminder1 ----
				radius_reminder1.setText("Loot radius should be lower than fight radius.");
				radius_reminder1.setFont(new Font("Tahoma", Font.PLAIN, 10));
				radius_reminder1.setHorizontalAlignment(SwingConstants.LEFT);
				panel1.add(radius_reminder1);
				radius_reminder1.setBounds(235, 65, 205, 15);

				//---- radius_reminder2 ----
				radius_reminder2.setText("Recommended to be less than 10.");
				radius_reminder2.setFont(radius_reminder2.getFont().deriveFont(radius_reminder2.getFont().getSize() - 1f));
				panel1.add(radius_reminder2);
				radius_reminder2.setBounds(new Rectangle(new Point(265, 80), radius_reminder2.getPreferredSize()));

				//======== panel7 ========
				{
					panel7.setBorder(new TitledBorder("Antiban"));
					panel7.setLayout(null);

					//---- ANTIBAN_chkbox_movemouse ----
					ANTIBAN_chkbox_movemouse.setText("Move mouse");
					ANTIBAN_chkbox_movemouse.setSelected(true);
					panel7.add(ANTIBAN_chkbox_movemouse);
					ANTIBAN_chkbox_movemouse.setBounds(new Rectangle(new Point(10, 20), ANTIBAN_chkbox_movemouse.getPreferredSize()));

					//---- ANTIBAN_chkbox_rotate ----
					ANTIBAN_chkbox_rotate.setText("Rotate screen");
					ANTIBAN_chkbox_rotate.setSelected(true);
					panel7.add(ANTIBAN_chkbox_rotate);
					ANTIBAN_chkbox_rotate.setBounds(new Rectangle(new Point(10, 40), ANTIBAN_chkbox_rotate.getPreferredSize()));

					//---- ANTIBAN_chkbox_checkskill ----
					ANTIBAN_chkbox_checkskill.setText("Check skill");
					ANTIBAN_chkbox_checkskill.setSelected(true);
					panel7.add(ANTIBAN_chkbox_checkskill);
					ANTIBAN_chkbox_checkskill.setBounds(new Rectangle(new Point(105, 40), ANTIBAN_chkbox_checkskill.getPreferredSize()));

					//---- ANTIBAN_chkbox_randtab ----
					ANTIBAN_chkbox_randtab.setText("Random tab");
					ANTIBAN_chkbox_randtab.setSelected(true);
					panel7.add(ANTIBAN_chkbox_randtab);
					ANTIBAN_chkbox_randtab.setBounds(new Rectangle(new Point(105, 20), ANTIBAN_chkbox_randtab.getPreferredSize()));

					//---- ANTIBAN_chkbox_hoverplayer ----
					ANTIBAN_chkbox_hoverplayer.setText("Hover player");
					ANTIBAN_chkbox_hoverplayer.setSelected(true);
					panel7.add(ANTIBAN_chkbox_hoverplayer);
					ANTIBAN_chkbox_hoverplayer.setBounds(new Rectangle(new Point(105, 60), ANTIBAN_chkbox_hoverplayer.getPreferredSize()));

					//---- ANTIBAN_chkbox_turn ----
					ANTIBAN_chkbox_turn.setText("Turn to NPC");
					ANTIBAN_chkbox_turn.setSelected(true);
					panel7.add(ANTIBAN_chkbox_turn);
					ANTIBAN_chkbox_turn.setBounds(new Rectangle(new Point(10, 60), ANTIBAN_chkbox_turn.getPreferredSize()));

					//---- antiban_freq_slider ----
					antiban_freq_slider.setMajorTickSpacing(20);
					antiban_freq_slider.setMinorTickSpacing(20);
					antiban_freq_slider.setSnapToTicks(true);
					antiban_freq_slider.setValue(60);
					antiban_freq_slider.setBorder(new TitledBorder("Antiban Frequency"));
					antiban_freq_slider.setFont(new Font("Verdana", Font.PLAIN, 11));
					antiban_freq_slider.setPaintTicks(true);
					panel7.add(antiban_freq_slider);
					antiban_freq_slider.setBounds(195, 25, 270, antiban_freq_slider.getPreferredSize().height);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel7.getComponentCount(); i++) {
							Rectangle bounds = panel7.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel7.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel7.setMinimumSize(preferredSize);
						panel7.setPreferredSize(preferredSize);
					}
				}
				panel1.add(panel7);
				panel7.setBounds(15, 105, 470, 95);

				//======== panel14 ========
				{
					panel14.setBorder(new TitledBorder("General Settings"));
					panel14.setLayout(null);

					//---- fight_radius ----
					fight_radius.setText("Fight Radius:");
					fight_radius.setFont(new Font("Verdana", Font.PLAIN, 12));
					panel14.add(fight_radius);
					fight_radius.setBounds(180, 30, fight_radius.getPreferredSize().width, 19);

					//---- fight_radius_field ----
					fight_radius_field.setText("10");
					fight_radius_field.setHorizontalAlignment(SwingConstants.RIGHT);
					panel14.add(fight_radius_field);
					fight_radius_field.setBounds(280, 30, 35, 20);

					//---- loot_radius ----
					loot_radius.setText("Loot Radius:");
					loot_radius.setFont(new Font("Verdana", Font.PLAIN, 12));
					panel14.add(loot_radius);
					loot_radius.setBounds(330, 30, 85, 20);

					//---- loot_radius_field ----
					loot_radius_field.setText("8");
					loot_radius_field.setHorizontalAlignment(SwingConstants.RIGHT);
					panel14.add(loot_radius_field);
					loot_radius_field.setBounds(430, 30, 35, 20);

					//---- skill_to_track ----
					skill_to_track.setText("Skill to track:");
					skill_to_track.setFont(new Font("Verdana", Font.PLAIN, 12));
					panel14.add(skill_to_track);
					skill_to_track.setBounds(20, 30, skill_to_track.getPreferredSize().width, 20);

					//---- skillChoose ----
					skillChoose.setFont(new Font("Verdana", Font.PLAIN, 12));
					skillChoose.setModel(new DefaultComboBoxModel<>(new String[] {
						"Attack",
						"Strength",
						"Defence",						
						"Range",
						"Magic"
					}));
					panel14.add(skillChoose);
					skillChoose.setBounds(20, 55, 145, 20);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel14.getComponentCount(); i++) {
							Rectangle bounds = panel14.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel14.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel14.setMinimumSize(preferredSize);
						panel14.setPreferredSize(preferredSize);
					}
				}
				panel1.add(panel14);
				panel14.setBounds(5, 5, 490, 205);

				//======== panel15 ========
				{
					panel15.setBorder(new TitledBorder("All Presets (coming soon)"));
					panel15.setLayout(null);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel15.getComponentCount(); i++) {
							Rectangle bounds = panel15.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel15.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel15.setMinimumSize(preferredSize);
						panel15.setPreferredSize(preferredSize);
					}
				}
				panel1.add(panel15);
				panel15.setBounds(10, 215, 485, 65);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < panel1.getComponentCount(); i++) {
						Rectangle bounds = panel1.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = panel1.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					panel1.setMinimumSize(preferredSize);
					panel1.setPreferredSize(preferredSize);
				}
			}
			tabbed_pane.addTab("General", panel1);


			//======== panel2 ========
			{
				panel2.setLayout(null);

				//======== scrollPane1 ========
				{
					scrollPane1.setViewportView(NPC_unselected_list);
				}
				panel2.add(scrollPane1);
				scrollPane1.setBounds(15, 20, 215, 160);

				//======== scrollPane2 ========
				{
					scrollPane2.setViewportView(NPC_selected_list);
				}
				panel2.add(scrollPane2);
				scrollPane2.setBounds(275, 20, 215, 160);

				//---- NPC_refresh_btn ----
				NPC_refresh_btn.setText("Refresh");
				panel2.add(NPC_refresh_btn);
				NPC_refresh_btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						NPC_refresh_btnActionPerformed(e);
						
					}
				});
				NPC_refresh_btn.setBounds(15, 195, 105, NPC_refresh_btn.getPreferredSize().height);

				//---- NPC_add_btn ----
				NPC_add_btn.setText("Add");
				panel2.add(NPC_add_btn);
				NPC_add_btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						NPC_add_btnActionPerformed(e);
						
					}
				});
				NPC_add_btn.setBounds(130, 195, 100, NPC_add_btn.getPreferredSize().height);

				//---- NPC_clear_btn ----
				NPC_clear_btn.setText("Clear");
				panel2.add(NPC_clear_btn);
				NPC_clear_btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						NPC_clear_btnActionPerformed(e);
						
					}
				});
				NPC_clear_btn.setBounds(275, 195, 215, NPC_clear_btn.getPreferredSize().height);

				//---- label19 ----
				label19.setText("Choose from left and select Add");
				label19.setFont(label19.getFont().deriveFont(label19.getFont().getSize() - 1f));
				label19.setHorizontalAlignment(SwingConstants.CENTER);
				panel2.add(label19);
				label19.setBounds(0, 220, 500, 20);

				//======== panel8 ========
				{
					panel8.setBorder(new TitledBorder("NPCs"));
					panel8.setLayout(null);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel8.getComponentCount(); i++) {
							Rectangle bounds = panel8.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel8.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel8.setMinimumSize(preferredSize);
						panel8.setPreferredSize(preferredSize);
					}
				}
				panel2.add(panel8);
				panel8.setBounds(5, 5, 235, 185);

				//======== panel9 ========
				{
					panel9.setBorder(new TitledBorder("Selected NPCs"));
					panel9.setLayout(null);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel9.getComponentCount(); i++) {
							Rectangle bounds = panel9.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel9.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel9.setMinimumSize(preferredSize);
						panel9.setPreferredSize(preferredSize);
					}
				}
				panel2.add(panel9);
				panel9.setBounds(265, 5, 235, 185);

				//======== panel10 ========
				{
					panel10.setBorder(new TitledBorder("Presets (coming soon)"));
					panel10.setLayout(null);
					
					//---- npc_save_btn ----
					npc_save_btn.setText("Save");
					npc_save_btn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							npc_save_btnActionPerformed(e);
							
						}
					});
					panel10.add(npc_save_btn);
					npc_save_btn.setBounds(405, 10, 70, 28);

					//---- npc_load_btn ----
					npc_load_btn.setText("Load");
					npc_load_btn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							npc_load_btnActionPerformed(e);
							
						}
					});
					panel10.add(npc_load_btn);
					npc_load_btn.setBounds(325, 10, 70, 28);

					//---- npc_preset_combobox ----
					npc_preset_combobox.setEditable(true);
					panel10.add(npc_preset_combobox);
					npc_preset_combobox.setBounds(60, 15, 250, 20);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel10.getComponentCount(); i++) {
							Rectangle bounds = panel10.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel10.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel10.setMinimumSize(preferredSize);
						panel10.setPreferredSize(preferredSize);
					}
				}
				panel2.add(panel10);
				panel10.setBounds(5, 235, 490, 45);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < panel2.getComponentCount(); i++) {
						Rectangle bounds = panel2.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = panel2.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					panel2.setMinimumSize(preferredSize);
					panel2.setPreferredSize(preferredSize);
				}
			}
			tabbed_pane.addTab("NPCs", panel2);


			//======== panel3 ========
			{
				panel3.setLayout(null);

				//---- enable_loot_chkbox ----
				enable_loot_chkbox.setText("Enable Looting");
				panel3.add(enable_loot_chkbox);
				enable_loot_chkbox.setBounds(65, 25, enable_loot_chkbox.getPreferredSize().width, 25);

				//======== scrollPane3 ========
				{
					scrollPane3.setViewportView(LOOT_selected_list);
				}
				panel3.add(scrollPane3);
				scrollPane3.setBounds(205, 55, 280, 140);

				//---- loot_name_field ----
				loot_name_field.setHorizontalAlignment(SwingConstants.CENTER);
				panel3.add(loot_name_field);
				loot_name_field.setBounds(15, 55, 185, 25);

				//---- LOOT_add_btn ----
				LOOT_add_btn.setText("Add");
				panel3.add(LOOT_add_btn);
				LOOT_add_btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						LOOT_add_btnActionPerformed(e);
						
					}
				});
				LOOT_add_btn.setBounds(70, 100, 70, LOOT_add_btn.getPreferredSize().height);

				//---- LOOT_refresh_btn ----
				LOOT_refresh_btn.setText("Refresh");
				panel3.add(LOOT_refresh_btn);
				LOOT_refresh_btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						LOOT_refresh_btnActionPerformed(e);
						
					}
				});
				LOOT_refresh_btn.setBounds(350, 25, 135, 25);

				//---- LOOT_clear_btn ----
				LOOT_clear_btn.setText("Clear");
				panel3.add(LOOT_clear_btn);
				LOOT_clear_btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						LOOT_clear_btnActionPerformed(e);
						
					}
				});
				LOOT_clear_btn.setBounds(205, 25, 135, 25);

				//---- LOOT_rdt_chkbox ----
				LOOT_rdt_chkbox.setText("Rare Drop Table");
				panel3.add(LOOT_rdt_chkbox);
				LOOT_rdt_chkbox.setBounds(55, 155, 105, 20);

				//---- loot_rdt_label ----
				loot_rdt_label.setText("Pick up all RDT items?");
				loot_rdt_label.setFont(loot_rdt_label.getFont().deriveFont(loot_rdt_label.getFont().getSize() - 1f));
				loot_rdt_label.setHorizontalAlignment(SwingConstants.CENTER);
				panel3.add(loot_rdt_label);
				loot_rdt_label.setBounds(55, 140, 105, loot_rdt_label.getPreferredSize().height);

				//---- loot_help4 ----
				loot_help4.setText("Type name of item and press Add");
				loot_help4.setFont(loot_help4.getFont().deriveFont(loot_help4.getFont().getSize() - 1f));
				loot_help4.setHorizontalAlignment(SwingConstants.CENTER);
				panel3.add(loot_help4);
				loot_help4.setBounds(15, 80, 185, loot_help4.getPreferredSize().height);

				//======== panel11 ========
				{
					panel11.setBorder(new TitledBorder("Presets (coming soon)"));
					panel11.setLayout(null);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel11.getComponentCount(); i++) {
							Rectangle bounds = panel11.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel11.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel11.setMinimumSize(preferredSize);
						panel11.setPreferredSize(preferredSize);
					}
				}
				panel3.add(panel11);
				panel11.setBounds(5, 235, 490, 45);

				//======== panel16 ========
				{
					panel16.setBorder(new TitledBorder("Loot Settings"));
					panel16.setLayout(null);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel16.getComponentCount(); i++) {
							Rectangle bounds = panel16.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel16.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel16.setMinimumSize(preferredSize);
						panel16.setPreferredSize(preferredSize);
					}
				}
				panel3.add(panel16);
				panel16.setBounds(5, 5, 490, 225);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < panel3.getComponentCount(); i++) {
						Rectangle bounds = panel3.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = panel3.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					panel3.setMinimumSize(preferredSize);
					panel3.setPreferredSize(preferredSize);
				}
			}
			tabbed_pane.addTab("Loot", panel3);
			
			//======== panel5 ========
			{
				panel5.setLayout(null);

				//======== panel22 ========
				{
					panel22.setLayout(null);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel22.getComponentCount(); i++) {
							Rectangle bounds = panel22.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel22.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel22.setMinimumSize(preferredSize);
						panel22.setPreferredSize(preferredSize);
					}
				}
				panel5.add(panel22);
				panel22.setBounds(new Rectangle(new Point(10, 15), panel22.getPreferredSize()));

				//======== panel23 ========
				{
					panel23.setBorder(new TitledBorder("Alchemy Settings"));
					panel23.setLayout(null);

					//---- alchemy_txt_field ----
					alchemy_txt_field.setHorizontalAlignment(SwingConstants.CENTER);
					panel23.add(alchemy_txt_field);
					alchemy_txt_field.setBounds(35, 70, 165, 30);

					//======== panel25 ========
					{
						panel25.setBorder(new TitledBorder("Items to Alchemise"));
						panel25.setLayout(null);

						//======== scrollPane4 ========
						{
							scrollPane4.setViewportView(alchemy_list);
						}
						panel25.add(scrollPane4);
						scrollPane4.setBounds(10, 20, 220, 130);

						//---- alchemy_refresh ----
						alchemy_refresh.setText("Refresh");
						alchemy_refresh.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								alchemy_refreshActionPerformed(e);
								
							}
						});
						panel25.add(alchemy_refresh);
						alchemy_refresh.setBounds(10, 160, 105, alchemy_refresh.getPreferredSize().height);

						//---- alchemy_clear ----
						alchemy_clear.setText("Clear");
						alchemy_clear.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								alchemy_clearActionPerformed(e);
								
							}
						});
						panel25.add(alchemy_clear);
						alchemy_clear.setBounds(125, 160, 102, alchemy_clear.getPreferredSize().height);

						{ // compute preferred size
							Dimension preferredSize = new Dimension();
							for(int i = 0; i < panel25.getComponentCount(); i++) {
								Rectangle bounds = panel25.getComponent(i).getBounds();
								preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
								preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
							}
							Insets insets = panel25.getInsets();
							preferredSize.width += insets.right;
							preferredSize.height += insets.bottom;
							panel25.setMinimumSize(preferredSize);
							panel25.setPreferredSize(preferredSize);
						}
					}
					panel23.add(panel25);
					panel25.setBounds(230, 15, 240, 195);

					//---- alchemy_help1 ----
					alchemy_help1.setText("Type name of item and press Add");
					alchemy_help1.setHorizontalAlignment(SwingConstants.CENTER);
					alchemy_help1.setFont(alchemy_help1.getFont().deriveFont(alchemy_help1.getFont().getSize() - 1f));
					panel23.add(alchemy_help1);
					alchemy_help1.setBounds(new Rectangle(new Point(40, 110), alchemy_help1.getPreferredSize()));

					//---- alchemy_add ----
					alchemy_add.setText("Add");
					alchemy_add.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							alchemy_addActionPerformed(e);
							
						}
					});
					panel23.add(alchemy_add);
					alchemy_add.setBounds(35, 130, 165, alchemy_add.getPreferredSize().height);

					//---- alchemy_enable ----
					alchemy_enable.setText("Enable High Alchemy?");
					panel23.add(alchemy_enable);
					alchemy_enable.setBounds(new Rectangle(new Point(55, 35), alchemy_enable.getPreferredSize()));

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel23.getComponentCount(); i++) {
							Rectangle bounds = panel23.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel23.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel23.setMinimumSize(preferredSize);
						panel23.setPreferredSize(preferredSize);
					}
				}
				panel5.add(panel23);
				panel23.setBounds(5, 5, 480, 225);

				//======== panel24 ========
				{
					panel24.setBorder(new TitledBorder("Presets (coming soon)"));
					panel24.setLayout(null);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel24.getComponentCount(); i++) {
							Rectangle bounds = panel24.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel24.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel24.setMinimumSize(preferredSize);
						panel24.setPreferredSize(preferredSize);
					}
				}
				panel5.add(panel24);
				panel24.setBounds(5, 235, 480, 45);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < panel5.getComponentCount(); i++) {
						Rectangle bounds = panel5.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = panel5.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					panel5.setMinimumSize(preferredSize);
					panel5.setPreferredSize(preferredSize);
				}
			}
			tabbed_pane.addTab("Alchemy", panel5);


			//======== panel4 ========
			{
				panel4.setLayout(null);

				//---- food_type ----
				food_type.setFont(new Font("Verdana", Font.PLAIN, 12));
				food_type.setModel(new DefaultComboBoxModel<>(new String[] {
					"Salmon",
					"Tuna",
					"Lobster",
					"Swordfish",
					"Monkfish",
					"Shark",
					"Rocktail"
				}));
				panel4.add(food_type);
				food_type.setBounds(215, 30, 195, 25);

				//---- eat_at_slider ----
				eat_at_slider.setBorder(new TitledBorder("Health percentage to eat at"));
				eat_at_slider.setValue(60);
				eat_at_slider.setPaintLabels(true);
				eat_at_slider.setPaintTicks(true);
				eat_at_slider.setMinorTickSpacing(5);
				eat_at_slider.setMajorTickSpacing(10);
				panel4.add(eat_at_slider);
				eat_at_slider.setBounds(15, 65, 470, eat_at_slider.getPreferredSize().height);

				//---- food_use_chkbox ----
				food_use_chkbox.setText("Use food?");
				food_use_chkbox.setSelected(true);
				panel4.add(food_use_chkbox);
				food_use_chkbox.setBounds(105, 30, food_use_chkbox.getPreferredSize().width, 25);

				//======== panel12 ========
				{
					panel12.setBorder(new TitledBorder("Food Settings"));
					panel12.setLayout(null);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel12.getComponentCount(); i++) {
							Rectangle bounds = panel12.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel12.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel12.setMinimumSize(preferredSize);
						panel12.setPreferredSize(preferredSize);
					}
				}
				panel4.add(panel12);
				panel12.setBounds(5, 10, 490, 220);

				//======== panel13 ========
				{
					panel13.setBorder(new TitledBorder("Presets (coming soon)"));
					panel13.setLayout(null);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel13.getComponentCount(); i++) {
							Rectangle bounds = panel13.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel13.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel13.setMinimumSize(preferredSize);
						panel13.setPreferredSize(preferredSize);
					}
				}
				panel4.add(panel13);
				panel13.setBounds(5, 235, 490, 45);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < panel4.getComponentCount(); i++) {
						Rectangle bounds = panel4.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = panel4.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					panel4.setMinimumSize(preferredSize);
					panel4.setPreferredSize(preferredSize);
				}
			}
			tabbed_pane.addTab("Food", panel4);	
			
			//======== panel19 ========
			{
				panel19.setLayout(null);

				//======== panel20 ========
				{
					panel20.setBorder(new TitledBorder("Potion Settings"));
					panel20.setLayout(null);

					//---- potions_actionbar_combo_slot23 ----
					potions_actionbar_combo_slot23.setModel(new DefaultComboBoxModel<>(new String[] {
						"None",
						"(Super) Strength Potion",
						"(Super) Attack Potion",
						"(Super) Defence Potion",
						"(Super) Attack + Strength"
					}));
					panel20.add(potions_actionbar_combo_slot23);
					potions_actionbar_combo_slot23.setBounds(20, 80, 135, 20);

					//---- potions_actionbar_slot23 ----
					potions_actionbar_slot23.setText("Combat Potion:");
					panel20.add(potions_actionbar_slot23);
					potions_actionbar_slot23.setBounds(new Rectangle(new Point(20, 60), potions_actionbar_slot23.getPreferredSize()));

					//---- potions_actionbar_combo_slot4 ----
					potions_actionbar_combo_slot4.setModel(new DefaultComboBoxModel<>(new String[] {
						"None",
						"Prayer Potion"
					}));
					panel20.add(potions_actionbar_combo_slot4);
					potions_actionbar_combo_slot4.setBounds(175, 80, 135, 20);

					//---- potions_actionbar_slot4 ----
					potions_actionbar_slot4.setText("Prayer Potion:");
					panel20.add(potions_actionbar_slot4);
					potions_actionbar_slot4.setBounds(175, 60, 83, 14);

					//---- potions_actionbar_combo_slot5 ----
					potions_actionbar_combo_slot5.setModel(new DefaultComboBoxModel<>(new String[] {
						"None",
						"Antifire"
					}));
					panel20.add(potions_actionbar_combo_slot5);
					potions_actionbar_combo_slot5.setBounds(330, 80, 135, 20);

					//---- potions_actionbar_slot5 ----
					potions_actionbar_slot5.setText("Antifire Potion (Not implemented):");
					panel20.add(potions_actionbar_slot5);
					potions_actionbar_slot5.setBounds(330, 60, 83, 14);

					//---- use_potions_chkbox ----
					use_potions_chkbox.setText("Use Potions");
					panel20.add(use_potions_chkbox);
					use_potions_chkbox.setBounds(205, 25, 81, use_potions_chkbox.getPreferredSize().height);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel20.getComponentCount(); i++) {
							Rectangle bounds = panel20.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel20.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel20.setMinimumSize(preferredSize);
						panel20.setPreferredSize(preferredSize);
					}
				}
				panel19.add(panel20);
				panel20.setBounds(5, 5, 485, 225);

				//======== panel21 ========
				{
					panel21.setBorder(new TitledBorder("Presets (coming soon)"));
					panel21.setLayout(null);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel21.getComponentCount(); i++) {
							Rectangle bounds = panel21.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel21.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel21.setMinimumSize(preferredSize);
						panel21.setPreferredSize(preferredSize);
					}
				}
				panel19.add(panel21);
				panel21.setBounds(5, 235, 485, 45);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < panel19.getComponentCount(); i++) {
						Rectangle bounds = panel19.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = panel19.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					panel19.setMinimumSize(preferredSize);
					panel19.setPreferredSize(preferredSize);
				}
			}
			tabbed_pane.addTab("Potions", panel19);
		


			//======== panel6 ========
			{
				panel6.setLayout(null);

				//======== panel17 ========
				{
					panel17.setBorder(new TitledBorder("Miscellaneous Settings"));
					panel17.setLayout(null);

					//---- misc_arrow_help ----
					misc_arrow_help.setText("Arrows to pick up and equip:");
					panel17.add(misc_arrow_help);
					misc_arrow_help.setBounds(15, 25, misc_arrow_help.getPreferredSize().width, 20);

					//---- misc_arrow_box ----
					misc_arrow_box.setModel(new DefaultComboBoxModel<>(new String[] {
						"Bronze arrow ID: 882",
						"Iron arrow ID: 884",
						"Steel arrow ID: 886",
						"Mithril arrow ID: 888",
						"Adamant arrow ID: 890",
						"Rune arrow ID: 892",
						"Bronze bolts ID: 877",
						"Iron bolts ID: 9140",
						"Steel bolts ID: 9141",
						"Mithril bolts ID: 9142",
						"Adamant bolts ID: 9143",
						"Runite bolts ID: 9144",
						"Broad-tipped bolts ID: 13280"
					}));
					panel17.add(misc_arrow_box);
					misc_arrow_box.setBounds(165, 25, 105, misc_arrow_box.getPreferredSize().height);

					//---- MISC_momentum_chkbox ----
					MISC_momentum_chkbox.setText("Use Momentum");
					panel17.add(MISC_momentum_chkbox);
					MISC_momentum_chkbox.setBounds(10, 50, 105, MISC_momentum_chkbox.getPreferredSize().height);

					//---- misc_help_label1 ----
					misc_help_label1.setText("Make sure Momentum is in the first skill slot.");
					misc_help_label1.setHorizontalAlignment(SwingConstants.CENTER);
					misc_help_label1.setFont(misc_help_label1.getFont().deriveFont(misc_help_label1.getFont().getSize() - 1f));
					panel17.add(misc_help_label1);
					misc_help_label1.setBounds(10, 75, 205, misc_help_label1.getPreferredSize().height);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel17.getComponentCount(); i++) {
							Rectangle bounds = panel17.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel17.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel17.setMinimumSize(preferredSize);
						panel17.setPreferredSize(preferredSize);
					}
				}
				panel6.add(panel17);
				panel17.setBounds(5, 5, 490, 225);

				//======== panel18 ========
				{
					panel18.setBorder(new TitledBorder("Presets (coming soon)"));
					panel18.setLayout(null);

					{ // compute preferred size
						Dimension preferredSize = new Dimension();
						for(int i = 0; i < panel18.getComponentCount(); i++) {
							Rectangle bounds = panel18.getComponent(i).getBounds();
							preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
							preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
						}
						Insets insets = panel18.getInsets();
						preferredSize.width += insets.right;
						preferredSize.height += insets.bottom;
						panel18.setMinimumSize(preferredSize);
						panel18.setPreferredSize(preferredSize);
					}
				}
				panel6.add(panel18);
				panel18.setBounds(5, 235, 490, 45);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < panel6.getComponentCount(); i++) {
						Rectangle bounds = panel6.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = panel6.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					panel6.setMinimumSize(preferredSize);
					panel6.setPreferredSize(preferredSize);
				}
			}
			tabbed_pane.addTab("Extras", panel6);

		}
		contentPane.add(tabbed_pane);
		tabbed_pane.setBounds(5, 80, 555, 290);

		//---- startButton ----
		startButton.setText("Apply Settings and Start");
		startButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startButtonActionPerformed(e);
				
			}
		});
		contentPane.add(startButton);
		startButton.setBounds(50, 400, 365, 20);

		//---- exitButton ----
		exitButton.setText("Close GUI");
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exitButtonActionPerformed(e);
				
			}
		});
		contentPane.add(exitButton);
		exitButton.setBounds(435, 400, 125, 20);

		//---- fill_reminder ----
		fill_reminder.setText("Remember to fill in all the tabs/presets before starting!");
		fill_reminder.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(fill_reminder);
		fill_reminder.setBounds(50, 380, 365, fill_reminder.getPreferredSize().height);

		//---- label3 ----
		label3.setText("MondoFighter");
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setFont(new Font("Myriad Pro", Font.PLAIN, 32));
		contentPane.add(label3);
		label3.setBounds(0, 5, 570, 50);

		//---- label4 ----
		label4.setText("Version 1.0 - Updated: 12/12/2012");
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label4);
		label4.setBounds(10, 60, 550, label4.getPreferredSize().height);
		contentPane.add(separator1);
		separator1.setBounds(0, 435, 570, 5);

		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for(int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		pack();
		setLocationRelativeTo(getOwner());
		
	}

	private JTabbedPane tabbed_pane;
	private JPanel panel1;
	private JLabel radius_reminder1;
	private JLabel radius_reminder2;
	private JPanel panel7;
	private JCheckBox ANTIBAN_chkbox_movemouse;
	private JCheckBox ANTIBAN_chkbox_rotate;
	private JCheckBox ANTIBAN_chkbox_checkskill;
	private JCheckBox ANTIBAN_chkbox_randtab;
	private JCheckBox ANTIBAN_chkbox_hoverplayer;
	private JCheckBox ANTIBAN_chkbox_turn;
	private JSlider antiban_freq_slider;
	private JPanel panel14;
	private JLabel fight_radius;
	private JTextField fight_radius_field;
	private JLabel loot_radius;
	private JTextField loot_radius_field;
	private JLabel skill_to_track;
	private JComboBox<String> skillChoose;
	private JPanel panel15;
	private JPanel panel2;
	private JScrollPane scrollPane1;
	private JList NPC_unselected_list;
	private JScrollPane scrollPane2;
	public static JList NPC_selected_list;
	private JButton NPC_refresh_btn;
	private JButton NPC_add_btn;
	private JButton NPC_clear_btn;
	private JLabel label19;
	private JPanel panel8;
	private JPanel panel9;
	private JPanel panel10;
	private JButton npc_save_btn;
	private JButton npc_load_btn;
	private JComboBox npc_preset_combobox;
	private JPanel panel3;
	private JCheckBox enable_loot_chkbox;
	private JScrollPane scrollPane3;
	private JList LOOT_selected_list;
	private JTextField loot_name_field;
	private JButton LOOT_add_btn;
	private JButton LOOT_refresh_btn;
	private JButton LOOT_clear_btn;
	private JCheckBox LOOT_rdt_chkbox;
	private JLabel loot_rdt_label;
	private JLabel loot_help4;
	private JPanel panel11;
	private JPanel panel16;	
	private JPanel panel5;
	private JPanel panel22;
	private JPanel panel23;
	private JTextField alchemy_txt_field;
	private JPanel panel25;
	private JScrollPane scrollPane4;
	private JList alchemy_list;
	private JButton alchemy_refresh;
	private JButton alchemy_clear;
	private JLabel alchemy_help1;
	private JButton alchemy_add;
	private JCheckBox alchemy_enable;
	private JPanel panel24;
	private JPanel panel4;
	private JComboBox<String> food_type;
	private JSlider eat_at_slider;
	private JCheckBox food_use_chkbox;
	private JPanel panel12;
	private JPanel panel13;	
	private JPanel panel19;
	private JPanel panel20;
	private JComboBox<String> potions_actionbar_combo_slot23;
	private JLabel potions_actionbar_slot23;
	private JComboBox<String> potions_actionbar_combo_slot4;
	private JLabel potions_actionbar_slot4;
	private JComboBox<String> potions_actionbar_combo_slot5;
	private JLabel potions_actionbar_slot5;
	private JCheckBox use_potions_chkbox;
	private JPanel panel21;
	private JPanel panel6;
	private JPanel panel17;
	private JLabel misc_arrow_help;
	private JComboBox<String> misc_arrow_box;
	private JCheckBox MISC_momentum_chkbox;
	private JLabel misc_help_label1;
	private JPanel panel18;
	private JButton startButton;
	private JButton exitButton;
	private JLabel fill_reminder;
	private JLabel label3;
	private JLabel label4;
	private JSeparator separator1;


}