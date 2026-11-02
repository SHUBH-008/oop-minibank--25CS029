# Object Oriented Programming (CEUE203) - Java Practicals 1 to 10

This repository contains complete, verified, and independently executable implementations for **Practicals 1 through 10** for Object Oriented Programming with Java (Semester 3).

---

## 🛠 Project Structure & Anti-Collision Design

1. **Mac File System & VS Code Case Sensitivity Protection:**
   - On macOS (APFS/HFS+), file names are case-insensitive. All class and file names strictly follow canonical PascalCase naming conventions (e.g., `PasswordChecker.java`, `ChatFilter.java`).
   - Old prototype files in the `Referance` folder contained lowercase/duplicate names (`passwordChecker.java`, duplicate `class Main`) which caused collision warnings in VS Code. The `Referance` directory is now filtered via `.vscode/settings.json` and `java.project.resourceFilters`.
2. **VS Code Multi-Root Workspace (`JavaPracticals.code-workspace`):**
   - Opening `JavaPracticals.code-workspace` in VS Code treats each `Practical N` folder as its own isolated project scope, eliminating duplicate class collisions across labs.
3. **Standalone Execution:**
   - Every practical folder contains its own complete model and driver classes, allowing each lab to be compiled and executed independently from the command line or any IDE.

---

## 📋 Summary of Practicals (1 to 10)

### [Practical 1](./Practical%201) - Enums, Records & Switch Expressions
- **Part A:**
  - `VendingMachine.java`: Enum `Coin` (`ONE`, `TWO`, `FIVE`, `TEN`), snack price 15, switch expression for coin value, returns change.
  - `TollBooth.java`: Record `Vehicle(number, type)`, switch expression for toll calculation (`bike->20`, `car->50`, `truck->150`), tracks frequencies.
  - `RPSLS.java`: Enum `Move`, switch expression in `winner(Move a, Move b)`, 5-round game against computer.
- **Part B:**
  - `MiniBank.java`: Record `BankInfo`, enum `MenuOption`, menu navigation loop using switch expressions.

### [Practical 2](./Practical%202) - Encapsulation & Constructors
- **Part A:**
  - `Thermostat.java`: Encapsulation, static `activeCount`, constructor chaining with `this()`, `raise()` and `lower()`.
  - `CinemaShow.java`: Capacity tracking, `book()`, `cancel()`, static `totalBooked`.
  - `ParkingLot.java`: Capacity limits for two- and four-wheelers, `park()`, `leave()`, static revenue tracking.
- **Part B:**
  - `Customer.java`: Static ID generator producing `"CUST101"`, encapsulation.
  - `Account.java`: Final `accountNumber`, balance protection (no public setter), constructor chaining.
  - `MiniBank.java`: Account array, deposits, withdrawals, and account-to-account transfer helper.

### [Practical 3](./Practical%203) - Object Methods & Nested Classes
- **Part A:**
  - `Point.java`: Overridden `toString()`, `equals()`, and `hashCode()`. Counts distinct coordinates.
  - `Card.java`: Overridden `equals()` and `hashCode()` by rank and suit. Detects duplicates.
  - `Fraction.java`: GCD reduction to lowest terms in constructor, equality checks.
- **Part B:**
  - `Customer.java`: Public static nested class `Address`, implements `Cloneable` and deep copies address.
  - `Account.java`: Formatted `toString()`, `equals()` comparing `accountNumber`, formatted statement generator.
  - `MiniBank.java`: Equality testing, `instanceof` validation, clone verification.

### [Practical 4](./Practical%204) - Strings, Regex & Command Parsing
- **Part A:**
  - `PasswordChecker.java`: Regex checks for length, uppercase, digit, and special characters; returns "Weak", "Medium", or "Strong".
  - `ChatFilter.java`: `split(" ", 3)`, case-insensitive keyword filtering with `StringBuilder` report.
  - `TemplateFiller.java`: Regex `\{(\w+)\}` placeholder replacement using `Matcher`.
- **Part B:**
  - `Validator.java`: Compiled regex patterns for mobile, email, PAN, IFSC, and amount.
  - `Command.java` & `TransactionType.java`: Record for commands and enum transaction types.
  - `CommandParser.java`: Parses strings like `"DEPOSIT AC0001 500"`.
  - `StatementFormatter.java`: Assembles multi-line bank statement via `StringBuilder`.
  - `MiniBank.java`: Tests validators, parses command tokens, prints formatted statement.

### [Practical 5](./Practical%205) - Abstract Classes & Polymorphism
- **Part A:**
  - `ShapeDemo.java`: Abstract `Shape`, subclasses `Circle`, `Rectangle`, `Triangle`. Polymorphic area calculation.
  - `EmployeeTest.java`: Abstract `Employee`, `FullTime`, `PartTime`, `Intern`. Pattern matching `instanceof`.
  - `MediaDemo.java`: Abstract `MediaItem`, late fee computation for `Book`, `Dvd`, `Magazine`.
