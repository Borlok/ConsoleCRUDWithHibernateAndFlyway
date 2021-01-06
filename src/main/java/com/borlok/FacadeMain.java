package com.borlok;

import com.borlok.repository.hibernate.JpaUtil;
import com.borlok.view.*;

public class FacadeMain {
    public void startTheWork() {
        JpaUtil.openConnectionToDatabase();
        try {

            MainView mainView = new MainView();
            mainView.main();

        } finally {
            JpaUtil.closeAllSessions();
        }
    }
}
