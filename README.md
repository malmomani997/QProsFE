# QProsFE Automation Testing Framework

# Overview
echo "# QProsFE Automation Testing Framework

## Overview

This repository contains the automation testing framework for the QProsFE project. The framework is designed for web application testing and includes test scenarios, helper classes, and configurations.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Setup and Configuration](#setup-and-configuration)
- [Test Execution](#test-execution)
- [Test Reports](#test-reports)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

Before running the tests, ensure you have the following installed:

- [Java](https://www.java.com/) (version 8 or higher)
- [Maven](https://maven.apache.org/)
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) for managing WebDriver binaries

## Project Structure

The project is structured as follows:

- \`src/main\`: Main source code directory.
- \`src/test\`: Test source code directory.
- \`Extensions\`: Directory to store browser extension files.
- \`reports\`: Directory to store test reports.
- \`screenshots\`: Directory to store screenshots captured during test execution.
- \`chromeRun.xml\` and \`firefoxRun.xml\`: TestNG XML files for configuring test runs.

## Setup and Configuration

1. **Clone the Repository:**
   \`\`\`bash
   git clone https://github.com/your-username/QProsFE.git
   cd QProsFE
   \`\`\`

2. **Install Dependencies:**
   \`\`\`bash
   mvn clean install
   \`\`\`

3. **WebDriver Setup:**
   - Ensure WebDriver binaries are managed by WebDriverManager in \`BaseTest.java\`.
   - Modify WebDriver configurations in \`chromeRun.xml\` and \`firefoxRun.xml\` as needed.

## Test Execution

### Chrome
\`\`\`bash
mvn test -P chrome
\`\`\`

### Firefox
\`\`\`bash
mvn test -P firefox
\`\`\`

## Test Reports

Test reports are generated using ExtentReports and can be found in the \`reports\` directory.

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.

## License

This project is licensed under the [MIT License](LICENSE).
" > README.md

# Display completion message
echo "Readme content has been added to README.md"
