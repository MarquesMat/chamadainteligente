// Pacote sistema
package sistema;

import database.AcessarBD;
import java.sql.Connection;
import usuario.Aluno;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private int id;
    private String disciplina;
    private String periodo;
    private List<Aluno> alunos;
    private List<Chamada> chamadas;

    public Turma(int id, String disciplina, String periodo) {
        this.id = id;
        this.disciplina = disciplina;
        this.periodo = periodo;
        this.alunos = new ArrayList<>();
        this.chamadas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    
    public String getDisciplina() {
        return disciplina;
    }

    public String getPeriodo() {
        return periodo;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }
    
    public List<Chamada> getChamadas() {
        return chamadas;
    }
    
    public void setChamadas(List<Chamada> chamadas) {
        this.chamadas = chamadas;
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }
    
    public void iniciandoChamada(String localizacao) {
        Chamada chamada = new Chamada(this.id, localizacao);
        this.chamadas.add(chamada);
        AcessarBD.criarChamada(chamada);
    }
}
