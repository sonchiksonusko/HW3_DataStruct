package src;

public class Match  {
    // Tournament-related fields
    public Tournament tournament;    // End date of tournament, yyyymmdd

    // Match-related fields
    public int matchNum;          // Match number within the tournament
    public String round;          // Round (e.g., "F", "SF", "QF", "R16", etc.)
    public int bestOf;            // Number of sets (3 or 5)
    public Integer minutes;       // Duration in minutes, can be null
    public String score;          // Match score (e.g., "6-3 6-4")

    // Winner fields
    public Player winner; // Player object for the winner
    public Player loser; // Player object for the loser

    public Integer w_ace, w_df, w_svpt, w_1stIn, w_1stWon, w_2ndWon, w_SvGms, w_bpSaved, w_bpFaced;

    // Loser match statistics
    public Integer l_ace, l_df, l_svpt, l_1stIn, l_1stWon, l_2ndWon, l_SvGms, l_bpSaved, l_bpFaced;



    public Match(
        Tournament tournament,
        int matchNum,
        String round,
        int bestOf,
        Integer minutes,
        String score,
        Player winner,
        Player loser,
        Integer w_ace, Integer w_df, Integer w_svpt, Integer w_1stIn, Integer w_1stWon, Integer w_2ndWon, Integer w_SvGms, Integer w_bpSaved, Integer w_bpFaced,
        Integer l_ace, Integer l_df, Integer l_svpt, Integer l_1stIn, Integer l_1stWon, Integer l_2ndWon, Integer l_SvGms, Integer l_bpSaved, Integer l_bpFaced
       
    ) {
        this.tournament = tournament;
        this.matchNum = matchNum;
        this.round = round;
        this.bestOf = bestOf;
        this.minutes = minutes;
        this.score = score;
        this.winner = winner;
        this.loser = loser;
        this.w_ace = w_ace;
        this.w_df = w_df;
        this.w_svpt = w_svpt;
        this.w_1stIn = w_1stIn;
        this.w_1stWon = w_1stWon;
        this.w_2ndWon = w_2ndWon;
        this.w_SvGms = w_SvGms;
        this.w_bpSaved = w_bpSaved;
        this.w_bpFaced = w_bpFaced;
        this.l_ace = l_ace;
        this.l_df = l_df;
        this.l_svpt = l_svpt;
        this.l_1stIn = l_1stIn;
        this.l_1stWon = l_1stWon;
        this.l_2ndWon = l_2ndWon;
        this.l_SvGms = l_SvGms;
        this.l_bpSaved = l_bpSaved;
        this.l_bpFaced = l_bpFaced;
    
    }

   /*  @Override
    public String toString() {
        return "[" + tournament.name + " " + tournament.date + " | " + round + "] "
            + winner.name + " def. " + loser.name + " (" + score + ")";
    */


  
    @Override
    public String toString() {
        return "[ "+ tournament + "MatchNum: "+matchNum + " " + " min "+minutes+ " ]";
    }

    // Getters and Setters
    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public int getMatchNum() {
        return matchNum;
    }

    public void setMatchNum(int matchNum) {
        this.matchNum = matchNum;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public int getBestOf() {
        return bestOf;
    }

    public void setBestOf(int bestOf) {
        this.bestOf = bestOf;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getLoser() {
        return loser;
    }
    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getWAce() {
        return w_ace;
    }

    public Integer getWDf() {
        return w_df;
    }

    public Integer getWSvpt() {
        return w_svpt;
    }

    public Integer getW1stIn() {
        return w_1stIn;
    }

    public Integer getW1stWon() {
        return w_1stWon;
    }

    public Integer getW2ndWon() {
        return w_2ndWon;
    }

    public Integer getWSvGms() {
        return w_SvGms;
    }

    public Integer getWBpSaved() {
        return w_bpSaved;
    }

    public Integer getWBpFaced() {
        return w_bpFaced;
    }

    public Integer getLAce() {
        return l_ace;
    }

    public Integer getLDf() {
        return l_df;
    }

    public Integer getLSvpt() {
        return l_svpt;
    }

    public Integer getL1stIn() {
        return l_1stIn;
    }

    public Integer getL1stWon() {
        return l_1stWon;
    }

    public Integer getL2ndWon() {
        return l_2ndWon;
    }

    public Integer getLSvGms() {
        return l_SvGms;
    }

    public Integer getLBpSaved() {
        return l_bpSaved;
    }

    public Integer getLBpFaced() {
        return l_bpFaced;
    }
}
