package com.joshuapetersen.backgammontournament.ui.components;

import javafx.event.EventHandler;

public class AutoUpdateColumn<S,T> extends AbstractAutoUpdateColumn<S,T>
{
    public AutoUpdateColumn()
    {
        super();
    }

    public AutoUpdateColumn(String text)
    {
        super(text);
    }

    @Override
    protected void setup()
    {
        setOnEditStart(this::startEdit);
        setOnEditCancel(this::stopEdit);
        setOnEditCommit(this::commitEdit);
        setEditable(true);
    }

    @Override
    protected void startEdit(CellEditEvent<S, T> event)
    {

    }

    @Override
    protected void stopEdit(CellEditEvent<S, T> event)
    {

    }

    @Override
    protected void commitEdit(CellEditEvent<S, T> event)
    {

    }


}
