package persistencia;

import dados.Motorista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MotoristaDAO {

    public void inserir(Motorista motorista) {
        String query = "INSERT INTO Motorista (nome, cnh, cpf, data_nascimento) VALUES (?, ?, ?, ?)";

        try(Connection conn = Conexao.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, motorista.getNome());
            st.setString(2, motorista.getCnh());
            st.setString(3, motorista.getCpf());
            st.setObject(4, motorista.getDtNascimento());

            st.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar motorista no banco!", e);
        }
    }

    public void remover(int idMotorista) {
        String query = "DELETE FROM Motorista WHERE id_motorista = ?";

        try(Connection conn = Conexao.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, idMotorista);

            int linhasAlteradas = st.executeUpdate();

            if(linhasAlteradas == 0) {
                throw new RuntimeException("Nenhum motorista com ID " + idMotorista + " encontrado!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar motorista do banco!", e);
        }
    }

    public List<Motorista> listarTodos() {
        List<Motorista> lista = new ArrayList<>();
        String query = "SELECT * FROM Motorista";

        try(Connection conn = Conexao.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                Motorista motorista = new Motorista();

                motorista.setIdMotorista(rs.getInt("id_motorista"));
                motorista.setNome(rs.getString("nome"));
                motorista.setCnh(rs.getString("cnh"));
                motorista.setCpf(rs.getString("cpf"));
                java.sql.Date dataBD = rs.getDate("data_nascimento");

                if(dataBD != null)
                    motorista.setDtNascimento(dataBD.toLocalDate());

                lista.add(motorista);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar motoristas!", e);
        }

        return lista;
    }
}
