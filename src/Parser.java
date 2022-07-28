
import java.util.ArrayList;
import javax.swing.DefaultListModel;


public class Parser {
    
    private Token actual;
    private ArrayList <simbolo> Tabla = new ArrayList();
    private principal padre; 
    private int tokenr = 0;
    private Analisis ana;
    
    
    DefaultListModel<Token> Flujotokens;
    
    //constructor
    public Parser(principal padre, DefaultListModel<Token> flujo_tokens, ArrayList<simbolo> tabla ){
        this.padre = padre;
        this.Tabla = tabla;
        Flujotokens = flujo_tokens;
    }
    
    public class ErrorSintaxis extends Exception{
        public ErrorSintaxis(String message){
            super(message);
        }
        
    }
    
    public void analizar() throws ErrorSintaxis{
        expresion(0);
    }
    
    public void programa(int r) throws ErrorSintaxis{
        
    }
    // metodo termino
    public void termino(int r) throws ErrorSintaxis {
        mensaje(r, "Buscando Termino");
         if((leerTerminal("$_multiplicacion") || leerTerminal("$_div"))){
            mensaje(r, "Se encontro" + actual.getToken() + " en termino.");
            avanzarToken();
            factor(r + 1);
        
        }
        if(!leerTerminal("$_multiplicacion") || !leerTerminal("$_div")){
         factor(r + 1);
        }else  if((leerTerminal("$_multiplicacion") || leerTerminal("$_div"))){
            mensaje(r, "Se encontro" + actual.getIdx() + " en termino.");
            avanzarToken();
            factor(r + 1);
        
        }
    
       
        mensaje(r, "Se encontro termino");

    }
//metodo expresion
    public void expresion(int r) throws ErrorSintaxis {
        mensaje(r, "Buscando Expresion");

        if (leerTerminal("$_mas") || leerTerminal("$_menos")) {
            mensaje(r, "Se encontro" + actual.getToken() + " en termino.");
            avanzarToken();
        }
        termino(r + 1);
        while (leerTerminal("$_mas") || leerTerminal("$_menos")) {
            mensaje(r, "Se encontro" + actual.getToken() + " en termino.");
            avanzarToken();
            termino(r + 1);
        }
        mensaje(r, "se encontro termino");
    }
//Metodo condicion
    public void condicion(int r) throws ErrorSintaxis {
        mensaje(r, "Buscando condición ");
        if (leerTerminal("$_odd")) {
            mensaje(r, "Se encontro" + actual.getIdx() + " en termino.");
            avanzarToken();
            expresion(r + 1);
        } else {
            expresion(r + 1);
            if (leerTerminal("$_igual") || leerTerminal("$_menorque")
                    || leerTerminal("$_menorigual")
                    || leerTerminal("$_mayorque")
                    || leerTerminal("$_mayorigual")) {
                mensaje(r, "Se encontro" + actual.getIdx() + " en termino.");
                avanzarToken();
                expresion(r + 1);
            } else {
                Error(r, "Error: se esperaba ccmparador");
            }
        }
        mensaje(r, "Se encontro condicion");
    }
// metodo declaracion
    public void declaracion(int r) throws ErrorSintaxis {
        mensaje(r, "Buscando declaracion");
        if (leerTerminal("identificador")) {
            mensaje(r, "Se encontro identificador" + actual.getIdx());
            avanzarToken();

            if (leerTerminal("$_asignar")) {
                avanzarToken();
                expresion(r + 1);
            } else {
                Error(r, "Se esperaba := en declaracion");
            }
        } else if (leerTerminal("$_llamar")) {
            avanzarToken();
            if (leerTerminal("identificador")) {
                mensaje(r, "Se encontro identificador" +  actual.getIdx());
                avanzarToken();
            } else {
                Error(r, "Se esperaba identificador despues de call en declaracion");
            }
        } else if (leerTerminal("s_interrogacion")) {
            mensaje(r, "se encontro ?");
            avanzarToken();
            if (leerTerminal("identificador")) {
                mensaje(r, "Se encontro identificador" +  actual.getIdx());
                avanzarToken();
            } else {
                Error(r, "se esperaba identificador despues de '?'");
            }
        } else if (leerTerminal("$_imprimir")) {
            mensaje(r, "Se encontro !");
            avanzarToken();
            expresion(r + 1);
        } else if (leerTerminal("$_inicio")) {
            mensaje(r, "se encontro beggin");
            avanzarToken();
            declaracion(r + 1);
            while (leerTerminal("$_puntoycoma")) {
                mensaje(r, "Se encontro" + actual.getIdx() + " en termino.");
                avanzarToken();
                declaracion(r + 1);
            }
            mensaje(r, "Se encontro ;");
            if (leerTerminal("$_fin")) {
                avanzarToken();
            } else {
                Error(r, "Error, se esperaba encontrar 'fin'");
            }
        } else if (leerTerminal("$_si")) {
            mensaje(r, "Se encontro if");
            avanzarToken();
            condicion(r + 1);
            if (leerTerminal("$_entonces")) {
                mensaje(r, "Se encontro" + actual.getIdx() + " en termino.");
                avanzarToken();
                declaracion(r + 1);
            } else {
                Error(r, "Error se esperaba encontrar then");
            }
        } else if (leerTerminal("s_mientras")) {
            mensaje(r, "Se encontro while");
            avanzarToken();
            condicion(r + 1);
            if (leerTerminal("$_hacer")) {
                mensaje(r, "Se encontro" + actual.getIdx() + " en termino.");
                avanzarToken();
                declaracion(r + 1);
            } else {
                Error(r, "Error Se esperaba do");
            }
        }
    }
//metodo bloque
    public void bloque(int r) throws ErrorSintaxis {
        if (leerTerminal("$_constante")) {
            mensaje(r, "Se encontro constante");
            avanzarToken();
            if (leerTerminal("identificador")) {
                mensaje(r, "Se encontro identificador" +  actual.getIdx());
                avanzarToken();
            } else {
                Error(r, "Error se esperaba encontrar identificador despues de constante");
            }
            if (leerTerminal("$_igual")) {
                mensaje(r, "se encontro =");
                avanzarToken();
            } else {
                Error(r, "No se encontron =");
            }
            if (leerTerminal("$_literal_num")) {
                mensaje(r, "Se encontro identificador" +  actual.getIdx());
                avanzarToken();
            } else {
                Error(r, "Se esperaba numero ");
            }
            while (leerTerminal("$_coma")) {
                if (leerTerminal("identificador")) {
                    mensaje(r, "Se encontro identificador" + actual.getIdx());
                    avanzarToken();
                } else {
                    Error(r, "Error se esperaba encontrar identificador despues de constante");
                }
                if (leerTerminal("$_igual")) {
                    mensaje(r, "se encontro =");
                    avanzarToken();
                } else {
                    Error(r, "No se encontron =");
                }
                if (leerTerminal("$_literal_num")) {
                    mensaje(r, "Se encontro numero" + actual.getIdx());
                    avanzarToken();
                } else {
                    Error(r, "Se esperaba numero ");
                }
            }
            mensaje(r, "Se encontro ,");
            if (leerTerminal("$_puntocoma")) {
                mensaje(r, "Se encontro ;");
                avanzarToken();
            } else {
                Error(r, "Error no se encontro ;");
            }
        }
        if (leerTerminal("$_defvariable")) {
            mensaje(r, "Se encontro mensaje");
            avanzarToken();
            if (leerTerminal("identificador")) {
                mensaje(r, "Se encontro identificador" +  actual.getIdx());
                avanzarToken();
            } else {
                Error(r, "Error se esperaba encontrar identificador despues de variable");
            }
            while (leerTerminal("$_coma")) {
                if (leerTerminal("identificador")) {
                    mensaje(r, "Se encontro identificador" + actual.getIdx());
                    avanzarToken();
                } else {
                    Error(r, "Error se esperaba encontrar identificador despues de coma");
                }
            }
            mensaje(r, "Se encontro ,");
            if (leerTerminal("$_puntocoma")) {
                mensaje(r, "Se encontro ;");
                avanzarToken();
            } else {
                Error(r, "Error no se encontro ;");
            }
        }
        while (leerTerminal("$_procedimiento")) {
            if (leerTerminal("identificador")) {
                mensaje(r, "Se encontro  " + actual.getIdx());
                avanzarToken();
            } else {
                Error(r, "Error se esperaba encontrar identificador ");
            }
            if (leerTerminal("$_puntocoma")) {
                mensaje(r, "Se encontro ;");
                avanzarToken();
            } else {
                Error(r, "Error no se encontro ;");
            }
            bloque(r+1);
            if (leerTerminal("$_puntocoma")) {
                mensaje(r, "Se encontro ;");
                avanzarToken();
            } else {
                Error(r, "Error no se encontro ;");
            }
        }
        mensaje(r, "Se encontro Procedimiento");
        declaracion(r+1);
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
            mensaje(r, "Se encontro identificador"+ actual.getToken());
            avanzarToken();
            mensaje(r,"se encontro factor con identificador");
        }else if(leerTerminal("$_literal_num")){
            mensaje(r, "Se encontro identificador "+ actual.getIdx());
            avanzarToken();
            mensaje(r,"se encontro factor con literal_numr");
            if(leerTerminal("$_literal_num"))
                factor(r+1);
        }else if(leerTerminal("$_abre_p9ar")){
             mensaje(r, "se encontro se abre par");
             avanzarToken();         
             expresion(r+1);
             
             if(leerTerminal("$_cierra_par")){
                 avanzarToken();
                 mensaje(r, "Se encontro factor con (expresion) ");
             }else
                 Error(r,"Error se esperaba ) para factor");
        }else
           Error(r, "Error se esperaba Factor");
        
    }
    
    //metodos exrtras
    public void mensaje(int j, String mensaje) throws ErrorSintaxis{
        String s = "";
        for (int i = 0; i < j; i++){
            s +=">\t";
        }
        s += mensaje + "\n";
        System.out.println(mensaje);
        padre.SinTextArea.append(s);
    }
    private void getToken() throws ErrorSintaxis{
        if(tokenr >= Flujotokens.size() )
            Error(0," Error: se esperaban más tokens pero ya no hay");
        actual  = Flujotokens.elementAt(tokenr);
        
        if(actual == null)
            Error(0," Error: el token actual fue null");
            
        
    }
    
    public void Error(int j, String mensaje)throws ErrorSintaxis{
        String s = "";
        
        for (int i = 0; i < j ; i++){
            s += "\t";
        }
        s += mensaje;
        s += "cerca de la linea"  ;
        System.err.println(s);
        throw new ErrorSintaxis(s);
    }
    
    private boolean leerTerminal (String to) throws ErrorSintaxis{
        getToken();
        if(to.equals(actual.token)){
        return true;
        }
        return false;
    }
    
    private void avanzarToken(){
        tokenr++;
    }
    
    public void añadirTexto(String linea) throws ErrorSintaxis{
        throw new ErrorSintaxis(linea);

    }
    
}