- **Part B:**
  - `Account.java`: Abstract class with abstract `interestRate()` and `canWithdraw(long amount)`.
  - `SavingsAccount.java`: 4.0% interest, minimum balance enforcement.
  - `CurrentAccount.java`: 0.0% interest, overdraft limit support.
  - `FixedDepositAccount.java`: 7.0% interest, locked until maturity.
  - `MiniBank.java`: Polymorphic account array, pattern matching `instanceof` handling each account type.

### [Practical 6](./Practical%206) - Interfaces, Packages & Runnable JAR
- **Part A:**
  - `RemoteControlDemo.java`: Interface `Switchable` with default `toggle()`, functional interface `SwitchPermission` as anonymous class and lambda.
  - `NotifierDemo.java`: Functional interface `Notifier`, marker interface `Urgent`.
  - `DiscountDemo.java`: Functional interface `DiscountRule` with runtime selectable lambdas.
- **Part B:**
  - `model/Transactable.java`, `model/InterestBearing.java`, `model/WithdrawRule.java`, `model/Premium.java`.
  - Package structure: `model`, `util`, `service`.
  - `service/MiniBank.java`: Static import of `Validator`, anonymous class vs lambda for `WithdrawRule`.
  - `minibank.jar`: Runnable JAR with manifest (`java -jar minibank.jar`).

### [Practical 7](./Practical%207) - Annotations & Reflection
- **Part A:**
  - `FormValidatorDemo.java`: Custom `@NotBlank` and `@MaxLength` reflection validator.
  - `MiniTestRunnerDemo.java`: Custom `@Run` annotation test runner using reflection.
  - `ColumnMapperDemo.java`: Custom `@Column(name)` reflective row mapper.
- **Part B:**
  - `model/annotation/Id.java`, `@Positive`, `@MaxLength`.
  - `util/AnnotationValidator.java`: Reflective validator checking `@Positive` and `@MaxLength`.
  - `MiniBank.java`: Verifies validation failure on negative account balance and excess length.

### [Practical 8](./Practical%208) - Custom Exceptions & Resources
- **Part A:**
  - `GuardedCalculator.java`: Custom `DivideByZeroException`, separate catch blocks, `finally` logging.
  - `WarehouseDemo.java`: Checked `OutOfStockException` with shortfall, `InvalidQuantityException`.
  - `AutoCloseableDemo.java`: `AutoCloseable` resource in try-with-resources.
- **Part B:**
  - `exception/BankException.java`, `InsufficientFundsException` (with `shortfall`), `AccountNotFoundException`, `InvalidAmountException`.
  - `model/Account.java`: `withdraw()` and `deposit()` declaring checked exceptions, `transfer()` with try-catch-finally.
  - `MiniBank.java`: Catches `InsufficientFundsException` printing exact shortfall ("short by 4000"), `BankSession` try-with-resources.

### [Practical 9](./Practical%209) - Multithreading & Synchronization
- **Part A:**
  - `CounterRaceDemo.java`: Unsynchronized vs synchronized counter across 10 threads.
  - `SeatBookingRaceDemo.java`: Demonstrates race overselling (5 seats across 10 threads) and synchronized fix.
  - `ArraySumRaceDemo.java`: Parallel array sum with race condition, synchronized block fix, and `AtomicLong` fix.
- **Part B:**
  - `AccountWorker.java`: Implements `Runnable` for batch deposits.
  - `Account.java`: Unsynchronized deposit vs synchronized deposit.
  - `MiniBank.java`: 10 threads x 1,000 deposits. Unsynchronized yields < 10,000; synchronized guarantees exactly 10,000.

### [Practical 10](./Practical%2010) - Thread Pools & Deadlock Avoidance
- **Part A:**
  - `ThreadPoolRunnerDemo.java`: `Executors.newFixedThreadPool(3)` executing 10 tasks.
  - `ProducerConsumerDemo.java`: Bounded buffer coordinated with `wait()` and `notifyAll()`.
  - `DeadlockDemo.java`: Lock inversion deadlock demonstration and consistent lock order fix.
- **Part B:**
  - `TransactionProcessor.java`: Managed pool of 4 worker threads with `submit()` and `stop()`.
  - `MiniBank.java`: Batch transactions on thread pool, producer-consumer transaction queue with `wait()`/`notify()`, deadlock avoidance on concurrent transfers (`A->B` and `B->A`) using ordered locking on `accountNumber`.

---

## 🚀 How to Run

### In VS Code:
1. Open the workspace file via **File > Open Workspace from File...** and select `JavaPracticals.code-workspace`.
2. Click the **Run** button on any `.java` file with a `main` method.

### From Command Line:
```bash
# Example: Running Practical 1 MiniBank
cd "Practical 1"
javac *.java
java MiniBank

# Example: Running Practical 6 Runnable JAR
cd "../Practical 6"
java -jar minibank.jar

# Example: Running Practical 9 Multithreading Demo
cd "../Practical 9"
javac *.java
java MiniBank

# Example: Running Practical 10 Deadlock Avoidance Demo
cd "../Practical 10"
javac *.java
java MiniBank
```
