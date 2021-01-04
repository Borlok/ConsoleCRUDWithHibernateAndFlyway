package com.borlok;

import com.borlok.controller.AccountController;
import com.borlok.controller.CompositeController;
import com.borlok.controller.CustomerController;
import com.borlok.controller.SpecialtyController;
import com.borlok.repository.CompositeRepository;
import com.borlok.repository.hibernate.JpaAccountRepository;
import com.borlok.repository.hibernate.JpaCustomerRepository;
import com.borlok.repository.hibernate.JpaSpecialtyRepository;
import com.borlok.service.AccountService;
import com.borlok.service.CompositeService;
import com.borlok.service.CustomerService;
import com.borlok.service.SpecialtyService;
import com.borlok.view.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class FacadeMain {
    public void startTheWork() {
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = new AnnotationConfiguration()
                    .configure("hibernate/hibernate.cfg.xml").buildSessionFactory();

            CompositeRepository repository = new CompositeRepository();
            repository
                    .addRepository(new JpaAccountRepository(sessionFactory))
                    .addRepository(new JpaCustomerRepository(sessionFactory))
                    .addRepository(new JpaSpecialtyRepository(sessionFactory));

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
        } finally {
            if (sessionFactory != null)
                sessionFactory.close();
        }
    }

}
