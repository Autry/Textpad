import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private static final String TITLE = "Textpad";

	private JPanel contentPane;
	private JMenu editMenu;
	private JTextArea textArea;
	private JCheckBoxMenuItem wordWrapMenuItem;

	private Dialog dialog;

	private Date date = new Date();
	private JFileChooser fileChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setResizable(false);
		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 657);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);

		JMenuItem openMenuItem = new JMenuItem("Open...");
		fileMenu.add(openMenuItem);
		openMenuItem.addActionListener(new FileMenuListener());

		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		saveMenuItem.addActionListener(new FileMenuListener());

		JMenuItem saveAsMenuItem = new JMenuItem("Save As...");
		fileMenu.add(saveAsMenuItem);
		saveAsMenuItem.addActionListener(new FileMenuListener());

		JSeparator separator = new JSeparator();
		fileMenu.add(separator);

		JMenuItem pageSetupMenuItem = new JMenuItem("Page Setup");
		fileMenu.add(pageSetupMenuItem);

		JMenuItem printMenuItem = new JMenuItem("Print...");
		fileMenu.add(printMenuItem);

		JSeparator separator_1 = new JSeparator();
		fileMenu.add(separator_1);

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);
		exitMenuItem.addActionListener(new FileMenuListener());

		editMenu = new JMenu("Edit");
		menuBar.add(editMenu);

		JMenuItem cutMenuItem = new JMenuItem("Cut");
		editMenu.add(cutMenuItem);
		cutMenuItem.addActionListener(new EditMenuListener());

		JMenuItem copyMenuItem = new JMenuItem("Copy");
		editMenu.add(copyMenuItem);
		copyMenuItem.addActionListener(new EditMenuListener());

		JMenuItem pasteMenuItem = new JMenuItem("Paste");
		editMenu.add(pasteMenuItem);
		pasteMenuItem.addActionListener(new EditMenuListener());

		JMenuItem clearMenuItem = new JMenuItem("Clear");
		editMenu.add(clearMenuItem);
		clearMenuItem.addActionListener(new EditMenuListener());

		JSeparator separator_3 = new JSeparator();
		editMenu.add(separator_3);

		JMenuItem selectAllMenuItem = new JMenuItem("Select All");
		editMenu.add(selectAllMenuItem);
		selectAllMenuItem.addActionListener(new EditMenuListener());

		JMenuItem timeDateMenuItem = new JMenuItem("Time/Date");
		editMenu.add(timeDateMenuItem);
		timeDateMenuItem.addActionListener(new EditMenuListener());

		JMenu formatMenu = new JMenu("Format");
		menuBar.add(formatMenu);

		wordWrapMenuItem = new JCheckBoxMenuItem("Word Wrap");
		formatMenu.add(wordWrapMenuItem);
		wordWrapMenuItem.setSelected(true);
		wordWrapMenuItem.addActionListener(new FormatMenuListener());

		JMenu printMenu = new JMenu("Print");
		menuBar.add(printMenu);

		JMenuItem printLabHeaderMenuItem = new JMenuItem("Print Lab Header");
		printMenu.add(printLabHeaderMenuItem);
		printLabHeaderMenuItem.addActionListener(new PrintMenuListener());

		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		JMenuItem viewHelpMenuItem = new JMenuItem("View Help");
		helpMenu.add(viewHelpMenuItem);
		viewHelpMenuItem.addActionListener(new HelpMenuListener());

		JSeparator separator_2 = new JSeparator();
		helpMenu.add(separator_2);

		JMenuItem aboutTextpadMenuItem = new JMenuItem("About Textpad");
		helpMenu.add(aboutTextpadMenuItem);
		aboutTextpadMenuItem.addActionListener(new HelpMenuListener());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new JTextArea();
		textArea.setLineWrap(true);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(0, 0, 564, 610);
		contentPane.add(scrollPane);

	}

	// ADD CODE HERE
	// -----------------------------------------------------------------

	class FileMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String source = e.getActionCommand();

			if (source.equals("Exit")) {
				System.exit(0);
			} else if (source.equals("Save As...")) {
				fileChooser = new JFileChooser();
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					writeToFile(file);
				}
			} else if (source.equals("Open...")) {
				fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					readFile(file);
				}

			} else if (source.equals("Save")) {
				System.out.println("nothing");
			} else if (source.equals("New")) {
				System.out.println("nothing");
			}

		}
	}

	class EditMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String source = e.getActionCommand();

			if (source.equals("Select All")) {
				textArea.selectAll();
			} else if (source.equals("Clear")) {
				textArea.setText("");
			} else if (source.equals("Paste")) {
				textArea.paste();
			} else if (source.equals("Copy")) {
				textArea.copy();
			} else if (source.equals("Cut")) {
				textArea.cut();
			} else if (source.equals("Time/Date")) {
				SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyy 'at' hh:mm a zzz");
				textArea.append(ft.format(date));
			}

		}
	}

	class FormatMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String source = e.getActionCommand();

			if (source.equals("Word Wrap")) {
				if (wordWrapMenuItem.isSelected()) {
					textArea.setLineWrap(true);
					;
				} else if (wordWrapMenuItem.isSelected() == false) {
					textArea.setLineWrap(false);
				}
			}
		}
	}

	class HelpMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String source = e.getActionCommand();
			if (source.equals("About Textpad")) {
				dialog = new Dialog();
				dialog.setLocationRelativeTo(MainFrame.this);
			} else if (source.equals("View Help")) {
				try {
					URI uri = new URI("http://www.google.com");
					open(uri);
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}

	class PrintMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String source = e.getActionCommand();

			if (source.equals("Print Lab Header")) {
				SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyy");
				textArea.append("Austin Autry \nComputer Science \nStudent ID: 900524134 \nDate: " + ft.format(date)
						+ "\nObjective: ");
			}
		}
	}

	private static void open(URI uri) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e) {
				/* TODO: error handling */ }
		} else {
			/* TODO: error handling */ }
	}

	private void writeToFile(File file) {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(textArea.getText());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readFile(File file) {
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = null;
			while ((line = reader.readLine()) != null) {
				textArea.append(line);
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
