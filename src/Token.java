
public class Token {
    String idx;
    String token;

    public Token(String idx, String token) {
        this.idx = idx;
        this.token = token;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        String tokenMostrar="";
        if(Integer.parseInt(idx)==0){
            tokenMostrar="<"+token+">";
        }else
            tokenMostrar="<"+token+","+idx+">";
        return tokenMostrar;
    }
    
}
