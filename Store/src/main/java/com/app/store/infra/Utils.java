package com.app.store.infra;

import com.app.store.exceptions.CustomerNotFoundException;
import com.app.store.exceptions.ExceptionResponse;
import com.app.store.exceptions.ProductNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

public class Utils {
    private Utils(){}
    private static Utils utils;

    public static Utils getInstance(){
        if(null == utils){
            return new Utils();
        }
        else{
            return utils;
        }
    }

    public CustomerNotFoundException getCustomerNotFoundException(Long customerId) {
        return new CustomerNotFoundException(new ExceptionResponse("No customer with ID: " + customerId + " found", ErrorCode.CUSTOMER_NOT_FOUND.code, new Date()));
    }

    public ProductNotFoundException getProductNotFoundException(long productID) {
        return new ProductNotFoundException(new ExceptionResponse("No product with ID: " + productID + " found", ErrorCode.PRODUCT_NOT_FOUND.code, new Date()));
    }
}
