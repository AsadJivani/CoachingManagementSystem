/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import Classes.ClosableTabbedPane;
import Classes.centercorner;
import groovyjarjarasm.asm.ClassReader;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asadj
 */
public class Menu extends javax.swing.JFrame {

//tabpanels
    JPanel BioPanel = new JPanel(new GridBagLayout());
    JPanel NewClassPanel = new JPanel(new GridBagLayout());
    JPanel EntryPanel = new JPanel(new GridBagLayout());
    JPanel EditMarksPanel = new JPanel(new GridBagLayout());
    JPanel New_Sms = new JPanel(new GridBagLayout());
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    centercorner center = new centercorner();

    public static String test1, test2, test3, test4;

    FeeRecieptFram another_fee = new FeeRecieptFram(this, rootPaneCheckingEnabled);

    Print_MonthlyResult result = new Print_MonthlyResult(this, rootPaneCheckingEnabled);
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

    public void BusyConnection(Exception ex) {
        ex.printStackTrace();
        if (ex.toString().contains("Connection is busy with results for another hstmt")) {
            con = conn();
        }
    }

    public Menu() {
        initComponents();

        Tab.setVisible(false);
        try {
            con = conn();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
        }

        this.setExtendedState(MAXIMIZED_BOTH);

//        String current_date, date1;
//        int year, current_month, admission_date, date1_year, date1_month, date1_day;
//        // getting date
//        Date date = new Date();
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
//        date1 = ft.format(date);
//        //date1 = "2016-08-31";
//                
//        
//        //splitting date and assigning it to variables
//        String[] y_m_d = date1.split("-");
//        date1_year = Integer.parseInt(y_m_d[0]);
//        date1_month = Integer.parseInt(y_m_d[1]);
//        date1_day = Integer.parseInt(y_m_d[2]); 
//        
//      //reading previous date from a file
//      String path = "D:\\Entry_log.txt";
//      BufferedWriter bw = null;
//      FileWriter fw = null;
//      /*FileReader  fr = new FileReader(path);
//      BufferedReader textReader = new BufferedReader(fr);
//      
//      String file_date;
//      file_date = textReader.readLine();
//      textReader.close();*/
//      String file_date = null;
//      RandomAccessFile fileHandler = null;
//        try {
//            fileHandler = new RandomAccessFile(path, "r" );
//            long fileLength = fileHandler.length() - 1;
//            StringBuilder sb = new StringBuilder();
//
//            for(long filePointer = fileLength; filePointer != -1; filePointer--){
//                fileHandler.seek( filePointer );
//                int readByte = fileHandler.readByte();
//
//                if( readByte == 0xA ) {
//                    if( filePointer == fileLength ) {
//                        continue;
//                    }
//                    break;
//
//                } else if( readByte == 0xD ) {
//                    if( filePointer == fileLength - 1 ) {
//                        continue;
//                    }
//                    break;
//                }
//
//                sb.append( ( char ) readByte );
//            }
//
//            file_date = sb.reverse().toString();
//
//        } catch( java.io.FileNotFoundException e ) {
//            e.printStackTrace();
//            return;
//        } catch( java.io.IOException e ) {
//            e.printStackTrace();
//            return;
//        } finally {
//            if (fileHandler != null )
//                try {
//                    fileHandler.close();
//                } catch (IOException e) {
//                    /* ignore */
//                }
//            
//            try {
//
//			
//
//			File file = new File(path);
//
//			// if file doesnt exists, then create it
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//
//			// true = append file
//			fw = new FileWriter(file.getAbsoluteFile(), true);
//			bw = new BufferedWriter(fw);
//
//                        bw.write("\r\n");
//
//			//System.out.println("Done");
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//
//		} finally {
//
//			try {
//
//				if (bw != null)
//					bw.close();
//
//				if (fw != null)
//					fw.close();
//
//			} catch (IOException ex) {
//
//				ex.printStackTrace();
//
//			}
//		}
//      
//      int counter = 0, required_point = 0;
//      int file_year = 0, file_month = 0, file_day = 0;
//      
//      y_m_d = file_date.split("-");
//      file_year = Integer.parseInt(y_m_d[0]);
//      file_month = Integer.parseInt(y_m_d[1]);
//      file_day = Integer.parseInt(y_m_d[2]); 
//      
//      
//          for (int a = file_year; a <= date1_year; a++)
//        {
//            for (int b = 1; b <= 12; b++)
//            {
//                if (counter == 0)
//                    b = file_month;
//
//                for(int c = 1; c <= 31; c++)
//                {
//                    if (counter == 0)
//                    {                      
//                        c = file_day + 1;
//                        counter++;
//                    }
//
//                    if (c > 31)
//                        {
//                            counter++;
//                            continue;
//                        }
//                    /*DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(path));
//                    dataOut.writeBytes(a+"-"+b+"-"+c);*/
//                    
//                    try {
//
//			
//
//			File file = new File(path);
//
//			// if file doesnt exists, then create it
//			if (!file.exists()) {
//                            file.createNewFile();
//			}
//
//			// true = append file
//			fw = new FileWriter(file.getAbsoluteFile(), true);
//			bw = new BufferedWriter(fw);
//
//			bw.write(a+"-"+b+"-"+c);
//		} catch (IOException e) {
//
//			e.printStackTrace();
//
//		} finally {
//
//			try {
//
//				if (bw != null)
//					bw.close();
//
//				if (fw != null)
//					fw.close();
//
//			} catch (IOException ex) {
//
//				ex.printStackTrace();
//
//			}
//		}
//                    
//                    if (b < 10)
//                    {
//                        if (c < 10)
//                            current_date = a+"-0"+b+"-0"+c;
//                        else
//                            current_date = a+"-0"+b+"-"+c;
//                    }
//                    else
//                    {
//                        if (c < 10)
//                            current_date = a+"-"+b+"-0"+c;
//                        else
//                            current_date = a+"-"+b+"-"+c;
//                    }
//                    y_m_d = current_date.split("-");
//                    year = Integer.parseInt(y_m_d[0]);
//                    current_month = Integer.parseInt(y_m_d[1]);
//                    admission_date = Integer.parseInt(y_m_d[2]); 
//
//                    if(a >= date1_year && b >= date1_month && c >= date1_day)
//                    {
//                        required_point++;
//                        break;
//                    }
//                    
//                    try {
//			File file = new File(path);
//
//			// if file doesnt exists, then create it
//			if (!file.exists()) {
//                            file.createNewFile();
//			}
//
//			// true = append file
//			fw = new FileWriter(file.getAbsoluteFile(), true);
//			bw = new BufferedWriter(fw);
//
//                        bw.write("\r\n");
//
//			//System.out.println("Done");
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//
//		} finally {
//
//			try {
//
//				if (bw != null)
//					bw.close();
//
//				if (fw != null)
//					fw.close();
//
//			} catch (IOException ex) {
//
//				ex.printStackTrace();
//
//			}
//		}
//                    
//                    int number_of_students = 0;
//                try {
//                    ps = con.prepareStatement("select COUNT(Roll_No) from Basic_info where DAY(Date_of_admission) = "+admission_date+" and Fee is not null");
//                    rs = ps.executeQuery();
//                    while (rs.next()) {
//                        number_of_students = rs.getInt(1);
//                    }
//
//                } catch (SQLException ex) {
//                    //JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
//                }
//                
//                if(number_of_students == 0)
//                    continue;
//
//                String[][] student_info = new String[number_of_students][8];
//
//                try {
//                    int j = 0;
//                    ps = con.prepareStatement("select Roll_No, MONTH(Date_of_admission), Name, Father_Name, Class, Contact, Address, Fee, Fee_Package, YEAR(Date_of_admission) from Basic_info where DAY(Date_of_admission) = "+admission_date+" and Fee is not null");
//                    rs = ps.executeQuery();
//
//                    while (rs.next()) {
//                        student_info[j][0] = rs.getString(1);
//                        student_info[j][6] = rs.getString(2);
//                        student_info[j][1] = rs.getString(3);
//                        student_info[j][2] = rs.getString(4);
//                        student_info[j][3] = rs.getString(5);
//                        student_info[j][4] = rs.getString(8);
//                        student_info[j][5] = rs.getString(9);
//                        student_info[j][7] = rs.getString(10);
//                        j++;
//                    }
//                } catch (SQLException ex) {
//                    //JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
//                }       
//
//
//                int[] test_info = new int[number_of_students];
//                int[] fee_info = new int[number_of_students];
//
//                if (current_month == 01)
//                {
//                    another_fee.sprevious_month = "12";
//                    another_fee.scurrent_month = "0"+Integer.toString(current_month);
//                    another_fee.sprevious_year = Integer.toString(year - 1);
//                    another_fee.scurrent_year = Integer.toString(year);
//                    another_fee.sprevious_date = another_fee.scurrent_date = Integer.toString(admission_date);
//
//                }
//                else if (current_month == 11 || current_month == 12)
//                {
//                    another_fee.sprevious_month = Integer.toString(current_month - 1);
//                    another_fee.scurrent_month = Integer.toString(current_month);
//                    another_fee.sprevious_year = another_fee.scurrent_year = Integer.toString(year);
//                    another_fee.sprevious_date = another_fee.scurrent_date = Integer.toString(admission_date);
//                }
//                else if (current_month == 10)
//                {
//                    another_fee.sprevious_month = "0"+Integer.toString(current_month - 1);
//                    another_fee.scurrent_month = Integer.toString(current_month);
//                    another_fee.sprevious_year = another_fee.scurrent_year = Integer.toString(year);
//                    another_fee.sprevious_date = another_fee.scurrent_date = Integer.toString(admission_date);
//                }
//                else
//                {
//                    another_fee.sprevious_month = "0"+Integer.toString(current_month - 1);
//                    another_fee.scurrent_month = "0"+Integer.toString(current_month);
//                    another_fee.sprevious_year = another_fee.scurrent_year = Integer.toString(year);
//                    another_fee.sprevious_date = another_fee.scurrent_date = Integer.toString(admission_date);
//                }
//
//
//                for (int i = 0; i < number_of_students; i++)
//                {
//
//
//                    try {                              
//                        ps = con.prepareStatement("select COUNT(Marks) as testPassed from WeeklyTestTable where (Date_of_Entry between '"+another_fee.sprevious_year+"-"+another_fee.sprevious_month+"-"+another_fee.sprevious_date+"' and '"+another_fee.scurrent_year+"-"+another_fee.scurrent_month+"-"+another_fee.scurrent_date+"') and Roll_No="+student_info[i][0]+" and Class='"+student_info[i][3]+"' and(Marks >= 5)");
//                        rs = ps.executeQuery();
//                        while (rs.next()) {
//                        test_info[i] = rs.getInt(1);               
//                        }
//
//                        if (student_info[i][5].equals("Fixed"))
//                        {                   
//                            fee_info[i] = Integer.parseInt(student_info[i][4]);                   
//                        }
//                        else
//                        {                   
//                            fee_info[i] = Integer.parseInt(student_info[i][4]) - (test_info[i] * 50);                   
//                        }
//                    }
//                    catch (SQLException ex) {
//                    //JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
//                    }
//                }
//
//                for (int i = 0; i < number_of_students; i++)
//                {
//                   int month_cur = 0, month_adm = 0, year_cur = 0, year_adm = 0;
//                    try
//                    {
//                        if(another_fee.scurrent_month != null)
//                          month_cur = Integer.parseInt(another_fee.scurrent_month);
//                    }
//                    catch (NumberFormatException e)
//                    {
//                        month_cur = 0;
//                    }
//
//                    try
//                    {
//                        if(student_info[i][6] != null)
//                          month_adm = Integer.parseInt(student_info[i][6]);
//                    }
//                    catch (NumberFormatException e)
//                    {
//                        month_adm = 0;
//                    }
//
//                    try
//                    {
//                        if(another_fee.scurrent_year != null)
//                          year_cur = Integer.parseInt(another_fee.scurrent_year);
//                    }
//                    catch (NumberFormatException e)
//                    {
//                          year_cur = 0;
//                    }
//
//                    try
//                    {
//                        if(student_info[i][7] != null)
//                          year_adm = Integer.parseInt(student_info[i][7]);
//                    }
//                    catch (NumberFormatException e)
//                    {
//                          year_adm = 0;
//                    }
//
//                    // preventing before admission entries 
//                    if((year_cur < year_adm) || (year_cur == year_adm && month_cur < month_adm))
//                        continue; 
//
//                    int repeated_data = 0;       
//                    try {              
//                        ps = con.prepareStatement("select COUNT(Roll_No) from Fee_Info where Roll_No ="+student_info[i][0]+" and Class='"+student_info[i][3]+"' and Date_of_Entry = '"+current_date+"'");
//                        rs = ps.executeQuery();
//                        while (rs.next()) {
//                            repeated_data = rs.getInt(1);
//                        }
//                    }
//                    catch (SQLException ex) {
//                        JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
//                    }
//
//
//                    if (repeated_data == 0)
//                    {
//                        int check_previous_data = -1;
//                        try {              
//                        ps = con.prepareStatement("select count(Roll_No) from Fee_Info where Roll_No ="+student_info[i][0]+" and Class='"+student_info[i][3]+"' and Month(Date_of_Entry) = "+another_fee.scurrent_month+" and Year(Date_of_Entry) = "+another_fee.scurrent_year);
//                        rs = ps.executeQuery();
//                        while (rs.next()) {
//                                check_previous_data = rs.getInt(1);
//                            }
//                        }
//                        catch (SQLException ex) {
//                            JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
//                        }
//
//                        if (check_previous_data == 0)
//                        {
//                            try{
//                            ps = con.prepareStatement("INSERT INTO Fee_Info(Roll_No,Name,Father_Name,Class,Date_of_Entry,Test_Passed,Fee_Package,Actual_Fee,Calculated_Fee,Fee_Paid,Fee_Debt) values("+Integer.parseInt(student_info[i][0])+",'"+student_info[i][1]+"','"+student_info[i][2]+"','"+student_info[i][3]+"','"+current_date+"',"+test_info[i]+",'"+student_info[i][5]+"',"+Integer.parseInt(student_info[i][4])+","+fee_info[i]+",0,"+fee_info[i]+")");
//                            ps.execute();
//                            } catch (SQLException ex) {
//                                //JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
//                            }
//                        }
//                        else if (check_previous_data == 1)
//                        {
//                            int paid_amount = 0;
//
//                            try {
//                            ps = con.prepareStatement("select Fee_Paid from Fee_Info where Roll_No = "+student_info[i][0]+" and Class ='"+student_info[i][3]+"' and Month(Date_of_Entry) = "+another_fee.scurrent_month+" and Year(Date_of_Entry) = "+another_fee.scurrent_year);
//                            rs = ps.executeQuery();
//                            while(rs.next())
//                            {
//                                paid_amount = rs.getInt(1);
//                            }
//
//                            } catch (SQLException ex) {
//                                JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());                   
//                            }
//
//                            try {
//                            ps = con.prepareStatement("update Fee_Info set Date_of_Entry = '"+current_date+"', Test_Passed ="+test_info[i]+", Calculated_Fee = "+fee_info[i]+", Fee_Debt = "+(fee_info[i] - paid_amount)+" where Roll_No = "+student_info[i][0]+" and Class ='"+student_info[i][3]+"' and Month(Date_of_Entry) = "+another_fee.scurrent_month+" and Year(Date_of_Entry) = "+another_fee.scurrent_year);
//                            ps.execute();
//
//                            } catch (SQLException ex) {
//                                JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());                   
//                            }
//                        }
//
//
//                    }           
//                }
//       
//                }
//
//                if(required_point != 0)
//                    break;
//            }
//            if(required_point != 0)
//                    break;
//        }
//      
//          
//         
//        
//        }
//       
    }

