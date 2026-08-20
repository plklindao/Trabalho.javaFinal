public class Agendamento {
    private int codigo;
    private String data;
    private String horario;
    private Cliente cliente;
    private Barbeiro barbeiro;

    private static int totalAgendamentos = 0;

    public enum StatusAgendamento {
        AGENDADO,
        CONCLUIDO,
        CANCELADO;
    }
    private StatusAgendamento status;
    public Agendamento (int codigo,String data, String horario, Cliente cliente, Barbeiro barbeiro) {
        if (codigo <= 0){
            System.out.println("Código do agendamento inválido.");
            return; 
        }


        this.codigo = codigo;
        this.data = data;
        this.horario = horario;
        this.cliente = cliente;
        this.barbeiro = barbeiro;
        this.status = StatusAgendamento.AGENDADO;
        Agendamento.totalAgendamentos++;
    }

    public int getCodigo() {
        return codigo;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getHorario(){
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Cliente getCliente(){
        return this.cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Barbeiro getBarbeiro(){
        return this.barbeiro;
    }
    public void setBarbeiro(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }


    public static int getTotalAgendamentos() {
        return Agendamento.totalAgendamentos;
    }
    public StatusAgendamento getStatus(){
        return this.status;
    }
    public void concluirAgendamento() {
        this.status = StatusAgendamento.CONCLUIDO;
    }
    public void cancelarAgendamento(){
        this.status = StatusAgendamento.CANCELADO;
    }
}


