package fr.pantheonsorbonne.exception;

public class InvalidBossException extends Throwable {
    public InvalidBossException(String galacticRegistrationNumberBossIsWrong) {
        super(galacticRegistrationNumberBossIsWrong);
    }
}
