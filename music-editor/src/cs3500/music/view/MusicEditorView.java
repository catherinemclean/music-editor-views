package cs3500.music.view;

import cs3500.music.model.MusicEditorModel;

/**
 * An interface to view MusicEditorModels
 */
public interface MusicEditorView {
  /** Draws the entire model */
  void render(MusicEditorModel model);
}