package com.joshuapetersen.backgammontournament.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hildan.fxgson.FxGson;
import org.hildan.fxgson.FxGsonBuilder;

import java.io.*;
import java.net.URL;

public class DataManager
{
    private static BackgammonTournamentData backgammonTournamentData;
    private static String path = DataManager.class.getClassLoader().getResource(
            "tournament/data/TournamentData.json").getPath();

    public static String[] names;
    public static MatchInfo[] matches;

    static
    {
        retrieveTournamentData();
    }

    public static BackgammonTournamentData retrieveTournamentData()
    {

        try
        {
            File file = new File(path);
            System.out.println("Can read: " + file.canRead());
//            if (backgammonTournamentData != null) return backgammonTournamentData;
            Gson gson = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create();

            BackgammonTournamentData backgammonTournamentData = gson.fromJson(new FileReader(path),
                    BackgammonTournamentData.class);

            Player[] players = backgammonTournamentData.getPlayers();

//            for (int i = 0; i < players.length; i++)
//            {
//                Player player = players[i];
//                names[i] = player.getContestantOne();
//            }
            DataManager.backgammonTournamentData = backgammonTournamentData;
            return backgammonTournamentData;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static BackgammonTournamentData getBackgammonTournamentData()
    {


        return DataManager.backgammonTournamentData;
    }

    /**
     * This will set the players in the tournament, overriding any existent players.
     */
    public static void overridePlayers(Player... players)
    {
        getBackgammonTournamentData().setPlayers(players);
        storeTournamentData();
    }

    public static void storeTournamentData()
    {
        Gson gson = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        String json = gson.toJson(getBackgammonTournamentData());
        try
        {

            gson.toJson(getBackgammonTournamentData(), new FileWriter(
                    path));
            System.out.println(path);
            try (Writer writer = new FileWriter(
                    path))
            {

                gson.toJson(backgammonTournamentData, writer);
            }

            URL resource = DataManager.class.getClassLoader().getResource("tournament/data/TournamentData.json");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Output.json")))
            {
                System.out.println(json);
                writer.write(json);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void setBackgammonTournamentData(
            BackgammonTournamentData backgammonTournamentData)
    {
        DataManager.backgammonTournamentData = backgammonTournamentData;
    }


    public static MatchInfo[] getMatches()
    {
        return getBackgammonTournamentData().getMatches().toArray(new MatchInfo[0]);
    }
}
