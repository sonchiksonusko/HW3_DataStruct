package src;
import src.Match;
import src.Player;
import src.Tournament;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import src.Main;

public class Comparators {
public static class MatchDurationComparator implements Comparator<Match> {
        @Override
        public int compare(Match m1, Match m2) {
        
            if (m1.getMinutes() == null && m2.getMinutes() == null) return 0;
            if (m1.getMinutes() == null) return -1;
            if (m2.getMinutes() == null) return 1;
            return Integer.compare(m2.getMinutes(), m1.getMinutes());
        }      
}


public static class TournamentWinnerComparator implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2) {
//compare by number of tournaments won
        int wins1 = countTournamentsWon(p1);
        int wins2 = countTournamentsWon(p2);
        return Integer.compare(wins2, wins1); //decreasing order
    }
    public static int countTournamentsWon(Player player) {
        int count = 0;
        for (Match m : Main.finalMatches) { 
            
                if (m.getWinner().getPlayerId().equals(player.getPlayerId())) {
                count++; // skip if winner is null    
                }
        }
           return count;
    }
}
  



    public static class MatchScoreComparator implements Comparator<Match> {
        @Override
        public int compare(Match m1, Match m2) {
            // Сравнение матчей по счёту, если score не null
            if (m1.getScore() == null && m2.getScore() == null) return 0;
            if (m1.getScore() == null) return -1;
            if (m2.getScore() == null) return 1;
            return m1.getScore().compareTo(m2.getScore());
        }
    }


    public static class PlayerWinningComparator implements Comparator<Player> {
        public int compare(Player p1, Player p2) {
        int wins1 = countWins(p1);
        int wins2 = countWins(p2);

        return Integer.compare(wins2, wins1); 
    }
    public static int countWins(Player p) {
        return p.getMatchesWon().size(); // Assuming matchesWon contains only won matches
    }
}


public static class PlayerPercentageComparator implements Comparator<Player> {
        @Override
        public int compare(Player p1, Player p2) {
            double winPercentage1 = calculateWinPercentage(p1);
            double winPercentage2 = calculateWinPercentage(p2);
            int loseCount1 = p1.getMatchesLost().size();
            int loseCount2 = p2.getMatchesLost().size();
            int winCount1 = p1.getMatchesWon().size();//  matchesWon contains only won matches
            int winCount2 = p1.getMatchesWon().size();
            if(Double.compare(winPercentage2, winPercentage1)!=0)   
            return Double.compare(winPercentage2, winPercentage1); 
            else
                return Integer.compare(winCount2, winCount1); 
        
        }

        private double calculateWinPercentage(Player player) {
            int wins = 0;
            int totalMatches = player.getMatchesPlayed().size();
            for (Match match : player.getMatchesPlayed()) {
                if (match.getWinner() != null && match.getWinner().getPlayerId().equals(player.getPlayerId())) {
                    wins++;
                }
            }
            return totalMatches > 0 ? (double) wins / totalMatches : 0.0;
        }
    }
    }
