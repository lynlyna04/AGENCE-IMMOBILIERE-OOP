package app;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.ImageIcon;

public class clientinter extends JFrame {
    private JTable table; // Move the table variable here
    private JTextField nomclient;
    private JTextField prenomcli;
    private JTextField adrcli;
    private JTextField telcli;
    private JTextField emailcli;
    private int clientID;

    private Connection connection;
    private Statement statement;



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
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setBounds(557, 11, 127, 46);
        getContentPane().add(lblNewLabel);

        nomclient = new JTextField();
        nomclient.setBackground(Color.WHITE);
        nomclient.setForeground(Color.BLACK);
        nomclient.setFont(new Font("Tahoma", Font.PLAIN, 16));
        nomclient.setBounds(231, 77, 286, 46);
        getContentPane().add(nomclient);
        nomclient.setColumns(10);

        prenomcli = new JTextField();
        prenomcli.setBackground(Color.WHITE);
        prenomcli.setForeground(Color.BLACK);
        prenomcli.setFont(new Font("Tahoma", Font.PLAIN, 16));
        prenomcli.setColumns(10);
        prenomcli.setBounds(231, 154, 286, 46);
        getContentPane().add(prenomcli);

        adrcli = new JTextField();
        adrcli.setBackground(Color.WHITE);
        adrcli.setForeground(Color.BLACK);
        adrcli.setFont(new Font("Tahoma", Font.PLAIN, 16));
        adrcli.setColumns(10);
        adrcli.setBounds(231, 308, 286, 46);
        getContentPane().add(adrcli);

        telcli = new JTextField();
        telcli.setBackground(Color.WHITE);
        telcli.setForeground(Color.BLACK);
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
        rdbtnAcheteur.setForeground(Color.BLACK);
        rdbtnAcheteur.setBackground(Color.WHITE);
        rdbtnAcheteur.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnAcheteur.setBounds(231, 453, 127, 23);
        getContentPane().add(rdbtnAcheteur);

        JRadioButton rdbtnLocataire = new JRadioButton("LOCATAIRE");
        rdbtnLocataire.setBackground(Color.WHITE);
        rdbtnLocataire.setForeground(Color.BLACK);
        rdbtnLocataire.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnLocataire.setBounds(360, 453, 127, 23);
        getContentPane().add(rdbtnLocataire);

        JRadioButton rdbtnVendeur = new JRadioButton("VENDEUR");
        rdbtnVendeur.setBackground(Color.WHITE);
        rdbtnVendeur.setForeground(Color.BLACK);
        rdbtnVendeur.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rdbtnVendeur.setBounds(231, 499, 109, 23);
        getContentPane().add(rdbtnVendeur);

        JRadioButton rdbtnBailleur = new JRadioButton("BAILLEUR");
        rdbtnBailleur.setBackground(Color.WHITE);
        rdbtnBailleur.setForeground(Color.BLACK);
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
        AJTCLIENT.setBackground(Color.WHITE);
        AJTCLIENT.setForeground(Color.BLACK);
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
        emailcli.setBackground(Color.WHITE);
        emailcli.setForeground(Color.BLACK);
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
        clearButton.setForeground(Color.BLACK);
        clearButton.setBackground(Color.WHITE);
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
        AFFICHCLI.setBackground(Color.WHITE);
        AFFICHCLI.setForeground(Color.BLACK);
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


