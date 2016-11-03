import java.util.ArrayList;

public class Team {
	private String teamName;
	private ArrayList<String> teamMembers;
	private ArrayList<String> banMembers;
	
	public Team(String teamName){
		this.teamName = teamName;
		teamMembers = new ArrayList<String>();
		banMembers = new ArrayList<String>();
	}
	
	public void addChampion(String name){
		teamMembers.add(name);
	}
	
	public ArrayList<String> getTeamChampions(){
		return teamMembers;
	}
	
	public ArrayList<String> getBannedChampions() {
		return banMembers;
	}

	public void addBan(String name) {
		banMembers.add(name);
	}

	public String toString(){
		StringBuilder team = new StringBuilder(100);
		team.append(teamName);
		team.append(" Picks: ");
		for(String champion : teamMembers){
			team.append(champion + " / ");
		}
		team.append("  ||   Bans: ");
		for(String champion : banMembers){
			team.append(champion  + " / ");
		}
		String statement = team.toString();
		return statement;
	}
}
