package edu.vortx3735.shuffleboard.extensions;

import java.net.URL;
import java.util.ResourceBundle;

import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import edu.wpi.first.shuffleboard.app.components.WidgetTile;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;


/**
 * An extension of the SimpleAnnotatedWidget that will produce a widget without a title bar.
 * 
 * It has been commented extensively to help students learn aspects of JavaFX.
 * 
 * @author Nathan Leach (nleach999@gmail.com)
 */
public abstract class BorderlessSimpleAnnotatedWidget<T> extends SimpleAnnotatedWidget<T>
        implements Initializable, ChangeListener<Bounds> {

    private WidgetTile _myTile = null;

    // Called when the layout changes, this method is from the ChangeListener interface.
    // We use this to intercept the component as it is drawn for the first time so we
    // can remove the title bar on the widget.
    @Override
    public void changed(ObservableValue<? extends Bounds> arg0, Bounds oldValue, Bounds newValue) {

        // We only want to do this once, so check to see if the WidgetTile instance
        // was found.  When the widget is in the gallery, the WidgetTile instance does not exist.
        if (_myTile == null) {

            // Traverse the parent tree untile we find the WidgetTile class.
            Parent p = getView().getParent();

            while (p != null) {
                p = p.getParent();

                if (p != null && p.getClass() == WidgetTile.class) {
                    _myTile = (WidgetTile) p;

                    // Set the title as the ToolTip for the widget so a user can hover over it and
                    // see the name.
                    Tooltip titleTip = new Tooltip();
                    titleTip.textProperty().bind(titleProperty());
                    Tooltip.install(_myTile, titleTip);

                    // Remove the first child of the WidgetTile.  This is the title bar.
                    // Future releases of Shuffleboard may break this.  There may be better
                    // ways to do this.
                    ObservableList<Node> c = _myTile.getChildren();
                    c.remove(0);

                    break;
                }
            }
        }
    }

    /*
    This is a method from the Initializable interface that JavaFX will detect and call before
    a Node is displayed.  There is nothing displayed on the screen when this is called, so
    finding the parent node via getParent() will not work.  What does work here is to set up
    change and invalidation listeners or create data bindings.
    */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // The view is the display of the widget.
        Pane p = getView();

        // If we listen to the layout bounds property, the layout bounds change
        // when the widget is first displayed.  This gives us the opportunity
        // to change the component as it is getting drawn on the screen.
        if (p != null)
            p.layoutBoundsProperty().addListener(this);

    }

}