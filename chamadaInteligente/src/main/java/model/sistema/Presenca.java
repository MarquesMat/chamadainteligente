// Pacote sistema
package model.sistema;

import java.time.LocalDate;
import model.usuario.Aluno;

public class Presenca {
    private LocalDate data;
    private int alunoId;
    private int chamadaId;
    private int presencaId;
    private int presente; // 0 --> falta; 1 --> presença

    // Construtor
    public Presenca(int alunoId, int chamadaId, int presente) {
        this.presencaId = -1; // Alterado na inserção do banco de dados
        this.data = java.time.LocalDate.now();
        this.alunoId = alunoId;
        this.chamadaId = chamadaId;
        this.presente = presente;
    }

    // Getters e Setters
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public int getPresente() {
        return presente;
    }

    public void setPresente(int presente) {
        this.presente = presente;
    }
    
    public int getChamadaId() {
        return chamadaId;
    }
    
    public void setPresencaId(int id) {
        this.presencaId = id;
    }
}
