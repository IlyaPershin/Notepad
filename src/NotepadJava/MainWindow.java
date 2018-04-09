/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NotepadJava;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;
/**
 *
 * @author Мария
 */

//Главное окно приложения
public class MainWindow extends JFrame
{
    static public int index=1;
    public MainWindow()
    {
        super("SuperNotepad");
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize(400,200);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        setJMenuBar(menuBar);
        
        setVisible(true);
    }
    
    private JMenu createFileMenu()
    {
        JMenu file=new JMenu("Файл");
        
        JMenuItem newFile=new JMenuItem("Создать");
        JMenuItem openFile=new JMenuItem("Открыть...");
        
        file.add(newFile);
        file.add(openFile);

        СreateNewFile crtNFl=new СreateNewFile();
        newFile.addActionListener(crtNFl);
        openFile.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc=new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                                 "RTF", "rtf");
                jfc.setFileFilter(filter);
                int result = jfc.showOpenDialog(new JFrame());
                if (result == JFileChooser.APPROVE_OPTION )
                {
                    InputStream os=null;
                    RTFEditorKit rtf = new RTFEditorKit();
                    Document doc = rtf.createDefaultDocument();

                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(jfc.getSelectedFile());
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    InputStreamReader i = null;
                    try {
                        i = new InputStreamReader(fis, "UTF-8");
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    try {
                        rtf.read(i,doc,0);
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (BadLocationException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    TextWindow txtFrm=new TextWindow();
                    txtFrm.file = jfc.getSelectedFile();
                    txtFrm.setTitle(txtFrm.file.getName());
                    txtFrm.txtWindow.setDocument(doc);
                    txtFrm.wasSaved = true;
                    txtFrm.fileName = txtFrm.file.getName();
                }
            }
            
        });
        
        return file;
    }
    
}
