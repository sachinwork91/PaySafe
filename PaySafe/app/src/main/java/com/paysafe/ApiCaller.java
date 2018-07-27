package com.paysafe;

import android.support.annotation.Nullable;

/**
 * @author Abhishek Vadnerkar
 */

public class ApiCaller {

    @Nullable
    public static Customer[] getCustomers() {
        try {
            //Simulates a delayed response from server
            Thread.sleep(2000);
            return new Customer[] {
                    new Customer("John Doe", "5140000000"),
                    new Customer("Jane Doe", "5141111111"),
                    new Customer("James Smith", "5142222222")
            };
        } catch (InterruptedException e) {
            return null;
        }
    }
}
