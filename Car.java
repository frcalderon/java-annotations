@JsonSerializable
public class Car {

    @JsonField
    private String brand;
    @JsonField
    private String model;
    private String chassisId;

    public Car(String brand, String model, String chassisId) {
        this.brand = brand;
        this.model = model;
        this.chassisId = chassisId;
    }
}
