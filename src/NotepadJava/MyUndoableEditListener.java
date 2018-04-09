/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NotepadJava;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

/**
 *
 * @author Илья
 */
public class MyUndoableEditListener implements UndoableEditListener {
        public void undoableEditHappened(UndoableEditEvent e) {
            TextWindow.undo.addEdit(e.getEdit());
            TextWindow.undoAction.updateUndoState();
            TextWindow.redoAction.updateRedoState();
        }
    }
