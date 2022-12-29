package com.professorangoti.condominio;

public class Teste {
    public static void main(String[] args) {
        System.out.println("chamando m1()");
        m1();
        System.out.println("fim do método main");
    }

    private static void m1() {
        System.out.println("chamando m2()");
        m2();
        System.out.println("fim do método m1()");
    }

    private static void m2() {
        System.out.println("início do m2()");
        try {  // colocar o código com potencial de erro dentro do bloco try
            int x = 1 / 0;
        } catch (ArithmeticException e) { //capturar as exceções que podem ocorrer
            System.out.println("Erro: " + e.getMessage());
        }
        System.out.println("fim do método m2()");
    }
}