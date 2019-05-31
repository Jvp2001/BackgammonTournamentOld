package com.joshuapetersen.backgammontournament.data;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class MatchInfo
{

    //TODO make sure 11 cannot in tie break.

    private SimpleStringProperty contestantOne,contestantTwo;
    private SimpleIntegerProperty contestantOnePoints, contestantTwoPoints;
    private SimpleBooleanProperty gameFinished = new SimpleBooleanProperty();
    private transient SimpleStringProperty winner = new SimpleStringProperty();
    private transient SimpleObjectProperty<MatchWonBy> wonBy = new SimpleObjectProperty<>(MatchWonBy.NONE);

    public MatchInfo(String contestantOne, String contestantTwo, int contestantOnePoints, int contestantTwoPoints)
    {
        this.contestantOne = new SimpleStringProperty(contestantOne);
        this.contestantTwo = new SimpleStringProperty(contestantTwo);
        this.contestantOnePoints = new SimpleIntegerProperty(contestantOnePoints);
        this.contestantTwoPoints = new SimpleIntegerProperty(contestantTwoPoints);
        this.contestantOnePoints.addListener((observable, oldValue, newValue) ->
        {
           if(TournamentRules.wonGame(newValue.intValue()) && wonBy.get() == MatchWonBy.NONE)
           {
               wonBy.set(MatchWonBy.CONTESTENT_ONE);
               gameFinished.set(true);
           }

            Player player = DataManager.getBackgammonTournamentData().findPlayer(contestantOne);
            player.setTotalPoints(player.getTotalPoints()+newValue.intValue());
            System.out.println("Total Points: "+player.getTotalPoints());

            // TODO update contestants overall points.
        });
        this.contestantTwoPoints.addListener((observable, oldValue, newValue) ->
        {
            if (TournamentRules.wonGame(observable.getValue().intValue()) && wonBy.getValue() == MatchWonBy.NONE)
            {
                wonBy.set(MatchWonBy.CONTESTENT_TWO);
            }

            Player player = DataManager.getBackgammonTournamentData().findPlayer(contestantTwo);
            player.setTotalPoints(player.getTotalPoints()+newValue.intValue());
            // TODO update contestants overall points.
        });
        this.wonBy.addListener((observable, oldValue, newValue) ->
        {
            //TODO update the overall number of matches the contestant has won.
            if(newValue == null) return;
            switch (newValue)
            {
                case CONTESTENT_ONE:
                    winner.set(this.contestantOne.getValue());
                    break;
                case CONTESTENT_TWO:
                    winner.set(this.contestantTwo.getValue());
                    break;
                case NONE:
                    winner.set("");
                    break;
            }
        });
        this.gameFinished.addListener(observable ->
        {
            this.gameFinished.set(wonBy.getValue() != MatchWonBy.NONE);
        });
    }


    public String getContestantOne()
    {
        return contestantOne.get();
    }

    public SimpleStringProperty contestantOneProperty()
    {
        return contestantOne;
    }

    public void setContestantOne(String contestantOne)
    {
        this.contestantOne.set(contestantOne);
    }

    public String getContestantTwo()
    {
        return contestantTwo.get();
    }

    public SimpleStringProperty contestantTwoProperty()
    {
        return contestantTwo;
    }

    public void setContestantTwo(String contestantTwo)
    {
        this.contestantTwo.set(contestantTwo);
    }

    public int getContestantOnePoints()
    {
        return contestantOnePoints.get();
    }

    public SimpleIntegerProperty contestantOnePointsProperty()
    {
        return contestantOnePoints;
    }

    public void setContestantOnePoints(int contestantOnePoints)
    {
        this.contestantOnePoints.set(contestantOnePoints);
    }

    public int getContestantTwoPoints()
    {
        return contestantTwoPoints.get();
    }

    public SimpleIntegerProperty contestantTwoPointsProperty()
    {
        return contestantTwoPoints;
    }

    public void setContestantTwoPoints(int contestantTwoPoints)
    {
        this.contestantTwoPoints.set(contestantTwoPoints);
    }

    public void setGameFinished(boolean gameFinished)
    {
        this.gameFinished.set(gameFinished);
    }

    public String getWinner()
    {
        return winner.get();
    }

    public SimpleStringProperty winnerProperty()
    {
        return winner;
    }

    public void setWinner(String winner)
    {
        this.winner.set(winner);
    }

    public MatchWonBy getWonBy()
    {
        return wonBy.get();
    }

    public SimpleObjectProperty<MatchWonBy> wonByProperty()
    {
        return wonBy;
    }

    public void setWonBy(MatchWonBy wonBy)
    {
        this.wonBy.set(wonBy);
    }

    public boolean isGameFinished()
    {
        return gameFinished.get();
    }

    public SimpleBooleanProperty gameFinishedProperty()
    {
        return gameFinished;
    }


    @Override
    public String toString()
    {
        return "MatchInfo{" +
                "contestantOne=" + contestantOne +
                ", contestantTwo=" + contestantTwo +
                ", contestantOnePoints=" + contestantOnePoints +
                ", contestantTwoPoints=" + contestantTwoPoints +
                ", gameFinished=" + gameFinished +
                ", winner=" + winner +
                ", wonBy=" + wonBy +
                '}';
    }
}
