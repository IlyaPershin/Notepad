/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NotepadJava;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.undo.CannotRedoException;

/**
 *
 * @author Илья
 */
public class RedoAction extends AbstractAction {
        public RedoAction() {
            super("Redo");
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                TextWindow.undo.redo();
            } catch (CannotRedoException ex) {
                System.out.println("Unable to redo: " + ex);
                ex.printStackTrace();
            }
            updateRedoState();
            TextWindow.undoAction.updateUndoState();
        }

        protected void updateRedoState() {
            if (TextWindow.undo.canRedo()) {
                setEnabled(true);
                putValue(Action.NAME, TextWindow.undo.getRedoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }
    }