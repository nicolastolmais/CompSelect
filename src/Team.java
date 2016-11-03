import java.util.ArrayList;

public class Team {
	private String teamName;
	private ArrayList<String> teamMembers;
	private ArrayList<String> banMembers;
	private ArrayList<String> teamAttributes;
	
	public Team(String teamName){
		this.teamName = teamName;
		teamMembers = new ArrayList<String>();
		banMembers = new ArrayList<String>();
		teamAttributes = new ArrayList<String>();
	}
	
	
	public ArrayList<String> getTeamAttributes() {
		return teamAttributes;
	}


	public void addTeamAttributes(String tag) {
		teamAttributes.add(tag);
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
	
	public String findMissingAttributes(){
		boolean marksman = false;
		boolean support = false;
		boolean tank = false;
		boolean mage = false;
		boolean fighter = false;
		boolean assassin = false;
		
		for(String tag : teamAttributes){
			if(tag.equals("Marksman")){
				marksman = true;
			}
		}
		for(String tag : teamAttributes){
			if(tag.equals("Support")){
				support = true;
			}
		}
		for(String tag : teamAttributes){
			if(tag.equals("Tank")){
				tank = true;
			}
		}
		for(String tag : teamAttributes){
			if(tag.equals("Mage")){
				mage = true;
			}
		}
		for(String tag : teamAttributes){
			if(tag.equals("Fighter")){
				fighter = true;
			}
		}
		for(String tag : teamAttributes){
			if(tag.equals("Assassin")){
				assassin = true;
			}
		}
		
		String missingAttributes= "";
		if(marksman == false){
			missingAttributes += " No Marksman /";
		}
		if(support == false){
			missingAttributes += " No Support /";
		}
		if(tank == false){
			missingAttributes += " No Tank /";
		}
		if(mage == false){
			missingAttributes += " No Mage /";
		}
		if(fighter == false){
			missingAttributes += " No Fighter /";
		}
		if(assassin == false){
			missingAttributes += " No Assassin /";
		}
		
		return missingAttributes;
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
