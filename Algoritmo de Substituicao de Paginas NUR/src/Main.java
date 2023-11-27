public class Main{
    public static void main(String[]args){
        System.out.println("\nBem vindo ao Algoritmo de Substituição de Página NUR.\nNosso sistema disponibiliza de 5 molduras para você utilizar, quando essas molduras acabarem, a ultima página modificada será substituída!\nNosso sistema iniciará com 3 páginas criadas.\n");
        AlgoritmoNUR algoritmoNUR=new AlgoritmoNUR();
        algoritmoNUR.menu();
    }
}
