import queries.EntityType;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        PropertiesLoader.readProperties();
        PlayerDAO playerDAOimpl = new PlayerDAO();
        TeamDAO teamDAO = new TeamDAO();
        Player player = new Player();
        Team team = new Team();
        Scanner scanner = new Scanner(System.in);
        displayMenu();

    }

    public static void displayMenu() throws Exception {
        PropertiesLoader.readProperties();
        PlayerDAO playerDAOimpl = new PlayerDAO();
        TeamDAO teamDAO = new TeamDAO();
        Player player = new Player();
        Team team = new Team();
        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;
        while (shouldContinue) {
            System.out.println("Wybierz opcję: ");
            System.out.println("1. Read all players ");
            System.out.println("2. Read player by id ");
            System.out.println("3. Insert player ");
            System.out.println("4. Update player ");
            System.out.println("5. Delete player ");
            System.out.println("6. Read all teams ");
            System.out.println("7. Read team by id ");
            System.out.println("8. Insert team ");
            System.out.println("9. Update team");
            System.out.println(("10. Delete team"));
            int userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1 -> {List<Player> players = playerDAOimpl.selectAll();}
                case 2 -> player = playerDAOimpl.selectById("2");
                case 3 -> playerDAOimpl.insert(player);
                case 4 -> playerDAOimpl.update(player, "level", "88", "11");
                case 5 -> playerDAOimpl.delete(player, "11");
                case 6 -> {List<Team> teams = teamDAO.selectAll();}
                case 7 -> teamDAO.selectById("1");
                case 8 -> teamDAO.insert(team);
                case 9 -> teamDAO.update(team, "teamName", "SW", "4");
                case 10 -> teamDAO.delete(team, "4");
            }
        }
    }
}
