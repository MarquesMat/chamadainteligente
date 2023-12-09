// Pacote database
package model.database;

import model.adaptadores.Adaptador;
import model.sistema.Turma;
import model.usuario.Usuario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.sistema.Chamada;
import model.sistema.Presenca;

public class AcessarBD {

    public static List<Turma> obterTurmasDoBanco(Connection conexao) {
        List<Turma> turmas = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Consultar o banco de dados para obter todas as turmas
            String consulta = "SELECT * FROM turmas";
            statement = conexao.prepareStatement(consulta);
            resultSet = statement.executeQuery();

            // Iterar sobre o ResultSet e criar instâncias de Turma
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String disciplina = resultSet.getString("disciplina");
                String periodo = resultSet.getString("periodo");

                // Crie uma instância de Turma com os detalhes recuperados do banco de dados
                Turma turma = new Turma(id, disciplina, periodo);

                // Adicione a instância à lista de turmas
                turmas.add(turma);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return turmas;
    }

    public static List<Chamada> obterChamadas(Connection conexao, int turmaId) {
        List<Chamada> chamadas = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Consultar o banco de dados para obter todas as turmas do professor
            String consulta = "SELECT * FROM chamadas WHERE id_turma = ?";
            statement = conexao.prepareStatement(consulta);
            statement.setInt(1, turmaId);
            resultSet = statement.executeQuery();

            // Iterar sobre o ResultSet e criar instâncias de Turma
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                LocalDate horario = Adaptador.dateToLocalDate(resultSet.getDate("horario"));
                String localizacao = resultSet.getString("localizacao");
                int aberta = resultSet.getInt("aberta");
                int id_turma = resultSet.getInt("id_turma");

                // Crie uma instância de Chamada com os detalhes recuperados do banco de dados
                Chamada chamada = new Chamada(id, id_turma, localizacao, horario, aberta);

                // Adicione a instância à lista de turmas
                chamadas.add(chamada);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return chamadas;
    }
    
    public static List<Turma> obterTurmasProfessor(int matProf) {
        List<Turma> turmas = new ArrayList<>();

        Connection conexao = BancoDeDados.obterConexao();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Consultar o banco de dados para obter todas as turmas do professor
            String consulta = "SELECT * FROM turmas WHERE mat_professor = ?";
            statement = conexao.prepareStatement(consulta);
            statement.setInt(1, matProf);
            resultSet = statement.executeQuery();

            // Iterar sobre o ResultSet e criar instâncias de Turma
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String disciplina = resultSet.getString("disciplina");
                String periodo = resultSet.getString("periodo");

                // Crie uma instância de Turma com os detalhes recuperados do banco de dados
                Turma turma = new Turma(id, disciplina, periodo);
                turma.setChamadas(obterChamadas(conexao, id));
                // Adicione a instância à lista de turmas
                turmas.add(turma);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        BancoDeDados.fecharConexao(conexao);
        return turmas;
    }
    
    public static List<Turma> obterTurmasAluno(int matAluno) {
        List<Turma> turmas = new ArrayList<>();
        
        Connection conexao = BancoDeDados.obterConexao();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Consultar o banco de dados para obter todas as turmas do aluno
            String consulta = """
                              SELECT t.* FROM turmas t
                              JOIN alunos_turmas at ON t.id = at.id_turma
                              JOIN usuarios u ON at.mat_aluno = u.matricula
                              WHERE u.matricula = ?;""";
            statement = conexao.prepareStatement(consulta);
            statement.setInt(1, matAluno);
            resultSet = statement.executeQuery();

            // Iterar sobre o ResultSet e criar instâncias de chamada
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String disciplina = resultSet.getString("disciplina");
                String periodo = resultSet.getString("periodo");

                // Crie uma instância de Turma com os detalhes recuperados do banco de dados
                Turma turma = new Turma(id, disciplina, periodo);
                turma.setChamadas(obterChamadas(conexao, id));

                // Adicione a instância à lista de turmas
                turmas.add(turma);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        BancoDeDados.fecharConexao(conexao);
        return turmas;
    }

    public static Usuario autenticarUsuario(String matricula, String senha) {
        Connection conexao = BancoDeDados.obterConexao();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Consultar o banco de dados para verificar se o usuário existe
            String consulta = "SELECT * FROM usuarios WHERE matricula = ? AND senha = ?";
            statement = conexao.prepareStatement(consulta);
            statement.setString(1, matricula);
            statement.setString(2, senha);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // O usuário foi encontrado, criar e retornar uma instância de Usuario
                return Usuario.obterInstanciaUnica(resultSet.getString("nome"), resultSet.getInt("matricula"), resultSet.getString("tipo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        BancoDeDados.fecharConexao(conexao);
        
        return null; // Usuário não reconhecido
    }

    public static void criarChamada(Chamada chamada) {
        Connection conexao = BancoDeDados.obterConexao();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Inserir uma nova chamada no banco de dados associada à turma e à localização
            String insercao = "INSERT INTO chamadas (horario, localizacao, aberta, id_turma) VALUES (?, ?, ?, ?)";
            statement = conexao.prepareStatement(insercao, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, Adaptador.getDataChamada(chamada));
            statement.setString(2, chamada.getLocalizacao());
            statement.setInt(3, chamada.getAberta());
            statement.setInt(4, chamada.getIdTurma());
            // Executar a inserção
            int linhasAfetadas = statement.executeUpdate();

            // Verificar se a inserção foi bem-sucedida
            if (linhasAfetadas > 0) {
                // Obter o ID gerado para a chamada
                resultSet = statement.getGeneratedKeys();
                
                if (resultSet.next()) {
                    int id_chamada = resultSet.getInt(1);
                    chamada.setChamadaId(id_chamada);
                    System.out.println("Nova chamada criada com sucesso!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        BancoDeDados.fecharConexao(conexao);

    }

    public static void criarPresenca(Presenca presenca) {
        Connection conexao = BancoDeDados.obterConexao();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Inserir uma nova chamada no banco de dados associada à turma e à localização
            String insercao = "INSERT INTO presencas (horario, mat_aluno, id_chamada, estatus) VALUES (?, ?, ?, ?)";
            statement = conexao.prepareStatement(insercao, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, Adaptador.getDataPresenca(presenca));
            statement.setInt(2, presenca.getAlunoId());
            statement.setInt(3, presenca.getChamadaId());
            statement.setInt(4, presenca.getPresente());
            // Executar a inserção
            int linhasAfetadas = statement.executeUpdate();

            // Verificar se a inserção foi bem-sucedida
            if (linhasAfetadas > 0) {
                // Obter o ID gerado para a chamada
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id_presenca = resultSet.getInt(1);
                    presenca.setPresencaId(id_presenca);
                    System.out.println("Nova presença criada com sucesso!");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        BancoDeDados.fecharConexao(conexao);

    }
    
}
