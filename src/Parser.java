
import java.util.ArrayList;
import javax.swing.DefaultListModel;


public class Parser {
    
    private Token actual;
    private ArrayList <simbolo> Tabla = new ArrayList();
    private principal padre; 
    private int tokenr = 0;
    
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
    
    //meodo termino
    
    
    
    //metodos exrtras
    public void mensaje(int j, String mensaje) throws ErrorSintaxis{
        String s = "";
        for (int i = 0; i < j; i++){
            s +=">\t";
        }
        s += mensaje + "\n";
    }
    
    private boolean leerTerminal (String to){
        if(to.equals(padre.ListaToken.get(tokenr).getToken())){
        return true;
        }
        return false;
    }
    
    private void avanzarToken(){
        tokenr++;
    }
    
}
