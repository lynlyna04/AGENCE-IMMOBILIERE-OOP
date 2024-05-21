package app;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.sql.*;
import app.transinter;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class interface1 {

    private JFrame frame;
    
    private Connection connection;
    private Statement statement;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    interface1 window = new interface1();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public interface1() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
    	
    	
    	 try {
             
             Class.forName("oracle.jdbc.driver.OracleDriver");
             
             connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","ouabel","lyna");
             
         } catch (Exception e) {
             e.printStackTrace();
         }
    	 
    	 
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setBounds(100, 100, 1334, 708);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton newclientbtn = new JButton("NEW CLIENT");
        newclientbtn.setForeground(Color.BLACK);
        newclientbtn.setBackground(Color.WHITE);
        newclientbtn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        newclientbtn.setBorder(new LineBorder(Color.WHITE));
        newclientbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openClientWindow(); // Call method to open client window
            }
        });
        newclientbtn.setBounds(224, 213, 208, 50);
        frame.getContentPane().add(newclientbtn);

        JLabel lblNewLabel = new JLabel("WELCOME");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(552, 64, 180, 57);
        frame.getContentPane().add(lblNewLabel);

        JButton newprop = new JButton("NEW PROPERTY");
        newprop.setBackground(Color.WHITE);
        newprop.setBorder(new LineBorder(Color.WHITE));
        newprop.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		openpropwindow();
        	}
        });
        newprop.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        newprop.setBounds(224, 390, 208, 50);
        frame.getContentPane().add(newprop);

        JButton newtrans = new JButton("NEW TRANSACTION");
        newtrans.setBackground(Color.WHITE);
        newtrans.setBorder(new LineBorder(Color.WHITE));
        newtrans.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		opentranswindow();
        	}
        });
        newtrans.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        newtrans.setBounds(824, 213, 208, 50);
        frame.getContentPane().add(newtrans);
        
        JButton newagent = new JButton("NEW AGENT");
        newagent.setBackground(Color.WHITE);
        newagent.setBorder(new LineBorder(Color.WHITE));
        newagent.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		openagentwindow();
        	}
        });
        newagent.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        newagent.setBounds(530, 300, 208, 50);
        frame.getContentPane().add(newagent);

        JButton newrdv = new JButton("NEW RDV");
        newrdv.setBackground(Color.WHITE);
        newrdv.setBorder(new LineBorder(Color.WHITE));
        newrdv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openrdvwindow();
            }
        });
        newrdv.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        newrdv.setBounds(824, 390, 208, 50);
        frame.getContentPane().add(newrdv);
        frame.getContentPane().setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\kooki\\Downloads\\Untitled design (2).png"));
        lblNewLabel_1.setBounds(0, 0, 1283, 708);
        frame.getContentPane().add(lblNewLabel_1);
    }

    // Method to open the client window
    private void openClientWindow() {
        clientinter clientWindow = new clientinter(); // Create instance of client window
        clientWindow.setVisible(true); // Make client window visible
    }
    
    //method to open new property window
    private void openpropwindow() {
    	propinter propwindow = new propinter();
    	propwindow.setVisible(true);
    }
    
    //method to open new rdv window 
    private void openrdvwindow() {
    	rdvinter rdvwindow = new rdvinter();
    	rdvwindow.setVisible(true);
    }
    
    //moethd to open new trans window
    
    private void opentranswindow() {
    	transinter transwindow = new transinter();
    	transwindow.setVisible(true);
    }
    
    private void openagentwindow() {
    	agentinter agentwindow = new agentinter();
    	agentwindow.setVisible(true);
    }
}
