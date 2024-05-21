package pack;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class interface1 {

    private JFrame frame;

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
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setBounds(100, 100, 1334, 708);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton newclientbtn = new JButton("NEW CLIENT");
        newclientbtn.setBackground(Color.WHITE);
        newclientbtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
        newclientbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openClientWindow(); // Call method to open client window
            }
        });
        newclientbtn.setBounds(224, 189, 208, 50);
        frame.getContentPane().add(newclientbtn);

        JLabel lblNewLabel = new JLabel("WELCOME");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(558, 66, 153, 41);
        frame.getContentPane().add(lblNewLabel);

        JButton newprop = new JButton("NEW PROPERTY");
        newprop.setBackground(Color.WHITE);
        newprop.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		openpropwindow();
        	}
        });
        newprop.setFont(new Font("Tahoma", Font.PLAIN, 15));
        newprop.setBounds(224, 356, 208, 50);
        frame.getContentPane().add(newprop);

        JButton newtrans = new JButton("NEW TRANSACTION");
        newtrans.setBackground(Color.WHITE);
        newtrans.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		opentranswindow();
        	}
        });
        newtrans.setFont(new Font("Tahoma", Font.PLAIN, 15));
        newtrans.setBounds(824, 189, 208, 50);
        frame.getContentPane().add(newtrans);
        
        JButton newagent = new JButton("NEW AGENT");
        newagent.setBackground(Color.WHITE);
        newagent.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		openagentwindow();
        	}
        });
        newagent.setFont(new Font("Tahoma", Font.PLAIN, 15));
        newagent.setBounds(530, 279, 208, 50);
        frame.getContentPane().add(newagent);

        JButton newrdv = new JButton("NEW RDV");
        newrdv.setBackground(Color.WHITE);
        newrdv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openrdvwindow();
            }
        });
        newrdv.setFont(new Font("Tahoma", Font.PLAIN, 15));
        newrdv.setBounds(824, 356, 208, 50);
        frame.getContentPane().add(newrdv);
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
