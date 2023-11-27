import java.text.SimpleDateFormat;
import java.util.Date;

public class Pagina {
    private String nome;
    private String ultimaExecucao;
    private boolean referenciada;
    private boolean modificada;

    public Pagina(String nome) {
        this.nome = nome;
        this.ultimaExecucao = obterHoraAtual();
        this.referenciada = true;
        this.modificada = true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUltimaExecucao() {
        return ultimaExecucao;
    }

    public void setUltimaExecucao(String ultimaExecucao) {
        this.ultimaExecucao = ultimaExecucao;
    }

    public boolean isReferenciada() {
        return referenciada;
    }

    public void setReferenciada(boolean referenciada) {
        this.referenciada = referenciada;
    }

    public boolean isModificada() {
        return modificada;
    }

    public void setModificada(boolean modificada) {
        this.modificada = modificada;
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

    public String toString() {
        return String.format("\nNome: %s\nÚltima execução: %s\nReferenciada: %s\nModificada: %s",
                nome, ultimaExecucao, referenciada, modificada);
    }
}
