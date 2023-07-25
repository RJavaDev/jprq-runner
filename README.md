# jprq-runner

jprq-runner is a Java project that allows you to make your local computer's port globally accessible and visible to others. This is achieved by using the token provided by the [jprq.io](https://jprq.io/) website. The project is base by Azimjon Pulatov's jprq, which simplifies the process of running jprq on your computer. With jprq-runner, you can easily expose your locally hosted service to the internet, allowing others to access it globally.

## Prerequisites

Before using jprq-runner, make sure you have the following installed on your computer:

- Java Development Kit (JDK) 8 or higher
- A GitHub account

## Getting Started

1. Visit the [jprq.io](https://jprq.io) website to learn more about the service and how it works.

2. Obtain the jprq.exe tool developed by [Azimjon Pulatov](https://azimjon.com/) from his [GitHub repository](https://github.com/azimjohn/jprq). This tool will be used by jprq-runner to expose your local service to the internet.

3. Clone the jprq-runner project from GitHub:

   ```
   git clone https://github.com/RJavaDev/jprq-runner.git
   ```

4. Navigate to the project folder:

   ```
   cd jprq-runner
   ```

5. Build the project using Maven:

   ```
   mvn clean package
   ```

## Running the Application

To run the jprq-runner application and open a port for others to access, follow these steps:

1. Obtain your authentication token from the [jprq.io](https://jprq.io) website by running the following command:

   ```
   $ jprq auth <your-auth-token>
   ```

   Replace `<your-auth-token>` with the token you receive from the website.

2. Execute the jprq.exe tool:

   ```
   java -jar target/jprq-runner-1.0-SNAPSHOT.jar
   ```
After creating a jar file, you need to use it by adding the files folder next to it, because the necessary files for the jprq-runner project are in this case.
   Make sure to choose a port number that is not in use. For example, you can use port 8080. The application will now expose your local service to the internet, making it globally accessible.

Note: Once you have entered your authentication token, you don't need to re-enter it every time you run the application. Simply execute the `java -jar` command again to start the service.

## Contact Information

If you have any questions or need further assistance, feel free to contact me through Telegram, Email, or Phone:

- Telegram: [@Ravahanbek_98](https://t.me/Ravahanbek_98)
- Email: ravshanbek9918@gmail.com
- Phone: +998901389918

Enjoy using jprq-runner and sharing your locally hosted projects with the world!
