package principal;

import java.util.Random;
import javax.swing.text.MaskFormatter;

public class CPF {

    private static String[] unidadesFederativas = {"Rio Grande do Sul", "Distrito Federal, Goiás, Mato Grosso e Tocantis", "Pará, Amazonas, Acre, Amapá, Rondônia e Roraima",
        "Ceará, Maranhão e Piauí", "Pernambuco, Rio Grande do Norte, Paraíba e Alagoas", "Bahia e Sergipe",
        "Minas Gerais", "Rio de Janeiro e Espírito Santo", "São Paulo", "Paraná e Santa Catarina"};

    static String gerarCPF() {
        String CPF;
        CPF = gerarNovePrimeirosNumeros();
        CPF += calcularDigito(CPF, 10);
        CPF += calcularDigito(CPF, 11);
        CPF = inserirMascara(CPF);

        System.out.println("CPF gerado: " + CPF);
        System.out.print("Unidade Federativa: " + obterEstadoDoCPF(CPF));
        System.out.println(" => Código " + obterCodigoDoEstado(CPF));
        exibirCodigoDeCadaEstado();

        return CPF;
    }

    static String gerarNovePrimeirosNumeros() {
        String cpf = "";

        while (cpf.length() != 9) {
            cpf += gerarNumeroAleatorio();
        }

        return cpf;
    }

    static int gerarNumeroAleatorio() {
        Random random = new Random();
        return random.nextInt(10);
    }

    static int calcularDigito(String cpf, int maximo) {
        int restoDaDivisao = multiplicarNumeros(cpf, maximo) % 11;

        if (restoDaDivisao == 0 || restoDaDivisao == 1) {
            return 0;
        }

        return 11 - restoDaDivisao;
    }

    static int multiplicarNumeros(String cpf, int maximo) {
        int resultadoDaSomaDaMultiplicacao = 0;
        int indiceDoCpf = 0;

        for (int i = maximo; i >= 2; i--) {
            resultadoDaSomaDaMultiplicacao += cpf.charAt(indiceDoCpf) * i;
            indiceDoCpf++;
        }

        return resultadoDaSomaDaMultiplicacao;
    }

    static String inserirMascara(String cpf) {
        int indice = 0;
        String cpfComMascara = "";

        try {
            MaskFormatter mf = new MaskFormatter("###.###.###-##");
            mf.setValueContainsLiteralCharacters(false);
            cpfComMascara = mf.valueToString(cpf);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return cpfComMascara;
    }

    static String obterEstadoDoCPF(String cpf) {
        String unidadeFederativaDoCPF = Character.toString(cpf.replace("-", "").replace(".", "").charAt(8));
        return unidadesFederativas[Integer.parseInt(unidadeFederativaDoCPF)];
    }

    static String obterCodigoDoEstado(String cpf) {
        String codigoDoEstado = Character.toString(cpf.replace("-", "").replace(".", "").charAt(8));
        return codigoDoEstado;
    }

    static void exibirCodigoDeCadaEstado() {
        System.out.println("\nCada estado tem um código para o CPF gerado\n"
                + "O estado em que o CPF foi gerado é representado pelo terceiro código da direita para a esquerda\n"
                + "\nCódigo de cada estado");

        for (int i = 0; i < unidadesFederativas.length; i++) {
            System.out.println(i + ". " + unidadesFederativas[i]);
        }
    }

}
