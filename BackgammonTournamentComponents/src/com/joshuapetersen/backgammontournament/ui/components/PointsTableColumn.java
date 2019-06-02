package com.joshuapetersen.backgammontournament.ui.components;

import com.joshuapetersen.backgammontournament.data.MatchInfo;
import com.joshuapetersen.backgammontournament.data.MatchWonBy;
import javafx.beans.property.SimpleStringProperty;

public class PointsTableColumn extends AutoUpdateColumn<MatchInfo,String>
{
    private MatchWonBy contestant;
    String points = "0";
    public PointsTableColumn(MatchWonBy contestant)
    {
        super();
        this.contestant = contestant != null ? contestant : MatchWonBy.NONE;
        setup();
    }

    public PointsTableColumn(String text, MatchWonBy contestant)
    {
        super(text);
        this.contestant = contestant != null ? contestant : MatchWonBy.NONE;
        System.out.println(this.contestant);
        setup();
    }

    @Override
    protected void setup()
    {
        super.setup();
        this.getStyleClass().setAll("Numbers");

        this.setCellFactory(tc -> new PointsCell(contestant));
        switch (contestant)
        {

            case CONTESTENT_ONE:
                this.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getContestantOnePoints())));

                break;
            case CONTESTENT_TWO:
                this.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getContestantTwoPoints())));

                break;
            case NONE:
                break;
        }
    }

    @Override
    protected void startEdit(CellEditEvent<MatchInfo, String> event)
    {
        super.startEdit(event);
    }

    @Override
    protected void stopEdit(CellEditEvent<MatchInfo, String> event)
    {
        super.stopEdit(event);
    }

    @Override
    protected void commitEdit(CellEditEvent<MatchInfo, String> event)
    {
        super.commitEdit(event);
        String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
        updateValue(value, event.getTableView().getItems().get(event.getTablePosition().getRow()));
        getTableView().refresh();   

    }

    private void updateValue(String value, MatchInfo matchInfo)
    {
        switch (contestant)
        {

            case CONTESTENT_ONE:
                matchInfo.setContestantOnePoints(Integer.parseInt(value));
                break;
            case CONTESTENT_TWO:
                matchInfo.setContestantTwoPoints(Integer.parseInt(value));
                break;
            case NONE:
                break;
        }
        this.getTableView().refresh();
    }
}
