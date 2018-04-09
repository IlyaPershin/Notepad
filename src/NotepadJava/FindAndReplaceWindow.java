/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NotepadJava;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author Илья
 */
public class FindAndReplaceWindow extends JFrame {
    
    int index = 0;
    
    public FindAndReplaceWindow(){
        JFrame findAndReplaceWindow=new JFrame("Найти/Заменить");
        findAndReplaceWindow.setAlwaysOnTop(true);


        JPanel contents = new JPanel();
        contents.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        contents.add(new JLabel("Найти:"),constraints);
        constraints.gridx=1;
        JTextField findString=new JTextField(20);
        contents.add(findString);
        constraints.gridx=0;
        constraints.gridy = 1;
        contents.add(new JLabel("Заменить на:  "),constraints);
        constraints.gridx=1;
        JTextField repString=new JTextField(20);
        contents.add(repString,constraints);
        
        constraints.gridx=2;
        constraints.gridy=0;
        contents.add(createButton("Найти",new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg3)
            {
                String str=findString.getText();

                    if(str.length()>1){
                        index=findSubStr(str,index)-1;
                    }
                    else{
                        index=findSubStr(str,index);
                    }
                    if(index<0){
                        JOptionPane.showMessageDialog(findAndReplaceWindow, "Поиск завершен");
                        findAndReplaceWindow.dispose();
                        index=0;
                    }
            }
        }),
                constraints);
        constraints.gridy=1;
        contents.add(createButton("Заменить первое",new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg4)
            {
                String str=findString.getText();
                String rep=repString.getText();
                replace(str,rep,0); 
            }
        }),constraints);
        
        constraints.gridy=2;
        contents.add(createButton("Заменить всё",new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg5)
            {
                String str=findString.getText();
                String rep=repString.getText();
                replace(str,rep,1); 
            }
        }),constraints);
        
        findAndReplaceWindow.getContentPane().add(contents);
        findAndReplaceWindow.setSize(700, 300);
        findAndReplaceWindow.setVisible(true);
    }
    
    private int findSubStr(String substring,int offset){
        Document doc= TextWindow.txtWindow.getDocument();
        String str="";
        try {
            str = doc.getText(0, doc.getLength());
        } catch (BadLocationException ex) {
            Logger.getLogger(TextWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        int index=str.indexOf(substring,offset);
        if(index!=-1)
        {
        TextWindow.txtWindow.requestFocus();
        TextWindow.txtWindow.setCaretPosition(index);
        TextWindow.txtWindow.setSelectionColor(Color.yellow);
        TextWindow.txtWindow.select(index, index+substring.length());
        return TextWindow.txtWindow.getSelectionEnd();
        }
        else
        {
           return -1; 
        }
        
    }
    
    private void replace(String substring, String replaceString, int replaceMode){
        Document doc=TextWindow.txtWindow.getDocument();
        String str="";
        AttributeSet attr=TextWindow.txtWindow.getCharacterAttributes();
        try {
            str = doc.getText(0, doc.getLength());
            
        } catch (BadLocationException ex) {
            Logger.getLogger(TextWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        String newStr="";
        if (replaceMode==0)
        {
            newStr=str.replaceFirst(substring, replaceString);
        }
        else
        {
            newStr=str.replace(substring, replaceString);
        }
        TextWindow.txtWindow.setText(newStr);
        try {
            doc.insertString(0, newStr, attr);
        } catch (BadLocationException ex) {
            Logger.getLogger(TextWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        TextWindow.txtWindow.setDocument(doc);
    }
    
    private JButton createButton(String text,ActionListener act){
        JButton button=new JButton(text);
        button.addActionListener(act);
        return button;
    }
}
