# Tonnochy Capital Trading Core

This is the backend of [Tonnochy Capital project](https://github.com/TP-Capital/).

## Prerequisites
- [jdk](https://www.oracle.com/in/java/technologies/downloads/) >= 19.x
- [maven](https://maven.apache.org/download.cgi) >= 3.8.x
- [ansible](https://docs.ansible.com/ansible/latest/installation_guide/intro_installation.html) >= 2.15.x
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) (recommended) but you can also use your favorite IDEs.

## Getting started with project
1. Clone this project to your local machine.
```sh
git clone https://github.com/TP-Capital/TC-Trading-Core.git
                            or
git clone git@github.com:TP-Capital/TC-Trading-Core.git
```
> You can also use IntelliJ IDEA to clone this project.


2. Open this project with your favorite IDE.

3. Decrypt the `.env` file for the api keys and secret keys of the exchanges.
```sh
ansible-vault decrypt .env
```
> You can get the decryption password for ansible vault from the project owner.

4. Run `mvn clean install` to install all dependencies.

5. Run `mvn spring-boot:run` to start the project.
> If you want to run this project with `IntelliJ IDEA`, you can simply click the `Run` button on the top right corner by navigatiing a file in `src/main/java/Spring/TPTradingStart.java`.

## FAQs
1. How to make contribution to this project?
> You can read the [CONTRIBUTING.md](CONTRIBUTING.md) file to learn more about how to contribute to this project.


2. How to request a feature or report a bug?
> You can open an issue in this repository to report a bug.


3. How to close a issue in the commit/PR message?
> You can use the keyword `closes #issue_number` to tag a issue in the commit/PR.

4. How to create a PR(Pull Request)?
> You can read the [CONTRIBUTING.md](CONTRIBUTING.md) file to learn more about how to create a PR.

## Caution
- Please do not push any sensitive information to this repository like `.env` file after decryption or any other files that contains sensitive information.

## License
This software and its source code (the "Software") are the confidential and proprietary information of Tonnochy Capital. Unauthorized copying, modification, distribution, or use of the Software, or any portion thereof, is strictly prohibited.

### License Terms

1. **Usage Restrictions:** You are prohibited from using, copying, modifying, distributing, sublicensing, or selling the Software, or any part thereof, except as expressly permitted in writing by the Owner.

2. **Confidentiality:** You agree to treat the Software as confidential information and take all reasonable steps to prevent unauthorized access, use, or disclosure of the Software.

3. **No Reverse Engineering:** You may not reverse engineer, decompile, or disassemble the Software, or otherwise attempt to derive the source code, structure, or algorithms of the Software.

4. **No Redistribution:** You may not distribute, sublicense, or make the Software available to any third party, including by making the Software available on any public or private repository, without the explicit written consent of the Owner.

## Contact

For inquiries about licensing, usage permissions, and other related matters, please contact Tonnochy Capital at [info@tonnochycapital.com](mailto:info@tonnochycapital.com).

## © 2023 Tonnochy Capital