    public void AddClass(JComboBox combobox) {

        
        Vector v=new Vector();
                
        combobox.removeAllItems();
        try {
            ps = con.prepareStatement("select distinct Class from Basic_info");
            rs = ps.executeQuery();
          while (rs.next()) {
                String list = rs.getString("Class");
                v.add(list);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(Delete_student, ex.getLocalizedMessage());
        }
        final DefaultComboBoxModel model = new DefaultComboBoxModel(v);
        combobox.setModel(model);
        
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NewAdmission = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        F_name = new javax.swing.JTextField();
        Contact = new javax.swing.JTextField();
        Feepakage = new javax.swing.JComboBox();
        Address = new javax.swing.JTextField();
        Enter = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        Rollno = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        fee = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        Grade = new javax.swing.JComboBox();
        Entrymarkspanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        RollNo1 = new javax.swing.JComboBox();
        Subject1 = new javax.swing.JComboBox();
        Name1 = new javax.swing.JTextField();
        Enter1 = new javax.swing.JButton();
        Marks1 = new javax.swing.JFormattedTextField();
        Grade1 = new javax.swing.JComboBox();
        Monthly_Result = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Monthy_result_table = new javax.swing.JTable();
        Overall_weekly = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Weekly_overall_table = new javax.swing.JTable();
        Monthly_weekly = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Weekly_monthly_table = new javax.swing.JTable();
        Editinfo = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        editName = new javax.swing.JTextField();
        editF_name = new javax.swing.JTextField();
        editContact = new javax.swing.JTextField();
        editFeepakage1 = new javax.swing.JComboBox();
        editAddress = new javax.swing.JTextField();
        editGrade = new javax.swing.JComboBox();
        editEnter = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        editRollno = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        editfee = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        EditMarks = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        editmGrade = new javax.swing.JComboBox();
        editmRollno = new javax.swing.JComboBox();
        editmSubject = new javax.swing.JComboBox();
        editmName = new javax.swing.JTextField();
        editmEdit = new javax.swing.JButton();
        EditmMarks = new javax.swing.JFormattedTextField();
        Date = new com.toedter.calendar.JDateChooser();
        jLabel25 = new javax.swing.JLabel();
        Weeklygraph = new javax.swing.JPanel();
        Overall_Defaulter = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Overall_Defaulter_table = new javax.swing.JTable();
        WeelkysmsPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<String>();
        jScrollPane9 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<String>();
        Bselectone = new javax.swing.JButton();
        Bdiselectone = new javax.swing.JButton();
        Bselectall = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        Tmessage = new javax.swing.JTextArea();
        Bdiselectall = new javax.swing.JButton();
        Bmsg = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        RandomSms = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<String>();
        jScrollPane12 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList<String>();
        Bselectone1 = new javax.swing.JButton();
        Bdiselectone1 = new javax.swing.JButton();
        Bselectall1 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        Tmessage1 = new javax.swing.JTextArea();
        Bdiselectall1 = new javax.swing.JButton();
        Bmsg1 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jProgressBar2 = new javax.swing.JProgressBar();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        Classwise_Defaulter = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Classwise_Defaulter_tablel = new javax.swing.JTable();
        Individual_Defaulter = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        Individual_Defaulter_table = new javax.swing.JTable();
        Throughout_Year = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        Throughout_Year_table = new javax.swing.JTable();
        Fee_Error = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        Fee_Error_table = new javax.swing.JTable();
        New_Class = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        Name2 = new javax.swing.JTextField();
        F_name1 = new javax.swing.JTextField();
        Contact1 = new javax.swing.JTextField();
        Feepakage1 = new javax.swing.JComboBox();
        Address1 = new javax.swing.JTextField();
        Enter2 = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        Rollno1 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        fee1 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        Grade_class = new javax.swing.JTextField();
        MonthlySmsPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jList5 = new javax.swing.JList<String>();
        jScrollPane16 = new javax.swing.JScrollPane();
        jList6 = new javax.swing.JList<String>();
        Bselectone2 = new javax.swing.JButton();
        Bdiselectone2 = new javax.swing.JButton();
        Bselectall2 = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        Tmessage2 = new javax.swing.JTextArea();
        Bdiselectall2 = new javax.swing.JButton();
        Bmsg2 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jProgressBar3 = new javax.swing.JProgressBar();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        unknownMessage = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Bmsg3 = new javax.swing.JButton();
        jLabel62 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jProgressBar4 = new javax.swing.JProgressBar();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        Tmessage3 = new javax.swing.JTextArea();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        MainPanel = new javax.swing.JPanel();
        Tab = new ClosableTabbedPane();
        Menu = new javax.swing.JMenuBar();
        Add = new javax.swing.JMenu();
        Newadd = new javax.swing.JMenuItem();
        MarksEntry = new javax.swing.JMenuItem();
        NewClass = new javax.swing.JMenuItem();
        Edit = new javax.swing.JMenu();
        editinfo = new javax.swing.JMenuItem();
        editmarks = new javax.swing.JMenuItem();
        editclass = new javax.swing.JMenuItem();
        View = new javax.swing.JMenu();
        Result = new javax.swing.JMenu();
        Monthly_result = new javax.swing.JMenuItem();
        Weekly_Result = new javax.swing.JMenu();
        overall = new javax.swing.JMenuItem();
        Monthly = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        Defaulter = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        Print = new javax.swing.JMenu();
        MonthlyResult = new javax.swing.JMenuItem();
        Weekly_Print = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        Delete = new javax.swing.JMenu();
        Delete_student = new javax.swing.JMenuItem();
        Delete_EntryRecord = new javax.swing.JMenuItem();
        Delete_student1 = new javax.swing.JMenuItem();
        Sms = new javax.swing.JMenu();
        Sms_weekly = new javax.swing.JMenuItem();
        Sms_Random = new javax.swing.JMenuItem();
        Sms_Monthly = new javax.swing.JMenuItem();
        NewMessage = new javax.swing.JMenuItem();

        NewAdmission.setBackground(new java.awt.Color(255, 255, 255));
        NewAdmission.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "New Admission", javax.swing.border.TitledBorder.TRAILING, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        NewAdmission.setName("New Admission"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Name:");

        jLabel2.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Father Name:");

        jLabel3.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Class:");

        jLabel4.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Address:");

        jLabel5.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Contact No:");

        jLabel6.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Fee Package:");

        Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NameKeyTyped(evt);
            }
        });

