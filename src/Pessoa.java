import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {

    private String nome;
    private LocalDate dataNascimento;

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimentoFormatted() {
        return dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public int getIdade() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() - dataNascimento.getYear();
    }
}
