import java.util.ArrayList;

public class Team {
	private ArrayList<String> teamMembers;
	private boolean isPicking;
	
	public Team(){
		teamMembers = new ArrayList<String>();
	}
	
	public void addChampion(String name){
		teamMembers.add(name);
	}
	
	public ArrayList<String> getTeamChampions(){
		return teamMembers;
	}
	
	public boolean getIsPicking(){
		return isPicking;
		
	}
	public void setIsPicking(boolean bool){
		bool = isPicking;
	}
	
	public String toString(){
		StringBuilder team = new StringBuilder(100);
		for(String champion : teamMembers){
			team.append(champion);
		}
		String statement = team.toString();
		return statement;
	}
}
