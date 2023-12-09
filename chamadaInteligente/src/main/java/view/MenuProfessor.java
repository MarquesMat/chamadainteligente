/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.List;
import java.util.Scanner;
import model.sistema.Turma;
import model.usuario.Professor;

/**
 *
 * @author Matheus
 */
public class MenuProfessor extends Menu {
    public static void menuProfessor(Professor prof, Turma turma, Scanner scanner) {
        System.out.println("-- TURMA ESCOLHIDA: " + turma.getDisciplina() + " - " + turma.getPeriodo());
        System.out.println("--- ESCOLHA UMA OPÇÃO ---");
        System.out.println("1 - Iniciar Chamada");
        int op = scanner.nextInt();
        switch (op) {
            case 1 -> prof.iniciarChamada(turma);
            case 2 -> System.out.println("Opção 2 selecionada");
            case 3 -> System.out.println("Opção 3 selecionada");
            default -> System.out.println("Opção inválida");
        }
    }
}
