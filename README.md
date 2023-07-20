# jprq-runner

jprq-runner is a Java project that allows you to expose your locally hosted service to the internet, making it accessible globally. It utilizes the jprq.io service to achieve this functionality.

## Prerequisites

Before using jprq-runner, make sure you have the following installed on your computer:

- Java Development Kit (JDK) 8 or higher
- A GitHub account

## Getting Started

1. Visit the jprq.io website and obtain your authentication token by running the following command:

   ```
   $ jprq auth <your-auth-token>
   ```

   Replace `<your-auth-token>` with the token you receive from the jprq.io website.

2. Clone the jprq-runner project from GitHub:

   ```
   git clone https://github.com/username/jprq-runner.git
   ```

   Replace `username` with your GitHub username.

3. Navigate to the project folder:

   ```
   cd jprq-runner
   ```

4. Build the project using Maven:

   ```
   mvn clean package
   ```

## Running the Application

To run the application and open a port for others to access, use the command:

```
java -jar target/jprq-runner-1.0-SNAPSHOT.jar
```

Make sure to choose a port number that is not in use. For example, you can use port 8080. The application will now expose your local service to the internet, making it globally accessible.

Note: Once you have entered your authentication token, you don't need to re-enter it every time you run the application. Simply execute the `java -jar` command again to start the service.

## Contact Information

If you have any questions or need further assistance, feel free to contact me through Telegram, Email, or Phone:

- Telegram: @Ravahanbek_98
- Email: ravshanbek9918@gmail.com
- Phone: +998901389918

Enjoy using jprq-runner and sharing your locally hosted projects with the world!
