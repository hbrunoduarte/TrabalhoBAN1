package dados;

public class Rota {
    private int idRota;
    private float distanciaKm;
    private Entrega entrega;
    private Motorista motorista;
    private Veiculo veiculo;

    public Rota() {}

    public Rota(float distanciaKm, Entrega entrega, Motorista motorista, Veiculo veiculo) {
        this.distanciaKm = distanciaKm;
        this.entrega = entrega;
        this.motorista = motorista;
        this.veiculo = veiculo;
    }

    public int getIdRota() {
        return idRota;
    }

    public void setIdRota(int idRota) {
        this.idRota = idRota;
    }

    public float getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(float distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public String toString() {
        return "[ID " + this.idRota + "]" + "\nMotorista: " + this.motorista.getNome() + "\nPlaca do Veiculo: " +
                this.veiculo.getPlaca() + "\nCliente: " + this.entrega.getCliente().getNome() +
                "\nStatus: " + this.entrega.getStatus() + "\nDistância (KM): " + this.distanciaKm;
    }

}
