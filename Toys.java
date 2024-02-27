

public abstract class Toys {
    int id, chance;
    String name, contryProdoucer;
    String dateDelivery, dateProduction;

    public Toys(int id, String name, String contryProdoucer, int chance,
            String dateDelivery, String dateProduction) {
        this.id = id;
        this.name = name;
        this.contryProdoucer = contryProdoucer;
        this.chance = chance;
        this.dateDelivery = dateDelivery;
        this.dateProduction = dateProduction;
    }

    @Override
    public String toString() {
        return " под серийным номером: " + id + ", Название модели: " + name
                + ", Страна производста: " + contryProdoucer + ", Дата изготовления: " + dateProduction
                + ", Дата поступления в магазин: " + dateDelivery;
    }

}
