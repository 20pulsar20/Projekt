package pl.shine.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;

public class AboutDialog extends JDialog {
	
	public AboutDialog() {
		getContentPane().setBackground(Color.WHITE);

        initUI();
    }

    public final void initUI() {

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


        JLabel name = new JLabel("ShineOnTime, 1.00" + "\n");
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        name.setFont(new Font("Serif", Font.BOLD, 12));
        name.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JTextArea autors = new JTextArea("");
        autors.setTabSize(4);
        autors.append("Autorzy: " + "\n" + "\t" + "Ewa Ciachanowska" + "\n" 
        + "\t" + "Marek Ciszewski" + "\n"
        + "\t" + "Maciej Jachowicz" + "\n"
        + "\t" + "Maciej Waluœ");
        autors.setEditable(false);
        autors.setBorder(new EmptyBorder(5, 10, 5, 10));
        
        
        getContentPane().add(name);
        getContentPane().add(autors);
        

        JButton close = new JButton("Zamknij");

        // Akcja podpieta pod przycisk "Zamknij"
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                dispose();
            }
        });

        close.setAlignmentX(0.5f);
        getContentPane().add(close);

        setModalityType(ModalityType.APPLICATION_MODAL);

        setTitle("O programie");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(240, 214);
    }
}
