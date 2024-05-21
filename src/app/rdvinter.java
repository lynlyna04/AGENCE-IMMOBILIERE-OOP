package app;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

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

public class rdvinter extends JFrame {

	    private JTextField daterdv;
	    private JTextField clientid;
	    private JTextField agentid;
	    private JTextField propid;
        private int rdvID;
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
					rdvinter window = new rdvinter();
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
	public rdvinter() {
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
       
       JLabel lblNewLabel = new JLabel("RDVs");
       lblNewLabel.setForeground(Color.WHITE);
       lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
       lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
       lblNewLabel.setBounds(557, 11, 127, 46);
       getContentPane().add(lblNewLabel);
       
       daterdv = new JTextField();
       daterdv.setFont(new Font("Tahoma", Font.PLAIN, 16));
       daterdv.setBounds(231, 77, 286, 46);
       getContentPane().add(daterdv);
       daterdv.setColumns(10);
       
       clientid = new JTextField();
       clientid.setFont(new Font("Tahoma", Font.PLAIN, 16));
       clientid.setColumns(10);
       clientid.setBounds(231, 154, 286, 46);
       getContentPane().add(clientid);
       
       agentid = new JTextField();
       agentid.setFont(new Font("Tahoma", Font.PLAIN, 16));
       agentid.setColumns(10);
       agentid.setBounds(231, 308, 286, 46);
       getContentPane().add(agentid);
       
       JLabel lblNom = new JLabel("DATE ET HEURE :");
       lblNom.setForeground(Color.WHITE);
       lblNom.setHorizontalAlignment(SwingConstants.LEFT);
       lblNom.setFont(new Font("Tahoma", Font.PLAIN, 20));
       lblNom.setBounds(59, 75, 175, 46);
       getContentPane().add(lblNom);
       
       JLabel lblPrenom = new JLabel("CLIENT ID :");
       lblPrenom.setForeground(Color.WHITE);
       lblPrenom.setHorizontalAlignment(SwingConstants.LEFT);
       lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 20));
       lblPrenom.setBounds(59, 152, 127, 46);
       getContentPane().add(lblPrenom);
       
       JLabel lblAdresse = new JLabel("AGENT ID :");
       lblAdresse.setForeground(Color.WHITE);
       lblAdresse.setHorizontalAlignment(SwingConstants.LEFT);
       lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 20));
       lblAdresse.setBounds(59, 306, 127, 46);
       getContentPane().add(lblAdresse);
       
       JLabel lblType = new JLabel("TYPE :");
       lblType.setForeground(Color.WHITE);
       lblType.setHorizontalAlignment(SwingConstants.LEFT);
       lblType.setFont(new Font("Tahoma", Font.PLAIN, 20));
       lblType.setBounds(59, 398, 127, 46);
       getContentPane().add(lblType);
       
       JRadioButton rdbtnplanif = new JRadioButton("PLANIFIE");
       rdbtnplanif.setFont(new Font("Tahoma", Font.PLAIN, 16));
       rdbtnplanif.setBounds(231, 412, 127, 23);
       getContentPane().add(rdbtnplanif);

       JRadioButton rdbtnannule = new JRadioButton("ANNULE");
       rdbtnannule.setFont(new Font("Tahoma", Font.PLAIN, 16));
       rdbtnannule.setBounds(390, 412, 127, 23);
       getContentPane().add(rdbtnannule);

       JRadioButton rdbtnrealise = new JRadioButton("REALISE");
       rdbtnrealise.setHorizontalAlignment(SwingConstants.CENTER);
       rdbtnrealise.setFont(new Font("Tahoma", Font.PLAIN, 16));
       rdbtnrealise.setBounds(322, 454, 109, 23);
       getContentPane().add(rdbtnrealise);
       
       ButtonGroup group = new ButtonGroup();
       group.add(rdbtnplanif);
       group.add(rdbtnannule);
       group.add(rdbtnrealise);
       
       String typerdv = null;
       JButton AJTRDV = new JButton("AJOUTER");
       AJTRDV.setBackground(Color.WHITE);
       AJTRDV.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        try {
    	            // Check if the connection is not null
    	            if (connection == null) {
    	                throw new Exception("Database connection is null");
    	            }

    	            // Fetch the current maximum ID from the database
    	            String query = "SELECT MAX(ID) AS max_id FROM RDV";
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
    	            int AGENT = 0;
    	            int PROP = 0;
    	            if (!clientid.getText().isEmpty()) {
    	                CLIENT = Integer.parseInt(clientid.getText());
    	            } else {
    	                throw new NumberFormatException("Client ID field is empty");
    	            }
    	            if (!agentid.getText().isEmpty()) {
    	                AGENT = Integer.parseInt(agentid.getText());
    	            } else {
    	                throw new NumberFormatException("Agent ID field is empty");
    	            }
    	            if (!propid.getText().isEmpty()) {
    	                PROP = Integer.parseInt(propid.getText());
    	            } else {
    	                throw new NumberFormatException("Property ID field is empty");
    	            }

    	            String typeRDV = null;
    	            if (rdbtnplanif.isSelected()) {
    	                typeRDV = "planifie";
    	            } else if (rdbtnannule.isSelected()) {
    	                typeRDV = "annule";
    	            } else if (rdbtnrealise.isSelected()) {
    	                typeRDV = "realise";
    	            } else {
    	                throw new Exception("Type RDV not selected");
    	            }


    	            String insertQuery = "INSERT INTO RDV (ID, DATE_ET_HEURE_NEW, CLIENT_ID, AGENT_ID, PROP_ID, TYPE_RDV) VALUES (?, ?, ?, ?, ?, ?)";
    	            PreparedStatement preparedStatement1 = connection.prepareStatement(insertQuery);
    	            preparedStatement1.setInt(1, ID);
    	            preparedStatement1.setString(2, daterdv.getText());
    	            preparedStatement1.setInt(3, CLIENT);
    	            preparedStatement1.setInt(4, AGENT);
    	            preparedStatement1.setInt(5, PROP);
    	            preparedStatement1.setString(6, typeRDV);
    	            preparedStatement1.executeUpdate();

    	            JOptionPane.showMessageDialog(AJTRDV, "Bien inséré!");

    	        } catch (NumberFormatException ex) {
    	            ex.printStackTrace();
    	            JOptionPane.showMessageDialog(null, "Veuillez entrer des valeurs entières valides pour l'identifiant client, l'identifiant agent, et l'identifiant de propriété.");
    	        } catch (Exception e1) {
    	            e1.printStackTrace();
    	            JOptionPane.showMessageDialog(null, "Erreur lors de l'insertion des données: " + e1.getMessage());
    	        }
    	    }
    	});



       
       AJTRDV.setFont(new Font("Tahoma", Font.PLAIN, 16));
       AJTRDV.setBounds(59, 528, 123, 46);
       getContentPane().add(AJTRDV);
       
       propid = new JTextField();
       propid.setFont(new Font("Tahoma", Font.PLAIN, 16));
       propid.setColumns(10);
       propid.setBounds(231, 228, 286, 46);
       getContentPane().add(propid);
       
       JLabel lblEmail = new JLabel("PROPRIETE ID :");
       lblEmail.setForeground(Color.WHITE);
       lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
       lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
       lblEmail.setBounds(59, 226, 144, 46);
       getContentPane().add(lblEmail);
       
       JButton clearButton = new JButton("New RDV");
       clearButton.setBackground(Color.WHITE);
       clearButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               // Clear the text in each JTextField
               daterdv.setText("");
               clientid.setText("");
               agentid.setText("");
               propid.setText("");
               
               // Deselect radio buttons
               group.clearSelection();
           }
       });
       clearButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
       clearButton.setBounds(451, 528, 123, 46);
       getContentPane().add(clearButton);

       
       JButton AFFICHRDV = new JButton("AFFICHER RDVs");
       AFFICHRDV.setBackground(Color.WHITE);
       AFFICHRDV.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		String sql="SELECT * FROM RDV";
       		
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
       AFFICHRDV.setFont(new Font("Tahoma", Font.PLAIN, 16));
       AFFICHRDV.setBounds(231, 528, 175, 46);
       getContentPane().add(AFFICHRDV);
       
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
       String[] searchOptions = {"DATE", "CLIENT ID", "PROP ID","AGENT ID", "Type RDV"};
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
       

       searchButton.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        // Get the search query and selected search option
    	        String query = searchField.getText().trim();
    	        String selectedOption = (String) searchOptionsComboBox.getSelectedItem();

    	        // Construct the SQL query based on the selected search option
    	        String sql = "";
    	        switch (selectedOption) {
    	            case "DATE":
    	                sql = "SELECT * FROM RDV WHERE DATE_ET_HEURE_NEW LIKE '%" + query + "%'";
    	                break;
    	            case "CLIENT ID":
    	                sql = "SELECT * FROM RDV WHERE CLIENT_ID LIKE '%" + query + "%'";
    	                break;
    	            case "PROP ID":
    	                sql = "SELECT * FROM RDV WHERE PROP_ID LIKE '%" + query + "%'";
    	                break;
    	            case "AGENT ID":
    	                sql = "SELECT * FROM RDV WHERE AGENT_ID LIKE '%" + query + "%'";
    	                break;
    	            case "Type RDV":
    	                sql = "SELECT * FROM RDV WHERE LOWER(TYPE_RDV) LIKE '%" + query + "%'";
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
       deleteButton.setBounds(727, 469, 125, 46);
       getContentPane().add(deleteButton);

       
       deleteButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               int selectedRow = table.getSelectedRow();
               if (selectedRow != -1) {
               	BigDecimal idValue = (BigDecimal) table.getValueAt(selectedRow, 0);
               	int rdvID = idValue.intValueExact();

                   try {
                       statement = connection.createStatement();
                       String sql = "DELETE FROM RDV WHERE ID = " + rdvID;
                       statement.execute(sql);
                       refreshrdvTable(); // Refresh the client table after deletion
                       JOptionPane.showMessageDialog(null, "rdv deleted successfully.");
                   } catch (SQLException ex) {
                       ex.printStackTrace();
                       JOptionPane.showMessageDialog(null, "Failed to delete rdv.");
                   }
               } else {
                   JOptionPane.showMessageDialog(null, "Please select a rdv to delete.");
               }
           }
       });
       
       

       // Create Modify button
       JButton modifyButton = new JButton("Modify");
       modifyButton.setBackground(Color.WHITE);
       modifyButton.setForeground(Color.BLACK);
       modifyButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
       modifyButton.setBounds(915, 469, 100, 46);
       getContentPane().add(modifyButton);

    // Add ActionListener for Modify button
    // Add ActionListener for Modify button
       modifyButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               int selectedRow = table.getSelectedRow();
               if (selectedRow == -1) {
                   JOptionPane.showMessageDialog(null, "Please select an rdv to modify.");
                   return;
               }
               BigDecimal idValue = (BigDecimal) table.getValueAt(selectedRow, 0);
               rdvID = idValue.intValueExact(); // Set the class-level agentID variable

               // Retrieve agent details from the database based on the agentID
               try {
                   statement = connection.createStatement();
                   String query = "SELECT * FROM RDV WHERE ID = " + rdvID;
                   ResultSet rs = statement.executeQuery(query);
                   if (rs.next()) {
                       // Populate input fields with agent information
                       clientid.setText(rs.getString("client_id"));
                       propid.setText(rs.getString("prop_id"));
                       agentid.setText(rs.getString("agent_id"));
                       daterdv.setText(rs.getString("date_et_heure_new"));
                       
                    // Select appropriate radio button based on type
                       String type = rs.getString("type_rdv");
                       switch (type) {
                           case "annule":
                               rdbtnannule.setSelected(true);
                               break;
                           case "planifie":
                               rdbtnplanif.setSelected(true);
                               break;
                           case "realise":
                               rdbtnrealise.setSelected(true);
                               break;
                           default:
                               break;
                       }
                   }
               } catch (SQLException ex) {
                   ex.printStackTrace();
                   JOptionPane.showMessageDialog(null, "Failed to retrieve rdv details for modification.");
               }
           }
       });
       
       
       // Create Save button
       JButton saveButton = new JButton("Save");
       saveButton.setBackground(Color.WHITE);
       saveButton.setForeground(Color.BLACK);
       saveButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
       saveButton.setBounds(1071, 469, 100, 46);
       getContentPane().add(saveButton);
       
       JLabel lblNewLabel_1 = new JLabel("New label");
       lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\kooki\\Downloads\\Untitled design (2).png"));
       lblNewLabel_1.setBounds(0, 0, 1283, 713);
       getContentPane().add(lblNewLabel_1);

    // Add ActionListener for Save button
       saveButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               try {
                   // Extract modified data from input fields
                   String date = daterdv.getText();
                   String clientID = clientid.getText();
                   String agentID = agentid.getText();
                   String propID = propid.getText();
                   String typeRDV = null;
                   if (rdbtnplanif.isSelected()) {
                       typeRDV = "planifie";
                   } else if (rdbtnannule.isSelected()) {
                       typeRDV = "annule";
                   } else if (rdbtnrealise.isSelected()) {
                       typeRDV = "realise";
                   }

                   // Construct SQL UPDATE statement
                   String updateQuery = "UPDATE RDV SET DATE_ET_HEURE_NEW=?, CLIENT_ID=?, AGENT_ID=?, PROP_ID=?, TYPE_RDV=? WHERE ID=?";
                   PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                   preparedStatement.setString(1, date);
                   preparedStatement.setString(2, clientID);
                   preparedStatement.setString(3, agentID);
                   preparedStatement.setString(4, propID);
                   preparedStatement.setString(5, typeRDV);
                   preparedStatement.setInt(6, rdvID); // rdvID is the ID of the RDV being modified

                   // Execute the update statement
                   int rowsAffected = preparedStatement.executeUpdate();

                   if (rowsAffected > 0) {
                       JOptionPane.showMessageDialog(null, "RDV details updated successfully.");

                       // Refresh the RDV table after modification
                       refreshrdvTable();

                       // Clear input fields after modification
                       daterdv.setText("");
                       clientid.setText("");
                       agentid.setText("");
                       propid.setText("");
                       group.clearSelection(); // Deselect radio buttons
                   } else {
                       JOptionPane.showMessageDialog(null, "Failed to update RDV details.");
                   }
               } catch (SQLException ex) {
                   ex.printStackTrace();
                   JOptionPane.showMessageDialog(null, "Failed to update RDV details.");
               }
           }
       });

       
       
       
       
       
       
	    
	}
	
	 private void refreshrdvTable() {
	        try {
	            String sql = "SELECT * FROM RDV";
	            statement = connection.createStatement();
	            ResultSet rs = statement.executeQuery(sql);
	            table.setModel(DbUtils.resultSetToTableModel(rs));
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Failed to refresh client table.");
	        }
	    }


}