
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

class Guardar {
    JTextArea text;

    public Guardar(JTextArea text) {
        this.text = text;
    }
    
    File archivo;
    
    public void guardar(){
        Guardarcomo guardarC = null;
        if(guardarC.titulo!=null){
            archivo=new File(guardarC.titulo);
            try {
                FileWriter archi=new FileWriter(guardarC.titulo); 
                    archi.write(text.getText());
                    archi.flush();
                    archi.close();
                    
            } catch (IOException ex) {
                Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            guardarC=new Guardarcomo(text);
            guardarC.Guardarcomo();
        }
    }
}
