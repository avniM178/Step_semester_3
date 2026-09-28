public class Problem3Main {

    public static void main(String[] args) {

        StringInstrument s = new StringInstrument();
        Violin v = new Violin();

        System.out.println(s.play());
        System.out.println(v.play());
    }
}

abstract class Instrument {

    public Instrument() {
    }

    public abstract String play();
}

class StringInstrument extends Instrument {

    public StringInstrument() {
        super();
    }

    @Override
    public String play() {
        return superPlay() + "Strumming the strings";
    }

    // Helper method because Instrument.play() is abstract
    protected String superPlay() {
        return "";
    }
}

class Violin extends StringInstrument {

    public Violin() {
        super();
    }

    @Override
    public String play() {
        String baseMessage = super.play();

        return baseMessage
                + ", with a bow drawn across four strings";
    }
}