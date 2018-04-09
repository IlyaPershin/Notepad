/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NotepadJava;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.undo.CannotUndoException;

/**
 *
 * @author Илья
 */
public class UndoAction extends AbstractAction {
        public UndoAction() {
            super("Undo");
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                TextWindow.undo.undo();
            } catch (CannotUndoException ex) {
                System.out.println("Unable to undo: " + ex);
                ex.printStackTrace();
            }
            updateUndoState();
            TextWindow.redoAction.updateRedoState();
        }

        protected void updateUndoState() {
            if (TextWindow.undo.canUndo()) {
                setEnabled(true);
                putValue(Action.NAME, TextWindow.undo.getUndoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Вернуть");
            }
        }
    }