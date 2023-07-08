Feature: Validating Place API's

  @AddPlaceAPI
  Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<phone_number>"
    When user calls "AddPlaceAPI" with Post http request
    Then the API call got success with ststus code 200
    And "status" in response body is "OK"
    And Verify place_Id created maps to "<name>" using getPlaceAPI

    Examples: 
      | name                         |  | phone_number |
      | Abhishek  Amitabh  Srivastav |  | 98087 45637  |

  @DeletePlaceAPI
  Scenario: Verify if Place is being Successfully delete using DeletePlaceAPI
    Given Add Delete Place Payload
    When user calls "DeletePlaceAPI" with Delete http request
    Then the API call got success with ststus2 code 200
    And "status" in response body of detele is "OK"
