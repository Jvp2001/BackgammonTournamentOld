package com.joshuapetersen.backgammontournament.ui.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlayerMatchTableContainer extends VBox
{
    private SimpleStringProperty titleProperty = new SimpleStringProperty("Josh");
    private CurrentMatchesTable playerMatchesTable = new CurrentMatchesTable();
    private Label tableTitle = new Label("Josh");

    public PlayerMatchTableContainer()
    {
        super(17);
        setup();
    }

    private void setup()
    {

        titleProperty.addListener((observable, oldValue, newValue) ->
        {
            if (!oldValue.equalsIgnoreCase(newValue))
            {
                tableTitle.setText(newValue);
                playerMatchesTable.setContestantName(newValue);

            } else
            {
                tableTitle.setText(oldValue);
                playerMatchesTable.setContestantName(oldValue);
            }
        });
        this.getStylesheets().setAll(
                getClass().getClassLoader().getResource("stylesheets/MatchTableContainer.css").toExternalForm());
        tableTitle.getStyleClass().setAll("TableTitle");
        this.getChildren().setAll(tableTitle, playerMatchesTable);
    }


    public String getTitleProperty()
    {
        return titleProperty.get();
    }

    public SimpleStringProperty titlePropertyProperty()
    {
        return titleProperty;
    }

    public void setTitleProperty(String titleProperty)
    {
        this.titleProperty.set(titleProperty);
    }
}
