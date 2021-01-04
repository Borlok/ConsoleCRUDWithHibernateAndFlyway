package com.borlok.view;

import com.borlok.controller.AccountController;
import com.borlok.controller.CompositeController;
import com.borlok.controller.builder.AccountBuilder;
import com.borlok.controller.builder.AccountBuilderImpl;
import com.borlok.model.Account;
import com.borlok.model.AccountStatus;

import java.util.List;
import java.util.Scanner;

public class AccountView implements View{
    private CompositeController compositeController;
    private AccountBuilder accountBuilder = new AccountBuilderImpl();
    private Scanner sc;

    public AccountView(CompositeController compositeController) {
        this.compositeController = compositeController;
    }

    @Override
    public void main() {
        try {
            sc = new Scanner(System.in);
            System.out.println("\n--Аккаунт--\n" +
                    "Выберите действие:\n" +
                    "1: Добавить аккаунт\n" +
                    "2: Посмотреть аккаунты\n" +
                    "3: Редактировать аккаунт\n" +
                    "4: Удалить аккаунт\n" +
                    "5: Назад");
            int choice = sc.nextInt();
            if (choice != 5)
                switch (choice) {
                    case 1: create();
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
            System.out.println("Введены неверные символы " + e);
            main();
        }
    }

    @Override
    public void create() {
        createAllAndSave();
        main();
    }

    private void createAllAndSave() {
        Account account = createAccount();
        ((AccountController) compositeController.getController(new AccountController())).create(account);
    }

    private Account createAccount() {
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

    @Override
    public void read() {
        viewAllAccounts();
        System.out.println("Введите любой символ для продолжения...");
        sc.next();
        main();
    }

    private void viewAllAccounts() {
        getAllAccountsAsList().forEach(x ->
                System.out.println(x.getId() + ": | " + x.getName() + " | " + x.getStatus() + "|"));
    }

    private List<Account> getAllAccountsAsList() {
        return ((AccountController) compositeController.getController(new AccountController()))
                .getAll();
    }

    @Override
    public void update() {
        System.out.println("Выберите аккаунт для замены");
        viewAllAccounts();
        int id = sc.nextInt();
        Account account = createAccount();
        account.setId(id);
        ((AccountController) compositeController.getController(new AccountController()))
                .update(account, id);
        main();
    }

    @Override
    public void delete() {
        System.out.println("Выберите аккаунт для удаления: ");
        viewAllAccounts();
        int id = sc.nextInt();
        compositeController.getController(new AccountController())
                .delete(id);
        main();
    }

    @Override
    public String toString() {
        return "Аккаунт";
    }
}
