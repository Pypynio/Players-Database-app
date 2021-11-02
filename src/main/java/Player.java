public class Player {
    private String ID;
    private String Nick;
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getNick() {
        return Nick;
    }
    public void setNick(String nick) {
        Nick = nick;
    }
    public void SayMyName() {
        System.out.println("ID: " + getID());
        System.out.println("NICK: " + getNick());
    }

    public Player(String ID, String nick) {
        this.ID = ID;
        Nick = nick;
    }

    public Player() {
    }
}
