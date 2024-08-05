@sogeti_UI
Feature:Sogeti UI Test task

  Background: prerequistes to tests
    Given I open url "https://www.sogeti.com/"
    And I click on "btnDeclineOptionalCookies"

  Scenario:UI_Test_TC001 Verify Services and Automation state is selected
    When I mouse hover on "Services"
    And I click on "linkAutomation"
    Then I verify if the element "AutomationPage" contains text "Automation"
    And I mouse hover on "Services"
    Then I verify if elements "Services" and "linkAutomation" are selected

  Scenario:UI_Test_TC002 Verify user fills contact form and submit
    When I mouse hover on "Services"
    And I click on "linkAutomation"
    Then I verify if the element "AutomationPage" contains text "Automation"
    And I scroll the page until element "ContactUs"
    Given I enter random input in "First Name" field
    Given I enter random input in "Last Name" field
    Given I enter random input in "Email" field
    Given I enter random input in "Phone" field
    Given I enter random input in "Message" field
    And I click on "checkBox"
    And I switch to captcha frame and select captcha check box
    #captcha's cannot be automated without the support from service provider
    #Why? -- The main intension of having captcha in application is to avoid the traffic caused by robot/automation actions.
    #     -- Technically most of the captcha's cannot be automated as they contains images and most of the times human observation is needed.
    #Workarounds: 1. Captcha's can be disabled in QA environment.
    #             2. As most of the captcha's are from third party providers, we can request provider for API or alternate solutions.

    And I click on "Submit"
    

  Scenario:UI_Test_TC003 Verify user fills contact form and submit
    When I click on "Worldwide"
    Then I open all links under "countryList" and check if links are working