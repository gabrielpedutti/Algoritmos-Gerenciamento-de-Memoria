import java.util.ArrayList;
import java.util.Scanner;

public class AlgoritmoNUR {
    ArrayList<Pagina> paginas;
    Scanner scanner = new Scanner(System.in);

    public AlgoritmoNUR() {
        this.paginas = new ArrayList<>();
        // Iniciar a execução com 3 páginas criadas
        for (int i = 1; i <= 3; i++) {
            adicionarPagina("Pagina " + i);
        }
    }

    // Visualizar as páginas criadas
    public void visualizarPaginas() {
        for (Pagina pagina : paginas) {
            System.out.println(pagina);
        }
    }

    // Adicionar uma nova página
    public void adicionarPagina(String nome) {
        if (paginas.size() > 4) {
            substituirPagina(nome);
        } else {
            Pagina novaPagina = new Pagina(nome);
            paginas.add(novaPagina);
        }

        // Atualizar o status das páginas existentes para false
        for (Pagina pagina : paginas) {
            if (pagina.getNome() != nome){
                if (pagina.isReferenciada() || pagina.isModificada()) {
                    pagina.atualizarStatusModificada(false);
                    pagina.atualizarStatusReferenciada(false);
                }
            }
        }
    }

    //Modificar uma página
    public void modificarPagina() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome da página que deseja modificar: ");
        String nomePagina = scanner.nextLine();

        for (Pagina pagina : paginas) {
            if (pagina.getNome().equals(nomePagina)) {
                pagina.atualizarStatusModificada(true);
                System.out.println("A página " + pagina.getNome() + " foi modificada.");
                return;

            }
        }
        System.out.println("Página não encontrada.");
    }

    //Referenciar uma página
    public void referenciarPagina() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome da página que deseja referenciar: ");
        String nomePagina = scanner.nextLine();

        for (Pagina pagina : paginas) {
            if (pagina.getNome().equals(nomePagina)) {
                pagina.atualizarStatusReferenciada(true);
                System.out.println("A página " + pagina.getNome() + " foi referenciada.");
                return;
            }
        }
        System.out.println("Página não encontrada.");
    }

    //Substituir uma página
    public void substituirPagina(String nome) {
        String nomeAntigo;
        for (Pagina pagina : paginas) {
            if (!pagina.isReferenciada()) {
                if (!pagina.isModificada()){
                    nomeAntigo = pagina.getNome();
                    pagina.atualizarStatusModificada(true);
                    pagina.atualizarStatusReferenciada(true);
                    pagina.setNome(nome);
                    System.out.println("Página " + nome + " inserida com sucesso!");
                    System.out.println("A página " + nomeAntigo + " foi substituída.");
                    return;
                }
            }
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1- Visualizar páginas atuais");
            System.out.println("2- Adicionar nova página");
            System.out.println("3- Modificar uma página");
            System.out.println("4- Referenciar uma página");
            System.out.println("5- Sair");

            System.out.print("Escolha a opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    visualizarPaginas();
                    break;
                case 2:
                    System.out.print("\nInforme o nome da nova página: ");
                    String nome = scanner.nextLine();
                    adicionarPagina(nome);
                    System.out.println( nome + " inserida com sucesso!\n");
                    break;
                case 3:
                    modificarPagina();
                    break;
                case 4:
                    referenciarPagina();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
            }
        }
    }
}
