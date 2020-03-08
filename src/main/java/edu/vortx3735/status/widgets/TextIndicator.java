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

@Description(dataTypes = { NumberType.class }, name = "Multicolor selector")
@ParametrizedController(value = "TextIndicator.fxml")
public class TextIndicator extends BorderlessSimpleAnnotatedWidget<Number> {

	@FXML
	protected Pane _thePane;

	@FXML
	private StatusText _statusText;

	private StatusText.DisplayState _lastState = StatusText.DisplayState.NEUTRAL;

	private SimpleDoubleProperty _greenMin = new SimpleDoubleProperty(0.0);
	private SimpleDoubleProperty _greenMax = new SimpleDoubleProperty(0.0);
	private SimpleBooleanProperty _greenAnimate = new SimpleBooleanProperty(false);

	private SimpleDoubleProperty _redMin = new SimpleDoubleProperty(0.0);
	private SimpleDoubleProperty _redMax = new SimpleDoubleProperty(0.0);
	private SimpleBooleanProperty _redAnimate = new SimpleBooleanProperty(false);

	private SimpleDoubleProperty _yellowMin = new SimpleDoubleProperty(0.0);
	private SimpleDoubleProperty _yellowMax = new SimpleDoubleProperty(0.0);
	private SimpleBooleanProperty _yellowAnimate = new SimpleBooleanProperty(false);

	private SimpleDoubleProperty _blackMin = new SimpleDoubleProperty(0.0);
	private SimpleDoubleProperty _blackMax = new SimpleDoubleProperty(0.0);
	private SimpleBooleanProperty _blackAnimate = new SimpleBooleanProperty(false);

	private SimpleDoubleProperty _greyMin = new SimpleDoubleProperty(0.0);
	private SimpleDoubleProperty _greyMax = new SimpleDoubleProperty(0.0);
	private SimpleBooleanProperty _greyAnimate = new SimpleBooleanProperty(false);

	private SimpleDoubleProperty _wheelRedMin = new SimpleDoubleProperty(0.0);
	private SimpleDoubleProperty _wheelRedMax = new SimpleDoubleProperty(0.0);
	private SimpleBooleanProperty _wheelRedAnimate = new SimpleBooleanProperty(false);

	private SimpleDoubleProperty _wheelYellowMin = new SimpleDoubleProperty(0.0);
	private SimpleDoubleProperty _wheelYellowMax = new SimpleDoubleProperty(0.0);
	private SimpleBooleanProperty _wheelYellowAnimate = new SimpleBooleanProperty(false);

	private SimpleDoubleProperty _wheelGreenMin = new SimpleDoubleProperty(0.0);
	private SimpleDoubleProperty _wheelGreenMax = new SimpleDoubleProperty(0.0);
	private SimpleBooleanProperty _wheelGreenAnimate = new SimpleBooleanProperty(false);

	private SimpleDoubleProperty _wheelBlueMin = new SimpleDoubleProperty(0.0);
	private SimpleDoubleProperty _wheelBlueMax = new SimpleDoubleProperty(0.0);
	private SimpleBooleanProperty _wheelBlueAnimate = new SimpleBooleanProperty(false);

	public Pane getView() {
		return _thePane;
	}

	@Override
	public java.util.List<edu.wpi.first.shuffleboard.api.prefs.Group> getSettings() {

		LinkedList<Group> propertyList = new LinkedList<Group>();

		propertyList.add(Group.of("Definition of Black",
				Setting.of("Min", "The minimum value in the range of values.", _blackMin, Double.class),
				Setting.of("Max", "The maximum value in the range of values.", _blackMax, Double.class), Setting.of(
						"Animate", "Turn on/off animation each time data is received", _blackAnimate, Boolean.class)));

		propertyList.add(Group.of("Definition of Grey",
				Setting.of("Min", "The minimum value in the range of values.", _greyMin, Double.class),
				Setting.of("Max", "The maximum value in the range of values.", _greyMax, Double.class), Setting.of(
						"Animate", "Turn on/off animation each time data is received", _greyAnimate, Boolean.class)));
		
		propertyList.add(Group.of("Definition of Green",
				Setting.of("Min", "The minimum value in the range of values.", _greenMin, Double.class),
				Setting.of("Max", "The maximum value in the range of values.", _greenMax, Double.class), Setting.of(
						"Animate", "Turn on/off animation each time data is received", _greenAnimate, Boolean.class)));

		propertyList.add(Group.of("Definition of Yellow",
				Setting.of("Min", "The minimum value in the range of values.", _yellowMin, Double.class),
				Setting.of("Max", "The maximum value in the range of values.", _yellowMax, Double.class),
				Setting.of("Animate", "Turn on/off animation each time data is received", _yellowAnimate, Boolean.class)));

		propertyList.add(Group.of("Definition of Red",
				Setting.of("Min", "The minimum value in the range of values.", _redMin, Double.class),
				Setting.of("Max", "The maximum value in the range of values.", _redMax, Double.class), 
				Setting.of("Animate", "Turn on/off animation each time data is received", _redAnimate, Boolean.class)));

		propertyList.add(Group.of("Definition of Wheel Red",
				Setting.of("Min", "The minimum value in the range of values.", _wheelRedMin, Double.class),
				Setting.of("Max", "The maximum value in the range of values.", _wheelRedMax, Double.class), 
				Setting.of("Animate", "Turn on/off animation each time data is received", _wheelRedAnimate, Boolean.class)));

		propertyList.add(Group.of("Definition of Wheel Yellow",
				Setting.of("Min", "The minimum value in the range of values.", _wheelYellowMin, Double.class),
				Setting.of("Max", "The maximum value in the range of values.", _wheelYellowMax, Double.class), 
				Setting.of("Animate", "Turn on/off animation each time data is received", _wheelYellowAnimate, Boolean.class)));

		propertyList.add(Group.of("Definition of Wheel Green",
				Setting.of("Min", "The value that corresponds to a display color of green.", _wheelGreenMin, Double.class),
				Setting.of("Max", "The maximum value in the range of values.", _wheelGreenMax, Double.class), 
				Setting.of("Animate", "Turn on/off animation each time data is received", _wheelGreenAnimate, Boolean.class)));

		propertyList.add(Group.of("Definition of Wheel Blue",
				Setting.of("Min", "The minimum value in the range of values.", _wheelBlueMin, Double.class),
				Setting.of("Max", "The maximum value in the range of values.", _wheelBlueMax, Double.class), 
				Setting.of("Animate", "Turn on/off animation each time data is received", _wheelBlueAnimate, Boolean.class)));
		

		return propertyList;
	}

