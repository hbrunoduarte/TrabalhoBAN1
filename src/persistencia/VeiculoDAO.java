package persistencia;

import dados.Motorista;
import dados.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    public void inserir(Veiculo veiculo) {
        String query = "INSERT INTO Veiculo (placa, capacidade, modelo) VALUES (?, ?, ?)";

        try(Connection conn = Conexao.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, veiculo.getPlaca());
            st.setFloat(2, veiculo.getCapacidade());
            st.setString(3, veiculo.getModelo());

            st.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar veiculo!", e);
        }
    }

    public void remover(int idVeiculo) {
        String query = "DELETE FROM Veiculo WHERE id_veiculo = ?";

        try(Connection conn = Conexao.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, idVeiculo);

            int linhasAlteradas = st.executeUpdate();

            if(linhasAlteradas == 0) {
                throw new RuntimeException("Nenhum veiculo com ID " + idVeiculo + " encontrado!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar veiculo!", e);
        }
    }

    private List<Veiculo> listarVeiculos(List<Veiculo> lista, String query) {
        try(Connection conn = Conexao.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                Veiculo veiculo = new Veiculo();

                veiculo.setIdVeiculo(rs.getInt("id_veiculo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setCapacidade(rs.getFloat("capacidade"));
                veiculo.setModelo(rs.getString("modelo"));

                lista.add(veiculo);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar veiculos!", e);
        }

        return lista;
    }

    public List<Veiculo> listarTodos() {
        List<Veiculo> lista = new ArrayList<>();
        String query = "SELECT * FROM Veiculo";

        return listarVeiculos(lista, query);
    }

    public List<Veiculo> listarVeiculosGrandePorte() {
        List<Veiculo> lista = new ArrayList<>();
        String query = "SELECT * FROM Veiculo WHERE capacidade > (SELECT AVG(capacidade) FROM Veiculo)";

        return listarVeiculos(lista, query);
    }

}
