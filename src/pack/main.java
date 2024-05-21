package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String reponse1, reponse2, reponse3, reponse4;

        // Création des services
        ServiceClient serviceClient = new ServiceClient();
        List<Agent> agents = new ArrayList<>();
        ServiceRDV serviceRDV = new ServiceRDV();
        ServicePropriete servicePropriete = new ServicePropriete();
        ServiceTransaction serviceTransaction = new ServiceTransaction();

        do {
            System.out.println("Quel service souhaitez-vous utiliser ? (Personnes/RDV/Proprietes/Recherche/Transactions/Pas de service)");
            reponse1 = scanner.nextLine();

            switch (reponse1.toLowerCase()) {
                case "personnes":
                    do {
                        System.out.println("Voulez-vous effectuer une modification dans le service personnes ? (Oui/Non)");
                        reponse2 = scanner.nextLine();
                        if (reponse2.equalsIgnoreCase("Oui")) {
                            System.out.println("Sur quel type de propriétés voulez-vous effectuer votre modification ? (Client/Agent)");
                            reponse3 = scanner.nextLine();
                            switch (reponse3.toLowerCase()) {
                                case "client":
                                    System.out.println("Quelle modification voulez-vous apporter au service ? (Ajout/Suppression/Modification)");
                                    reponse4 = scanner.nextLine();
                                    switch (reponse4.toLowerCase()) {
                                        case "ajout":
                                            System.out.println("Entrez les détails du client :");
                                            Client nouveauClient = Client.createClientFromInput();
                                            serviceClient.ajouterClient(nouveauClient);
                                            break;
                                        case "suppression":
                                            System.out.println("Entrez l'ID du client à supprimer : ");
                                            int idSuppressionClient = scanner.nextInt();
                                            scanner.nextLine();
                                            Client clientASupprimer = serviceClient.getClientParId(idSuppressionClient);
                                            if (clientASupprimer != null) {
                                                serviceClient.supprimerClient(clientASupprimer);
                                            } else {
                                                System.out.println("Client non trouvé avec l'ID spécifié.");
                                            }
                                            break;
                                        case "modification":
                                            System.out.println("Donnez l'ID du client que vous voulez modifier : ");
                                            int idModificationClient = scanner.nextInt();
                                            scanner.nextLine();
                                            Client clientAModifier = serviceClient.getClientParId(idModificationClient);
                                            if (clientAModifier != null) {
                                                System.out.println("Entrez les nouveaux détails du client :");
                                                Client nouveauClientModifie = Client.createClientFromInput();
                                                nouveauClientModifie.setID(clientAModifier.getID()); // Garde l'ID inchangé
                                                serviceClient.modifierClient(nouveauClientModifie);
                                            } else {
                                                System.out.println("Client non trouvé avec l'ID spécifié.");
                                            }
                                            break;
                                        default:
                                            System.out.println("Option non reconnue.");
                                    }
                                    break;

                                case "agent":
                                    System.out.println("Quelle modification voulez-vous apporter au service ? (Ajout/Suppression/Modification)");
                                    reponse4 = scanner.nextLine();
                                    switch (reponse4.toLowerCase()) {
                                        case "ajout":
                                            System.out.println("Entrez les détails de l'agent :");
                                            Agent nouveauAgent = Agent.createAgentFromInput();
                                            agents.add(nouveauAgent);
                                            break;
                                        case "suppression":
                                            System.out.println("Entrez l'ID de l'agent à supprimer : ");
                                            int idSuppressionAgent = scanner.nextInt();
                                            scanner.nextLine();
                                            Agent agentASuppression = agents.stream().filter(agent -> agent.getID() == idSuppressionAgent).findFirst().orElse(null);
                                            if (agentASuppression != null) {
                                                agents.removeIf(agent -> agent.getID() == idSuppressionAgent);
                                            } else {
                                                System.out.println("Agent non trouvé avec l'ID spécifié.");
                                            }
                                            break;
                                        case "modification":
                                            System.out.println("Donnez l'ID de l'agent que vous voulez modifier : ");
                                            int idModificationAgent = scanner.nextInt();
                                            scanner.nextLine();
                                            Agent agentAModifier = agents.stream().filter(agent -> agent.getID() == idModificationAgent).findFirst().orElse(null);
                                            if (agentAModifier != null) {
                                                System.out.println("Entrez les nouveaux détails de l'agent :");
                                                Agent nouveauAgentModifie = Agent.createAgentFromInput();
                                                nouveauAgentModifie.setID(agentAModifier.getID()); 
                                                int index = agents.indexOf(agentAModifier);
                                                agents.set(index, nouveauAgentModifie);
                                            } else {
                                                System.out.println("Agent non trouvé avec l'ID spécifié.");
                                            }
                                            break;
                                        default:
                                            System.out.println("Option non reconnue.");
                                    }
                                    break;

                                default:
                                    System.out.println("Option non reconnue.");
                            }
                        }
                    } while (reponse2.equalsIgnoreCase("Oui"));

                    System.out.println("Liste des clients créés :");
                    for (Client client : serviceClient.getTousLesClients()) {
                        System.out.println(client);
                    }

                    System.out.println("Liste des agents créés :");
                    for (Agent agent : agents) {
                        System.out.println(agent);
                    }
                    break;

                case "rdv":
                    do {
                        System.out.println("Voulez-vous effectuer une modification dans le service RDVs ? (Oui/Non)");
                        reponse2 = scanner.nextLine();
                        if (reponse2.equalsIgnoreCase("Oui")) {
                            System.out.println("Quelle modification voulez-vous apporter au service ? (Ajout/Suppression/Modification)");
                            reponse4 = scanner.nextLine();
                            switch (reponse4.toLowerCase()) {
                                case "ajout":
                                    System.out.println("Entrez les détails du RDV :");
                                    RDV nouveauRDV = RDV.createRDVFromInput();
                                    serviceRDV.ajouterRDV(nouveauRDV);
                                    break;
                                case "suppression":
                                    System.out.println("Entrez l'ID du RDV à supprimer : ");
                                    int idSuppressionRDV = scanner.nextInt();
                                    scanner.nextLine();
                                    RDV rdvASupprimer = serviceRDV.getRDVParId(idSuppressionRDV);
                                    if (rdvASupprimer != null) {
                                        serviceRDV.supprimerRDV(rdvASupprimer);
                                    } else {
                                        System.out.println("RDV non trouvé avec l'ID spécifié.");
                                    }
                                    break;
                                case "modification":
                                    System.out.println("Donnez l'ID du RDV que vous voulez modifier : ");
                                    int idModificationRDV = scanner.nextInt();
                                    scanner.nextLine();
                                    RDV rdvAModifier = serviceRDV.getRDVParId(idModificationRDV);
                                    if (rdvAModifier != null) {
                                        System.out.println("Entrez les nouveaux détails du RDV :");
                                        RDV nouveauRDVModifie = RDV.createRDVFromInput();
                                        nouveauRDVModifie.setId(rdvAModifier.getId()); 
                                        serviceRDV.modifierRDV(nouveauRDVModifie);
                                    } else {
                                        System.out.println("RDV non trouvé avec l'ID spécifié.");
                                    }
                                    break;
                                default:
                                    System.out.println("Option non reconnue.");
                            }
                        }
                    } while (reponse2.equalsIgnoreCase("Oui"));

                    System.out.println("Liste des RDV créés :");
                    for (RDV rdv : serviceRDV.getTousRDV()) {
                        System.out.println(rdv);
                    }
                    break;

                case "proprietes":
                    do {
                        System.out.println("Voulez-vous effectuer une modification dans le service propriétés ? (Oui/Non)");
                        reponse2 = scanner.nextLine();
                        if (reponse2.equalsIgnoreCase("Oui")) {
                            System.out.println("Sur quel type de propriétés voulez-vous effectuer votre modification ? (Maison/Appartement)");
                            reponse3 = scanner.nextLine();
                            switch (reponse3.toLowerCase()) {
                                case "maison":
                                    System.out.println("Quelle modification voulez-vous apporter au service ? (Ajout/Suppression/Modification)");
                                    reponse4 = scanner.nextLine();
                                    switch (reponse4.toLowerCase()) {
                                        case "ajout":
                                            System.out.println("Entrez les détails de la maison à ajouter :");
                                            Maison nouvelleMaison = Maison.createMaisonFromInput();
                                            servicePropriete.ajouterPropriete(nouvelleMaison);
                                            break;
                                        case "suppression":
                                            System.out.println("Entrez l'ID de la maison à supprimer : ");
                                            int idSuppressionMaison = scanner.nextInt();
                                            scanner.nextLine();
                                            Propriete maisonASupprimer = servicePropriete.getProprieteParId(idSuppressionMaison);
                                            if (maisonASupprimer != null && maisonASupprimer instanceof Maison) {
                                                servicePropriete.supprimerPropriete(maisonASupprimer);
                                            } else {
                                                System.out.println("Maison non trouvée avec l'ID spécifié.");
                                            }
                                            break;
                                        case "modification":
                                            System.out.println("Donnez l'ID de la maison que vous voulez modifier : ");
                                            int idModificationMaison = scanner.nextInt();
                                            scanner.nextLine();
                                            Propriete maisonAModifier = servicePropriete.getProprieteParId(idModificationMaison);
                                            if (maisonAModifier != null && maisonAModifier instanceof Maison) {
                                                System.out.println("Entrez les nouveaux détails de la maison :");
                                                Maison nouvelleMaisonModifiee = Maison.createMaisonFromInput();
                                                nouvelleMaisonModifiee.setID(idModificationMaison); 
                                                servicePropriete.modifierPropriete(nouvelleMaisonModifiee);
                                            } else {
                                                System.out.println("Maison non trouvée avec l'ID spécifié.");
                                            }
                                            break;
                                        default:
                                            System.out.println("Option non reconnue.");
                                    }
                                    break;
                                case "appartement":
                                    System.out.println("Quelle modification voulez-vous apporter au service ? (Ajout/Suppression/Modification)");
                                    reponse4 = scanner.nextLine();
                                    switch (reponse4.toLowerCase()) {
                                        case "ajout":
                                            System.out.println("Entrez les détails de l'appartement à ajouter :");
                                            Appartement nouvelAppartement = Appartement.createAppartementFromInput();
                                            servicePropriete.ajouterPropriete(nouvelAppartement);
                                            break;
                                        case "suppression":
                                            System.out.println("Entrez l'ID de l'appartement à supprimer : ");
                                            int idSuppressionAppartement = scanner.nextInt();
                                            scanner.nextLine();
                                            Propriete appartementASupprimer = servicePropriete.getProprieteParId(idSuppressionAppartement);
                                            if (appartementASupprimer != null && appartementASupprimer instanceof Appartement) {
                                                servicePropriete.supprimerPropriete(appartementASupprimer);
                                            } else {
                                                System.out.println("Appartement non trouvé avec l'ID spécifié.");
                                            }
                                            break;
                                        case "modification":
                                            System.out.println("Donnez l'ID de l'appartement que vous voulez modifier : ");
                                            int idModificationAppartement = scanner.nextInt();
                                            scanner.nextLine();
                                            Propriete appartementAModifier = servicePropriete.getProprieteParId(idModificationAppartement);
                                            if (appartementAModifier != null && appartementAModifier instanceof Appartement) {
                                                System.out.println("Entrez les nouveaux détails de l'appartement :");
                                                Appartement nouvelAppartementModifie = Appartement.createAppartementFromInput();
                                                nouvelAppartementModifie.setID(idModificationAppartement); // Garde l'ID inchangé
                                                servicePropriete.modifierPropriete(nouvelAppartementModifie);
                                            } else {
                                                System.out.println("Appartement non trouvé avec l'ID spécifié.");
                                            }
                                            break;
                                        default:
                                            System.out.println("Option non reconnue.");
                                    }
                                    break;
                                default:
                                    System.out.println("Option non reconnue.");
                            }
                        }
                    } while (reponse2.equalsIgnoreCase("Oui"));

                    System.out.println("Liste des propriétés créées :");
                    for (Propriete propriete : servicePropriete.getToutesLesProprietes()) {
                        System.out.println(propriete);
                    }
                    break;

                case "recherche":
                    try {
                        System.out.println("Entrez les critères de recherche pour la propriété :");

                        System.out.print("Prix minimum : ");
                        double prixMin = scanner.nextDouble();

                        System.out.print("Prix maximum : ");
                        double prixMax = scanner.nextDouble();

                        scanner.nextLine(); // Consuming the leftover newline

                        System.out.print("Type de propriété (MAISON, APPARTEMENT, etc.) : ");
                        String typeString = scanner.nextLine();
                        TypePropriete type = null;
                        if (!typeString.isEmpty()) {
                            try {
                                type = TypePropriete.valueOf(typeString.toUpperCase());
                            } catch (IllegalArgumentException e) {
                                System.out.println("Type de propriété invalide.");
                                break;
                            }
                        }

                        System.out.print("Localisation : ");
                        String localisation = scanner.nextLine();

                        CritereRecherchePropriete criteres = new CritereRecherchePropriete(prixMin, prixMax, type, localisation);
                        List<Propriete> proprietesTrouvees = servicePropriete.rechercherProprietes(criteres);

                        if (proprietesTrouvees.isEmpty()) {
                            System.out.println("Aucune propriété ne correspond aux critères de recherche.");
                        } else {
                            System.out.println("Propriétés trouvées :");
                            for (Propriete propriete : proprietesTrouvees) {
                                System.out.println(propriete);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Erreur lors de la saisie des critères de recherche. Veuillez réessayer.");
                        scanner.nextLine(); 
                    }
                    break;

                    case "transactions":
                    do {
                        System.out.println("Voulez-vous effectuer une modification dans le service transactions ? (Oui/Non)");
                        reponse2 = scanner.nextLine();
                        if (reponse2.equalsIgnoreCase("Oui")) {
                            System.out.println("Quelle modification voulez-vous apporter au service ? (Ajout/Suppression/Modification)");
                            reponse4 = scanner.nextLine();
                            switch (reponse4.toLowerCase()) {
                                case "ajout":
                                    System.out.println("Entrez les détails de la transaction :");
                                    Transaction nouvelleTransaction = Transaction.createTransactionFromInput(servicePropriete, serviceClient);
                                    serviceTransaction.ajouterTransaction(nouvelleTransaction);
                                    break;
                                case "suppression":
                                    System.out.println("Entrez l'ID de la transaction à supprimer : ");
                                    int idSuppressionTransaction = scanner.nextInt();
                                    scanner.nextLine();
                                    Transaction transactionASupprimer = serviceTransaction.getTransactionParId(idSuppressionTransaction);
                                    if (transactionASupprimer != null) {
                                        serviceTransaction.supprimerTransaction(transactionASupprimer);
                                    } else {
                                        System.out.println("Transaction non trouvée avec l'ID spécifié.");
                                    }
                                    break;
                                case "modification":
                                    System.out.println("Donnez l'ID de la transaction que vous voulez modifier : ");
                                    int idModificationTransaction = scanner.nextInt();
                                    scanner.nextLine();
                                    Transaction transactionAModifier = serviceTransaction.getTransactionParId(idModificationTransaction);
                                    if (transactionAModifier != null) {
                                        System.out.println("Entrez les nouveaux détails de la transaction :");
                                        Transaction nouvelleTransactionModifiee = Transaction.createTransactionFromInput(servicePropriete, serviceClient);
                                        nouvelleTransactionModifiee.setID(transactionAModifier.getID()); 
                                        serviceTransaction.modifierTransaction(nouvelleTransactionModifiee);
                                    } else {
                                        System.out.println("Transaction non trouvée avec l'ID spécifié.");
                                    }
                                    break;
                                default:
                                    System.out.println("Option non reconnue.");
                            }
                        }
                    } while (reponse2.equalsIgnoreCase("Oui"));

                    System.out.println("Liste des transactions créées :");
                    for (Transaction transaction : serviceTransaction.getToutesLesTransactions()) {
                        System.out.println(transaction);
                    }
                    break;

                case "pas de service":
                    System.out.println("Fin du service.");
                    break;

                default:
                    System.out.println("Option non reconnue.");
            }

        } while (!reponse1.equalsIgnoreCase("Pas de service"));

        scanner.close();
    }
}