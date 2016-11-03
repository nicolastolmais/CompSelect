import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;

import net.rithms.riot.constant.Region;
import net.rithms.riot.constant.Season;
import net.rithms.riot.constant.staticdata.ChampData;
//import net.rithms.riot.dto.Champion.Champion;
//import net.rithms.riot.dto.Champion.ChampionList;
import net.rithms.riot.dto.Summoner.Summoner;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.Region;
import net.rithms.riot.constant.staticdata.ChampData;
import net.rithms.riot.dto.Static.Champion;
import net.rithms.riot.dto.Static.ChampionList;
import net.rithms.riot.dto.Static.Skin;
import net.rithms.riot.dto.Static.*;

import com.google.gson.*;

public class compBuiler {

	public static void main(String[] args) throws RiotApiException {
		
		
		Scanner input = new Scanner(System.in);
		
		ArrayList<String> allChamps = new ArrayList<String>();
		ArrayList<String> bannedChampions = new ArrayList<String>();
		ArrayList<String> pickedChampions = new ArrayList<String>();
		Team teamOneChampions = new Team("Purple Side ");
		Team teamTwoChampions = new Team("Red Side ");		
	
		//setup

		allChamps = getAllChampionData();
		
		System.out.println("Welcome to COMP SELECT");
		System.out.println();
		
		
		//banning phase
		
		for (int i = 0; i<6; i++)
		{
			System.out.println("Ban a Champion.");
			
			String ban = chooseChampion(input);
			boolean check = false;
			
			check = championCheck(ban, bannedChampions, pickedChampions, allChamps);
			while(check == false){
				ban = chooseChampion(input);
				check = championCheck(ban, bannedChampions, pickedChampions, allChamps);
			}
			
			
			if(i == 0 || i == 2 || i == 4){
				teamOneChampions.addBan(ban);
			} else {
				teamTwoChampions.addBan(ban);
			}
			
			bannedChampions.add(ban);
			
			System.out.println(teamOneChampions.toString());
			System.out.println(teamTwoChampions.toString());
			
		}
		
		//picking phase
		for (int i = 0; i<10; i++)
		{
			System.out.println("Pick a Champion.");
			
			String pick = chooseChampion(input);
			boolean check = false;
			
			check = championCheck(pick, bannedChampions, pickedChampions, allChamps);
			while(check == false){
				pick = chooseChampion(input);
				check = championCheck(pick, bannedChampions, pickedChampions, allChamps);
			}
			
			if(i == 0 || i == 2 || i == 4 || i == 6 || i == 8){
				teamOneChampions.addChampion(pick);
			} else {
				teamTwoChampions.addChampion(pick);
			}
			
			pickedChampions.add(pick);
			System.out.println(teamOneChampions.toString());
			System.out.println(teamTwoChampions.toString());
		}
		
		
		System.out.println("Preparing Stats");
		
		
		//After select
		for(String champion : teamOneChampions.getTeamChampions()){	
			teamOneChampions.addTeamAttributes(getChampionTag(champion));
		}
		for(String champion : teamTwoChampions.getTeamChampions()){	
			teamTwoChampions.addTeamAttributes(getChampionTag(champion));
		}
		
		
		//Printing the teams
		System.out.println();
		System.out.println(teamOneChampions.toString());
		System.out.println("Missing Attributes: ");
		System.out.print(teamOneChampions.findMissingAttributes());
		System.out.println();
		System.out.println();
		System.out.println(teamTwoChampions.toString());
		System.out.print("Missing Attributes: ");
		System.out.print(teamTwoChampions.findMissingAttributes());
		System.out.println();
		System.out.println();
		System.out.println("Program End");
	}

	
	private static String chooseChampion(Scanner input){
		String champion = input.nextLine();
		return champion;
	}
	
	private static boolean championCheck(String possibleChampion, ArrayList<String> bannedChampions, ArrayList<String> pickedChampions, ArrayList<String> allChampions ){
		
		boolean banned = checkIfBanned(possibleChampion, bannedChampions);
		boolean picked = checkIfPicked(possibleChampion, pickedChampions);
		boolean isChampion = checkIfLeagueChampion(possibleChampion, allChampions);

		if(banned == false && picked == false && isChampion == true){
			return true;
		} else {
			System.out.println("Champion picked or banned or not a real. Pick Again");
			return false;
		}
	}
	
	
	private static boolean checkIfPicked(String isChamp, ArrayList<String> pickedChampions){
        for(String champion : pickedChampions){
        	if(champion.equals(isChamp)){
        		return true;
        	}
        }
        return false;
	}
	
	
	private static boolean checkIfBanned(String isChamp, ArrayList<String> bannedChampions){
        for(String champion : bannedChampions){
        	if(champion.equals(isChamp)){
        		return true;
        	}
        }
        return false;
	}
	
	
	private static boolean checkIfLeagueChampion(String isChamp, ArrayList<String> allChampions){
        for(String champion : allChampions){
        	if(champion.equals(isChamp)){
        		return true;
        	}
        }
        return false;
	}
	
	
    public static ArrayList<String> getAllChampionData() throws RiotApiException {
        // TODO Auto-generated method stub
    	ArrayList<String> allChampions = new ArrayList<String>();
    	
    	RiotApi api = new RiotApi("9e77d1de-7d58-43e9-91b5-5c07b91828fe", Region.NA);
        ChampionList championList = api.getDataChampionList(null, null, false, ChampData.TAGS);
        for(Champion champion : championList.getData().values()) {
            System.out.println(champion.getName());
            allChampions.add(champion.getName());
//            System.out.println(champion.getPartype());
            for(String tag : champion.getTags()) {
               System.out.println(tag.toString());
            } System.out.println("======");
        } 
        return allChampions;
    }
    
    public static String getChampionTag(String champ) throws RiotApiException {

    	RiotApi api = new RiotApi("9e77d1de-7d58-43e9-91b5-5c07b91828fe", Region.NA);
        ChampionList championList = api.getDataChampionList(null, null, false, ChampData.TAGS);
        for(Champion champion : championList.getData().values()) {
            if(champion.getName().equals(champ)){
            	for(String tag : champion.getTags()) {
            		System.out.println(tag.toString());
            		return tag.toString();
            	}
            }
        } 
        return "";
    }
    
    
    
	
}

