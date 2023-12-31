
public class Value {
    private long id;
    private String value;

    public Value(long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Value() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id = " + id +
                ", value = " + value +
                "}";
    }
}
