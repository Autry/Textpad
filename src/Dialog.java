import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Dialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Dialog() {
		setEnabled(true);
		setResizable(false);
		setTitle("About Textpad");
		setBounds(100, 100, 489, 353);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblAboutTextpad = new JLabel("About Textpad");
			lblAboutTextpad.setBounds(122, 21, 203, 31);
			lblAboutTextpad.setFont(new Font("Sui Generis Rg", Font.PLAIN, 25));
			contentPanel.add(lblAboutTextpad);
		}
		{
			JTextArea txtrMicrosoftWindows = new JTextArea();
			txtrMicrosoftWindows.setEditable(false);
			txtrMicrosoftWindows.setWrapStyleWord(true);
			txtrMicrosoftWindows.setText(
					"Microsoft Windows\r\nVersion .01 (OS Build 10586.318)\r\n\u00A9 2016 Private Autry Programs. All rights reserved.\r\n\r\n------------------------------------------------------------------------------------------------\r\n\r\nThis product is licensed under the Autry Software License Terms to: \r\n\tRyan Autry");
			txtrMicrosoftWindows.setBounds(10, 76, 453, 194);
			contentPanel.add(txtrMicrosoftWindows);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});

			}
		}

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
