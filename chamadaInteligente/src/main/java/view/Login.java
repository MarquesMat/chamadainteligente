/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;
import model.usuario.Usuario;

/**
 *
 * @author Matheus
 */
public class Login {
    public static String inserirCredenciais(String cred) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira a " + cred+ ": ");
        String credencial = scanner.nextLine();
        return credencial;
    }
    
    public static void exibirInfo(Usuario usuario) {
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Matrícula: " + usuario.getMatricula());
        System.out.println("Ocupação: " + usuario.getOcupacao());
    }
    
    public static void falhaLogin() {
        System.out.println("Falha na autenticação. Usuário não reconhecido.");
    }
}
