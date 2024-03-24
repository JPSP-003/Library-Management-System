/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package oneautomate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;
import java.sql.Statement;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class LoginFaculty extends javax.swing.JFrame {

    public LoginFaculty() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        bgImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login - OneAutomate");
        setLocation(new java.awt.Point(350, 150));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Welcome to OneAutomate (Faculty)");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 20, 350, 37);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oneautomate/assets/icons8-male-user-94.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        jLabel4.setMaximumSize(new java.awt.Dimension(94, 94));
        jLabel4.setMinimumSize(new java.awt.Dimension(94, 94));
        getContentPane().add(jLabel4);
        jLabel4.setBounds(350, 60, 94, 94);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 255), 3, true));
        jPanel1.setForeground(new java.awt.Color(0, 153, 255));
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel2.setText("Enter your admission no.");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(40, 90, 180, 30);

        jLabel3.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        jLabel3.setText("Enter your password");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(70, 140, 160, 30);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1);
        jTextField1.setBounds(250, 90, 160, 30);
        jTextField1.getAccessibleContext().setAccessibleParent(this);

        jButton1.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(200, 230, 90, 30);
        jButton1.getAccessibleContext().setAccessibleParent(this);

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jPasswordField1);
        jPasswordField1.setBounds(250, 140, 160, 30);
        jPasswordField1.getAccessibleContext().setAccessibleParent(this);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(150, 110, 500, 300);

        bgImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oneautomate/assets/pexels-codioful-(formerly-gradienta)-7134986.jpg"))); // NOI18N
        bgImage.setText("jLabel1");
        getContentPane().add(bgImage);
        bgImage.setBounds(0, 0, 800, 500);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(evt.getSource() == jButton1)
        {
            String user = jTextField1.getText();
            String password = jPasswordField1.getText();
            
            try{
            
                if(isValidUser(user,password)){          
                JOptionPane.showMessageDialog(this, "Welcome back, "+user+"! ");
                DashStudent d = new DashStudent();
                d.setVisible(true);
                this.dispose();
               
                final String username = "novumxr@gmail.com"; // Your Gmail email address
                final String apppassword = "xyafqcpmfmdpguqq"; // Your Gmail password

                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.ssl.protocols", "TLSv1.2");


        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, apppassword);
            }
        });

        try {
            
            Connection con=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/OneAutomate","root","admin");
            Statement stmt=con.createStatement();
            String data=null;
            ResultSet rs1=stmt.executeQuery("Select email from LoginDetails where admn_no = '" + user + "'");
            while (rs1.next()) {
                // Retrieve the data from the result set and store it in a Java string
                data = rs1.getString("email");

                // Do something with the retrieved data
                System.out.println("Retrieved data: " + data);
            }
            java.util.Date date = new java.util.Date();    
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(data)); // Replace with the recipient's email address
            message.setSubject("Recent Login to OneAutomate - The OneAutomate App");
            message.setText("The OneAutomate App\n\nHello, "+user+"! \n\nOur systems detected a login to OneAutomate at "+ date +". \n\nIf it was you, please ignore this mail. \n\n- The OneAutomate Team");

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
                }
                else{
                        JOptionPane.showMessageDialog(this, "Oops, couldn't find you! Try again");
                        new LoginFaculty().setVisible(true);
                        setVisible(false);
                        
                }
            }
            catch (SQLException e)
                    {
                        System.out.println(e);
                    }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed

    }//GEN-LAST:event_jPasswordField1ActionPerformed
                                      
private boolean isValidUser(String user,String password) throws SQLException{
        Connection con=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/OneAutomate","root","admin");
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("Select * from LoginDetails where admn_no = '"+user+"' and password = '" +password+ "'");
        return rs.next();
}
        
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(LoginFaculty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFaculty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFaculty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFaculty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFaculty().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bgImage;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
