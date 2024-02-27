public class Dolls extends Toys {
    public Dolls(int id, String name, String contryProdoucer, String dateDelivery, String dateProduction) {
        super(id,
                name,
                contryProdoucer,
                55,
                dateDelivery,
                dateProduction);
    }

    @Override
    public String toString() {
        return "Кукла" + super.toString();
    }

}
