<h2> Design Pattern(s) </h2>

Chain of Responsibility: As, for the given problem statement, based on category of item certain checks needs to be performed. Thus, chain of responsibility design pattern was employed to create a chain of responsibility based on Item's category. An item goes through provided chain till responsibility is not assigned to particular item i.e. category-based tasks are not performed. In this problem prices are calculated and items belonging to particular category is check for it's max quantity limit.

-	Quantity for each item is permissible
-	Calculating Price
-	Keep track of items belonging to particular category for later use if Category Quantity limit exceeds.


Iterator: In the given problem statement, an item holds specific characteristics so using Iterator design pattern we can traverse, modify and update the items and its characteristics effectively. An Item have characteristics like name, price, quantity and category. Using Iterator pattern, we can also protect item's data to be get manipulate by another unauthorized external factor.

-	Provide a way to access the elements of an aggregate object sequentially without exposing its underlying representation.


Singleton: In the problem statement, we also had to put limit to each category, which will apply to all items belonging to particular category. Thus, singleton class “CategoryLimit” is created to ensure that limit is only set once and applied to all items belonging to particular category.


<h2> Class Diagram </h2>

![Class Diagram](https://github.com/gopinathsjsu/individual-project-Dhrupa-patel/blob/2c7414e0fa08f4b036b88c5b44f8071d96d1087e/Order%20Management%20UML%20class.png?raw=true)
