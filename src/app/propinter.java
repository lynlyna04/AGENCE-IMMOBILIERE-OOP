package app;

import java.awt.Color;
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
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;


public class propinter extends JFrame {
	private JTextField textField;
	 private JTextField SUPERPROP;
	    private JTextField PRIXPROP;
	    private JTextField DESCPROP;
	    private JTextField LOCAPROP;
	    private JTable table;
	    private int propid;


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
        lblNewLabel.setForeground(Color.WHITE);
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
        lblNom.setForeground(Color.WHITE);
        lblNom.setHorizontalAlignment(SwingConstants.LEFT);
        lblNom.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNom.setBounds(59, 75, 127, 46);
        getContentPane().add(lblNom);
        
        JLabel lblPrenom = new JLabel("PRIX :");
        lblPrenom.setForeground(Color.WHITE);
        lblPrenom.setHorizontalAlignment(SwingConstants.LEFT);
        lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPrenom.setBounds(59, 152, 127, 46);
        getContentPane().add(lblPrenom);
        
        JLabel lblAdresse = new JLabel("DESCRIPTION :");
        lblAdresse.setForeground(Color.WHITE);
        lblAdresse.setHorizontalAlignment(SwingConstants.LEFT);
        lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblAdresse.setBounds(59, 306, 149, 46);
        getContentPane().add(lblAdresse);
        
        JLabel lblType = new JLabel("TYPE :");
        lblType.setForeground(Color.WHITE);
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
        AJTPROP.setBackground(Color.WHITE);
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
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEmail.setBounds(59, 226, 149, 46);
        getContentPane().add(lblEmail);
        
        JButton clearButton = new JButton("New Prop");
        clearButton.setBackground(Color.WHITE);
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
        clearButton.setBounds(231, 547, 123, 46);
        getContentPane().add(clearButton);

        
        JButton AFFICHCLI = new JButton("AFFICHER PROPRIETEES");
        AFFICHCLI.setBackground(Color.WHITE);
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
        AFFICHCLI.setBounds(319, 469, 215, 46);
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
        searchOptionsComboBox.setBackground(Color.WHITE);
        searchOptionsComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchOptionsComboBox.setBounds(694, 21, 84, 30);
        getContentPane().add(searchOptionsComboBox);

        // Create a JButton for triggering the search operation
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.WHITE);
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
        
     // Create Delete button
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.WHITE);
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        deleteButton.setBounds(715, 469, 125, 46);
        getContentPane().add(deleteButton);

        
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                	BigDecimal idValue = (BigDecimal) table.getValueAt(selectedRow, 0);
                	int clientID = idValue.intValueExact();

                    try {
                        statement = connection.createStatement();
                        String sql = "DELETE FROM PROPRIETE WHERE ID = " + clientID;
                        statement.execute(sql);
                        refreshClientTable(); // Refresh the client table after deletion
                        JOptionPane.showMessageDialog(null, "property deleted successfully.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Failed to delete property.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a property to delete.");
                }
            }
        });
        
        
        
    

        
       

        // Create Modify button
        JButton modifyButton = new JButton("Modify");
        modifyButton.setBackground(Color.WHITE);
        modifyButton.setForeground(Color.BLACK);
        modifyButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        modifyButton.setBounds(905, 469, 100, 46);
        getContentPane().add(modifyButton);

     // Add ActionListener for Modify button
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a property to modify.");
                    return;
                }
                BigDecimal idValue = (BigDecimal) table.getValueAt(selectedRow, 0);
                propid = idValue.intValueExact(); // Set the class-level propid variable

                // Retrieve property details from the database based on the propid
                try {
                    statement = connection.createStatement();
                    String query = "SELECT * FROM PROPRIETE WHERE ID = " + propid;
                    ResultSet rs = statement.executeQuery(query);
                    if (rs.next()) {
                        // Populate input fields with property information
                        SUPERPROP.setText(rs.getString("superficie"));
                        PRIXPROP.setText(rs.getString("prix"));
                        LOCAPROP.setText(rs.getString("localisation"));
                        DESCPROP.setText(rs.getString("description_prop"));

                        // Select appropriate radio button based on type
                        String type = rs.getString("type_prop");
                        if (type != null) {
                            if (type.equalsIgnoreCase("APPARTEMENT")) {
                                rdbtnappar.setSelected(true);
                            } else if (type.equalsIgnoreCase("MAISON")) {
                                rdbtnmaison.setSelected(true);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to retrieve property details for modification.");
                }
            }
        });
        
        
     // Create Save button
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(Color.WHITE);
        saveButton.setForeground(Color.BLACK);
        saveButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        saveButton.setBounds(1072, 469, 100, 46);
        getContentPane().add(saveButton);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\kooki\\Downloads\\Untitled design (1) (1).png"));
        lblNewLabel_1.setBounds(0, 0, 1283, 700);
        getContentPane().add(lblNewLabel_1);

     // Add ActionListener for Save button
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Extract modified data from input fields
                    String superficie = SUPERPROP.getText();
                    String prix = PRIXPROP.getText();
                    String description = DESCPROP.getText();
                    String localisation = LOCAPROP.getText();
                    String typeprop = null;
                    if (rdbtnappar.isSelected()) {
                        typeprop = "APPARTEMENT";
                    } else if (rdbtnmaison.isSelected()) {
                        typeprop = "MAISON";
                    }

                    // Construct SQL UPDATE statement
                    String updateQuery = "UPDATE PROPRIETE SET superficie=?, prix=?, localisation=?, description_prop=?, type_prop=? WHERE ID=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                    preparedStatement.setString(1, superficie);
                    preparedStatement.setString(2, prix);
                    preparedStatement.setString(3, localisation);
                    preparedStatement.setString(4, description);
                    preparedStatement.setString(5, typeprop);
                    preparedStatement.setInt(6, propid); // propid is the ID of the property being modified

                    // Execute the update statement
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Property details updated successfully.");

                        // Refresh the property table after modification
                        refreshClientTable();

                        // Clear input fields after modification
                        SUPERPROP.setText("");
                        PRIXPROP.setText("");
                        DESCPROP.setText("");
                        LOCAPROP.setText("");
                        group.clearSelection(); // Deselect radio buttons
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update property details.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to update property details.");
                }
            }
        });


        
        
        
        
    }
	
	
	private void refreshClientTable() {
	    try {
	        String sql = "SELECT * FROM PROPRIETE";
	        statement = connection.createStatement();
	        ResultSet rs = statement.executeQuery(sql);
	        table.setModel(DbUtils.resultSetToTableModel(rs));
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Failed to refresh property table.");
	    }
	}
}
