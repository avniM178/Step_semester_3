public class Problem2Main {

    public static void main(String[] args) {

        Painting p = new Painting("Sunset Fields");
        Sculpture s = new Sculpture("The Thinker II");

        System.out.println(p.describe());
        System.out.println(s.describe());

        System.out.println(p.getPieceId());
        System.out.println(s.getPieceId());

        // ArtPiece a = new ArtPiece(); // Does not compile
    }
}

abstract class ArtPiece {

    private static int pieceCounter = 1000;
    private final String pieceId;

    public ArtPiece() {
        pieceCounter++;
        pieceId = "ART-" + pieceCounter;
    }

    public abstract String describe();

    public String getPieceId() {
        return pieceId;
    }
}

class Painting extends ArtPiece {

    private String title;

    public Painting(String title) {
        super();
        this.title = title;
    }

    @Override
    public String describe() {
        return "Painting: " + title + ", framed on canvas";
    }
}

class Sculpture extends ArtPiece {

    private String title;

    public Sculpture(String title) {
        super();
        this.title = title;
    }

    @Override
    public String describe() {
        return "Sculpture: " + title + ", carved from stone";
    }
}
