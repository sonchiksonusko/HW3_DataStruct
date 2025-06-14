package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import src.Comparators;
public class Main {
    static  HashMap<String, List<Match>> tourneyMatches = new HashMap<>();
    static List<Match> allMatches = new ArrayList<>();
    static HashMap<String, Player> allPlayersMap = new HashMap<>();
    static HashMap<Tournament,List<Match>> finalMatchesTournaments = new HashMap<>();
    static HashMap<String,List<Player>> countryPlayerMap = new HashMap<>();
    static HashMap<String, Tournament> allTournaments = new HashMap<>(); // Store all tournaments by tourneyId
    static List<Match> finalMatches = new ArrayList<>(); // Store all final matches
 public static void main(String[] args) {
    // Read matches and tournament maps from CSV
    

    readMatchesAndMapsFromCSV("atp_matches_2010.csv");

    // -------------------------------------------------------------------------------------------------------------------

    System.out.println("ALL UNIQUE TOURNAMENT NAMES: ");
    printUniqueTournamentNames(tourneyMatches);
    System.out.println("----------------------------------------------------------------------------------------------");

    System.out.println("COUNT OF ALL PLAYERS: " + allPlayersMap.size());//************************** */
   


    System.out.println("ACCORDING TO TOURNAMENT MATCHES");
    printUniqueTournamentNames(tourneyMatches);
    System.out.println("----------------------------------------------------------------------------------------------");



    System.out.println("THE LONGEST MATCH: ");
    // Find the longest match using the custom comparator
    Match longest = thelongestMatch( allMatches.toArray(new Match[0]),0,new Comparators.MatchDurationComparator());
    System.out.println(longest.getTournament().getName() + " - Duration: " + longest.getMinutes() + " minutes");
    System.out.println("----------------------------------------------------------------------------------------------");



    System.out.println("THE LONGEST MATCH PLAYER: ");     
    System.out.println(longestPlayerGameTime(allPlayersMap).getName(longestPlayerGameTime(allPlayersMap))+ " - Total Minutes Played: " + longestPlayerGameTime(allPlayersMap).getMinutesPlayed()   + " minutes"); 
    System.out.println("----------------------------------------------------------------------------------------------");

    System.out.println("GET THE LONGEST AVERAGE PLAYER: ");
    System.out.println(AveragebiggestPlayer(allPlayersMap).getName(AveragebiggestPlayer(allPlayersMap)) + " - Average Minutes Played: " + (AveragebiggestPlayer(allPlayersMap).getMinutesPlayed() / AveragebiggestPlayer(allPlayersMap).matchesPlayed.size()) + " minutes");
    System.out.println("----------------------------------------------------------------------------------------------");
    
    System.out.println("COUNT OF ALL PLAYERS: ");
    System.out.print(allPlayersMap.size()+"\n");
    System.out.println("----------------------------------------------------------------------------------------------");

    
    Player[] playerArray = allPlayersMap.values().toArray(new Player[0]);
    System.out.print(playerArray);
    System.out.println(theBiggest(playerArray,0,new Comparators.PlayerWinningComparator()));
    System.out.println("----------------------------------------------------------------------------------------------");

    System.out.println("GET TOP 5 WINNERS in Maches");
    get_K_winners(playerArray, 5, new Comparators.PlayerWinningComparator());  
    System.out.println("----------------------------------------------------------------------------------------------");


    System.out.println("GET TOP 5 WINNERS in Tournaments");

    get_K_winners(playerArray, 7, new Comparators.TournamentWinnerComparator());  
    System.out.println("----------------------------------------------------------------------------------------------");



    System.out.println("BY PLAYERS FROM THE SAME COUNTRY"+"\n");
    printPlayersFromSameCountry(countryPlayerMap,"ISR");
    System.out.println("----------------------------------------------------------------------------------------------");

    System.out.println("COMPARE PLAYERS BY WINNING COUNT: "+"\n");
    PlayerComparatorbywinningCount("Rafael Nadal", "Roger Federer");

    System.out.println("----------------------------------------------------------------------------------------------");   
    System.out.println("COMPARE PLAYERS BY WINNING ON SERFACE"+"\n");
    PlayerComparatorbywinningPercent("Rafael Nadal", "Roger Federer");
}






public static void readMatchesAndMapsFromCSV(String myFile) {
    try (BufferedReader br = new BufferedReader(new FileReader(myFile))) {
        String line;
        boolean firstLine = true;

        while ((line = br.readLine()) != null) {
            if (firstLine) {
                firstLine = false; // skip header
                continue;
            }
            String[] values = line.split(",", -1);

            
            // Check for missing important columns
            if (values.length < 19) continue;

            String tourneyId = values[0] != null ? values[0].trim() : "";
            String tourneyName = values[1] != null ? values[1].trim() : "";
            String surface = values[2] != null ? values[2].trim() : "";
            int drawSize = parseIntSafe(values[3] != null ? values[3].trim() : "-1");
            String tourneyLevel = values[4] != null ? values[4].trim() : "";
            String tourneyDate = values[5] != null ? values[5].trim() : "";
            String winnerId = values[7] != null ? values[7].trim() : "";
            String wseed = values[8] != null ? values[8].trim() : "";
            String wentry = values[9] != null ? values[9].trim() : "";
            String winnerName = values[10] != null ? values[10].trim() : "";
            String whand = values[11] != null ? values[11].trim() : "";
            Integer wheight = parseIntSafe(values[12] != null ? values[12].trim() : "-1");
            String wioc = values[13] != null ? values[13].trim() : "";
            Double wage = parseDouble(values[14] != null ? values[14].trim() : "-1");

            String loserId = values[15] != null ? values[15].trim() : "";
            String lseed = values[16] != null ? values[16].trim() : "";
            String lentry = values[17] != null ? values[17].trim() : "";
            String loserName = values[18] != null ? values[18].trim() : "";
            String lhand = values[19] != null ? values[19].trim() : "";
            Integer lheight = parseIntSafe(values[20] != null ? values[20].trim() : "-1");
            String lioc = values[21] != null ? values[21].trim() : "";
            Double lage = parseDouble(values[22] != null ? values[22].trim() : "-1");  

            Tournament tournament = allTournaments.get(tourneyId);
            if (tournament == null) {
                tournament = new Tournament(tourneyId, tourneyName, surface, drawSize, tourneyLevel, tourneyDate);
                allTournaments.put(tourneyId, tournament);
            }

            
            int matchNum = parseIntSafe(values[6]!= null ? values[6].trim() : "-1");
            

            String score = values[23] != null ? values[23].trim() : "";
            int bestOf = parseIntSafe(values[24] != null ? values[24].trim() : "-1");
            String round = values[25] != null ? values[25].trim() : "";
            int minutes = parseIntSafe(values[26]!= null ? values[26].trim() : "-1");
            int w_ace = parseIntSafe(values[27]!= null ? values[27].trim() : "-1");
            int w_df = parseIntSafe(values[28]!= null ? values[28].trim() : "-1");
            int w_svpt = parseIntSafe(values[29]!= null ? values[29].trim() : "-1");
            int w_1stIn = parseIntSafe(values[30]!= null ? values[30].trim() : "-1");
            int w_1stWon = parseIntSafe(values[31]!= null ? values[31].trim() : "-1");
            int w_2ndWon = parseIntSafe(values[32]!= null ? values[32].trim() : "-1");
            int w_SvGms = parseIntSafe(values[33]!= null ? values[33].trim() : "-1");
            int w_bpSaved = parseIntSafe(values[34]!= null ? values[34].trim() : "-1");
            int w_bpFaced = parseIntSafe(values[34]!= null ? values[35].trim() : "-1");
            int l_ace = parseIntSafe(values[36]!= null ? values[36].trim() : "-1");
            int l_df = parseIntSafe(values[37]!= null ? values[37].trim() : "-1");
            int l_svpt = parseIntSafe(values[38]!= null ? values[38].trim() : "-1");
            int l_1stIn = parseIntSafe(values[39]!= null ? values[39].trim() : "-1");
            int l_1stWon = parseIntSafe(values[40]!= null ? values[40].trim() : "-1");
            int l_2ndWon = parseIntSafe(values[41]!= null ? values[41].trim() : "-1");
            int l_SvGms = parseIntSafe(values[42]!= null ? values[42].trim() : "-1");
            int l_bpSaved = parseIntSafe(values[43]!= null ? values[43].trim() : "-1");
            int l_bpFaced = parseIntSafe(values[44]!= null ? values[44].trim() : "-1");

            String winner_rank = values[45]!= null ? values[45].trim() : "-1";
            String winner_rank_points = values[46]!= null ? values[46].trim() : "-1";
            String loser_rank = values[47]!= null ? values[47].trim() : "-1";
            String loser_rank_points = values[48]!= null ? values[48].trim() : "-1";
      
          Player winner = allPlayersMap.getOrDefault(winnerName, new Player(
    winnerId, wseed, wentry, winnerName, whand, wheight, wioc, wage,
    winner_rank, winner_rank_points,minutes 
));
Player loser = allPlayersMap.getOrDefault(loserName, new Player(
    loserId, lseed, lentry, loserName, lhand, lheight, lioc, lage,
    loser_rank, loser_rank_points,minutes));
           
            
 if (!allPlayersMap.containsKey(winnerName)) {
                winner = new Player(winnerId, wseed, wentry, winnerName, whand, wheight, wioc, wage,
                                    winner_rank, winner_rank_points,minutes);
                allPlayersMap.put(winnerName, winner);
                countryPlayerMap.computeIfAbsent(wioc, k -> new ArrayList<>()).add(winner);
            } else {
                winner = allPlayersMap.get(winnerName);
            }

            loser = allPlayersMap.get(loserName);
            if (loser == null) {
                loser = new Player(loserId, lseed, lentry, loserName, lhand, lheight, lioc, lage,
                                loser_rank, loser_rank_points,minutes);
             allPlayersMap.put(loserName, loser);
                countryPlayerMap.computeIfAbsent(lioc, k -> new ArrayList<>()).add(loser);
            } else {
                loser = allPlayersMap.get(loserName);
            }
            // Create Match object
          

           Match match = new Match(
            tournament, matchNum, round, bestOf, minutes, score, winner, loser,
            w_ace, w_df, w_svpt, w_1stIn, w_1stWon, w_2ndWon, w_SvGms, w_bpSaved, w_bpFaced,
            l_ace, l_df, l_svpt, l_1stIn, l_1stWon, l_2ndWon, l_SvGms, l_bpSaved, l_bpFaced

);
            if ("F".equals(round)) {
            finalMatchesTournaments.computeIfAbsent(tournament, k -> new ArrayList<>()).add(match);
            finalMatches.add(match);
}

           // allPlayersMap.put(winnerId, winner);
          //  allPlayersMap.put(loserId, loser);
            allMatches.add(match);
            tourneyMatches.computeIfAbsent(tourneyId, k -> new ArrayList<>()).add(match);
            //finalMatchesTournaments.putIfAbsent(tournament, tournament);
            winner.addMatch(match);
            loser.addMatch(match);
            winner.matchesWon.add(match);
            loser.matchesLost.add(match);
            winner.addMinutesPlayed(minutes);
            loser.addMinutesPlayed(minutes);

   
           
            //countryPlayerMap.putIfAbsent(wioc, new ArrayList<>());// Initialize if not present
          //  countryPlayerMap.get(wioc).add(winner);// Add winner to the list for their country
            

            //countryPlayerMap.putIfAbsent(lioc, new ArrayList<>());
           // countryPlayerMap.get(lioc).add(loser);
            //System.out.println("Winner: " + winner.getName() + " from " + wioc);
            //System.out.println("Loser: " + loser.getName() + " from " + lioc);

            
           //allplayers.add(loser);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}





//----------------------------------------------------------------------------------------------------------------------------------------
public static void printUniqueTournamentNames(Map<String, List<Match>> tourneyMatches){
    Set<String> uniqueTournamentNames = new HashSet<>();
    for (List<Match> matches : tourneyMatches.values()) {
        if (!matches.isEmpty()) {
            uniqueTournamentNames.add(matches.get(0).tournament.getName());
            //For each tournament only look at the first match to get the tournament name
        }
    }
    for (String name : uniqueTournamentNames) {
        System.out.println(name);
    }
}

 public static <T> Match thelongestMatch(T[] arr,int k, Comparator<T> comparator) {
    return (Match) theBiggest(arr, k, comparator);
}


public static <T> void get_K_winners(T[] arr, int k, Comparator<T> comparator) {
    if (k < 0 || k >= arr.length) {
        throw new IllegalArgumentException("Invalid value of k");
    }
   quickselect(arr, 0, arr.length - 1, k , comparator);
   List<T> result = new ArrayList<>();
     for (int i = 0; i < k; i++ ) {
        result.add(arr[i]); // Get the k largest elements
    }

    result.sort(comparator); // Sort the result in descending order
    for (int i = 0 ; i < result.size(); i++) {
        System.out.println(result.get(i)); // Print the k largest elements
       System.out.print( Comparators.TournamentWinnerComparator.countTournamentsWon((Player)result.get(i)));
    }
}


public static <T> T theBiggest(T[] arr, int k, Comparator<T> comparator) {
    return quickselect(arr, 0, arr.length - 1, k, comparator);
}

private static <T> T quickselect(T[] arr, int left, int right, int k, Comparator<T> comparator) {
    if (left == right) return arr[left];

    int pivotIndex = partition(arr, left, right, comparator);
    if (k == pivotIndex){
    return arr[k];
}
    else if (k < pivotIndex) return quickselect(arr, left, pivotIndex - 1, k, comparator);
    else return quickselect(arr, pivotIndex + 1, right, k, comparator);
}

private static <T> int partition(T[] arr, int left, int right, Comparator<T> comparator) {
    T pivot = arr[right]; 
    int i = left;

    for (int j = left; j < right; j++) {
        if (comparator.compare(arr[j], pivot) < 0) { 
            swap(arr, i, j);
            i++;
        }
    }

    swap(arr, i, right);
    return i;
}

private static <T> void swap(T[] arr, int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}




public static Player AveragebiggestPlayer(HashMap<String,Player> allPlayersMap) {
    double maxAverage = 0;
    Player longestaverageplayer = null;
    for(Player player : allPlayersMap.values()) {
        double average = player.matchesPlayed.size() > 0 ? (double) player.minutesPlayed / player.matchesPlayed.size() : 0;
        if (average > maxAverage) {
            maxAverage = average;
            longestaverageplayer = player;
        }
       
    }
    return longestaverageplayer;
   
}






public static void printPlayersFromSameCountry(HashMap<String,List<Player>> countryPlayerMap, String country) {
   
    Player[] players = countryPlayerMap.get(country).toArray(new Player[0]);
   java.util.Arrays.sort(players, new Comparators.PlayerPercentageComparator());////add winner loser matches + ptint
    System.out.println("Players from country " + country + ":");
    for (Player player : players) {
        System.out.println(Player.getName(player) + " - Matches Played: " +(player.matchesPlayed.size())+" Percentage "+ (player.matchesWon.size()*100.0 / player.matchesPlayed.size()) + " % "+ player.matchesWon.size()+" Wins");
    }
    // Uncomment to get top winners from this country
    // System.out.println("Top winners from country " + country + ":");
    // get_K_winners(players,players.length, new Comparators.PlayerWinningComparator());
}

public static void PlayerComparatorbywinningCount(String p1, String p2) {
    Player player1 = allPlayersMap.get(p1);
    Player player2 = allPlayersMap.get(p2);
    int countplayer1wins = 0;
    int countcommongames = 0;
    int countplayer2wins = 0;
    for(Match m : player1.matchesWon){
        if(m.getLoser().getPlayerId().equals(player2.getPlayerId())) {
            countcommongames++;
            countplayer1wins++;
        }
    }
    for(Match m : player2.matchesWon){
        if(m.getLoser().getPlayerId().equals(player1.getPlayerId())) {
            countcommongames++;
            countplayer2wins++;
        }
    }
    int compareResult = Integer.compare(countplayer1wins, countplayer2wins);
    System.out.println("Player 1: " + player1.getName(player1) + " Wins: " + countplayer1wins);
    System.out.println("Player 2: " + player2.getName(player2) + " Wins: " + countplayer2wins);
    System.out.println("Common Games: " + countcommongames);
    //System.out.println("Player 1 > player 2 ? " + (countplayer1wins > countplayer2wins ? "Yes" : "No"));


}
public static void PlayerComparatorbywinningPercent(String p1, String p2){
    Player player1 = allPlayersMap.get(p1);
    Player player2 = allPlayersMap.get(p2);
    int p1counthard = 0, p1countclay = 0, p1countgrass = 0;
    int p2counthard = 0, p2countclay = 0, p2countgrass = 0;
    
    HashMap<String, Integer> player1Wins = new HashMap<>();
    HashMap<String, Integer> player2Wins = new HashMap<>();

    for (Match m1 : player1.matchesWon) {
        if(m1.getTournament().getSurface().equals("Hard")) {
            player1Wins.put("Hard", player1Wins.getOrDefault("Hard", 0) + 1);
            p1counthard++;   
        }
        else if(m1.getTournament().getSurface().equals("Clay")) {
            player1Wins.put("Clay", player1Wins.getOrDefault("Clay", 0) + 1);
            p1countclay++;
        }
        else if(m1.getTournament().getSurface().equals("Grass")) {
            player1Wins.put("Grass", player1Wins.getOrDefault("Grass", 0) + 1);
            p1countgrass++;
        }
    }
     for (Match m2 : player1.matchesLost) {
        if(m2.getTournament().getSurface().equals("Hard")) {
            p1counthard++;   
        }
        else if(m2.getTournament().getSurface().equals("Clay")) {      
            p1countclay++;
        }
        else if(m2.getTournament().getSurface().equals("Grass")) {
            p1countgrass++;
        }
    }
    for (Match m3 : player2.matchesWon) {
        if(m3.getTournament().getSurface().equals("Hard")) {
            player2Wins.put("Hard", player2Wins.getOrDefault("Hard", 0) + 1);
            p2counthard++;
        }
        else if(m3.getTournament().getSurface().equals("Clay")) {
            player2Wins.put("Clay", player2Wins.getOrDefault("Clay", 0) + 1);
            p2countclay++;
        }
        else if(m3.getTournament().getSurface().equals("Grass")) {
            player2Wins.put("Grass", player2Wins.getOrDefault("Grass", 0) + 1);
            p2countgrass++;
        }
    }
    for (Match m4 : player2.matchesLost) {
        if(m4.getTournament().getSurface().equals("Hard")) {
           p2counthard++;
          
        }
        else if(m4.getTournament().getSurface().equals("Clay")) {
           p2countclay++;
        }
        else if(m4.getTournament().getSurface().equals("Grass")) {
            p2countgrass++;
        }
    }
   
 
System.out.print(player2Wins.get("Hard"));
 System.out.println("p2counthard: " + p2counthard);
    System.out.println(player1.getName(player1) + " Wins on Hard Percentage: " + player1Wins.get("Hard")*100/(p1counthard)  + " %" );
    System.out.println( player1.getName(player1) + " Wins on Clay Percentage: " + player1Wins.get("Clay")*100/(p1countclay)  + " %" );
    System.out.println(player1.getName(player1) + " Wins on Grass Percentage: " + player1Wins.get("Grass")*100/(p1countgrass)  + " %" + "\n");
  
    System.out.println(player2.getName(player2) + " Wins on Hard Percentage: " + player2Wins.get("Hard")*100/(p2counthard)  + " %" );
    System.out.println( player2.getName(player2) + " Wins on Clay Percentage: " + player2Wins.get("Clay")*100/(p2countclay)  + " %" );      
    System.out.println(player2.getName(player2) + " Wins on Grass Percentage: " + player2Wins.get("Grass")*100/(p2countgrass)  + " %" );
}



public static Player longestPlayerGameTime(HashMap<String,Player> allPlayersMap) {
    Player longestPlayer = null;
    int maxMinutes = 0;
    for (Player player : allPlayersMap.values()) {
        if (player.getMinutesPlayed() > maxMinutes) {
            maxMinutes = player.getMinutesPlayed();
            longestPlayer = player;
            
        }
    }
    return longestPlayer;
    
}




    private static int parseIntSafe(String s) {
        try {
            return s == null || s.trim().isEmpty() ? 0 : Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }


    // Helper: returns the value at index i, or null if not present
    private static String safeGet(String[] arr, int i) {
        return (i < arr.length && arr[i] != null && !arr[i].isEmpty()) ? arr[i] : null;
    }

    private static String[] splitCsv(String line) {
        return line.split(",", -1);
    }
    private static Integer parseInteger(String s) {
        try {
            return (s == null || s.isEmpty()) ? null : Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
    }
    private static Double parseDouble(String s) {
        try {
            return (s == null || s.isEmpty()) ? null : Double.parseDouble(s);
        } catch (Exception e) {
            return null;
        }
    }
    private static int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }






}