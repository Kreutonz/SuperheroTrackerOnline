package ca.cmpt213.a3.onlinesuperherotracker.model;

public class Superhero {
    private long id;
    private String name;
    private double heightInCm;
    private int civilianSaveCount;
    private String superPower;

    public Superhero()
    { }

    public Superhero(long id, String name, double heightInCm, int civiliansSavedCount, String superPower){
        this.id = id;
        this.name = name;
        this.heightInCm = heightInCm;
        this.civilianSaveCount = civiliansSavedCount;
        this.superPower = superPower;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperPower() {
        return superPower;
    }

    public void setSuperPower(String superPower) {
        this.superPower = superPower;
    }

    public double getHeightInCm() {
        return heightInCm;
    }

    public void setHeightInCm(double heightInCm) {
        this.heightInCm = heightInCm;
    }

    public int getCivilianSaveCount() {
        return civilianSaveCount;
    }

    public void setCivilianSaveCount(int civilianSaveCount) {
        this.civilianSaveCount = civilianSaveCount;
    }
}
