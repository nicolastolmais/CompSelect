import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;

import net.rithms.riot.constant.Region;
import net.rithms.riot.constant.Season;
import net.rithms.riot.dto.Champion.Champion;
import net.rithms.riot.dto.Champion.ChampionList;
import net.rithms.riot.dto.Summoner.Summoner;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;

import com.google.gson.*; 

public class compBuilder2 {

	public static void main(String[] args) throws RiotApiException{

		Scanner input = new Scanner(System.in);
		ArrayList<String> bans = new ArrayList<String>();
		Bans teamOneBans = new Bans();
		Bans teamTwoBans = new Bans();
		ArrayList<String> pics = new ArrayList<String>();
		Team teamOne = new Team();
		Team teamTwo = new Team();
		Bans allBans = new Bans();
		
		
		System.out.println("Welcome to COMP SELECT");
		System.out.println();
		
		for (int i = 0; i<6; i++)
		{
			boolean champCheck = false;
			System.out.println("Ban a Champion.");
			String ban = input.nextLine();
			while(champCheck == false){
				for(int j = 0; j< allBans.getBannedChampions().size();j++){
					if(allBans.getBannedChampions().get(j) == ban)
					{
						System.out.println("Champion already banned. Pick again.");
						ban = input.nextLine();
					}
				}
					
				if(checkIfChamp(ban) == true){
					allBans.addBannedChampion(ban);
					champCheck = true;
				} else {
					System.out.println("Not a champion. Pick again.");
					ban = input.nextLine();
				}
				
			}
			champCheck = false;
			System.out.println("The bans are ");
			if(i == 0 || i == 2 || i == 3){
				teamOneBans.addBannedChampion(ban);
			} else {
				teamTwoBans.addBannedChampion(ban);
			}
			System.out.println(teamOneBans.toString());
			System.out.println(teamTwoBans.toString());
			System.out.println();
		}
	}
	
	public static boolean checkIfChamp(String isChamp) throws RiotApiException{
		boolean champMatch = false;
		RiotApi api = new RiotApi("9e77d1de-7d58-43e9-91b5-5c07b91828fe");
		
        ChampionList champlist = api.getChampions();
        List<net.rithms.riot.dto.Champion.Champion> clist = champlist.getChampions();
        for(net.rithms.riot.dto.Champion.Champion c : clist){
        	String realChamp = api.getDataChampion((int) c.getId()).getName();
        	if(realChamp.equals(isChamp)){
        		champMatch = true;
        		break;
        	}
        }
        return champMatch;
        
	}

}
