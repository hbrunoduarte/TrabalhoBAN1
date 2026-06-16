package persistencia;

import dados.Cliente;

import javax.naming.ldap.PagedResultsControl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void inserir(Cliente cliente) {
        String query = "INSERT INTO Cliente (nome, telefone, endereco) VALUES (?, ?, ?)";

        try(Connection conn = Conexao.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getTelefone());
            st.setString(3, cliente.getEndereco());

            st.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar cliente!", e);
        }
    }

    public void remover(int idCliente) {
        String query = "DELETE FROM Cliente WHERE id_cliente = ?";

        try(Connection conn = Conexao.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, idCliente);

            int linhasAlteradas = st.executeUpdate();

            if(linhasAlteradas == 0) {
                throw new RuntimeException("Nenhum cliente com ID " + idCliente + " encontrado!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deltear cliente!", e);
        }
    }

    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String query = "SELECT * FROM Cliente";

        try(Connection conn = Conexao.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));

                lista.add(cliente);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes!", e);
        }

        return lista;
    }
}
