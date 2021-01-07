package com.borlok.view;

import com.borlok.controller.SpecialtyController;
import com.borlok.controller.builder.SpecialtyBuilderImpl;
import com.borlok.model.Specialty;

import java.util.List;
import java.util.Scanner;

public class SpecialtyView {
    private SpecialtyController specialtyController = new SpecialtyController();
    private SpecialtyBuilderImpl specialtyBuilder = new SpecialtyBuilderImpl();
    private Scanner sc;

    public void main() {
        try {
            sc = new Scanner(System.in);
            int choice;
            System.out.println("\n--Специальности--\n" +
                    "Выберите действие:\n" +
                    "1: Добавить специальность\n" +
                    "2: Посмотреть специальности\n" +
                    "3: Редактировать специальность\n" +
                    "4: Удалить специальность\n" +
                    "5: Назад");
            choice = sc.nextInt();
                switch (choice) {
                    case 1 : create();
                        break;
                    case 2 : read();
                        break;
                    case 3 : update();
                        break;
                    case 4 : delete();
                        break;
                    case 5 : new MainView().main();
                    default : {
                        System.out.print("\nТакого действия нет");
                        main();
                    }
                }
        } catch (Exception e) {
            System.out.println("Введены неверные символы\n" + e);
            main();
        }
    }

    public void create() {
        specialtyBuilder.createSpecialty();
        System.out.println("Введите наименование специальности: ");
        specialtyBuilder.setName(sc.next());
        specialtyController.create(specialtyBuilder.getSpecialty());
        main();
    }

    public void read() {
        readAll();
        System.out.println("Введите любой символ для продолжения...");
        sc.next();
        main();
    }

    private void readAll() {
        for (Specialty x : getAll())
            System.out.println(x.getId() + ": | " + x.getName() + "|");
    }

    private List<Specialty> getAll() {
        return specialtyController.getAll();
    }

    public void update() {
        System.out.println("Выберите специальность для замены");
        readAll();
        int id = sc.nextInt();
        System.out.println("Введите новую специальность: ");
        specialtyBuilder.createSpecialty();
        specialtyBuilder.getSpecialty().setName(sc.next());
        specialtyBuilder.getSpecialty().setId(id);
        specialtyController.update(specialtyBuilder.getSpecialty());
        main();
    }

    public void delete() {
        System.out.println("Выберите специальность для удаления: ");
        readAll();
        specialtyController.delete(sc.nextInt());
        main();
    }
}