	private void setState(Double forValue) {

		if (forValue >= _blackMin.doubleValue() && forValue <= _blackMax.doubleValue()) {
			if (_blackAnimate.getValue())
				_statusText.doAnimateOnData();

			if (_lastState == StatusText.DisplayState.BLACK)
				return;

			_lastState = StatusText.DisplayState.BLACK;
			_statusText.displayStateProperty().set(_lastState);
			return;
		}

		if (forValue >= _greyMin.doubleValue() && forValue <= _greyMax.doubleValue()) {
			if (_greyAnimate.getValue())
				_statusText.doAnimateOnData();

			if (_lastState == StatusText.DisplayState.GREY)
				return;

			_lastState = StatusText.DisplayState.GREY;
			_statusText.displayStateProperty().set(_lastState);
			return;
		}
		
		
		if (forValue >= _greenMin.doubleValue() && forValue <= _greenMax.doubleValue()) {
			if (_greenAnimate.getValue())
				_statusText.doAnimateOnData();

			if (_lastState == StatusText.DisplayState.GREEN)
				return;

			_lastState = StatusText.DisplayState.GREEN;
			_statusText.displayStateProperty().set(_lastState);
			return;
		}

		if (forValue >= _yellowMin.doubleValue() && forValue <= _yellowMax.doubleValue()) {
			if (_yellowAnimate.getValue())
				_statusText.doAnimateOnData();

			if (_lastState == StatusText.DisplayState.YELLOW)
				return;

			_lastState = StatusText.DisplayState.YELLOW;
			_statusText.displayStateProperty().set(_lastState);
			return;
		}

		if (forValue >= _redMin.doubleValue() && forValue <= _redMax.doubleValue()) {
			if (_redAnimate.getValue())
				_statusText.doAnimateOnData();

			if (_lastState == StatusText.DisplayState.RED)
				return;

			_lastState = StatusText.DisplayState.RED;
			_statusText.displayStateProperty().set(_lastState);
			return;
		}
		
		if (forValue >= _wheelRedMin.doubleValue() && forValue <= _wheelRedMax.doubleValue()) {
			if (_wheelRedAnimate.getValue())
				_statusText.doAnimateOnData();

			if (_lastState == StatusText.DisplayState.WHEELRED)
				return;

			_lastState = StatusText.DisplayState.WHEELRED;
			_statusText.displayStateProperty().set(_lastState);
			return;
		}

		if (forValue >= _wheelYellowMin.doubleValue() && forValue <= _wheelYellowMax.doubleValue()) {
			if (_wheelYellowAnimate.getValue())
				_statusText.doAnimateOnData();

			if (_lastState == StatusText.DisplayState.WHEELYELLOW)
				return;

			_lastState = StatusText.DisplayState.WHEELYELLOW;
			_statusText.displayStateProperty().set(_lastState);
			return;
		}

		if (forValue >= _wheelGreenMin.doubleValue() && forValue <= _wheelGreenMax.doubleValue()) {
			if (_wheelGreenAnimate.getValue())
				_statusText.doAnimateOnData();

			if (_lastState == StatusText.DisplayState.WHEELGREEN)
				return;

			_lastState = StatusText.DisplayState.WHEELGREEN;
			_statusText.displayStateProperty().set(_lastState);
			return;
		}

		if (forValue >= _wheelBlueMin.doubleValue() && forValue <= _wheelBlueMax.doubleValue()) {
			if (_wheelBlueAnimate.getValue())
				_statusText.doAnimateOnData();

			if (_lastState == StatusText.DisplayState.WHEELBLUE)
				return;

			_lastState = StatusText.DisplayState.WHEELBLUE;
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
