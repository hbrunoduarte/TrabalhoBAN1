package apresentacao;

import dados.*;
import persistencia.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final MotoristaDAO motoristaDAO = new MotoristaDAO();
    private static final VeiculoDAO veiculoDAO = new VeiculoDAO();
    private static final ClienteDAO clienteDAO = new ClienteDAO();
    private static final EntregaDAO entregaDAO = new EntregaDAO();
    private static final RotaDAO rotaDAO = new RotaDAO();

    private static final DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        System.out.println("==========================================");
        System.out.println("   BEM-VINDO AO SISTEMA DE LOGÍSTICA"      );
        System.out.println("==========================================");

        while(opcao != 0) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Gerenciar Motoristas");
            System.out.println("2. Gerenciar Veículos");
            System.out.println("3. Gerenciar Clientes");
            System.out.println("4. Gerenciar Entregas");
            System.out.println("5. Gerenciar Rotas");
            // entregas pendentes; veiculos de grande porte; motoristas por km
            System.out.println("6. Consultas Avançadas");
            System.out.println("0. Sair do Sistema");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: submenuMotoristas(scanner); break;
                    case 2: submenuVeiculos(scanner); break;
                    case 3: submenuClientes(scanner); break;
                    case 4: submenuEntregas(scanner); break;
                    case 5: submenuRotas(scanner); break;
                    case 6: submenuRelatorios(scanner); break;
                    case 0: System.out.println("\nEncerrando o sistema"); break;
                    default: System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro! Digite apenas números");
                continue;
            }

        }
        scanner.close();
    }

    private static void submenuMotoristas(Scanner scanner) {
        System.out.println("\n--- GERENCIAR MOTORISTAS ---");
        System.out.println("1. Cadastrar Motorista");
        System.out.println("2. Listar Todos");
        System.out.println("3. Remover Motorista");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int op = Integer.parseInt(scanner.nextLine());

        try {
            if (op == 1) {
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("CNH: ");
                String cnh = scanner.nextLine();
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Data de Nascimento (dd/mm/aaaa): ");
                String dataStr = scanner.nextLine();
                LocalDate data = LocalDate.parse(dataStr, formatadorData);

                motoristaDAO.inserir(new Motorista(nome, cnh, cpf, data));
                System.out.println("Motorista cadastrado com sucesso!");
            } else if (op == 2) {
                List<Motorista> lista = motoristaDAO.listarTodos();
                if(lista.isEmpty()) System.out.println("Nenhum motorista cadastrado!");
                for (Motorista m : lista) {
                    System.out.println(m.toString() + "\n");
                }
            } else if (op == 3) {
                System.out.print("ID do motorista a remover: ");
                motoristaDAO.remover(Integer.parseInt(scanner.nextLine()));
                System.out.println("Removido com sucesso!");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Erro: Formato de data inválido! Use dd/MM/yyyy.");
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void submenuVeiculos(Scanner scanner) {
        System.out.println("\n--- GERENCIAR VEÍCULOS ---");
        System.out.println("1. Cadastrar Veículo");
        System.out.println("2. Listar Todos");
        System.out.println("3. Remover Veículo");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int op = Integer.parseInt(scanner.nextLine());

        try {
            if (op == 1) {
                System.out.print("Placa: ");
                String placa = scanner.nextLine();
                System.out.print("Modelo: ");
                String modelo = scanner.nextLine();
                System.out.print("Capacidade (Ton/Kg): ");
                float cap = Float.parseFloat(scanner.nextLine());

                veiculoDAO.inserir(new Veiculo(placa, cap, modelo));
                System.out.println("Veículo cadastrado com sucesso!");
            } else if (op == 2) {
                List<Veiculo> lista = veiculoDAO.listarTodos();
                if(lista.isEmpty()) System.out.println("Nenhum veiculo cadastrado!");
                for (Veiculo v : lista) {
                    System.out.println(v.toString() + "\n");
                }
            } else if (op == 3) {
                System.out.print("ID do veículo a remover: ");
                veiculoDAO.remover(Integer.parseInt(scanner.nextLine()));
                System.out.println("Removido com sucesso!");
            }
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void submenuClientes(Scanner scanner) {
        System.out.println("\n--- GERENCIAR CLIENTES ---");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Listar Todos");
        System.out.println("3. Remover Cliente");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int op = Integer.parseInt(scanner.nextLine());

        try {
            if (op == 1) {
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Telefone: ");
                String tel = scanner.nextLine();
                System.out.print("Endereço: ");
                String end = scanner.nextLine();

                clienteDAO.inserir(new Cliente(nome, tel, end));
                System.out.println("Cliente cadastrado com sucesso!");
            } else if (op == 2) {
                List<Cliente> lista = clienteDAO.listarTodos();
                if(lista.isEmpty()) System.out.println("Nenhum cliente cadastrado!");
                for (Cliente c : lista) {
                    System.out.println(c.toString() + "\n");
                }
            } else if (op == 3) {
                System.out.print("ID do cliente a remover: ");
                clienteDAO.remover(Integer.parseInt(scanner.nextLine()));
                System.out.println("Removido com sucesso!");
            }
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void submenuEntregas(Scanner scanner) {
        System.out.println("\n--- GERENCIAR ENTREGAS ---");
        System.out.println("1. Cadastrar Nova Entrega");
        System.out.println("2. Listar Todas");
        System.out.println("3. Remover Entrega");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int op = Integer.parseInt(scanner.nextLine());

        try {
            if (op == 1) {
                System.out.print("ID do Cliente: ");
                int idCliente = Integer.parseInt(scanner.nextLine());
                Cliente c = new Cliente();
                c.setIdCliente(idCliente);

                Entrega entrega = new Entrega();
                entrega.setDtPedido(LocalDate.now());
                entrega.setStatus("Pendente");
                entrega.setCliente(c);

                entregaDAO.inserir(entrega);
                System.out.println("Entrega registrada como 'Pendente'!");
            } else if (op == 2) {
                List<Entrega> lista = entregaDAO.listarTodos();
                if(lista.isEmpty()) System.out.println("Nenhuma entrega cadastrada!");
                for (Entrega e : lista) {
                    System.out.println(e.toString() + "\n");
                }
            } else if (op == 3) {
                System.out.print("ID da entrega a remover: ");
                entregaDAO.remover(Integer.parseInt(scanner.nextLine()));
                System.out.println("Removida com sucesso!");
            }
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void submenuRotas(Scanner scanner) {
        System.out.println("\n--- GERENCIAR ROTAS ---");
        System.out.println("1. Criar Nova Rota");
        System.out.println("2. Listar Todas");
        System.out.println("3. Remover Rota");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int op = Integer.parseInt(scanner.nextLine());

        try {
            if (op == 1) {
                System.out.print("Distância da viagem (KM): ");
                float dist = Float.parseFloat(scanner.nextLine());

                System.out.print("ID da Entrega: ");
                int idEntrega = Integer.parseInt(scanner.nextLine());
                Entrega ent = new Entrega();
                ent.setIdEntrega(idEntrega);

                System.out.print("ID do Motorista: ");
                int idMot = Integer.parseInt(scanner.nextLine());
                Motorista mot = new Motorista();
                mot.setIdMotorista(idMot);

                System.out.print("ID do Veículo: ");
                int idVeic = Integer.parseInt(scanner.nextLine());
                Veiculo veic = new Veiculo();
                veic.setIdVeiculo(idVeic);

                rotaDAO.inserir(new Rota(dist, ent, mot, veic));
                System.out.println("Rota alocada com sucesso!");
            } else if (op == 2) {
                List<Rota> lista = rotaDAO.listarTodas();
                if(lista.isEmpty()) System.out.println("Nenhuma rota cadastrada!");
                for (Rota r : lista) {
                    System.out.println(r.toString() + "\n");
                }
            } else if (op == 3) {
                System.out.print("ID da rota a remover: ");
                rotaDAO.remover(Integer.parseInt(scanner.nextLine()));
                System.out.println("Removida com sucesso!");
            }
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void submenuRelatorios(Scanner scanner) {
        System.out.println("\n--- CONSULTAS AVANÇADAS ---");
        System.out.println("1. Entregas Pendentes");
        System.out.println("2. Veículos de Grande Porte");
        System.out.println("3. Distância Total por Motorista");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int op = Integer.parseInt(scanner.nextLine());

        try {
            if (op == 1) {
                System.out.println("\n--- LISTA DE ENTREGAS PENDENTES ---");
                List<Entrega> pendentes = entregaDAO.listarPendentes();
                if(pendentes.isEmpty()) {
                    System.out.println("Nenhuma entrega pendente!");
                } else {
                    for(Entrega e : pendentes) {
                        System.out.println(e.toString() + "\n");
                    }
                }
            } else if (op == 2) {
                System.out.println("\n--- VEÍCULOS ACIMA DA CAPACIDADE MÉDIA ---");
                List<Veiculo> vGrandePorte = veiculoDAO.listarVeiculosGrandePorte();
                if(vGrandePorte.isEmpty()) {
                    System.out.println("Nenhum veículo encontrado.");
                } else {
                    for(Veiculo v : vGrandePorte) {
                        System.out.println(v.toString() + "\n");
                    }
                }
            } else if (op == 3) {
                System.out.println("\n--- KM TOTAL PERCORRIDO POR MOTORISTA ---");
                List<String> relatorio = motoristaDAO.listarPorKm();
                if(relatorio.isEmpty()) {
                    System.out.println("Nenhuma rota registrada.");
                } else {
                    for(String linha : relatorio) {
                        System.out.println(linha);
                    }
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }



}

