
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
    
    //meodo factor
    public void factor (int r) throws ErrorSintaxis{
        mensaje(r, "Buscando factor");
        
        if(leerTerminal("$_identificador")){
            mensaje(r, "Se encontro identificador"+ getNombreSimboloTabla(actual.getToken()) );
            avanzarToken();
            mensaje(r,"se encontro factor con identificador");
        }else if(leerTerminal("$_literal_num")){
            mensaje(r, "Se encontro identificador"+ getNombreSimboloTabla(actual.getToken()) );
            avanzarToken();
            mensaje(r,"se encontro factor con literal_numr");
//        }else if(leerTerminal("$_abre_p9ar")){
//             mensaje(r, "se encontro se abre par");
//             avanzarToken();             
        }else{
             mensaje(r, "error");
        }
    }
    
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
