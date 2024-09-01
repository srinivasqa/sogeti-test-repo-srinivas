Feature:Sogeti API Test task

  Scenario:API_Test_TC001 Verify response time and
    Given I trigger a GET call to url "http://api.zippopotam.us" with endpoint "/de/bw/stuttgart"
    Then I verify response status code should be 200
    Then I verify if response content type is "application/json"
    Then I verify if response time should be less than 1 seconds
    Then I verify if "country" field contains the value as "Germany"
    Then I verify if "state" field contains the value as "Baden-Württemberg"
    Then I verify if post code "70597" has place name as "Stuttgart Degerloch"

  Scenario Outline:API_Test_TC002 Verify place names for various postal codes
    Given I send a GET request to the Zippopotam API for country "<country>" and postal code "<postal_code>"
    Then the response status code should be 200
    And the response content type should be JSON
    And the response time should be less than 1 seconds
    And the response should contain place name "<place_name>"

    Examples:
      | country | postal_code | place_name     |
      | us      | 90210       | Beverly Hills  |
      | us      | 12345       | Schenectady    |
      | ca      | B2R         | Waverley       |

