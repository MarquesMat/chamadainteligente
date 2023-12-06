// Pacote sistema
package sistema;

import usuario.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunosTurmas {
    private int matriculaAluno;
    private int idTurma;

    public AlunosTurmas(int matriculaAluno, int idTurma) {
        this.matriculaAluno = matriculaAluno;
        this.idTurma = idTurma;
    }

    public int getMatriculaAluno() {
        return this.matriculaAluno;
    }

    public int getIdTurma() {
        return this.idTurma;
    }
}
