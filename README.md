**Author: Durga Srinivas Wuyyuru**


**Assessment expected output:**
Total testcases: **7**
Tests Passed: **6**
Test Failed: **1**

**Explanation for failed testcase:** 
One testcase will is expected to be failed, because to submit the contact form successfully, user need to verify the captcha.

Technically most of the captcha's cannot be automated as they contains images and most of the times human intervention is needed.

**Suggestion/Workaround for Automating captcha:**
1. Captcha's can be disabled in QA environment.
2. As most of the captcha's are from third party providers, we can request provider for API or alternate solutions.

**Prerequisites to execute script:**
1. Maven.
2. Java 11 or higher version.

**Steps to execute script:**

**Method 1:**
1. Navigate to folder where "pom.xml" is present.
2. Open command prompt.
3. Run command "mvn clean"
4. Run command "mvn build"
5. Run command "mvn test"

**Method 2**
1. Import project in IDE(Eclipse, IntelliJ) as maven project.
2. Navigate to "\src\test\java\org\runner".
3. Open "TestRunner" class.
4. Right click and select "Run".

**Note:**

 The current script will invoke chrome browser.
 
 In case of changing the browser, navigate to \src\main\config.
 
 Open "Config.properties" and replace the "browser" property with keywords same like below.
 
     chrome
     firefox
     edge

