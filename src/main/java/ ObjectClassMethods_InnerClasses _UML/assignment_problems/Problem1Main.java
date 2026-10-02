public class Problem1Main {

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        WashType quick = new QuickWash();
        WashType normal = new NormalWash();
        WashType heavy = new HeavyWash();

        WashCycle cycle1 = m1.startWash(asha, quick);

        m1.startWash(ravi, heavy);

        WashCycle cycle2 = m2.startWash(ravi, heavy);

        m1.completeWash();

        WashCycle cycle3 = m1.startWash(neha, normal);
    }
}


class Student {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

abstract class WashType {

    public abstract String getName();

    public abstract int getDuration();

    public abstract double getCharge();
}


class QuickWash extends WashType {

    @Override
    public String getName() {
        return "Quick";
    }

    @Override
    public int getDuration() {
        return 30;
    }

    @Override
    public double getCharge() {
        return 20.00;
    }
}


class NormalWash extends WashType {

    @Override
    public String getName() {
        return "Normal";
    }

    @Override
    public int getDuration() {
        return 45;
    }

    @Override
    public double getCharge() {
        return 30.00;
    }
}


class HeavyWash extends WashType {

    @Override
    public String getName() {
        return "Heavy";
    }

    @Override
    public int getDuration() {
        return 60;
    }

    @Override
    public double getCharge() {
        return 45.00;
    }
}

class WashCycle {

    private Student student;
    private WashingMachine machine;
    private WashType washType;

    public WashCycle(
            Student student,
            WashingMachine machine,
            WashType washType) {

        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public Student getStudent() {
        return student;
    }

    public WashType getWashType() {
        return washType;
    }
}

class WashingMachine {

    private String machineId;
    private boolean busy;
    private WashCycle currentCycle;

    public WashingMachine(String machineId) {
        this.machineId = machineId;
        this.busy = false;
    }

    public WashCycle startWash(
            Student student,
            WashType washType) {

        if (busy) {

            System.out.println(
                    "Machine " +
                    machineId +
                    " is currently busy."
            );

            return null;
        }

        currentCycle =
                new WashCycle(
                        student,
                        this,
                        washType
                );

        busy = true;

        System.out.println(
                washType.getName() +
                " wash started on " +
                machineId +
                " for " +
                student.getName() +
                " (" +
                washType.getDuration() +
                " min)."
        );

        System.out.printf(
                "Charge: ₹%.2f%n",
                washType.getCharge()
        );

        return currentCycle;
    }

    public void completeWash() {

        if (!busy) {
            return;
        }

        System.out.println(
                machineId +
                " cycle completed."
        );

        busy = false;
        currentCycle = null;

        System.out.println(
                machineId +
                " is now free."
        );
    }

    public boolean isBusy() {
        return busy;
    }
}