public class parClass {
    protected double atrA;
    protected int id;
    protected String name;
    protected double speed;
    protected double strength;

    static int numCount = 0;

    parClass(double atrA, int id, String name, double speed, double strength) {
        this.atrA = atrA;
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.strength = strength;
    }

    protected void changeState(String state) {
        System.out.println("State changed to: " + state);

    }

    protected String name() {
        return name;
    }

    private void test() {
        System.out.println("test");
    }
}
