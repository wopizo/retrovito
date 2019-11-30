package project.domain.filters;

public enum Type {
    GAMEPAD("Геймпад"), CONSOLE("Приставка"), DISK("Диск"), ANOTHER("Другое");

    private String view;

    Type(String view){
        this.view = view;
    }

    @Override
    public String toString() {
        return view;
    }
}
