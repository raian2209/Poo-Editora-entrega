package main.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Avaliacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "avaliador_id")
    private Avaliador avaliadorObra;

    @ManyToOne
    @JoinColumn(name="Obra_id")
    private Obra obraAvaliar;

    @Column
    private LocalDateTime dataAvaliacao;
// LEMBRAR NA HORA DO TRATAMENTO DE INPUT NA VIEW QUE AS OPÇÕES SÃO PADRAO / ACEITO / REJEITADO
    @Enumerated(EnumType.STRING)
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
        return LocalDateTime.parse(dataStr);
    }

    @Override
    public String toString() {
        return "Avaliacoes{" +
                "id=" + id +
                ", avaliadorObra=" + avaliadorObra +
                ", obraAvaliar=" + obraAvaliar +
                ", dataAvaliacao=" + dataAvaliacao +
                ", status=" + status +
                '}';
    }
}
