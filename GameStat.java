// created by Nuha Abdul Rasheed and Hibah Mohammed Ghouse
package PDGAME;

public class GameStat {
    private String computerStrategy;
    private int numofroundsPlayed;
    private int userYearsInPrison;
    private int computerYearsInPrison;
    
    public void setComputerStrategy(String computerStrategy) {
        this.computerStrategy = computerStrategy;
    }
    
    public String getComputerStrategy() {
        return computerStrategy;
    }
    
    public void setUserYearsInPrison(int userYearsInPrison) {
        this.userYearsInPrison = userYearsInPrison;
    }
    
    public int getUserYearsInPrison() {
        return userYearsInPrison;
    }
    
    public void setComputerYearsInPrison(int computerYearsInPrison) {
        this.computerYearsInPrison = computerYearsInPrison;
    }
    
    public int getComputerYearsInPrison() {
        return computerYearsInPrison;
    }
    
    public void setRoundsPlayedNum(int numofroundsPlayed) {
        this.numofroundsPlayed = numofroundsPlayed;
    }
    
    public int getRoundsPlayedNum() {
        return numofroundsPlayed;
    }
    
    public void update(int userSentence, int computerSentence) {
        numofroundsPlayed++; 
        userYearsInPrison += userSentence;
        computerYearsInPrison += computerSentence;
        setUserYearsInPrison(userYearsInPrison);
        setComputerYearsInPrison(computerYearsInPrison);
    }
      
    public String getWinner() {
        if (userYearsInPrison < computerYearsInPrison) {
            return "The winner is you, the game player!";
        } 
        else if (userYearsInPrison > computerYearsInPrison) {
            return "The winner is the computer!";
        } 
        else {
            return "Tie game.";
        }
    }

	
}

