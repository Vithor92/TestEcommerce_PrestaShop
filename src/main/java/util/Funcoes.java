package util;

public class Funcoes {

    public static Double removeCifraoDevolveValorDouble(String texto){
        texto = texto.replace("$","");
        return Double.parseDouble(texto);
    }

    public static int removeTextoItensDevolveValorInteiro(String texto){
        texto = texto.replace(" items", "");
        return Integer.parseInt(texto);
    }
}
