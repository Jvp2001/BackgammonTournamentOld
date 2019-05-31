package com.joshuapetersen.backgammontournament.ui.components;

import com.joshuapetersen.backgammontournament.data.MatchInfo;
import com.joshuapetersen.backgammontournament.data.MatchWonBy;
import com.joshuapetersen.backgammontournament.ui.utilities.Calculator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

/**
 *
 * Code base on <a herf="https://stackoverflow.com/questions/27900344/how-to-make-a-table-column-with-String-datatype-editable-without-changing-it-to">James_D</a> code.
 */
public class IntegerEditingCell extends TableCell<MatchInfo, String>
{

    private final TextField textField = new TextField();
    private final Pattern intPattern = Pattern.compile("-?\\d+");
    /**Taken from <a herf="https://stackoverflow.com/questions/11009320/validate-mathematical-expressions-using-regular-expression">Kapa and p.s.w.g</a>**/
    private final Pattern exprPattern = Pattern.compile("/^(\\\\d+)\\\\s*([+\\-*\\\\/])\\\\s*(\\\\d+)$/\n");
    private MatchWonBy contestantType = MatchWonBy.NONE;

    public void setContestantType(MatchWonBy contestantType)
    {
        this.contestantType = contestantType;
    }

    public MatchWonBy getContestantType()
    {
        return contestantType;
    }

    public IntegerEditingCell(MatchWonBy contestantType)
    {
        this.contestantType = contestantType;
        textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) ->
        {
            if (!isNowFocused)
            {
                processEdit();
            }
        });
        textField.setOnAction(event -> processEdit());
    }

    private void processEdit()
    {
        String text = textField.getText();
        if (text.equalsIgnoreCase(getItem()) || text.equalsIgnoreCase(""))
        {
            cancelEdit();
        }
        else
        {
            Integer floor = Math.toIntExact(Math.round(Calculator.eval(text)));
            System.out.println(contestantType +": "+floor);
            commitEdit(floor.toString());
        }
    }

    @Override
    public void updateItem(String value, boolean empty)
    {
        super.updateItem(value, empty);
        if (empty)
        {
            setText(null);
            setGraphic(null);
        }
        else if (isEditing())
        {
            setText(null);
            textField.setText(value);
            setGraphic(textField);
        }
        else
        {
            setText(value.toString());
            setGraphic(null);
        }
    }

    @Override
    public void startEdit()
    {
        super.startEdit();
        System.out.println("Editing...");
        Integer value = Integer.parseInt(getItem());
        if (value != null)
        {
            textField.setText(value.toString());
            setGraphic(textField);
            setText(null);
        }
    }

    @Override
    public void cancelEdit()
    {
        super.cancelEdit();
        setText(getItem().toString());
        setGraphic(null);
    }

    // This seems necessary to persist the edit on loss of focus; not sure why:
    @Override
    public void commitEdit(String value)
    {
        super.commitEdit(value);
        switch (this.contestantType)
        {
            case CONTESTENT_ONE:
                ((MatchInfo) this.getTableRow().getItem()).setContestantOnePoints(Integer.parseInt(value));
                System.out.println(contestantType);
                break;
            case CONTESTENT_TWO:
                ((MatchInfo) this.getTableRow().getItem()).setContestantTwoPoints(Integer.parseInt(value));
                System.out.println(contestantType);
                break;
            case NONE:
                break;

        }
    }
}

