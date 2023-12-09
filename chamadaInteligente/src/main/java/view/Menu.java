/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.List;
import java.util.Scanner;
import model.sistema.Turma;

/**
 *
 * @author Matheus
 */
public class Menu {
    public static Turma escolherTurma(List<Turma> turmas, Scanner scanner) {
        int i = 1;
        System.out.println("--- ESCOLHA UMA TURMA ---");
        for (Turma turma : turmas) {
            System.out.println(i++ + " -> " + turma.getDisciplina() + " / " + turma.getPeriodo());
        }
        System.out.println(" > Escolha: ");
        i = scanner.nextInt();
        return turmas.get(--i);
    }
}
