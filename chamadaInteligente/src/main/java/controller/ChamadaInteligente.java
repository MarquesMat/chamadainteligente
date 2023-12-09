// Pacote main
package controller;

import model.usuario.Usuario;
import model.sistema.Turma;
import model.sistema.Chamada;
import model.database.AcessarBD;

import java.util.List;
import java.util.Scanner;
import model.usuario.Aluno;
import model.usuario.Professor;
import view.Login;
import view.MenuAluno;
import view.MenuProfessor;

public class ChamadaInteligente {
       
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Simulação de autenticação
        String matricula = Login.inserirCredenciais("matrícula");
        String senha = Login.inserirCredenciais("senha");

        // Autenticar o usuário no banco de dados
        Usuario usuarioAutenticado = AcessarBD.autenticarUsuario(matricula, senha);

        if (usuarioAutenticado != null) {
            Login.exibirInfo(usuarioAutenticado);
            if (usuarioAutenticado.definirUsuario()) {
                Professor professor = new Professor(usuarioAutenticado.getNome(), usuarioAutenticado.getMatricula(), usuarioAutenticado.getTipo());
                List<Turma> turmasProfessor = AcessarBD.obterTurmasProfessor(professor.getMatricula());
                Turma turma = MenuProfessor.escolherTurma(turmasProfessor, scanner);
                MenuProfessor.menuProfessor(professor, turma, scanner);
            } else {
                Aluno aluno = new Aluno(usuarioAutenticado.getNome(), usuarioAutenticado.getMatricula(), usuarioAutenticado.getTipo());
                List<Turma> turmasAluno = AcessarBD.obterTurmasAluno(aluno.getMatricula());
                Turma turma = MenuAluno.escolherTurma(turmasAluno, scanner);
                Chamada chamada = MenuAluno.escolherChamada(turma, scanner);
                MenuAluno.menuAluno(aluno, chamada, scanner);
            }

        } else Login.falhaLogin();
    }

}
