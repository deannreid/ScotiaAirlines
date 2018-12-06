/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */


public class NewJFrame extends javax.swing.JFrame {

    public NewJFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectFlightPanel = new javax.swing.JPanel();
        saLabel1 = new javax.swing.JLabel();
        tempButton1 = new javax.swing.JButton();
        tempButton2 = new javax.swing.JButton();
        tempButton3 = new javax.swing.JButton();
        tempButton4 = new javax.swing.JButton();
        tempButton5 = new javax.swing.JButton();
        tempButton6 = new javax.swing.JButton();
        tempButton7 = new javax.swing.JButton();
        tempButton8 = new javax.swing.JButton();
        btnExit1 = new javax.swing.JButton();
        saSeatPlan = new javax.swing.JPanel();
        seatMap = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        saLabel = new javax.swing.JLabel();
        btnBook = new javax.swing.JButton();
        btnFlight = new javax.swing.JButton();
        btnSeat = new javax.swing.JButton();
        btnFA = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        selectFlightPanel.setLayout(null);

        saLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/tbselectflight.png"))); // NOI18N
        selectFlightPanel.add(saLabel1);
        saLabel1.setBounds(60, -50, 281, 207);

        tempButton1.setText("FLIGHT INFO");
        selectFlightPanel.add(tempButton1);
        tempButton1.setBounds(10, 120, 90, 90);

        tempButton2.setText("FLIGHT INFO");
        selectFlightPanel.add(tempButton2);
        tempButton2.setBounds(110, 120, 90, 90);

        tempButton3.setText("FLIGHT INFO");
        selectFlightPanel.add(tempButton3);
        tempButton3.setBounds(320, 120, 90, 90);

        tempButton4.setText("FLIGHT INFO");
        selectFlightPanel.add(tempButton4);
        tempButton4.setBounds(220, 120, 90, 90);

        tempButton5.setText("FLIGHT INFO");
        selectFlightPanel.add(tempButton5);
        tempButton5.setBounds(10, 220, 90, 90);

        tempButton6.setText("FLIGHT INFO");
        selectFlightPanel.add(tempButton6);
        tempButton6.setBounds(110, 220, 90, 90);

        tempButton7.setText("FLIGHT INFO");
        selectFlightPanel.add(tempButton7);
        tempButton7.setBounds(320, 220, 90, 90);

        tempButton8.setText("FLIGHT INFO");
        selectFlightPanel.add(tempButton8);
        tempButton8.setBounds(220, 220, 90, 90);

        btnExit1.setText("Exit");
        selectFlightPanel.add(btnExit1);
        btnExit1.setBounds(160, 330, 100, 30);

        saSeatPlan.setLayout(new javax.swing.OverlayLayout(saSeatPlan));

        seatMap.setLayout(new java.awt.GridLayout(2, 2));

        jButton1.setText("jButton1");
        seatMap.add(jButton1);

        jButton5.setText("jButton1");
        seatMap.add(jButton5);

        jLabel2.setText("jLabel2");
        seatMap.add(jLabel2);

        jButton6.setText("jButton1");
        seatMap.add(jButton6);

        jButton3.setText("jButton1");
        seatMap.add(jButton3);

        jButton4.setText("jButton1");
        seatMap.add(jButton4);

        jButton2.setText("jButton1");
        seatMap.add(jButton2);

        jButton7.setText("jButton1");
        seatMap.add(jButton7);

        jButton8.setText("jButton1");
        seatMap.add(jButton8);

        saSeatPlan.add(seatMap);

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("saFrame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(300, 400));
        setSize(new java.awt.Dimension(300, 400));
        setType(java.awt.Window.Type.UTILITY);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        saLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/tbico.png"))); // NOI18N
        getContentPane().add(saLabel);
        saLabel.setBounds(50, 0, 278, 85);

        btnBook.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBook.setText("Book Flight");
        getContentPane().add(btnBook);
        btnBook.setBounds(80, 90, 220, 60);

        btnFlight.setText("Check Flight Status");
        getContentPane().add(btnFlight);
        btnFlight.setBounds(80, 160, 220, 60);

        btnSeat.setText("Check Seat Status");
        getContentPane().add(btnSeat);
        btnSeat.setBounds(80, 230, 220, 60);

        btnFA.setText("Administration");
        getContentPane().add(btnFA);
        btnFA.setBounds(80, 300, 110, 60);

        btnExit.setText("Exit");
        getContentPane().add(btnExit);
        btnExit.setBounds(200, 300, 100, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
          // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    /**
     @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBook;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnExit1;
    private javax.swing.JButton btnFA;
    private javax.swing.JButton btnFlight;
    private javax.swing.JButton btnSeat;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel saLabel;
    private javax.swing.JLabel saLabel1;
    private javax.swing.JPanel saSeatPlan;
    private javax.swing.JPanel seatMap;
    private javax.swing.JPanel selectFlightPanel;
    private javax.swing.JButton tempButton1;
    private javax.swing.JButton tempButton2;
    private javax.swing.JButton tempButton3;
    private javax.swing.JButton tempButton4;
    private javax.swing.JButton tempButton5;
    private javax.swing.JButton tempButton6;
    private javax.swing.JButton tempButton7;
    private javax.swing.JButton tempButton8;
    // End of variables declaration//GEN-END:variables
}
