package src;

public class Tournament {
public String tourneyId;      // Unique tournament code, e.g. "2010-339"
    public String name;           // Tournament name, e.g. "Brisbane"
    public String surface;        // Surface type: "Hard", "Clay", "Grass", or "Carpet"
    public int drawSize;          // Number of players in the draw
    public String level;          // Tournament level: "G" (Grand Slam), "M" (Masters), "A" (ATP), "D" (Davis Cup), etc.
    public String date;           // End date of tournament, yyyymmdd

    public Tournament(String tourneyId, String name, String surface, int drawSize, String level, String date) {
        this.tourneyId = tourneyId;
        this.name = name;
        this.surface = surface;
        this.drawSize = drawSize;
        this.level = level;
        this.date = date;
    }

public static Player getWinner(Match match) {
        return match.winner;
    }


    @Override
    public String toString() {
        return tourneyId+" "+name + " (" + date + ")";
    }


public String getTourneyId() {
    return tourneyId;
}

public void setTourneyId(String tourneyId) {
    this.tourneyId = tourneyId;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getSurface() {
    return surface;
}

public void setSurface(String surface) {
    this.surface = surface;
}

public int getDrawSize() {
    return drawSize;
}

public void setDrawSize(int drawSize) {
    this.drawSize = drawSize;
}

public String getLevel() {
    return level;
}

public void setLevel(String level) {
    this.level = level;
}

public String getDate() {
    return date;
}

public void setDate(String date) {
    this.date = date;
}
}