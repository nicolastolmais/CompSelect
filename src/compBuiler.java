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

public class compBuiler {

	public static void main(String[] args) throws RiotApiException {
		
		Scanner input = new Scanner(System.in);
		ArrayList<String> bans = new ArrayList<String>();
		ArrayList<String> bans1 = new ArrayList<String>();
		ArrayList<String> bans2 = new ArrayList<String>();
		ArrayList<String> pics = new ArrayList<String>();
		ArrayList<String> team1 = new ArrayList<String>();
		ArrayList<String> team2 = new ArrayList<String>();
		Bans teamOneBans = new Bans();
		Bans teamTwoBans = new Bans();
		Bans allBans = new Bans();
		
		System.out.println("Welcome to COMP SELECT");
		System.out.println();
		//bans
		
		
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
			System.out.print(teamOneBans.toString());
			System.out.print(teamTwoBans.toString());
			System.out.println();
		}
		//pics
		for (int i = 0; i<10; i++)
		{
			if(i == 0 || i == 3 || i == 4 || i == 7 || i == 8 || i == 10)
			{
				PickChampion(input, bans, pics, team1);
			} else {
				PickChampion(input, bans, pics, team2);
			}
		}
		System.out.println();
		//Printing the teams
		System.out.println("Purple side: ");
		for(String s : team1)
		{
			System.out.println(s);
		}
		System.out.println("Bans:");
		System.out.print(teamOneBans.toString());
		System.out.println();
		System.out.println("Blue side: ");
		for(String s : team2)
		{
			System.out.println(s);
		}
		System.out.println("Bans:");
		System.out.print(teamTwoBans.toString());
	}

	private static void PickChampion(Scanner input, ArrayList<String> bans, ArrayList<String> pics,
			ArrayList<String> team1) throws RiotApiException {
		System.out.println("Pick a Champion.");
		String champ = input.nextLine();
		boolean champCheck = false;
		while(champCheck  == false){
			champ = checkIfBanned(input, bans, champ);
			champ = checkIfPicked(input, pics, champ);
			if(checkIfChamp(champ) == true){
				pics.add(champ);
				team1.add(champ);
				champCheck = true;
			} else {
				System.out.println("Not a champion. Pick again.");
				champ = input.nextLine();
			}
		}
		champCheck = false;
		System.out.println("The pics are ");
		for(String s : pics)
		{
			System.out.println(s);
		}
	}

	private static String checkIfPicked(Scanner input, ArrayList<String> pics, String champ) {
		for(int j = 0; j< pics.size();j++){
			if(pics.get(j) == champ)
			{
				System.out.println("Champion already picked. Pick again.");
				champ = input.nextLine();
			}
		}
		return champ;
	}

	private static String checkIfBanned(Scanner input, ArrayList<String> bans, String champ) {
		for(int j = 0; j< bans.size();j++){
			if(bans.get(j) == champ)
			{
				System.out.println("Champion already banned. Pick again.");
				champ = input.nextLine();
			}
		}
		return champ;
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

