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


public class transinter extends JFrame {
	    private JTextField dateTRANS;
	    private JTextField clientid;
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
	       
	       
	       setTitle("Client Interface");
	       setSize(1343, 737);
	       setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	       getContentPane().setLayout(null);
	       
	       JLabel lblNewLabel = new JLabel("TRANSACTIONS");
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
	       lblNom.setHorizontalAlignment(SwingConstants.LEFT);
	       lblNom.setFont(new Font("Tahoma", Font.PLAIN, 20));
	       lblNom.setBounds(59, 134, 175, 46);
	       getContentPane().add(lblNom);
	       
	       JLabel lblPrenom = new JLabel("CLIENT :");
	       lblPrenom.setHorizontalAlignment(SwingConstants.LEFT);
	       lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 20));
	       lblPrenom.setBounds(59, 222, 127, 46);
	       getContentPane().add(lblPrenom);
	       
	       JLabel lblType = new JLabel("TYPE :");
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
	       AJTTRANS.addActionListener(new ActionListener() {
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
	                   
	                   String DATE = dateTRANS.getText();
	                   String CLIENT = clientid.getText();
	                   String PROP = propid.getText();
	                   
	                   String typetrans= null;
	                   if (rdbtnvente.isSelected()) { typetrans ="vente";}
	                   if (rdbtnloca.isSelected()) { typetrans ="location";}
	                   
	                   // SQL
	                   String query = "INSERT INTO RDV VALUES ('"+ID+"','"+DATE+"','"+CLIENT+"','"+PROP+"','"+typetrans+"')";
	                   
	                   statement.execute(query);
	                   JOptionPane.showMessageDialog(AJTTRANS, "bien insere!");
	                   
	               } catch (Exception e1) {
	                   e1.printStackTrace();
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
	       lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
	       lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
	       lblEmail.setBounds(59, 309, 127, 46);
	       getContentPane().add(lblEmail);
	       
	       JButton clearButton = new JButton("New transaction");
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
	       AFFICHTRANS.addActionListener(new ActionListener() {
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
	       searchOptionsComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
	       searchOptionsComboBox.setBounds(727, 31, 84, 30);
	       getContentPane().add(searchOptionsComboBox);

	       // Create a JButton for triggering the search operation
	       JButton searchButton = new JButton("Search");
	       searchButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
	       searchButton.setBounds(1021, 31, 100, 30);
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
	                   case "Type trans":
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
