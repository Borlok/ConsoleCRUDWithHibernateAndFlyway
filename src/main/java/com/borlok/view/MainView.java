package com.borlok.view;

import java.util.List;
import java.util.Scanner;

public class MainView {
    private CompositeView views;
    private Scanner sc;

    public MainView(CompositeView compositeView) {
        this.views = compositeView;
    }

    public void main() {
        try {
            sc = new Scanner(System.in);
            List<View> allView = views.getListOfView();
            System.out.println("\n--Меню--\nДобро пожаловать, выберите вариант:");
            while (true) {
                views.getAllView();
                System.out.println((allView.size() + 1) + ": Выход");

                int order = sc.nextInt();
                if (order == (allView.size() + 1))
                    System.exit(0);
                allView.get(order - 1).main();
            }
        } catch (RuntimeException e) {
            System.err.println("Введены неверные символы или есть другая проблема\n" + e);
            main();
        } catch (Exception e) {
            System.err.println("Для начала нужно добавить объект CompositeView"
                    + " в MainViews методом (addViews())\n" + e);
        } finally {
            sc.close();
        }
    }
}
