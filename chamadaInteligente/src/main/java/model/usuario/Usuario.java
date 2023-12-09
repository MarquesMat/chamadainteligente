/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.usuario;

import java.util.ArrayList;
import java.util.List;
import model.sistema.Turma;

public class Usuario {
    private String nome;
    private int matricula;
    // private String senha;
    private String tipo;
    private String localizacao = "prv - ic - sala 207";
    protected List<Turma> turmas;
    private static Usuario instanciaUnica;

    protected Usuario(String nome, int matricula, String tipo) {
        this.nome = nome;
        this.matricula = matricula;
        this.tipo = tipo;
        this.turmas = new ArrayList<>();
    }
    
    public static Usuario obterInstanciaUnica(String nome, int matricula, String tipo) {
        if (instanciaUnica == null) {
            instanciaUnica = new Usuario(nome, matricula, tipo);
        }
        return instanciaUnica;
    }

    public String getNome() {
        return this.nome;
    }
    
    public int getMatricula() {
        return this.matricula;
    }
    
    public String getTipo() {
        return this.tipo;
    }
    
    public String getOcupacao() {
        if ("P".equals(this.getTipo())) return "PROFESSOR(A)";
        return "ALUNO(A)";
    }
    
    public String getLocalizacao() {
        return this.localizacao;
    }
    
    public boolean definirUsuario() {
           return "P".equals(this.getTipo());
    }
}
