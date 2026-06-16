package persistencia;

import dados.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RotaDAO {

    public void inserir(Rota rota) {
        String query = "INSERT INTO Rota (id_entrega, id_motorista, id_veiculo, distancia_km) " +
                       "VALUES (?, ?, ?, ?)";

        try(Connection conn = Conexao.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);

            st.setObject(1, rota.getEntrega());
            st.setObject(2, rota.getMotorista());
            st.setObject(3, rota.getVeiculo());
            st.setFloat(4, rota.getDistanciaKm());

            st.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar rota!", e);
        }
    }

    public void remover(int idRota) {
        String query = "DELETE FROM Rota WHERE id_rota = ?";

        try(Connection conn = Conexao.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, idRota);

            int linhasAlteradas = st.executeUpdate();

            if(linhasAlteradas == 0) {
                throw new RuntimeException("Nenhuma rota com ID " + idRota + " ncontrada!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar rota!", e);
        }
    }

    public List<Rota> listarTodas() {
        List<Rota> lista = new ArrayList<>();
        String query = "SELECT " +
                       "r.id_rota, r.distancia_km, " +
                       "m.id_motorista, m.nome AS nome_motorista, m.cnh, m.cpf, m.data_nascimento, " +
                       "v.id_veiculo, v.placa, v.capacidade, v.modelo, " +
                       "e.id_entrega, e.data_pedido, e.status, " +
                       "c.id_cliente, c.nome AS nome_cliente, c.telefone, c.endereco " +
                       "FROM " +
                       "Rota r " +
                       "INNER JOIN " +
                       "Motorista m ON r.id_motorista = m.id_motorista " +
                       "INNER JOIN " +
                       "Veiculo v ON r.id_veiculo = v.id_veiculo " +
                       "INNER JOIN " +
                       "Entrega e ON r.id_entrega = e.id_entrega " +
                       "INNER JOIN " +
                       "Cliente c ON e.id_cliente = c.id_cliente";

        try(Connection conn = Conexao.getConnection()) {
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {

                // Motorista
                Motorista motorista = new Motorista();
                motorista.setIdMotorista(rs.getInt("m.id_motorista"));
                motorista.setNome(rs.getString("nome_motorista"));
                motorista.setCnh(rs.getString("m.cnh"));
                motorista.setCpf(rs.getString("m.cpf"));
                java.sql.Date mDataBD = rs.getDate("m.data_nascimento");
                if(mDataBD != null)
                    motorista.setDtNascimento(mDataBD.toLocalDate());

                // Veiculo
                Veiculo veiculo = new Veiculo();
                veiculo.setIdVeiculo(rs.getInt("v.id_veiculo"));
                veiculo.setPlaca(rs.getString("v.placa"));
                veiculo.setCapacidade(rs.getFloat("v.capacidade"));
                veiculo.setModelo(rs.getString("v.modelo"));

                // Cliente
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("c.id_cliente"));
                cliente.setNome(rs.getString("nome_cliente"));
                cliente.setTelefone(rs.getString("c.telefone"));
                cliente.setEndereco(rs.getString("c.endereco"));

                // Entrega
                Entrega entrega = new Entrega();
                entrega.setIdEntrega(rs.getInt("e.id_entrega"));
                java.sql.Date eDataBD = rs.getDate("e.data_pedido");
                if(eDataBD != null)
                    motorista.setDtNascimento(eDataBD.toLocalDate());
                entrega.setStatus(rs.getString("e.status"));
                entrega.setCliente(cliente);

                // Rota
                Rota rota = new Rota();
                rota.setIdRota(rs.getInt("r.id_rota"));
                rota.setDistanciaKm(rs.getFloat("r.distancia_km"));
                rota.setMotorista(motorista);
                rota.setVeiculo(veiculo);
                rota.setEntrega(entrega);

                lista.add(rota);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todas as rotas!", e);
        }

        return lista;
    }

}
