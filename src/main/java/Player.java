public class Player extends Entity {
    private String id;
    private String Nick;
    private int level;
    private int teamId;
    private String team=null;

    public Player(String ID, String nick, int level, int teamId, String team) {
        this.id = ID;
        Nick = nick;
        this.level = level;
        this.teamId = teamId;
        this.team = team;
    }

    public String getTeam() {return team;}
    public void setTeam(String team) {this.team = team;}

    public int getTeamId() {return teamId;}
    public void setTeamId(int teamId) {this.teamId = teamId;}
    public int getLevel() {return level;}
    public void setLevel(int level) {this.level = level;}
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNick() {
        return Nick;
    }
    public void setNick(String nick) {
        Nick = nick;
    }

    public void SayMyName() {
        System.out.println("ID: " + getId());
        System.out.println("NICK: " + getNick());
        System.out.println("LEVEL: " +getLevel());
        System.out.println("TEAM #" + getTeamId());
        System.out.println("TEAMNAME :" + getTeam());
    }

    public Player(String ID, String nick) {
        this.id = ID;
        Nick = nick;
    }

    public Player(String ID, String nick, int level) {
        this.id = ID;
        Nick = nick;
        this.level = level;
    }

    public Player(String ID, String nick, int level, int teamId) {
        this.id = ID;
        this.Nick = nick;
        this.level = level;
        this.teamId = teamId;
    }

    public Player() {
    }
}
