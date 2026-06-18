package apresentacao;

import java.util.Scanner;

public class Main {
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
            System.out.println("6. Consultas Avançadas");
            System.out.println("0. Sair do Sistema");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
//                    case 1: submenuMotoristas(scanner); break;
//                    case 2: submenuVeiculos(scanner); break;
//                    case 3: submenuClientes(scanner); break;
//                    case 4: submenuEntregas(scanner); break;
//                    case 5: submenuRotas(scanner); break;
//                    case 6: submenuRelatorios(scanner); break;
//                    case 0: System.out.println("\nEncerrando o sistema"); break;
//                    default: System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro! Digite apenas números");
                continue;
            }

            scanner.close();
        }



    }
}

