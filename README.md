# GitHub User Activity CLI

A lightweight command-line interface (CLI) tool built in Java to fetch and display a user's recent public activity from the GitHub API.

Built as part of the [roadmap.sh](https://roadmap.sh/) project challenges — specifically the [GitHub User Activity](https://roadmap.sh/projects/github-user-activity) project — this focuses on raw HTTP network requests, JSON tree parsing, and robust error handling, without relying on heavy external web frameworks.

## Features

- **Dynamic API Fetching** — Queries the live GitHub Events endpoint for any valid username.
- **JSON Tree Parsing** — Uses Jackson `ObjectMapper` and `JsonNode` to safely read and extract nested fields.
- **Human-Readable Translation** — Converts raw API events into clean terminal sentences (`PushEvent`, `WatchEvent`, `CreateEvent`, etc.).
- **Graceful Error Handling** — Catches missing arguments and handles HTTP status errors (like `404 Not Found` or `403 Forbidden`) smoothly.

## Prerequisites

- **Java Development Kit (JDK)** — Java 21 or higher
- **Apache Maven** — for dependency management and compilation

## Project Structure

```text
GithubCLi/
│
├── src/
│   └── main/
│       └── java/
│           └── org/
│               └── example/
│                   ├── GithubActivity.java   # Entry point & argument validation
│                   ├── Http.java             # Network client & status code handling
│                   ├── GithubEvent.java      # Strict Java records for mapping
│                   └── ParseJSON.java        # JSON tree parsing & event translation
│
├── .gitignore
├── README.md
└── pom.xml                                   # Maven configuration & Jackson dependencies
```

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/GithubCLi.git
cd GithubCLi
```

### 2. Compile and run

```bash
mvn compile exec:java -Dexec.mainClass="org.example.GithubActivity" -Dexec.args="<github-username>"
```

## Example Usage

```bash
mvn compile exec:java -Dexec.mainClass="org.example.GithubActivity" -Dexec.args="utkarshu281"
```

**Output:**

```text
- Starred utkarshu281/repo-name
------------------------------------------
- Pushed 3 commits to utkarshu281/repo-name
------------------------------------------
```

## Built With

- **Java 21**
- **Apache Maven**
- **Jackson** — JSON parsing (`ObjectMapper`, `JsonNode`)
- **GitHub Events API**

## License

This project is open source and available under the [MIT License](LICENSE).