        F_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                F_nameKeyTyped(evt);
            }
        });

        Contact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ContactKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ContactKeyTyped(evt);
            }
        });

        Feepakage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fixed", "Flexible" }));

        Enter.setText("Enter");
        Enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnterActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel7.setText("Roll No:");

        jLabel26.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Fee:");

        jLabel42.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("Date of Admission:");

        Grade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Grade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                GradeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout NewAdmissionLayout = new javax.swing.GroupLayout(NewAdmission);
        NewAdmission.setLayout(NewAdmissionLayout);
        NewAdmissionLayout.setHorizontalGroup(
            NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewAdmissionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NewAdmissionLayout.createSequentialGroup()
                        .addGroup(NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(NewAdmissionLayout.createSequentialGroup()
                                .addComponent(fee, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Feepakage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Address)))
                    .addGroup(NewAdmissionLayout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(NewAdmissionLayout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(31, 31, 31)
                            .addComponent(Grade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(NewAdmissionLayout.createSequentialGroup()
                            .addGroup(NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Enter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(NewAdmissionLayout.createSequentialGroup()
                                    .addGroup(NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(31, 31, 31)
                                    .addGroup(NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Rollno, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(F_name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                                            .addComponent(Contact)
                                            .addComponent(Name)))))
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        NewAdmissionLayout.setVerticalGroup(
            NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewAdmissionLayout.createSequentialGroup()
                .addGroup(NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Rollno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(F_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Feepakage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(fee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(NewAdmissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Grade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Enter, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        NewAdmissionLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel7});

        Entrymarkspanel.setBackground(new java.awt.Color(255, 255, 255));
        Entrymarkspanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Marks Entry", javax.swing.border.TitledBorder.TRAILING, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel8.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel8.setText("Roll Number:");

        jLabel9.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel9.setText("Name:");

        jLabel10.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel10.setText("Subject:");

        jLabel11.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel11.setText("Marks:");

        jLabel12.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel12.setText("Class:");

        RollNo1.setMaximumSize(null);
        RollNo1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                RollNo1ItemStateChanged(evt);
            }
        });

        Subject1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Physics", "Chemistry", "Mathematics", "Biology", "Computer", "Pakistan Studies", "Islamiat", "English", "Sindhi", "Urdu", "ITP", "Accounting", "Economics", "Statistics", "Business Maths", "B.Management", "B.Communication", "E.O.P", "B.Law", "Banking", "Adv.Accounting", "Cost Accounting", "C.Geo", "P.O.C" }));
        Subject1.setMaximumSize(null);
        Subject1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Subject1ActionPerformed(evt);
            }
        });

        Name1.setEditable(false);
        Name1.setBackground(new java.awt.Color(255, 255, 255));
        Name1.setMaximumSize(null);

        Enter1.setText("Enter");
        Enter1.setPreferredSize(new java.awt.Dimension(59, 28));
        Enter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Enter1ActionPerformed(evt);
            }
        });

        Marks1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        Marks1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Marks1ActionPerformed(evt);
            }
        });
        Marks1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Marks1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Marks1KeyTyped(evt);
            }
        });

        Grade1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Grade1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Grade1ItemStateChanged(evt);
            }
        });
        Grade1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Grade1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EntrymarkspanelLayout = new javax.swing.GroupLayout(Entrymarkspanel);
        Entrymarkspanel.setLayout(EntrymarkspanelLayout);
        EntrymarkspanelLayout.setHorizontalGroup(
            EntrymarkspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EntrymarkspanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Grade1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(EntrymarkspanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EntrymarkspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Enter1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(EntrymarkspanelLayout.createSequentialGroup()
                        .addGroup(EntrymarkspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EntrymarkspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RollNo1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Subject1, 0, 264, Short.MAX_VALUE)
                            .addComponent(Name1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Marks1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        EntrymarkspanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Grade1, Marks1, Name1, RollNo1, Subject1});

        EntrymarkspanelLayout.setVerticalGroup(
            EntrymarkspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EntrymarkspanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EntrymarkspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Grade1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EntrymarkspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(RollNo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EntrymarkspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(Name1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EntrymarkspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(Subject1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EntrymarkspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Marks1))
                .addGap(14, 14, 14)
                .addComponent(Enter1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        EntrymarkspanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Grade1, Marks1, Name1, RollNo1, Subject1});

        Monthly_Result.setBackground(new java.awt.Color(255, 255, 255));

        Monthy_result_table.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        Monthy_result_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(Monthy_result_table);

        javax.swing.GroupLayout Monthly_ResultLayout = new javax.swing.GroupLayout(Monthly_Result);
        Monthly_Result.setLayout(Monthly_ResultLayout);
        Monthly_ResultLayout.setHorizontalGroup(
            Monthly_ResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Monthly_ResultLayout.setVerticalGroup(
            Monthly_ResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Monthly_ResultLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        Weekly_overall_table.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        Weekly_overall_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(Weekly_overall_table);

        javax.swing.GroupLayout Overall_weeklyLayout = new javax.swing.GroupLayout(Overall_weekly);
        Overall_weekly.setLayout(Overall_weeklyLayout);
        Overall_weeklyLayout.setHorizontalGroup(
            Overall_weeklyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Overall_weeklyLayout.setVerticalGroup(
            Overall_weeklyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        Weekly_monthly_table.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        Weekly_monthly_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(Weekly_monthly_table);

        javax.swing.GroupLayout Monthly_weeklyLayout = new javax.swing.GroupLayout(Monthly_weekly);
        Monthly_weekly.setLayout(Monthly_weeklyLayout);
        Monthly_weeklyLayout.setHorizontalGroup(
            Monthly_weeklyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Monthly_weeklyLayout.setVerticalGroup(
            Monthly_weeklyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        Editinfo.setBackground(new java.awt.Color(255, 255, 255));
        Editinfo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Edit Information", javax.swing.border.TitledBorder.TRAILING, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel13.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Name:");

        jLabel14.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Father Name:");

        jLabel15.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Class:");

        jLabel16.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Address:");

        jLabel17.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Date Of Admission");

        jLabel18.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel18.setText("Fee Package:");

        editName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editNameActionPerformed(evt);
            }
        });
        editName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                editNameKeyTyped(evt);
            }
        });

        editF_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                editF_nameKeyTyped(evt);
            }
        });

        editContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editContactActionPerformed(evt);
            }
        });
        editContact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                editContactKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                editContactKeyTyped(evt);
            }
        });

        editFeepakage1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fixed", "Flexible" }));

        editGrade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "IX-A", "X-A", "X-B", "XI-A", "XI-B", "XII-A", "XI-Commerce", "XII-Commerce", "B.com-I", "B.com-II" }));
        editGrade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                editGradeItemStateChanged(evt);
            }
        });

        editEnter.setText("Edit");
        editEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editEnterActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel19.setText("Roll No:");

        jLabel27.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Fee:");

        jLabel41.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("Contact No:");

        javax.swing.GroupLayout EditinfoLayout = new javax.swing.GroupLayout(Editinfo);
        Editinfo.setLayout(EditinfoLayout);
        EditinfoLayout.setHorizontalGroup(
            EditinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditinfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EditinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(EditinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(editEnter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(EditinfoLayout.createSequentialGroup()
                            .addGroup(EditinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(EditinfoLayout.createSequentialGroup()
                                    .addGroup(EditinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(EditinfoLayout.createSequentialGroup()
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(22, 22, 22)))
                            .addGroup(EditinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(editRollno, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(EditinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(editF_name)
                                    .addComponent(editName)
                                    .addComponent(editAddress)
                                    .addGroup(EditinfoLayout.createSequentialGroup()
                                        .addComponent(editfee, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(editFeepakage1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(editContact)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editGrade, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        EditinfoLayout.setVerticalGroup(
            EditinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditinfoLayout.createSequentialGroup()
                .addGroup(EditinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(editRollno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(editF_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(editAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editFeepakage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(editfee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(editGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(editEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        EditMarks.setBackground(new java.awt.Color(255, 255, 255));
        EditMarks.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Edit Marks", javax.swing.border.TitledBorder.TRAILING, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel20.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel20.setText("Roll Number:");

        jLabel21.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel21.setText("Name:");

        jLabel22.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel22.setText("Subject:");

        jLabel23.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel23.setText("Marks:");

        jLabel24.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel24.setText("Class:");

        editmGrade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "IX-A", "X-A", "X-B", "XI-A", "XI-B", "XII-A", "XI-Commerce", "XII-Commerce", "B.com-I", "B.com-II" }));
        editmGrade.setMaximumSize(null);
        editmGrade.setMinimumSize(new java.awt.Dimension(50, 25));
        editmGrade.setPreferredSize(new java.awt.Dimension(50, 25));
        editmGrade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                editmGradeItemStateChanged(evt);
            }
        });

        editmRollno.setMaximumSize(null);
        editmRollno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                editmRollnoItemStateChanged(evt);
            }
        });

        editmSubject.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Physics", "Chemistry", "Mathematics", "Biology", "Computer", "Pakistan Studies", "Islamiat", "English", "Sindhi", "Urdu", "ITP", "Accounting", "Economics", "Statistics", "Business Maths", "B.Management", "B.Communication", "E.O.P", "B.Law", "Banking", "Adv.Accounting", "Cost Accounting", "C.Geo", "P.O.C" }));
        editmSubject.setMaximumSize(null);
        editmSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editmSubjectActionPerformed(evt);
            }
        });

        editmName.setEditable(false);
        editmName.setBackground(new java.awt.Color(255, 255, 255));
        editmName.setMaximumSize(null);

        editmEdit.setText("Edit");
        editmEdit.setPreferredSize(new java.awt.Dimension(59, 28));
        editmEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editmEditActionPerformed(evt);
            }
        });

        EditmMarks.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        EditmMarks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EditmMarksKeyTyped(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel25.setText("Date Of Entry:");

        javax.swing.GroupLayout EditMarksLayout = new javax.swing.GroupLayout(EditMarks);
        EditMarks.setLayout(EditMarksLayout);
        EditMarksLayout.setHorizontalGroup(
            EditMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditMarksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EditMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editmEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditMarksLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(EditmMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditMarksLayout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editmSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EditMarksLayout.createSequentialGroup()
                        .addGroup(EditMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(EditMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(editmRollno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editmName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editmGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(EditMarksLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        EditMarksLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Date, EditmMarks, editmName});

        EditMarksLayout.setVerticalGroup(
            EditMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditMarksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EditMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editmGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(editmRollno, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(editmName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editmSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel25)
                    .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditMarksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EditmMarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(editmEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        EditMarksLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Date, EditmMarks, editmName});

        Weeklygraph.setBackground(new java.awt.Color(0, 0, 204));
        Weeklygraph.setName("Weekly Tests Report"); // NOI18N
        Weeklygraph.setPreferredSize(new java.awt.Dimension(500, 500));
        Weeklygraph.setLayout(new java.awt.BorderLayout());

        Overall_Defaulter_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(Overall_Defaulter_table);

        javax.swing.GroupLayout Overall_DefaulterLayout = new javax.swing.GroupLayout(Overall_Defaulter);
        Overall_Defaulter.setLayout(Overall_DefaulterLayout);
        Overall_DefaulterLayout.setHorizontalGroup(
            Overall_DefaulterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
        );
        Overall_DefaulterLayout.setVerticalGroup(
            Overall_DefaulterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );

        WeelkysmsPanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane8.setViewportView(jList1);

        jList2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
        });
        jScrollPane9.setViewportView(jList2);

        Bselectone.setText(">");
        Bselectone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BselectoneActionPerformed(evt);
            }
        });

        Bdiselectone.setText("<");
        Bdiselectone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BdiselectoneActionPerformed(evt);
            }
        });

        Bselectall.setText(">>");
        Bselectall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BselectallActionPerformed(evt);
            }
        });

        Tmessage.setEditable(false);
        Tmessage.setColumns(20);
        Tmessage.setRows(5);
        jScrollPane10.setViewportView(Tmessage);

        Bdiselectall.setText("<<");
        Bdiselectall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BdiselectallActionPerformed(evt);
            }
        });

        Bmsg.setText("Send Messages");
        Bmsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BmsgActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("TOTAL CONTACTS");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("SELECTED CONTACTS");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("Please Wait ");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText(".");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText(".");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setText(".");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Bmsg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane10)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(21, 21, 21)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(Bdiselectone, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Bselectone, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Bselectall, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Bdiselectall, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(Bselectone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(Bdiselectone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(Bselectall, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(Bdiselectall, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Bmsg, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout WeelkysmsPanelLayout = new javax.swing.GroupLayout(WeelkysmsPanel);
        WeelkysmsPanel.setLayout(WeelkysmsPanelLayout);
        WeelkysmsPanelLayout.setHorizontalGroup(
            WeelkysmsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WeelkysmsPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        WeelkysmsPanelLayout.setVerticalGroup(
            WeelkysmsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        RandomSms.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane11.setViewportView(jList3);

        jList4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jList4.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList4MouseClicked(evt);
            }
        });
        jList4.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList4ValueChanged(evt);
            }
        });
        jScrollPane12.setViewportView(jList4);

        Bselectone1.setText(">");
        Bselectone1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bselectone1ActionPerformed(evt);
            }
        });

        Bdiselectone1.setText("<");
        Bdiselectone1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bdiselectone1ActionPerformed(evt);
            }
        });

        Bselectall1.setText(">>");
        Bselectall1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bselectall1ActionPerformed(evt);
            }
        });

        Tmessage1.setColumns(20);
        Tmessage1.setRows(5);
        jScrollPane13.setViewportView(Tmessage1);

        Bdiselectall1.setText("<<");
        Bdiselectall1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bdiselectall1ActionPerformed(evt);
            }
        });

        Bmsg1.setText("Send Messages");
        Bmsg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bmsg1ActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("TOTAL CONTACTS");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("SELECTED CONTACTS");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setText("Please Wait ");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setText(".");

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel38.setText(".");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setText(".");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Bmsg1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel39))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane13)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(21, 21, 21)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(Bdiselectone1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Bselectone1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Bselectall1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Bdiselectall1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(Bselectone1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(Bdiselectone1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(Bselectall1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(Bdiselectall1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Bmsg1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout RandomSmsLayout = new javax.swing.GroupLayout(RandomSms);
        RandomSms.setLayout(RandomSmsLayout);
        RandomSmsLayout.setHorizontalGroup(
            RandomSmsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RandomSmsLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        RandomSmsLayout.setVerticalGroup(
            RandomSmsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Classwise_Defaulter_tablel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(Classwise_Defaulter_tablel);

        javax.swing.GroupLayout Classwise_DefaulterLayout = new javax.swing.GroupLayout(Classwise_Defaulter);
        Classwise_Defaulter.setLayout(Classwise_DefaulterLayout);
        Classwise_DefaulterLayout.setHorizontalGroup(
            Classwise_DefaulterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Classwise_DefaulterLayout.setVerticalGroup(
            Classwise_DefaulterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        Individual_Defaulter_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(Individual_Defaulter_table);

        javax.swing.GroupLayout Individual_DefaulterLayout = new javax.swing.GroupLayout(Individual_Defaulter);
        Individual_Defaulter.setLayout(Individual_DefaulterLayout);
        Individual_DefaulterLayout.setHorizontalGroup(
            Individual_DefaulterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Individual_DefaulterLayout.setVerticalGroup(
            Individual_DefaulterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        Throughout_Year_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(Throughout_Year_table);

        javax.swing.GroupLayout Throughout_YearLayout = new javax.swing.GroupLayout(Throughout_Year);
        Throughout_Year.setLayout(Throughout_YearLayout);
        Throughout_YearLayout.setHorizontalGroup(
            Throughout_YearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Throughout_YearLayout.setVerticalGroup(
            Throughout_YearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        Fee_Error_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane14.setViewportView(Fee_Error_table);

        javax.swing.GroupLayout Fee_ErrorLayout = new javax.swing.GroupLayout(Fee_Error);
        Fee_Error.setLayout(Fee_ErrorLayout);
        Fee_ErrorLayout.setHorizontalGroup(
            Fee_ErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Fee_ErrorLayout.setVerticalGroup(
            Fee_ErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        New_Class.setBackground(new java.awt.Color(255, 255, 255));
        New_Class.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "New Class", javax.swing.border.TitledBorder.TRAILING, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        New_Class.setName("New Class"); // NOI18N

        jLabel40.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setText("Name:");

        jLabel43.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("Father Name:");

        jLabel44.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("Class:");

        jLabel45.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("Address:");

        jLabel46.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("Contact No:");

        jLabel47.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel47.setText("Fee Package:");

        Name2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Name2KeyTyped(evt);
            }
        });

        F_name1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                F_name1KeyTyped(evt);
            }
        });

        Contact1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Contact1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Contact1KeyTyped(evt);
            }
        });

        Feepakage1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fixed", "Flexible" }));

        Enter2.setText("Enter");
        Enter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Enter2ActionPerformed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel48.setText("Roll No:");

        Rollno1.setEditable(false);
        Rollno1.setText("1");

        jLabel49.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel49.setText("Fee:");

        jLabel50.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("Date of Admission:");

        javax.swing.GroupLayout New_ClassLayout = new javax.swing.GroupLayout(New_Class);
        New_Class.setLayout(New_ClassLayout);
        New_ClassLayout.setHorizontalGroup(
            New_ClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(New_ClassLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(New_ClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, New_ClassLayout.createSequentialGroup()
                        .addGroup(New_ClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(New_ClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(New_ClassLayout.createSequentialGroup()
                                .addComponent(fee1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel47)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Feepakage1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Address1)))
                    .addGroup(New_ClassLayout.createSequentialGroup()
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(New_ClassLayout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Grade_class, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(New_ClassLayout.createSequentialGroup()
                        .addGroup(New_ClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Enter2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(New_ClassLayout.createSequentialGroup()
                                .addGroup(New_ClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, New_ClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                        .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(New_ClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Rollno1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(New_ClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(F_name1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                                        .addComponent(Contact1)
                                        .addComponent(Name2)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        New_ClassLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Address1, Grade_class});

        New_ClassLayout.setVerticalGroup(
            New_ClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(New_ClassLayout.createSequentialGroup()
                .addGroup(New_ClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(Rollno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(New_ClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Name2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(New_ClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(F_name1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(New_ClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(Contact1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(New_ClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(New_ClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(Address1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(New_ClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Feepakage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49)
                    .addComponent(fee1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(New_ClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(Grade_class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Enter2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        New_ClassLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Address1, Grade_class});

        MonthlySmsPanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane15.setViewportView(jList5);

        jList6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jList6.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList6MouseClicked(evt);
            }
        });
        jList6.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList6ValueChanged(evt);
            }
        });
        jScrollPane16.setViewportView(jList6);

        Bselectone2.setText(">");
        Bselectone2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bselectone2ActionPerformed(evt);
            }
        });

        Bdiselectone2.setText("<");
        Bdiselectone2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bdiselectone2ActionPerformed(evt);
            }
        });

        Bselectall2.setText(">>");
        Bselectall2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bselectall2ActionPerformed(evt);
            }
        });

        Tmessage2.setEditable(false);
        Tmessage2.setColumns(20);
        Tmessage2.setRows(5);
        jScrollPane17.setViewportView(Tmessage2);

        Bdiselectall2.setText("<<");
        Bdiselectall2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bdiselectall2ActionPerformed(evt);
            }
        });

        Bmsg2.setText("Send Messages");
        Bmsg2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bmsg2ActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("TOTAL CONTACTS");

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("SELECTED CONTACTS");

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel53.setText("Please Wait ");

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel54.setText(".");

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel55.setText(".");

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel56.setText(".");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Bmsg2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel55)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel56))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane17)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(21, 21, 21)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(Bdiselectone2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Bselectone2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Bselectall2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Bdiselectall2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jLabel52))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(Bselectone2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(Bdiselectone2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(Bselectall2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(Bdiselectall2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Bmsg2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54)
                    .addComponent(jLabel55)
                    .addComponent(jLabel56))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MonthlySmsPanelLayout = new javax.swing.GroupLayout(MonthlySmsPanel);
        MonthlySmsPanel.setLayout(MonthlySmsPanelLayout);
        MonthlySmsPanelLayout.setHorizontalGroup(
            MonthlySmsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MonthlySmsPanelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        MonthlySmsPanelLayout.setVerticalGroup(
            MonthlySmsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        unknownMessage.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 637, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        Bmsg3.setText("Send Messages");
        Bmsg3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bmsg3ActionPerformed(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel62.setText(".");

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel59.setText("Please Wait ");

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel60.setText(".");

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel61.setText(".");

        Tmessage3.setColumns(20);
        Tmessage3.setRows(5);
        jScrollPane20.setViewportView(Tmessage3);

        jLabel63.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel63.setText("Message:");

        jLabel64.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel64.setText("Numbers:");

        jLabel65.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel65.setText("Note: Use comma to separate numbers");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextArea1KeyTyped(evt);
            }
        });
        jScrollPane18.setViewportView(jTextArea1);

        javax.swing.GroupLayout unknownMessageLayout = new javax.swing.GroupLayout(unknownMessage);
        unknownMessage.setLayout(unknownMessageLayout);
        unknownMessageLayout.setHorizontalGroup(
            unknownMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, unknownMessageLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(unknownMessageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(unknownMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Bmsg3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(unknownMessageLayout.createSequentialGroup()
                        .addGroup(unknownMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(unknownMessageLayout.createSequentialGroup()
                                .addComponent(jLabel59)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel60)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel61)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel62))
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel64)
                            .addComponent(jLabel63)
                            .addComponent(jLabel65))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        unknownMessageLayout.setVerticalGroup(
            unknownMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(unknownMessageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel65)
                .addGap(34, 34, 34)
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Bmsg3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jProgressBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(unknownMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60)
                    .addComponent(jLabel61)
                    .addComponent(jLabel62))
                .addGap(53, 53, 53)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(" SIR SYED COACHING CENTER");

        MainPanel.setBackground(new java.awt.Color(255, 255, 255));
        MainPanel.setPreferredSize(new java.awt.Dimension(2000, 317));

        Tab.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tab, javax.swing.GroupLayout.DEFAULT_SIZE, 2000, Short.MAX_VALUE)
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
        );

        Add.setBackground(new java.awt.Color(255, 255, 255));
        Add.setText("FILE");
        Add.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        Newadd.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        Newadd.setBackground(new java.awt.Color(255, 255, 255));
        Newadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/newadd.png"))); // NOI18N
        Newadd.setText("New Admission");
        Newadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewaddActionPerformed(evt);
            }
        });
        Add.add(Newadd);

        MarksEntry.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        MarksEntry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/entry.png"))); // NOI18N
        MarksEntry.setText("Marks Entry");
        MarksEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MarksEntryActionPerformed(evt);
            }
        });
        Add.add(MarksEntry);

        NewClass.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        NewClass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/class.png"))); // NOI18N
        NewClass.setText("New Class");
        NewClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewClassActionPerformed(evt);
            }
        });
        Add.add(NewClass);

        Menu.add(Add);

        Edit.setText("EDIT");
        Edit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        editinfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/newadd.png"))); // NOI18N
        editinfo.setText("Student Information");
        editinfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editinfoActionPerformed(evt);
            }
        });
        Edit.add(editinfo);

        editmarks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/entry.png"))); // NOI18N
        editmarks.setText("Entered Marks");
        editmarks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editmarksActionPerformed(evt);
            }
        });
        Edit.add(editmarks);

        editclass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/class.png"))); // NOI18N
        editclass.setText("Edit Class");
        editclass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editclassActionPerformed(evt);
            }
        });
        Edit.add(editclass);

        Menu.add(Edit);

        View.setText("VIEW");
        View.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        Result.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/details.png"))); // NOI18N
        Result.setText("Result");

        Monthly_result.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        Monthly_result.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/details.png"))); // NOI18N
        Monthly_result.setText("Monthly Result");
        Monthly_result.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Monthly_resultActionPerformed(evt);
            }
        });
        Result.add(Monthly_result);

        Weekly_Result.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/details.png"))); // NOI18N
        Weekly_Result.setText("Weekly Result");

        overall.setText("Overall");
        overall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overallActionPerformed(evt);
            }
        });
        Weekly_Result.add(overall);

        Monthly.setText("Monthly");
        Monthly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonthlyActionPerformed(evt);
            }
        });
        Weekly_Result.add(Monthly);

        Result.add(Weekly_Result);

        View.add(Result);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/details.png"))); // NOI18N
        jMenuItem1.setText("Weekly Graph");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        View.add(jMenuItem1);

        Defaulter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/details.png"))); // NOI18N
        Defaulter.setText("List of Defaulters");

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/details.png"))); // NOI18N
        jMenuItem3.setText("Overall");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        Defaulter.add(jMenuItem3);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/details.png"))); // NOI18N
        jMenuItem4.setText("Class Wise");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        Defaulter.add(jMenuItem4);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/details.png"))); // NOI18N
        jMenuItem6.setText("Individual");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        Defaulter.add(jMenuItem6);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/details.png"))); // NOI18N
        jMenuItem7.setText("Throughout Year");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        Defaulter.add(jMenuItem7);

        View.add(Defaulter);

        Menu.add(View);

        Print.setText("PRINT");
        Print.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        MonthlyResult.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/print.png"))); // NOI18N
        MonthlyResult.setText("Monthly Result");
        MonthlyResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonthlyResultActionPerformed(evt);
            }
        });
        Print.add(MonthlyResult);

        Weekly_Print.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        Weekly_Print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/print.png"))); // NOI18N
        Weekly_Print.setText("Weekly Test Result");
        Weekly_Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Weekly_PrintActionPerformed(evt);
            }
        });
        Print.add(Weekly_Print);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/print.png"))); // NOI18N
        jMenuItem2.setText("Fee Reciept");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        Print.add(jMenuItem2);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/print.png"))); // NOI18N
        jMenuItem5.setText("Weekly Test Report");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        Print.add(jMenuItem5);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/print.png"))); // NOI18N
        jMenuItem8.setText("Complete Class Report");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        Print.add(jMenuItem8);

        Menu.add(Print);

        Delete.setText("DELETE");
        Delete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        Delete_student.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/delete.png"))); // NOI18N
        Delete_student.setText("Student");
        Delete_student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_studentActionPerformed(evt);
            }
        });
        Delete.add(Delete_student);

        Delete_EntryRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/delete.png"))); // NOI18N
        Delete_EntryRecord.setText("Entry Record");
        Delete_EntryRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_EntryRecordActionPerformed(evt);
            }
        });
        Delete.add(Delete_EntryRecord);

        Delete_student1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/delete.png"))); // NOI18N
        Delete_student1.setText("Class");
        Delete_student1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_student1ActionPerformed(evt);
            }
        });
        Delete.add(Delete_student1);

        Menu.add(Delete);

        Sms.setText("SMS");
        Sms.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        Sms_weekly.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sms.png"))); // NOI18N
        Sms_weekly.setText("Send Weekly Sms");
        Sms_weekly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sms_weeklyActionPerformed(evt);
            }
        });
        Sms.add(Sms_weekly);

        Sms_Random.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sms.png"))); // NOI18N
        Sms_Random.setText("Random Sms");
        Sms_Random.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sms_RandomActionPerformed(evt);
            }
        });
        Sms.add(Sms_Random);

        Sms_Monthly.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sms.png"))); // NOI18N
        Sms_Monthly.setText("Monthly Result Sms");
        Sms_Monthly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sms_MonthlyActionPerformed(evt);
            }
        });
        Sms.add(Sms_Monthly);

        NewMessage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sms.png"))); // NOI18N
        NewMessage.setText("New Message");
        NewMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewMessageActionPerformed(evt);
            }
        });
        Sms.add(NewMessage);

        Menu.add(Sms);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void NameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NameKeyTyped
        if (Character.isDigit(evt.getKeyChar())) {
            evt.consume();
//            JOptionPane.showMessageDialog(rootPane, "KINDLY SUBMIT VALID DATA");
            Toolkit.getDefaultToolkit().beep();
        }

    }//GEN-LAST:event_NameKeyTyped

    private void F_nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_F_nameKeyTyped
        if (Character.isDigit(evt.getKeyChar())) {
            evt.consume();
//            JOptionPane.showMessageDialog(rootPane, "KINDLY SUBMIT VALID DATA");
            Toolkit.getDefaultToolkit().beep();
        }

    }//GEN-LAST:event_F_nameKeyTyped

    private void ContactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ContactKeyTyped
        if (!(Character.isDigit(evt.getKeyChar()) || evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {

            evt.consume();

        }

    }//GEN-LAST:event_ContactKeyTyped

    private void EnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnterActionPerformed

        String date;
        if (Name.getText().equals("") || F_name.getText().equals("") || Contact.getText().equals("") || Address.getText().equals("") || fee.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Fill Compeletly");
        } else {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            date = ft.format(jDateChooser2.getDate());
            /////////checking whether rollno is already taken or not
            ArrayList<Integer> rollnocheck = new ArrayList<Integer>();
            try {
                ps = con.prepareStatement("select Roll_No from Basic_info where Class='" + Grade.getSelectedItem() + "'");
                rs = ps.executeQuery();
                while (rs.next()) {
                    rollnocheck.add(rs.getInt(1));
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());

            }

            if (rollnocheck.contains(Integer.parseInt(Rollno.getText()))) {

                JOptionPane.showMessageDialog(rootPane, "Roll Number is already taken");
                Rollno.setText(null);
                Rollno.requestFocusInWindow();
            } else {

                try {

                    String query = "INSERT INTO Basic_info (Roll_No,Date_of_admission,Name, Father_Name, Class,Contact,Address,Fee_Package,Fee)"
                            + "VALUES('" + Rollno.getText() + "','" + date + "','" + Name.getText() + "','" + F_name.getText() + "','" + Grade.getSelectedItem() + "','" + Contact.getText() + "','" + Address.getText() + "','" + Feepakage.getSelectedItem() + "'," + fee.getText() + ")";
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());

                }

                JOptionPane.showMessageDialog(rootPane, "Entered Successfully");
                Rollno.setText(null);
                Name.setText(null);
                F_name.setText(null);
                fee.setText(null);
//            Grade.setSelectedIndex(0);
                Contact.setText(null);
                Address.setText(null);
                Feepakage.setSelectedIndex(0);
                GradeItemStateChanged(null);
                Rollno.requestFocusInWindow();

            }
        }
    }//GEN-LAST:event_EnterActionPerformed

    private void NewaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewaddActionPerformed

        Rollno.setText(null);
        Name.setText(null);
        F_name.setText(null);
        Contact.setText(null);
        Address.setText(null);
        Feepakage.setSelectedIndex(0);

        AddClass(Grade);

        int newrollno = 1;
        try {
            ps = con.prepareStatement("select top 1 Roll_No from Basic_info where Class='" + Grade.getSelectedItem() + "' order by Roll_No DESC");
            rs = ps.executeQuery();
            while (rs.next()) {
                newrollno = rs.getInt(1) + 1;
            }
        } catch (SQLException ex) {
        }

        BioPanel.setPreferredSize(Tab.getSize());
        BioPanel.setBackground(Color.WHITE);
        BioPanel.add(NewAdmission);

        BioPanel.setName("New Admission");

        Tab.setVisible(true);
        Rollno.setText(String.valueOf(newrollno));
        Tab.add(BioPanel);
        Tab.setSelectedComponent(BioPanel);
        Icon icon = new ImageIcon(getClass().getResource("/Images/newadd.png"));
        Tab.setIconAt(Tab.getSelectedIndex(), icon);
        Grade.requestFocusInWindow();

    }//GEN-LAST:event_NewaddActionPerformed

    private void MarksEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MarksEntryActionPerformed

        EntryPanel.setPreferredSize(Tab.getSize());
        EntryPanel.setBackground(Color.WHITE);
        EntryPanel.add(Entrymarkspanel);
        EntryPanel.setName("Entry Marks");
        Icon icon = new ImageIcon(getClass().getResource("/Images/entry.png"));

        Tab.setVisible(true);
        Tab.add(EntryPanel);
        Tab.setSelectedComponent(EntryPanel);
        Tab.setIconAt(Tab.getSelectedIndex(), icon);
        AddClass(Grade1);
        RollNo1.removeAllItems();
        try {
            ps = con.prepareStatement("select Roll_No from Basic_info where Class='" + Grade1.getSelectedItem() + "' order by  Roll_No ASC");
            rs = ps.executeQuery();
            while (rs.next()) {

                RollNo1.addItem(rs.getString(1));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_MarksEntryActionPerformed

    private void RollNo1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_RollNo1ItemStateChanged

        Name1.setText(null);
        try {
            ps = con.prepareStatement("select Name from Basic_info where Roll_No='" + RollNo1.getSelectedItem() + "' AND Class='" + Grade1.getSelectedItem() + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                Name1.setText(rs.getString(1));
            }
        } catch (SQLException ex) {

        }
    }//GEN-LAST:event_RollNo1ItemStateChanged

    private void Enter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Enter1ActionPerformed
        if (Name1.getText().equals("") || Marks1.getText().equals("") || Grade1.getItemCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Fill Compeletly");
        } else if (Float.valueOf(Marks1.getText()) > 15) {
            JOptionPane.showMessageDialog(rootPane, "Incorrect Marks");
            Marks1.setText(null);

        } else {

            /////checking whether data of the same data is entered or not before
            ArrayList<String> checkingdate = new ArrayList<String>();
            try {
                ps = con.prepareStatement("select Date_of_Entry from WeeklyTestTable where Class='" + Grade1.getSelectedItem() + "' AND Roll_No=" + RollNo1.getSelectedItem() + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    checkingdate.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
            }
            String date = "";
            try {
                ///geting curent date
                ps = con.prepareStatement("SELECT CONVERT(date, getdate())");
                rs = ps.executeQuery();
                while (rs.next()) {
                    date = rs.getString(1);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
            }
            if (checkingdate.contains(date)) {
                JOptionPane.showMessageDialog(rootPane, "Record Of this Date is already Entered");
                Marks1.setText(null);
                Subject1.requestFocusInWindow();
            } else {

                String query = "INSERT INTO WeeklyTestTable (Roll_No,Date_of_Entry,Name,Class,Marks,Subject)"
                        + "VALUES('" + RollNo1.getSelectedItem() + "',GETDATE(),'" + Name1.getText() + "','" + Grade1.getSelectedItem() + "','" + Marks1.getText() + "','" + Subject1.getSelectedItem() + "')";

                try {
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();
                } catch (SQLException ex) {

                }
                JOptionPane.showMessageDialog(rootPane, "Entered Successfully");
                Marks1.setText(null);
                int index = RollNo1.getSelectedIndex() + 1;

                if ((RollNo1.getItemCount() - 1) >= index) {
                    RollNo1.setSelectedIndex(index);
                }
                Marks1.requestFocusInWindow();
            }
        }

    }//GEN-LAST:event_Enter1ActionPerformed

    private void ContactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ContactKeyPressed
        if (!(Character.isDigit(evt.getKeyChar()) || evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {

            Toolkit.getDefaultToolkit().beep();

//            JOptionPane.showMessageDialog(rootPane, "KINDLY SUBMIT VALID DATA");
        }
    }//GEN-LAST:event_ContactKeyPressed

    private void Marks1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Marks1KeyTyped
        if (Character.isAlphabetic(evt.getKeyChar())) {

            evt.consume();
            Toolkit.getDefaultToolkit().beep();

//            JOptionPane.showMessageDialog(rootPane, "KINDLY SUBMIT VALID DATA");
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Enter1ActionPerformed(null);

        }
    }//GEN-LAST:event_Marks1KeyTyped

    private void MonthlyResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MonthlyResultActionPerformed
        AddClass(result.GradeReslut);
        result.Month_result.removeAllItems();
        try {
            ps = con.prepareStatement("select DISTINCT month(Date_of_Entry),year(Date_of_Entry) from WeeklyTestTable where Class='" + result.GradeReslut.getSelectedItem() + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                result.Month_result.addItem(str[rs.getInt(1) - 1]);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
        }
        center.getscreenCenteredCorner(result);
        result.setVisible(true);
    }//GEN-LAST:event_MonthlyResultActionPerformed

    private void Monthly_resultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Monthly_resultActionPerformed

        View_monthlyresult view = new View_monthlyresult(this, rootPaneCheckingEnabled);
        center.getscreenCenteredCorner(view);
        AddClass(view.GradeReslut_view);
        view.Month_result_view.removeAllItems();
        try {
            ps = con.prepareStatement("select DISTINCT month(Date_of_Entry),year(Date_of_Entry) from WeeklyTestTable where Class='" + view.GradeReslut_view.getSelectedItem() + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                view.Month_result_view.addItem(str[rs.getInt(1) - 1]);
            }
        } catch (SQLException ex) {
        }
        view.setVisible(true);
    }//GEN-LAST:event_Monthly_resultActionPerformed

    private void Weekly_PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Weekly_PrintActionPerformed
        Print_WeeklyResult weeklyprint = new Print_WeeklyResult(this, rootPaneCheckingEnabled);
        center.getscreenCenteredCorner(weeklyprint);
        AddClass(weeklyprint.Grade_weeklyprint);
        weeklyprint.setVisible(true);
    }//GEN-LAST:event_Weekly_PrintActionPerformed

    private void overallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overallActionPerformed
        Viewoverall_weeklyresult overallweekly = new Viewoverall_weeklyresult(this, rootPaneCheckingEnabled);
        center.getscreenCenteredCorner(overallweekly);
        AddClass(overallweekly.Grade1);
        overallweekly.RollNo1.removeAllItems();
        Vector v=new Vector();
        try {
            ps = con.prepareStatement("select [Roll_No] from Basic_info where Class='" + overallweekly.Grade1.getSelectedItem() + "' order by  Roll_No ASC");
            rs = ps.executeQuery();
           while (rs.next()) {
                String list = rs.getString("Roll_No");
                v.add(list);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
        }
        final DefaultComboBoxModel model = new DefaultComboBoxModel(v);
        overallweekly.RollNo1.setModel(model);
        
        overallweekly.Name1.setText(null);
        try {
            ps = con.prepareStatement("select Name from Basic_info where Roll_No='" + overallweekly.RollNo1.getSelectedItem() + "' AND Class='" + overallweekly.Grade1.getSelectedItem() + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                overallweekly.Name1.setText(rs.getString(1));
            }
        } catch (SQLException ex) {

        }
        overallweekly.setVisible(true);
    }//GEN-LAST:event_overallActionPerformed

    private void MonthlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MonthlyActionPerformed
        Viewmonthly_weeklyresult monthlweekly = new Viewmonthly_weeklyresult(this, rootPaneCheckingEnabled);
        center.getscreenCenteredCorner(monthlweekly);
        monthlweekly.Name1.setText(null);
        AddClass(monthlweekly.GradeReslut_view);
        monthlweekly.RollNo1.removeAllItems();
          Vector v=new Vector();
        try {
            ps = con.prepareStatement("select [Roll_No] from Basic_info where Class='" + monthlweekly.GradeReslut_view.getSelectedItem() + "' order by  Roll_No ASC");
            rs = ps.executeQuery();
           while (rs.next()) {
                String list = rs.getString("Roll_No");
                v.add(list);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
        }
        final DefaultComboBoxModel model = new DefaultComboBoxModel(v);
        monthlweekly.RollNo1.setModel(model);
       
        
        
        
        
        
        
        
        try {
            ps = con.prepareStatement("select Name from Basic_info where Roll_No='" + monthlweekly.RollNo1.getSelectedItem() + "' AND Class='" + monthlweekly.GradeReslut_view.getSelectedItem() + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                monthlweekly.Name1.setText(rs.getString(1));
            }
        } catch (SQLException ex) {

        }
        monthlweekly.Weekly_result_view.removeAllItems();
        try {
            ps = con.prepareStatement("select DISTINCT month(Date_of_Entry),year(Date_of_Entry) from WeeklyTestTable where Class='" + monthlweekly.GradeReslut_view.getSelectedItem() + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                monthlweekly.Weekly_result_view.addItem(str[rs.getInt(1) - 1]);
            }
        } catch (SQLException ex) {
        }
        monthlweekly.setVisible(true);
    }//GEN-LAST:event_MonthlyActionPerformed

    private void editNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editNameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_editNameKeyTyped

    private void editF_nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editF_nameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_editF_nameKeyTyped

    private void editContactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editContactKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_editContactKeyPressed

    private void editContactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editContactKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_editContactKeyTyped

    private void editGradeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_editGradeItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_editGradeItemStateChanged

    private void editEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editEnterActionPerformed
        String date;
        if (editName.getText().equals("") || editF_name.getText().equals("") || editContact.getText().equals("") || editAddress.getText().equals("") || editfee.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Fill Compeletly");
        } else {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            date = ft.format(jDateChooser1.getDate());
            /////////checking whether rollno is already taken or not
            ArrayList<Integer> rollnocheck = new ArrayList<Integer>();
            try {
                ps = con.prepareStatement("select Roll_No from Basic_info where Class='" + editGrade.getSelectedItem() + "'");
                rs = ps.executeQuery();
                while (rs.next()) {
                    rollnocheck.add(rs.getInt(1));
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());

            }

            if (rollnocheck.contains(Integer.parseInt(editRollno.getText())) && (!(Editinform.roll_noedit.equals(editRollno.getText())))) {

                JOptionPane.showMessageDialog(rootPane, "Roll Number is already taken");
                editRollno.setText(null);
                editRollno.requestFocusInWindow();
            } else {

                try {
                    String query = "UPDATE Basic_info set Roll_No=" + editRollno.getText() + ", Date_of_admission='" + date + "', Name='" + editName.getText() + "',Father_Name='" + editF_name.getText() + "',Class='" + editGrade.getSelectedItem() + "',Contact=" + editContact.getText() + ",Address='" + editAddress.getText() + "',Fee_Package='" + editFeepakage1.getSelectedItem() + "', Fee=" + editfee.getText() + ""
                            + "Where Class='" + Editinform.gradeedit + "' AND Roll_No=" + Editinform.roll_noedit + "";
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());

                }

                JOptionPane.showMessageDialog(rootPane, "Edited Successfully");
                Tab.remove(Tab.getSelectedComponent());

            }
        }
    }//GEN-LAST:event_editEnterActionPerformed

    private void editinfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editinfoActionPerformed
        Editinform edit = new Editinform(this, rootPaneCheckingEnabled);
        center.getscreenCenteredCorner(edit);
        AddClass(edit.Grade1);
        Vector v=new Vector();
        edit.RollNo1.removeAllItems();
        try {
            ps = con.prepareStatement("select [Roll_No] from Basic_info where Class='" + edit.Grade1.getSelectedItem() + "' order by  Roll_No ASC");
            rs = ps.executeQuery();
            while (rs.next()) {
                String list = rs.getString("Roll_No");
                v.add(list);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(Delete_student, ex.getLocalizedMessage());
        }
        final DefaultComboBoxModel model = new DefaultComboBoxModel(v);
        edit.RollNo1.setModel(model);
        
        
        
        edit.Name1.setText(null);
        try {
            ps = con.prepareStatement("select Name from Basic_info where Roll_No='" + edit.RollNo1.getSelectedItem() + "' AND Class='" + edit.Grade1.getSelectedItem() + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                edit.Name1.setText(rs.getString(1));
            }
        } catch (SQLException ex) {

        }
        edit.setVisible(true);
    }//GEN-LAST:event_editinfoActionPerformed

    private void editmGradeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_editmGradeItemStateChanged
       editmRollno.removeAllItems();
        Vector v = new Vector();
        try {
            ps = con.prepareStatement("select Roll_No from Basic_info where Class='" + editmGrade.getSelectedItem() + "' order by  Roll_No ASC");
            rs = ps.executeQuery();
            while (rs.next()) {
                String list = rs.getString("Roll_No");
                v.add(list);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(Delete_student, ex.getLocalizedMessage());
        }
        final DefaultComboBoxModel model = new DefaultComboBoxModel(v);
        editmRollno.setModel(model);
        editmRollnoItemStateChanged(evt);
    }//GEN-LAST:event_editmGradeItemStateChanged

    private void editmRollnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_editmRollnoItemStateChanged

        editmName.setText(null);
        try {
            ps = con.prepareStatement("select Name from Basic_info where Roll_No='" + editmRollno.getSelectedItem() + "' AND Class='" + editmGrade.getSelectedItem() + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                editmName.setText(rs.getString(1));
            }
        } catch (SQLException ex) {

        }
    }//GEN-LAST:event_editmRollnoItemStateChanged

    private void editmEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editmEditActionPerformed
        if (editmName.getText().equals("") || EditmMarks.getText().equals("") || editmGrade.getItemCount() == 0 || Date.getDate() == null) {
            JOptionPane.showMessageDialog(rootPane, "Fill Compeletly");
        } else if (Float.valueOf(EditmMarks.getText()) > 15) {
            JOptionPane.showMessageDialog(rootPane, "Incorrect Marks");
            EditmMarks.setText(null);

        } else {

            /////checking whether data of the same data is entered or not before
            ArrayList<String> checkingdate = new ArrayList<String>();
            try {
                ps = con.prepareStatement("select Date_of_Entry from WeeklyTestTable where Class='" + editmGrade.getSelectedItem() + "' AND Roll_No=" + editmRollno.getSelectedItem() + "");
                rs = ps.executeQuery();
                while (rs.next()) {
                    checkingdate.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
            }
            String date;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.format(Date.getDate());
            if (!(checkingdate.contains(date))) {
                JOptionPane.showMessageDialog(rootPane, "Record Of this Date is not Entered");
                EditmMarks.setText(null);
                editmSubject.requestFocusInWindow();
            } else {

                String query = "UPDATE WeeklyTestTable set Marks=" + EditmMarks.getText() + ""
                        + "where Class='" + editmGrade.getSelectedItem() + "' AND Roll_No=" + editmRollno.getSelectedItem() + " AND Subject='" + editmSubject.getSelectedItem() + "' AND Date_of_Entry='" + date + "'";

                try {
                    ps = con.prepareStatement(query);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
                }
                JOptionPane.showMessageDialog(rootPane, "Edited Successfully");
                EditmMarks.setText(null);
                editmGrade.requestFocusInWindow();
            }
        }

    }//GEN-LAST:event_editmEditActionPerformed

    private void EditmMarksKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EditmMarksKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_EditmMarksKeyTyped

    private void editmarksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editmarksActionPerformed
        EditMarksPanel.setPreferredSize(Tab.getSize());
        EditMarksPanel.setBackground(Color.WHITE);
        EditMarksPanel.add(EditMarks);
        EditMarksPanel.setName("Edit Marks");
        Icon icon = new ImageIcon(getClass().getResource("/Images/entry.png"));
        AddClass(editmGrade);
        Tab.setVisible(true);
        Tab.add(EditMarksPanel);
        Tab.setSelectedComponent(EditMarksPanel);
        Tab.setIconAt(Tab.getSelectedIndex(), icon);

        editmGradeItemStateChanged(null);
    }//GEN-LAST:event_editmarksActionPerformed

    private void editmSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editmSubjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editmSubjectActionPerformed

    private void Subject1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Subject1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Subject1ActionPerformed

    private void Delete_studentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_studentActionPerformed
        delete_student deletestudent = new delete_student(this, rootPaneCheckingEnabled);
        center.getscreenCenteredCorner(deletestudent);
        AddClass(deletestudent.Grade1);
         Vector v=new Vector();
        try {
            ps = con.prepareStatement("select [Roll_No] from Basic_info where Class='" + deletestudent.Grade1.getSelectedItem() + "' order by  Roll_No ASC");
            rs = ps.executeQuery();
          while (rs.next()) {
                String list = rs.getString("Roll_No");
                v.add(list);
 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
        }
        final DefaultComboBoxModel model = new DefaultComboBoxModel(v);
      deletestudent.RollNo1.setModel(model);
        
        
        deletestudent.Name1.setText(null);
        try {
            ps = con.prepareStatement("select Name from Basic_info where Roll_No='" + deletestudent.RollNo1.getSelectedItem() + "' AND Class='" + deletestudent.Grade1.getSelectedItem() + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                deletestudent.Name1.setText(rs.getString(1));
            }
        } catch (SQLException ex) {

        }
        deletestudent.setVisible(true);
    }//GEN-LAST:event_Delete_studentActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        view_Weeklygraph view = new view_Weeklygraph(this, rootPaneCheckingEnabled);
        center.getscreenCenteredCorner(view);
        AddClass(view.Grade1);
        view.Month_view_weekly.removeAllItems();
        view.RollNo1.removeAllItems();
        Vector v=new Vector();
                
        try {
            ps = con.prepareStatement("select [Roll_No] from Basic_info where Class='" + view.Grade1.getSelectedItem() + "' order by  Roll_No ASC");
            rs = ps.executeQuery();
            while (rs.next()) {
                String list = rs.getString("Roll_No");
                v.add(list);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
        }
        final DefaultComboBoxModel model = new DefaultComboBoxModel(v);
        view.RollNo1.setModel(model);
        
          try {
            ps = con.prepareStatement("select Name from Basic_info where Roll_No='" + view.RollNo1.getSelectedItem() + "' AND Class='" + view.Grade1.getSelectedItem() + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
              view.Name1.setText(rs.getString(1));
            }
        } catch (SQLException ex) {

        }
        
        
        
        
        
        try {
            ps = con.prepareStatement("select DISTINCT month(Date_of_Entry),year(Date_of_Entry) from WeeklyTestTable where Class='" + view.Grade1.getSelectedItem() + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                view.Month_view_weekly.addItem(str[rs.getInt(1) - 1]);
            }
        } catch (SQLException ex) {
        }
        view.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        FeeRecieptFram fee = new FeeRecieptFram(this, rootPaneCheckingEnabled);
        center.getscreenCenteredCorner(fee);
        fee.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Overall_Defaulte overall_defaulter = new Overall_Defaulte(this, rootPaneCheckingEnabled);
        center.getscreenCenteredCorner(overall_defaulter);
        overall_defaulter.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void Sms_weeklyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sms_weeklyActionPerformed
        Tmessage.setText(null);
        jProgressBar1.setVisible(false);
        jLabel30.setVisible(false);
        jLabel31.setVisible(false);
        jLabel32.setVisible(false);
        jLabel33.setVisible(false);
        Weekly_sms weekly_sms = new Weekly_sms(this, rootPaneCheckingEnabled);
        center.getscreenCenteredCorner(weekly_sms);
        AddClass(weekly_sms.jComboBox1);
        weekly_sms.setVisible(true);


    }//GEN-LAST:event_Sms_weeklyActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Classwise_Defaulter classwise_defaulter = new Classwise_Defaulter(this, rootPaneCheckingEnabled);
        center.getscreenCenteredCorner(classwise_defaulter);
        classwise_defaulter.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void BselectoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BselectoneActionPerformed

        Weekly_sms.l2.addElement(jList1.getSelectedValue());
        Weekly_sms.l1.removeElement(jList1.getSelectedValue());
        jList2.setModel(Weekly_sms.l2);


    }//GEN-LAST:event_BselectoneActionPerformed

    private void BdiselectoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BdiselectoneActionPerformed
        Weekly_sms.l1.addElement(jList2.getSelectedValue());
        Weekly_sms.l2.removeElement(jList2.getSelectedValue());
        jList1.setModel(Weekly_sms.l1);

    }//GEN-LAST:event_BdiselectoneActionPerformed

    private void BselectallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BselectallActionPerformed
        int start = 0;
        int end = jList1.getModel().getSize();

        for (int i = 0; i < end; i++) {
            jList1.setSelectedIndex(i);
            Weekly_sms.l2.addElement(jList1.getSelectedValue());
            jList2.setModel(Weekly_sms.l2);
        }
        Weekly_sms.l1.removeAllElements();
        jList1.setModel(Weekly_sms.l1);
    }//GEN-LAST:event_BselectallActionPerformed

    private void BdiselectallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BdiselectallActionPerformed
        int start = 0;
        int end = jList2.getModel().getSize();

        for (int i = 0; i < end; i++) {
            jList2.setSelectedIndex(i);
            Weekly_sms.l1.addElement(jList2.getSelectedValue());
            jList1.setModel(Weekly_sms.l1);
        }
        Weekly_sms.l2.removeAllElements();
        jList2.setModel(Weekly_sms.l2);
    }//GEN-LAST:event_BdiselectallActionPerformed

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged

    }//GEN-LAST:event_jList2ValueChanged
    public static int counter = 0;
    public static Timer time = null;
    private void BmsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BmsgActionPerformed
        jLabel31.setVisible(false);
        jLabel32.setVisible(false);
        jLabel33.setVisible(false);
        jLabel30.setText("Please Wait");
        Bmsg.setEnabled(false);

        jProgressBar1.setVisible(true);
        jLabel30.setVisible(true);
        jProgressBar1.setStringPainted(true);
        jProgressBar1.setString(0 + "/" + Weekly_sms.l2.getSize());
        jProgressBar1.setMaximum(Weekly_sms.l2.getSize());
        jProgressBar1.setMinimum(0);
        jProgressBar1.setValue(0);
        ActionListener myaction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (counter < Weekly_sms.l2.getSize()) {

                    System.out.println(SendMessage(counter));

                    jProgressBar1.setValue(counter + 1);

                    String status = ((counter + 1) + "/" + (Weekly_sms.l2.getSize()));
                    jProgressBar1.setString(status);
                    counter++;

                    if (jLabel31.isVisible()) {
                        jLabel31.setVisible(false);
                        jLabel32.setVisible(false);
                        jLabel33.setVisible(false);
                    } else {
                        jLabel31.setVisible(true);
                        jLabel32.setVisible(true);
                        jLabel33.setVisible(true);
                    }
                } else {
                    jLabel31.setVisible(false);
                    jLabel32.setVisible(false);
                    jLabel33.setVisible(false);
                    jLabel30.setText("Completed");
                    JOptionPane.showMessageDialog(Delete_student, "Messages Sent Successfully");

                    time.stop();
                }
            }
        };

        time = new Timer(2000, myaction);

        time.start();

    }//GEN-LAST:event_BmsgActionPerformed

    public String SendRandomMessage(int counter1) {
        Tmessage1.setEditable(false);
        String response = null;
        boolean isnumeric = true;
        String recipient = null;
        String[] splited = RandomMessage.l2.getElementAt(counter1).toString().split("\\s+");
        try {
            Long.parseLong(splited[2]);

        } catch (NumberFormatException ex) {
            System.out.println(RandomMessage.l2.getSize());
            isnumeric = false;

        }

        if (isnumeric) {
            recipient = splited[2];
        } else {
            recipient = splited[3];
        }

        jList4.setSelectedIndex(counter1);

        try {

            String message = Tmessage1.getText();
            String requestUrl = "http://Lifetimesms.com/plain?"
                    + "username=AsadJivani"
                    + "&password=sesame46"
                    + "&to=" + URLEncoder.encode(recipient, "UTF-8")
                    + "&from=SirSyedEDU"
                    + "&message=" + URLEncoder.encode("SIR SYED COACHING CENTER\n" + message, "UTF-8");

            URL url = new URL(requestUrl);
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
            response = uc.getResponseMessage();
            uc.disconnect();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return response;

    }

    public String SendMessage(int counter) {
        String response = null;
        boolean isnumeric = true;
        String recipient = null;
        String[] splited = Weekly_sms.l2.getElementAt(counter).toString().split("\\s+");
        try {
            Long.parseLong(splited[2]);

        } catch (NumberFormatException ex) {
            System.out.println(Weekly_sms.l2.getSize());
            isnumeric = false;

        }

        if (isnumeric) {
            recipient = splited[2];
        } else {
            recipient = splited[3];
        }

        jList2.setSelectedIndex(counter);
        Tmessage.setText(null);
        Getmessage(splited[0]);

        try {

            String message = Tmessage.getText();
            String requestUrl = "http://Lifetimesms.com/plain?"
                    + "username=Asadjivani"
                    + "&password=sesame46"
                    + "&to=" + URLEncoder.encode(recipient, "UTF-8")
                    + "&from=SirSyedEDU"
                    + "&message=" + URLEncoder.encode("SIR SYED COACHING CENTER\n" + message, "UTF-8");

            URL url = new URL(requestUrl);
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
            response = uc.getResponseMessage();
            uc.disconnect();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return response;
    }
    
    public String unknownMessage(String recipient,String message){
         String response = null;
try {

           
            String requestUrl = "http://Lifetimesms.com/plain?"
                    + "username=Asadjivani"
                    + "&password=sesame46"
                    + "&to=" + URLEncoder.encode(recipient, "UTF-8")
                    + "&from=SirSyedEDU"
                    + "&message=" + URLEncoder.encode("SIR SYED COACHING CENTER\n" + message, "UTF-8");

            URL url = new URL(requestUrl);
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
            response = uc.getResponseMessage();
            uc.disconnect();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return response;
    }
    
    public String sendMonthlyMessage(int counter){
        
        String response = null;
        boolean isnumeric = true;
        String recipient = null;
        String[] splited = Monthly_Sms.l2.getElementAt(counter).toString().split("\\s+");
        try {
            Long.parseLong(splited[2]);

        } catch (NumberFormatException ex) {
            //System.out.println(Weekly_sms.l2.getSize());
            isnumeric = false;

        }

        if (isnumeric) {
            recipient = splited[2];
        } else {
            recipient = splited[3];
        }

        jList6.setSelectedIndex(counter);
        Tmessage2.setText(null);
        GetMonthlyMessage(splited[0]);

        try {

            String message = Tmessage2.getText();
            String requestUrl = "http://Lifetimesms.com/plain?"
                    + "username=Asadjivani"
                    + "&password=sesame46"
                    + "&to=" + URLEncoder.encode(recipient, "UTF-8")
                    + "&from=SirSyedEDU"
                    + "&message=" + URLEncoder.encode("SIR SYED COACHING CENTER\n" + message, "UTF-8");

            URL url = new URL(requestUrl);
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
            response = uc.getResponseMessage();
            uc.disconnect();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

        return response;
    
    }
    
    public void GetMonthlyMessage(String rollno){
        String name = null;
        String marks=null;
        String totalmarks=null;
        String percentage=null;
        String rank=null;
        String overallrank=null;
    
          try {
            ps = con.prepareStatement("select  *  from Monthly_Result where  Roll_No = " + rollno + " and Class = '" + Monthly_Sms.grade + "' and Month = '" + Monthly_Sms.month + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
               name=rs.getString("Name");
               percentage=rs.getString("percentage");
               marks=rs.getString("Marks_obtain");
               totalmarks=rs.getString("Total_marks");
               rank=rs.getString("rank");
               
                
                
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(EntryPanel, ex.getLocalizedMessage());
        }
          
           try {
            ps = con.prepareStatement("select COUNT(*)  from Monthly_Result where  Class = '" + Monthly_Sms.grade + "' and Month = '" + Monthly_Sms.month + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
             overallrank=rs.getString(1);
               
                
                
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(EntryPanel, ex.getLocalizedMessage());
        }
          
       
           Tmessage2.setText("Your ward "+name+" got "+percentage+"% in the month of "+Monthly_Sms.month+". He/She got "+rank+" position out of "+overallrank+" students. He/She got "+marks+" out of "+totalmarks+" in Weekly Tests");
           
    }

    public void Getmessage(String rollno) {

        try {
            ps = con.prepareStatement("select  Subject + ': '+ CONVERT(varchar(10), Marks)  from WeeklyTestTable where  Roll_No = " + rollno + " and Class = '" + Weekly_sms.grade + "' and Date_of_Entry = '" + test1 + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                Tmessage.append(rs.getString(1));
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(EntryPanel, ex.getLocalizedMessage());
        }
        try {
            ps = con.prepareStatement("select  Subject + ': '+ CONVERT(varchar(10), Marks)  from WeeklyTestTable where  Roll_No = " + rollno + " and Class = '" + Weekly_sms.grade + "' and Date_of_Entry = '" + test2 + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                Tmessage.append("\n");
                Tmessage.append(rs.getString(1));
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(EntryPanel, ex.getLocalizedMessage());
        }
        try {
            ps = con.prepareStatement("select  Subject + ': '+ CONVERT(varchar(10), Marks)  from WeeklyTestTable where  Roll_No = " + rollno + " and Class = '" + Weekly_sms.grade + "' and Date_of_Entry = '" + test3 + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                Tmessage.append("\n");
                Tmessage.append(rs.getString(1));
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(EntryPanel, ex.getLocalizedMessage());
        }
        try {
            ps = con.prepareStatement("select  Subject + ': '+ CONVERT(varchar(10), Marks)  from WeeklyTestTable where  Roll_No = " + rollno + " and Class = '" + Weekly_sms.grade + "' and Date_of_Entry = '" + test4 + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                Tmessage.append("\n");
                Tmessage.append(rs.getString(1));
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(EntryPanel, ex.getLocalizedMessage());
        }

    }
    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked

        Tmessage.setText(null);
        String[] splited = jList2.getSelectedValue().split("\\s+");
        Getmessage(splited[0]);


    }//GEN-LAST:event_jList2MouseClicked

    private void jList4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jList4MouseClicked

    private void jList4ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList4ValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jList4ValueChanged

    private void Bselectone1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bselectone1ActionPerformed

        RandomMessage.l2.addElement(jList3.getSelectedValue());
        RandomMessage.l1.removeElement(jList3.getSelectedValue());
        jList4.setModel(RandomMessage.l2);

    }//GEN-LAST:event_Bselectone1ActionPerformed

    private void Bdiselectone1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bdiselectone1ActionPerformed
        RandomMessage.l1.addElement(jList4.getSelectedValue());
        RandomMessage.l2.removeElement(jList4.getSelectedValue());
        jList3.setModel(RandomMessage.l1);
    }//GEN-LAST:event_Bdiselectone1ActionPerformed

    private void Bselectall1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bselectall1ActionPerformed
        int start = 0;
        int end = jList3.getModel().getSize();

        for (int i = 0; i < end; i++) {
            jList3.setSelectedIndex(i);
            RandomMessage.l2.addElement(jList3.getSelectedValue());
            jList4.setModel(RandomMessage.l2);
        }
        RandomMessage.l1.removeAllElements();
        jList3.setModel(RandomMessage.l1);
    }//GEN-LAST:event_Bselectall1ActionPerformed

    private void Bdiselectall1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bdiselectall1ActionPerformed
        int start = 0;
        int end = jList4.getModel().getSize();

        for (int i = 0; i < end; i++) {
            jList4.setSelectedIndex(i);
            RandomMessage.l1.addElement(jList4.getSelectedValue());
            jList3.setModel(RandomMessage.l1);
        }
        RandomMessage.l2.removeAllElements();
        jList4.setModel(RandomMessage.l2);
    }//GEN-LAST:event_Bdiselectall1ActionPerformed

    public static int counter1 = 0;
    public static Timer time1 = null;
    private void Bmsg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bmsg1ActionPerformed

        jLabel37.setVisible(false);
        jLabel38.setVisible(false);
        jLabel39.setVisible(false);
        jLabel36.setText("Please Wait");
        counter1 = 0;
        Bmsg1.setEnabled(false);
        jProgressBar2.setVisible(true);
        jLabel36.setVisible(true);
        jProgressBar2.setStringPainted(true);
        jProgressBar2.setString(0 + "/" + RandomMessage.l2.getSize());
        jProgressBar2.setMaximum(RandomMessage.l2.getSize());
        jProgressBar2.setMinimum(0);
        jProgressBar2.setValue(0);
        ActionListener myaction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (counter1 < RandomMessage.l2.getSize()) {

                    System.out.println(SendRandomMessage(counter1));

                    jProgressBar2.setValue(counter1 + 1);

                    String status = ((counter1 + 1) + "/" + (RandomMessage.l2.getSize()));
                    jProgressBar2.setString(status);
                    counter1++;

                    if (jLabel37.isVisible()) {
                        jLabel37.setVisible(false);
                        jLabel38.setVisible(false);
                        jLabel39.setVisible(false);
                    } else {
                        jLabel37.setVisible(true);
                        jLabel38.setVisible(true);
                        jLabel39.setVisible(true);
                    }
                } else {
                    jLabel37.setVisible(false);
                    jLabel38.setVisible(false);
                    jLabel39.setVisible(false);
                    jLabel36.setText("Completed");

                    JOptionPane.showMessageDialog(rootPane, "Messages Sent Successfully");

                    time1.stop();
                }
            }
        };

        time1 = new Timer(2000, myaction);

        time1.start();

    }//GEN-LAST:event_Bmsg1ActionPerformed

    private void Sms_RandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sms_RandomActionPerformed
        Tmessage1.setText(null);
        jProgressBar2.setVisible(false);
        jLabel36.setVisible(false);
        jLabel37.setVisible(false);
        jLabel38.setVisible(false);
        jLabel39.setVisible(false);
        RandomMessage random_sms = new RandomMessage(this, rootPaneCheckingEnabled);
        center.getscreenCenteredCorner(random_sms);
        AddClass(random_sms.jComboBox1);
        random_sms.setVisible(true);
    }//GEN-LAST:event_Sms_RandomActionPerformed

    private void editContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editContactActionPerformed

    private void editNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editNameActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        PrintView_Weeklyreport weeklyreport = new PrintView_Weeklyreport(this, rootPaneCheckingEnabled);
        center.getscreenCenteredCorner(weeklyreport);
        AddClass(weeklyreport.GradeReslut_view);
        weeklyreport.Name1.setText(null);
        weeklyreport.RollNo1.removeAllItems();
        Vector v=new Vector();
        try {
            ps = con.prepareStatement("select [Roll_No] from Basic_info where Class='" + weeklyreport.GradeReslut_view.getSelectedItem() + "' order by  Roll_No ASC");
            rs = ps.executeQuery();
          while (rs.next()) {
                String list = rs.getString("Roll_No");
                v.add(list);
 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
        }
        final DefaultComboBoxModel model = new DefaultComboBoxModel(v);
        weeklyreport.RollNo1.setModel(model);
       
        try {
            ps = con.prepareStatement("select Name from Basic_info where Roll_No='" + weeklyreport.RollNo1.getSelectedItem() + "' AND Class='" + weeklyreport.GradeReslut_view.getSelectedItem() + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                weeklyreport.Name1.setText(rs.getString(1));
            }
        } catch (SQLException ex) {

        }
        weeklyreport.Weekly_result_view.removeAllItems();
        try {
            ps = con.prepareStatement("select DISTINCT month(Date_of_Entry),year(Date_of_Entry) from WeeklyTestTable where Class='" + weeklyreport.GradeReslut_view.getSelectedItem() + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                weeklyreport.Weekly_result_view.addItem(str[rs.getInt(1) - 1]);
            }
        } catch (SQLException ex) {
        }
        weeklyreport.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void Delete_EntryRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_EntryRecordActionPerformed
        delete_classwise_record deleterec = new delete_classwise_record(this, rootPaneCheckingEnabled);
        center.getscreenCenteredCorner(deleterec);
        AddClass(deleterec.Cclass);
        deleterec.setVisible(true);

    }//GEN-LAST:event_Delete_EntryRecordActionPerformed

    private void Sms_MonthlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sms_MonthlyActionPerformed
        Tmessage2.setText(null);
        jProgressBar3.setVisible(false);
        jLabel53.setVisible(false);
        jLabel54.setVisible(false);
        jLabel55.setVisible(false);
        jLabel56.setVisible(false);
        Monthly_Sms sms=new Monthly_Sms(this, rootPaneCheckingEnabled);
        center.getscreenCenteredCorner(sms);
        AddClass(sms.GradeReslut_view4);
        sms.setVisible(true);
    }//GEN-LAST:event_Sms_MonthlyActionPerformed

    private void Marks1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Marks1ActionPerformed


    }//GEN-LAST:event_Marks1ActionPerformed

    private void Marks1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Marks1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Enter1ActionPerformed(null);

        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            int index = RollNo1.getSelectedIndex() + 1;

            if ((RollNo1.getItemCount() - 1) >= index) {
                RollNo1.setSelectedIndex(index);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            int index = RollNo1.getSelectedIndex() - 1;

            if (0 <= index) {
                RollNo1.setSelectedIndex(index);
            }
        }
    }//GEN-LAST:event_Marks1KeyPressed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        Individual_Defaulter individual_defaulter = new Individual_Defaulter(this, rootPaneCheckingEnabled);
        center.getscreenCenteredCorner(individual_defaulter);
        individual_defaulter.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    public static JPanel Throughout_Year_panel = new JPanel(new GridBagLayout());
    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        Vector<String> header;
        Vector<Vector<String>> total = new Vector<Vector<String>>();

        header = new Vector<String>();
        header.add("Roll No");
        header.add("Name");
        header.add("Father Name");
        header.add("Class");
        header.add("Date");
        header.add("Fee Package");
        header.add("Fee");
        header.add("Fee Paid");
        header.add("Fee Debt");

        try {

            ps = con.prepareStatement("SELECT * FROM Fee_Info where (Calculated_Fee != Fee_Paid and Fee_Debt > 0) order by Date_of_Entry asc, Class asc, Roll_No asc");
            rs = ps.executeQuery();
            while (rs.next()) {
                Vector<String> data = new Vector<String>();
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                data.add(rs.getString(3));
                data.add(rs.getString(4));
                data.add(rs.getString(5));
                data.add(rs.getString(7));
                data.add(rs.getString(9));
                data.add(rs.getString(10));
                data.add(rs.getString(11));
                total.add(data);

                Throughout_Year_table.setModel(new DefaultTableModel(
                        total, header) {
                            public boolean isCellEditable(int row, int col) {
                                return false;
                            }

                        }
                );
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
        }

        Throughout_Year_table.setRowHeight(25);
        Throughout_Year_table.getColumnModel().getColumn(0).setMaxWidth(50);
        Throughout_Year_table.getColumnModel().getColumn(0).setMinWidth(50);
        Throughout_Year_table.getColumnModel().getColumn(1).setMaxWidth(65);
        Throughout_Year_table.getColumnModel().getColumn(1).setMinWidth(65);
        Throughout_Year_table.getColumnModel().getColumn(2).setMaxWidth(140);
        Throughout_Year_table.getColumnModel().getColumn(2).setMinWidth(140);
        Throughout_Year_table.getColumnModel().getColumn(3).setMaxWidth(60);
        Throughout_Year_table.getColumnModel().getColumn(3).setMinWidth(60);
        Throughout_Year_table.getColumnModel().getColumn(4).setMaxWidth(70);
        Throughout_Year_table.getColumnModel().getColumn(4).setMinWidth(70);
        Throughout_Year_table.getColumnModel().getColumn(5).setMaxWidth(80);
        Throughout_Year_table.getColumnModel().getColumn(5).setMinWidth(80);
        Throughout_Year_table.getColumnModel().getColumn(6).setMaxWidth(50);
        Throughout_Year_table.getColumnModel().getColumn(6).setMinWidth(50);
        Throughout_Year_table.getColumnModel().getColumn(7).setMaxWidth(60);
        Throughout_Year_table.getColumnModel().getColumn(7).setMinWidth(60);
        Throughout_Year_table.getColumnModel().getColumn(8).setMaxWidth(60);
        Throughout_Year_table.getColumnModel().getColumn(8).setMinWidth(60);

        Throughout_Year_table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        Throughout_Year_panel.setPreferredSize(Tab.getSize());
        Throughout_Year_panel.setBackground(Color.WHITE);
        Throughout_Year_panel.add(Throughout_Year);
        Throughout_Year_panel.setName("Defaulters Throughout The Year");

        Tab.setVisible(true);
        Tab.add(Throughout_Year_panel);

        Tab.setSelectedComponent(Throughout_Year_panel);
        Icon icon = new ImageIcon(getClass().getResource("/Images/details.png"));
        Tab.setIconAt(Tab.getSelectedIndex(), icon);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        Class_Report report=new Class_Report();
                
        center.getscreenCenteredCorner(report);
        AddClass(report.Grade1);

        report.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void NewClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewClassActionPerformed

        Name2.setText(null);
        F_name1.setText(null);
        Contact1.setText(null);
        Address1.setText(null);
        Feepakage1.setSelectedIndex(0);

        NewClassPanel.setPreferredSize(Tab.getSize());
        NewClassPanel.setBackground(Color.WHITE);
        NewClassPanel.add(New_Class);

        NewClassPanel.setName("New Class");

        Tab.setVisible(true);
        Tab.add(NewClassPanel);
        Tab.setSelectedComponent(NewClassPanel);
        Icon icon = new ImageIcon(getClass().getResource("/Images/class.png"));
        Tab.setIconAt(Tab.getSelectedIndex(), icon);

    }//GEN-LAST:event_NewClassActionPerformed

    private void editclassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editclassActionPerformed
        Edit_Class EditClass = new Edit_Class(this, rootPaneCheckingEnabled);
        center.getscreenCenteredCorner(EditClass);
        AddClass(EditClass.Grade1);

        EditClass.setVisible(true);
    }//GEN-LAST:event_editclassActionPerformed

    private void NewMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewMessageActionPerformed
        jLabel59.setVisible(false);
        jLabel60.setVisible(false);
        jLabel61.setVisible(false);
        jLabel62.setVisible(false);
        time3=null;
        counter3=0;
        New_Sms.setPreferredSize(Tab.getSize());
        New_Sms.setBackground(Color.WHITE);
        New_Sms.add(unknownMessage);

        New_Sms.setName("New Message");

        Tab.setVisible(true);
       
        Tab.add(New_Sms);
        Tab.setSelectedComponent(New_Sms);
        Icon icon = new ImageIcon(getClass().getResource("/Images/sms.png"));
        Tab.setIconAt(Tab.getSelectedIndex(), icon);
        jTextArea1.requestFocusInWindow();
    }//GEN-LAST:event_NewMessageActionPerformed

    private void Delete_student1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_student1ActionPerformed
        Delete_Class DeleteClass = new Delete_Class(this, rootPaneCheckingEnabled);
        center.getscreenCenteredCorner(DeleteClass);
        AddClass(DeleteClass.Grade1);

        DeleteClass.setVisible(true);
    }//GEN-LAST:event_Delete_student1ActionPerformed

    private void Name2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Name2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_Name2KeyTyped

    private void F_name1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_F_name1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_F_name1KeyTyped

    private void Contact1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Contact1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Contact1KeyPressed

    private void Contact1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Contact1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_Contact1KeyTyped

    private void Enter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Enter2ActionPerformed
        String date;
        if (Name2.getText().equals("") || F_name1.getText().equals("") || Contact1.getText().equals("") || Address1.getText().equals("") || fee1.getText().equals("") || Grade_class.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Fill Compeletly");
        } else {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            date = ft.format(jDateChooser3.getDate());

            try {

                String query = "INSERT INTO Basic_info (Roll_No,Date_of_admission,Name, Father_Name, Class,Contact,Address,Fee_Package,Fee)"
                        + "VALUES('" + Rollno1.getText() + "','" + date + "','" + Name2.getText() + "','" + F_name1.getText() + "','" + Grade_class.getText() + "','" + Contact1.getText() + "','" + Address1.getText() + "','" + Feepakage1.getSelectedItem() + "'," + fee1.getText() + ")";
                ps = con.prepareStatement(query);
                ps.executeUpdate();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());

            }

            JOptionPane.showMessageDialog(rootPane, "Entered Successfully");

            Name2.setText(null);
            F_name1.setText(null);
            fee1.setText(null);
