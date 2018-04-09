/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NotepadJava;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
/**
 *
 * @author Мария
 */
public class СreateNewFile implements ActionListener
{
    @Override 
    public void actionPerformed(ActionEvent e)
    {
        TextWindow txtFrm=new TextWindow();
        txtFrm.file=new File(txtFrm.getTitle());
        MainWindow.index++;
    }
}
