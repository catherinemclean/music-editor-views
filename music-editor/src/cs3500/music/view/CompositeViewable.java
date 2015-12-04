package cs3500.music.view;

/**
 * Represents a composite view that is composed of a gui and a midi view.
 * An interface representing a view that extends two other views.
 */
public interface CompositeViewable extends GuiView, MidiView {
  // No extra methods, it only inherits from GuiView and MidiView.
}
