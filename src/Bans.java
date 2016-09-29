import java.util.ArrayList;

public class Bans {

	private ArrayList<String> bannedChampions;
	
	public Bans(){
		 bannedChampions = new ArrayList<String>();
	}
	
	public void addBannedChampion(String name){
		bannedChampions.add(name);
	}
	
	public ArrayList<String> getBannedChampions(){
		return bannedChampions;
	}
	
	
	public String toString(){
		StringBuilder bans = new StringBuilder(100);
		for(String champion : bannedChampions){
			bans.append(champion);
			bans.append(" / ");
		}
		String statement = bans.toString();
		return statement;
	}
}
