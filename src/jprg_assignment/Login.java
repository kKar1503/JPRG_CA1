
// Class : DIT / FT / 1B / 04
// Admission Number : P2123181
// Name : Yam Kar Lok

package jprg_assignment;

public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        // Initialize hidden components
        lblWrongPW.setVisible(false);
        // Set default button to Login to capture 'Enter' Key
        getRootPane().setDefaultButton(btnLogin);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Student Data Serialization
                IOSystem.studentSerialization(StudentManagement.getStudentList());
                // Good bye sound effect
                SoundPlayer.playSound("SoundEffects\\\\Bye.wav");
                // Log Program termination
                UserActivityLogger.infoLog("Program Terminated");
                // Await Soundplayer duration
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException ex) {
                    UserActivityLogger.errLog("Unable to sleep thread.", ex);
                }
                System.exit(0);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelBackground = new javax.swing.JPanel();
        imgLogo = new javax.swing.JLabel();
        panelLogin = new javax.swing.JPanel();
        labelUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        labelPassword = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        lblWrongPW = new javax.swing.JLabel();
        lblDeveloper = new javax.swing.JLabel();
        lblHeader = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Singapore Polytechnic - Mini Student System");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(500, 500));
        setPreferredSize(new java.awt.Dimension(500, 500));
        setResizable(false);

        panelBackground.setBackground(new java.awt.Color(255, 255, 255));
        panelBackground.setToolTipText(null);

        imgLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jprg_assignment/sp_logo_large.png"))); // NOI18N
        imgLogo.setToolTipText(null);

        panelLogin.setBackground(new java.awt.Color(255, 255, 255));
        panelLogin.setBorder(javax.swing.BorderFactory.createTitledBorder("Login"));
        panelLogin.setToolTipText(null);
        panelLogin.setMaximumSize(new java.awt.Dimension(350, 200));
        panelLogin.setMinimumSize(new java.awt.Dimension(300, 200));
        panelLogin.setName(""); // NOI18N

        labelUsername.setText("Username");
        labelUsername.setToolTipText(null);
        labelUsername.setMaximumSize(new java.awt.Dimension(120, 20));
        labelUsername.setMinimumSize(new java.awt.Dimension(120, 20));
        labelUsername.setPreferredSize(new java.awt.Dimension(120, 20));

        txtUsername.setToolTipText(null);
        txtUsername.setMaximumSize(new java.awt.Dimension(150, 20));
        txtUsername.setMinimumSize(new java.awt.Dimension(150, 20));
        txtUsername.setPreferredSize(new java.awt.Dimension(150, 20));

        labelPassword.setText("Password");
        labelPassword.setToolTipText(null);
        labelPassword.setMaximumSize(new java.awt.Dimension(120, 20));
        labelPassword.setMinimumSize(new java.awt.Dimension(120, 20));
        labelPassword.setPreferredSize(new java.awt.Dimension(120, 20));

        btnLogin.setBackground(java.awt.Color.blue);
        btnLogin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLogin.setText("<html><font color='white'>LOGIN</font></html>");
        btnLogin.setToolTipText(null);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnCancel.setBackground(java.awt.Color.red);
        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnCancel.setText("<html><font color='white'>CANCEL</font></html>");
        btnCancel.setToolTipText(null);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        txtPassword.setMaximumSize(new java.awt.Dimension(150, 20));
        txtPassword.setMinimumSize(new java.awt.Dimension(150, 20));
        txtPassword.setPreferredSize(new java.awt.Dimension(150, 20));

        lblWrongPW.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblWrongPW.setForeground(java.awt.Color.red);
        lblWrongPW.setText("Invalid login credentials. Try again!");
        lblWrongPW.setToolTipText(null);

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLoginLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLoginLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addComponent(labelUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(13, 13, 13))
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblWrongPW)
                .addContainerGap())
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblWrongPW)
                .addGap(18, 18, 18)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblDeveloper.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDeveloper.setText("Developed by: Yam Kar Lok");
        lblDeveloper.setToolTipText(null);

        lblHeader.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblHeader.setText("<html><font color='blue'>MINI STUDENT SYSTEM</font></html>");
        lblHeader.setToolTipText(null);

        javax.swing.GroupLayout panelBackgroundLayout = new javax.swing.GroupLayout(panelBackground);
        panelBackground.setLayout(panelBackgroundLayout);
        panelBackgroundLayout.setHorizontalGroup(
            panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackgroundLayout.createSequentialGroup()
                .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblDeveloper, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelBackgroundLayout.createSequentialGroup()
                            .addGroup(panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelBackgroundLayout.createSequentialGroup()
                                    .addGap(50, 50, 50)
                                    .addComponent(imgLogo))
                                .addGroup(panelBackgroundLayout.createSequentialGroup()
                                    .addGap(96, 96, 96)
                                    .addComponent(lblHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(38, 38, 38)))
                    .addGroup(panelBackgroundLayout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(panelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBackgroundLayout.setVerticalGroup(
            panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackgroundLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(imgLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(panelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(lblDeveloper)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Login Button">
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String username = txtUsername.getText();
        String password = String.valueOf(txtPassword.getPassword());
        Object[] userObj = Authentication.authenticate(username, password);
        if (userObj[0].equals(true)) {
            if (userObj[2].equals(true)) {
                this.dispose();
                AdminMainMenu.adminMenu(userObj);
            } else {
                this.dispose();
                StudentMainMenu.studentMenu(userObj);
            }
        } else {
            UserActivityLogger.infoLog("User entered wrong login credentials:"
                    + "\nUsername: " + username
                    + "\nPassword: " + password);
            lblWrongPW.setVisible(true);
        }
    }//GEN-LAST:event_btnLoginActionPerformed
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Cancel Button">
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // Student Data Serialization
        IOSystem.studentSerialization(StudentManagement.getStudentList());
        // Good bye sound effect
        SoundPlayer.playSound("SoundEffects\\\\Bye.wav");
        // Log Program termination
        UserActivityLogger.infoLog("Program Terminated");
        // Await Soundplayer duration
        try {
            Thread.sleep(1200);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        System.exit(0);
    }//GEN-LAST:event_btnCancelActionPerformed
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Main Method">
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Displays login screen
                new Login().setVisible(true);
                // Sound effect for starting up the application
                SoundPlayer.playSound("SoundEffects\\\\Start.wav");
                // Initialize logger
                UserActivityLogger.setupLogger();
                // Log Program Initation
                UserActivityLogger.infoLog("Program started");
                // Initialize students
                IOSystem.initializeStudents();
            }
        });
    }
    // </editor-fold>

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel imgLogo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelUsername;
    private javax.swing.JLabel lblDeveloper;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblWrongPW;
    private javax.swing.JPanel panelBackground;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

}
