# Selenium Web Automation with Java

### __PRE-REQUISITES__
#### Java
- [Install Java](https://www.oracle.com/java/technologies/downloads/#jdk21-mac)
- Update Environment Variables
  - ex: Mac > Terminal > open Bash or Zsh file > create/update Java Home
    ```
    export JAVA_HOME=/library/Java/JavaVirtualMachines/jdk-{version}.jdk/Contents/Home/bin
    
    ```

#### Maven 
- [Install Maven](https://maven.apache.org/download.cgi)
- [Install Maven Step by Step](https://www.lambdatest.com/blog/getting-started-with-maven-for-selenium-testing/)
- For Mac, follow [Install Maven - Mac](https://www.digitalocean.com/community/tutorials/install-maven-mac-os)
- Open ZSH file and create/update the maven environment variable
  - ```
    export M2_HOME="/Users/{username}/Maven/apache-maven-{version}"
    PATH="${M2_HOME}/bin:${PATH}"
    export PATH
    ```
- Check if Maven is installed correctly --> `mvn -version`

#### IDE
Install an IDE for the Selenium Project
- [IntelliJ](https://www.jetbrains.com/idea/download/?section=mac)
- [Eclipse](https://eclipseide.org/getting-started/)


### __INSTALLATION__

#### TestNG
- Install via Maven and the following via POM.xml
  ```
  <!-- https://mvnrepository.com/artifact/org.testng/testng -->
 		<dependency>
 		    <groupId>org.testng</groupId>
 		    <artifactId>testng</artifactId>
 		    <version>${testng.version}</version>
 		    <scope>test</scope>
 		</dependency>
  ```
- Create TestNG.xml File
  - For eclipse only, 
      1. Right-click the project > TestNG > Convert TestNG
      2. Pop-up should appear to create the xml file
      3. <img width="799" alt="Refactoring" src="https://github.com/user-attachments/assets/29b92172-d5b0-44ff-82f5-807a92d1a9e7" />

#### Allure

- For Mac, Install allure via Homebrew `brew install allure`
- For IDE, set environment variable
    ```
    export ALLURE_HOME="/opt/homebrew/Cellar/allure/{version}"
    PATH="${ALLURE_HOME}/bin:${PATH}"
    export PATH
    ```
- Set dependencies in POM XML
- Create allure-results folder manually in target/allure-results path.
    - Possible to get duplicate allure-results folders
        1. One of them in project directory
        2. Another in target folder
        3. These can conflict with one another , delete the one in the project directory
- Use `allure serve "target/allure-results" ` 
    1. Creates a temporary report of your results with the results from the specified path
- Use `allure generate --clean “target/allure-results” `
    1. Creates a permanent report of your results with the temp results from the specified path
- Use `allure open` to open the newly generated report
- References: 
  1. https://allurereport.org/docs/
  2. https://qameta.io/blog/allure-report-hands-on/
  3. https://github.com/allure-framework/allure2/issues/1614
  4. https://medium.com/getir/allure-usage-in-maven-projects-1900152e7a11
  5. https://www.programsbuzz.com/article/allure-results-directory-path-setup-allure-java
  6. https://sarkershantonu.github.io/2021/04/29/allure2-annotations/
