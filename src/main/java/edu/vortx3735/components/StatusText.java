package edu.vortx3735.components;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import animatefx.animation.Pulse;
import animatefx.animation.Wobble;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;


/**
 * This is a generic JavaFX control that implements the graphical part of
 * a shuffleboard widget.  The widget provides the logic for controling the
 * graphical change, this control implements the graphical representation
 * of the widget.
 * 
 * @author Nathan Leach (nleach999@gmail.com)
 */
public class StatusText extends VBox implements Initializable, ChangeListener<StatusText.DisplayState> {

    @FXML
    private Label _textContent;
    private Pulse _pulser;
    private Wobble _wobbler;

    public enum DisplayState {
        NEUTRAL, BLACK, GREY, GREEN, YELLOW, RED, WHEELRED, WHEELYELLOW, WHEELGREEN, WHEELBLUE
    }

    private ObjectProperty<DisplayState> _stateProperty = new SimpleObjectProperty<DisplayState>(DisplayState.NEUTRAL);

    public StatusText() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StatusText.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String getText() {
        return textProperty().get();
    }

    public void setText(String value) {
        textProperty().set(value);
    }

    public StringProperty textProperty() {
        return _textContent.textProperty();
    }

    public ObjectProperty<Paint> textColorProperty() {
        return _textContent.textFillProperty();
    }

    public ObjectProperty<DisplayState> displayStateProperty() {
        return _stateProperty;
    }

    public void doAnimateOnData ()
    {
        _wobbler.play();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        _stateProperty.addListener(this);
        _stateProperty.setValue(DisplayState.NEUTRAL);

        // The pulser expands and contracts the context when 
        // the state changes.
        _pulser = new Pulse (_textContent);

        // The wobbler makes the context wobble visibly on demand.
        _wobbler = new Wobble (_textContent);
    }

    @Override
    public void changed(ObservableValue<? extends DisplayState> arg0, DisplayState arg1, DisplayState arg2) {
        _textContent.getStyleClass().clear();
        _textContent.getStyleClass().add(arg2.toString().toLowerCase());
        _pulser.play();

    }

}
