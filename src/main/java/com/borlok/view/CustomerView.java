package com.borlok.view;

import com.borlok.controller.AccountController;
import com.borlok.controller.CompositeController;
import com.borlok.controller.CustomerController;
import com.borlok.controller.SpecialtyController;
import com.borlok.controller.builder.AccountBuilder;
import com.borlok.controller.builder.AccountBuilderImpl;
import com.borlok.controller.builder.CustomerBuilderImpl;
import com.borlok.model.Account;
import com.borlok.model.AccountStatus;
import com.borlok.model.Customer;
import com.borlok.model.Specialty;
import com.borlok.repository.JDBC.JdbcCustomerRepository;

import java.util.*;

public class CustomerView implements View {
    private CompositeController compositeController;
    private CustomerBuilderImpl customerBuilder = new CustomerBuilderImpl();
    private Scanner sc;

    public CustomerView(CompositeController compositeController) {
        this.compositeController = compositeController;
    }

    @Override
    public void main() {
        try {
            sc = new Scanner(System.in);
            System.out.println(
                    "--Покупатели--\n" +
                            "Выберите действие:\n" +
                            "1: Создать покупателя\n" +
                            "2: Посмотреть покупателей\n" +
                            "3: Редактировать покупателя\n" +
                            "4: Удалить покупателя\n" +
                            "5: Назад");
            int choice = sc.nextInt();
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
            System.out.println("Введены неверные символы: " + e);
            main();
        }
    }

    @Override
    public void create() {
        Customer customer = createCustomer();
        ((CustomerController) compositeController.getController(new CustomerController())).create(customer);
        main();
    }

    private Customer createCustomer() {
        sc = new Scanner(System.in);
        Account account = createAccount();
        customerBuilder.createCustomer();
        customerBuilder.getCustomer().setAccount(account);
        customerBuilder.setSpecialties(getSpecialties());
        return customerBuilder.getCustomer();
    }

    private Account createAccount() {
        AccountBuilder accountBuilder = new AccountBuilderImpl();
        System.out.println("Введите имя");
        accountBuilder.createAccount();
        accountBuilder.setName(sc.next());

        System.out.println("Выберите статус");
        viewAccountStatus();
        accountBuilder.setStatus(AccountStatus.values()[sc.nextInt() - 1]);

        return accountBuilder.getAccount();
    }

    private void viewAccountStatus() {
        for (int i = 0; i < AccountStatus.values().length; i++)
            System.out.println((i + 1) + ": " + AccountStatus.values()[i]);
    }

    private Set<Specialty> getSpecialties() {
        Set<Specialty> specialties = new HashSet<>();
        List<Specialty> specialtyList = ((SpecialtyController) compositeController
                .getController(new SpecialtyController()))
                .getAll();

        specialtyList.forEach(x -> System.out.println((specialtyList.indexOf(x) + 1) + ": " + x.getName()));

        System.out.println("Добавьте специальности\n" + "для выхода нажмите '0'");
        int order = sc.nextInt();
        while (order != 0) {
            specialties.add(specialtyList.get(order - 1));
            System.out.println("добавьте еще или нажмите '0' для продолжения");
            order = sc.nextInt();
        }
        return specialties;
    }

    @Override
    public void read() {
        viewAllCustomers();
        System.out.println("Введите любой символ для продолжения...");
        sc.next();
        main();
    }

    private List<Customer> getAllCustomersAsList() {
        return ((CustomerController) compositeController.getController(new CustomerController()))
                .getAll();
    }

    private void viewAllCustomers() {
        getAllCustomersAsList().forEach(x ->
                System.out.println(x.getId()
                        + ": | " + x.getAccount().getName()
                        + " | " + x.getAccount().getStatus()
                        + " | " + Arrays.toString(x.getSpecialties().toArray()) + "|"));
    }

    @Override
    public void update() {
        System.out.println("Выберите покупателя для замены");
        viewAllCustomers();
        int id = sc.nextInt();

        Customer customer = createCustomer();
        customer.setId(id);
        ((CustomerController) compositeController
                .getController(new CustomerController())).update(customer, id);
        main();
    }

    @Override
    public void delete() {
        System.out.println("Выберите покупателя для удаления: ");
        viewAllCustomers();
        int id = sc.nextInt();
        compositeController
                .getController(new CustomerController()).delete(id);
        main();
    }

    @Override
    public String toString() {
        return "Покупатели";
    }
}
