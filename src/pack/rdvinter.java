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

public class rdvinter extends JFrame {

	    private JTextField daterdv;
	    private JTextField clientid;
	    private JTextField agentid;
	    private JTextField propid;

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
       lblNom.setHorizontalAlignment(SwingConstants.LEFT);
       lblNom.setFont(new Font("Tahoma", Font.PLAIN, 20));
       lblNom.setBounds(59, 75, 175, 46);
       getContentPane().add(lblNom);
       
       JLabel lblPrenom = new JLabel("CLIENT :");
       lblPrenom.setHorizontalAlignment(SwingConstants.LEFT);
       lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 20));
       lblPrenom.setBounds(59, 152, 127, 46);
       getContentPane().add(lblPrenom);
       
       JLabel lblAdresse = new JLabel("AGENT :");
       lblAdresse.setHorizontalAlignment(SwingConstants.LEFT);
       lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 20));
       lblAdresse.setBounds(59, 306, 127, 46);
       getContentPane().add(lblAdresse);
       
       JLabel lblType = new JLabel("TYPE :");
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
       rdbtnannule.setBounds(360, 412, 127, 23);
       getContentPane().add(rdbtnannule);

       JRadioButton rdbtnrealise = new JRadioButton("REALISE");
       rdbtnrealise.setHorizontalAlignment(SwingConstants.CENTER);
       rdbtnrealise.setFont(new Font("Tahoma", Font.PLAIN, 16));
       rdbtnrealise.setBounds(287, 455, 109, 23);
       getContentPane().add(rdbtnrealise);
       
       ButtonGroup group = new ButtonGroup();
       group.add(rdbtnplanif);
       group.add(rdbtnannule);
       group.add(rdbtnrealise);
       
       String typeclient = null;
       JButton AJTRDV = new JButton("AJOUTER");
       AJTRDV.addActionListener(new ActionListener() {
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
                   
                   String DATE = daterdv.getText();
                   String CLIENT = clientid.getText();
                   String AGENT = agentid.getText();
                   String PROP = propid.getText();
                   
                   String typeRDV = null;
                   if (rdbtnplanif.isSelected()) { typeRDV ="planifie";}
                   if (rdbtnannule.isSelected()) { typeRDV ="annule";}
                   if (rdbtnrealise.isSelected()) { typeRDV ="realise";}
                   
                   // SQL
                   String query = "INSERT INTO RDV VALUES ('"+ID+"','"+DATE+"','"+CLIENT+"','"+AGENT+"','"+PROP+"','"+typeRDV+"')";
                   
                   statement.execute(query);
                   JOptionPane.showMessageDialog(AJTRDV, "bien insere!");
                   
               } catch (Exception e1) {
                   e1.printStackTrace();
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
       
       JLabel lblEmail = new JLabel("PROPRIETE :");
       lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
       lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
       lblEmail.setBounds(59, 226, 127, 46);
       getContentPane().add(lblEmail);
       
       JButton clearButton = new JButton("New RDV");
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
                   case "DATE":
                       sql = "SELECT * FROM CLIENT WHERE date LIKE '%" + query + "%'";
                       break;
                   case "CLIENT ID":
                       sql = "SELECT * FROM CLIENT WHERE client_id LIKE '%" + query + "%'";
                       break;
                   case "PROP ID":
                       sql = "SELECT * FROM CLIENT WHERE prop_id '%" + query + "%'";
                       break;
                   case "AGENT ID":
                       sql = "SELECT * FROM CLIENT WHERE agent_id '%" + query + "%'";
                       break;    
                   case "Type RDV":
                       sql = "SELECT * FROM CLIENT WHERE LOWER(type_rdv) LIKE '%" + query + "%'";
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
