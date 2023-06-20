package Model;

public class ConcreteFiling extends Filing {
    private String name;

    public ConcreteFiling(int id, boolean unreal, String s) {
        super(id, unreal);
        this.name = s;
    }

    public ConcreteFiling(int id) {
        super(id);
        this.name = null;
    }
}