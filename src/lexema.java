
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

public class lexema {

    int idx_actual = 0, idx_simbolo = 0;
    public ArrayList<simbolo> TablaSimbolo;
    public ArrayList<String> TablaP;
    int tabla = 0;

    public lexema() {
        TablaSimbolo = new ArrayList<>();
        TablaP = new ArrayList<>();
    }

    public void lexemaAnalizar(String linea, int num_linea,
            DefaultListModel<Token> Tokens, principal padre) throws Exception {
        idx_actual = 0;
        Analisis ana = new Analisis(padre);
        boolean ta = false;
        String lexema1 = "";
        while (idx_actual < linea.length()) {
            for (int i = 0; i < padre.ListaPatrones.getSize(); i++) {
                Patron p = padre.ListaPatrones.get(i);

                Pattern pattern = Pattern.compile("^" + p.getRegexp(), Pattern.CASE_INSENSITIVE);
                String subs = linea.substring(idx_actual);
                Matcher matcher = pattern.matcher(subs);
                boolean matchFound = matcher.find();

                if (matchFound) {
                    String lexema = subs.substring(matcher.start(), matcher.end());
                    lexema1 = lexema;
                    //System.out.println("Coincide! " + matcher.start() + "," + matcher.end());
                    idx_actual += matcher.end();
                    if (p.getNombre().equals("ws")) {
                        System.out.println("espacio");
                    } else if (p.getNombre().equals("comentario")) {
                        System.out.println("es comentario");
                        return;
                    } else if (p.getNombre().equals("$_identificador")) {
                        System.out.println("$_identificador");

                        simbolo sim = new simbolo();
                        sim.lexer = lexema;
                        sim.num_linea = num_linea;
                        sim.idx = idx_simbolo;
                        //sim
                        TablaSimbolo.add(sim);

                        Token t = new Token(String.valueOf(idx_simbolo), p.getNombre());
                        t.setIdx(String.valueOf(idx_simbolo));
                        Tokens.addElement(t);

                    } else if (p.getNombre().equals("$_literal_num")) {
                        System.out.println("$_literal_num");

                        Token t = new Token(lexema, p.getNombre());
                        t.setIdx(lexema);
                        Tokens.addElement(t);
                    } else {
                        Token t = new Token(String.valueOf(0), p.getNombre());
                        t.setIdx(String.valueOf(0));
                        System.out.println(p.getNombre());
                        Tokens.addElement(t);
                    }
                }
                /*for(int b=0;b<padre.ListaPatrones.getSize();b++){
                        p=padre.ListaPatrones.get(b);
                        if(p.getRegexp()!=s){
                            //sim.cadena = s; 
                            //sim.num_linea = num_linea;
                        }
                    }
                    TablaSimbolo.add(sim);
                    //System.out.println(num_linea + ": "+s);*/

//                if (!matchFound) {
//                    System.out.println("no se encontro");
//                    throw new Exception("Error, np coincide ningun patron");
//                }
            }

        }
    }
}
