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


public class transinter extends JFrame {
	    private JTextField dateTRANS;
	    private JTextField clientid;
	    private JTextField propid;
	    private int transID;

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
					transinter window = new transinter();
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
	public transinter() {
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
	       
	       
	       setTitle("TRANS Interface");
	       setSize(1343, 737);
	       setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	       getContentPane().setLayout(null);
	       
	       JLabel lblNewLabel = new JLabel("TRANSACTIONS");
	       lblNewLabel.setForeground(Color.WHITE);
	       lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	       lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
	       lblNewLabel.setBounds(565, 21, 161, 46);
	       getContentPane().add(lblNewLabel);
	       
	       dateTRANS = new JTextField();
	       dateTRANS.setFont(new Font("Tahoma", Font.PLAIN, 16));
	       dateTRANS.setBounds(231, 136, 286, 46);
	       getContentPane().add(dateTRANS);
	       dateTRANS.setColumns(10);
	       
	       clientid = new JTextField();
	       clientid.setFont(new Font("Tahoma", Font.PLAIN, 16));
	       clientid.setColumns(10);
	       clientid.setBounds(231, 224, 286, 46);
	       getContentPane().add(clientid);
	       
	       JLabel lblNom = new JLabel("DATE ET HEURE :");
	       lblNom.setForeground(Color.WHITE);
	       lblNom.setHorizontalAlignment(SwingConstants.LEFT);
	       lblNom.setFont(new Font("Tahoma", Font.PLAIN, 20));
	       lblNom.setBounds(59, 134, 175, 46);
	       getContentPane().add(lblNom);
	       
	       JLabel lblPrenom = new JLabel("CLIENT :");
	       lblPrenom.setForeground(Color.WHITE);
	       lblPrenom.setHorizontalAlignment(SwingConstants.LEFT);
	       lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 20));
	       lblPrenom.setBounds(59, 222, 127, 46);
	       getContentPane().add(lblPrenom);
	       
	       JLabel lblType = new JLabel("TYPE :");
	       lblType.setForeground(Color.WHITE);
	       lblType.setHorizontalAlignment(SwingConstants.LEFT);
	       lblType.setFont(new Font("Tahoma", Font.PLAIN, 20));
	       lblType.setBounds(59, 384, 127, 46);
	       getContentPane().add(lblType);
	       
	       JRadioButton rdbtnvente = new JRadioButton("VENTE");
	       rdbtnvente.setFont(new Font("Tahoma", Font.PLAIN, 16));
	       rdbtnvente.setBounds(227, 398, 127, 23);
	       getContentPane().add(rdbtnvente);

	       JRadioButton rdbtnloca = new JRadioButton("LOCATION");
	       rdbtnloca.setFont(new Font("Tahoma", Font.PLAIN, 16));
	       rdbtnloca.setBounds(356, 398, 127, 23);
	       getContentPane().add(rdbtnloca);
	       
	       ButtonGroup group = new ButtonGroup();
	       group.add(rdbtnvente);
	       group.add(rdbtnloca);
	       
	       String typetrans = null;
	       JButton AJTTRANS = new JButton("AJOUTER");
	       AJTTRANS.setBackground(Color.WHITE);
	       
	       
	       AJTTRANS.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	        try {
	    	            // Check if the connection is not null
	    	            if (connection == null) {
	    	                throw new Exception("Database connection is null");
	    	            }

	    	            // Fetch the current maximum ID from the database
	    	            String query = "SELECT MAX(ID) AS max_id FROM TRANS";
	    	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	    	            ResultSet rs = preparedStatement.executeQuery();
	    	            int maxID = 0;
	    	            if (rs.next()) {
	    	                maxID = rs.getInt("max_id");
	    	            }
	    	            // Increment the ID
	    	            int ID = maxID + 1;

	    	            // Validate and parse integer values from text fields
	    	            int CLIENT = 0;
	    	            int PROP = 0;
	    	            if (!clientid.getText().isEmpty()) {
	    	                CLIENT = Integer.parseInt(clientid.getText());
	    	            } else {
	    	                throw new NumberFormatException("Client ID field is empty");
	    	            }
	    	            if (!propid.getText().isEmpty()) {
	    	                PROP = Integer.parseInt(propid.getText());
	    	            } else {
	    	                throw new NumberFormatException("Property ID field is empty");
	    	            }

	    	            // Check if CLIENT_ID exists in CLIENT table
	    	            String checkClientQuery = "SELECT COUNT(*) AS count FROM CLIENT WHERE ID = ?";
	    	            PreparedStatement checkClientStmt = connection.prepareStatement(checkClientQuery);
	    	            checkClientStmt.setInt(1, CLIENT);
	    	            ResultSet rsClient = checkClientStmt.executeQuery();
	    	            if (rsClient.next() && rsClient.getInt("count") == 0) {
	    	                throw new Exception("Client ID does not exist in CLIENT table");
	    	            }

	    	            // Check if PROP_ID exists in PROPRIETE table
	    	            String checkPropQuery = "SELECT COUNT(*) AS count FROM PROPRIETE WHERE ID = ?";
	    	            PreparedStatement checkPropStmt = connection.prepareStatement(checkPropQuery);
	    	            checkPropStmt.setInt(1, PROP);
	    	            ResultSet rsProp = checkPropStmt.executeQuery();
	    	            if (rsProp.next() && rsProp.getInt("count") == 0) {
	    	                throw new Exception("Property ID does not exist in PROPRIETE table");
	    	            }

	    	            String typetrans = null;
	    	            if (rdbtnloca.isSelected()) {
	    	                typetrans = "LOCATION";
	    	            } else if (rdbtnvente.isSelected()) {
	    	                typetrans = "VENTE";
	    	            } else {
	    	                throw new Exception("Type transaction not selected");
	    	            }

	    	            String insertQuery = "INSERT INTO TRANS (ID, DATE_ET_HEURE, CLIENT_ID, PROP_ID, TYPE_TRANS) VALUES (?, ?, ?, ?, ?)";
	    	            PreparedStatement preparedStatement1 = connection.prepareStatement(insertQuery);
	    	            preparedStatement1.setInt(1, ID);
	    	            preparedStatement1.setString(2, dateTRANS.getText());
	    	            preparedStatement1.setInt(3, CLIENT);
	    	            preparedStatement1.setInt(4, PROP);
	    	            preparedStatement1.setString(5, typetrans);
	    	            preparedStatement1.executeUpdate();

	    	            JOptionPane.showMessageDialog(AJTTRANS, "Bien inséré!");

	    	        } catch (NumberFormatException ex) {
	    	            ex.printStackTrace();
	    	            JOptionPane.showMessageDialog(null, "Veuillez entrer des valeurs entières valides pour l'identifiant client et l'identifiant de propriété.");
	    	        } catch (Exception e1) {
	    	            e1.printStackTrace();
	    	            JOptionPane.showMessageDialog(null, "Erreur lors de l'insertion des données: " + e1.getMessage());
	    	        }
	    	    }
	    	});



	       
	       AJTTRANS.setFont(new Font("Tahoma", Font.PLAIN, 16));
	       AJTTRANS.setBounds(59, 528, 123, 46);
	       getContentPane().add(AJTTRANS);
	       
	       propid = new JTextField();
	       propid.setFont(new Font("Tahoma", Font.PLAIN, 16));
	       propid.setColumns(10);
	       propid.setBounds(231, 311, 286, 46);
	       getContentPane().add(propid);
	       
	       JLabel lblEmail = new JLabel("PROPRIETE :");
	       lblEmail.setForeground(Color.WHITE);
	       lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
	       lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
	       lblEmail.setBounds(59, 309, 127, 46);
	       getContentPane().add(lblEmail);
	       
	       JButton clearButton = new JButton("New transaction");
	       clearButton.setBackground(Color.WHITE);
	       clearButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	               // Clear the text in each JTextField
	               dateTRANS.setText("");
	               clientid.setText("");
	               propid.setText("");
	               
	               // Deselect radio buttons
	               group.clearSelection();
	           }
	       });
	       clearButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
	       clearButton.setBounds(492, 528, 155, 46);
	       getContentPane().add(clearButton);

	       
	       JButton AFFICHTRANS = new JButton("AFFICHER TRANSACTIONS");
	       AFFICHTRANS.setBackground(Color.WHITE);
	       AFFICHTRANS.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent e) {
	       		String sql="SELECT * FROM TRANS";
	       		
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
	       
	       
	       AFFICHTRANS.setFont(new Font("Tahoma", Font.PLAIN, 16));
	       AFFICHTRANS.setBounds(212, 528, 252, 46);
	       getContentPane().add(AFFICHTRANS);
	       
	       JScrollPane tabcli = new JScrollPane();
	       tabcli.setBounds(565, 95, 691, 352);
	       getContentPane().add(tabcli);
	       
	       table = new JTable();
	       tabcli.setViewportView(table);
	       
	    // After initializing the table
	       table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	       
	    // Create a JTextField for search queries
	       JTextField searchField = new JTextField();
	       searchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
	       searchField.setBounds(822, 31, 200, 30);
	       getContentPane().add(searchField);

	       // Create a JComboBox for selecting search options
	       String[] searchOptions = {"DATE", "CLIENT ID", "PROP ID", "Type trans"};
	       JComboBox<String> searchOptionsComboBox = new JComboBox<>(searchOptions);
	       searchOptionsComboBox.setBackground(Color.WHITE);
	       searchOptionsComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
	       searchOptionsComboBox.setBounds(727, 31, 84, 30);
	       getContentPane().add(searchOptionsComboBox);

	       // Create a JButton for triggering the search operation
	       JButton searchButton = new JButton("Search");
	       searchButton.setBackground(Color.WHITE);
	       searchButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
	       searchButton.setBounds(1021, 31, 100, 30);
	       getContentPane().add(searchButton);
	       

	       // Add ActionListener for the search button
	    // Add ActionListener for the search button
	       searchButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	               // Get the search query and selected search option
	               String query = searchField.getText().trim();
	               String selectedOption = (String) searchOptionsComboBox.getSelectedItem();

	               // Construct the SQL query based on the selected search option
	               String sql = "";
	               switch (selectedOption) {
	                   case "DATE":
	                       sql = "SELECT * FROM TRANS WHERE DATE_ET_HEURE LIKE ?";
	                       break;
	                   case "CLIENT ID":
	                       sql = "SELECT * FROM TRANS WHERE CLIENT_ID LIKE ?";
	                       break;
	                   case "PROP ID":
	                       sql = "SELECT * FROM TRANS WHERE PROP_ID LIKE ?";
	                       break;
	                   case "Type trans":
	                       sql = "SELECT * FROM TRANS WHERE LOWER(TYPE_TRANS) LIKE ?";
	                       break;
	                   default:
	                       JOptionPane.showMessageDialog(null, "Please select a valid search option.");
	                       return;
	               }

	               // Perform the search operation in the database
	               try {
	                   PreparedStatement preparedStatement = connection.prepareStatement(sql);
	                   preparedStatement.setString(1, "%" + query + "%");
	                   ResultSet rs = preparedStatement.executeQuery();

	                   // Display search results in the table
	                   table.setModel(DbUtils.resultSetToTableModel(rs));

	               } catch (SQLException ex) {
	                   ex.printStackTrace();
	                   JOptionPane.showMessageDialog(null, "Error executing search query: " + ex.getMessage());
	               }
	           }
	       });

	       
	       
	    // Create Delete button
	       JButton deleteButton = new JButton("Delete");
	       deleteButton.setBackground(Color.WHITE);
	       deleteButton.setForeground(Color.BLACK);
	       deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
	       deleteButton.setBounds(755, 469, 125, 46);
	       getContentPane().add(deleteButton);

	       
	       deleteButton.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	        int selectedRow = table.getSelectedRow();
	    	        if (selectedRow != -1) {
	    	            BigDecimal idValue = (BigDecimal) table.getValueAt(selectedRow, 0);
	    	            int rdvID = idValue.intValueExact();

	    	            try {
	    	                // Use PreparedStatement to prevent SQL injection
	    	                String sql = "DELETE FROM TRANS WHERE ID = ?";
	    	                PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    	                preparedStatement.setInt(1, rdvID);
	    	                int affectedRows = preparedStatement.executeUpdate();

	    	                if (affectedRows > 0) {
	    	                    refreshrdvTable(); // Refresh the transaction table after deletion
	    	                    JOptionPane.showMessageDialog(null, "Transaction deleted successfully.");
	    	                } else {
	    	                    JOptionPane.showMessageDialog(null, "Transaction not found.");
	    	                }

	    	            } catch (SQLException ex) {
	    	                ex.printStackTrace();
	    	                JOptionPane.showMessageDialog(null, "Failed to delete transaction.");
	    	            }
	    	        } else {
	    	            JOptionPane.showMessageDialog(null, "Please select a transaction to delete.");
	    	        }
	    	    }
	    	});

	       
	       

	       // Create Modify button
	       JButton modifyButton = new JButton("Modify");
	       modifyButton.setBackground(Color.WHITE);
	       modifyButton.setForeground(Color.BLACK);
	       modifyButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
	       modifyButton.setBounds(922, 469, 100, 46);
	       getContentPane().add(modifyButton);

	    // Add ActionListener for Modify button
	       modifyButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	               int selectedRow = table.getSelectedRow();
	               if (selectedRow == -1) {
	                   JOptionPane.showMessageDialog(null, "Please select a transaction to modify.");
	                   return;
	               }
	               BigDecimal idValue = (BigDecimal) table.getValueAt(selectedRow, 0);
	               transID = idValue.intValueExact(); // Set the class-level agentID variable

	               // Retrieve agent details from the database based on the agentID
	               try {
	                   statement = connection.createStatement();
	                   String query = "SELECT * FROM TRANS WHERE ID = " + transID;
	                   ResultSet rs = statement.executeQuery(query);
	                   if (rs.next()) {
	                       // Populate input fields with agent information
	                       clientid.setText(rs.getString("client_id"));
	                       propid.setText(rs.getString("prop_id"));
	                       dateTRANS.setText(rs.getString("date_et_heure"));
	                       
	                    // Select appropriate radio button based on type
	                       String type = rs.getString("type_trans");
	                       switch (type) {
	                           case "location":
	                               rdbtnloca.setSelected(true);
	                               break;
	                           case "vente":
	                               rdbtnvente.setSelected(true);
	                           break;
	                       }
	                   }
	               } catch (SQLException ex) {
	                   ex.printStackTrace();
	                   JOptionPane.showMessageDialog(null, "Failed to retrieve transaction details for modification.");
	               }
	           }
	       });
	       
	       
	       // Create Save button
	       JButton saveButton = new JButton("Save");
	       saveButton.setBackground(Color.WHITE);
	       saveButton.setForeground(Color.BLACK);
	       saveButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
	       saveButton.setBounds(1061, 469, 100, 46);
	       getContentPane().add(saveButton);
	       
	       JLabel lblNewLabel_1 = new JLabel("New label");
	       lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\kooki\\Downloads\\Untitled design (2).png"));
	       lblNewLabel_1.setBounds(0, 0, 1283, 700);
	       getContentPane().add(lblNewLabel_1);

	    // Add ActionListener for Save button
	       saveButton.addActionListener(new ActionListener() {
	    	    public void actionPerformed(ActionEvent e) {
	    	        int selectedRow = table.getSelectedRow();
	    	        if (selectedRow != -1) {
	    	            BigDecimal idValue = (BigDecimal) table.getValueAt(selectedRow, 0);
	    	            int transID = idValue.intValueExact();

	    	            try {
	    	                // Extract modified data from input fields
	    	                String date = dateTRANS.getText();
	    	                String clientID = clientid.getText();
	    	                String propID = propid.getText();
	    	                String typetrans = null;
	    	                if (rdbtnloca.isSelected()) {
	    	                    typetrans = "LOCATION"; // Ensure the value is exactly 'LOCATION'
	    	                } else if (rdbtnvente.isSelected()) {
	    	                    typetrans = "VENTE"; // Ensure the value is exactly 'VENTE'
	    	                }

	    	                // Print the value of TYPE_TRANS for debugging
	    	                System.out.println("TYPE_TRANS: " + typetrans);

	    	                // Ensure typetrans is not null and has the correct value
	    	                if (typetrans == null || (!typetrans.equals("LOCATION") && !typetrans.equals("VENTE"))) {
	    	                    throw new SQLException("Invalid value for TYPE_TRANS: " + typetrans);
	    	                }

	    	                // Construct SQL UPDATE statement
	    	                String updateQuery = "UPDATE TRANS SET DATE_ET_HEURE=?, CLIENT_ID=?, PROP_ID=?, TYPE_TRANS=? WHERE ID=?";
	    	                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
	    	                preparedStatement.setString(1, date);
	    	                preparedStatement.setString(2, clientID);
	    	                preparedStatement.setString(3, propID);
	    	                preparedStatement.setString(4, typetrans);
	    	                preparedStatement.setInt(5, transID); // transID is the ID of the TRANSACTION being modified

	    	                // Execute the update statement
	    	                int rowsAffected = preparedStatement.executeUpdate();

	    	                if (rowsAffected > 0) {
	    	                    JOptionPane.showMessageDialog(null, "TRANSACTION details updated successfully.");

	    	                    // Refresh the RDV table after modification
	    	                    refreshrdvTable();

	    	                    // Clear input fields after modification
	    	                    dateTRANS.setText("");
	    	                    clientid.setText("");
	    	                    propid.setText("");
	    	                    group.clearSelection(); // Deselect radio buttons
	    	                } else {
	    	                    JOptionPane.showMessageDialog(null, "Failed to update TRANSACTION details.");
	    	                }
	    	            } catch (SQLException ex) {
	    	                ex.printStackTrace();
	    	                JOptionPane.showMessageDialog(null, "Failed to update TRANSACTION details. Error: " + ex.getMessage());
	    	            }
	    	        } else {
	    	            JOptionPane.showMessageDialog(null, "Please select a transaction to update.");
	    	        }
	    	    }
	    	});


	       
	       
	       
	       
	       
	       
		    
		}
	
	
	
	private void refreshrdvTable() {
        try {
            String sql = "SELECT * FROM TRANS";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to refresh trans table.");
        }
    }

}
