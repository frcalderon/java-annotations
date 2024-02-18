import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Annotations {

    public static void main(String[] args) {
        Car car1 = new Car("Ford", "Mustang", "0123456789");
        Car car2 = new Car("Porsche", "991", "1234567890");
        Car car3 = new Car("Audi", "RS3", "2345678901");

        System.out.println(serializeObject(car1));
        System.out.println(serializeObject(car2));
        System.out.println(serializeObject(car3));
    }

    public static String serializeObject(Object o) {
        if (!o.getClass().isAnnotationPresent(JsonSerializable.class)) {
            throw new RuntimeException("This object could not be serializable!");
        }

        List<String> serializedFields = new ArrayList<>();

        for (Field field : o.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonField.class)) {
                field.setAccessible(true);
                String value;
                try {
                    value = field.get(o).toString();
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                serializedFields.add(String.format("\"%s\": %s", field.getName(), value));
            }
        }

        return String.format(
                "%s { %s }",
                o.getClass().getName(),
                String.join(", ", serializedFields)
        );
    }
}
