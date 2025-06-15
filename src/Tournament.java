package src;

public class Tournament {
public String tourneyId;     
    public String name;         
    public String surface;      
    public int drawSize;
    public String level;         
    public String date;           

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