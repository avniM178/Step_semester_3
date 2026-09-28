public class Problem1Main {

    public static void main(String[] args) {

        AlarmClock a = new AlarmClock("7:00 AM");
        Doorbell d = new Doorbell("Front Door");

        System.out.println(a.ring());
        System.out.println(d.ring());

        Ringable[] devices = {a, d};

        Ringable.ringAll(devices);
    }
}

interface Ringable {

    String ring();

    static void ringAll(Ringable[] devices) {

        for (Ringable device : devices) {
            System.out.println(device.ring());
        }
    }
}

class AlarmClock implements Ringable {

    private String time;

    public AlarmClock(String time) {
        this.time = time;
    }

    @Override
    public String ring() {
        return "Alarm ringing for " + time;
    }
}

class Doorbell implements Ringable {

    private String location;

    public Doorbell(String location) {
        this.location = location;
    }

    @Override
    public String ring() {
        return "Doorbell ringing at " + location;
    }
}