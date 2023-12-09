/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.List;
import java.util.Scanner;
import model.sistema.Chamada;
import model.sistema.Turma;
import model.usuario.Aluno;

/**
 *
 * @author Matheus
 */
public class MenuAluno extends Menu {
    public static Chamada escolherChamada(Turma turma, Scanner scanner) {
        List<Chamada> chamadas = turma.getChamadas();
        int i = 1;
        System.out.println("-- TURMA ESCOLHIDA: " + turma.getDisciplina() + " - " + turma.getPeriodo());
        System.out.println("--- ESCOLHA UMA CHAMADA ---");
        for (Chamada chamada : chamadas) {
            System.out.println(i++ + " -> " + chamada.getData() + " / " + chamada.getLocalizacao() + " / " + chamada.getEstado());
        }
        System.out.println(" > Escolha: ");
        i = scanner.nextInt();
        return chamadas.get(--i);
    }
    
    public static void menuAluno(Aluno alu, Chamada chamada, Scanner scanner) {
        System.out.println("-- CHAMADA ESCOLHIDA: " + chamada.getData() + " / " + chamada.getLocalizacao() + " / " + chamada.getEstado());
        System.out.println("--- ESCOLHA UMA OPÇÃO ---");
        System.out.println("1 - Registrar Presença");
        int op = scanner.nextInt();
        switch (op) {
            case 1 -> alu.registrarPresenca(chamada);
            case 2 -> System.out.println("Opção 2 selecionada");
            case 3 -> System.out.println("Opção 3 selecionada");
            default -> System.out.println("Opção inválida");
        }
    }
}
