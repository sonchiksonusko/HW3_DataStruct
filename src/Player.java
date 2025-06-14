package src;

import java.util.ArrayList;
import java.util.List;

public class Player  {
  

    // Player-related fields
 public String playerId;
 public String seed;
 public String entry;
    public String name;
    public String hand;
    public Integer height; // cm
    public String ioc; // country code
    public Double age; // years
    public String rank;
    public String rankPoints;
    public List<Match> matchesPlayed = new ArrayList<>();
    public List<Match> matchesWon = new ArrayList<>();
    public List<Match> matchesLost = new ArrayList<>();
    public int minutesPlayed = 0; // total minutes played in matches

    public Player(String playerId,String seed, String entry ,String name, String hand, Integer height, String ioc, Double age, String rank, String rankPoints, List<Match> matchesPlayed,int minutesPlayed) {
        this.playerId = playerId;
        this.seed = seed;
        this.entry = entry;
        this.name = name;
        this.hand = hand;
        this.height = height;
        this.ioc = ioc;
        this.age = age;
        this.rank = rank;
        this.rankPoints = rankPoints;
        this.matchesPlayed = matchesPlayed != null ? matchesPlayed : new ArrayList<>();
        this.minutesPlayed = minutesPlayed;

    }
    public Player(String winnerId, String wseed, String wentry, String winnerName, String whand, Integer wheight,
            String wioc, Double wage, String winner_rank, String winner_rank_points,int minutesPlayed) {
        this.playerId = winnerId;   
        this.seed = wseed;
                this.entry = wentry;
                this.name = winnerName;
                this.hand = whand;
                this.height = wheight;
                this.ioc = wioc;
                this.age = wage;
                this.rank = winner_rank;
                this.rankPoints = winner_rank_points;
                this.matchesPlayed = new ArrayList<>();
        this.minutesPlayed = minutesPlayed;

    
    }
    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public static String getName(Player player) {
        return player.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getIoc() {
        return ioc;
    }

    public void setIoc(String ioc) {
        this.ioc = ioc;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRankPoints() {
        return rankPoints;
    }

    public void setRankPoints(String rankPoints) {
        this.rankPoints = rankPoints;
    }

    public void addMatch(Match match) {
    matchesPlayed.add(match);
}
public void addMinutesPlayed(int minutes) {
    this.minutesPlayed += minutes;
}

public List<Match> getMatchesPlayed() {
    return matchesPlayed;
}
public List<Match> getMatchesWon() {
    return matchesWon;

}
public List<Match> getMatchesLost() {
    return matchesLost;

}
    public int getMinutesPlayed() {
        return minutesPlayed;
    }
    public void setMinutesPlayed(int minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    @Override
    public String toString() {
        return name + " (" + playerId + ")";
    }
}
