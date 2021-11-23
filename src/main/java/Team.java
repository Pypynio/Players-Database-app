public class Team extends Entity {
    private String id;
    private String teamName;

    public Team() {
    }

    public Team(String id, String teamName) {
        this.id = id;
        this.teamName = teamName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void sayMyName(){
        System.out.println("ID: " + getId());
        System.out.println("TeamName: " + getTeamName());
    }
}
