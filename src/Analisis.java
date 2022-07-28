
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Analisis {

    public ArrayList<simbolo> TablaSimbolo;
    Parser psr;
    principal padre;
    public  int cont_linea = 0;
    public Analisis(principal p) {
        TablaSimbolo = new ArrayList<>();
        padre = p;
    
    }

    public void Analizar(File archivo) {
        //Limpia la tabla de simbolos
        TablaSimbolo.clear();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
           
            while ((linea = br.readLine()) != null) {
                analizarLinea(linea, cont_linea);

                cont_linea++;
            }
        } 
        catch (IOException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            
        
            padre.jTextArea2.append(ex.getMessage() + "\n");
        
        }
        
    }
    public lexema lx;
    int tabla = 0;

    private void analizarLinea(String linea, int num_linea) throws Exception{
        lx = new lexema();
            lx.lexemaAnalizar(linea, num_linea, padre.getListaToken(), padre);
            
        
       
        for (int a = 0; a < lx.TablaSimbolo.size(); a++) {
            guardar(lx.TablaSimbolo.get(a));           
        }
        
        
    }
    
    private int guardar(simbolo s){
        for(int i = 0; i < TablaSimbolo.size();i++){
            if(TablaSimbolo.get(i).getLexer().equals(s.getLexer())){
                return i;
            }
        }
        TablaSimbolo.add(s);
        tabla++;
        return TablaSimbolo.size()-1;
    }

    
    
  
}
