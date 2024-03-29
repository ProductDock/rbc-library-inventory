## PD Library Inventory

Inventory is one of the services that makes PD Library.

Keeps track of how many copies of the book the Library has and how many copies are rented.
It can be considered as a physical place where books are held. Bookshelves are currently placed in ProductDock's cantina.

### Delivery mechanism

* REST API served over [Gateway](https://github.com/ProductDock/rbc-library-gateway)

### Security

* Protected by the User Profile JWT token issued by the [PD Library User Profile](https://github.com/ProductDock/rbc-library-user-profiles) service

### Dependencies

1. Mongo database
2. Kafka

Dependencies can be run from [Infrastructure](https://github.com/ProductDock/rbc-library-infrastructure) project,
by [docker compose](https://docs.docker.com/compose/) scripts.

### Build and run locally

Locally, application can be started in two ways.

* From source code
* From latest published Docker image

#### From source code (Run from IntelliJ)

_Suitable when developing features in this project._

1. Set [Spring Profile](https://docs.spring.io/spring-boot/docs/1.1.4.RELEASE/reference/html/boot-features-profiles.html) to local by following these steps:
- Open "Edit Configurations"
- in the section "Library Application" find the field "Program arguments";
- enter the following command: --spring.profiles.active=local
2. Press Run or Debug Library Application

#### From latest published Docker image

_Suitable when developing features in other projects, and you need this one as a dependency._

For this purpose see [Infrastructure](https://github.com/ProductDock/rbc-library-infrastructure) project.  