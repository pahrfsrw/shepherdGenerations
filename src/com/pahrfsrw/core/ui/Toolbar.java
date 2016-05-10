package com.pahrfsrw.core.ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Toolbar {
	
	private static final Toolbar INSTANCE = new Toolbar();
	
	private JMenuBar mb;
		private JMenu mb_file;
			private JMenuItem mb_file_newSimulation;
			private JMenuItem mb_file_saveSimulation;
			private JMenuItem mb_file_loadSimulation;
			private JMenu mb_file_import;
				private JMenuItem mb_file_import_herd;
				private JMenuItem mb_file_import_shepherd;
			private JMenu mb_file_export;
				private JMenuItem mb_file_export_herd;
				private JMenuItem mb_file_export_shepherd;
			private JMenuItem mb_file_exit;
		private JMenu mb_tools;
			private JMenuItem mb_tools_openDebugger;
			
	private Toolbar(){
		// Initialization
		mb = new JMenuBar();
		mb_file = new JMenu("File");
		mb_tools = new JMenu("Tools");
		mb_tools_openDebugger = new JMenuItem("Open debugger");
		mb_file_import = new JMenu("Import");
		mb_file_export = new JMenu("Export");
		mb_file_exit = new JMenuItem("Exit");
	    mb_file_import_herd = new JMenuItem("Import herd...");
	    mb_file_export_herd = new JMenuItem("Export herd...");
	    mb_file_import_shepherd = new JMenuItem("Import shepherd...");
	    mb_file_export_shepherd = new JMenuItem("Export shepherd...");
	    mb_file_newSimulation = new JMenuItem("New simulation");
	    mb_file_saveSimulation = new JMenuItem("Load simulation");
	    mb_file_loadSimulation = new JMenuItem("Load simulation");
	    
		// Composition
		mb_file_import.add(mb_file_import_herd);
		mb_file_import.add(mb_file_import_shepherd);
		mb_file_export.add(mb_file_export_herd);
		mb_file_export.add(mb_file_export_shepherd);
		mb_file.add(mb_file_newSimulation);
		mb_file.add(mb_file_saveSimulation);
		mb_file.add(mb_file_loadSimulation);
		mb_file.addSeparator();
		mb_file.add(mb_file_import);
		mb_file.add(mb_file_export);
		mb_file.addSeparator();
		mb_file.add(mb_file_exit);
		mb.add(mb_file);
		
		mb_tools.add(mb_tools_openDebugger);
		mb.add(mb_tools);
		
		// Layout
		mb_file_import.setPreferredSize(new Dimension(100, 20)); // Other menus and items automatically adjust to same size.
		
		// Add listeners to menu exit button
		ExitAction ea = new ExitAction();
		//ea.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control X")); // Lætur ctrl+x vera exit shortcut. Hef ekki prófað þetta.
		mb_file_exit.addActionListener(ea);
	}
	
	public static Toolbar getInstance(){
		return INSTANCE;
	}
	
	public void addTo(JFrame frame){
		frame.setJMenuBar(mb);
	}
}
