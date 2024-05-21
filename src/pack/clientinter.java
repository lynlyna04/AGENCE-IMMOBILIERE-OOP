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
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.ImageIcon;

public class clientinter extends JFrame {
    
    private JTextField nomclient;
    private JTextField prenomcli;
    private JTextField adrcli;
    private JTextField telcli;
    private JTextField emailcli;

    private Connection connection;
    private Statement statement;
    private JTable table;
    
 // Method to refresh the client table
    private void refreshClientTable() {
        try {
            String sql = "SELECT * FROM CLIENT";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to refresh client table.");
        }
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    clientinter window = new clientinter();
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
    public clientinter() {
    	getContentPane().setForeground(Color.WHITE);
    	getContentPane().setBackground(Color.BLACK);
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
        
        JLabel lblNewLabel = new JLabel("CLIENTS");
        lblNewLabel.setForeground(Color.GREEN);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setBounds(557, 11, 127, 46);
        getContentPane().add(lblNewLabel);
        
        nomclient = new JTextField();
        nomclient.setBackground(Color.BLACK);
        nomclient.setForeground(Color.WHITE);
        nomclient.setFont(new Font("Tahoma", Font.PLAIN, 16));
        nomclient.setBounds(231, 77, 286, 46);
        getContentPane().add(nomclient);
        nomclient.setColumns(10);
        
        prenomcli = new JTextField();
        prenomcli.setBackground(Color.BLACK);
        prenomcli.setForeground(Color.WHITE);
        prenomcli.setFont(new Font("Tahoma", Font.PLAIN, 16));
        prenomcli.setColumns(10);
        prenomcli.setBounds(231, 154, 286, 46);
        getContentPane().add(prenomcli);
        
        adrcli = new JTextField();
        adrcli.setBackground(Color.BLACK);
        adrcli.setForeground(Color.WHITE);
        adrcli.setFont(new Font("Tahoma", Font.PLAIN, 16));
        adrcli.setColumns(10);
        adrcli.setBounds(231, 308, 286, 46);
        getContentPane().add(adrcli);
        
        telcli = new JTextField();
        telcli.setBackground(Color.BLACK);
        telcli.setForeground(Color.WHITE);
        telcli.setFont(new Font("Tahoma", Font.PLAIN, 16));
        telcli.setColumns(10);
        telcli.setBounds(231, 377, 286, 46);
        getContentPane().add(telcli);
        
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
        
        JLabel lblType = new JLabel("TYPE :");
        lblType.setForeground(Color.WHITE);
        lblType.setHorizontalAlignment(SwingConstants.LEFT);
        lblType.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblType.setBounds(59, 439, 127, 46);
        getContentPane().add(lblType);
        
        JRadioButton rdbtnAcheteur = new JRadioButton("ACHETEUR");
        rdbtnAcheteur.setForeground(Color.MAGENTA);
        rdbtnAcheteur.setBackground(Color.BLACK);
        rdbtnAcheteur.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnAcheteur.setBounds(231, 453, 127, 23);
        getContentPane().add(rdbtnAcheteur);

        JRadioButton rdbtnLocataire = new JRadioButton("LOCATAIRE");
        rdbtnLocataire.setBackground(Color.BLACK);
        rdbtnLocataire.setForeground(Color.MAGENTA);
        rdbtnLocataire.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnLocataire.setBounds(360, 453, 127, 23);
        getContentPane().add(rdbtnLocataire);

        JRadioButton rdbtnVendeur = new JRadioButton("VENDEUR");
        rdbtnVendeur.setBackground(Color.BLACK);
        rdbtnVendeur.setForeground(Color.MAGENTA);
        rdbtnVendeur.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnVendeur.setBounds(231, 499, 109, 23);
        getContentPane().add(rdbtnVendeur);

        JRadioButton rdbtnBailleur = new JRadioButton("BAILLEUR");
        rdbtnBailleur.setBackground(Color.BLACK);
        rdbtnBailleur.setForeground(Color.MAGENTA);
        rdbtnBailleur.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnBailleur.setBounds(360, 499, 127, 23);
        getContentPane().add(rdbtnBailleur);
        
        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnAcheteur);
        group.add(rdbtnLocataire);
        group.add(rdbtnVendeur);
        group.add(rdbtnBailleur);
        
        String typeclient = null;
        JButton AJTCLIENT = new JButton("AJOUTER");
        AJTCLIENT.setBackground(Color.BLACK);
        AJTCLIENT.setForeground(Color.GREEN);
        AJTCLIENT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    statement = connection.createStatement();
                    
                    // Fetch the current maximum ID from the database
                    ResultSet rs = statement.executeQuery("SELECT MAX(ID) AS max_id FROM CLIENT");
                    int maxID = 0;
                    if (rs.next()) {
                        maxID = rs.getInt("max_id");
                    }
                    // Increment the ID
                    int ID = maxID + 1;
                    
                    String nom = nomclient.getText();
                    String prenom = prenomcli.getText();
                    String adresse = adrcli.getText();
                    String telephone = telcli.getText();
                    String email = emailcli.getText();
                    
                    String typeclient = null;
                    if (rdbtnAcheteur.isSelected()) { typeclient ="acheteur";}
                    if (rdbtnLocataire.isSelected()) { typeclient ="locataire";}
                    if (rdbtnVendeur.isSelected()) { typeclient ="vendeur";}
                    if (rdbtnBailleur.isSelected()) { typeclient ="bailleur";}
                    
                    // SQL
                    String query = "INSERT INTO CLIENT VALUES ('"+ID+"','"+nom+"','"+prenom+"','"+adresse+"','"+telephone+"','"+email+"','"+typeclient+"')";
                    
                    statement.execute(query);
                    JOptionPane.showMessageDialog(AJTCLIENT, "bien insere!");
                    
                } catch (Exception e1) {
                    e1.printStackTrace();
                }   
            }
        });
        
        AJTCLIENT.setFont(new Font("Tahoma", Font.PLAIN, 16));
        AJTCLIENT.setBounds(59, 562, 123, 46);
        getContentPane().add(AJTCLIENT);
        
        emailcli = new JTextField();
        emailcli.setBackground(Color.BLACK);
        emailcli.setForeground(Color.WHITE);
        emailcli.setFont(new Font("Tahoma", Font.PLAIN, 16));
        emailcli.setColumns(10);
        emailcli.setBounds(231, 228, 286, 46);
        getContentPane().add(emailcli);
        
        JLabel lblEmail = new JLabel("EMAIL :");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEmail.setBounds(59, 226, 127, 46);
        getContentPane().add(lblEmail);
        
        JButton clearButton = new JButton("New client");
        clearButton.setForeground(Color.GREEN);
        clearButton.setBackground(Color.BLACK);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Clear the text in each JTextField
                nomclient.setText("");
                prenomcli.setText("");
                adrcli.setText("");
                telcli.setText("");
                emailcli.setText("");
                
                // Deselect radio buttons
                group.clearSelection();
            }
        });
        clearButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        clearButton.setBounds(451, 562, 123, 46);
        getContentPane().add(clearButton);

        
        JButton AFFICHCLI = new JButton("AFFICHER CLIENTS");
        AFFICHCLI.setBackground(Color.BLACK);
        AFFICHCLI.setForeground(Color.GREEN);
        AFFICHCLI.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String sql="SELECT * FROM CLIENT";
        		
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
        AFFICHCLI.setBounds(231, 562, 175, 46);
        getContentPane().add(AFFICHCLI);
        
        JScrollPane tabcli = new JScrollPane();
        tabcli.setBounds(565, 95, 691, 326);
        getContentPane().add(tabcli);
        
        table = new JTable();
        tabcli.setViewportView(table);
        
     // After initializing the table
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
     // Create a JTextField for search queries
        JTextField searchField = new JTextField();
        searchField.setBackground(Color.BLACK);
        searchField.setForeground(Color.WHITE);
        searchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchField.setBounds(821, 21, 200, 30);
        getContentPane().add(searchField);

        // Create a JComboBox for selecting search options
        String[] searchOptions = {"Nom", "Prenom", "Adresse", "Type Client"};
        JComboBox<String> searchOptionsComboBox = new JComboBox<>(searchOptions);
        searchOptionsComboBox.setBackground(Color.BLACK);
        searchOptionsComboBox.setForeground(Color.WHITE);
        searchOptionsComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchOptionsComboBox.setBounds(694, 21, 84, 30);
        getContentPane().add(searchOptionsComboBox);

        // Create a JButton for triggering the search operation
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.BLACK);
        searchButton.setForeground(new Color(204, 0, 204));
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchButton.setBounds(1021, 21, 100, 30);
        getContentPane().add(searchButton);
        
     // Create Delete button
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.BLACK);
        deleteButton.setForeground(Color.MAGENTA);
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        deleteButton.setBounds(800, 469, 100, 46);
        getContentPane().add(deleteButton);

        // Add ActionListener for Delete button
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a client to delete.");
                    return;
                }
                int clientID = (int) table.getValueAt(selectedRow, 0); // Assuming ID is in the first column
                System.out.println("Selected client ID: " + clientID); // Debug statement
                try {
                    // Execute SQL DELETE statement
                    String sql = "DELETE FROM CLIENT WHERE ID LIKE '%" + clientID + "%'";
                    PreparedStatement statement = connection.prepareStatement(sql);
                 // After executing the DELETE statement
                    System.out.println("Client deleted successfully."); // Debug statement
                    statement.setInt(1, clientID);
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Client deleted successfully.");
                    // Refresh table after deletion
                    refreshClientTable();
                    System.out.println("Table refreshed successfully."); // Debug statement
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to delete client.");
                }
            }
        });

        // Create Modify button
        JButton modifyButton = new JButton("Modify");
        modifyButton.setBackground(Color.BLACK);
        modifyButton.setForeground(Color.MAGENTA);
        modifyButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        modifyButton.setBounds(950, 469, 100, 46);
        getContentPane().add(modifyButton);

        // Add ActionListener for Modify button
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a client to modify.");
                    return;
                }
                int clientID = (int) table.getValueAt(selectedRow, 0); // Assuming ID is in the first column
                // Retrieve client details from the database based on ID and populate input fields for modification
                // After modification, execute SQL UPDATE statement to update client details
            }
        });
    

     

        
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
                    case "Type Client":
                        sql = "SELECT * FROM CLIENT WHERE LOWER(type_client) LIKE '%" + query + "%'";
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
