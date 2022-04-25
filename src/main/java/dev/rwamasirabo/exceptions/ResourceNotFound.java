package dev.rwamasirabo.exceptions;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(int emplId){
        super("The resource with id " + emplId+ "was not found");
    }
}
