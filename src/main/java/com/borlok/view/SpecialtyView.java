package com.borlok.view;

import com.borlok.controller.CompositeController;
import com.borlok.controller.SpecialtyController;
import com.borlok.controller.builder.SpecialtyBuilderImpl;
import com.borlok.model.Specialty;

import java.util.List;
import java.util.Scanner;

public class SpecialtyView implements View {
    private CompositeController compositeController;
    private SpecialtyBuilderImpl specialtyBuilder = new SpecialtyBuilderImpl();
    private Scanner sc;

    public SpecialtyView(CompositeController compositeController) {
        this.compositeController = compositeController;
    }

    @Override
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
            if (choice != 5)
                switch (choice) {
                    case 1 : create();
                    break;
                    case 2 : read();
                        break;
                    case 3 : update();
                        break;
                    case 4 : delete();
                        break;
                    default : {
                        System.out.print("\nТакого действия нет");
                        main();
                    }
                    break;
                }
        } catch (Exception e) {
            System.out.println("Введены неверные символы\n" + e);
            main();
        }
    }

    @Override
    public void create() {
        specialtyBuilder.createSpecialty();
        System.out.println("Введите наименование специальности: ");
        specialtyBuilder.setName(sc.next());
        ((SpecialtyController) compositeController.getController(new SpecialtyController()))
                .create(specialtyBuilder.getSpecialty());
        main();
    }

    @Override
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
        return ((SpecialtyController) compositeController.getController(new SpecialtyController()))
                .getAll();
    }

    @Override
    public void update() {
        System.out.println("Выберите специальность для замены");
        readAll();
        int id = sc.nextInt();
        System.out.println("Введите новую специальность: ");
        specialtyBuilder.createSpecialty();
        specialtyBuilder.getSpecialty().setName(sc.next());
        ((SpecialtyController) compositeController.getController(new SpecialtyController()))
                .update(specialtyBuilder.getSpecialty(), id);
        main();
    }

    @Override
    public void delete() {
        System.out.println("Выберите специальность для удаления: ");
        readAll();
        compositeController.getController(new SpecialtyController()).delete(sc.nextInt());
        main();
    }

    @Override
    public String toString() {
        return "Специальности";
    }
}
