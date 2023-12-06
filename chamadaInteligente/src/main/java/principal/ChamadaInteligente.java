// Pacote main
package principal;

import usuario.Usuario;
import sistema.Turma;
import sistema.Chamada;
import sistema.Presenca;
import database.BancoDeDados;
import database.AcessarBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import usuario.Aluno;
import usuario.Professor;
import view.MenuAluno;
import view.MenuProfessor;

public class ChamadaInteligente {
       
    public static void main(String[] args) {

        // Obter uma conexão com o banco de dados
        Connection conexao = BancoDeDados.obterConexao();

        Scanner scanner = new Scanner(System.in);
        int op = 0;

        // Simulação de autenticação
        System.out.println("Insira a matrícula: ");
        String matricula = scanner.nextLine();
        System.out.println("Insira a senha: ");
        String senha = scanner.nextLine();

        // Autenticar o usuário no banco de dados
        Usuario usuarioAutenticado = AcessarBD.autenticarUsuario(conexao, matricula, senha);

        if (usuarioAutenticado != null) {
            System.out.println("Nome: " + usuarioAutenticado.getNome());
            System.out.println("Matrícula: " + usuarioAutenticado.getMatricula());
            System.out.println("Ocupação: " + usuarioAutenticado.getOcupacao());
            if (usuarioAutenticado.definirUsuario()) {
                Professor professor = new Professor(usuarioAutenticado.getNome(), usuarioAutenticado.getMatricula(), usuarioAutenticado.getTipo());
                List<Turma> turmasProfessor = AcessarBD.obterTurmasProfessor(conexao, professor.getMatricula());
                Turma turma = MenuProfessor.escolherTurma(turmasProfessor, scanner);
                MenuProfessor.menuProfessor(professor, turma, scanner);
            } else {
                Aluno aluno = new Aluno(usuarioAutenticado.getNome(), usuarioAutenticado.getMatricula(), usuarioAutenticado.getTipo());
                List<Turma> turmasAluno = AcessarBD.obterTurmasAluno(aluno.getMatricula());
                Turma turma = MenuAluno.escolherTurma(turmasAluno, scanner);
                Chamada chamada = MenuAluno.escolherChamada(turma, scanner);
                MenuAluno.menuAluno(aluno, chamada, scanner);
            }

        } else {
            System.out.println("Falha na autenticação. Usuário não reconhecido.");
        }

        BancoDeDados.fecharConexao(conexao);
    }

}
/*
"""
                              SELECT * FROM chamadas WHERE mat_professor = ?SELECT c.* 
                              FROM chamadas c JOIN turmas t ON c.id_turma = t.id JOIN alunos_turmas at ON t.id = at.id_turma JOIN usuarios u ON at.mat_aluno = u.matricula 
                              WHERE t.id = ? AND u.matricula = ?;"""
*/