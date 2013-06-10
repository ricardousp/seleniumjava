Scenario: browsing product 

Given I am a shopper
When I am viewing HomePage
Then I search for <product>
Then I enter <product> in search box
Then I click search button
Then I browse through <product> pages



Examples:
|product|
|Fridges and Freezers|
