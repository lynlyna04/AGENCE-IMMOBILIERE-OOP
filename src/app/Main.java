package app;
import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "ouabel";
        String password = "lyna";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            // Exemple de requête SQL
            String sql = "SELECT * FROM CLIENT";
            ResultSet resultSet = statement.executeQuery(sql);

            // Traitement des résultats de la requête
            while (resultSet.next()) {
                // Lire les données de la ligne courante
                int id = resultSet.getInt("ID");
                String nom = resultSet.getString("NOM");
                String prenom = resultSet.getString("PRENOM");
                String adresse = resultSet.getString("ADRESSE");
                String telephone = resultSet.getString("TELEPHONE");
                String email = resultSet.getString("EMAIL");
                String typeClient = resultSet.getString("TYPE_CLIENT");

                // Affichage des données
                System.out.println("ID : " + id);
                System.out.println("Nom : " + nom);
                System.out.println("Prenom : " + prenom);
                System.out.println("Adresse : " + adresse);
                System.out.println("Telephone : " + telephone);
                System.out.println("Email : " + email);
                System.out.println("Type Client : " + typeClient);
                System.out.println("---------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
