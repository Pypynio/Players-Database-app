public class Player {
    private String ID;
    private String Nick;
    private String team;
    private int level;
    private int teamId;

    public int getTeamId() {return teamId;}
    public void setTeamId(int teamId) {this.teamId = teamId;}
    public int getLevel() {return level;}
    public void setLevel(int level) {this.level = level;}
    public String getTeam() { return team;}
    public void setTeam(String team) {this.team = team;}
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
        System.out.println("TEAM: " +getTeam());
        System.out.println("LEVEL: " +getLevel());
    }

    public Player(String ID, String nick,String team) {
        this.ID = ID;
        Nick = nick;
        this.team = team;
    }

    public Player(String ID, String nick, String team, int level) {
        this.ID = ID;
        Nick = nick;
        this.team = team;
        this.level = level;
    }

    public Player(String ID, String nick, String team, int level, int teamId) {
        this.ID = ID;
        Nick = nick;
        this.team = team;
        this.level = level;
        this.teamId = teamId;
    }

    public Player(String ID, String nick, int level, int teamId) {
        this.ID = ID;
        Nick = nick;
        this.level = level;
        this.teamId = teamId;
    }

    public Player() {
    }
}
