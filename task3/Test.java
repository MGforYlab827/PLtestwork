import java.util.List;

public class Test {
    private long id;
    private String title;
    private String value;
    private List<Test> values;

    public Test(long id, String title, String value, List<Test> values) {
        this.id = id;
        this.title = title;
        this.value = value;
        this.values = values;
    }

    public Test() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Test> getValues() {
        return values;
    }

    public void setValues(List<Test> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id = " + id +
                ", title = " + title +
                ", value = " + value +
                ", values = " + values +
                "}";
    }
}