//            Grade.setSelectedIndex(0);
            Contact1.setText(null);
            Address1.setText(null);
            Feepakage1.setSelectedIndex(0);
            Grade_class.setText(null);

        }
    }//GEN-LAST:event_Enter2ActionPerformed

    private void Grade1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Grade1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Grade1ActionPerformed

    private void Grade1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Grade1ItemStateChanged
        RollNo1.removeAllItems();
        Vector v = new Vector();
        try {
            ps = con.prepareStatement("select Roll_No from Basic_info where Class='" + Grade1.getSelectedItem() + "' order by  Roll_No ASC");
            rs = ps.executeQuery();
            while (rs.next()) {
                String list = rs.getString("Roll_No");
                v.add(list);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(Delete_student, ex.getLocalizedMessage());
        }
        final DefaultComboBoxModel model = new DefaultComboBoxModel(v);
        RollNo1.setModel(model);
        RollNo1ItemStateChanged(evt);
    }//GEN-LAST:event_Grade1ItemStateChanged

    private void GradeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_GradeItemStateChanged
        int newrollno = 1;
        try {
            ps = con.prepareStatement("select top 1 Roll_No from Basic_info where Class='" + Grade.getSelectedItem() + "' order by Roll_No DESC");
            rs = ps.executeQuery();
            while (rs.next()) {
                newrollno = rs.getInt(1) + 1;
            }
        } catch (SQLException ex) {
        }
        Rollno.setText(String.valueOf(newrollno));

    }//GEN-LAST:event_GradeItemStateChanged

    private void jList6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList6MouseClicked
        Tmessage2.setText(null);
        String[] splited = jList6.getSelectedValue().split("\\s+");
       GetMonthlyMessage(splited[0]);
    }//GEN-LAST:event_jList6MouseClicked

    private void jList6ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList6ValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jList6ValueChanged

    private void Bselectone2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bselectone2ActionPerformed
        Monthly_Sms.l2.addElement(jList5.getSelectedValue());
        Monthly_Sms.l1.removeElement(jList5.getSelectedValue());
        jList6.setModel(Monthly_Sms.l2);
    }//GEN-LAST:event_Bselectone2ActionPerformed

    private void Bdiselectone2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bdiselectone2ActionPerformed
         Monthly_Sms.l1.addElement(jList6.getSelectedValue());
         Monthly_Sms.l2.removeElement(jList6.getSelectedValue());
         jList5.setModel(Monthly_Sms.l1);

    }//GEN-LAST:event_Bdiselectone2ActionPerformed

    private void Bselectall2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bselectall2ActionPerformed
     int start = 0;
        int end = jList5.getModel().getSize();

        for (int i = 0; i < end; i++) {
            jList5.setSelectedIndex(i);
            Monthly_Sms.l2.addElement(jList5.getSelectedValue());
            jList6.setModel(Monthly_Sms.l2);
        }
        Monthly_Sms.l1.removeAllElements();
        jList5.setModel(Monthly_Sms.l1);
    }//GEN-LAST:event_Bselectall2ActionPerformed

    private void Bdiselectall2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bdiselectall2ActionPerformed
         int start = 0;
        int end = jList6.getModel().getSize();

        for (int i = 0; i < end; i++) {
            jList6.setSelectedIndex(i);
            Monthly_Sms.l1.addElement(jList6.getSelectedValue());
            jList5.setModel(Monthly_Sms.l1);
        }
        Monthly_Sms.l2.removeAllElements();
        jList6.setModel(Monthly_Sms.l2);
    }//GEN-LAST:event_Bdiselectall2ActionPerformed
    public static int counter2 = 0;
    public static Timer time2 = null;
    private void Bmsg2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bmsg2ActionPerformed
        jLabel53.setVisible(false);
        jLabel54.setVisible(false);
        jLabel55.setVisible(false);
        jLabel56.setText("Please Wait");
        Bmsg2.setEnabled(false);

        jProgressBar3.setVisible(true);
        jLabel53.setVisible(true);
        jProgressBar3.setStringPainted(true);
        jProgressBar3.setString(0 + "/" + Monthly_Sms.l2.getSize());
        jProgressBar3.setMaximum(Monthly_Sms.l2.getSize());
        jProgressBar3.setMinimum(0);
        jProgressBar3.setValue(0);
        ActionListener myaction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (counter2 < Monthly_Sms.l2.getSize()) {

                    System.out.println(sendMonthlyMessage(counter2));

                    jProgressBar3.setValue(counter2 + 1);

                    String status = ((counter2 + 1) + "/" + (Monthly_Sms.l2.getSize()));
                    jProgressBar3.setString(status);
                    counter2++;

                    if (jLabel53.isVisible()) {
                        jLabel54.setVisible(false);
                        jLabel55.setVisible(false);
                        jLabel56.setVisible(false);
                    } else {
                        jLabel54.setVisible(true);
                        jLabel55.setVisible(true);
                        jLabel56.setVisible(true);
                    }
                } else {
                    jLabel54.setVisible(false);
                    jLabel55.setVisible(false);
                    jLabel56.setVisible(false);
                    jLabel53.setText("Completed");
                    JOptionPane.showMessageDialog(Delete_student, "Messages Sent Successfully");

                    time.stop();
                }
            }
        };

        time = new Timer(2000, myaction);

        time.start();
    }//GEN-LAST:event_Bmsg2ActionPerformed

    public static boolean isNotNullNotEmptyNotWhiteSpaceOnly(final String string)
{
   return string != null && !string.isEmpty() && !string.trim().isEmpty();
}
     public static int counter3 = 0;
    public static Timer time3 = null;
    private void Bmsg3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bmsg3ActionPerformed
      if(!isNotNullNotEmptyNotWhiteSpaceOnly(jTextArea1.getText()) && ! isNotNullNotEmptyNotWhiteSpaceOnly(Tmessage3.getText())){
          JOptionPane.showMessageDialog(Delete_student, "Fill Completely");
      }else{
      
        jLabel59.setVisible(false);
        jLabel60.setVisible(false);
        jLabel61.setVisible(false);
        jLabel62.setText("Please Wait");
        Bmsg3.setEnabled(false);
        jTextArea1.setEnabled(false);
        Tmessage3.setEnabled(false);

        
        String[] numbers=jTextArea1.getText().split(",");
        jProgressBar4.setVisible(true);
        jLabel59.setVisible(true);
        jProgressBar4.setStringPainted(true);
        jProgressBar4.setString(0 + "/" + numbers.length);
        jProgressBar4.setMaximum(numbers.length);
        jProgressBar4.setMinimum(0);
        jProgressBar4.setValue(0);
        ActionListener myaction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (counter3 < numbers.length) {

                    unknownMessage(numbers[counter3], Tmessage3.getText());

                    jProgressBar4.setValue(counter3 + 1);

                    String status = ((counter3 + 1) + "/" + (numbers.length));
                    jProgressBar4.setString(status);
                    counter3++;

                    if (jLabel59.isVisible()) {
                        jLabel60.setVisible(false);
                        jLabel61.setVisible(false);
                        jLabel62.setVisible(false);
                    } else {
                        jLabel60.setVisible(true);
                        jLabel61.setVisible(true);
                        jLabel62.setVisible(true);
                    }
                } else {
                    jLabel60.setVisible(false);
                    jLabel61.setVisible(false);
                    jLabel62.setVisible(false);
                    jLabel59.setText("Completed");
                    JOptionPane.showMessageDialog(Delete_student, "Messages Sent Successfully");

                    time.stop();
                }
            }
        };

        time = new Timer(2000, myaction);

        time.start();
      
      
      
      
      
      
      
      
      }
      
    }//GEN-LAST:event_Bmsg3ActionPerformed

    private void jTextArea1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyTyped
        if ((Character.isAlphabetic(evt.getKeyChar()) || evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {

            Toolkit.getDefaultToolkit().beep();

//            JOptionPane.showMessageDialog(rootPane, "KINDLY SUBMIT VALID DATA");
        }
    }//GEN-LAST:event_jTextArea1KeyTyped

    public static JPanel Fee_Error_panel = new JPanel(new GridBagLayout());

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Add;
    private javax.swing.JTextField Address;
    private javax.swing.JTextField Address1;
    public static javax.swing.JButton Bdiselectall;
    public static javax.swing.JButton Bdiselectall1;
    public static javax.swing.JButton Bdiselectall2;
    public static javax.swing.JButton Bdiselectone;
    public static javax.swing.JButton Bdiselectone1;
    public static javax.swing.JButton Bdiselectone2;
    public static javax.swing.JButton Bmsg;
    public static javax.swing.JButton Bmsg1;
    public static javax.swing.JButton Bmsg2;
    public static javax.swing.JButton Bmsg3;
    public static javax.swing.JButton Bselectall;
    public static javax.swing.JButton Bselectall1;
    public static javax.swing.JButton Bselectall2;
    public static javax.swing.JButton Bselectone;
    public static javax.swing.JButton Bselectone1;
    public static javax.swing.JButton Bselectone2;
    public static javax.swing.JPanel Classwise_Defaulter;
    public static javax.swing.JTable Classwise_Defaulter_tablel;
    private javax.swing.JTextField Contact;
    private javax.swing.JTextField Contact1;
    private com.toedter.calendar.JDateChooser Date;
    private javax.swing.JMenu Defaulter;
    private javax.swing.JMenu Delete;
    private javax.swing.JMenuItem Delete_EntryRecord;
    private javax.swing.JMenuItem Delete_student;
    private javax.swing.JMenuItem Delete_student1;
    private javax.swing.JMenu Edit;
    private javax.swing.JPanel EditMarks;
    public static javax.swing.JPanel Editinfo;
    private javax.swing.JFormattedTextField EditmMarks;
    private javax.swing.JButton Enter;
    private javax.swing.JButton Enter1;
    private javax.swing.JButton Enter2;
    private javax.swing.JPanel Entrymarkspanel;
    private javax.swing.JTextField F_name;
    private javax.swing.JTextField F_name1;
    public static javax.swing.JPanel Fee_Error;
    public static javax.swing.JTable Fee_Error_table;
    private javax.swing.JComboBox Feepakage;
    private javax.swing.JComboBox Feepakage1;
    private javax.swing.JComboBox Grade;
    private javax.swing.JComboBox Grade1;
    private javax.swing.JTextField Grade_class;
    public static javax.swing.JPanel Individual_Defaulter;
    public static javax.swing.JTable Individual_Defaulter_table;
    public static javax.swing.JPanel MainPanel;
    private javax.swing.JFormattedTextField Marks1;
    private javax.swing.JMenuItem MarksEntry;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenuItem Monthly;
    private javax.swing.JMenuItem MonthlyResult;
    public static javax.swing.JPanel MonthlySmsPanel;
    public static javax.swing.JPanel Monthly_Result;
    private javax.swing.JMenuItem Monthly_result;
    public static javax.swing.JPanel Monthly_weekly;
    public static javax.swing.JTable Monthy_result_table;
    private javax.swing.JTextField Name;
    private javax.swing.JTextField Name1;
    private javax.swing.JTextField Name2;
    private javax.swing.JPanel NewAdmission;
    private javax.swing.JMenuItem NewClass;
    private javax.swing.JMenuItem NewMessage;
    private javax.swing.JPanel New_Class;
    private javax.swing.JMenuItem Newadd;
    public static javax.swing.JPanel Overall_Defaulter;
    public static javax.swing.JTable Overall_Defaulter_table;
    public static javax.swing.JPanel Overall_weekly;
    private javax.swing.JMenu Print;
    public static javax.swing.JPanel RandomSms;
    private javax.swing.JMenu Result;
    private javax.swing.JComboBox RollNo1;
    private javax.swing.JTextField Rollno;
    private javax.swing.JTextField Rollno1;
    private javax.swing.JMenu Sms;
    private javax.swing.JMenuItem Sms_Monthly;
    private javax.swing.JMenuItem Sms_Random;
    private javax.swing.JMenuItem Sms_weekly;
    private javax.swing.JComboBox Subject1;
    public static javax.swing.JTabbedPane Tab;
    public static javax.swing.JPanel Throughout_Year;
    public static javax.swing.JTable Throughout_Year_table;
    public static javax.swing.JTextArea Tmessage;
    public static javax.swing.JTextArea Tmessage1;
    public static javax.swing.JTextArea Tmessage2;
    public static javax.swing.JTextArea Tmessage3;
    private javax.swing.JMenu View;
    private javax.swing.JMenuItem Weekly_Print;
    private javax.swing.JMenu Weekly_Result;
    public static javax.swing.JTable Weekly_monthly_table;
    public static javax.swing.JTable Weekly_overall_table;
    public static javax.swing.JPanel Weeklygraph;
    public static javax.swing.JPanel WeelkysmsPanel;
    public static javax.swing.JTextField editAddress;
    public static javax.swing.JTextField editContact;
    private javax.swing.JButton editEnter;
    public static javax.swing.JTextField editF_name;
    public static javax.swing.JComboBox editFeepakage1;
    public static javax.swing.JComboBox editGrade;
    public static javax.swing.JTextField editName;
    public static javax.swing.JTextField editRollno;
    private javax.swing.JMenuItem editclass;
    public static javax.swing.JTextField editfee;
    private javax.swing.JMenuItem editinfo;
    private javax.swing.JButton editmEdit;
    private javax.swing.JComboBox editmGrade;
    private javax.swing.JTextField editmName;
    private javax.swing.JComboBox editmRollno;
    private javax.swing.JComboBox editmSubject;
    private javax.swing.JMenuItem editmarks;
    private javax.swing.JTextField fee;
    private javax.swing.JTextField fee1;
    public static com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JList<String> jList1;
    public static javax.swing.JList<String> jList2;
    public static javax.swing.JList<String> jList3;
    public static javax.swing.JList<String> jList4;
    public static javax.swing.JList<String> jList5;
    public static javax.swing.JList<String> jList6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    public static javax.swing.JPanel jPanel3;
    public static javax.swing.JPanel jPanel4;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane10;
    public static javax.swing.JScrollPane jScrollPane11;
    public static javax.swing.JScrollPane jScrollPane12;
    public static javax.swing.JScrollPane jScrollPane13;
    public static javax.swing.JScrollPane jScrollPane14;
    public static javax.swing.JScrollPane jScrollPane15;
    public static javax.swing.JScrollPane jScrollPane16;
    public static javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    public static javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JScrollPane jScrollPane6;
    public static javax.swing.JScrollPane jScrollPane7;
    public static javax.swing.JScrollPane jScrollPane8;
    public static javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuItem overall;
    public static javax.swing.JPanel unknownMessage;
    // End of variables declaration//GEN-END:variables

}
