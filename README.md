
# 🧮 String Calculator TDD Kata (Java)

A fully test-driven solution to the classic String Calculator Kata.  
Developed in Java using JUnit 4 and follows the Red → Green → Refactor TDD cycle strictly.

---

## ✅ Features Implemented

- [x] Return 0 for empty string
- [x] Handle 1 or 2 comma-separated numbers
- [x] Support any number of comma-separated numbers
- [x] Allow newline (`\n`) as a valid delimiter
- [x] Support custom single-character delimiters (e.g., `//;\n1;2`)
- [x] Support custom delimiters of any length (e.g., `//[***]\n1***2***3`)
- [x] Support multiple custom delimiters (e.g., `//[*][%]\n1*2%3`)
- [x] Support multiple delimiters with length > 1 (e.g., `//[**][%%]\n1**2%%3`)
- [x] Throw exception on negative numbers and list them all
- [x] Ignore numbers > 1000
- [x] Count how many times `add()` was called using `getCalledCount()`

---

## 🧪 TDD Cycle Demonstration

Each feature was developed using the TDD (Test-Driven Development) cycle:  
**Red** – Write a failing test → **Green** – Make the test pass → **Refactor** – Clean up the code.

All test progress screenshots are organized below.

| Step | Description                                     | Red (Failing)                                      | Green (Passing)                   |
|------|-------------------------------------------------|----------------------------------------------------|-----------------------------------|
| 1    | Empty string returns 0                          | ![](assets/test-results/1.0.png)                   | ![](assets/test-results/1.1.png)  |
| 2    | Single number returns itself                    | ![](assets/test-results/2.0.png)                   | ![](assets/test-results/2.1.png)  |
| 3    | Two comma-separated numbers                     | ![](assets/test-results/3.0.png)                   | ![](assets/test-results/3.1.png)  |
| 4    | Multiple comma-separated numbers                | ![](assets/test-results/4.0.png)                   | ![](assets/test-results/4.1.png)  |
| 5    | Support newline delimiters                      | ![](assets/test-results/5.0.png)                   | ![](assets/test-results/5.1.png)  |
| 6    | Support custom delimiter `//;\n1;2`             | ![](assets/test-results/6.0.png)                   | ![](assets/test-results/6.1.png)  |
| 7    | Handle negative numbers (throws error)          | ![](assets/test-results/7.0.png)                   | ![](assets/test-results/7.1.png)  |
| 8    | Track add() call count (compile error)          | ![](assets/test-results/9.0%20Compilation%20error.png) | ![](assets/test-results/9.1.png)  |
| 9    | Track add() call count for multiple add() calls | ![](assets/test-results/10.0.png)                  | ![](assets/test-results/10.1.png) |
| 10   | Ignore numbers > 1000                           | ![](assets/test-results/11.0.png)                  | ![](assets/test-results/11.1.png) |
| 11   | Support multi-char multi-delims `[**][%%]`      | ![](assets/test-results/12.0.png)                  | ![](assets/test-results/12.1.png) |
| 12   | Final refactor and full test pass               | ![](assets/test-results/13.0.png)                  | ✅ All tests passing               |

---

## 📦 Project Structure

```
string-calculator-kata/
├── src/
│   └── main/java/org/example/Calculator.java
│   └── test/java/CalculatorTest.java
├── assets/
│   └── test-results/*.png
└── README.md
```

---

## 🧪 Running the Tests

Make sure you have Java and Maven or JUnit configured in IntelliJ/CLI.

To run tests using CLI:

```bash
mvn test
```

Or run tests using IntelliJ’s built-in test runner.

---

## 🛠️ Tech Stack

- Java 17+ (works on JDK 8+)
- JUnit 4.13.2
- Maven (optional)
- IntelliJ IDEA (for development)

---

## 🙌 Author

**Kushal Zinzuvadia**  
TDD Enthusiast | Software Developer | Java, Spring Boot, React  
[LinkedIn](https://www.linkedin.com/in/kushalzinzuvadia) | [GitHub](https://github.com/YOUR_USERNAME)

---

## 📜 License

This project is open-source and available under the [MIT License](LICENSE).

---