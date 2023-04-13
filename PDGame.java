//created by Hibah Mohammed Ghouse and Nuha abdul Rasheed

package PDGAME;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PDGame{
	private ArrayList<String> strategiesList = new ArrayList<>(); //storing strategies
	private ArrayList<Integer> History = new ArrayList<>(); //storing User inputs
	private int strategy; //Instance variable
	GameStat stats = new GameStat(); //creating object
	Scanner sc = null;
	public PDGame(String file) //Constructor
	{
		strategiesList.add("1.Computer Reads Strategy from Input File.");
		strategiesList.add("2.Tit-For-Tat");
		strategiesList.add("3.Tit-For-Two-Tats");
		strategiesList.add("4.Random Choice by Computer");
	try {
		sc = new Scanner(new File(System.getProperty("user.dir") +"/"+file));
	} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
    
	public GameStat getStats() {
	    return stats;
	}
	public ArrayList<String> getStrategiesList() {
	    return strategiesList;
	}
	public String getScores() {
        String score = "Your prison sentence is: "+stats.getUserYearsInPrison()+"\n Your partner's/computer prison sentence is: "+stats.getComputerYearsInPrison();
        return score;
	}
	//Setter Methods
	public void setStrategy(int strategy) {
        this.strategy = strategy;
        stats.setComputerStrategy(strategiesList.get(strategy-1));
        this.setStats(stats);
	}
	public void setStats(GameStat stats) {
        this.stats = stats;
	}
	public String playRound(int decision)
	{
		History.add(decision);
		int computerDecision = figureComputerDecision(strategy);
		if(decision==1 && computerDecision==1)
		{
            stats.update(2, 2); //Updating prison years
            return "You and your partner remain silent\nYou both get 2 years in prison.";
        }
		else if(decision==2 && computerDecision==1)
		{
            stats.update(1, 5); //Updating prison years
            return "You testify against your partner and they remain silent.\nYou get 1 year in prison and they get 5.";
		}
		else if(decision==1 && computerDecision==2)
		{
            stats.update(5, 1); //Updating prison years
            return "You remain silent and they testify against you.\nYou get 5 years in prison and they get 1.";
		}
		else
		{
            stats.update(3, 3); //Updating prison years
            return "You and your partner testify against eachother.\nYou both get 3 years in prison";
		}
	}
		//reading computer decision from different strategies chosen and returning
	private int figureComputerDecision(int Strategy)
	{
            int decision = 0;
            switch(Strategy)
            {
            case 1:
            decision = inputFromFile(); //Decision from file
            break;
            case 2:
            decision = titForTat(); //TitForTat
            break;
            case 3:
            decision = titForTwoTat(); //TitForTwoTat
            break;
            case 4:
            decision = (int)((Math.random()*2)+1); //Random number decision
            break;
            }
            return decision;
    }
            
        //Taking input from a file
    private int inputFromFile() {
            if (sc.hasNextInt())
            {
            
            int decision = sc.nextInt();
            return decision;
            }
            else {
            	System.out.println("No integer found");
            }
		return 0;
	}
		//Cooperate on the first two moves, otherwise betray if the player's last two moves were betrayal
	private int titForTwoTat() {
		int decision=0;
		if(stats.getRoundsPlayedNum()==0 || stats.getRoundsPlayedNum()==1)
		//Cooperating on first two moves
		{
            decision=1;
		}
		else
		{
            int lastMove1 = History.get(History.size()-2);
            int lastMove2 = History.get(History.size()-3);
            if(lastMove1==2 && lastMove2==2)
            {
                decision=2;
            }
            else
            {
                decision=1;
            }
            }
            return decision;
		}

		//Cooperate on the first move, otherwise play the player's last move
		private int titForTat()
		{
            int decision = 0;
            if(stats.getRoundsPlayedNum()==0) //Cooperating on first move
            {
                decision = 1;
            }
            else
            {
                decision = History.get(History.size()-2);
            }
            return decision;
		}
		}