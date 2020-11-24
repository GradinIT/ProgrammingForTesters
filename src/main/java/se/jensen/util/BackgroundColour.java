package se.jensen.util;

public enum BackgroundColour {
    BLACK("\u001B[40m"),
    RED("\u001B[41m"),
    GREEN("\u001B[42m"),
    YELLOW("\u001B[43m"),
    BLUE("\u001B[44m"),
    PURPLE("\u001B[45m"),
    CYAN("\u001B[46m"),
    WHITE("\u001B[47m");
    private final String colourCode;

    BackgroundColour(String colourCode) {
        this.colourCode = colourCode;
    }

    @Override
    public String toString() {
        return colourCode;
    }
}
