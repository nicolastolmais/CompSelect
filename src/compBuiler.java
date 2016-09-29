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
		ArrayList<String> pics = new ArrayList<String>();
		Bans teamOneBans = new Bans();
		Bans teamTwoBans = new Bans();
		Team teamOne = new Team();
		Team teamTwo = new Team();
		
		
		System.out.println("Welcome to COMP SELECT");
		System.out.println();
		//bans
		
		
		for (int i = 0; i<6; i++)
		{
			if(i == 0 || i == 2 || i == 4){
				banChampion(input, teamOneBans, bans);
			} else {
				banChampion(input, teamTwoBans, bans);
			}
		}
		
		
		//pics
		for (int i = 0; i<10; i++)
		{
			if(i == 0 || i == 3 || i == 4 || i == 7 || i == 8 || i == 10)
			{
				PickChampion(input, bans, pics, teamOne.getTeamChampions());
			} else {
				PickChampion(input, bans, pics, teamTwo.getTeamChampions());
			}
		}
		System.out.println();
		
		
		//Printing the teams
		System.out.println("Purple side: ");
		System.out.println(teamOne.getTeamChampions().toString());
		System.out.println("Bans:");
		System.out.print(teamOneBans.toString());
		System.out.println();
		System.out.println("Blue side: ");
		System.out.println(teamTwo.getTeamChampions().toString());
		System.out.println("Bans:");
		System.out.print(teamTwoBans.toString());
	}

	


	private static void banChampion(Scanner input, Bans teamOneBans, ArrayList<String> bans ) throws RiotApiException {
		boolean champCheck = false;
		System.out.println("Ban a Champion.");
		String ban = input.nextLine();
		while(champCheck == false){
			ban = checkIfBanned(input, bans, ban);
			ban = checkIfChampion(input, ban);
			champCheck = true;
		}
		champCheck = false;
		System.out.println("The bans are ");
		teamOneBans.addBannedChampion(ban);
		System.out.print(teamOneBans.toString());
		System.out.println();
	}




	
	private static void PickChampion(Scanner input, ArrayList<String> bans, ArrayList<String> pics,
			ArrayList<String> team1) throws RiotApiException {
		System.out.println("Pick a Champion.");
		String champ = input.nextLine();
		boolean champCheck = false;
		while(champCheck  == false){
			champ = checkIfBanned(input, bans, champ);
			champ = checkIfPicked(input, pics, champ);
			champ = checkIfChampion(input, champ);
			pics.add(champ);
			team1.add(champ);
			champCheck = true;
			
		}
		champCheck = false;
		System.out.println("The pics are ");
		for(String s : pics)
		{
			System.out.println(s);
		}
	}



	private static String checkIfChampion(Scanner input, String champ) throws RiotApiException {
		if(checkIfLeagueChampion(champ) == true){
		} else {
			System.out.println("Not a champion. Pick again.");
			champ = input.nextLine();
		}
		return champ;
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
	
	public static boolean checkLeagueIfReal(String isChamp) throws RiotApiException{
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
	
	public static boolean checkIfLeagueChampion(String champ)  {
		ArrayList<String> champList = new ArrayList<String>();
		champList.add("Aatrox");
		champList.add("Ahri");
		champList.add("Akali");
		champList.add("Alistar");
		champList.add("Amumu");
		champList.add("Anivia");
		champList.add("Annie");
		champList.add("Ashe");
		champList.add("A");
		
		for(int i = 0; i < champList.size(); i++){
			if(champ.equals(champList.get(i))){
				return true;
			}
		}
		return false;
	}
}

