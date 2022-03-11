public class childClass extends parClass {
    int[] sampleArr;

    childClass(double atrA, int id, String name, double speed, double strength, int[] arr) {
        super(atrA, id, name, speed, strength);
        sampleArr = arr;
        printAll();
    }

    private void printAll() {
        for (int i = 0; i < sampleArr.length; i++) {
            System.out.println(sampleArr[i]);
        }
        System.out.println(this.atrA);
        System.out.println(this.id);
        System.out.println(this.name);
        System.out.println(this.speed);
        System.out.println(this.strength);

    }

    public void changeState(String state) {
        int a = 0;
        for (int i = 0; i < sampleArr.length; i++) {
            a += sampleArr[i];
        }
    }

}
