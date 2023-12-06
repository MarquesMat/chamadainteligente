// Pacote sistema
package sistema;

import java.time.LocalDate;
import usuario.Aluno;

import java.util.ArrayList;
import java.util.List;

public class Chamada {
    private int chamadaId;
    private int turmaId;
    private String localizacao;
    private List<Presenca> presencas;
    private int aberta; // 0 --> fechada; 1 --> aberta
    private LocalDate data;
    

    public Chamada(int chamadaId, int turmaId, String localizacao, LocalDate data, int aberta) {
        this.chamadaId = chamadaId;
        this.turmaId = turmaId;
        this.localizacao = localizacao;
        this.presencas = new ArrayList<>();
        this.aberta = 1;
        this.data = data;
        this.aberta = aberta;
    }
    
    public Chamada(int turmaId, String localizacao) {
        this.chamadaId = -1; // Alterado na inserção do banco de dados
        this.turmaId = turmaId;
        this.localizacao = localizacao;
        this.presencas = new ArrayList<>();
        this.aberta = 1;
        this.data = java.time.LocalDate.now();
    }

    public int getIdTurma() {
        return turmaId;
    }
    
    public String getLocalizacao() {
        return localizacao;
    }
    
    public LocalDate getData() {
        return data;
    }
    
    public int getAberta() {
        return aberta;
    }
    
    public String getEstado() {
        if(this.getAberta() == 1) return "Aberta";
        return "Fechada";
    }
    
    public void setChamadaId(int id) {
        this.chamadaId = id;
    }
    
    public List<Presenca> getPresencas() {
        return presencas;
    }
    
    public void registrandoPresenca(int matricula, String localizacao) {
        if(!localizacao.equals(this.localizacao) || this.aberta == 0) {
            System.out.println("Não foi possível registrar a presença. Verifique sua localização e se a chamada ainda está aberta.");
            return;
        }
        Presenca presenca = new Presenca(matricula,this.chamadaId , 1);
        this.presencas.add(presenca);
        
    }

}