                try {
                    if (connection != null && !connection.isClosed()) {
                        statement = connection.createStatement();
                        // Votre code pour exécuter la requête SQL
                    } else {
                        System.out.println("La connexion est null ou fermée.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
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

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create a JTextField for search queries
        JTextField searchField = new JTextField();
        searchField.setBackground(Color.WHITE);
        searchField.setForeground(Color.BLACK);
        searchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchField.setBounds(821, 21, 200, 30);
        getContentPane().add(searchField);

        // Create a JComboBox for selecting search options
        String[] searchOptions = {"Nom", "Prenom", "Adresse", "Type Client"};
        JComboBox<String> searchOptionsComboBox = new JComboBox<>(searchOptions);
        searchOptionsComboBox.setBackground(Color.WHITE);
        searchOptionsComboBox.setForeground(Color.BLACK);
        searchOptionsComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchOptionsComboBox.setBounds(694, 21, 84, 30);
        getContentPane().add(searchOptionsComboBox);

        // Create a JButton for triggering the search operation
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.WHITE);
        searchButton.setForeground(Color.BLACK);
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchButton.setBounds(1021, 21, 100, 30);
        getContentPane().add(searchButton);




        // Create Delete button
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.WHITE);
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        deleteButton.setBounds(694, 469, 125, 46);
        getContentPane().add(deleteButton);


        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    BigDecimal idValue = (BigDecimal) table.getValueAt(selectedRow, 0);
                    int clientID = idValue.intValueExact();

                    try {
                        statement = connection.createStatement();
                        String sql = "DELETE FROM CLIENT WHERE ID = " + clientID;
                        statement.execute(sql);
                        refreshClientTable(); // Refresh the client table after deletion
                        JOptionPane.showMessageDialog(null, "Client deleted successfully.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Failed to delete client.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a client to delete.");
                }
            }
        });








        // Create Modify button
        JButton modifyButton = new JButton("Modify");
        modifyButton.setBackground(Color.WHITE);
        modifyButton.setForeground(Color.BLACK);
        modifyButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        modifyButton.setBounds(886, 469, 100, 46);
        getContentPane().add(modifyButton);

        // Add ActionListener for Modify button
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a client to modify.");
                    return;
                }
                BigDecimal idValue = (BigDecimal) table.getValueAt(selectedRow, 0);
                clientID = idValue.intValueExact(); // Set the class-level clientID variable

                // Retrieve client details from the database based on the clientID
                try {
                    statement = connection.createStatement();
                    String query = "SELECT * FROM CLIENT WHERE ID = " + clientID;
                    ResultSet rs = statement.executeQuery(query);
                    if (rs.next()) {
                        // Populate input fields with client information
                        nomclient.setText(rs.getString("nom"));
                        prenomcli.setText(rs.getString("prenom"));
                        adrcli.setText(rs.getString("adresse"));
                        telcli.setText(rs.getString("telephone"));
                        emailcli.setText(rs.getString("email"));

                        // Select appropriate radio button based on type
                        String type = rs.getString("type_client");
                        switch (type) {
                            case "acheteur":
                                rdbtnAcheteur.setSelected(true);
                                break;
                            case "locataire":
                                rdbtnLocataire.setSelected(true);
                                break;
                            case "vendeur":
                                rdbtnVendeur.setSelected(true);
                                break;
                            case "bailleur":
                                rdbtnBailleur.setSelected(true);
                                break;
                            default:
                                break;
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to retrieve client details for modification.");
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
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\kooki\\Downloads\\Untitled design (1) (1).png"));
        lblNewLabel_1.setBounds(10, 0, 1263, 700);
        getContentPane().add(lblNewLabel_1);

        // Add ActionListener for Save button
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Extract modified data from input fields
                    String nom = nomclient.getText();
                    String prenom = prenomcli.getText();
                    String adresse = adrcli.getText();
                    String telephone = telcli.getText();
                    String email = emailcli.getText();
                    String typeclient = null;
                    if (rdbtnAcheteur.isSelected()) {
                        typeclient = "acheteur";
                    } else if (rdbtnLocataire.isSelected()) {
                        typeclient = "locataire";
                    } else if (rdbtnVendeur.isSelected()) {
                        typeclient = "vendeur";
                    } else if (rdbtnBailleur.isSelected()) {
                        typeclient = "bailleur";
                    }

                    // Construct SQL UPDATE statement
                    String updateQuery = "UPDATE CLIENT SET nom=?, prenom=?, adresse=?, telephone=?, email=?, type_client=? WHERE ID=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                    preparedStatement.setString(1, nom);
                    preparedStatement.setString(2, prenom);
                    preparedStatement.setString(3, adresse);
                    preparedStatement.setString(4, telephone);
                    preparedStatement.setString(5, email);
                    preparedStatement.setString(6, typeclient);
                    preparedStatement.setInt(7, clientID); // clientID is the ID of the client being modified

                    // Execute the update statement
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Client details updated successfully.");

                        // Refresh the client table after modification
                        refreshClientTable();

                        // Clear input fields after modification
                        nomclient.setText("");
                        prenomcli.setText("");
                        adrcli.setText("");
                        telcli.setText("");
                        emailcli.setText("");
                        group.clearSelection(); // Deselect radio buttons
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update client details.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to update client details.");
                }
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






}