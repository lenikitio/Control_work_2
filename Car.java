public class Car extends Toys {
    public Car(int id, String name, String contryProdoucer, String dateDelivery, String dateProduction) {
        super(id,
                name,
                contryProdoucer,
                40,
                dateDelivery,
                dateProduction);
    }

    @Override
    public String toString() {
        return "Машинка" + super.toString();
    }

}
