package com.borlok.view;

import java.util.Scanner;

public class MainView {
    private AccountView accountView = new AccountView();
    private CustomerView customerView = new CustomerView();
    private SpecialtyView specialtyView = new SpecialtyView();
    private Scanner sc;

    public MainView() {

    }

    public void main() {
        try {
            sc = new Scanner(System.in);

            System.out.println("\n--Меню--\n" +
                                    "Выберите действие:\n" +
                                    "1: Аккауты\n" +
                                    "2: Покупатели\n" +
                                    "3: Специальности\n" +
                                    "4: Выход");
            switch (sc.nextInt()) {
                case 1: accountView.main();
                break;
                case 2: customerView.main();
                break;
                case 3: specialtyView.main();
                break;
                case 4: System.exit(0);
            }
        } catch (RuntimeException e) {
            System.err.println("Введены неверные символы или есть другая проблема\n" + e);
            main();
        } finally {
            sc.close();
        }
    }
}
