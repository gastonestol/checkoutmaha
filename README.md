**Checkout Maha**

Some design characteristics:

* Prices are being stored as float types in order to contemplate the possibility of using decimals
* Discount and Items have a ManyToOne relation in order to contemplate the possibility of multiples discounts for the 
same item
* A small sqlite database is inside the project: sample.db 

Running Requirements:
* Gradle 6.8.2

Running instructions:

* How to run tests: gradle test -i
* How to run application: gradle run
    * command line testing sample:

`curl --location --request POST 'http://localhost:8080/checkout' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '[
     "001",
     "002",
     "001",
     "004",
     "003"
]'`