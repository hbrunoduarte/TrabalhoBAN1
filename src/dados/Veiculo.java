package dados;

public class Veiculo {
    private int idVeiculo;
    private String placa;
    private float capacidade;
    private String modelo;

    public Veiculo(String placa, float capacidade, String modelo) {
        this.placa = placa;
        this.capacidade = capacidade;
        this.modelo = modelo;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public float getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(float capacidade) {
        this.capacidade = capacidade;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Placa: " + this.placa + "\nModelo: " + "\nCapacidade: ";
    }
}
