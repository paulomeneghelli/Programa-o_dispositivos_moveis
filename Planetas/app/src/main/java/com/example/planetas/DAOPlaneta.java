package com.example.planetas;

import java.util.ArrayList;

public class DAOPlaneta {
    ArrayList<Planeta> Planetas;

    public DAOPlaneta(){
        Planetas = new ArrayList<>();
        Planetas.add(new Planeta("Sol", R.drawable.sun));
        Planetas.add(new Planeta("TERRA", R.drawable.earth));
        Planetas.add(new Planeta("VÊNUS", R.drawable.venus));
        Planetas.add(new Planeta("MERCURIO", R.drawable.mercury));
        Planetas.add(new Planeta("Marte", R.drawable.mars));
        Planetas.add(new Planeta("Jupter", R.drawable.jupter));
        Planetas.add(new Planeta("saturno", R.drawable.saturn));
        Planetas.add(new Planeta("Vênus", R.drawable.venus));
        Planetas.add(new Planeta("Urano", R.drawable.uranus));

    }
    public ArrayList<Planeta> getPlanetas() {
        return Planetas;
    }
}
