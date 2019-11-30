package project.domain.filters;

public enum Company {
    SONY("Sony"), XBOX("Xbox"), NINTENDO("Nintendo"), SEGA("Sega"), ANOTHER("Другое");

    private String view;

    Company(String view){
        this.view = view;
    }

    @Override
    public String toString() {
        return view;
    }
}
