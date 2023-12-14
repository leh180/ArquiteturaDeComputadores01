import java.util.Scanner;

public class ConversorDeBases {

  // Método para converter decimal para binário
  public static String decimalParaBinario(double numero, int casasDecimais) {
    String binario = "";
    int parteInteira = (int) numero;
    double parteDecimal = numero - parteInteira;

    // Concatena a parte inteira convertida para binário
    binario += decimalParaBinarioInteiro(parteInteira);
    binario += '.';
    // Concatena a parte decimal convertida para binário
    binario += decimalParaBinarioDecimal(parteDecimal, casasDecimais);

    return binario;
  }

  // Método para converter a parte inteira
  public static String decimalParaBinarioInteiro(int numero) {
    int resto;
    String binario = "";

    // Converte a parte inteira para binário
    while (numero != 0) {
      resto = numero % 2;
      binario += resto;
      numero = numero / 2;
    }

    // Inverte a string binária e retorna
    return inverter(binario);
  }

  // Método para converter a parte decimal
  public static String decimalParaBinarioDecimal(double numero, int casasDecimais) {
    int contador = 0;
    String binario = "";

    // Converte a parte decimal para binário
    while (contador < casasDecimais) {
      numero = numero * 2;
      if (numero >= 1) {
        binario += '1';
        numero = numero - 1;
      } else {
        binario += '0';
      }
      contador++;
    }

    return binario;
  }

  // Método para inverter uma string
  public static String inverter(String str) {
    StringBuilder invertida = new StringBuilder();

    for (int i = str.length() - 1; i >= 0; i--) {
      invertida.append(str.charAt(i));
    }

    return invertida.toString();
  }

  // Método para converter um número binário para decimal
  public static double binarioParaDecimal(String numeroBinario) {
    int tamanho = numeroBinario.length();
    double decimal = 0;
    int contador = contarDigitosAntesDaVirgula(numeroBinario);

    contador--;

    // Converte os números antes da vírgula para decimal
    for (int i = 0; i < tamanho; i++) {
      if (numeroBinario.charAt(i) != '.') {
        String temp = "";
        temp += numeroBinario.charAt(i);
        int auxiliar = Integer.parseInt(temp);
        decimal += auxiliar * Math.pow(2, (contador));
        contador--;
      }
    }

    return decimal;
  }

  // Método para contar a quantidade de números antes da vírgula
  public static int contarDigitosAntesDaVirgula(String numeroBinario) {
    int resposta = 0;
    int tamanho = numeroBinario.length();

    for (int i = 0; i < tamanho; i++) {
      if (numeroBinario.charAt(i) == '.') {
        i = tamanho;
      } else {
        resposta++;
      }
    }

    return resposta;
  }

  // Método para verificar se um número é binário
  public static boolean ehBinario(String numeroBinario) {
    boolean resposta = true;
    int tamanho = numeroBinario.length();
    int contador = 0;

    for (int i = 0; i < tamanho; i++) {
      if (numeroBinario.charAt(i) == '.') {
        contador++;
      } else if ((numeroBinario.charAt(i) != '1' && numeroBinario.charAt(i) != '0') || contador > 1) {
        resposta = false;
        i = tamanho;
      }
    }

    return resposta;
  }

  public static void main(String[] args) {
    // Cria um scanner para entrada de dados do usuário
    Scanner scanner = new Scanner(System.in);

    //Cabeçalho
    System.out.println(" * * * Conversor de Bases! * * * \n");
    System.out.println("Letícia Azevedo Cota Barbosa");

    // Exibe o menu
    System.out.println("\nDigite 1 para conversão de decimal para binário");
    System.out.println("Digite 2 para conversão de binário para decimal");
    System.out.println("Digite 0 para encerrar");

    // Obtém a opção do usuário
    int opcao = scanner.nextInt();

    // Loop principal
    while (opcao != 0) {
      // Condição para conversão de decimal para binário
      if (opcao == 1) {
        // Solicita o número a ser convertido
        System.out.println("Digite o número a ser convertido (em caso de número fracionário, utilize ponto): ");
        double numero = scanner.nextDouble();
        // Solicita o número de casas decimais desejadas
        System.out.println("Digite o número de casas decimais: ");
        int casasDecimais = scanner.nextInt();

        // Exibe o resultado da conversão
        System.out.println("\nNúmero em binário: " + decimalParaBinario(numero, casasDecimais));
      }
      // Condição para conversão de binário para decimal
      else if (opcao == 2) {
        // Solicita o número binário a ser convertido
        System.out.println("Digite o número binário a ser convertido (em caso de número fracionário, utilize ponto): ");
        String numeroBinario = scanner.next();
        // Verifica se o número binário é válido
        if (ehBinario(numeroBinario)) {
          // Exibe o resultado da conversão
          System.out.println("\nNúmero em decimal: " + binarioParaDecimal(numeroBinario));
        } else {
          // Exibe uma mensagem de erro para números binários inválidos
          System.out.println("O número digitado não é binário ou tem mais de uma vírgula");
        }
      }
      // Condição para opção inválida
      else {
        System.out.println("Opção inválida, tente novamente!");
      }

      // Exibe novamente o menu para o usuário
      System.out.println("\nDigite 1 para conversão de decimal para binário");
      System.out.println("Digite 2 para conversão de binário para decimal");
      System.out.println("Digite 0 para encerrar");
      opcao = scanner.nextInt();
    }

    // Fecha o scanner
    scanner.close();
  }

}
