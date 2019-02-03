package com.app.tddshop;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.app.tddshop.test.ProductBusinessServiceTestUnit;
import com.app.tddshop.test.ShoppingCartTest;

@RunWith(Suite.class)
@SuiteClasses({ ProductBusinessServiceTestUnit.class,ShoppingCartTest.class })
public class TddShopApplicationTests {
}