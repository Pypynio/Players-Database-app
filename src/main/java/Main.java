
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            PropertiesLoader.readProperties();
            PlayerDAOimpl playerDAOimpl = new PlayerDAOimpl();
/*            System.out.println("~~~~~~~~~");
            playerDAOimpl.selectAllPlayers();
            playerDAOimpl.countPlayers();
            playerDAOimpl.countTeamMembers(QueryBuilder.displayNumberOfMembers("2"));
            playerDAOimpl.genericSelect("players", "3");*/
            Scanner scanner = new Scanner(System.in);
            boolean shouldContinue = true;
            while (shouldContinue) {
                System.out.println("Wybierz opcję: ");
                System.out.println("1. Wyświetl wszystkich graczy ");
                System.out.println("2. Dodaj gracza do bazy ");
                System.out.println("3. Dodaj gracza z losowym nickiem ");
                System.out.println("4. Znajdź gracza po nazwie ");
                System.out.println("5. Znajdź rekord po  tabeli, ID ");
                System.out.println("6. Usuń gracza po ID ");
                System.out.println("7. Usuń gracza po nicku ");
                System.out.println("8. Wyświetl liczbe graczy ");
                System.out.println("9. Wyświetl liczbę członków drużyny");
                System.out.println(("10. Wyjście"));

                int userChoice = scanner.nextInt();
                switch (userChoice) {
                    case 1 -> playerDAOimpl.selectAllPlayers();
                    case 2 -> playerDAOimpl.insert();
                    case 3 -> playerDAOimpl.secondPlayerInsert();
                    case 4 -> playerDAOimpl.selectPlayerByNick();
                    case 5 -> playerDAOimpl.genericSelect();
                    case 6 -> playerDAOimpl.deletePlayerById();
                    case 7 -> playerDAOimpl.deletePlayerByNick();
                    case 8 -> playerDAOimpl.countPlayers();
                    case 9 -> playerDAOimpl.countTeamMembers();
                   case 10 -> shouldContinue = false;
                    default -> {
                        throw new IllegalStateException("Podaj cyfrę od 1 do 10 ");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}