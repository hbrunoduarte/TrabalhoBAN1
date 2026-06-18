package persistencia;

import dados.Cliente;
import dados.Entrega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {

    public void inserir(Entrega entrega) {
        String query = "INSERT INTO Entrega (data_pedido, status, id_cliente) VALUES (?, ?, ?)";

        try(Connection conn = Conexao.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);

            st.setObject(1, entrega.getDtPedido());
            st.setString(2, entrega.getStatus());
            st.setInt(3, entrega.getCliente().getIdCliente());

            st.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar entrega!", e);
        }
    }

    public void remover(int idEntrega) {
        String query = "DELETE FROM Entrega WHERE id_entrega = ?";

        try(Connection conn = Conexao.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, idEntrega);

            int linhasAlteradas = st.executeUpdate();

            if(linhasAlteradas == 0) {
                throw new RuntimeException("Nenhuma entrega com ID " + idEntrega + " encontrada!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar entrega!", e);
        }
    }

    private List<Entrega> listarEntregas(List<Entrega> lista, String query) {
        try(Connection conn = Conexao.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {

                // Monta o cliente
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));

                // Monta a entrega
                Entrega entrega = new Entrega();
                entrega.setIdEntrega(rs.getInt("id_entrega"));
                java.sql.Date dataBD = rs.getDate("data_pedido");

                if(dataBD != null)
                    entrega.setDtPedido(dataBD.toLocalDate());

                entrega.setStatus(rs.getString("status"));
                entrega.setCliente(cliente);

                lista.add(entrega);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todas as entregas!", e);
        }

        return lista;
    }

    public List<Entrega> listarTodos() {
        List<Entrega> lista = new ArrayList<>();
        String query = "SELECT e.id_entrega, e.data_pedido, e.status, " +
                       "c.id_cliente, c.nome, c.telefone, c.endereco " +
                       "FROM Entrega e INNER JOIN Cliente c " +
                       "ON e.id_cliente = c.id_cliente ";

        return listarEntregas(lista, query);
    }

    public List<Entrega> listarPendentes() {
        List<Entrega> lista = new ArrayList<>();
        String query = "SELECT e.id_entrega, e.data_pedido, e.status " +
                       "c.id_cliente, c.nome, c.telefone, c.endereco " +
                       "FROM Entrega e INNER JOIN Cliente c " +
                       "ON e.id_cliente = c.id_cliente " +
                       "WHERE e.status = 'Pendente'";

        return listarEntregas(lista, query);
    }

}