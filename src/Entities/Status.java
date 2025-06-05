package Entities;


// LEMBRAR NA HORA DO TRATAMENTO DE INPUT NA VIEW QUE AS OPÇÕES SÃO PADRAO / ACEITO / REJEITADO
public enum Status {
    PADRAO("Em avaliação"),
    ACEITO("Aceita"),
    REJEITADO("rejeitada");

    private final String avaliar;

    public static Status convString(String valor){
        if(valor == null ) return PADRAO;

        try{
            return Status.valueOf(valor.toUpperCase());
        } catch (IllegalArgumentException e ){
            return PADRAO;
        }

    }

    public setStatus(String avaliar) {
        this.avaliar = avaliar;
    }

    public String getAvaliar() {
        return avaliar;
    }

}
