package edu.vortx3735.status;

import java.util.List;

import com.google.common.collect.ImmutableList;

import edu.vortx3735.status.widgets.TextIndicator;
import edu.wpi.first.shuffleboard.api.plugin.Description;
import edu.wpi.first.shuffleboard.api.plugin.Plugin;
import edu.wpi.first.shuffleboard.api.widget.ComponentType;
import edu.wpi.first.shuffleboard.api.widget.WidgetType;

@Description(group = "VorTX 3735", name = "Status Plugins", summary = "Plugins for indicating status", version = "1.0.0")
public class Entry extends Plugin {
	@Override
	@SuppressWarnings("all")
	public List<ComponentType> getComponents() {

		return ImmutableList.of(WidgetType.forAnnotatedWidget(TextIndicator.class));
	}
}
