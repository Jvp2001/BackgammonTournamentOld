package com.joshuapetersen.backgammontournament.data;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MatchInfo
{

    //TODO make sure 11 cannot in tie break.

    private SimpleStringProperty contestantOne, contestantTwo;
    private SimpleIntegerProperty contestantOnePoints, contestantTwoPoints;
    private transient boolean gameFinished;
    private transient SimpleBooleanProperty gameFinishedProperty;

    private transient String winner;
    private transient MatchWonBy wonBy;

    public MatchInfo(String contestantOne, String contestantTwo, int contestantOnePoints, int contestantTwoPoints)
    {
        this.contestantOne = new SimpleStringProperty(contestantOne);
        this.contestantTwo = new SimpleStringProperty(contestantTwo);
        this.contestantOnePoints = new SimpleIntegerProperty(contestantOnePoints);
        this.contestantTwoPoints = new SimpleIntegerProperty(contestantTwoPoints);


        checkForGameFinished();

        this.gameFinishedProperty = new SimpleBooleanProperty(this.gameFinished);
        this.contestantOnePoints.addListener((observable, oldValue, newValue) ->
        {
            int value = (int) (newValue != null ? newValue : oldValue);
            System.out.println("Property cahnged");
            if (TournamentRules.wonGame(value))
            {
                System.out.println("Game finished");
                gameFinished = true;
            }

        });
        this.contestantTwoPoints.addListener((observable, oldValue, newValue) ->
        {
            int value = (int) (newValue != null ? newValue : oldValue);
            if (TournamentRules.wonGame(value))
            {
                gameFinished = true;
            }
        });

        winner = getContestantTwo();
        wonBy = MatchWonBy.CONTESTENT_TWO;

    }

    public boolean gameFinishedPropertyProperty()
    {
        return gameFinishedProperty.get();
    }

    public void setGameFinishedProperty(boolean gameFinishedProperty)
    {
        this.gameFinishedProperty.set(gameFinishedProperty);
    }


    public void checkForGameFinished()
    {
        this.gameFinished = TournamentRules.wonGame(this.contestantOnePoints.get()) || TournamentRules.wonGame(
                this.getContestantTwoPoints());
        if (TournamentRules.wonGame(this.getContestantOnePoints()))
        {
            this.wonBy = MatchWonBy.CONTESTENT_ONE;
            winner = this.getContestantOne();
        }
        else if (TournamentRules.wonGame(this.contestantTwoPoints.get()))
        {
            System.out.println("TWO!");
            this.wonBy = MatchWonBy.CONTESTENT_TWO;
            this.winner = this.contestantTwo.getName();
        }
        else
        {
            this.wonBy = MatchWonBy.NONE;
        }
            System.out.println(this.winner);
            System.out.println(winner);
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


    public String getWinner()
    {
        return winner;
    }

    public void setWinner(String winner)
    {
        this.winner = winner;
    }

    public MatchWonBy getWonBy()
    {
        return wonBy;
    }

    public void setWonBy(MatchWonBy wonBy)
    {
        this.wonBy = wonBy;
    }

    public boolean getGameFinished()
    {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished)
    {
        this.gameFinished = gameFinished;
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
