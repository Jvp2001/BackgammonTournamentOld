package com.joshuapetersen.backgammontournament.data;

import java.util.ArrayList;
import java.util.List;

public class Player
{
    private String name;
    private int rank = 1, gamesPlayed = 0,gamesWon = 0, gamesLost = 0, totalPoints = 0;


    public Player(String name)
    {
        this.name = name;
    }
    public Player(String name, int gamesPlayed, int gamesWon, int totalPoints)
    {
        this.name = name;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.totalPoints = totalPoints;
    }

    public Player(String name, int rank, int gamesPlayed, int gamesWon, int gamesLost, int totalPoints,
            MatchInfo...matches)
    {
        this.name = name;
        this.rank = rank;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.totalPoints = totalPoints;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getRank()
    {
        return rank;
    }

    public void setRank(int rank)
    {
        this.rank = rank;
    }

    public int getGamesPlayed()
    {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed)
    {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon()
    {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon)
    {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost()
    {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost)
    {
        this.gamesLost = gamesLost;
    }

    public int getTotalPoints()
    {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints)
    {
        this.totalPoints = totalPoints;
    }


}
