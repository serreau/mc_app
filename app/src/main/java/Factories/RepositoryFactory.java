package Factories;

import android.content.Context;

import repositories.Repository;
import repositories.impl.NanoRepository_Room;

public class RepositoryFactory {
    public enum Entities{
        NANO
    }

    public static Repository get(Entities entities, Context context){
        switch (entities){
            case NANO:
                return new NanoRepository_Room(context);
            default:
                throw new IllegalArgumentException("Table inconnue");
        }
    }

}
