
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

class Guardarcomo {
JTextArea text; 
String titulo=null;
    public Guardarcomo(JTextArea text) {
        this.text = text;
    }
    JFileChooser file=new JFileChooser();
    File archivo;
    principal p=new principal();
        public void Guardarcomo(){
            file.showSaveDialog(file);
            archivo=file.getSelectedFile();
            if(archivo==null){
                JOptionPane.showMessageDialog(null,"No Guardaste el archivo");
                titulo=null;
            }if(archivo!=null){
                try {
                    FileWriter archi=new FileWriter(archivo);
                    archi.write(text.getText());
                    archi.flush();
                    archi.close();
                    titulo=archivo.getName();
                    p.b=true;
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
            p.pref.put("archivo",archivo.getAbsolutePath() );
        }
}
