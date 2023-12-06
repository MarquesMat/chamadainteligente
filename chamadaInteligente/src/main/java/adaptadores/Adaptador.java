/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;

import java.sql.Date;
import java.time.LocalDate;
import sistema.Chamada;
import sistema.Presenca;

/**
 *
 * @author Matheus
 */
public class Adaptador {
    public static Date getDataChamada(Chamada chamada) {
        Date dataSql = Date.valueOf(chamada.getData());
        return dataSql;
    }
    public static Date getDataPresenca(Presenca presenca) {
        Date dataSql = Date.valueOf(presenca.getData());
        return dataSql;
    }
    public static LocalDate dateToLocalDate(Date data) {
        return data.toLocalDate();
    }
}
