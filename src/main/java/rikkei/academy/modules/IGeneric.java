package rikkei.academy.modules;

public interface IGeneric <T,E>{


    T findById(E id);

    void delete(E id);

}
