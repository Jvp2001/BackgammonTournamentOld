package com.joshuapetersen.backgammontournament.ui.utilities;

import com.sun.javafx.scene.control.skin.TableViewSkin;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TableViewUtilites
{


    public static <T, C> void setupColumn(TableColumn<T, C> tableColumn, String propertyName, boolean editable)
    {
        tableColumn.setEditable(editable);
        tableColumn.setCellValueFactory(new PropertyValueFactory<T, C>(propertyName));

    }

}
