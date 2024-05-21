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
import javax.swing.ListSelectionModel;

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

public class agentinter extends JFrame {

	private JTextField nomagent;
    private JTextField prenomagent;
    private JTextField adragent;
    private JTextField telagent;
    private JTextField emailagent;
    private int agentID;

    private Connection connection;
    private Statement statement;
    private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					agentinter window = new agentinter();
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
	public agentinter() {
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
        
        JLabel lblNewLabel = new JLabel("AGENTS");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(557, 11, 127, 46);
        getContentPane().add(lblNewLabel);
        
        nomagent = new JTextField();
        nomagent.setFont(new Font("Tahoma", Font.PLAIN, 16));
        nomagent.setBounds(231, 77, 286, 46);
        getContentPane().add(nomagent);
        nomagent.setColumns(10);
        
        prenomagent = new JTextField();
        prenomagent.setFont(new Font("Tahoma", Font.PLAIN, 16));
        prenomagent.setColumns(10);
        prenomagent.setBounds(231, 154, 286, 46);
        getContentPane().add(prenomagent);
        
        adragent = new JTextField();
        adragent.setFont(new Font("Tahoma", Font.PLAIN, 16));
        adragent.setColumns(10);
        adragent.setBounds(231, 308, 286, 46);
        getContentPane().add(adragent);
        
        telagent = new JTextField();
        telagent.setFont(new Font("Tahoma", Font.PLAIN, 16));
        telagent.setColumns(10);
        telagent.setBounds(231, 377, 286, 46);
        getContentPane().add(telagent);
        
        JLabel lblNom = new JLabel("NOM :");
        lblNom.setForeground(Color.WHITE);
        lblNom.setHorizontalAlignment(SwingConstants.LEFT);
        lblNom.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNom.setBounds(59, 75, 127, 46);
        getContentPane().add(lblNom);
        
        JLabel lblPrenom = new JLabel("PRENOM :");
        lblPrenom.setForeground(Color.WHITE);
        lblPrenom.setHorizontalAlignment(SwingConstants.LEFT);
        lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPrenom.setBounds(59, 152, 127, 46);
        getContentPane().add(lblPrenom);
        
        JLabel lblAdresse = new JLabel("ADRESSE :");
        lblAdresse.setForeground(Color.WHITE);
        lblAdresse.setHorizontalAlignment(SwingConstants.LEFT);
        lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblAdresse.setBounds(59, 306, 127, 46);
        getContentPane().add(lblAdresse);
        
        JLabel lblTelephone = new JLabel("TELEPHONE :");
        lblTelephone.setForeground(Color.WHITE);
        lblTelephone.setHorizontalAlignment(SwingConstants.LEFT);
        lblTelephone.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTelephone.setBounds(59, 375, 127, 46);
        getContentPane().add(lblTelephone);
        
        ButtonGroup group = new ButtonGroup();
        
        String typeclient = null;
        JButton AJTAGENT = new JButton("AJOUTER");
        AJTAGENT.setBackground(Color.WHITE);
        AJTAGENT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    
                    // Fetch the current maximum ID from the database
                    ResultSet rs = statement.executeQuery("SELECT MAX(ID) AS max_id FROM AGENT");
                    int maxID = 0;
                    if (rs.next()) {
                        maxID = rs.getInt("max_id");
                    }
                    // Increment the ID
                    int ID = maxID + 1;
                    
                    String nom = nomagent.getText();
                    String prenom = prenomagent.getText();
                    String adresse = adragent.getText();
                    String telephone = telagent.getText();
                    String email = emailagent.getText();
                    
                   
                    
                    // SQL
                    String query = "INSERT INTO AGENT VALUES ('"+ID+"','"+nom+"','"+prenom+"','"+adresse+"','"+telephone+"','"+email+"')";
                    
                    statement.execute(query);
                    JOptionPane.showMessageDialog(AJTAGENT, "bien insere!");
                    
                } catch (Exception e1) {
                    e1.printStackTrace();
                }   
            }
        });
        
        AJTAGENT.setFont(new Font("Tahoma", Font.PLAIN, 16));
        AJTAGENT.setBounds(59, 516, 123, 46);
        getContentPane().add(AJTAGENT);
        
        emailagent = new JTextField();
        emailagent.setFont(new Font("Tahoma", Font.PLAIN, 16));
        emailagent.setColumns(10);
        emailagent.setBounds(231, 228, 286, 46);
        getContentPane().add(emailagent);
        
        JLabel lblEmail = new JLabel("EMAIL :");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEmail.setBounds(59, 226, 127, 46);
        getContentPane().add(lblEmail);
        
        JButton clearButton = new JButton("New agent");
        clearButton.setBackground(Color.WHITE);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Clear the text in each JTextField
                nomagent.setText("");
                prenomagent.setText("");
                adragent.setText("");
                telagent.setText("");
                emailagent.setText("");
                
                // Deselect radio buttons
                group.clearSelection();
            }
        });
        clearButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        clearButton.setBounds(449, 516, 123, 46);
        getContentPane().add(clearButton);

        
        JButton AFFICHAGENT = new JButton("AFFICHER AGENTS");
        AFFICHAGENT.setBackground(Color.WHITE);
        AFFICHAGENT.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String sql="SELECT * FROM AGENT";
        		
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
        AFFICHAGENT.setFont(new Font("Tahoma", Font.PLAIN, 16));
        AFFICHAGENT.setBounds(231, 516, 175, 46);
        getContentPane().add(AFFICHAGENT);
        
        JScrollPane tabcli = new JScrollPane();
        tabcli.setBounds(565, 95, 691, 326);
        getContentPane().add(tabcli);
        
        table = new JTable();
        tabcli.setViewportView(table);
        
     // After initializing the table
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
     // Create a JTextField for search queries
        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchField.setBounds(821, 21, 200, 30);
        getContentPane().add(searchField);

        // Create a JComboBox for selecting search options
        String[] searchOptions = {"Nom", "Prenom", "Adresse"};
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
                    case "Nom":
                        sql = "SELECT * FROM CLIENT WHERE nom LIKE '%" + query + "%'";
                        break;
                    case "Prenom":
                        sql = "SELECT * FROM CLIENT WHERE prenom LIKE '%" + query + "%'";
                        break;
                    case "Adresse":
                        sql = "SELECT * FROM CLIENT WHERE adresse LIKE '%" + query + "%'";
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
        deleteButton.setBounds(711, 469, 125, 46);
        getContentPane().add(deleteButton);

        
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                	BigDecimal idValue = (BigDecimal) table.getValueAt(selectedRow, 0);
                	int clientID = idValue.intValueExact();

                    try {
                        statement = connection.createStatement();
                        String sql = "DELETE FROM AGENT WHERE ID = " + clientID;
                        statement.execute(sql);
                        refreshClientTable(); // Refresh the client table after deletion
                        JOptionPane.showMessageDialog(null, "agent deleted successfully.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Failed to delete agent.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a agent to delete.");
                }
            }
        });
        
        
        
    

        
       

        // Create Modify button
        JButton modifyButton = new JButton("Modify");
        modifyButton.setBackground(Color.WHITE);
        modifyButton.setForeground(Color.BLACK);
        modifyButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        modifyButton.setBounds(887, 469, 100, 46);
        getContentPane().add(modifyButton);

     // Add ActionListener for Modify button
     // Add ActionListener for Modify button
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select an agent to modify.");
                    return;
                }
                BigDecimal idValue = (BigDecimal) table.getValueAt(selectedRow, 0);
                agentID = idValue.intValueExact(); // Set the class-level agentID variable

                // Retrieve agent details from the database based on the agentID
                try {
                    statement = connection.createStatement();
                    String query = "SELECT * FROM AGENT WHERE ID = " + agentID;
                    ResultSet rs = statement.executeQuery(query);
                    if (rs.next()) {
                        // Populate input fields with agent information
                        nomagent.setText(rs.getString("nom"));
                        prenomagent.setText(rs.getString("prenom"));
                        adragent.setText(rs.getString("adresse"));
                        telagent.setText(rs.getString("telephone"));
                        emailagent.setText(rs.getString("email"));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to retrieve agent details for modification.");
                }
            }
        });
        
     // Create Save button
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(Color.WHITE);
        saveButton.setForeground(Color.BLACK);
        saveButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        saveButton.setBounds(1037, 469, 100, 46);
        getContentPane().add(saveButton);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\kooki\\Downloads\\Untitled design (1) (1).png"));
        lblNewLabel_1.setBounds(0, 0, 1295, 689);
        getContentPane().add(lblNewLabel_1);

     // Add ActionListener for Save button
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Extract modified data from input fields
                    String nom = nomagent.getText();
                    String prenom = prenomagent.getText();
                    String adresse = adragent.getText();
                    String telephone = telagent.getText();
                    String email = emailagent.getText();
                    
                    // Construct SQL UPDATE statement
                    String updateQuery = "UPDATE AGENT SET nom=?, prenom=?, adresse=?, telephone=?, email=? WHERE ID=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                    preparedStatement.setString(1, nom);
                    preparedStatement.setString(2, prenom);
                    preparedStatement.setString(3, adresse);
                    preparedStatement.setString(4, telephone);
                    preparedStatement.setString(5, email);
                    preparedStatement.setInt(6, agentID); // agentID is the ID of the agent being modified
                    
                    // Execute the update statement
                    int rowsAffected = preparedStatement.executeUpdate();
                    
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Agent details updated successfully.");
                        
                        // Refresh the agent table after modification
                        refreshClientTable();
                        
                        // Clear input fields after modification
                        nomagent.setText("");
                        prenomagent.setText("");
                        adragent.setText("");
                        telagent.setText("");
                        emailagent.setText("");
                        group.clearSelection(); // Deselect radio buttons
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update agent details.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to update agent details.");
                }
            }
        });

        
        
        
        
        
        
	}
	
	 private void refreshClientTable() {
	        try {
	            String sql = "SELECT * FROM AGENT";
	            statement = connection.createStatement();
	            ResultSet rs = statement.executeQuery(sql);
	            table.setModel(DbUtils.resultSetToTableModel(rs));
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Failed to refresh client table.");
	        }
	    }

	

}
