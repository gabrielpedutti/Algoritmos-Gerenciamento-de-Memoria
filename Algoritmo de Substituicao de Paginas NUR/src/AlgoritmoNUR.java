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

    public void visualizarPaginas() {
        for (Pagina pagina : paginas) {
            System.out.println(pagina);
        }
    }

    public void adicionarPagina(String nome) {
        if (paginas.size() > 4) {
            substituirPagina(nome);
        } else {
            // Adicionar uma nova página
            Pagina novaPagina = new Pagina(nome);
            paginas.add(novaPagina);
        }

        // Atualizar o status das páginas existentes para false
        for (Pagina pagina : paginas) {
            if (pagina.nome != nome){
                if (pagina.referenciada || pagina.modificada) {
                    pagina.atualizarStatusModificada(false);
                    pagina.atualizarStatusReferenciada(false);
                }
            }
        }
    }

    public void modificarPagina() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome da página que deseja modificar: ");
        String nomePagina = scanner.nextLine();

        for (Pagina pagina : paginas) {
            if (pagina.nome.equals(nomePagina)) {
                pagina.atualizarStatusModificada(true);
                System.out.println("A página " + pagina.nome + " foi modificada.");
                return;

            }
        }
        System.out.println("Página não encontrada.");
    }

    public void referenciarPagina() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome da página que deseja referenciar: ");
        String nomePagina = scanner.nextLine();

        for (Pagina pagina : paginas) {
            if (pagina.nome.equals(nomePagina)) {
                pagina.atualizarStatusReferenciada(true);
                System.out.println("A página " + pagina.nome + " foi referenciada.");
                return;
            }
        }
        System.out.println("Página não encontrada.");
    }

    public void substituirPagina(String nome) {
        String nomeAntigo;
        for (Pagina pagina : paginas) {
            if (!pagina.referenciada) {
                if (!pagina.modificada){
                    nomeAntigo = pagina.nome;
                    pagina.atualizarStatusModificada(true);
                    pagina.atualizarStatusReferenciada(true);
                    pagina.nome = nome;
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
            System.out.println("Menu:");
            System.out.println("1- Visualizar páginas atuais");
            System.out.println("2- Adicionar nova página");
            System.out.println("3- Modificar uma página");
            System.out.println("4- Referenciar uma página");
            System.out.println("5- Sair");

            System.out.print("Escolha a opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (escolha) {
                case 1:
                    visualizarPaginas();
                    break;
                case 2:
                    System.out.print("\nNome da nova página: ");
                    String nome = scanner.nextLine();
                    adicionarPagina(nome);
                    System.out.println("Página inserida com sucesso!\n");
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
