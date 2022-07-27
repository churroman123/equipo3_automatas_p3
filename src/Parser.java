
import java.util.ArrayList;
import javax.swing.DefaultListModel;


public class Parser {
    
    private Token actual;
    private ArrayList <simbolo> Tabla = new ArrayList();
    
    DefaultListModel<Token> Flujotokens;
    
    public class ErrorSintaxis extends Exception{
        public ErrorSintaxis(String message){
            super(message);
        }
    }
    
    public void analizar() throws ErrorSintaxis{
        
    }
    
    public void programa(int r) throws ErrorSintaxis{
        
    }
    
    private String getNombreSimboloTabla(String str_idx){
        int idx = Integer.valueOf(str_idx);
        simbolo sim = Tabla.get(idx);
        String s = " ";
        if (sim != null)
            s += sim.getLexer();
        else
            s += "null";
        return s;
    }
    
    
    
    //metodos exrtras
    public void mensaje(int j, String mensaje) throws ErrorSintaxis{
        for (int i = 0; i < j; i++){
            
        }
    }
}
