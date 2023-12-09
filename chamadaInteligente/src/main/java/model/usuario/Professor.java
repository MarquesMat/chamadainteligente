// Subpacote usuario (professor)
package model.usuario;

import static model.database.AcessarBD.obterTurmasProfessor;
import java.sql.Connection;
import java.util.List;
import model.sistema.Turma;

public class Professor extends Usuario {
    public Professor(String nome, int matricula, String tipo) {
        super(nome, matricula, tipo);
    }
    
    public void iniciarChamada(Turma turma) {
        turma.iniciandoChamada(this.getLocalizacao());
    }
    /*
    public List<Turma> obterTurmas() {
        
    }
    */
}
