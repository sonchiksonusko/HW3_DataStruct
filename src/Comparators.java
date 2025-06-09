package src;
import src.Match;
import src.Player;
import src.Tournament;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Comparators {
public static class MatchDurationComparator implements Comparator<Match> {
        @Override
        public int compare(Match m1, Match m2) {
            // Сравнение матчей по длительности (минуты), учитывая null
            if (m1.getMinutes() == null && m2.getMinutes() == null) return 0;
            if (m1.getMinutes() == null) return -1;
            if (m2.getMinutes() == null) return 1;
            return m1.getMinutes().compareTo(m2.getMinutes());
        }      
}


public static class TournamentWinnerComparator implements Comparator<Player> {
    private final Map<String, Tournament> allTournaments;
    private final Map<String,List<Match>> tourneyMatches;

    public TournamentWinnerComparator(Map<String, Tournament> allTournaments,Map<String,List<Match>> tourneyMatches) {
        this.allTournaments = allTournaments;
        this.tourneyMatches = tourneyMatches;
    }
   

    @Override
    public int compare(Player p1, Player p2) {
        // Сравнение игроков по количеству выигранных турниров
        int wins1 = countTournamentsWon(p1);
        int wins2 = countTournamentsWon(p2);
        return Integer.compare(wins2, wins1); // Сортировка по убыванию
    }

    private int countTournamentsWon(Player player) {
        int count = 0;
        for (Tournament t : allTournaments.values()) {
            List<Match> matches = t.tourneyId != null ? tourneyMatches.get(t.getTourneyId()) : null;
            if (matches != null) {
                for (Match match : matches) {
                    if ("F".equals(match.getRound())) { 
                        Player winner = match.getWinner();
                        if (winner != null && winner.getPlayerId().equals(player.getPlayerId())) {
                            count++;
                        }
                    }
                }
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
    }public static class PlayerWinningComparator implements Comparator<Player> {
        public int compare(Player p1, Player p2) {
        int wins1 = countWins(p1);
        int wins2 = countWins(p2);

        return Integer.compare(wins2, wins1); 
    }

    public static int countWins(Player p) {
        int count = 0;
        for (Match match : p.getMatchesPlayed()) {
            if (match.getWinner().getPlayerId().equals(p.getPlayerId())) {
                count++;
            }
        }
        return count;
    }
}
}
