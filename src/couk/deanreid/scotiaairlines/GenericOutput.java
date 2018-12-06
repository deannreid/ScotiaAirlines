/*
 Scotia Airlines - HND Computer Science
 Version 1.4
 @Author: Dean D. Reid
 */

 /*
  *@Depricated - No longer required at the moment
  */
package couk.deanreid.scotiaairlines;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GenericOutput {

    public void genericOutput(String inputMessage) {
        JLabel saTitle = new javax.swing.JLabel();
        final JFrame saFrame = new JFrame();
        saFrame.setSize(300, 100);
        saFrame.setLocationRelativeTo(null);
        saFrame.setType(java.awt.Window.Type.UTILITY);
        saFrame.setTitle("Scotia Airlines - ");

        saTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        saTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon.png"))); // NOI18N
        saTitle.setText("Scotia Airlines Booking System");

        JPanel saPanel = new JPanel();

        Container c1 = new Container();
        Container c2 = new Container();

        c1.setLayout(new GridLayout(1, 0));
        c2.setLayout(new GridLayout(1, 0));

        JLabel message = new JLabel(inputMessage);

        JButton backBtn = new JButton("Main Menu");

        c1.add(message);
        c2.add(backBtn);

        saPanel.add(c1);
        saPanel.add(c2);

        saFrame.add(saPanel);
        saFrame.setVisible(true);

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saFrame.dispose();
            }
        });

    }
}
