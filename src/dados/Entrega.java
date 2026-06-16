package dados;

import java.time.LocalDate;

public class Entrega {
    private int idEntrega;
    private LocalDate dtPedido;
    private String status;
    private Cliente cliente;

    public Entrega() {}

    public Entrega(LocalDate data_pedido, String status, Cliente cliente) {
        this.dtPedido = data_pedido;
        this.status = status;
        this.cliente = cliente;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public LocalDate getDtPedido() {
        return dtPedido;
    }

    public void setDtPedido(LocalDate dtPedido) {
        this.dtPedido = dtPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "[ID " + this.idEntrega + "]" + "\nData do pedido: " + this.dtPedido + "\nNome do cliente: " + this.cliente.getNome() +
                "\nStatus: " + this.status;
    }

}
