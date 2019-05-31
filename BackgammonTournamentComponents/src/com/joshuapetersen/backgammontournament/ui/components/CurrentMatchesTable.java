package com.joshuapetersen.backgammontournament.ui.components;

import com.google.gson.Gson;
import com.joshuapetersen.backgammontournament.data.MatchInfo;
import com.joshuapetersen.backgammontournament.data.MatchWonBy;
import com.joshuapetersen.backgammontournament.data.TournamentRules;
import com.joshuapetersen.backgammontournament.ui.utilities.TableViewUtilites;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import org.hildan.fxgson.FxGsonBuilder;

public class CurrentMatchesTable extends TableView<MatchInfo>
{

    private TableColumn<MatchInfo, char[]> opponentNameColumn = new TableColumn<>("Opponents");
    private TableColumn<MatchInfo, char[]> opponentOneNameColumn = new TableColumn<>("Opponent One");
    private TableColumn<MatchInfo, char[]> opponentTwoNameColumn = new TableColumn<>("Opponent Two");


    private TableColumn<MatchInfo, Integer> pointsColumn = new TableColumn<>("Points");
    //TODO Add change listeners to cell values for the point columns.
    private TableColumn<MatchInfo, String> opponentOnePoints = new TableColumn<>("Opponent One");
    private TableColumn<MatchInfo, String> opponentTwoPoints = new TableColumn<>("Opponent Two");

    private TableColumn<MatchInfo, Boolean> gameFinishedColumn = new TableColumn<>("Finished");
    private TableColumn<MatchInfo, MatchWonBy> wonByTableColumn = new TableColumn<>("Won By");
    private ObservableList<MatchInfo> data = FXCollections.observableArrayList();

    private SimpleStringProperty contestantName = new SimpleStringProperty("Joshua");

    public CurrentMatchesTable()
    {
        setup();
    }


    public void setup()
    {
        Gson gson = new FxGsonBuilder().create();
        MatchInfo matchInfo = gson.fromJson("" +
                "{" +
                "\"contestantOne\":\"Joshua\"," +
                "\"contestantTwo\":\"Dominic\"," +
                "\"contestantOnePoints\":0," +
                "\"contestantTwoPoints\":0" +
                "}", MatchInfo.class);
        data.addAll(matchInfo);
        //setup table
        final TableView<MatchInfo> resultsTable = this;

        resultsTable.setEditable(true);
        resultsTable.getColumns().setAll(opponentNameColumn, pointsColumn, gameFinishedColumn);
        resultsTable.setItems(data);
        resultsTable.setTableMenuButtonVisible(true);
        resultsTable.getStylesheets().setAll(getClass().getClassLoader().getResource(
                "stylesheets/DefaultTableStyles.css").toExternalForm());
       // GUIUtils.autoFitTable(this);
        //setup columns
        //setupColumn(rankColumn,"rank",false);
        setupColumn(opponentNameColumn, "", false);
        setupColumn(opponentOneNameColumn, "contestantOne", !false);
        setupColumn(opponentTwoNameColumn, "contestantTwo", !false);
        opponentNameColumn.getColumns().addAll(opponentOneNameColumn, opponentTwoNameColumn);

        //GUIUtils.autoFitTable(this, opponentNameColumn);
        setupColumn(pointsColumn, "", false);
        setupColumn(opponentOnePoints, "", true, "Number");
        opponentOnePoints.setCellValueFactory(param ->
                new SimpleStringProperty(String.valueOf(param.getValue().getContestantOnePoints())));
        opponentOnePoints.setCellFactory(param ->
        {
            IntegerEditingCell integerEditingCell = new IntegerEditingCell(MatchWonBy.CONTESTENT_ONE);
            System.out.println("this."+integerEditingCell.getContestantType());
            return integerEditingCell;
        });

        setupColumn(opponentTwoPoints, "", true, "Number");
        opponentTwoPoints.setCellValueFactory(param ->
                new SimpleStringProperty(String.valueOf(param.getValue().getContestantTwoPoints())));
        opponentOnePoints.setCellFactory(param ->
        {
            IntegerEditingCell integerEditingCell = new IntegerEditingCell(MatchWonBy.CONTESTENT_TWO);
            System.out.println("this."+integerEditingCell.getContestantType());
            integerEditingCell.setContestantType(MatchWonBy.CONTESTENT_ONE);
            return integerEditingCell;
        });
        pointsColumn.getColumns().setAll(opponentOnePoints, opponentTwoPoints);

        setupColumn(gameFinishedColumn, "gameFinished", false);
        gameFinishedColumn.setCellFactory(param -> new CheckBoxTableCell<MatchInfo, Boolean>()
        {
            @Override
            public void updateItem(Boolean item, boolean empty)
            {
                super.updateItem(item, empty);

                if(!empty)
                {

                    int pointsOne = Integer.parseInt(opponentOnePoints.get());
                    int pointsTwo = Integer.parseInt(opponentTwoPoints.getText());
                    this.setItem(TournamentRules.wonGame(pointsOne) || TournamentRules.wonGame(pointsTwo));
                }
            }
        });
        setupColumn(wonByTableColumn, "wonBy", false);

        resultsTable.refresh();
        System.out.println(resultsTable.getItems().get(0));
    }
    private <T> void setupColumn(TableColumn<MatchInfo, T> tableColumn, String propertyName, boolean editable,
            String... cssClasses)
    {
        TableViewUtilites.setupColumn(tableColumn, propertyName, editable);
        tableColumn.getStyleClass().setAll(cssClasses);
    }

    public String getContestantName()
    {
        return contestantName.get();
    }

    public SimpleStringProperty contestantNameProperty()
    {
        return contestantName;
    }

    public void setContestantName(String contestantName)
    {
        this.contestantName.set(contestantName);
    }
}
