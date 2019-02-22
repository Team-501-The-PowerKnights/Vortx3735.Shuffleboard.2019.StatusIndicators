package edu.vortx3735.status.widgets;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import edu.vortx3735.components.StatusText;
import edu.vortx3735.shuffleboard.extensions.BorderlessSimpleAnnotatedWidget;
import edu.wpi.first.shuffleboard.api.data.types.NumberType;
import edu.wpi.first.shuffleboard.api.prefs.Group;
import edu.wpi.first.shuffleboard.api.prefs.Setting;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

@Description(dataTypes = { NumberType.class }, name = "VorTx 3735 Text Status Indicator")
@ParametrizedController(value = "TextIndicator.fxml")
public class TextIndicator extends BorderlessSimpleAnnotatedWidget<Number> {

	@FXML
	protected Pane _thePane;

	@FXML
	private StatusText _statusText;

	private StatusText.DisplayState _lastState = StatusText.DisplayState.NEUTRAL;

	private SimpleDoubleProperty _normalMin = new SimpleDoubleProperty(0.0);
	private SimpleDoubleProperty _normalMax = new SimpleDoubleProperty(0.0);
	private SimpleBooleanProperty _normalAnimate = new SimpleBooleanProperty(false);

	private SimpleDoubleProperty _warningMin = new SimpleDoubleProperty(0.0);
	private SimpleDoubleProperty _warningMax = new SimpleDoubleProperty(0.0);
	private SimpleBooleanProperty _warningAnimate = new SimpleBooleanProperty(false);

	private SimpleDoubleProperty _alertMin = new SimpleDoubleProperty(0.0);
	private SimpleDoubleProperty _alertMax = new SimpleDoubleProperty(0.0);
	private SimpleBooleanProperty _alertAnimate = new SimpleBooleanProperty(false);

	public Pane getView() {
		return _thePane;
	}

	@Override
	public java.util.List<edu.wpi.first.shuffleboard.api.prefs.Group> getSettings() {

		LinkedList<Group> propertyList = new LinkedList<Group>();

		propertyList.add(Group.of("Definition of Normal",
				Setting.of("Min", "The minimum value in the range of values.", _normalMin, Double.class),
				Setting.of("Max", "The maximum value in the range of values.", _normalMax, Double.class), Setting.of(
						"Animate", "Turn on/off animation each time data is received", _normalAnimate, Boolean.class)));

		propertyList.add(Group.of("Definition of Warning",
				Setting.of("Min", "The minimum value in the range of values.", _warningMin, Double.class),
				Setting.of("Max", "The maximum value in the range of values.", _warningMax, Double.class),
				Setting.of("Animate", "Turn on/off animation each time data is received", _warningAnimate,
						Boolean.class)));

		propertyList.add(Group.of("Definition of Alert",
				Setting.of("Min", "The minimum value in the range of values.", _alertMin, Double.class),
				Setting.of("Max", "The maximum value in the range of values.", _alertMax, Double.class), Setting.of(
						"Animate", "Turn on/off animation each time data is received", _alertAnimate, Boolean.class)));

		return propertyList;
	}

	private void setState(Double forValue) {

		if (forValue >= _normalMin.doubleValue() && forValue <= _normalMax.doubleValue()) {
			if (_normalAnimate.getValue())
				_statusText.doAnimateOnData();

			if (_lastState == StatusText.DisplayState.NORMAL)
				return;

			_lastState = StatusText.DisplayState.NORMAL;
			_statusText.displayStateProperty().set(_lastState);
			return;
		}

		if (forValue >= _warningMin.doubleValue() && forValue <= _warningMax.doubleValue()) {
			if (_warningAnimate.getValue())
				_statusText.doAnimateOnData();

			if (_lastState == StatusText.DisplayState.WARNING)
				return;

			_lastState = StatusText.DisplayState.WARNING;
			_statusText.displayStateProperty().set(_lastState);
			return;
		}

		if (forValue >= _alertMin.doubleValue() && forValue <= _alertMax.doubleValue()) {
			if (_alertAnimate.getValue())
				_statusText.doAnimateOnData();

			if (_lastState == StatusText.DisplayState.ALERT)
				return;

			_lastState = StatusText.DisplayState.ALERT;
			_statusText.displayStateProperty().set(_lastState);
			return;
		}

		if (_lastState != StatusText.DisplayState.NEUTRAL) {
			_lastState = StatusText.DisplayState.NEUTRAL;
			_statusText.displayStateProperty().set(_lastState);
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		_statusText.textProperty().bind(titleProperty());

		// Using an anonymous class that implements the ChangeListener<T> interface, every time
		// the bound number changes the value is checked to see if the state (color) needs to be changed.
		dataProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				setState(arg2.doubleValue());
			}

		});

	}

}
