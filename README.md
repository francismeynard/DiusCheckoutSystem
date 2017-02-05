# DIUS Checkout Sytem
Dius is starting a computer store with a *brand new* checkout system. This checkout system can *scan items* in any order, validates the items if *exist in the catalogue*, applies any *pricing rules*, and then returns the *total amount* to be paid.

In this **DIUSCheckoutSystem** java project, I have implemented a solution for the coding exercise problem relating to checkout system. Also, the project comes with a *test classes* and *simple application* to test and verify the solution to the problem.

Click here **[dius_shopping_coding.md](https://gist.github.com/codingricky/2913880)** to see the full description of the coding exercise problem.

### System Requirements
--------------------
In order to compile and run the test classes and the application, this java project requires.

* [Java Runtime Environment 8](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)
<br/>This comes with the latest Java8 APIs that used in the project
* JUnit 4
<br/>The test classes in the project are written in JUnit 4

### Unit Testing
--------------------
The project comes with a **unit test classes**, and you can just simply run these tests to verify the program's solution to the coding exercise problem.
```
1. ExampleScenarioTest  - This test the scenarios listed in the coding exercise problem
2. CheckoutTest         - This test the checkout process, including any pricing rules applied
3. CatalogueTest        - Focus of the test is only on the Catalogue
4. ProductTest          - Focus of the test is only on the Product/Item
```
**Pricing Rules Test Classes**
<br/>
Additionally, the pricing rule implementation classes have their own unit test classes.
```
1. BulkDiscountPricingRuleTest
2. FreeBundleItemPricingRuleTest
3. ItemDealDiscountPricingRuleTest
```

### Running the Application
--------------------
Alternatively, the project shipped with a very simple application where the scenarios listed in the coding exercise problem have been tested and results are displayed in the console screen. The application doesn't accept any parameters, so simply run the DiusCheckoutSystemApp using java command in console, or in java IDE of your choice.
```sh
java au.com.dius.shop.app.DiusCheckoutSystemApp
```

### Implementation Summary
--------------------
The following are the classes and interface implemented for the checkout system. 
- Checkout
- Catalogue
- Product
- PricingRule (interface)
<br/>- BulkDiscountPricingRule
<br/>- FreeBundleItemPricingRule
<br/>- ItemDealDiscountPricingRule
