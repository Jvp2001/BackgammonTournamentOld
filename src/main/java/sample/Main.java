package sample;

import com.google.gson.Gson;
import com.joshuapetersen.backgammontournament.data.DataManager;
import com.joshuapetersen.backgammontournament.data.MatchInfo;
import com.joshuapetersen.backgammontournament.ui.utilities.Calculator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hildan.fxgson.FxGsonBuilder;

public class
Main extends Application
{

    public static final int WIDTH = 800, HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/sample.fxml"));
        DataManager.retrieveTournamentData();
        //DataManager.overridePlayers(new Player("Josh"),new Player("Mark"),new Player("Victoria"), new Player("Freya"));
        //primaryStage.setTitle(DataManager.getBackgammonTournamentData().getTournamentName());
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
        Gson gson = new FxGsonBuilder().create();
        MatchInfo matchInfo = gson.fromJson("" +
                "{" +
                "\"contestantOne\":\"Joshua\"," +
                "\"contestantTwo\":\"Dominic\"," +
                "\"contestantOnePoints\":0," +
                "\"contestantTwoPoints\":0" +
                "}", MatchInfo.class);

        System.out.println(matchInfo);

        System.out.println(Calculator.eval("1-3+2"));
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
