
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


class Abrir {
    JTextArea text;

    public Abrir(principal p, JTextArea text) {
        this.text = text;
        padre = p;
    }
    
    principal padre;
    JFileChooser selector=new JFileChooser();
    String titulo=null;
    public void abrir(){
        try {
            FileReader archi;
            BufferedReader lector;
            String cad;
            text.setText("");
            
            selector.showOpenDialog(selector);
            padre.archivo=selector.getSelectedFile();
            
            if(padre.archivo==null){
                JOptionPane.showMessageDialog(null,"No seleccionaste el archivo");
                titulo="null";
            }else{
                titulo=padre.archivo.getName(); 
                archi=new FileReader(padre.archivo);
                    lector=new BufferedReader(archi);
                    cad=lector.readLine();
                while(cad!=null){
                    text.append(cad+"\n");
                    cad=lector.readLine();
                }
                lector.close();
                archi.close();
                padre.b=true;
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Abrir.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Abrir.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
