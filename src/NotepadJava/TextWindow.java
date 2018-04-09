/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NotepadJava;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.logging.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;
import javax.swing.text.rtf.RTFEditorKit;
import javax.swing.undo.UndoManager;
/**
 *
 * @author Мария
 */

//окно с документов
public class TextWindow extends JFrame{
    public static JTextPane txtWindow=new JTextPane(); //текстовая область
    public boolean wasSaved=false; //был ли хоть раз файл сохранен
    public String fileName=this.getTitle();
    public File file;//текущий файл
    HashMap<Object, Action> actions = createActionTable(txtWindow);
    public static UndoAction undoAction;
    public static RedoAction redoAction;
    public static UndoManager undo = new UndoManager();
    RTFEditorKit redk=new RTFEditorKit();
    
    public TextWindow(){
        super("Новый документ "+MainWindow.index);
        setSize(800,600);
        
        JMenuBar menuBar = new JMenuBar();
        
        menuBar.add(createFileMenu());
        menuBar.add(createEditMenu());
        menuBar.add(createTextStyleMenu());
        menuBar.add(createFontFamilyMenu());
        menuBar.add(createIndentMenu());
        
        JMenuItem findAndReplaceButton = new JMenuItem("Найти и заменить");
        findAndReplaceButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                FindAndReplaceWindow farw = new FindAndReplaceWindow();
            }
        
        });
        menuBar.add(findAndReplaceButton);
        
        setJMenuBar(menuBar);
        
        getContentPane().add(new JScrollPane(txtWindow));
        txtWindow.setContentType("text/rtf");
        txtWindow.setSize(700, 500);
        this.add(txtWindow);
        
        txtWindow.getDocument().addUndoableEditListener(new MyUndoableEditListener());

        setVisible(true);
    }
    
    private JMenu createFileMenu(){
        JMenu document=new JMenu("Документ");
        
        JMenuItem saveFile=new JMenuItem("Сохранить");
        JMenuItem saveFileAs=new JMenuItem("Сохранить как...");
        
        document.add(saveFile);
        document.add(saveFileAs);
        
        //навешиваем действие на кнопку  
        saveFile.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    if (wasSaved)
                        SaveFile();
                    else
                    {
                        try {
                            SaveAs();
                        } catch (BadLocationException ex) {
                            Logger.getLogger(TextWindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        wasSaved=true;
                    }
                }
            });
        
          
        saveFileAs.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        SaveAs();
                    } catch (BadLocationException ex) {
                        Logger.getLogger(TextWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        
        return document;
    }
    
    protected JMenu createEditMenu() {
        JMenu menu = new JMenu("Изменить");

        undoAction = new UndoAction();
        menu.add(undoAction);

        redoAction = new RedoAction();
        menu.add(redoAction);

        menu.addSeparator();
        menu.add(getActionByName(DefaultEditorKit.cutAction));
        menu.add(getActionByName(DefaultEditorKit.copyAction));
        menu.add(getActionByName(DefaultEditorKit.pasteAction));

        menu.addSeparator();

        menu.add(getActionByName(DefaultEditorKit.selectAllAction));
        
        return menu;
    }
    
    
    protected JMenu createIndentMenu(){
        JMenu indentMenu = new JMenu("Отступы");
        indentMenu.add(createLeftIndentMenu());
        indentMenu.add(createRightIndentMenu());
        indentMenu.add(createFirstLineIndentMenu());
        indentMenu.addSeparator();
        indentMenu.add(createSpaceAboveMenu());
        indentMenu.add(createSpaceBelowMenu());
        indentMenu.add(createLineSpacingMenu());
        return indentMenu;
    }
    
    protected JMenu createLeftIndentMenu(){
        JMenu leftIndentMenu = new JMenu("Левый отступ");
        
        JMenuItem Indent0 = new JMenuItem("0");
        Indent0.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLeftIndent(txtWindow, 0);
            }
        
        });
        leftIndentMenu.add(Indent0);
        
        JMenuItem Indent10 = new JMenuItem("10");
        Indent10.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLeftIndent(txtWindow, 10);
            }
        
        });
        leftIndentMenu.add(Indent10);
        
        JMenuItem Indent20 = new JMenuItem("20");
        Indent20.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLeftIndent(txtWindow, 20);
            }
        
        });
        leftIndentMenu.add(Indent20);
        
        JMenuItem Indent30 = new JMenuItem("30");
        Indent30.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLeftIndent(txtWindow, 30);
            }
        
        });
        leftIndentMenu.add(Indent30);
        
        JMenuItem Indent40 = new JMenuItem("40");
        Indent40.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLeftIndent(txtWindow, 40);
            }
        
        });
        leftIndentMenu.add(Indent40);
        
        JMenuItem Indent50 = new JMenuItem("50");
        Indent50.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLeftIndent(txtWindow, 50);
            }
        
        });
        leftIndentMenu.add(Indent50);
        
        return leftIndentMenu;
    }
    
    protected JMenu createRightIndentMenu(){
        JMenu leftIndentMenu = new JMenu("Правый отступ");
        
        JMenuItem Indent0 = new JMenuItem("0");
        Indent0.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeRightIndent(txtWindow, 0);
            }
        
        });
        leftIndentMenu.add(Indent0);
        
        JMenuItem Indent10 = new JMenuItem("10");
        Indent10.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeRightIndent(txtWindow, 10);
            }
        
        });
        leftIndentMenu.add(Indent10);
        
        JMenuItem Indent20 = new JMenuItem("20");
        Indent20.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeRightIndent(txtWindow, 20);
            }
        
        });
        leftIndentMenu.add(Indent20);
        
        JMenuItem Indent30 = new JMenuItem("30");
        Indent30.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeRightIndent(txtWindow, 30);
            }
        
        });
        leftIndentMenu.add(Indent30);
        
        JMenuItem Indent40 = new JMenuItem("40");
        Indent40.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeRightIndent(txtWindow, 40);
            }
        
        });
        leftIndentMenu.add(Indent40);
        
        JMenuItem Indent50 = new JMenuItem("50");
        Indent50.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeRightIndent(txtWindow, 50);
            }
        
        });
        leftIndentMenu.add(Indent50);
        
        return leftIndentMenu;
    }
    
    protected JMenu createFirstLineIndentMenu(){
        JMenu leftIndentMenu = new JMenu("Красная строка");
        
        JMenuItem Indent0 = new JMenuItem("0");
        Indent0.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFirstLineIndent(txtWindow, 0);
            }
        
        });
        leftIndentMenu.add(Indent0);
        
        JMenuItem Indent10 = new JMenuItem("10");
        Indent10.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFirstLineIndent(txtWindow, 10);
            }
        
        });
        leftIndentMenu.add(Indent10);
        
        JMenuItem Indent20 = new JMenuItem("20");
        Indent20.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFirstLineIndent(txtWindow, 20);
            }
        
        });
        leftIndentMenu.add(Indent20);
        
        JMenuItem Indent30 = new JMenuItem("30");
        Indent30.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFirstLineIndent(txtWindow, 30);
            }
        
        });
        leftIndentMenu.add(Indent30);
        
        JMenuItem Indent40 = new JMenuItem("40");
        Indent40.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFirstLineIndent(txtWindow, 40);
            }
        
        });
        leftIndentMenu.add(Indent40);
        
        JMenuItem Indent50 = new JMenuItem("50");
        Indent50.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFirstLineIndent(txtWindow, 50);
            }
        
        });
        leftIndentMenu.add(Indent50);
        
        return leftIndentMenu;
    }
    
    protected JMenu createSpaceAboveMenu(){
        JMenu leftIndentMenu = new JMenu("Над абзацем");
        
        JMenuItem Indent0 = new JMenuItem("0");
        Indent0.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSpaceAbove(txtWindow, 0);
            }
        
        });
        leftIndentMenu.add(Indent0);
        
        JMenuItem Indent10 = new JMenuItem("10");
        Indent10.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSpaceAbove(txtWindow, 10);
            }
        
        });
        leftIndentMenu.add(Indent10);
        
        JMenuItem Indent20 = new JMenuItem("20");
        Indent20.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSpaceAbove(txtWindow, 20);
            }
        
        });
        leftIndentMenu.add(Indent20);
        
        JMenuItem Indent30 = new JMenuItem("30");
        Indent30.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSpaceAbove(txtWindow, 30);
            }
        
        });
        leftIndentMenu.add(Indent30);
        
        JMenuItem Indent40 = new JMenuItem("40");
        Indent40.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSpaceAbove(txtWindow, 40);
            }
        
        });
        leftIndentMenu.add(Indent40);
        
        JMenuItem Indent50 = new JMenuItem("50");
        Indent50.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSpaceAbove(txtWindow, 50);
            }
        
        });
        leftIndentMenu.add(Indent50);
        
        return leftIndentMenu;
    }
    
    protected JMenu createSpaceBelowMenu(){
        JMenu leftIndentMenu = new JMenu("Под абзацем");
        
        JMenuItem Indent0 = new JMenuItem("0");
        Indent0.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSpaceBelow(txtWindow, 0);
            }
        
        });
        leftIndentMenu.add(Indent0);
        
        JMenuItem Indent10 = new JMenuItem("10");
        Indent10.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSpaceBelow(txtWindow, 10);
            }
        
        });
        leftIndentMenu.add(Indent10);
        
        JMenuItem Indent20 = new JMenuItem("20");
        Indent20.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSpaceBelow(txtWindow, 20);
            }
        
        });
        leftIndentMenu.add(Indent20);
        
        JMenuItem Indent30 = new JMenuItem("30");
        Indent30.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSpaceBelow(txtWindow, 30);
            }
        
        });
        leftIndentMenu.add(Indent30);
        
        JMenuItem Indent40 = new JMenuItem("40");
        Indent40.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSpaceBelow(txtWindow, 40);
            }
        
        });
        leftIndentMenu.add(Indent40);
        
        JMenuItem Indent50 = new JMenuItem("50");
        Indent50.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSpaceBelow(txtWindow, 50);
            }
        
        });
        leftIndentMenu.add(Indent50);
        
        return leftIndentMenu;
    }
    
    protected JMenu createLineSpacingMenu(){
        JMenu leftIndentMenu = new JMenu("Между строчками");
        
        JMenuItem Indent0 = new JMenuItem("0");
        Indent0.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLineSpacing(txtWindow, 0f);
            }
        
        });
        leftIndentMenu.add(Indent0);
        
        JMenuItem Indent10 = new JMenuItem("2");
        Indent10.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLineSpacing(txtWindow, 0.2f);
            }
        
        });
        leftIndentMenu.add(Indent10);
        
        JMenuItem Indent20 = new JMenuItem("4");
        Indent20.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLineSpacing(txtWindow, 0.4f);
            }
        
        });
        leftIndentMenu.add(Indent20);
        
        JMenuItem Indent30 = new JMenuItem("6");
        Indent30.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLineSpacing(txtWindow, 0.6f);
            }
        
        });
        leftIndentMenu.add(Indent30);
        
        JMenuItem Indent40 = new JMenuItem("8");
        Indent40.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLineSpacing(txtWindow, 0.8f);
            }
        
        });
        leftIndentMenu.add(Indent40);
        
        JMenuItem Indent50 = new JMenuItem("10");
        Indent50.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLineSpacing(txtWindow, 1f);
            }
        
        });
        leftIndentMenu.add(Indent50);
        
        return leftIndentMenu;
    }
    
    
    protected JMenu createTextStyleMenu(){
        JMenu indentMenu = new JMenu("Работа с текстом");
        
        JMenuItem Indent0 = new JMenuItem("Синий цвет");
        Indent0.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setBlueLetters(txtWindow);
            }
        
        });
        indentMenu.add(Indent0);
        
        JMenuItem Indent10 = new JMenuItem("Чёрный цвет");
        Indent10.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setBlackLetters(txtWindow);
            }
        
        });
        indentMenu.add(Indent10);
        
        indentMenu.addSeparator();
        
        JMenuItem Indent20 = new JMenuItem("Жирный");
        Indent20.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setBold(txtWindow, true);
            }
        
        });
        indentMenu.add(Indent20);
        
        JMenuItem Indent30 = new JMenuItem("Не жирный");
        Indent30.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setBold(txtWindow, false);
            }
        
        });
        indentMenu.add(Indent30);
        
        indentMenu.addSeparator();
        
        JMenuItem Indent40 = new JMenuItem("Курсив");
        Indent40.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setItalic(txtWindow, true);
            }
        
        });
        indentMenu.add(Indent40);
        
        JMenuItem Indent50 = new JMenuItem("Не курсив");
        Indent50.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setItalic(txtWindow, false);
            }
        
        });
        indentMenu.add(Indent50);
        
        indentMenu.addSeparator();
        
        JMenuItem Indent60 = new JMenuItem("Зачёркнутый");
        Indent60.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setStrikeThrough(txtWindow, true);
            }
        
        });
        indentMenu.add(Indent60);
        
        JMenuItem Indent70 = new JMenuItem("Не зачёркнутый");
        Indent70.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setStrikeThrough(txtWindow, false);
            }
        
        });
        indentMenu.add(Indent70);
        return indentMenu;
    }
    
    protected JMenu createFontFamilyMenu(){
        JMenu indentMenu = new JMenu("Шрифт");
        
        JMenuItem Indent0 = new JMenuItem("Times New Roman");
        Indent0.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setFontFamily(txtWindow, "Times New Roman");
            }
        
        });
        indentMenu.add(Indent0);
        
        JMenuItem Indent10 = new JMenuItem("Comic Sans MS");
        Indent10.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setFontFamily(txtWindow, "Comic Sans MS");
            }
        
        });
        indentMenu.add(Indent10);
        
        JMenuItem Indent20 = new JMenuItem("Calibri");
        Indent20.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setFontFamily(txtWindow, "Calibri");
            }
        
        });
        indentMenu.add(Indent20);
        
        JMenuItem Indent30 = new JMenuItem("Arial");
        Indent30.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setFontFamily(txtWindow, "Arial");
            }
        
        });
        indentMenu.add(Indent30);
        
        JMenuItem Indent40 = new JMenuItem("Segoe Script");
        Indent40.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setFontFamily(txtWindow, "Segoe Script");
            }
        
        });
        indentMenu.add(Indent40);
        
        indentMenu.addSeparator();
        
        JMenuItem Indent50 = new JMenuItem("8");
        Indent50.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setFontSize(txtWindow, 8);
            }
        
        });
        indentMenu.add(Indent50);
        
        JMenuItem Indent60 = new JMenuItem("11");
        Indent60.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setFontSize(txtWindow, 11);
            }
        
        });
        indentMenu.add(Indent60);
        
        JMenuItem Indent70 = new JMenuItem("14");
        Indent70.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setFontSize(txtWindow, 14);
            }
        
        });
        indentMenu.add(Indent70);
        
        JMenuItem Indent80 = new JMenuItem("17");
        Indent80.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setFontSize(txtWindow, 17);
            }
        
        });
        indentMenu.add(Indent80);
        
        JMenuItem Indent90 = new JMenuItem("20");
        Indent90.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setFontSize(txtWindow, 20);
            }
        
        });
        indentMenu.add(Indent90);
        return indentMenu;
    }
    
    
    private HashMap<Object, Action> createActionTable(JTextComponent textComponent) {
        HashMap<Object, Action> actions = new HashMap<Object, Action>();
        Action[] actionsArray = textComponent.getActions();
        for (int i = 0; i < actionsArray.length; i++) {
            Action a = actionsArray[i];
            actions.put(a.getValue(Action.NAME), a);
        }
	return actions;
    }

    private Action getActionByName(String name) {
        return actions.get(name);
    }
    

    private void SaveFile() {
        try {
            FileOutputStream os = new FileOutputStream(file);
            //Document doc=jep.getDocument();
            Document doc=txtWindow.getDocument();
            try {
                redk.write(os, doc, 0, doc.getLength());
            } catch (BadLocationException ex) {
                Logger.getLogger(TextWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            os.close();
        } catch (IOException ex) {
            Logger.getLogger(TextWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void SaveAs() throws BadLocationException{
        //создание openfiledialog
        JFileChooser jfc=new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                         "RTF", "rtf");
        jfc.setFileFilter(filter);
        int result=jfc.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION )
        {

                OutputStream os=null;
            try {
                os = new FileOutputStream(jfc.getSelectedFile());
                //Document doc=jep.getDocument();
                Document doc=txtWindow.getDocument();
                    try {
                        redk.write(os, doc, 0, doc.getLength());
                        file=jfc.getSelectedFile();
                    } catch (IOException ex) {
                        Logger.getLogger(TextWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        os.close();
                    } catch (IOException ex) {
                        Logger.getLogger(TextWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                fileName=jfc.getSelectedFile().getName();
                this.setTitle(fileName);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TextWindow.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    os.close();
                } catch (IOException ex) {
                    Logger.getLogger(TextWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            } 
        }
    
    
    private void changeLeftIndent(JTextPane editor, int _indent){
        AttributeSet indent = new SimpleAttributeSet();
        StyleConstants.ParagraphConstants.setLeftIndent((MutableAttributeSet) indent, _indent);
        StyledDocument doc = editor.getStyledDocument();
        
        int start = doc.getParagraphElement(editor.getSelectionStart()).getStartOffset();
        int length = doc.getParagraphElement(editor.getSelectionEnd()).getEndOffset() - start;
        doc.setParagraphAttributes(start, length, indent, false);
    }
    
    private void changeRightIndent(JTextPane editor, int _indent){
        AttributeSet indent = new SimpleAttributeSet();
        StyleConstants.ParagraphConstants.setRightIndent((MutableAttributeSet) indent, _indent);
        StyledDocument doc = editor.getStyledDocument();
        
        int start = doc.getParagraphElement(editor.getSelectionStart()).getStartOffset();
        int length = doc.getParagraphElement(editor.getSelectionEnd()).getEndOffset() - start;
        doc.setParagraphAttributes(start, length, indent, false);
    }
    
    private void changeFirstLineIndent(JTextPane editor, int _indent){
        AttributeSet indent = new SimpleAttributeSet();
        StyleConstants.ParagraphConstants.setFirstLineIndent((MutableAttributeSet) indent, _indent + StyleConstants.ParagraphConstants.getLeftIndent(indent));
        StyledDocument doc = editor.getStyledDocument();
        
        int start = doc.getParagraphElement(editor.getSelectionStart()).getStartOffset();
        int length = doc.getParagraphElement(editor.getSelectionEnd()).getEndOffset() - start;
        doc.setParagraphAttributes(start, length, indent, false);
    }
    
    private void changeSpaceAbove(JTextPane editor, int _indent){
        AttributeSet indent = new SimpleAttributeSet();
        StyleConstants.ParagraphConstants.setSpaceAbove((MutableAttributeSet) indent, _indent);
        StyledDocument doc = editor.getStyledDocument();
        
        int start = doc.getParagraphElement(editor.getSelectionStart()).getStartOffset();
        int length = doc.getParagraphElement(editor.getSelectionEnd()).getEndOffset() - start;
        doc.setParagraphAttributes(start, length, indent, false);
    }
    
    private void changeSpaceBelow(JTextPane editor, int _indent){
        AttributeSet indent = new SimpleAttributeSet();
        StyleConstants.ParagraphConstants.setSpaceBelow((MutableAttributeSet) indent, _indent);
        StyledDocument doc = editor.getStyledDocument();
        
        int start = doc.getParagraphElement(editor.getSelectionStart()).getStartOffset();
        int length = doc.getParagraphElement(editor.getSelectionEnd()).getEndOffset() - start;
        doc.setParagraphAttributes(start, length, indent, false);
    }
    
    private void changeLineSpacing(JTextPane editor, float _indent){
        AttributeSet indent = new SimpleAttributeSet();
        StyleConstants.ParagraphConstants.setLineSpacing((MutableAttributeSet) indent, _indent);
        StyledDocument doc = editor.getStyledDocument();
        
        int start = doc.getParagraphElement(editor.getSelectionStart()).getStartOffset();
        int length = doc.getParagraphElement(editor.getSelectionEnd()).getEndOffset() - start;
        doc.setParagraphAttributes(start, length, indent, false);
    }
    
    
    private void setBlueLetters(JTextPane editor){
        // Изменение стиля части текста
        SimpleAttributeSet blue = new SimpleAttributeSet();
        StyleConstants.setForeground(blue, Color.blue);

        StyledDocument doc = editor.getStyledDocument();
        if(editor.getSelectedText()!=null)
        {
            int start=editor.getSelectionStart();
            int length=editor.getSelectionEnd()-editor.getSelectionStart();
            doc.setCharacterAttributes(start, length, blue, false);
            
        }
        else
        {
            txtWindow.setCharacterAttributes(blue, false);
        }

    }
    
    private void setBlackLetters(JTextPane editor){

        SimpleAttributeSet black = new SimpleAttributeSet();
        StyleConstants.setForeground(black, Color.black);
        StyledDocument doc = editor.getStyledDocument();
        if(editor.getSelectedText()!=null)
        {
            int start=editor.getSelectionStart();
            int length=editor.getSelectionEnd()-editor.getSelectionStart();
            doc.setCharacterAttributes(start, length, black, false);
            
        }
        else
        {
            txtWindow.setCharacterAttributes(black, false);
        }

    }
    
    private void setBold(JTextPane editor,boolean isBold){
        SimpleAttributeSet bold=new SimpleAttributeSet();
        StyleConstants.setBold(bold, isBold);
        StyledDocument doc = editor.getStyledDocument();
        if(editor.getSelectedText()!=null)
        {
            int start=editor.getSelectionStart();
            int length=editor.getSelectionEnd()-editor.getSelectionStart();
            doc.setCharacterAttributes(start, length, bold, false);
            
        }
        else
        {
            txtWindow.setCharacterAttributes(bold, false);
        }
    }
    
    private void setItalic(JTextPane editor,boolean isItalic){
        SimpleAttributeSet italic=new SimpleAttributeSet();
        StyleConstants.setItalic(italic, isItalic);
        StyledDocument doc = editor.getStyledDocument();
        if(editor.getSelectedText()!=null)
        {
            int start=editor.getSelectionStart();
            int length=editor.getSelectionEnd()-editor.getSelectionStart();
            doc.setCharacterAttributes(start, length, italic, false);
            
        }
        else
        {
            txtWindow.setCharacterAttributes(italic, false);
        }
    }
    
    private void setStrikeThrough(JTextPane editor,boolean isItalic){
        SimpleAttributeSet italic=new SimpleAttributeSet();
        StyleConstants.setStrikeThrough(italic, isItalic);
        StyledDocument doc = editor.getStyledDocument();
        if(editor.getSelectedText()!=null)
        {
            int start=editor.getSelectionStart();
            int length=editor.getSelectionEnd()-editor.getSelectionStart();
            doc.setCharacterAttributes(start, length, italic, false);
            
        }
        else
        {
            txtWindow.setCharacterAttributes(italic, false);
        }
    }
    
    private void setFontFamily(JTextPane editor, String isItalic){
        SimpleAttributeSet italic=new SimpleAttributeSet();
        StyleConstants.setFontFamily(italic, isItalic);
        StyledDocument doc = editor.getStyledDocument();
        if(editor.getSelectedText()!=null)
        {
            int start=editor.getSelectionStart();
            int length=editor.getSelectionEnd()-editor.getSelectionStart();
            doc.setCharacterAttributes(start, length, italic, false);
            
        }
        else
        {
            txtWindow.setCharacterAttributes(italic, false);
        }
    }
    
    private void setFontSize(JTextPane editor, int isItalic){
        SimpleAttributeSet italic=new SimpleAttributeSet();
        StyleConstants.setFontSize(italic, isItalic);
        StyledDocument doc = editor.getStyledDocument();
        if(editor.getSelectedText()!=null)
        {
            int start=editor.getSelectionStart();
            int length=editor.getSelectionEnd()-editor.getSelectionStart();
            doc.setCharacterAttributes(start, length, italic, false);
            
        }
        else
        {
            txtWindow.setCharacterAttributes(italic, false);
        }
    }
}