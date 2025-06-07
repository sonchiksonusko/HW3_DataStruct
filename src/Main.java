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
 public static void main(String[] args) {
    // Read matches and tournament maps from CSV
    Map<String, List<Match>> tourneyMatches = new HashMap<>();
    List<Match> allMatches = new ArrayList<>();
    HashMap<String, Player> allPlayersMap = new HashMap<>();
    readMatchesAndMapsFromCSV("atp_matches_2010.csv", allMatches, tourneyMatches, allPlayersMap);

    // -------------------------------------------------------------------------------------------------------------------
    System.out.println("ACCORDING TO TOURNAMENT MATCHES");
    printUniqueTournamentNames(tourneyMatches);
    System.out.println("ACCORDING TO ALL MATCHES");
    //Match longest = theBiggest( allMatches.toArray(new Match[0]), 0,new Comparators.MatchDurationComparator()   );
    //
    Player thelongestMatchPlayer = longestPlayerGameTime(allMatches);
    System.out.println(thelongestMatchPlayer);  

    Player[] playerArray = allPlayersMap.values().toArray(new Player[0]);
    System.out.println(theBiggest(playerArray,0,new Comparators.PlayerWinningComparator()));

    System.out.println("GET TOP 5 WINNERS in Maches");
    get_K_winners(playerArray, 5, new Comparators.PlayerWinningComparator());  

    // System.out.println("GET TOP 5 WINNERS in Tournaments");
    // get_K_winners(playerArray, 5, new Comparators.TournamentWinnerComparator());  
}






public static void readMatchesAndMapsFromCSV(String myFile, List<Match> allMatches, Map<String, List<Match>> tourneyMatches,  HashMap<String, Player> allPlayersMap) {
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

            Tournament tournament = new Tournament(tourneyId, tourneyName, surface, drawSize, tourneyLevel, tourneyDate);
            
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
      
          Player winner = allPlayersMap.getOrDefault(winnerId, new Player(
    winnerId, wseed, wentry, winnerName, whand, wheight, wioc, wage,
    winner_rank, winner_rank_points
));
Player loser = allPlayersMap.getOrDefault(loserId, new Player(
    loserId, lseed, lentry, loserName, lhand, lheight, lioc, lage,
    loser_rank, loser_rank_points
));
           
            

            // Create Match object
          

           Match match = new Match(
            tournament, matchNum, round, bestOf, minutes, score, winner, loser,
            w_ace, w_df, w_svpt, w_1stIn, w_1stWon, w_2ndWon, w_SvGms, w_bpSaved, w_bpFaced,
            l_ace, l_df, l_svpt, l_1stIn, l_1stWon, l_2ndWon, l_SvGms, l_bpSaved, l_bpFaced
);

            allPlayersMap.put(winnerId, winner);
            allPlayersMap.put(loserId, loser);
            allMatches.add(match);
            tourneyMatches.computeIfAbsent(tourneyId, k -> new ArrayList<>()).add(match);
            winner.addMatch(match);
            loser.addMatch(match);
            
           // allplayers.add(loser);
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
/*public static Match thelongestMatch(T[] arr,int k) {
    return quickselect(arr, 0, arr.length - 1, k);
}*/


public static <T> void get_K_winners(T[] arr, int k, Comparator<T> comparator) {
    if (k < 0 || k >= arr.length) {
        throw new IllegalArgumentException("Invalid value of k");
    }
    quickselect(arr, 0, arr.length - 1, arr.length - k , comparator);
   List<T> result = new ArrayList<>();
    for (int i = 0; i < k; i++) {
        result.add(arr[i]); // Get the k largest elements
    }
    result.sort(comparator); // Sort the result in descending order
    for (T item : result) {
        System.out.println(item);
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







public static Player longestPlayerGameTime(List<Match> allMatches) {
    Map<Player, Integer> totalDurations = new HashMap<>();
    Map<Player, Integer> matchCounts = new HashMap<>();

    for (Match match : allMatches) {
        Integer minutes = match.getMinutes();
        if (minutes == null) continue;

        totalDurations.merge(match.getWinner(), minutes, Integer::sum);
        matchCounts.merge(match.getWinner(), 1, Integer::sum);

        totalDurations.merge(match.getLoser(), minutes, Integer::sum);
        matchCounts.merge(match.getLoser(), 1, Integer::sum);
}
        Player longestPlayer = null;
        int maxDuration = -1;
           for (Map.Entry<Player, Integer> entry : totalDurations.entrySet()) {
            if (entry.getValue() > maxDuration) {
                maxDuration = entry.getValue();
                longestPlayer = entry.getKey();
            }
        }
        
        System.out.println("Match counts of : "+longestPlayer+" = " + matchCounts.get(longestPlayer));
        System.out.println("The Longest time in all matches : "+totalDurations.get(longestPlayer));

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