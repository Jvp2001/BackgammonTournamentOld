package com.joshuapetersen.backgammontournament.data;

public class TournamentRules
{
    public static final int POINTS_TO_WIN_MATCH = 11;
    public static boolean wonGame(int points)
    {
        return points >= POINTS_TO_WIN_MATCH;
    }
}
