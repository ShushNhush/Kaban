package app.entities;

public class Team {

    private int teamId;

    private String teamName;

    private String password;


    public Team(String teamName, String password) {
        this.teamName = teamName;
        this.password = password;
    }

    public Team(int teamId, String teamName, String password) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.password = password;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
