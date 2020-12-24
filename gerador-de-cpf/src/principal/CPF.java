package principal;

import java.util.Random;

public class CPF {

    static String gerarCPF() {
        String CPF;
        String unidadeFederativa;
        CPF = gerarNovePrimeirosNumeros();
        CPF += calcularDigito(CPF, 10);
        CPF += calcularDigito(CPF, 11);
        unidadeFederativa = obterEstadoDoCPF(CPF);
        CPF = inserirMascara(CPF);
        
        System.out.println("CPF gerado: " + CPF);
        System.out.println("Unidade Federativa: " + unidadeFederativa);
        
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

        while (cpfComMascara.replace(".", "").length() != 9) {
            cpfComMascara += cpf.charAt(indice);

            if (cpfComMascara.replace(".", "").length() % 3 == 0) {
                if (cpfComMascara.replace(".", "").length() != 9) {
                    cpfComMascara += ".";
                }
            }

            indice++;
        }

        cpfComMascara += "-";
        cpfComMascara += cpf.charAt(indice);
        cpfComMascara += cpf.charAt(indice + 1);

        return cpfComMascara;
    }

    static String obterEstadoDoCPF(String cpf) {
        String[] unidadeFederativa = {"Rio Grande do Sul", "Distrito Federal, Goiás, Mato Grosso e Tocantis", "Pará, Amazonas, Acre, Amapá, Rondônia e Roraima",
            "Ceará, Maranhão e Piauí", "Pernambuco, Rio Grande do Norte, Paraíba e Alagoas", "Bahia e Sergipe",
            "Minas Gerais", "Rio de Janeiro e Espírito Santo", "São Paulo", "Paraná e Santa Catarina"};

        String unidadeFederativaDoCPF = Character.toString(cpf.charAt(8));
        return unidadeFederativa[Integer.parseInt(unidadeFederativaDoCPF)];
    }

}
