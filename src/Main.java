import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // 3.1 - Adicionar funcionarios
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, Month.OCTOBER, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, Month.MAY, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, Month.MAY, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, Month.OCTOBER, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, Month.JANUARY, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, Month.NOVEMBER, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, Month.MARCH, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, Month.JULY, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, Month.MAY, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, Month.SEPTEMBER, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 - Remover o funcionário "João" da lista
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários com todas as informações
        funcionarios.forEach(funcionario -> {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimentoFormatted());
            System.out.println("Salário: R$" + funcionario.getSalarioFormatted());
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println();
        });

        // 3.4 - Aumentar os salários em 10%
        funcionarios.forEach(funcionario -> {
            BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.10"));
            funcionario.setSalario(novoSalario);
        });

        // 3.5 - Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir os funcionários agrupados por função
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Funcionários da função: " + funcao);
            lista.forEach(funcionario -> System.out.println("Nome: " + funcionario.getNome()));
            System.out.println();
        });

        // 3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12
        funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10)
                .forEach(funcionario -> System.out.println("Nome: " + funcionario.getNome() + " faz aniversário em outubro."));

        funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 12)
                .forEach(funcionario -> System.out.println("Nome: " + funcionario.getNome() + " faz aniversário em dezembro."));

        // 3.9 - Encontrar o funcionário com a maior idade
        Funcionario funcionarioMaiorIdade = funcionarios.stream()
                .max(Comparator.comparingInt(Funcionario::getIdade))
                .orElse(null);

        if (funcionarioMaiorIdade != null) {
            System.out.println("Funcionário com a maior idade: " + funcionarioMaiorIdade.getNome() +
                    " (Idade: " + funcionarioMaiorIdade.getIdade() + ")");
        }

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("Lista de funcionários por ordem alfabética:");
        funcionarios.forEach(funcionario -> System.out.println("Nome: " + funcionario.getNome()));

        // 3.11 - Imprimir o total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários dos funcionários: R$" + totalSalarios);

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(funcionario -> {
            BigDecimal salarioAtual = funcionario.getSalario();
            int quantosSalariosMinimos = salarioAtual.divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN).intValue();
            System.out.println("Nome: " + funcionario.getNome() + " - Salários Mínimos: " + quantosSalariosMinimos);
        });

    }
}