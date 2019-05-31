package com.joshuapetersen.backgammontournament.ui.components;

import com.joshuapetersen.backgammontournament.data.DataManager;
import com.joshuapetersen.backgammontournament.data.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.HashSet;
import java.util.Set;

import static com.joshuapetersen.backgammontournament.ui.utilities.TableViewUtilites.setupColumn;

public class BackgammonTournamentTable extends TableView<Player>
{

    private TableColumn<Player, char[]> nameColumn = new TableColumn<>("Name");
    private TableColumn<Player, Integer> gamesWonColumn = new TableColumn<>("Games Won");
    private TableColumn<Player, Integer> totalPointsColumn = new TableColumn<>("Total Points");
    private ObservableList<Player> data;


    public BackgammonTournamentTable()
    {
        setup();
    }


    public void setup()
    {


        //setup table
        TableView<Player> resultsTable = this;

        resultsTable.setEditable(true);
        resultsTable.getColumns().setAll(nameColumn, gamesWonColumn, totalPointsColumn);
        data = FXCollections.observableArrayList(DataManager.getBackgammonTournamentData().getPlayers());

        resultsTable.setItems(FXCollections.observableArrayList(data));
        resultsTable.setTableMenuButtonVisible(true);
        resultsTable.getSortOrder().setAll(totalPointsColumn);

        //setup columns
        //setupColumn(rankColumn,"rank",false);
        setupColumn(nameColumn, "name", false);
        nameColumn.setSortable(false);
        setupColumn(gamesWonColumn, "gamesWon", false);
        gamesWonColumn.getStyleClass().setAll("centredText");
        gamesWonColumn.setStyle("-fx-alignment: CENTER;");
        gamesWonColumn.setSortable(false);
        setupColumn(totalPointsColumn, "totalPoints", false);
        totalPointsColumn.getStyleClass().setAll("centredText");
        totalPointsColumn.setStyle("-fx-alignment: CENTER;");
    }

}
