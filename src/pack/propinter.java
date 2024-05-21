package pack;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.JScrollPane;


public class propinter extends JFrame {
	private JTextField textField;
	 private JTextField SUPERPROP;
	    private JTextField PRIXPROP;
	    private JTextField DESCPROP;
	    private JTextField LOCAPROP;
	    private JTable table;


	    private Connection connection;
	    private Statement statement;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					propinter window = new propinter();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public propinter() {
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
        
        
        setTitle("Client Interface");
        setSize(1343, 737);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("PROPRIETES");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(557, 11, 127, 46);
        getContentPane().add(lblNewLabel);
        
        SUPERPROP = new JTextField();
        SUPERPROP.setFont(new Font("Tahoma", Font.PLAIN, 16));
        SUPERPROP.setBounds(231, 77, 286, 46);
        getContentPane().add(SUPERPROP);
        SUPERPROP.setColumns(10);
        
        PRIXPROP = new JTextField();
        PRIXPROP.setFont(new Font("Tahoma", Font.PLAIN, 16));
        PRIXPROP.setColumns(10);
        PRIXPROP.setBounds(231, 154, 286, 46);
        getContentPane().add(PRIXPROP);
        
        DESCPROP = new JTextField();
        DESCPROP.setFont(new Font("Tahoma", Font.PLAIN, 16));
        DESCPROP.setColumns(10);
        DESCPROP.setBounds(231, 308, 286, 46);
        getContentPane().add(DESCPROP);
        
        JLabel lblNom = new JLabel("SUPERFICIE :");
        lblNom.setHorizontalAlignment(SwingConstants.LEFT);
        lblNom.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNom.setBounds(59, 75, 127, 46);
        getContentPane().add(lblNom);
        
        JLabel lblPrenom = new JLabel("PRIX :");
        lblPrenom.setHorizontalAlignment(SwingConstants.LEFT);
        lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPrenom.setBounds(59, 152, 127, 46);
        getContentPane().add(lblPrenom);
        
        JLabel lblAdresse = new JLabel("DESCRIPTION :");
        lblAdresse.setHorizontalAlignment(SwingConstants.LEFT);
        lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblAdresse.setBounds(59, 306, 149, 46);
        getContentPane().add(lblAdresse);
        
        JLabel lblType = new JLabel("TYPE :");
        lblType.setHorizontalAlignment(SwingConstants.LEFT);
        lblType.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblType.setBounds(59, 389, 127, 46);
        getContentPane().add(lblType);
        
        JRadioButton rdbtnappar = new JRadioButton("APPARTEMENT");
        rdbtnappar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnappar.setBounds(231, 403, 140, 23);
        getContentPane().add(rdbtnappar);

        JRadioButton rdbtnmaison = new JRadioButton("MAISON");
        rdbtnmaison.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnmaison.setBounds(390, 403, 127, 23);
        getContentPane().add(rdbtnmaison);
        
        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnappar);
        group.add(rdbtnmaison);
        
        String typeclient = null;
        JButton AJTPROP = new JButton("AJOUTER");
        AJTPROP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    
                    ResultSet rs = statement.executeQuery("SELECT id_seq.NEXTVAL FROM DUAL");
                    int ID = 0;
                    if (rs.next()) {
                        ID = rs.getInt(1);
                    }
                    
                    String supertext = SUPERPROP.getText();
                    double superficie = Double.parseDouble(supertext);
                    String prixtext = PRIXPROP.getText();
                    double prix = Double.parseDouble(prixtext);
                    String description = DESCPROP.getText();
                    String localisation = LOCAPROP.getText();
                    
                    String typeprop = null;
                    if (rdbtnappar.isSelected()) { typeprop ="APPARTEMENT";}
                    if (rdbtnmaison.isSelected()) { typeprop ="MAISON";}
                  
                    
                    // SQL
                    String query = "INSERT INTO PROPRIETE VALUES ('"+ID+"','"+superficie+"','"+prix+"','"+localisation+"','"+description+"','"+typeprop+"')";
                    
                    statement.execute(query);
                    JOptionPane.showMessageDialog(AJTPROP, "bien insere!");
                    
                } catch (Exception e1) {
                    e1.printStackTrace();
                }   
            }
        });
        
        AJTPROP.setFont(new Font("Tahoma", Font.PLAIN, 16));
        AJTPROP.setBounds(138, 469, 123, 46);
        getContentPane().add(AJTPROP);
        
        LOCAPROP = new JTextField();
        LOCAPROP.setFont(new Font("Tahoma", Font.PLAIN, 16));
        LOCAPROP.setColumns(10);
        LOCAPROP.setBounds(231, 228, 286, 46);
        getContentPane().add(LOCAPROP);
        
        JLabel lblEmail = new JLabel("LOCALISATION :");
        lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEmail.setBounds(59, 226, 149, 46);
        getContentPane().add(lblEmail);
        
        JButton clearButton = new JButton("New Prop");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Clear the text in each JTextField
                SUPERPROP.setText("");
                PRIXPROP.setText("");
                DESCPROP.setText("");
                LOCAPROP.setText("");
                
                // Deselect radio buttons
                group.clearSelection();
            }
        });
        clearButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        clearButton.setBounds(234, 547, 123, 46);
        getContentPane().add(clearButton);

        
        JButton AFFICHCLI = new JButton("AFFICHER PROPRIETEES");
        AFFICHCLI.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String sql="SELECT * FROM PROPRIETE";
        		
        		try {
					statement = connection.createStatement();
					ResultSet rs = statement.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		
        	}
        });
        AFFICHCLI.setFont(new Font("Tahoma", Font.PLAIN, 16));
        AFFICHCLI.setBounds(319, 469, 175, 46);
        getContentPane().add(AFFICHCLI);
        
        JScrollPane tabcli = new JScrollPane();
        tabcli.setBounds(565, 95, 691, 326);
        getContentPane().add(tabcli);
        
        table = new JTable();
        tabcli.setViewportView(table);
        
        // Create a JTextField for search queries
        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchField.setBounds(821, 21, 200, 30);
        getContentPane().add(searchField);

        // Create a JComboBox for selecting search options
        String[] searchOptions = {"localisation", "superficie","Type prop"};
        JComboBox<String> searchOptionsComboBox = new JComboBox<>(searchOptions);
        searchOptionsComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchOptionsComboBox.setBounds(694, 21, 84, 30);
        getContentPane().add(searchOptionsComboBox);

        // Create a JButton for triggering the search operation
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchButton.setBounds(1021, 21, 100, 30);
        getContentPane().add(searchButton);

        // Add ActionListener for the search button
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the search query and selected search option
                String query = searchField.getText().trim();
                String selectedOption = (String) searchOptionsComboBox.getSelectedItem();

                // Construct the SQL query based on the selected search option
                String sql = "";
                switch (selectedOption) {
                    case "localisation":
                        sql = "SELECT * FROM PROPRIETE WHERE localisation LIKE '%" + query + "%'";
                        break;
                    case "superficie":
                        sql = "SELECT * FROM PROPRIETE WHERE superficie LIKE '%" + query + "%'";
                        break;
                    case "Type prop":
                        sql = "SELECT * FROM PROPRIETE WHERE LOWER(type_prop) LIKE '%" + query + "%'";
                        break;
                    
                    default:
                        break;
                }

                // Perform the search operation in the database
                try {
                    statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(sql);

                    // Display search results in the table
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
