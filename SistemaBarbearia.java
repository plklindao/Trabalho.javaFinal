import java.util.Scanner;
public class SistemaBarbearia {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Cliente[] clientes = new Cliente[50];
        Barbeiro[] barbeiros = new Barbeiro[50];
        Agendamento[] agendamentos = new Agendamento[50];

        int totalClientes = 0;
        int totalBarbeiros = 0;
        int totalAgendamentos = 0;
        int opcao;

        do {
            System.out.println("==============================");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Barbeiro");
            System.out.println("3 - Novo Agendamento");
            System.out.println("4 - Cancelar Agendamento");
            System.out.println("5 - Finalizar Atendimento");
            System.out.println("6 - Mostrar Agendamento");
            System.out.println("7 - Total de Agendamentos");
            System.out.println("0 - Sair");
            System.out.println("==============================");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    System.out.println("\n--- CADASTRO DE CLIENTE ---");
                    System.out.print("Nome do cliente: ");
                    String nomeCliente = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();
                    clientes[totalClientes] =
                            new Cliente(nomeCliente, telefone);

                    totalClientes++;

                    System.out.println("Cliente cadastrado com sucesso!");

                    break;

                case 2:
                    System.out.println("\n--- CADASTRO DE BARBEIRO ---");
                    System.out.print("Nome do barbeiro: ");
                    String nomeBarbeiro = scanner.nextLine();
                    System.out.print("Especialidade: ");
                    String especialidade = scanner.nextLine();
                    barbeiros[totalBarbeiros] =
                            new Barbeiro(nomeBarbeiro, especialidade);
                    totalBarbeiros++;
                    System.out.println("Barbeiro cadastrado com sucesso!");
                    break;

                case 3:
                    System.out.println("\n--- NOVO AGENDAMENTO ---");
                    if (totalClientes == 0) {
                        System.out.println("Cadastre pelo menos um cliente primeiro.");
                        break;
                    }
                    if (totalBarbeiros == 0) {
                        System.out.println("Cadastre pelo menos um barbeiro primeiro.");
                        break;
                    }
                    System.out.println("\nClientes cadastrados:");
                    for (int i = 0; i < totalClientes; i++) {
                        System.out.println(
                                (i + 1) + " - " +
                                        clientes[i].getNome()
                        );
                    }
                    System.out.print("Escolha o cliente: ");
                    int escolhaCliente = scanner.nextInt();
                    scanner.nextLine();
                    if (escolhaCliente < 1 ||
                            escolhaCliente > totalClientes) {

                        System.out.println("Cliente inválido.");
                        break;
                    }
                    System.out.println("\nBarbeiros cadastrados:");
                    for (int i = 0; i < totalBarbeiros; i++) {
                        System.out.println(
                                (i + 1) + " - " +
                                        barbeiros[i].getNome()
                        );
                    }
                    System.out.print("Escolha o barbeiro: ");
                    int escolhaBarbeiro = scanner.nextInt();
                    scanner.nextLine();
                    if (escolhaBarbeiro < 1 ||
                            escolhaBarbeiro > totalBarbeiros) {

                        System.out.println("Barbeiro inválido.");
                        break;
                    }
                    System.out.print("Digite a data: ");
                    String data = scanner.nextLine();
                    System.out.print("Digite o horário: ");
                    String horario = scanner.nextLine();
                    int codigo = totalAgendamentos + 1;
                    agendamentos[totalAgendamentos] =
                            new Agendamento(
                                    codigo,
                                    data,
                                    horario,
                                    clientes[escolhaCliente - 1],
                                    barbeiros[escolhaBarbeiro - 1]
                            );
                    totalAgendamentos++;
                    System.out.println("Agendamento criado com sucesso!");
                    System.out.println("Código do agendamento: " + codigo);
                    break;

                case 4:
                    System.out.println("\n--- CANCELAR AGENDAMENTO ---");
                    if (totalAgendamentos == 0) {
                        System.out.println("Nenhum agendamento cadastrado.");
                        break;
                    }
                    System.out.print("Digite o código do agendamento: ");
                    int codigoCancelar = scanner.nextInt();
                    scanner.nextLine();
                    boolean encontradoCancelar = false;
                    for (int i = 0; i < totalAgendamentos; i++) {
                        if (agendamentos[i].getCodigo() == codigoCancelar) {
                            encontradoCancelar = true;
                            if (agendamentos[i].getStatus() ==
                                    Agendamento.StatusAgendamento.CONCLUIDO) {
                                System.out.println(
                                        "Não é possível cancelar um atendimento finalizado."
                                );
                            } else if (agendamentos[i].getStatus() ==
                                    Agendamento.StatusAgendamento.CANCELADO) {
                                System.out.println("Este agendamento ja esta cancelado");
                            } else {
                                agendamentos[i].cancelarAgendamento();
                                System.out.println("Agendamento cancelado com sucesso");
                            }
                            break;
                        }
                    }
                    if (!encontradoCancelar) {
                        System.out.println("Agendamento não encontrado.");
                    }
                    break;

                case 5:
                    System.out.println("\n--- FINALIZAR ATENDIMENTO ---");
                    if (totalAgendamentos == 0) {
                        System.out.println("Nenhum agendamento cadastrado.");
                        break;
                    }
                    System.out.print("Digite o código do agendamento: ");
                    int codigoFinalizar = scanner.nextInt();
                    scanner.nextLine();
                    boolean encontradoFinalizar = false;
                    for (int i = 0; i < totalAgendamentos; i++) {
                        if (agendamentos[i].getCodigo() == codigoFinalizar) {
                            encontradoFinalizar = true;
                            if (agendamentos[i].getStatus() ==
                                    Agendamento.StatusAgendamento.CANCELADO) {
                                System.out.println(
                                        "Não é possível finalizar um agendamento cancelado."
                                );
                            } else if (agendamentos[i].getStatus() ==
                                    Agendamento.StatusAgendamento.CONCLUIDO) {
                                System.out.println(
                                        "Esse atendimento já foi finalizado."
                                );
                            } else {
                                agendamentos[i].concluirAgendamento();
                                System.out.println(
                                        "Atendimento finalizado com sucesso!"
                                );
                            }
                            break;
                        }
                    }

                    if (!encontradoFinalizar) {
                        System.out.println("Agendamento não encontrado.");
                    }
                    break;

                case 6:
                    System.out.println("\n--- AGENDAMENTOS ---");
                    if (totalAgendamentos == 0) {
                        System.out.println("Nenhum agendamento cadastrado.");
                        break;
                    }
                    for (int i = 0; i < totalAgendamentos; i++) {

                        System.out.println("\n------------------------------");
                        {
                            System.out.println("Código: " + agendamentos[i].getCodigo());
                            System.out.println("Data: " + agendamentos[i].getData());
                            System.out.println("Horário: " + agendamentos[i].getHorario());
                            System.out.println("Cliente: " + agendamentos[i].getCliente().getNome());
                            System.out.println("Telefone: " + agendamentos[i].getCliente().getTelefone());
                            System.out.println("Barbeiro: " + agendamentos[i].getBarbeiro().getNome());
                            System.out.println("Especialidade: " + agendamentos[i].getBarbeiro().getEspecialidade());
                            System.out.println("Status: " + agendamentos[i].getStatus());
                        }
                    }

                    System.out.println("\n------------------------------");
                    break;

                case 7:
                    System.out.println("\n--- TOTAL DE AGENDAMENTOS ---");
                    System.out.println("Total de agendamentos criados: "+ Agendamento.getTotalAgendamentos());
                    break;

                case 0:
                    System.out.println("\n Sistema encerrado");
                    break;
                default:

                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}