import java.text.SimpleDateFormat;
import java.util.Date;

public class Pagina {
    String nome;
    String ultimaExecucao;
    boolean referenciada;
    boolean modificada;
    boolean substituida;

    public Pagina(String nome) {
        this.nome = nome;
        this.ultimaExecucao = obterHoraAtual();
        this.referenciada = true;
        this.modificada = true;
        this.substituida = false;
    }

    public static String obterHoraAtual() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    public void atualizarStatusReferenciada(boolean referenciada) {
        this.referenciada = referenciada;
        this.ultimaExecucao = obterHoraAtual();
    }

    public void atualizarStatusModificada(boolean modificada) {
        this.modificada = modificada;
        this.ultimaExecucao = obterHoraAtual();
    }

    public void atualizarStatusSubstituida(boolean substituida) {
        this.substituida = substituida;
        this.ultimaExecucao = obterHoraAtual();
    }

    public String toString() {
        return String.format("\nNome: %s\nÚltima execução: %s\nReferenciada: %s\nModificada: %s\nSubstituída: %s\n",
                nome, ultimaExecucao, referenciada, modificada, substituida);
    }
}