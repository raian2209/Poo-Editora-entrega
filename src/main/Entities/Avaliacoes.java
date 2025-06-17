package main.Entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Avaliacoes {

    private long id;
    private Avaliador avaliadorObra;
    private Obra obraAvaliar;
    private LocalDateTime dataAvaliacao;
// LEMBRAR NA HORA DO TRATAMENTO DE INPUT NA VIEW QUE AS OPÇÕES SÃO PADRAO / ACEITO / REJEITADO
    private Status status;


// construtores
// CONSTRUTOR PARA A CRIAÇÃO DO OBJETO
    public Avaliacoes(Avaliador avaliadorObra, Obra obraAvaliar) {
    setAvaliadorObra(avaliadorObra);
    setObraAvaliar(obraAvaliar);
    setDataAvaliacao(null);
    setStatus(Status.convString("padrao"));
}

// LEMBRAR NA HORA DO TRATAMENTO DE INPUT NA VIEW QUE AS OPÇÕES SÃO PADRAO / ACEITO / REJEITADO
// CONSTRUTOR PARA QUANDO NÃO SOUBER A DATA QUE ESTA VINDO NEM O STATUS
    public Avaliacoes(Avaliador avaliadorObra, Obra obraAvaliar, String dataStr, String status) {
        setAvaliadorObra(avaliadorObra);
        setObraAvaliar(obraAvaliar);
        setDataAvaliacao(dataStr);
        setStatus(Status.convString(status));
    }

    public Avaliacoes() {
    }

// sets e gets
    public void setId(long id) {
        this.id = id;
    }

    public void setAvaliadorObra(Avaliador avaladorObra) {
        if(!(avaladorObra == null)) {
            this.avaliadorObra = avaladorObra;
            
        } else {
            System.out.println("Erro Avaliador não pode ser nulo");
        }
    }

    public void setObraAvaliar(Obra obraAvaliar) {
        if(!(obraAvaliar == null)) {
            this.obraAvaliar = obraAvaliar;
        } else {
            System.out.println("Erro Obra não pode ser nulo");
        }
    
    }

    public void setDataAvaliacao(String dataStr) {
        this.dataAvaliacao = comverterData(dataStr);
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public long getId() {
        return id;
    }

    public Avaliador getAvaliadorObra() {
        return avaliadorObra;
    }

    public Obra getObraAvaliar() {
        return obraAvaliar;
    }

    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }

    public String getStatus() {
        return status.getAvaliar();
    }

// metodos
    public LocalDateTime comverterData(String dataStr){
        if (dataStr == null || dataStr.isBlank()) {
            return LocalDateTime.now();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(dataStr, formatter);
    }

    public Avaliacoes cadastrarAvaliacoes(Avaliacoes avaliacoes){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");


        return avaliacoes;
    }

    public Avaliacoes alterarAvaliacoes(Avaliacoes avaliacoes){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");


        return avaliacoes;
    }

    public Avaliacoes exluirAvaliacoes(Avaliacoes avaliacoes){

        System.out.println("Tentando conexão do banco");

        System.out.println("Montando query ");

        System.out.println("preparando statment ");

        System.out.println("Inserindo valores na query");

        System.out.println("EXECUTANDO");


        return avaliacoes;
    }

    public List<Avaliacoes> buscarTodasAvaliacoes(){
       return new ArrayList<>();
    }

    public List<Avaliacoes> buscarPorAvaliador(String string){
        return new ArrayList<>();
    }


    public List<Avaliacoes> buscarPorObra(String string){
        return new ArrayList<>();
    }

    // Fazer por Mes , Ano , Dia 
    public List<Avaliacoes> buscarPorData(String string){
        return new ArrayList<>();
    }

    public List<Avaliacoes> buscarPorStatus(String string){
        return new ArrayList<>();
    }


}
