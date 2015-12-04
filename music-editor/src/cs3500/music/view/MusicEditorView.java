package cs3500.music.view;

import cs3500.music.model.MusicEditorModel;

/**
 * An interface to view MusicEditorModels
 */
public interface MusicEditorView {
  /**
   * Draw the entire model
   *
   * @param model the model to render
   */
  void render(MusicEditorModel model);
}