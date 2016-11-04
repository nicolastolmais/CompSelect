import java.util.ArrayList;

public class Team {
	private String teamName;
	private ArrayList<String> teamMembers;
	private ArrayList<String> banMembers;
	private ArrayList<String> teamAttributes;
	private int adStat;
	private int apStat;
	private int defenseStat;
	private int meleeChamps;
	
	public Team(String teamName){
		this.teamName = teamName;
		teamMembers = new ArrayList<String>();
		banMembers = new ArrayList<String>();
		teamAttributes = new ArrayList<String>();
	}
	
	
	public String getTeamName(){
		return teamName;
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
			} else if(tag.equals("Support")){
				support = true;
			} else if(tag.equals("Tank")){
				tank = true;
			} else if(tag.equals("Mage")){
				mage = true;
			} else if(tag.equals("Fighter")){
				fighter = true;
			} else if(tag.equals("Assassin")){
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

	
	public int getAdStat() {
		return adStat;
	}

	public void addAdStat(int adStat) {
		this.adStat += adStat;
	}

	public int getApStat() {
		return apStat;
	}

	public void addApStat(int apStat) {
		this.apStat += apStat;
	}
	
	public String adVSap(){
		if((adStat - apStat) >= 10){
			return "Too much AD";
		} else if((apStat - adStat) >= 10){
			return "Too much AP";
		} else {
			return "Good balance of AD and AP";
		}
	}


	public int getDefenseStat() {
		return defenseStat;
	}

	public void addDefenseStat(int defenseStat) {
		this.defenseStat += defenseStat;
	}
	
	public String attackVSdefense(){
		int attackStat = (adStat + apStat) / 2;
		if((attackStat - defenseStat) >= 5){
			return "All ATTACK";
		} else if((attackStat - defenseStat) >= 2){
			return "Mostly ATTACL";
		} else if((defenseStat - attackStat) >= 5){
			return "All DEFENSE";
		} else if((defenseStat - attackStat) >= 2){
			return "Mostly DEFENSE";
		} else {
			return "Good balance of ATTACK and DEFENSE";
		}
	}

	public int getMeleeChamps() {
		return meleeChamps;
	}

	public void addMeleeChamp(boolean melee) {
		if(melee){
			this.meleeChamps += 1;
		}
	}
	
	public String meleeVSrange(){
		if(this.meleeChamps == 5){
			return "No ranged champions";
		} else if(this.meleeChamps == 4){
			return "Short ranged team";
		} else if(this.meleeChamps == 3){
			return "Balance of range and melee";
		} else if(this.meleeChamps == 2){
			return "Good number of ranged champions";
		} else {
			return "Poke composition";
		}
		
	}
}
