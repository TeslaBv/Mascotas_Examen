/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CruzF
 */
public class conjuntoMascotas {
    private List<MascotasBean> mascotasList;

    public conjuntoMascotas() {
        this.mascotasList = new ArrayList<>();
    }

    // Método para agregar una mascota a la lista
    public void agregarMascota(MascotasBean mascota) {
        this.mascotasList.add(mascota);
    }

    // Método para obtener todas las mascotas
    public List<MascotasBean> getMascotas() {
        return this.mascotasList;
    }

    // Método para obtener una mascota por índice
    public MascotasBean getMascota(int index) {
        if (index >= 0 && index < mascotasList.size()) {
            return mascotasList.get(index);
        }
        return null;
    }

    // Método para obtener el total de mascotas en la lista
    public int getTotalMascotas() {
        return this.mascotasList.size();
    }
}
