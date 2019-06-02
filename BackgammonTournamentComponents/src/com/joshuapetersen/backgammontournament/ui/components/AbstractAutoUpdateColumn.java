package com.joshuapetersen.backgammontournament.ui.components;

import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;

public abstract class AbstractAutoUpdateColumn<S,T> extends TableColumn<S,T>
{
    public AbstractAutoUpdateColumn()
    {
        super();
    }

    protected abstract void setup();
    protected abstract void startEdit(CellEditEvent<S,T> event);
    protected abstract void stopEdit(CellEditEvent<S,T> event);
    protected abstract void commitEdit(CellEditEvent<S,T> event);


    public AbstractAutoUpdateColumn(String text)
    {
        super(text);

    }

}
