public class ZMoves {
    private String name;
    private String type;
    private boolean status; // Indicates whether the move has been used or not
    private String desc; 
    private int basePower;

    public ZMoves(String name, String type, String desc, int basePower) {
        this.name = name;
        this.type = type;
        this.status = false;
        this.desc = desc;
        this.basePower = basePower;
    }
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    public int getBasePower() {
        return basePower;
    }
    
}
