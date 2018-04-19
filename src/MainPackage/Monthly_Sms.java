/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import static MainPackage.Weekly_sms.grade;
import static MainPackage.Weekly_sms.l1;
import static MainPackage.Weekly_sms.l2;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author asadj
 */
public class Monthly_Sms extends javax.swing.JDialog {

    String[] str = {"January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"};

    public Connection conn() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:49172;"
                    + "databaseName=sscc;user=sa;password=sa9";
            return DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "An error occured while Connecting to Database.");
        }
        return null;
    }
    int numberoftest;
    int noofstudents;

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public static DefaultListModel l1 = new DefaultListModel();
    public static DefaultListModel l2 = new DefaultListModel();
    public static String grade;
    public static String month;
    public static JPanel Monthlysms = new JPanel(new GridBagLayout());

    public Monthly_Sms(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
       try{
            con=conn();
        }
        catch(Exception ex ){
            JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MonthResult4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        GradeReslut_view4 = new javax.swing.JComboBox();
        Month_result_view = new javax.swing.JComboBox();
        View = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        MonthResult4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel21.setText("Class:");

        jLabel22.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel22.setText("Month:");

        GradeReslut_view4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "IX-A", "X-A", "X-B", "XI-A", "XI-B", "XII-A", "XI-Commerce", "XII-Commerce", "B.com-I", "B.com-II" }));
        GradeReslut_view4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                GradeReslut_view4ItemStateChanged(evt);
            }
        });

        View.setText("Enter");
        View.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MonthResult4Layout = new javax.swing.GroupLayout(MonthResult4);
        MonthResult4.setLayout(MonthResult4Layout);
        MonthResult4Layout.setHorizontalGroup(
            MonthResult4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MonthResult4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MonthResult4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(View, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(MonthResult4Layout.createSequentialGroup()
                        .addGroup(MonthResult4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(MonthResult4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(GradeReslut_view4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Month_result_view, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        MonthResult4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {GradeReslut_view4, Month_result_view});

        MonthResult4Layout.setVerticalGroup(
            MonthResult4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MonthResult4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MonthResult4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addGroup(MonthResult4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(GradeReslut_view4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(MonthResult4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addGroup(MonthResult4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(Month_result_view, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(View, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MonthResult4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {GradeReslut_view4, Month_result_view});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MonthResult4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MonthResult4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GradeReslut_view4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_GradeReslut_view4ItemStateChanged
        Month_result_view.removeAllItems();
        try {
            ps = con.prepareStatement("select DISTINCT month(Date_of_Entry),year(Date_of_Entry) from WeeklyTestTable where Class='" + GradeReslut_view4.getSelectedItem() + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                Month_result_view.addItem(str[rs.getInt(1) - 1]);
            }
        } catch (SQLException ex) {
        }
    }//GEN-LAST:event_GradeReslut_view4ItemStateChanged

    private void ViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewActionPerformed

        if (Month_result_view.getItemCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Please Select Appropriate Month");
        } else {
            int index = Arrays.asList(str).indexOf(Month_result_view.getSelectedItem());
            //            System.out.println(index);

            try {

                ps = con.prepareStatement("select Count(DISTINCT Date_of_Entry) from WeeklyTestTable where Class='" + GradeReslut_view4.getSelectedItem() + "' AND month(Date_of_Entry)=" + (index + 1) + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    numberoftest = rs.getInt(1);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());

            }

            int totalmarks = 15 * numberoftest;

            //for no of students
            try {

                ps = con.prepareStatement("select count(Name) from Basic_info where class='" + GradeReslut_view4.getSelectedItem() + "'");
                rs = ps.executeQuery();
                while (rs.next()) {

                    noofstudents = rs.getInt(1);

                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
            }

            ///now calculating final month marks and inserting it
            float totalmarksobtained = 0;
            String name = "";
            int roll_no = 0;

            //        getting markstotal
            for (int i = 1; i <= noofstudents; i++) {

                //first get roll no then use it
                try {
                    /**
                     * query logic OFFSET: Specifies the number of rows to skip
                     * before it starts to return rows from the query
                     * expression.
                     *
                     * FETCH NEXT: Specifies the number of rows to return after
                     * the OFFSET clause has been processed. *
                     *
                     */
                    String querry = ";WITH Results_CTE AS (SELECT Roll_No,ROW_NUMBER() OVER (ORDER BY Roll_No ASC ) AS RowNum From Basic_info  where class='" + GradeReslut_view4.getSelectedItem() + "') select Roll_No from Results_CTE where RowNum=" + i + "";
                    ps = con.prepareStatement(querry);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        roll_no = rs.getInt(1);

                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
                }

                //////getting name
                try {

                    ps = con.prepareStatement("select Name from Basic_info where Roll_No='" + roll_no + "' AND class='" + GradeReslut_view4.getSelectedItem() + "'");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        name = rs.getString(1);

                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
                }

                //totalmarksobtain
                try {

                    ps = con.prepareStatement("select sum(Marks) from WeeklyTestTable where Roll_No='" + roll_no + "' AND class='" + GradeReslut_view4.getSelectedItem() + "' AND month(Date_of_Entry)=" + (index + 1) + "");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        totalmarksobtained = rs.getFloat(1);

                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
                }

                System.out.println(totalmarksobtained);
                //        JOptionPane.showMessageDialog(rootPane, roll_no+name+totalmarksobtained);
                String insert = "Insert into Monthly_Result(Roll_No,Class,Name,Month,Marks_obtain,Total_marks)"
                        + "Values(" + roll_no + ",'" + GradeReslut_view4.getSelectedItem() + "','" + name + "','" + Month_result_view.getSelectedItem() + "'," + totalmarksobtained + "," + totalmarks + ")";
                String update = "update Monthly_Result set Marks_obtain=" + totalmarksobtained + ",Total_marks=" + totalmarks + " where Class ='" + GradeReslut_view4.getSelectedItem() + "' AND Roll_No=" + roll_no + "AND Month='" + Month_result_view.getSelectedItem() + "'";

                String mainquery
                        = "IF (NOT EXISTS(SELECT * FROM Monthly_Result WHERE Name='" + name + "' AND Class ='" + GradeReslut_view4.getSelectedItem() + "' AND Month='" + Month_result_view.getSelectedItem() + "'))"
                        + "BEGIN\n"
                        + insert + "\n"
                        + "END\n"
                        + "ELSE\n"
                        + "BEGIN\n"
                        + update + "\n"
                        + "END";

                try {
                    ps = con.prepareStatement(mainquery);
                    ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
                }

            }

            ////for calculating percentage anotherloop
            float monthmarks = 0;
            float percentage;
            for (int i = 1; i <= noofstudents; i++) {

                ///////geting highst mark in descending
                try {
                    ////////////query logic
                    /**
                     * OFFSET: Specifies the number of rows to skip before it
                     * starts to return rows from the query expression.
                     *
                     * FETCH NEXT: Specifies the number of rows to return after
                     * the OFFSET clause has been processed. *
                     *
                     */
                    ps = con.prepareStatement(";WITH Results_CTE AS (SELECT Marks_obtain,ROW_NUMBER() OVER (ORDER BY Marks_obtain DESC ) AS RowNum From Monthly_Result  where class='" + GradeReslut_view4.getSelectedItem() + "' AND Month='" + Month_result_view.getSelectedItem() + "') select Marks_obtain from Results_CTE where RowNum=" + i + "");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        monthmarks = rs.getFloat(1);

                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
                }

                ///geting name
                try {

                    ps = con.prepareStatement(";WITH Results_CTE AS (SELECT Name,ROW_NUMBER() OVER (ORDER BY Marks_obtain DESC ) AS RowNum From Monthly_Result  where class='" + GradeReslut_view4.getSelectedItem() + "' AND Month='" + Month_result_view.getSelectedItem() + "') select Name from Results_CTE where RowNum=" + i + "");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        name = rs.getString(1);

                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
                }

                percentage = monthmarks * 100 / totalmarks;

                try {
                    String Rank = i + getOrdinalFor(i);
                    ps = con.prepareStatement("update Monthly_Result set percentage=" + String.format("%.2f", percentage) + ", rank='" + Rank + "' where Marks_obtain=" + monthmarks + " AND Name='" + name + "' AND Class='" + GradeReslut_view4.getSelectedItem() + "' AND Month='" + Month_result_view.getSelectedItem() + "'");
                    ps.executeUpdate();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage() + "error");
                }

            }

            this.setVisible(false);

            
            
        Menu.Bmsg2.setEnabled(true);
        l1.removeAllElements();
        l2.removeAllElements();
        grade = GradeReslut_view4.getSelectedItem().toString();
        month=Month_result_view.getSelectedItem().toString();
        Menu.counter2=0;
        Menu.time2=null;
         try {
            ps = con.prepareStatement("select CONVERT(varchar(10), Roll_No) + ' ' + Name+' '+Contact as full_name from Basic_Info WHERE Class = '"+grade+"' order by Roll_No ASC");
            rs = ps.executeQuery();
            while (rs.next()) {
                l1.addElement(rs.getString(1));
            }
            Menu.jList5.setModel(l1);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
        }
        
            
            
            
            
            
            
            
            
            
            ////////geting view
            Monthlysms.setPreferredSize(Menu.Tab.getSize());
            Monthlysms.setBackground(Color.WHITE);
            Monthlysms.add(Menu.MonthlySmsPanel);
            Monthlysms.setName("Send Monthly Result");
            Menu.Tab.setVisible(true);
            Menu.Tab.add(Monthlysms);

            Menu.Tab.setSelectedComponent(Monthlysms);
            Icon icon = new ImageIcon(getClass().getResource("/Images/details.png"));
            Menu.Tab.setIconAt(Menu.Tab.getSelectedIndex(), icon);

            this.setVisible(false);
        }
    }//GEN-LAST:event_ViewActionPerformed
    public static String getOrdinalFor(int value) {
        int hundredRemainder = value % 100;
        int tenRemainder = value % 10;
        if (hundredRemainder - tenRemainder == 10) {
            return "th";
        }

        switch (tenRemainder) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
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
            java.util.logging.Logger.getLogger(Monthly_Sms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Monthly_Sms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Monthly_Sms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Monthly_Sms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Monthly_Sms dialog = new Monthly_Sms(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox GradeReslut_view;
    public javax.swing.JComboBox GradeReslut_view1;
    public javax.swing.JComboBox GradeReslut_view2;
    public javax.swing.JComboBox GradeReslut_view3;
    public javax.swing.JComboBox GradeReslut_view4;
    private javax.swing.JPanel MonthResult;
    private javax.swing.JPanel MonthResult1;
    private javax.swing.JPanel MonthResult2;
    private javax.swing.JPanel MonthResult3;
    private javax.swing.JPanel MonthResult4;
    public javax.swing.JComboBox Month_result_view;
    private javax.swing.JButton View;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    // End of variables declaration//GEN-END:variables
}
