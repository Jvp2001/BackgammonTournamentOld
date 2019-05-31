package com.joshuapetersen.backgammontournament.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackgammonTournamentData
{
    @SerializedName(value = "tournamentName", alternate = {"Name","name","Tournament Name","TournamentName"})
    private String tournamentName = "Backgammon Tournament";
    @SerializedName(value = "pointsToWin",alternate = {"Points To Win","PointsToWin"})
    private  final int pointsToWinGame;
    @SerializedName(value = "players", alternate = {"Players","Contestants","contestants"})
    private Player[] players;

    private List<MatchInfo> matches = new ArrayList<>();

    //transient MatchInfo[][] matches;
//    private BackgammonTournamentData(Player[] players)
//    {
//        this.players.setAll(Arrays.asList(players));
//    }
//    public BackgammonTournamentData() {}

    public BackgammonTournamentData(String tournamentName,int pointsToWinGame, Player...players)
    {
        this.tournamentName = tournamentName;
        this.pointsToWinGame = pointsToWinGame;
        this.players = players;

    }

    public BackgammonTournamentData(String tournamentName, int pointsToWinGame,
            Player[] players, MatchInfo...matches)
    {
        this.tournamentName = tournamentName;
        this.pointsToWinGame = pointsToWinGame;
        this.players = players;
        this.matches = Arrays.asList(matches);

    }

//    public  void storeMatches()
//    {
//        Player[] players1 = this.players;
//        matches = new MatchInfo[this.getPlayers().length-2][this.getPlayers().length-2];
//        for (int i = 0; i < players1.length; i++)
//        {
//            Player player = players1[i];
//            player.setMatches(new MatchInfo[players.length-2]);
//            for(int j = 0; i < players.length; i++)
//            {
//
//                String name = player.getContestantOne();
//                if(!name.equalsIgnoreCase(player.getContestantOne()) && !player.hasMatches())
//                {
//                    player.getMatches()[j] = new MatchInfo(name,0);
//                }
//                else if (player.hasMatches())
//                {
//                    break;
//                }
//            }
//                matches[i] = player.getMatches();
//
//
//        }
//        DataManager.storeTournamentData();
//    }

    public BackgammonTournamentData(String tournamentName, int pointsToWinGame)
    {
        this.tournamentName = tournamentName;
        this.pointsToWinGame = pointsToWinGame;
    }


    public String getTournamentName()
    {
        return tournamentName;
    }

    public Player[] getPlayers()
    {
        return players;
    }

    public int getPointsToWin()
    {
        return pointsToWinGame;
    }


    public void setPlayers(Player[] players)
    {
        this.players = players;
    }



    public Player findPlayer(String name)
    {
        for (Player player : players)
        {
            if(name.equalsIgnoreCase(player.getName()))
                return player;
        }
        return null;

    }

    public List<MatchInfo> getMatches()
    {
        return matches;
    }
}
