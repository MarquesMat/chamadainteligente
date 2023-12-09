// Subpacote usuario (aluno)
package model.usuario;

import model.sistema.Chamada;

public class Aluno extends Usuario {
    public Aluno(String nome, int matricula, String tipo) {
        super(nome, matricula, tipo);
    }
    /*
    public String getLocalizacao () {
        // Este método necessita integração com o GPS
        String localizacao = "prv - ic - sala 207"; // Descobrir localização atual
        return localizacao;
    }
    */
    public void registrarPresenca(Chamada chamada) {
        chamada.registrandoPresenca(this.getMatricula(), this.getLocalizacao());
    }
}
