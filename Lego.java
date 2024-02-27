public class Lego extends Toys {

    public Lego(int id, String name, String contryProdoucer, String dateDelivery, String dateProduction) {
        super(id,
                name,
                contryProdoucer,
                35,
                dateDelivery,
                dateProduction);
    }

    @Override
    public String toString() {
        return "Набор лего" + super.toString();
    }

}
