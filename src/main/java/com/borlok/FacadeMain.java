package com.borlok;

import com.borlok.controller.AccountController;
import com.borlok.controller.CompositeController;
import com.borlok.controller.CustomerController;
import com.borlok.controller.SpecialtyController;
import com.borlok.repository.CompositeRepository;
import com.borlok.repository.JDBC.JdbcAccountRepository;
import com.borlok.repository.JDBC.JdbcCustomerRepository;
import com.borlok.repository.JDBC.JdbcSpecialtyRepository;
import com.borlok.service.AccountService;
import com.borlok.service.CompositeService;
import com.borlok.service.CustomerService;
import com.borlok.service.SpecialtyService;
import com.borlok.view.*;

public class FacadeMain {
    public void startTheWork() {
        CompositeRepository repository = new CompositeRepository();
        repository
                .addRepository(new JdbcAccountRepository())
                .addRepository(new JdbcCustomerRepository())
                .addRepository(new JdbcSpecialtyRepository());

        CompositeService compositeService = new CompositeService();
        compositeService
                .addService(new AccountService(repository))
                .addService(new CustomerService(repository))
                .addService(new SpecialtyService(repository));

        CompositeController compositeController = new CompositeController();
        compositeController
                .addController(new AccountController(compositeService))
                .addController(new CustomerController(compositeService))
                .addController(new SpecialtyController(compositeService));

        CompositeView compositeView = new CompositeView();
        compositeView
                .addView(new AccountView(compositeController))
                .addView(new CustomerView(compositeController))
                .addView(new SpecialtyView(compositeController));

        MainView mainView = new MainView(compositeView);
        mainView.main();
    }

}
