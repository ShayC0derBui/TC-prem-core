package Spring.model.enums.gateio;

public enum GateioSide {
    BUY("buy"),
    SELL("sell");

    private final String side;

    GateioSide(String side) {
        this.side = side;
    }

    public String getSide() {
        return side;
    }
}

