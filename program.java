import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Scanner;

public class program {

    public static void main(String[] args) {
        System.out.println("Чего хотите, введите соответствующий номер команды: \n"
                + "1 - Сгенерировать ассортимент магазина\n"
                + "2 - Внести измениния в ассортимент\n"
                + "3 - Розыгрыш\n"
                + "4 - Показать ассортимент\n"
                + "5 - Выйти");

        ArrayList<Toys> assortiment = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag != false) {
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("Введите колличество игрушек в ассортименте: ");
                    int n = sc.nextInt();
                    generateAssortimate(assortiment, n);
                    System.out.println("Ассортимент сгенерирован");
                    break;
                case 2:
                    System.out.println("Что хотите сделать: \nadd - добавить игрушку в ассортимент, delete - удалить");
                    String answer = sc.next();
                    if (answer.equals("add")) {
                        addToys(assortiment);
                    } else if (answer.equals("delete")) {
                        deleteToys(assortiment);
                    } else
                        System.out.println("Команда " + answer + " не распознана");
                    break;
                case 3:
                    System.out.println("Ну начнём розыгрыш");
                    getRaffle(assortiment);
                    break;
                case 4:
                    for (Toys toy : assortiment) {
                        System.out.println(toy);
                    }
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.out.println("Такой команды нет");
            }

        }
    }

    private static void getRaffle(ArrayList<Toys> assortiment) {
        Random rd = new Random();
        double target = rd.nextGaussian();
        int win = (int) Math.ceil(target * 10);
        Toys winner = null;
        for (Toys toy : assortiment) {
            int player = rd.nextInt(-1 * toy.chance / 10, toy.chance / 10 + 1);
            if (player == win) {
                winner = toy;
                System.out.println("Вы выиграли " + winner.toString());
                writeWinner(toy);
                assortiment.remove(toy);
                break;              
            } 
        }
        if (winner == null) {
            System.out.println("В другой раз повезёт");
        }
        
    }

    private static void writeWinner(Toys toy) {
        Scanner sc = new Scanner(System.in, "Cp866");
        System.out.println("Ваше имя: ");
        String winnerName = sc.next();
        System.out.println("Ваш почтовый индекс: ");
        int sendIndex = sc.nextInt();
        File file = new File("ListOfWinner.txt");
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write("Имя победителя: " + winnerName + "; Почтовый индекс: " + sendIndex + "; Выигранная игрушка: " + toy + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Ваш приз уже вам выслан");
    }

    private static Toys findToy(ArrayList<Toys> assortiment) {
        System.out.println("Введите id игрушки: ");
        Scanner sc = new Scanner(System.in);
        int deleteToyId = sc.nextInt();
        for (Toys toy : assortiment) {
            if (toy.id == deleteToyId) {
                return toy;
            }
        }
        throw new RuntimeException("Такого id нет");
    }

    private static void deleteToys(ArrayList<Toys> assortiment) {
        Toys toy = findToy(assortiment);
        assortiment.remove(toy);
        System.out.println("Игрушка под номером " + toy.id + " удалена");
    }

    private static void addToys(ArrayList<Toys> assortiment) {
        Toys toy;
        Scanner sc = new Scanner(System.in, "Cp866");
        int id = assortiment.size() + 1;
        System.out.println("Введите название модели:");
        String name = sc.next();
        System.out.println("Введите страну производителя:");
        String contry = sc.next();
        System.out.println("Введите дату завозки игрушки в формате dd.mm.yyyy:");
        String dateDelivery = sc.next();
        System.out.println("Введите дату производства игрушки в формате dd.mm.yyyy:");
        String dateProduction = sc.next();
        System.out.println("Введите тип игрушки:\n1 - Лего, 2 - Кукла, 3 - Машинка");
        switch (sc.nextInt()) {
            case 1:
                toy = new Lego(id, name, contry, dateDelivery, dateProduction);
                assortiment.add(toy);
                break;
            case 2:
                toy = new Dolls(id, name, contry, dateDelivery, dateProduction);
                assortiment.add(toy);
                break;
            case 3:
                toy = new Car(id, name, contry, dateDelivery, dateProduction);
                assortiment.add(toy);
                break;
            default:
                System.out.println("Такого типа нет");
                break;
        }
        System.out.println("Игрушка, добавлена");
    }

    static String getName(int typeToys) {
        switch (typeToys) {
            case 1:
                return LegoName.values()[new Random().nextInt(LegoName.values().length - 1)].name();
            case 2:
                return DollsName.values()[new Random().nextInt(DollsName.values().length - 1)].name();
            case 3:
                return CarsName.values()[new Random().nextInt(CarsName.values().length - 1)].name();
            default:
                return "Игрушка";
        }
    }

    static String getContry() {
        return ContryNames.values()[new Random().nextInt(ContryNames.values().length - 1)].name();
    }

    static String[] getDateDelandProd() {
        Random rd = new Random();
        String[] date = new String[2];
        Calendar production = new GregorianCalendar(rd.nextInt(2000, 2025), rd.nextInt(1, 13), rd.nextInt(1, 28));
        Calendar deliver = new GregorianCalendar(rd.nextInt(2000, 2025), rd.nextInt(1, 13), rd.nextInt(1, 28));
        if (deliver.after(production)) {
            Date d = production.getTime();
            DateFormat form = new SimpleDateFormat("dd.MM.yyyy");
            date[1] = form.format(d);
            d = deliver.getTime();
            date[0] = form.format(d);
            return date;
        } else
            return getDateDelandProd();
    }

    public static void generateAssortimate(ArrayList<Toys> assortiment, int n) {
        Random rd = new Random();
        for (int i = 0; i < n; i++) {
            int typeToys = rd.nextInt(1, 4);
            switch (typeToys) {
                case 1:
                    assortiment.add(new Lego(i + 1, getName(typeToys), getContry(), getDateDelandProd()[0],
                            getDateDelandProd()[1]));
                    break;
                case 2:
                    assortiment.add(new Dolls(i + 1, getName(typeToys), getContry(), getDateDelandProd()[0],
                            getDateDelandProd()[1]));
                    break;
                case 3:
                    assortiment.add(
                            new Car(i + 1, getName(typeToys), getContry(), getDateDelandProd()[0],
                                    getDateDelandProd()[1]));
                    break;
            }

        }

    }
}